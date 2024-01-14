import { dropdownHandler } from "./dropdown.js";

export default class Admin {
  constructor() {
    this.adminContent = document.querySelector(".content-admin");
    this.managerContent = document.querySelector(".content-manager");
    this.couriersHTML = "";
    this.couriers = {};

    this.tableDropdown = document.querySelector("#content-admin-dropdown");
    this.tableOptions = this.tableDropdown.querySelector(".dropdown-options");
    this.getOptions();
    this.table = document.querySelector("#content-admin-table");
    this.inputListener();
    this.endpoint = "users/all";
    this.getTable(this.endpoint);

    this.registerEvents();
  }

  getCouriers() {
    const xhr = new XMLHttpRequest();
    xhr.open("GET", "http://localhost:8080/api/v1/users/couriers");
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
      const response = JSON.parse(xhr.responseText);

      console.log(response);

      let mapped = response.map((item) => {
        this.couriers[`${item.lastname} ${item.firstname} ${item.surname}`] =
          item.id;

        return `<a href="#" data-courier-id="${
          item.id
        }">${`${item.lastname} ${item.firstname} ${item.surname}`}</a>`;
      });

      console.log(mapped);

      this.couriersHTML = `<div class="dropdown" style="margin: 1em;">
          <button type="button">
              <span class="dropdown-value">${
                mapped[0]
              }</span><span class="dropdown-arrow">▼</span>
          </button>
          <div class="dropdown-options">
              ${mapped.join(" ")}
          </div>
      </div>`;

      console.log(this.couriersHTML);
    };
    xhr.send();
  }

  getOptions() {
    const role = JSON.parse(localStorage.getItem("User")).role;
    if ((role !== "Admin" && role !== "Manager") || !role)
      window.location.href = document.querySelector(".header-logo a").href;
    if (role === "Admin") {
      document.querySelector(".content-admin").classList.remove("display-none");
      this.tableOptions.insertAdjacentHTML(
        "beforeend",
        `<a href="#" data-endpoint="users/all">Пользователи</a>
        <a href="#" data-endpoint="restaurants/all">Рестораны</a>
        <a href="#" data-endpoint="dishes/all">Блюда</a>
        <a href="#" data-endpoint="orders/all">Заказы</a>
        <a href="#" data-endpoint="booking">Брони</a>`
      );
      dropdownHandler();
      return;
    }
    document.querySelector(".content-manager").classList.remove("display-none");
    this.tableOptions.insertAdjacentHTML(
      "beforeend",
      `<a href="#" data-endpoint="users/all">Пользователи</a>
      <a href="#" data-endpoint="orders/all">Заказы</a>
      <a href="#" data-endpoint="booking">Брони</a>`
    );
    dropdownHandler();
    this.getCouriers();
    this.getOrders();
  }

  getDishesHTML(dishes) {
    let html = "";
    dishes.forEach((item) => {
      html += `<div class="order-description-dish">
      <h1 class="order-description-dish-name">
          ${item.dish.name}
      </h1>
      <p class="order-description-dish-price">
          <span>${item.dish.price}</span> р. <span>${item.count}</span>шт.
      </p>
  </div>`;
    });
    return html;
  }

  courierButtonHandler() {
    document
      .querySelectorAll(".content-manager-dropdown-button")
      .forEach((item) => {
        item.addEventListener("click", (e) => {
          let orderId = e.target.closest(".order").dataset.orderId;
          let courierName = e.target
            .closest(".order")
            .querySelector(".dropdown-value").textContent;

          console.log(
            orderId + " " + this.couriers + " " + this.couriers[courierName]
          );
          const xhr = new XMLHttpRequest();
          xhr.open(
            "PUT",
            `http://localhost:8080/api/v1/manager/setCourier?order_id=${+orderId}&courier_id=${+this
              .couriers[courierName]}`
          );
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
            location.reload();
          };
          xhr.send(
            JSON.stringify({
              order_id: orderId,
              courier_id: this.couriers[courierName],
            })
          );
        });
      });
  }

  inputListener() {
    const inputs = document.querySelectorAll("#content-admin-table input");
    inputs.forEach((item) => {
      item.addEventListener("change", (e) => {
        e.target.closest("td").style.backgroundColor = "#44cc4420";
        e.target.closest("tr").style.backgroundColor = "#44cc4420";
        e.target.closest("tr").classList.add("changed");
      });
    });
  }

  getOrders() {
    const xhr = new XMLHttpRequest();
    xhr.open("GET", "http://localhost:8080/api/v1/orders/all?actual=true");
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
      const response = JSON.parse(xhr.responseText);

      console.log(response);

      response.forEach((item) => {
        this.managerContent.insertAdjacentHTML(
          "afterbegin",
          `<div class="order" data-order-id="${item.id}">
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
            <p>${item.payMethod.name}</p>
            <h1>Адрес:</h1>
            <p>${
              item.address ? item.address : "ул. Мичурина, д. 148, кв. 139"
            }</p>
            <h1>Адрес ресторана:</h1>
            <p>${
              item.restaurant.address
                ? item.restaurant.address
                : "г. Самара, ул. Калужская, 11"
            }</p>
        </div>
        ${this.couriersHTML}
        <button class="content-manager-dropdown-button" type="button">
            Назначить
        </button>
    </div>`
        );
      });
      this.courierButtonHandler();
    };
    xhr.send();
  }

  getTable(tableName) {
    const xhr = new XMLHttpRequest();
    xhr.open("GET", `http://localhost:8080/api/v1/${tableName}`);
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

      const response = JSON.parse(xhr.responseText);

      if (!response[0]) {
        this.table.innerHTML = "Таблица пуста";
        return;
      }
      const keys = Object.keys(response[0]);

      // thead HTML
      let theadHTML = "";

      for (let i = 0; i < keys.length; i++) {
        if (keys[i] === "id" || keys[i] === "password") continue;
        theadHTML += `<th>${
          keys[i].charAt(0).toUpperCase() + keys[i].slice(1)
        }</th>`;
      }

      // tbody HTML

      let tbodyHTML = "";

      response.forEach((item) => {
        let row = "";

        for (let i = 0; i < keys.length; i++) {
          if (keys[i] === "id" || keys[i] === "password") continue;
          if (keys[i] === "photo") {
            row += `<td><img src="data:image/png;base64,${
              item[keys[i]]
            }"></td>`;
            continue;
          }
          const value = item[keys[i]];
          if (value === null) {
            row += `<td>-</td>`;
            continue;
          }
          if (typeof value === "number") {
            row += `<td>${value}</td>`;
            continue;
          }
          if (typeof value !== "object") {
            let date = new Date(value);
            if (isNaN(date)) row += `<td>${value}</td>`;
            else row += `<td>${date.toLocaleString()}</td>`;
            continue;
          }
          if (!Array.isArray(value)) {
            row += `<td>${value.name ? value.name : value.id}</td>`;
            continue;
          }
          row += `<td>${value.map((item) => item.id).join(", ")}</td>`;
        }

        tbodyHTML += `<tr data-id=${item.id}>${row}</tr>`;
      });

      // render table
      this.table.innerHTML = `<thead>
        <tr>
            ${theadHTML}
        </tr>
      </thead>
      <tbody>
          ${tbodyHTML}
      </tbody>`;

      this.inputListener();
    };
    xhr.send();
  }

  registerEvents() {
    this.tableOptions.addEventListener("click", (e) => {
      this.endpoint = e.target.dataset.endpoint;
      setTimeout(() => {
        this.getTable(this.endpoint);
      }, 0);
    });
  }
}
