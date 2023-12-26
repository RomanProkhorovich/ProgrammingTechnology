import Alert from "./alert.js";

export default class Header {
  constructor() {
    this.accountButton = document.querySelector(".header-account");
    this.accountMenu = document.querySelector(".header-account-menu");
    this.cartButton = document.querySelector(".header-cart-logo");
    this.cartSum = document.querySelector(".header-cart-sum-value");
    this.cartContent = document.querySelector(".header-cart-content");
    this.toSummarize = document.querySelector("#cart-to-order");
    this.summary = document.querySelector(
      ".content-summary-paymethods-total-value"
    );
    this.cartDishes = [];

    this.accountCabinet = document.querySelector("#header-account-menu-logout");
    this.accountLogout = document.querySelector("#header-account-menu-logout");

    if (localStorage.getItem("Cart")) {
      this.cartDishes = JSON.parse(localStorage.getItem("Cart"));
    }

    this.registerEvents();
    this.getActiveOrder();
  }

  // GET DISHES
  getDishes() {
    if (!document.querySelector(".content-menu")) return;
    const xhr = new XMLHttpRequest();
    xhr.open("GET", "http://localhost:8080/api/v1/menus/actual");
    xhr.onreadystatechange = () => {
      if (xhr.readyState !== 4 || xhr.status !== 200) {
        return;
      }

      const dishes = JSON.parse(xhr.responseText).dishes;

      sessionStorage.setItem("Menu", JSON.stringify(dishes));
    };
    xhr.send();
  }

  // GET ACTIVE ORDER
  getActiveOrder() {
    const xhr = new XMLHttpRequest();
    xhr.open("GET", "http://localhost:8080/api/v1/orders");
    const user = JSON.parse(localStorage.getItem("User"));
    if (!user) return;
    const email = user.username;
    const pass = user.password;
    xhr.setRequestHeader("Authorization", "Basic " + btoa(`${email}:${pass}`));
    xhr.onreadystatechange = () => {
      if (xhr.readyState !== 4 || xhr.status !== 200) {
        return;
      }

      const order = JSON.parse(xhr.responseText)[0];

      if (!order) return;
      document.querySelector(".content").insertAdjacentHTML(
        "afterbegin",
        `<div class="content-status">
          <p>Ваш заказ №${order.id} от ${new Date(
          order.orderTime
        ).toLocaleString()} на данный момент ${order.orderStatus.name}</p>
        </div>`
      );
    };
    xhr.send(JSON.stringify({ actual: true }));
  }

  // CART CALC
  calcCart() {
    let arr = [];
    this.cartContent
      .querySelectorAll(".header-cart-content-dish")
      .forEach((item) => {
        arr.push({
          id: +item.dataset.cartId,
          quantity: +item.querySelector(".quantity-value").value,
          price: +item.querySelector(".content-menu-dish-cart-price-value")
            .textContent,
        });
      });
    this.cartSum.textContent = arr.reduce((sum, current) => {
      return sum + current.price * current.quantity;
    }, 0);

    if (this.summary) {
      this.summary.textContent = this.cartSum.textContent;
    }

    localStorage.setItem(
      "Cart",
      JSON.stringify(
        arr.filter((item) => {
          return item.quantity > 0;
        })
      )
    );
  }

  // RENDER CART
  renderCart() {
    if (!this.cartDishes || this.cartDishes.length === 0) return;
    if (!sessionStorage.getItem("Menu")) this.getDishes();
    const dishes = JSON.parse(sessionStorage.getItem("Menu"));
    this.cartDishes.forEach((dish) => {
      const curDish = dishes.find((item) => {
        return item.id === dish.id;
      });

      document.querySelector("#cart-to-order").insertAdjacentHTML(
        "beforebegin",
        `<div class="header-cart-content-dish" data-cart-id="${dish.id}">
    <img class="header-cart-content-dish-photo" src="data:image/png;base64,${curDish.photo}" alt="${curDish.name}">
    <div class="header-cart-content-dish-info">
        <h1>${curDish.name}</h1>
        <p class="header-cart-content-dish-info-quantity">
          <div class="quantity" data-quantity-id="${dish.id}">
            <span class="quantity-control minus">-</span><input class="quantity-value"
            value="${dish.quantity}"></input><span class="quantity-control plus">+</span>
          </div>
        </p>
        <p class="header-cart-content-dish-info-price"><span
            class="content-menu-dish-cart-price-value">${dish.price}</span> р.</p>
    </div>
  </div>`
      );
      this.calcCart();
    });
  }

