const dropdown = document.querySelectorAll(".dropdown");
const dropdownOptions = document.querySelectorAll(".dropdown-options");

export const dropdownHandler = () => {
  // EXPAND DROPDOWNS
  dropdown.forEach((item) => {
    item.addEventListener("click", (e) => {
      const container = e.target.closest(".dropdown");
      const opened = document.querySelector(".on-top");
      if (!container.classList.contains("on-top") && opened) {
        opened.classList.toggle("on-top");
        opened
          .querySelector(".dropdown-options")
          .classList.toggle("dropdown-show");
        opened
          .querySelector(".dropdown-arrow")
          .classList.toggle("dropdown-arrow-collapsed");
      }
      container.classList.toggle("on-top");
      container
        .querySelector(".dropdown-options")
        .classList.toggle("dropdown-show");
      container
        .querySelector(".dropdown-arrow")
        .classList.toggle("dropdown-arrow-collapsed");
    });
  });
  // DROPDOWNS OPTION
  dropdownOptions.forEach((item) => {
    item.addEventListener("click", (e) => {
      e.preventDefault();
      if (e.target.textContent === "Добавить адрес") return;
      const container = e.target.closest(".dropdown");
      container.querySelector(".dropdown-value").textContent =
        e.target.textContent;
    });
  });
};
