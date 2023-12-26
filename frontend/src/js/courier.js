export default class Courier {
  constructor() {
    this.tabWaiting = document.querySelector("#tab-waiting");
    this.tabActive = document.querySelector("#tab-active");
    this.contentWaiting = document.querySelector(".content-courier-waiting");
    this.contentActive = document.querySelector(".content-courier-active");

    this.registerEvents();
  }
  // ORDERS RENDERING XHR
  getOrders() {
    const xhr = new XMLHttpRequest();
    xhr.open("GET", "http://localhost:8080/api/v1/orders");
    const user = JSON.parse(localStorage.getItem("User"));
    const email = user.username;
    const pass = user.password;
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
          <p class="content-cabinet-order-info">Заказ №${item.id} от ${new Date(
            item.orderTime
          ).toLocaleString()}</p>
          <div class="content-cabinet-order-description-dishes">
              ${this.getDishesHTML(item.cartItems)}
              <div class="content-cabinet-order-description-sum">
              <h1>
                  Итого
              </h1>
              <p class="content-cabinet-order-description-dish-price">
                  <span>${item.sum}</span> р.
              </p>
              </div>
          </div>
          <div class="content-cabinet-order-description-data">
              <h1>Способ получения:</h1>
              <p>${item.receivingType.name}</p>
              <h1>Способ оплаты:</h1>
              <p>${"Картой курьеру"}</p>
              <h1>Адрес:</h1>
              <p>${item.address}</p>
          </div>
      </div>`
        );
      });
    };
    xhr.send();
  }

  registerEvents() {
    this.tabWaiting.addEventListener("click", () => {
      this.tabActive.classList.remove("content-cabinet-tab-active");
      this.tabWaiting.classList.add("content-cabinet-tab-active");
      this.contentActive.classList.add(".display-none");
      this.contentWaiting.classList.remove(".display-none");
    });
    this.tabActive.addEventListener("click", () => {
      this.tabActive.classList.add("content-cabinet-tab-active");
      this.tabWaiting.classList.remove("content-cabinet-tab-active");
      this.contentActive.classList.remove(".display-none");
      this.contentWaiting.classList.add(".display-none");
    });
  }
}
