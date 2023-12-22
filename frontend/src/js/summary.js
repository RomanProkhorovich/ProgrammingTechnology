export default class Summary {
  constructor() {
    this.container = document.querySelector(".content-summary");
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

    params[dishes] = Array.from(JSON.parse(localStorage.getItem("Cart")));

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
    console.log(dishes);
    xhr.send(
      JSON.stringify({
        dishes: dishes,
        address: "hui",
        deliveryTime: new Date(),
      })
    );
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
    const dishes = JSON.parse(localStorage.getItem("Menu"));
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

  registerEvents() {
    // EXPAND DROPDOWNS
    this.dropdown.forEach((item) => {
      item.addEventListener("click", (e) => {
        const container = e.target.closest(".content-summary-dropdown");
        container.classList.toggle("on-top");
        container
          .querySelector(".content-summary-dropdown-options")
          .classList.toggle("dropdown-show");
        container
          .querySelector(".dropdown-arrow")
          .classList.toggle("dropdown-arrow-collapsed");
      });
    });
    // DROPDOWNS OPTION
    this.dropdownOptions.forEach((item) => {
      item.addEventListener("click", (e) => {
        const container = e.target.closest(".content-summary-dropdown");
        container.querySelector(".dropdown-value").textContent =
          e.target.textContent;
      });
    });
    //
  }
}

console.log("summary script loaded");
