import header from "./src/js/main.js";
import menu from "./src/js/menu.js";
import summary from "./src/js/summary.js";

new header();
if (document.querySelector(".content-menu")) {
  new menu();
}
if (document.querySelector(".content-summary")) {
  new summary();
}

console.log("index script loaded");
