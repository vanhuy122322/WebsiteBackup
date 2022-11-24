$(document).ready(function () {
  $(".login-wrap").hide();
});

$("#login-btn-close").click(() => {
  $(".login-wrap").hide();
});

$("#btn-login-show").click(() => {
  $(".login-wrap").show();
});

$("#btn-username-show").click(() => {
  let styleDetail = $("#login-detail").css("display");
  if (styleDetail != "none") {
    $("#login-detail").hide();
  } else {
    $("#login-detail").show();
  }
});
