class Summary {
  constructor() {
    this.dropdown = document.querySelectorAll(".content-summary-dropdown");
    this.dropdownOptions = document.querySelectorAll(
      ".content-summary-dropdown-options"
    );

    this.registerEvents();
  }

  // SEND ORDER
  sendOrder() {
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
    let email = "cherni@example.ru";
    let pass = "password";
    xhr.setRequestHeader("Authorization", "Basic " + btoa(`${email}:${pass}`));
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
        deliveryTime: new Date(),
      })
    );
  }

  registerEvents() {
    // EXPAND DROPDOWNS
    this.dropdown.forEach((item) => {
      item.addEventListener("click", (e) => {
        const container = e.target.closest(".content-summary-dropdown");
        container
          .querySelector(".content-summary-dropdown-options")
          .classList.toggle("dropdown-show");
        container
          .querySelector(".dropdown-arrow")
          .classList.toggle("dropdown-arrow-collapsed");
      });
    });
    // DROPDOWNS OPTION
    this.dropdownOptions.forEach((item) => {
      item.addEventListener("click", (e) => {
        const container = e.target.closest(".content-summary-dropdown");
        container.querySelector(".dropdown-value").textContent =
          e.target.textContent;
      });
    });
  }
}

new Summary();

console.log("summary script loaded");
