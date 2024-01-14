import Alert from "./alert.js";

export default class Courier {
  constructor() {
    this.tabActive = document.querySelector("#tab-active");
    this.tabFinished = document.querySelector("#tab-finished");
    this.contentFinished = document.querySelector(".content-courier-finished");
    this.contentActive = document.querySelector(".content-courier-active");

    this.check();
    this.getActiveOrders();
    this.getFinishedOrders();
    this.registerEvents();

    setInterval(() => {
      this.getActiveOrders();
      this.getFinishedOrders();
    }, 30000);
  }

  check() {
    const user = JSON.parse(localStorage.getItem("User"));
    if (!user || !user.role || user.role !== "Courier")
      window.location.href = document.querySelector(".header-logo a").href;
  }

  addStatusListeners() {
    document.querySelectorAll(".order-button").forEach((item) => {
      item.addEventListener("click", (e) => {
        const order = e.target.closest(".order");
        this.changeOrderStatus(order.dataset.orderId);

        this.getActiveOrders();
        this.getFinishedOrders();
      });
    });
  }

  // CARTITEMS TO HTML
  getDishesHTML(dishes) {
    let html = "";
    dishes.forEach((item) => {
      html += `<div class="content-cabinet-order-description-dish">
      <h1 class="content-cabinet-order-description-dish-name">
          ${item.dish.name}
      </h1>
      <p class="content-cabinet-order-description-dish-price">
          <span>${item.dish.price}</span> р. <span>${item.count}</span>шт.
      </p>
  </div>`;
    });
    return html;
  }

  changeOrderStatus(id) {
    const xhr = new XMLHttpRequest();
    xhr.open("PUT", `http://localhost:8080/api/v1/courier?id=${id}`);
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
      if (xhr.readyState === 4 || xhr.status === 200) {
        this.getActiveOrders();
        this.getFinishedOrders();
      } else {
        new Alert("error", "Ошибка изменения статуса заказа", 3000);
      }
    };
    xhr.send(JSON.stringify({ id: id }));
  }

  // ORDERS RENDERING XHR
  getFinishedOrders() {
    const xhr = new XMLHttpRequest();
    xhr.open("GET", "http://localhost:8080/api/v1/courier?actual=false");
    const user = JSON.parse(localStorage.getItem("User"));
    if (!user) return;
    const username = user.username;
    const password = user.password;
    xhr.setRequestHeader(
      "Authorization",
      "Basic " + btoa(`${username}:${password}`)
    );
    xhr.onreadystatechange = () => {
      if (xhr.readyState !== 4 || xhr.status !== 200) {
        return;
      }

      const orders = JSON.parse(xhr.responseText);

      console.log(orders);

      const ordersContainer = document.querySelector(
        ".content-courier-finished"
      );

      let html = "";
      orders.forEach((item) => {
        html += `<div class="order" data-order-id="${item.id}">
        <div class="order-header">
            <p class="order-info">
                Заказ №${item.id} от ${new Date(
          item.orderTime
        ).toLocaleString()}
            </p>
        </div>
        <div class="order-description-dishes">
            ${this.getDishesHTML(item.cartItems)}
            <div class="order-description-sum">
                <h1>
                    Итого
                </h1>
                <p class="order-description-dish-price">
                    <span>${item.sum}</span> р.
                </p>
            </div>
        </div>
        <div class="order-description-data">
            <h1>Способ оплаты:</h1>
            <p>${"Картой курьеру"}</p>
            <h1>Адрес:</h1>
            <p>${item.address}</p>
            <h1>Адрес ресторана:</h1>
            <p>${item.restaurant.address}</p>
        </div>
    </div>`;
      });
      ordersContainer.innerHTML = html;
    };
    xhr.send();
  }

  getActiveOrders() {
    const xhr = new XMLHttpRequest();
    xhr.open("GET", "http://localhost:8080/api/v1/courier?actual=true");
    const user = JSON.parse(localStorage.getItem("User"));
    if (!user) return;
    const username = user.username;
    const password = user.password;
    xhr.setRequestHeader(
      "Authorization",
      "Basic " + btoa(`${username}:${password}`)
    );
    xhr.onreadystatechange = () => {
      if (xhr.readyState !== 4 || xhr.status !== 200) {
        return;
      }

      const orders = JSON.parse(xhr.responseText);

      console.log(orders);

      const ordersContainer = document.querySelector(".content-courier-active");

      let html = "";

      orders.forEach((item) => {
        let caption;
        if (item.orderStatus.id === 4) caption = "Заказ доставлен";
        else caption = "Заказ взят в работу";
        html += `<div class="order" data-order-id="${item.id}">
        <div class="order-header">
            <p class="order-info">
                Заказ №${item.id} от ${new Date(
          item.orderTime
        ).toLocaleString()}
            </p>
            <button class="order-button">
                ${caption}
            </button>
        </div>
        <div class="order-description-dishes">
            ${this.getDishesHTML(item.cartItems)}
            <div class="order-description-sum">
                <h1>
                    Итого
                </h1>
                <p class="order-description-dish-price">
                    <span>${item.sum}</span> р.
                </p>
            </div>
        </div>
        <div class="order-description-data">
            <h1>Способ оплаты:</h1>
            <p>${"Картой курьеру"}</p>
            <h1>Адрес:</h1>
            <p>${item.address}</p>
            <h1>Адрес ресторана:</h1>
            <p>${item.restaurant.address}</p>
        </div>
    </div>`;
      });
      ordersContainer.innerHTML = html;
      this.addStatusListeners();
    };
    xhr.send();
  }

  registerEvents() {
    this.tabActive.addEventListener("click", () => {
      this.tabActive.classList.add("tab-active");
      this.tabFinished.classList.remove("tab-active");
      this.contentActive.classList.remove("display-none");
      this.contentFinished.classList.add("display-none");
    });
    this.tabFinished.addEventListener("click", () => {
      this.tabActive.classList.remove("tab-active");
      this.tabFinished.classList.add("tab-active");
      this.contentActive.classList.add("display-none");
      this.contentFinished.classList.remove("display-none");
    });
    // CHANGE STATUS
  }
}
