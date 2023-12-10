const popups = document.getElementsByClassName("popup");
const accountButton = document.querySelector(".header-account");

// GET DISHES
const getDish = () => {
  const xhr = new XMLHttpRequest();
  xhr.open("GET", "http://localhost:8080/api/v1/dishes");
  xhr.onreadystatechange = () => {
    if (xhr.readyState !== 4 || xhr.status !== 200) {
      return;
    }

    const dishes = JSON.parse(xhr.responseText);

    const dishesContainer = document.querySelector("content-menu");

    dishes.forEach((item) => {
      dishesContainer.insertAdjacentHTML(
        "beforeend",
        `<div class="content-menu-dish">
      <img class="content-menu-dish-photo" src="https://placehold.co/400" alt="">
      <h1>${item.name}</h1>
      <p class="content-menu-dish-description">${item.description}</p>
      <div class="content-menu-dish-cart">
          <p class="content-menu-dish-cart-price">${item.price} р.</p>
          <button class="content-menu-dish-cart-button">В корзину</button>
      </div>
  </div>`
      );
    });
  };
  xhr.send();
};

// REGISTRATION DATA SEND
const authXhr = (authForm) => {
  let params = {};

  Array.from(authForm.getElementsByTagName("input")).map((item) => {
    params[item.name] = item.value;
  });

  console.log(params);

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

// SWITCH TO REGISTRATION
const register = () => {
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
    .addEventListener("click", (e) => authXhr(e.target.closest("form")));
};

// AUTH POPUP
const insertForm = () => {
  document.body.insertAdjacentHTML(
    "afterbegin",
    `<div class="popup">
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
  document
    .getElementById("authorization-button")
    .addEventListener("click", () => {});
  const popup = document.querySelector(".popup");
  popup
    .querySelector("#registration-button")
    .addEventListener("click", () => register());
  popup
    .querySelector("#authorization-button")
    .addEventListener("click", (e) => authXhr(e.target.closest("form")));
};

document.addEventListener("click", (e) => {
  if (e.target.classList.contains("popup")) e.target.remove();
});

accountButton.addEventListener("click", () => insertForm());

getDish();
