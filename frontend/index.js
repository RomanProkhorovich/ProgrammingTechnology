const accountButton = document.querySelector(".header-account");
const cartButton = document.querySelector(".header-cart-logo");
const cartSum = document.querySelector("header-cart-sum-value");

// SEND ORDER
const sendOrder = () => {
  const dishes = [];

  document.querySelectorAll(".header-cart-content-dish").forEach((item) => {
    dishes.push({
      id: item.dataset.cartId,
      quantity: item.querySelector(
        ".header-cart-content-dish-info-quantity-value"
      ).value,
    });
  });

  const xhr = new XMLHttpRequest();
  xhr.open("POST", "http://localhost:8080/api/v1/orders");
  xhr.setRequestHeader("Content-Type", "application/json");
  xhr.onreadystatechange = () => {
    if (xhr.readyState !== 4 || xhr.status !== 200) {
      return;
    }
    console.log(JSON.parse(xhr.responseText));
  };
  console.log(dishes);
  xhr.send(
    JSON.stringify({
      dishes: dishes,
      address: "hui",
      deliveryTime: new Date().getDate(),
    })
  );
};

// DISHES RENDERING XHR
const getDishes = () => {
  const xhr = new XMLHttpRequest();
  xhr.open("GET", "http://localhost:8080/api/v1/dishes");
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
      <div class="content-menu-dish-cart">
          <p class="content-menu-dish-cart-price"><span
          class="content-menu-dish-cart-price-value">${item.price}</span>р.</p>
          <button class="content-menu-dish-cart-button">В корзину</button>
      </div>
  </div>`
      );
    });
    addToCart();
  };
  xhr.send();
};

// REGISTRATION DATA SEND XHR
const authSendRequest = (authForm) => {
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
    console.log(JSON.parse(xhr.responseText));
  };
  xhr.send(JSON.stringify(params));

  authForm.closest(".popup").remove();
};

// SWITCH TO REGISTRATION !!!remove values
const popupRegForm = () => {
  const popup = document.querySelector(".popup");
  popup.innerHTML = `<form id="registration-form">
  <h1>
      Регистрация
  </h1>
  <input type="text" placeholder="Фамилия" name="lastname" value="Kirkorov">
  <input type="text" placeholder="Имя" name="firstname" value="Philipp">
  <input type="text" placeholder="Отчество (при наличии)" name="surname" value="hh">
  <input type="text" placeholder="E-mail" name="email" value="fasfjnakf@fsda.sga">
  <input type="text" placeholder="Номер телефона" name="phone" value="9037285732">
  <input type="password" placeholder="Пароль" name="password" value="fhsdfghdf87">
  <div class="button-group">
      <button id="registration-button" type="button">Зарегистрироваться</button>
  </div>
</form>
`;
  popup
    .querySelector("#registration-button")
    .addEventListener("click", (e) =>
      authSendRequest(e.target.closest("form"))
    );
};

// AUTH POPUP !!!remove values
const popupAuthForm = () => {
  document.body.insertAdjacentHTML(
    "afterbegin",
    `<div class="popup hidden">
    <form id="authorization-form">
        <h1>
            Войдите, чтобы сделать заказ
        </h1>
        <input type="text" placeholder="Номер телефона или E-Mail" name="phone-email" value="fasfjnakf@fsda.sga">
        <input type="password" placeholder="Пароль" name="password" value="fhsdfghdf87">
        <div class="button-group">
            <button id="registration-button" type="button">Зарегистрироваться</button>
            <button id="authorization-button" type="button">Войти</button>
        </div>
    </form>
</div>`
  );
  const popup = document.querySelector(".popup");
  setTimeout(() => {
    popup.classList.remove("hidden");
  }, 0);
  popup
    .querySelector("#registration-button")
    .addEventListener("click", () => popupRegForm());
  popup
    .querySelector("#authorization-button")
    .addEventListener("click", (e) =>
      authSendRequest(e.target.closest("form"))
    );
};

// HIDE POPUP
document.body.addEventListener("click", (e) => {
  if (e.target.classList.contains("popup")) {
    e.target.classList.add("hidden");
    setTimeout(() => {
      e.target.remove();
    }, 200);
  }
});

// SHOW/HIDE CART
cartButton.addEventListener("click", () => {
  const cartContent = document.querySelector(".header-cart-content");
  if (cartContent.classList.contains("display-none")) {
    cartContent.classList.toggle("display-none");
    setTimeout(() => {
      cartContent.classList.toggle("hidden");
    }, 0);
  } else {
    cartContent.classList.toggle("hidden");
    setTimeout(() => {
      cartContent.classList.toggle("display-none");
    }, 200);
  }
});

// ADD DISH TO CART LISTENER
const addToCart = () => {
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
          ".content-menu-dish-cart-price"
        ).textContent;

        document.querySelector("#cart-to-order").insertAdjacentHTML(
          "beforebegin",
          `<div class="header-cart-content-dish" data-cart-id="${id}">
      <img class="header-cart-content-dish-photo" src="${src}" alt="${alt}">
      <div class="header-cart-content-dish-info">
          <h1>${name}</h1>
          <p class="header-cart-content-dish-info-quantity">
              <span class="header-cart-content-dish-info-quantity-control minus">-</span><input
              class="header-cart-content-dish-info-quantity-value" value="1"></input><span
              class="header-cart-content-dish-info-quantity-control plus">+</span>
          </p>
          <p class="header-cart-content-dish-info-price">${price}</p>
      </div>
  </div>`
        );

        // switch to quantity control

        const parent = item.parentElement;

        item.outerHTML = `<span class="header-cart-content-dish-info-quantity-control minus">-</span><input
        class="header-cart-content-dish-info-quantity-value" value="1"></input><span
        class="header-cart-content-dish-info-quantity-control plus">+</span>`;

        const plus = parent.querySelector(".plus");
        const minus = parent.querySelector(".minus");
        const value = parent.querySelector(
          ".header-cart-content-dish-info-quantity-value"
        );

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
};

// ACCOUNT LISTENER
accountButton.addEventListener("click", () => popupAuthForm());

document.querySelector("#cart-to-order").addEventListener("click", sendOrder);

// CART QUANTITY LISTENER
document
  .querySelector(".header-cart-content")
  .addEventListener("click", (e) => {
    if (
      !e.target.classList.contains(
        "header-cart-content-dish-info-quantity-control"
      )
    )
      return;

    const id = e.target.closest(".header-cart-content-dish").dataset.cartId;
    const value = e.target.parentElement.querySelector(
      ".header-cart-content-dish-info-quantity-value"
    );

    if (e.target.classList.contains("plus")) {
      value.value++;
      document
        .querySelector(`[data-menu-id="${id}"]`)
        .querySelector("input").value = value.value;
      return;
    }
    value.value--;
    document
      .querySelector(`[data-menu-id="${id}"]`)
      .querySelector("input").value = value.value;
  });

getDishes();
