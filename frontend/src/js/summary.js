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
    this.dropdown = document.querySelectorAll(".content-summary-dropdown");
    this.dropdownOptions = document.querySelectorAll(
      ".content-summary-dropdown-options"
    );

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
    console.log(params.dishes);
    params.address = document.querySelector("#order-address").textContent;
    params.receivingType =
      document.querySelector("#order-delivery").textContent;

    const xhr = new XMLHttpRequest();
    xhr.open("POST", "http://localhost:8080/api/v1/orders");
    let email = "cherni@example.ru";
    let pass = "password";
    xhr.setRequestHeader("Authorization", "Basic " + btoa(`${email}:${pass}`));
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = () => {
      if (xhr.readyState !== 4 || xhr.status !== 200) {
        return;
      }
      console.log(JSON.parse(xhr.responseText));
    };
    console.log(params);
    xhr.send(JSON.stringify(params));
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
              <span class="content-summary-dish-price-value">${price}</span>р.
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
    const email = "cherni@example.ru";
    const pass = "password";
    const authHeader = "Basic " + btoa(`${email}:${pass}`);

    const xhrDelivery = new XMLHttpRequest();
    xhrDelivery.open("GET", "http://localhost:8080/api/v1/receiving");
    xhrDelivery.setRequestHeader("Authorization", authHeader);
    xhrDelivery.onreadystatechange = () => {
      if (xhrDelivery.readyState !== 4 || xhrDelivery.status !== 200) {
        return;
      }
      const response = JSON.parse(xhrDelivery.responseText);
      const value = document.querySelector("#order-delivery");
      const curDropdown = value.closest(".content-summary-dropdown");
      const options = curDropdown.querySelector(
        ".content-summary-dropdown-options"
      );
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
      const curDropdown = value.closest(".content-summary-dropdown");
      const options = curDropdown.querySelector(
        ".content-summary-dropdown-options"
      );
      curDropdown.querySelector(".dropdown-value").textContent = response[0];
      response.forEach((item) => {
        options.insertAdjacentHTML("beforeend", `<a href="#">${item}</a>`);
      });
    };
    xhrAddresses.send();
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
    // EXPAND DROPDOWNS
    this.dropdown.forEach((item) => {
      item.addEventListener("click", (e) => {
        const container = e.target.closest(".content-summary-dropdown");
        if (!container.classList.contains("on-top")) {
          container.classList.remove("on-top");
        }
        container.classList.toggle("on-top");
        container
          .querySelector(".content-summary-dropdown-options")
          .classList.toggle("dropdown-show");
        container
          .querySelector(".dropdown-arrow")
          .classList.toggle("dropdown-arrow-collapsed");
      });
      this.sum.textContent = this.cartSum.textContent;
    });
    // DROPDOWNS OPTION
    this.dropdownOptions.forEach((item) => {
      item.addEventListener("click", (e) => {
        const container = e.target.closest(".content-summary-dropdown");
        container.querySelector(".dropdown-value").textContent =
          e.target.textContent;
      });
    });
    //ORDER
    this.orderButton.addEventListener("click", this.sendOrder);
  }
}

console.log("summary script loaded");
