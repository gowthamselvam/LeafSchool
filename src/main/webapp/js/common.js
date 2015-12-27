/**
 * 
 */
 $(function(){
	$("#register").click(function(){
		var username = $('#orgname').val();
		if(username === '') {
			alert("username can't be empty");
			$('#username').focus();
			return false;
		}
		var password = $("#address").val();
		if(password === '') {
			alert("password can't be empty");
			$("#password").focus();
			return false;
		}
	});
	function isEmail(email) {
		  var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
		  return regex.test(email);
		}
});