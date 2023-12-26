export default class Alert {
  constructor(type, message, timeout, href) {
    document.body.insertAdjacentHTML(
      "afterbegin",
      `<div class="alert">
        <div class="alert-body ${type}">
            ${message}<span class="alert-close">✕</span>
        </div>
    </div>`
    );
    const alert = document.querySelector(".alert");
    alert.querySelector(".alert-close").addEventListener("click", () => {
      alert.classList.remove("alert-show");
      setTimeout(() => {
        alert.remove();
        if (href) window.location.href = href;
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
      if (href) window.location.href = href;
    }, timeout + 200);
  }
}
