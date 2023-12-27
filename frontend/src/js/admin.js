import { dropdownHandler } from "./dropdown.js";

export default class Admin {
  constructor() {
    this.tableDropdown = document.querySelector("#content-admin-dropdown");
    this.tableOptions = this.tableDropdown.querySelector(".dropdown-options");
    this.getOptions();
    dropdownHandler();
    this.table = document.querySelector("#content-admin-table");
    this.inputListener();
    this.endpoint = "users/all";
    this.getTable(this.endpoint);

    this.registerEvents();
  }

  getOptions() {
    const role = JSON.parse(localStorage.getItem("User")).role;
    if ((role !== "Admin" && role !== "Manager") || !role)
      window.location.href = document.querySelector(".header-logo a").href;
    if (role === "Admin") {
      document.querySelector('.content-admin').classList.remove('display-none')
      this.tableOptions.insertAdjacentHTML(
        "beforeend",
        `<a href="#" data-endpoint="users/all">Пользователи</a>
        <a href="#" data-endpoint="restaurants/all">Рестораны</a>
        <a href="#" data-endpoint="dishes/all">Блюда</a>
        <a href="#" data-endpoint="orders/all">Заказы</a>`
      );
      return;
    }
    document.querySelector('.content-manager').classList.remove('display-none')
    document.querySelector('.tabs').classList.remove('display-none')
    this.tableOptions.insertAdjacentHTML(
      "beforeend",
      `<a href="#" data-endpoint="users/all">Пользователи</a>
      <a href="#" data-endpoint="orders/all">Заказы</a>`
    );
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
    xhr.open("GET", "http://localhost:8080/api/v1/orders");
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
    };
    xhr.send({ actual: true });
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
      console.log(response);

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
