import { dropdownHandler } from "./dropdown.js";

export default class Admin {
  constructor() {
    dropdownHandler();
    this.table = document.querySelector("#content-admin-table");
    this.getTable("orders");
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
      const keys = Object.keys(response[0]);

      // thead HTML
      let theadHTML = "";

      for (let i = 0; i < keys.length; i++) {
        theadHTML += `<th>${
          keys[i].charAt(0).toUpperCase() + keys[i].slice(1)
        }</th>`;
      }

      // keys.forEach((item) => {
      //   theadHTML += `<th>${item.charAt(0).toUpperCase() + item.slice(1)}</th>`;
      // });

      // tbody HTML

      let tbodyHTML = "";

      response.forEach((item) => {
        let row = "";

        for (let i = 0; i < keys.length; i++) {
          if (keys[i] === "id") return;
          const value = item[keys[i]];
          if (typeof value !== "object" || value === null) {
            row += `<td>${value}</td>`;
            return;
          }
          if (!Array.isArray(value)) {
            row += `<td>${value.id}</td>`;
            return;
          }
          row += `<td>${value.map((item) => item.id).join(", ")}</td>`;
        }

        // keys.forEach((key) => {
        //   if (key === "id") return;
        //   const value = item[key];
        //   if (typeof value !== "object" || value === null) {
        //     row += `<td>${value}</td>`;
        //     return;
        //   }
        //   if (!Array.isArray(value)) {
        //     row += `<td>${value.id}</td>`;
        //     return;
        //   }
        //   row += `<td>${value.map((item) => item.id).join(", ")}</td>`;
        // });

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
}
