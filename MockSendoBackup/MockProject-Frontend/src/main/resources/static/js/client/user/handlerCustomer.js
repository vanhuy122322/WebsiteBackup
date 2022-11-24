$(document).ready(function () {
  let message = $("#message-text").html();
  if (message != null) {
    setTimeout(function () {
      $("#message-text").hide();
    }, 3000);
  }

  let id = $("input[name='id']").val();
  $("#id-formChange").val(id);
});

$("#btn-update").click(() => {
  let birthDate = $("input[name='birthDate']").val();
  console.log(birthDate);
  let date = birthDate.split("-");
  let yearB = new Date(date[0], date[1], date[2]);
  if (new Date().getYear() - yearB.getYear() > 90) {
    $("#error-birthdate").html("Bạn quá già");
  } else if (new Date().getYear() - yearB.getYear() > 10) {
    $("form[name='form-updateCustomer']").submit();
  } else {
    $("#error-birthdate").html("Bạn chưa đủ tuổi");
  }
});

$("#btn-changePassword").click(() => {
  $("#form-updateCustomer").hide();
  $(".contain-changepassword").show();
});

$("#submit-form-changepassword").click(() => {
  let newP = $("input[name='newPassword']").val();
  let confirmP = $("input[name='confirmPassword']").val();
  if (newP === confirmP) {
    $("form[name='form-changepassword']").submit();
  } else {
    $("#error-message-changeP").html("Confirm password wrong!");
    $("input[name='confirmPassword']").val("");
    $("input[name='newPassword']").val("");
  }
});
