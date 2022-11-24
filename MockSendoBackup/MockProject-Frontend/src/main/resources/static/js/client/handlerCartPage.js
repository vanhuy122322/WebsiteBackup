$(document).ready(function () {});

$(window).on("load", () => {});

function validateForm() {
  let selected = [];
  $(".checkbox-to-buy").each(function () {
    if ($(this).is(":checked")) {
      selected.push($(this).attr("value"));
    }
  });
  if (selected.length == 0) {
    alert("Bạn chưa chọn sản phẩm để mua");
    return false;
  } else {
    return true;
  }
}

var total = 0;

function updateTotal(index) {
  let saveValueTotal = Number($(`#save-value-total-${index}`).val());
  let price = Number(
    $(`#delete-product-in-cart-${index} #price-${index}`).val()
  );
  let quantity = Number(
    $(`#delete-product-in-cart-${index} #quantity-value-${index}`).val()
  );
  if ($(`#checkbox-total-update-${index}`).is(":checked")) {
    if (quantity > saveValueTotal) {
      $(`#save-value-total-${index}`).val(quantity);
      total = total + Number(price);
      $("#total").val(total);
      $("#total-value").text(
        `${total.toLocaleString({
          style: "currency",
          currency: "VND",
        })}đ`
      );
    } else {
      $(`#save-value-total-${index}`).val(quantity);
      total = total - price;
      $("#total").val(total);
      $("#total-value").text(
        `${total.toLocaleString({
          style: "currency",
          currency: "VND",
        })}đ`
      );
    }
  }
}

function deleteProductInCart(value) {
  let id = value;
  if (confirm("Bạn muốn xóa sản phẩm này không?")) {
    $.post("/delete-product-in-cart", { id }, (resp) => {
      setTimeout(() => {
        $(`#delete-product-in-cart-${id}`).remove();
      }, 200);
      setTimeout(() => {
        alert("Thành công");
        document.location.href = "/cart";
      }, 500);
    });
  }
}

function checkboxTotalUpdate(value) {
  let price = $(`#delete-product-in-cart-${value} #price-${value}`).val();
  let quantity = $(
    `#delete-product-in-cart-${value} #quantity-value-${value}`
  ).val();
  if ($(`#checkbox-total-update-${value}`).is(":checked")) {
    $(`#choose-status-value-${value}`).val("true");
    total = total + price * quantity;
    if (isNaN(total)) {
      total = 0;
    }
    $("#total").val(total);
    $("#total-value").text(
      `${total.toLocaleString({
        style: "currency",
        currency: "VND",
      })}đ`
    );
  } else {
    total = total - price * quantity;
    if (isNaN(total)) {
      total = 0;
    }
    $("#total").val(total);
    $("#total-value").text(
      `${total.toLocaleString({
        style: "currency",
        currency: "VND",
      })}đ`
    );
  }
}
