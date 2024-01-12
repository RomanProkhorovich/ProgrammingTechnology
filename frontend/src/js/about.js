const tabManual = document.querySelector("#tab-manual");
const tabDevelopers = document.querySelector("#tab-developers");

const contentManual = document.querySelector(".content-about-manual");
const contentDevelopers = document.querySelector(".content-about-developers");

tabManual.addEventListener("click", () => {
  contentManual.classList.remove("display-none");
  contentDevelopers.classList.add("display-none");
  tabManual.classList.add("tab-active");
  tabDevelopers.classList.remove("tab-active");
});

tabDevelopers.addEventListener("click", () => {
  contentManual.classList.add("display-none");
  contentDevelopers.classList.remove("display-none");
  tabManual.classList.remove("tab-active");
  tabDevelopers.classList.add("tab-active");
});
