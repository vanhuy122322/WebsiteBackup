$(document).ready(function () {});

function cancelOrder(value) {
  let checkoutCode = value;
  if (confirm("Bạn muốn hủy đơn hàng này không?")) {
    $.post("/customer/order/cancel-order", { checkoutCode }, (resp) => {
      alert("Hủy đơn hàng thành công");
      setTimeout(() => {
        document.location.href = "/customer/order/has-cancel";
      }, 1000);
    });
  }
}

function deleteOrder(value) {
  let checkoutCode = value;
  if (confirm("Bạn muốn xóa đơn hàng này không?")) {
    $.post("/customer/order/delete-order", { checkoutCode }, (resp) => {
      alert("Xóa đơn hàng thành công");
      setTimeout(() => {
        document.location.href = "/customer/order/has-cancel";
      }, 1000);
    });
  }
}
