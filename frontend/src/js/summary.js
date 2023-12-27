import { dropdownHandler } from "./dropdown.js";
import Alert from "./alert.js";

export default class Summary {
  constructor() {
    this.getDropdownContent();
    this.cartContent = document.querySelector(".header-cart-content");
    this.container = document.querySelector(".content-summary");
    this.cartSum = document.querySelector(".header-cart-sum-value");
    this.sum = document.querySelector(
      ".content-summary-paymethods-total-value"
    );
    this.orderButton = document.querySelector(
      ".content-summary-paymethods-button"
    );
    this.dropdown = document.querySelectorAll(".dropdown");
    this.dropdownOptions = document.querySelectorAll(".dropdown-options");
    this.addressButton = document.querySelector("#add-address");

    this.renderDishes();
    this.registerEvents();
  }

  // SEND ORDER
  sendOrder() {
    const params = {};

    params.dishes = Array.from(JSON.parse(localStorage.getItem("Cart"))).map(
      (item) => {
        return { id: item.id, count: item.quantity };
      }
    );
    params.address = document.querySelector("#order-address").textContent;
    params.receivingType =
      document.querySelector("#order-delivery").textContent;

    const xhr = new XMLHttpRequest();
    xhr.open("POST", "http://localhost:8080/api/v1/orders");
    const user = JSON.parse(localStorage.getItem("User"));
    if (!user) return;
    const username = user.username;
    const password = user.password;
    xhr.setRequestHeader(
      "Authorization",
      "Basic " + btoa(`${username}:${password}`)
    );
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = () => {
      if (xhr.readyState !== 4 || xhr.status !== 200) {
        return;
      }

      localStorage.setItem("Cart", JSON.stringify([]));
      new Alert(
        "success",
        "Заказ оформлен успешно. Перенаправление на главную страницу...",
        3000,
        document.querySelector(".header-logo a").href
      );
    };
    xhr.send(JSON.stringify(params));
    setTimeout(() => {
      this.orderButton.disabled = false;
    }, 4000);
  }

  insertDish(id, name, quantity, description, photo, price) {
    this.container.insertAdjacentHTML(
      "beforeend",
      `<div class="content-summary-dish">
      <img src="data:image/png;base64,${photo}" alt="${name}">
      <div class="content-summary-dish-info">
          <h1 class="content-summary-dish-name">${name}</h1>
          <div class="quantity" data-quantity-id="${id}">
              <span class="quantity-control minus">-</span><input class="quantity-value"
                  value="${quantity}"></input><span class="quantity-control plus">+</span>
          </div>
          <p class="content-summary-dish-description">${description}</p>
          <p class="content-summary-dish-price">
              <span class="content-summary-dish-price-value">${price}</span> р.
          </p>
      </div>
  </div>`
    );
  }

  renderDishes() {
    const cartDishes = JSON.parse(localStorage.getItem("Cart"));
    if (!cartDishes) return;
    const dishes = JSON.parse(sessionStorage.getItem("Menu"));
    cartDishes.forEach((item) => {
      const curDish = dishes.find((dish) => dish.id === item.id);
      this.insertDish(
        item.id,
        curDish.name,
        item.quantity,
        curDish.description,
        curDish.photo,
        curDish.price
      );
    });
  }

