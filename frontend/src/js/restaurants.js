export default class Restaurant {
  constructor() {
    this.cardContainer = document.querySelector(".content-restaurants");
    this.bookButtons = document.querySelectorAll(
      ".content-restaurants-card-button"
    );
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
          `<div class="content-restaurants-card">
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
}
