export default class Alert {
  constructor(type, message, timeout, href) {
    document.querySelector(".content").insertAdjacentHTML(
      "afterbegin",
      `<div class="alert">
        <div class="alert-body ${type}">
            ${message}<span class="alert-close">x</span>
        </div>
    </div>`
    );
    const alert = document.querySelector(".alert");
    alert.querySelector(".alert-close").addEventListener("click", () => {
      alert.classList.remove("alert-show");
      setTimeout(() => {
        alert.remove();
        window.location.href = href;
      }, 200);
    });
    console.log(alert);
    setTimeout(() => {
      alert.classList.add("alert-show");
    }, 0);
    setTimeout(() => {
      alert.classList.remove("alert-show");
    }, timeout);
    setTimeout(() => {
      alert.remove();
      window.location.href = href;
    }, timeout + 200);
  }
}
