import Header from "./src/js/main.js";
import Menu from "./src/js/menu.js";
import Summary from "./src/js/summary.js";
import Cabinet from "./src/js/cabinet.js";
import Admin from "./src/js/admin.js";
import Courier from "./src/js/courier.js";
import Restaurant from "./src/js/restaurants.js";

new Header();
if (document.querySelector(".content-menu")) {
  new Menu();
}
if (document.querySelector(".content-summary")) {
  new Summary();
}
if (document.querySelector(".content-cabinet")) {
  new Cabinet();
}
if (document.querySelector(".content-admin")) {
  new Admin();
}
if (document.querySelector(".content-courier")) {
  new Courier();
}
if (document.querySelector(".content-restaurants")) {
  new Restaurant();
}

console.log("index script loaded");
