const popups = document.getElementsByClassName("popup");
const accountButton = document.querySelector(".header-account");

const register = () => {
  const popup = document.querySelector(".popup");
  popup.innerHTML = `
  <form id="authorization-form" method="get">
      <h1>
          Регистрация
      </h1>
      <input type="text" placeholder="Фамилия">
      <input type="text" placeholder="Имя">
      <input type="text" placeholder="Отчество (при наличии)">
      <input type="text" placeholder="E-mail">
      <input type="text" placeholder="Номер телефона">
      <input type="password" placeholder="Пароль">
      <div class="button-group">
          <button id="authorization-button" type="button">Зарегистрироваться</button>
      </div>
  </form>`;
};

const insertForm = () => {
  document.body.insertAdjacentHTML(
    "afterbegin",
    `<div class="popup">
    <form id="authorization-form" method="get">
        <h1>
            Войдите, чтобы сделать заказ
        </h1>
        <input type="text" placeholder="Номер телефона или E-Mail">
        <input type="password" placeholder="Пароль">
        <div class="button-group">
            <button id="registration-button" type="button">Зарегистрироваться</button>
            <button id="authorization-button" type="button">Войти</button>
        </div>
    </form>
</div>
`
  );
  const popup = document.querySelector(".popup");
  popup
    .querySelector("#registration-button")
    .addEventListener("click", () => register());
};

document.addEventListener("click", (e) => {
  if (e.target.classList.contains("popup")) e.target.remove();
});

accountButton.addEventListener("click", () => insertForm());
