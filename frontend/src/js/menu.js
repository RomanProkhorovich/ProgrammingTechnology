class Menu {
  constructor() {
    this.cartSum = document.querySelector(".header-cart-sum-value");
    this.dishes = [];

    this.getDishes();
  }
  // DISHES RENDERING XHR
  getDishes() {
    if (!document.querySelector(".content-menu")) return;
    const xhr = new XMLHttpRequest();
    xhr.open("GET", "http://localhost:8080/api/v1/dishes");
    let email = "cherni@example.ru";
    let pass = "password";
    xhr.setRequestHeader("Authorization", "Basic " + btoa(`${email}:${pass}`));
    xhr.onreadystatechange = () => {
      if (xhr.readyState !== 4 || xhr.status !== 200) {
        return;
      }

      const dishes = JSON.parse(xhr.responseText);

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
      localStorage.setItem("dishes", JSON.stringify(dishes));
      this.addToCart();
    };
    xhr.send();
  }
  // ADD DISH TO CART LISTENER
  addToCart() {
    document
      .querySelectorAll(".content-menu-dish-cart-button")
      .forEach((item) => {
        item.addEventListener("click", (e) => {
          e.preventDefault();

          const dish = e.target.closest(".content-menu-dish");
          const id = dish.dataset.menuId;
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
            <div class="quantity">
              <span class="quantity-control minus">-</span><input class="quantity-value"
              value="1"></input><span class="quantity-control plus">+</span>
            </div>
          </p>
          <p class="header-cart-content-dish-info-price"><span
              class="content-menu-dish-cart-price-value">${price}</span>р.</p>
      </div>
  </div>`
          );

          this.cartSum.textContent = +this.cartSum.textContent + +price;

          // switch to quantity control

          const parent = item.parentElement;

          item.outerHTML = `<div class="quantity">
          <span class="quantity-control minus">-</span><input class="quantity-value"
              value="1"></input><span class="quantity-control plus">+</span>
      </div>`;

          const plus = parent.querySelector(".plus");
          const minus = parent.querySelector(".minus");
          const value = parent.querySelector(".quantity-value");

          plus.addEventListener("click", (e) => {
            value.value++;
            document
              .querySelector(`[data-cart-id="${id}"]`)
              .querySelector("input").value = value.value;
          });

          minus.addEventListener("click", (e) => {
            value.value--;
            document
              .querySelector(`[data-cart-id="${id}"]`)
              .querySelector("input").value = value.value;
          });
        });
      });
  }
}

new Menu();
