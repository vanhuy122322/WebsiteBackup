$(document).ready(function () {});

$(window).on("change", () => {
  let value = $("#method-delivery").val();
  let sale = Number(100) - Number($("#voucher").val());
  console.log(sale);
  if (value == "giao-hang-nhanh") {
    $("#delivery-money").text("15.000đ");
    $("#delivery-money-value").val(15000);

    $("#total-checkout-value").val(
      ((Number($("#total-product").val()) +
        Number($("#delivery-money-value").val())) *
        sale) /
        100
    );

    $("#total-checkout").text(
      `${$("#total-checkout-value")
        .val()
        .toLocaleString({ style: "currency", currency: "VND" })}đ`
    );
  } else if (value == "hoa-toc") {
    $("#delivery-money").text("55.000đ");
    $("#delivery-money-value").val(55000);

    $("#total-checkout-value").val(
      ((Number($("#total-product").val()) +
        Number($("#delivery-money-value").val())) *
        sale) /
        100
    );

    $("#total-checkout").text(
      `${$("#total-checkout-value")
        .val()
        .toLocaleString({ style: "currency", currency: "VND" })}đ`
    );
  }
});
