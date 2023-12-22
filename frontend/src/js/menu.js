export default class Menu {
  constructor() {
    this.cartContent = document.querySelector(".header-cart-content");
    this.dishes = [];

    this.getDishes();
  }

  getDishesLS() {
    const dishes = JSON.parse(localStorage.getItem("Menu"));

    const dishesContainer = document.querySelector(".content-menu");

    dishes.forEach((item) => {
      dishesContainer.insertAdjacentHTML(
        "beforeend",
        `<div class="content-menu-dish" data-menu-id="${item.id}">
        <img class="content-menu-dish-photo" src="data:image/png;base64,${item.photo}" alt="${item.name}">
        <h1>${item.name}</h1>
        <p class="content-menu-dish-description">${item.description}</p>
        <p class="content-menu-dish-cart-price">
          <span class="content-menu-dish-cart-price-value">${item.price}</span>р.
        </p>
        <div class="content-menu-dish-cart-quantity">
            <button class="content-menu-dish-cart-button">В корзину</button>
        </div>
    </div>`
      );
    });
    this.addToCart();
  }
  // DISHES RENDERING XHR
  getDishes() {
    if (!document.querySelector(".content-menu")) return;
    const xhr = new XMLHttpRequest();
    xhr.open("GET", "http://localhost:8080/api/v1/menus/actual");
    let email = "cherni@example.ru";
    let pass = "password";
    xhr.setRequestHeader("Authorization", "Basic " + btoa(`${email}:${pass}`));
    xhr.onreadystatechange = () => {
      if (xhr.readyState !== 4 || xhr.status !== 200) {
        return;
      }

      const dishes = JSON.parse(xhr.responseText).dishes;

      const dishesContainer = document.querySelector(".content-menu");

      dishes.forEach((item) => {
        this.dishes.push(item);
        dishesContainer.insertAdjacentHTML(
          "beforeend",
          `<div class="content-menu-dish" data-menu-id="${item.id}">
          <img class="content-menu-dish-photo" src="data:image/png;base64,${item.photo}" alt="${item.name}">
          <h1>${item.name}</h1>
          <p class="content-menu-dish-description">${item.description}</p>
          <p class="content-menu-dish-cart-price">
            <span class="content-menu-dish-cart-price-value">${item.price}</span>р.
          </p>
          <div class="content-menu-dish-cart-quantity">
              <button class="content-menu-dish-cart-button">В корзину</button>
          </div>
      </div>`
        );
      });
      localStorage.setItem("Menu", JSON.stringify(this.dishes));
      this.addToCart();
    };
    xhr.send();
  }

  // SWITCH TO QUANTITY CONTROL
  toQuantity(elem, id, quantity) {
    const parent = elem.parentElement;

    elem.classList.add("display-none");
    elem.insertAdjacentHTML(
      "afterend",
      `<div class="quantity" data-quantity-id="${id}">
              <span class="quantity-control minus">-</span><input class="quantity-value"
                  value="${quantity}"></input><span class="quantity-control plus">+</span>
          </div>`
    );
    const cartQuantity = this.cartContent.querySelector(
      `[data-quantity-id="${id}"] input`
    );
    cartQuantity.dispatchEvent(new Event("change", { bubbles: true }));
    const plus = parent.querySelector(".plus");
    const minus = parent.querySelector(".minus");

    plus.addEventListener("click", (e) => {
      cartQuantity.value++;
      cartQuantity.dispatchEvent(new Event("change", { bubbles: true }));
    });

    minus.addEventListener("click", (e) => {
      cartQuantity.value--;
      cartQuantity.dispatchEvent(new Event("change", { bubbles: true }));
    });
  }
  // ADD DISH TO CART LISTENER
  addToCart() {
    document
      .querySelectorAll(".content-menu-dish-cart-button")
      .forEach((item) => {
        const dish = item.closest(".content-menu-dish");
        const id = dish.dataset.menuId;
        // already in cart
        try {
          const fromCart = JSON.parse(localStorage.getItem("Cart")).find(
            (item) => {
              return item.id === id;
            }
          );
          if (fromCart) {
            this.toQuantity(item, id, fromCart.quantity);
            return;
          }
        } catch (E) {
          console.log("cart is empty");
        }
        // not in cart
        item.addEventListener("click", (e) => {
          e.preventDefault();

          const src = dish.querySelector(".content-menu-dish-photo").src;
          const alt = dish.querySelector(".content-menu-dish-photo").alt;
          const name = dish.querySelector("h1").textContent;
          const price = dish.querySelector(
            ".content-menu-dish-cart-price-value"
          ).textContent;

          document.querySelector("#cart-to-order").insertAdjacentHTML(
            "beforebegin",
            `<div class="header-cart-content-dish" data-cart-id="${id}">
      <img class="header-cart-content-dish-photo" src="${src}" alt="${alt}">
      <div class="header-cart-content-dish-info">
          <h1>${name}</h1>
          <p class="header-cart-content-dish-info-quantity">
            <div class="quantity" data-quantity-id="${id}">
              <span class="quantity-control minus">-</span><input class="quantity-value"
              value="1"></input><span class="quantity-control plus">+</span>
            </div>
          </p>
          <p class="header-cart-content-dish-info-price"><span
              class="content-menu-dish-cart-price-value">${price}</span>р.</p>
      </div>
  </div>`
          );
          this.toQuantity(item, id, 1);
        });
      });
  }
}

console.log("menu script loaded");
