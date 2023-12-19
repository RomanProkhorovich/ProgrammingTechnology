// SEND ORDER
const sendOrder = () => {
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
};
