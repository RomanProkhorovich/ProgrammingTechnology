import menu from "./menu.js";

export default class Header {
  constructor() {
    this.accountButton = document.querySelector(".header-account");
    this.cartButton = document.querySelector(".header-cart-logo");
    this.cartSum = document.querySelector(".header-cart-sum-value");
    this.cartContent = document.querySelector(".header-cart-content");
    this.cartSummary = document.querySelector("#cart-to-order");
    this.cartDishes;

    if (localStorage.getItem("Cart")) {
      this.cartDishes = JSON.parse(localStorage.getItem("Cart"));
    }

    this.registerEvents();
  }

  // CART CALC
  calcCart() {
    let arr = [];
    this.cartContent
      .querySelectorAll(".header-cart-content-dish")
      .forEach((item) => {
        arr.push({
          id: item.dataset.cartId,
          quantity: +item.querySelector(".quantity-value").value,
          price: +item.querySelector(".content-menu-dish-cart-price-value")
            .textContent,
          // price: Array.from(JSON.parse(localStorage.getItem("Menu"))).find(
          //   (item) => {
          //     item.id === item.dataset.cartId;
          //   }
          // ).price,
        });
      });
    this.cartSum.textContent = arr.reduce((sum, current) => {
      return sum + current.price * current.quantity;
    }, 0);

    localStorage.setItem(
      "Cart",
      JSON.stringify(
        arr.filter((item) => {
          return item.quantity > 0;
        })
      )
    );
  }

  renderCart() {
    if (!this.cartDishes) return;

    this.cartDishes.forEach((dish) => {
      const curDish = Array.from(JSON.parse(localStorage.getItem("Menu"))).find(
        (item) => {
          return item.id === dish.id;
        }
      );

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
            class="content-menu-dish-cart-price-value">${dish.price}</span>р.</p>
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
      localStorage.setItem("Auth", 1);
      console.log(JSON.parse(xhr.responseText));
    };
    xhr.send(JSON.stringify(params));

    authForm.closest(".popup").remove();
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
          <input type="text" placeholder="Номер телефона или E-Mail" name="phone-email" value="">
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
    // HIDE POPUP/CART
    document.body.addEventListener("click", (e) => {
      if (e.target.classList.contains("popup")) {
        e.target.classList.add("hidden");
        setTimeout(() => {
          e.target.remove();
        }, 200);
      }
      if (!e.target.closest(".header-cart")) {
        console.log("entered");
        this.cartContent.classList.add("hidden");
        setTimeout(() => {
          this.cartContent.classList.add("display-none");
        }, 200);
      }
    });
    // ACCOUNT LISTENER
    this.accountButton.addEventListener("click", () => {
      if (!localStorage.getItem("Auth")) this.popupAuthForm();
      else {
        window.location.href = "./src/html/cabinet.html";
      }
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
    // CART QUANTITY LISTENER
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
        e.target.closest(".header-cart-content-dish").remove();
      }
      this.calcCart();
    });
    // TO SUMMARY PAGE
    this.cartSummary.addEventListener("click", () => {
      window.location.href = "./src/html/summary.html";
    });
    this.renderCart();
  }
}
