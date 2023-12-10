const popups = document.getElementsByClassName("popup");
const accountButton = document.querySelector(".header-account");

const authXhr = () => {
  const authForm = document.forms["registration-form"];

  let params = {};

  Array.from(document.getElementsByTagName("input")).map((item) => {
    params[item.name] = item.value;
  });

  console.log(params);

  const xhr1 = new XMLHttpRequest();
  xhr1.open("POST", "http://localhost:8080/auth/reg");
  xhr1.setRequestHeader("Content-Type", "application/json");
  xhr1.onreadystatechange = () => {
    if (xhr1.readyState === 4 && xhr1.status === 200) {
      console.log(JSON.parse(xhr1.responseText));
    }
  };
  xhr1.send(JSON.stringify(params));

};

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
    .addEventListener("click", () => authXhr());
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
  popup.querySelector("#authorization-button");
};

document.addEventListener("click", (e) => {
  if (e.target.classList.contains("popup")) e.target.remove();
});

accountButton.addEventListener("click", () => insertForm());
