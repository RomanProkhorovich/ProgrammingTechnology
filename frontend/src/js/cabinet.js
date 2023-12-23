export default class Cabinet {
  constructor() {
    this.tabOrders = document.querySelector("#tab-orders");
    this.tabData = document.querySelector("#tab-data");
    this.tabAbout = document.querySelector("#tab-about");

    this.registerEvents();
  }

  // EDIT USER FIELD
  editUserField() {
    xhr = new XMLHttpRequest();
    xhr.open("POST", "http://localhost:8080/api/v1/orders");
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = () => {
      if (xhr.readyState !== 4 || xhr.status !== 200) {
        return;
      }
      console.log(JSON.parse(xhr.responseText));
    };
    xhr.send();
  }

  // ORDERS RENDERING XHR
  getOrders() {
    const xhr = new XMLHttpRequest();
    xhr.open("GET", "http://localhost:8080/api/v1/orders");
    let email = "cherni@example.ru";
    let pass = "password";
    xhr.setRequestHeader("Authorization", "Basic " + btoa(`${email}:${pass}`));
    xhr.onreadystatechange = () => {
      if (xhr.readyState !== 4 || xhr.status !== 200) {
        return;
      }

      const orders = JSON.parse(xhr.responseText);

      console.log(orders);

      const ordersContainer = document.querySelector(".content-cabinet-orders");

      orders.forEach((item) => {
        ordersContainer.insertAdjacentHTML(
          "beforeend",
          `<div class="content-cabinet-order">
          <p class="content-cabinet-order-info">Заказ №${затычка} от ${затычка} в ${затычка}</p>
          <div class="content-cabinet-order-description-dishes">
              <div class="content-cabinet-order-description-dish">
                  <h1 class="content-cabinet-order-description-dish-name">
                      name
                  </h1>
                  <p class="content-cabinet-order-description-dish-price">
                      <span>9999</span>р.
                  </p>
              </div>
          </div>
          <div class="content-cabinet-order-description-data">
              <h1>Способ получения:</h1>
              <p>${затычка}</p>
              <h1>Способ оплаты:</h1>
              <p>${затычка}</p>
              <h1>Адрес:</h1>
              <p>${затычка}</p>
          </div>
      </div>`
        );
      });
    };
    xhr.send();
  }

  registerEvents() {
    // TAB LISTENERS
    this.tabOrders.addEventListener("click", () => {
      document
        .querySelector(".content-cabinet-data")
        .classList.add("display-none");

      document
        .querySelector(".content-cabinet-orders")
        .classList.remove("display-none");

      document
        .querySelector(".content-cabinet-about")
        .classList.add("display-none");

      this.tabOrders.classList.add("content-cabinet-tab-active");
      this.tabData.classList.remove("content-cabinet-tab-active");
      this.tabAbout.classList.remove("content-cabinet-tab-active");
    });
    this.tabData.addEventListener("click", () => {
      document
        .querySelector(".content-cabinet-data")
        .classList.remove("display-none");

      document
        .querySelector(".content-cabinet-orders")
        .classList.add("display-none");

      document
        .querySelector(".content-cabinet-about")
        .classList.add("display-none");

      this.tabOrders.classList.remove("content-cabinet-tab-active");
      this.tabData.classList.add("content-cabinet-tab-active");
      this.tabAbout.classList.remove("content-cabinet-tab-active");
    });
    this.tabAbout.addEventListener("click", () => {
      document
        .querySelector(".content-cabinet-data")
        .classList.add("display-none");

      document
        .querySelector(".content-cabinet-orders")
        .classList.add("display-none");

      document
        .querySelector(".content-cabinet-about")
        .classList.remove("display-none");

      this.tabOrders.classList.remove("content-cabinet-tab-active");
      this.tabData.classList.remove("content-cabinet-tab-active");
      this.tabAbout.classList.add("content-cabinet-tab-active");
    });
    // LISTENER
  }
}
