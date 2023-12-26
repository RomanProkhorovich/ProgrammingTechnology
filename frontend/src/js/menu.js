export default class Menu {
  constructor() {
    this.cartContent = document.querySelector(".header-cart-content");

    this.getDishes();
  }

  getDishesLS() {
    const dishes = JSON.parse(localStorage.getItem("Menu"));
    sessionStorage.setItem("Menu", JSON.stringify(dishes));

    const dishesContainer = document.querySelector(".content-menu");

    dishes.forEach((item) => {
      dishesContainer.insertAdjacentHTML(
        "beforeend",
        `<div class="content-menu-dish" data-menu-id="${item.id}">
        <img class="content-menu-dish-photo" src="data:image/png;base64,${item.photo}" alt="${item.name}">
        <h1>${item.name}</h1>
        <p class="content-menu-dish-description">${item.description}</p>
        <p class="content-menu-dish-cart-price">
          <span class="content-menu-dish-cart-price-value">${item.price}</span> р.
        </p>
        <div class="content-menu-dish-cart-quantity">
            <button class="content-menu-dish-cart-button">В корзину</button>
        </div>
    </div>`
      );
    });
    this.addToCart();
    this.popupDish();
  }
  // DISHES RENDERING XHR
  getDishes() {
    if (!document.querySelector(".content-menu")) return;
    const xhr = new XMLHttpRequest();
    xhr.open("GET", "http://localhost:8080/api/v1/menus/actual");
    xhr.onreadystatechange = () => {
      if (xhr.readyState !== 4 || xhr.status !== 200) {
        return;
      }

      const dishes = JSON.parse(xhr.responseText).dishes;

      const dishesContainer = document.querySelector(".content-menu");

      dishes.forEach((item) => {
        dishesContainer.insertAdjacentHTML(
          "beforeend",
          `<div class="content-menu-dish" data-menu-id="${item.id}">
          <img class="content-menu-dish-photo" src="data:image/png;base64,${item.photo}" alt="${item.name}">
          <h1>${item.name}</h1>
          <p class="content-menu-dish-description">${item.description}</p>
          <p class="content-menu-dish-cart-price">
            <span class="content-menu-dish-cart-price-value">${item.price}</span> р.
          </p>
          <div class="content-menu-dish-cart-quantity">
              <button class="content-menu-dish-cart-button">В корзину</button>
          </div>
      </div>`
        );
      });
      sessionStorage.setItem("Menu", JSON.stringify(dishes));
      this.addToCart();
      this.popupDish();
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
    const plus = parent.querySelector(".plus");
    const minus = parent.querySelector(".minus");

    cartQuantity.dispatchEvent(new Event("change", { bubbles: true }));

    plus.addEventListener("click", (e) => {
      cartQuantity.value++;
      cartQuantity.dispatchEvent(new Event("change", { bubbles: true }));
    });

    minus.addEventListener("click", (e) => {
      cartQuantity.value--;
      cartQuantity.dispatchEvent(new Event("change", { bubbles: true }));
    });
  }

  cartButtonHandler(elem, dish) {
    // already in cart
    try {
      const fromCart = JSON.parse(localStorage.getItem("Cart")).find((item) => {
        return +item.id === +dish.id;
      });
      if (fromCart) {
        this.toQuantity(elem, dish.id, fromCart.quantity);
      }
    } catch (E) {
      console.log(E);
    }
    // not in cart
    elem.addEventListener("click", (e) => {
      document.querySelector("#cart-to-order").insertAdjacentHTML(
        "beforebegin",
        `<div class="header-cart-content-dish" data-cart-id="${dish.id}">
      <img class="header-cart-content-dish-photo" src="data:image/png;base64,${dish.photo}" alt="${dish.name}">
      <div class="header-cart-content-dish-info">
          <h1>${dish.name}</h1>
          <p class="header-cart-content-dish-info-quantity">
            <div class="quantity" data-quantity-id="${dish.id}">
              <span class="quantity-control minus">-</span><input class="quantity-value"
              value="1"></input><span class="quantity-control plus">+</span>
            </div>
          </p>
          <p class="header-cart-content-dish-info-price"><span
              class="content-menu-dish-cart-price-value">${dish.price}</span> р.</p>
      </div>
  </div>`
      );
      this.toQuantity(elem, dish.id, 1);
      if (elem.closest(".popup")) {
        this.toQuantity(
          document.querySelector(`[data-menu-id="${dish.id}"] button`),
          dish.id,
          1
        );
      }
    });
  }

  // ADD DISH TO CART LISTENER
  addToCart() {
    document
      .querySelectorAll(".content-menu-dish-cart-button")
      .forEach((item) => {
        const id = item.closest(".content-menu-dish").dataset.menuId;
        const dish = JSON.parse(sessionStorage.getItem("Menu")).find(
          (item) => +item.id === +id
        );

        this.cartButtonHandler(item, dish);
      });
  }

  popupDish() {
    document.querySelectorAll(".content-menu-dish").forEach((item) => {
      item.addEventListener("click", (e) => {
        if (
          e.target.closest(".content-menu-dish-cart-quantity") ||
          e.target.classList.contains("quantity-control")
        )
          return;
        const id = item.dataset.menuId;
        const dish = JSON.parse(sessionStorage.getItem("Menu")).find(
          (item) => +item.id === +id
        );

        document.body.insertAdjacentHTML(
          "afterbegin",
          `<div class="popup">
        <div class="popup-dish">
            <div class="popup-dish-photo">
                <img src="data:image/png;base64,${dish.photo}" alt="${dish.name}">
            </div>
            <div class="popup-dish-info">
                <h1>${dish.name}</h1>
                <p class="popup-dish-info-description">
                    ${dish.description}
                </p>
                <p>Ккал. на порцию: <span>${dish.calories}</span> ккал.</p>
                <p>Вес: <span>${dish.weight}</span> г.</p>
                <div class="popup-dish-footer">
                    <h1 class="popup-dish-info-price">
                        <span class="popup-dish-info-price-value">${dish.price}</span> р.
                    </h1>
                    <div class="popup-dish-quantity">
                        <button class="content-menu-dish-cart-button">В корзину</button>
                    </div>
                </div>
            </div>
        </div>
    </div>`
        );

        this.cartButtonHandler(
          document.querySelector(".popup .content-menu-dish-cart-button"),
          dish
        );
      });
    });
  }
}

console.log("menu script loaded");
