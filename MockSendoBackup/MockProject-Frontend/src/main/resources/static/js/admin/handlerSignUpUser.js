$("input[name='confirm-password']").change(() => {
	let confirm = $("input[name='confirm-password']").val();
	console.log(confirm)
	let pass = $("input[name='password']").val();
	console.log(pass)
	if(confirm != pass){
		$("#error-text").text("confirm password not valid!");
	}
})