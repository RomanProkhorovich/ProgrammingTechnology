import Alert from "./alert.js";

export default class Restaurant {
  constructor() {
    this.cardContainer = document.querySelector(".content-restaurants");
    this.getRestaurants();
    this.bookButtons = document.querySelectorAll(
      ".content-restaurants-card-button"
    );
    console.log(this.bookButtons);

    this.registerEvents();
  }

  getRestaurants() {
    const xhr = new XMLHttpRequest();
    xhr.open("GET", "http://localhost:8080/api/v1/restaurants");
    xhr.onreadystatechange = () => {
      if (xhr.readyState !== 4 || xhr.status !== 200) {
        return;
      }

      const response = JSON.parse(xhr.responseText);

      response.forEach((item) => {
        this.cardContainer.insertAdjacentHTML(
          "beforeend",
          `<div class="content-restaurants-card" data-restaurant-id="${response.address}">
              <div class="content-restaurants-card-img">
                  <img src="https://pgdv.ru/images/blog/restorany-michelin-moskva/michelin-moskva-7-min.jpg" alt="">
              </div>
              <div class="content-restaurants-card-info">
                  <h1>Roman's на ${response.address}</h1>
                  <p>${response.description}</p>
                  <button class="content-restaurants-card-button">Забронировать столик</button>
              </div>
          </div>`
        );
      });
    };
    xhr.send();
  }

  popupBooking(id) {
    document.body.insertAdjacentHTML(
      "afterbegin",
      `<div class="popup hidden">
          <form action="#" id="booking-form">
              <h1>
                  Забронировать столик
              </h1>
              <label for="date">Дата</label>
              <input type="date" placeholder="Дата брони" name="date" value="">
              <label for="time">Время</label>
              <input type="time" name="time">
              <label for="count">Кол-во персон</label>
              <input type="number" min="0" placeholder="Количество персон" name="count" value="">
              <button id="book-button" type="button">Забронировать</button>
          </form>
      </div>`
    );
    setTimeout(() => {
      document.querySelector(".popup").classList.remove("hidden");
    }, 0);
    document.querySelector("#book-button").addEventListener("click", () => {
      let formData = {};

      Array.from(
        document.forms["booking-form"].getElementsByTagName("input")
      ).forEach((item) => {
        formData[item.name] = item.value;
      });

      console.log(formData);

      const xhr = new XMLHttpRequest();
      xhr.open("POST", "http://localhost:8080/api/v1/booking");
      xhr.setRequestHeader("Content-Type", "application/json");
      xhr.onreadystatechange = () => {
        if (xhr.readyState !== 4 || xhr.status !== 200) {
          return;
        }
        let params = {};

        params["userId"] = JSON.parse(localStorage.getItem("User")).id;
        params["dateTime"] = formData.date + ", " + formData.time;
        params["restId"] = id;
        params["count"] = formData.count;

        document.forms["booking-form"].closest(".popup").remove();

        new Alert(
          "success",
          `Забронирован столик на ${params.count} человек в ${params.dateTime}.`,
          3000
        );
      };
      xhr.send(JSON.stringify(params));
    });
  }

  registerEvents() {
    this.bookButtons.forEach((item) => {
      item.addEventListener("click", (e) => {
        this.popupBooking(
          e.target.closest(".content-restaurants-card").dataset.restaurantId
        );
      });
    });
  }
}