  // REG/AUTH DATA SEND XHR
  authSendRequest(authForm) {
    let params = {};

    Array.from(authForm.getElementsByTagName("input")).map((item) => {
      params[item.name] = item.value;
    });

    const xhr = new XMLHttpRequest();
    xhr.open(
      "POST",
      `http://localhost:8080/auth${
        authForm.id === "authorization-form" ? "" : "/reg"
      }`
    );
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = () => {
      if (xhr.readyState !== 4 || xhr.status !== 200) {
        return;
      }
      const user = JSON.parse(xhr.responseText);
      console.log(user);

      localStorage.setItem("Auth", 1);
      localStorage.setItem(
        "User",
        JSON.stringify({
          id: user.id,
          username: params.username ? params.username : params.email,
          password: params.password,
          role: user.role.name,
        })
      );

      window.location.reload();

      authForm.closest(".popup").remove();
    };
    xhr.send(JSON.stringify(params));
  }

  // SWITCH TO REGISTRATION
  popupRegForm() {
    const popup = document.querySelector(".popup");
    popup.innerHTML = `<form id="registration-form">
    <h1>
        Регистрация
    </h1>
    <input type="text" placeholder="Фамилия" name="lastname" value="">
    <input type="text" placeholder="Имя" name="firstname" value="">
    <input type="text" placeholder="Отчество (при наличии)" name="surname" value="">
    <input type="text" placeholder="E-mail" name="email" value="">
    <input type="text" placeholder="Номер телефона" name="phone" value="">
    <input type="password" placeholder="Пароль" name="password" value="">
    <button id="registration-button" type="button">Зарегистрироваться</button>
  </form>
  `;
    popup
      .querySelector("#registration-button")
      .addEventListener("click", (e) =>
        this.authSendRequest(e.target.closest("form"))
      );
  }

  // AUTH POPUP
  popupAuthForm() {
    document.body.insertAdjacentHTML(
      "afterbegin",
      `<div class="popup hidden">
      <form id="authorization-form">
          <h1>
              Войдите, чтобы сделать заказ
          </h1>
          <input type="text" placeholder="Номер телефона или E-Mail" name="username" value="">
          <input type="password" placeholder="Пароль" name="password" value="">
          <button id="registration-button" type="button">Зарегистрироваться</button>
          <button id="authorization-button" type="button">Войти</button>
      </form>
  </div>`
    );
    const popup = document.querySelector(".popup");
    setTimeout(() => {
      popup.classList.remove("hidden");
    }, 0);
    popup
      .querySelector("#registration-button")
      .addEventListener("click", () => this.popupRegForm());
    popup
      .querySelector("#authorization-button")
      .addEventListener("click", (e) =>
        this.authSendRequest(e.target.closest("form"))
      );
  }

