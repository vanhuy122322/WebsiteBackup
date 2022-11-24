$(document).ready(function () {
 	let id = $("#id-formUpdate").val();
 	 $("#id-formChange").val(id);
 	 let message = $("#text-message").html();
 	 if(message != null){
		setTimeout(() => {
			$("#text-message").hide();
		}, 3000)
}
});


$("#btn-changepassword").click(() => {
	$("form[name='form-update-user']").hide();
	$(".contain-changepassword").show();
});



$("#submit-form-changepassword").click(() =>{
	let newP = $("input[name='newPassword']").val();
	let confirmP = $("input[name='confirmPassword']").val();
	if(newP === confirmP){
		$("form[name='form-changepassword']").submit();
	} else {
		$("#error-message-changeP").html("Confirm password wrong!");
		$("input[name='confirmPassword']").val("");
		$("input[name='newPassword']").val("");
	}
});

$("#submit-form-addrole").click(() =>{
	$("form[name='form-addrole']").submit();
});

$("#btn-add-role").click(() => {
	$("form[name='form-update-user']").hide();
	$(".contain-addrole").show();
});

$(".btn-back-update").click(() => {
	$("form[name='form-update-user']").show();
	$(".contain-changepassword").hide();
	$(".contain-addrole").hide();
});