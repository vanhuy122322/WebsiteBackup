$(document).ready(function () {});

function addToCart() {
  let name = $("#product-name").val();
  let price = $("#price").val();
  let color = $("#color").val();
  let size = $("#size").val();
  let quantity = $("#quantity").val();
  let image = $("#image").val();

  $.post(
    "/add-product-to-cart",
    { name, price, color, size, quantity, image },
    (resp) => {
      setTimeout(() => {
        alert("Thêm vào giỏ hàng thành công");
      }, 500);
    }
  );
}

function buyNow() {
  let name = $("#product-name").val();
  let price = $("#price").val();
  let color = $("#color").val();
  let size = $("#size").val();
  let quantity = $("#quantity").val();

  $.post(
    "/add-product-to-cart",
    { name, price, color, size, quantity, image },
    (resp) => {}
  );
}