  registerEvents() {
    // BUTTON FOR ROLE
    if (localStorage.getItem("User")) {
      const role = JSON.parse(localStorage.getItem("User")).role;
      let href, src;
      if (
        document
          .querySelector(".header-account a")
          .getAttribute("href")
          .includes("src/")
      ) {
        src = "./src/svg/";
        href = "./src/html/";
      } else {
        src = "../svg/";
        href = "../html/";
      }
      switch (role) {
        case "Courier":
          this.accountButton.insertAdjacentHTML(
            "beforebegin",
            `<div class="header-account">
                  <a href="${href + "courier.html"}">
                      <img class="header-account-logo" src="${
                        src + "delivery-svgrepo-com.svg"
                      }" alt="">
                  </a>
              </div>`
          );

          break;
        case "Admin":
          this.accountButton.insertAdjacentHTML(
            "beforebegin",
            `<div class="header-account">
                  <a href="${href + "admin.html"}">
                      <img class="header-account-logo" src="${
                        src + "admin-svgrepo-com.svg"
                      }" alt="">
                  </a>
              </div>`
          );

          break;
        case "Manager":
          this.accountButton.insertAdjacentHTML(
            "beforebegin",
            `<div class="header-account">
                  <a href="${href + "admin.html"}">
                      <img class="header-account-logo" src="${
                        src + "admin-svgrepo-com.svg"
                      }" alt="">
                  </a>
              </div>`
          );

          break;

        default:
          break;
      }
    }
    // HIDE POPUP/CART
    document.body.addEventListener("click", (e) => {
      if (e.target.classList.contains("popup")) {
        e.target.classList.add("hidden");
        setTimeout(() => {
          e.target.remove();
        }, 200);
      }
      if (!e.target.closest(".header-cart")) {
        this.cartContent.classList.add("hidden");
        setTimeout(() => {
          this.cartContent.classList.add("display-none");
        }, 200);
      }
      if (!e.target.closest(".header-account")) {
        this.accountMenu.classList.add("hidden");
        setTimeout(() => {
          this.accountMenu.classList.add("display-none");
        }, 200);
      }
    });
    // ACCOUNT LISTENERS
    this.accountButton.addEventListener("click", (e) => {
      if (
        localStorage.getItem("Auth") &&
        +localStorage.getItem("Auth") === +1
      ) {
        if (this.accountMenu.classList.contains("display-none")) {
          this.accountMenu.classList.toggle("display-none");
          setTimeout(() => {
            this.accountMenu.classList.toggle("hidden");
          }, 0);
        } else {
          this.accountMenu.classList.toggle("hidden");
          setTimeout(() => {
            this.accountMenu.classList.toggle("display-none");
          }, 200);
        }
        return;
      }
      e.preventDefault();
      if (e.target === this.accountButton.querySelector(".header-account-logo"))
        this.popupAuthForm();
    });

    this.accountLogout.addEventListener("click", (e) => {
      e.preventDefault();

      localStorage.removeItem("Auth");
      localStorage.removeItem("User");
      localStorage.removeItem("Cart");
      window.location.reload();
    });
    // SHOW/HIDE CART
    this.cartButton.addEventListener("click", () => {
      if (this.cartContent.classList.contains("display-none")) {
        this.cartContent.classList.toggle("display-none");
        setTimeout(() => {
          this.cartContent.classList.toggle("hidden");
        }, 0);
      } else {
        this.cartContent.classList.toggle("hidden");
        setTimeout(() => {
          this.cartContent.classList.toggle("display-none");
        }, 200);
      }
    });
    // TO SUMMARIZE
    this.toSummarize.addEventListener("click", (e) => {
      e.preventDefault();
      if (localStorage.getItem("Auth") && +localStorage.getItem("Auth") === +1)
        return;
      if (
        !localStorage.getItem("Cart") ||
        JSON.parse(localStorage.getItem("Cart")).length === 0
      ) {
        e.preventDefault();
        new Alert(
          "error",
          "Оформление заказа недоступно. Корзина пуста.",
          5000
        );
        return;
      }
      e.preventDefault();
      this.popupAuthForm();
    });
    // CART QUANTITY LISTENER CLICK
    this.cartContent.addEventListener("click", (e) => {
      if (!e.target.classList.contains("quantity-control")) return;

      const value = e.target.closest(".quantity").querySelector("input");

      if (e.target.classList.contains("plus")) {
        value.value++;
        value.dispatchEvent(new Event("change", { bubbles: true }));
        return;
      }
      value.value--;
      value.dispatchEvent(new Event("change", { bubbles: true }));
    });
    // CART QUANTITY LISTENER CHANGE
    this.cartContent.addEventListener("change", (e) => {
      const quantity = e.target.value;
      const id = e.target.closest(".quantity").dataset.quantityId;
      document
        .querySelectorAll(`[data-quantity-id="${id}"]`)
        .forEach((item) => {
          const value = item.querySelector("input");
          value.value = quantity;
        });
      if (quantity < 1) {
        e.preventDefault();
        e.target.closest(".header-cart-content-dish").remove();
        const remaining = document.querySelectorAll(
          `[data-quantity-id="${id}"]`
        );
        if (remaining) {
          remaining.forEach((item) => {
            const parent = item.parentElement;
            if (
              parent.classList.contains("content-menu-dish-cart-quantity") ||
              parent.classList.contains("popup-dish-quantity")
            ) {
              parent
                .querySelector(".content-menu-dish-cart-button")
                .classList.remove("display-none");
              item.remove();
            }
            if (parent.classList.contains("content-summary-dish-info")) {
              item.closest(".content-summary-dish").remove();
            }
          });
        }
      }
      this.calcCart();
    });
    // CART FINAL RENDER
    this.renderCart();
  }
}

// CART EXAMPLE

// [
//   {
//     id: 1,
//     quantity: 4,
//     price: Array.from(JSON.parse(sessionStorage.getItem("Menu"))).find((item) => {
//       return item.id === 1;
//     }).price,
//   },
//   {
//     id: 2,
//     quantity: 6,
//     price: Array.from(JSON.parse(sessionStorage.getItem("Menu"))).find((item) => {
//       return item.id === 2;
//     }).price,
//   },
// ];

// MENU EXAMPLE

// [
//   {
//     id: 1,
//     name: "pitsa",
//     price: 920,
//     calories: 300,
//     weight: 1200,
//     description:
//       "vkusnaya pitsa vkusnaya pitsa vkusnaya pitsa vkusnaya pitsa vkusnaya pitsa vkusnaya pitsa",
//     photo: "",
//   },
//   {
//     id: 2,
//     name: "rolli",
//     price: 321,
//     calories: 200,
//     weight: 900,
//     description:
//       "rolli rolli rolli rolli rolli rolli rolli rolli rolli rolli rolli rolli rolli rolli",
//     photo: "",
//   },
// ];
