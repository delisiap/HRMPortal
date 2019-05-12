$(document).ready(function () {
	$('#loginform').submit(function (e) {
		e.preventDefault();
		var empId = document.getElementById("emailID").value;
		var password = document.getElementById("password").value;
		var data = "empId=" + empId + "&" + "password=" + password;
		localStorage.setItem("empID", empId);
		var url = "/user/login";
		ajaxCallWrapper(url, data, onloginbackendresponse, showErrorMessage);
	});
});

function onloginbackendresponse(response, status) {
	if (status == SUCCESS && response && response.hasOwnProperty(RESPONSE_STATUS_KEY) && response[RESPONSE_STATUS_KEY] == SUCCESS) {
		return window.location.href = '/homepage.html';
	} else {
		showErrorMessage();
	}
}

function showErrorMessage() {
	var errorElement = document.getElementById('errormessage');
	errorElement.style["display"] = DISPLAY_BLOCK;
}

function onErrorBtnCloseClick() {
	document.getElementById('errormessage').style["display"] = DISPLAY_NONE;
}