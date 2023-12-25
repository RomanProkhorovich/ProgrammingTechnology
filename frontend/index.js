import header from "./src/js/main.js";
import menu from "./src/js/menu.js";
import summary from "./src/js/summary.js";
import cabinet from "./src/js/cabinet.js";
import admin from "./src/js/admin.js";

new header();
if (document.querySelector(".content-menu")) {
  new menu();
}
if (document.querySelector(".content-summary")) {
  new summary();
}
if (document.querySelector(".content-cabinet")) {
  new cabinet();
}
if (document.querySelector(".content-admin")) {
  new admin();
}

console.log("index script loaded");