  getDropdownContent() {
    const user = JSON.parse(localStorage.getItem("User"));
    if (!user) return;
    const username = user.username;
    const password = user.password;
    const authHeader = "Basic " + btoa(`${username}:${password}`);

    const xhrDelivery = new XMLHttpRequest();
    xhrDelivery.open("GET", "http://localhost:8080/api/v1/receiving");
    xhrDelivery.setRequestHeader("Authorization", authHeader);
    xhrDelivery.onreadystatechange = () => {
      if (xhrDelivery.readyState !== 4 || xhrDelivery.status !== 200) {
        return;
      }
      const response = JSON.parse(xhrDelivery.responseText);
      const value = document.querySelector("#order-delivery");
      const curDropdown = value.closest(".dropdown");
      const options = curDropdown.querySelector(".dropdown-options");
      curDropdown.querySelector(".dropdown-value").textContent =
        response[0].name;
      response.forEach((item) => {
        options.insertAdjacentHTML(
          "beforeend",
          `<a href="#" data-delivery-id="${item.id}">${item.name}</a>`
        );
      });
    };
    xhrDelivery.send();

    const xhrAddresses = new XMLHttpRequest();
    xhrAddresses.open("GET", "http://localhost:8080/api/v1/orders/addresses");
    xhrAddresses.setRequestHeader("Authorization", authHeader);
    xhrAddresses.onreadystatechange = () => {
      if (xhrAddresses.readyState !== 4 || xhrAddresses.status !== 200) {
        return;
      }
      const response = JSON.parse(xhrAddresses.responseText);
      const value = document.querySelector("#order-address");
      const curDropdown = value.closest(".dropdown");
      const options = curDropdown.querySelector(".dropdown-options a");
      curDropdown.querySelector(".dropdown-value").textContent = response[0]
        ? response[0]
        : "Вы ещё не добавили адрес";
      response.forEach((item) => {
        options.insertAdjacentHTML("beforebegin", `<a href="#">${item}</a>`);
      });
    };
    xhrAddresses.send();

    const xhrPayMethods = new XMLHttpRequest();
    xhrPayMethods.open("GET", "http://localhost:8080/api/v1/paymethods");
    xhrPayMethods.setRequestHeader("Authorization", authHeader);
    xhrPayMethods.onreadystatechange = () => {
      if (xhrPayMethods.readyState !== 4 || xhrPayMethods.status !== 200) {
        return;
      }
      const response = JSON.parse(xhrPayMethods.responseText);
      const value = document.querySelector("#order-paymethods");
      const curDropdown = value.closest(".dropdown");
      const options = curDropdown.querySelector(".dropdown-options");
      curDropdown.querySelector(".dropdown-value").textContent =
        response[0].name;
      response.forEach((item) => {
        options.insertAdjacentHTML(
          "beforeend",
          `<a href="#" data-paymethods-id="${item.id}">${item.name}</a>`
        );
      });
    };
    xhrPayMethods.send();
  }

  registerEvents() {
    // QUANTITY CONTROL
    this.container.querySelectorAll(".quantity").forEach((item) => {
      const id = item.dataset.quantityId;
      const plus = item.querySelector(".plus");
      const minus = item.querySelector(".minus");
      const cartQuantity = this.cartContent.querySelector(
        `[data-quantity-id="${id}"] input`
      );

      plus.addEventListener("click", () => {
        cartQuantity.value++;
        cartQuantity.dispatchEvent(new Event("change", { bubbles: true }));
      });

      minus.addEventListener("click", (e) => {
        cartQuantity.value--;
        cartQuantity.dispatchEvent(new Event("change", { bubbles: true }));
      });
    });

    dropdownHandler();

    this.sum.textContent = this.cartSum.textContent;

    //ORDER
    this.orderButton.addEventListener("click", this.sendOrder);

    this.addressButton.addEventListener("click", () => {
      document.body.insertAdjacentHTML(
        "afterbegin",
        `<div class="popup hidden">
        <form id="address-form">
            <h1>
                Добавление адреса
            </h1>
            <input type="text" placeholder="Адрес" name="address" value="">
            <button id="confirm-button" type="button">Добавить</button>
        </form>
    </div>`
      );
      const popup = document.querySelector(".popup");
      setTimeout(() => {
        popup.classList.remove("hidden");
      }, 0);
      popup.querySelector("#confirm-button").addEventListener("click", () => {
        const newAddress = popup.querySelector("input").value;

        document.querySelector("#order-address").textContent = newAddress;
        document
          .querySelector("#add-address")
          .insertAdjacentHTML("beforebegin", `<a href="#">${newAddress}</a>`);

        popup.remove();
      });
    });
  }
}

console.log("summary script loaded");
