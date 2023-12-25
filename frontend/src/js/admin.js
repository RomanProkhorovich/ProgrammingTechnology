import { dropdownHandler } from "./dropdown.js";

export default class Admin {
  constructor() {
    dropdownHandler();
    this.table = document.querySelector("#content-admin-table");
    this.options = document.querySelector(".dropdown-options");
    this.endpoint = "users/all";
    this.getTable(this.endpoint);

    this.registerEvents();
  }

  getTable(tableName) {
    const xhr = new XMLHttpRequest();
    xhr.open("GET", `http://localhost:8080/api/v1/${tableName}`);
    let email = "cherni@example.ru";
    let pass = "password";
    xhr.setRequestHeader("Authorization", "Basic " + btoa(`${email}:${pass}`));
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
        if (keys[i] === "id") continue;
        theadHTML += `<th>${
          keys[i].charAt(0).toUpperCase() + keys[i].slice(1)
        }</th>`;
      }

      // tbody HTML

      let tbodyHTML = "";

      response.forEach((item) => {
        let row = "";

        for (let i = 0; i < keys.length; i++) {
          if (keys[i] === "id") continue;
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
          console.log(value.map((item) => item.id).join(", "));
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
    };
    xhr.send();
  }

  registerEvents() {
    this.options.addEventListener("click", (e) => {
      this.endpoint = e.target.dataset.endpoint;
      setTimeout(() => {
        this.getTable(this.endpoint);
      }, 0);
    });
  }
}
