export default class Cabinet {
  constructor() {
    this.tabOrders = document.querySelector("#tab-orders");
    this.tabData = document.querySelector("#tab-data");
    this.saveData = document.querySelector("#content-cabinet-data-form-button");

    this.firstname = document.querySelector(`[name="firstname"]`);
    this.lastname = document.querySelector(`[name="lastname"]`);
    this.surname = document.querySelector(`[name="surname"]`);
    this.email = document.querySelector(`[name="email"]`);
    this.phone = document.querySelector(`[name="phone"]`);
    this.password = document.querySelector(`[name="password"]`);

    this.cabinetButtons = document.querySelectorAll(
      ".content-cabinet-data-label button"
    );

    this.getUser();
    this.getOrders();
    this.registerEvents();
  }

  // GET CURRENT USER DATA
  getUser() {
    const xhr = new XMLHttpRequest();
    xhr.open("GET", "http://localhost:8080/api/v1/users");
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

      this.firstname.value = response.firstname;
      this.lastname.value = response.lastname;
      this.surname.value = response.surname;
      this.email.value = response.email;
      this.phone.value = response.phone;
      this.password.value = password;
    };
    xhr.send();
  }

  // DISHES FROM ORDER TO HTML
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

  // ORDERS RENDERING XHR
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

      const orders = JSON.parse(xhr.responseText);

      const ordersContainer = document.querySelector(".content-cabinet-orders");

      orders.forEach((item) => {
        ordersContainer.insertAdjacentHTML(
          "afterbegin",
          `<div class="order">
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
    // TAB LISTENERS
    this.tabOrders.addEventListener("click", () => {
      document
        .querySelector(".content-cabinet-data")
        .classList.add("display-none");

      document
        .querySelector(".content-cabinet-orders")
        .classList.remove("display-none");

      this.tabOrders.classList.add("tab-active");
      this.tabData.classList.remove("tab-active");
    });
    this.tabData.addEventListener("click", () => {
      document
        .querySelector(".content-cabinet-data")
        .classList.remove("display-none");

      document
        .querySelector(".content-cabinet-orders")
        .classList.add("display-none");

      this.tabOrders.classList.remove("tab-active");
      this.tabData.classList.add("tab-active");
    });
    // SAVE USER DATA LISTENER
    this.saveData.addEventListener("click", () => {
      const user = JSON.parse(localStorage.getItem("User"));
      if (!user) return;
      const username = user.username;
      const password = user.password;

      let params = {};

      Array.from(document.forms[0].getElementsByTagName("input")).forEach(
        (item) => {
          params[item.name] = item.value;
        }
      );

      params["id"] = user.id;
      params["role"] = user.role;

      console.log(JSON.stringify(params));

      const xhr = new XMLHttpRequest();
      xhr.open("PUT", "http://localhost:8080/api/v1/users");
      xhr.setRequestHeader(
        "Authorization",
        "Basic " + btoa(`${username}:${password}`)
      );
      xhr.setRequestHeader("Content-Type", "application/json");
      xhr.onreadystatechange = () => {
        if (xhr.readyState !== 4 || xhr.status !== 200) {
          return;
        }
      };
      xhr.send(JSON.stringify(params));
    });
  }
}
