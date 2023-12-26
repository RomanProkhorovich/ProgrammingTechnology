export default class Courier {
  constructor() {
    this.tabWaiting = document.querySelector("#tab-waiting");
    this.tabActive = document.querySelector("#tab-active");
    this.contentWaiting = document.querySelector(".content-courier-waiting");
    this.contentActive = document.querySelector(".content-courier-active");

    this.check();
    this.registerEvents();
  }

  check() {
    const user = localStorage.getItem("User");
    if (!user || !role || role !== "Courier")
      window.location.href = document.querySelector(".header-logo a").href;
  }
  // ORDERS RENDERING XHR
  getOrders() {
    const xhr = new XMLHttpRequest();
    xhr.open("GET", "http://localhost:8080/api/v1/orders");
    const user = JSON.parse(localStorage.getItem("User"));
    const email = user.username;
    const pass = user.password;
    xhr.setRequestHeader("Authorization", "Basic " + btoa(`${email}:${pass}`));
    xhr.onreadystatechange = () => {
      if (xhr.readyState !== 4 || xhr.status !== 200) {
        return;
      }

      const orders = JSON.parse(xhr.responseText);

      console.log(orders);

      const ordersContainer = document.querySelector(".content-cabinet-orders");

      orders.forEach((item) => {
        ordersContainer.insertAdjacentHTML("beforeend", ``);
      });
    };
    xhr.send();
  }

  registerEvents() {
    this.tabWaiting.addEventListener("click", () => {
      this.tabActive.classList.remove("content-cabinet-tab-active");
      this.tabWaiting.classList.add("content-cabinet-tab-active");
      this.contentActive.classList.add(".display-none");
      this.contentWaiting.classList.remove(".display-none");
    });
    this.tabActive.addEventListener("click", () => {
      this.tabActive.classList.add("content-cabinet-tab-active");
      this.tabWaiting.classList.remove("content-cabinet-tab-active");
      this.contentActive.classList.remove(".display-none");
      this.contentWaiting.classList.add(".display-none");
    });
  }
}
