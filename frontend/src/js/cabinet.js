// EDIT USER FIELD
const editUserField = () => {
  xhr = new XMLHttpRequest();
  xhr.open("POST", "http://localhost:8080/api/v1/orders");
  xhr.setRequestHeader("Content-Type", "application/json");
  xhr.onreadystatechange = () => {
    if (xhr.readyState !== 4 || xhr.status !== 200) {
      return;
    }
    console.log(JSON.parse(xhr.responseText));
  };
  xhr.send();
};

// ORDERS RENDERING XHR
const getOrders = () => {
  const xhr = new XMLHttpRequest();
  xhr.open("GET", "http://localhost:8080/api/v1/orders");
  let email = "cherni@example.ru";
  let pass = "password";
  xhr.setRequestHeader("Authorization", "Basic " + btoa(`${email}:${pass}`));
  console.log(btoa(`${email}:${pass}`));
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
};
