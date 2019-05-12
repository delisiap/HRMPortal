var changePasswordView = null;

function onLogoutClick() {
	var url = "/user/logout";
	ajaxCallWrapper(url, {}, function () { }, showErrorMessage);
}

function onChangePasswordLinkClick() {
	if (null == changePasswordView) {
		var changePasswordViewHtml = '<div class="chnagePasswordInnerDiv">' +
			'<span class="closeButton" onclick="onCancelButtonClick()">&times;</span>' +
			'<h2 class="text-center heading changepasswordheading">Change Password</h2>' +
			'<form class="changePasswordForm" id="changePasswordForm" method="post">' +
			'<div class="clsInlineAlertMessage clsErrorMessage" id="errormessage" name="divfloatErrorMessage"> <span class="clsLeaveMessage" name="errorMesssage">Invalid credentials login failed.</span>' +
			'<input type="button" class="clsErrorMessageBtnClose" value="X" onclick="onErrorBtnCloseClick(this)">' +
			'</div>' +
			'<div class="form-group custom-form-group">' +
			'<label class="float-left label changepasswordLabels">Current Password</label>' +
			'<input class="form-control float-right field" type="password" name="password" required>' +
			'</div>' +
			'<div class="form-group custom-form-group">' +
			'<label class="float-left label changepasswordLabels">New Password</label>' +
			'<input class="form-control float-right field" type="password" name="newPassword" required>' +
			'</div>' +
			'<div class="form-group custom-form-group">' +
			'<label class="float-left label changepasswordLabels">Confirm Password</label>' +
			'<input class="form-control float-right field" type="password" name="confirmPassword" required>' +
			'</div>' +
			'<div class="changepassword-btngroup">' +
			'<input type= "submit" class="btn btn-primary float-left changepassword-button" onclick="onChangePasswordButtonClick()" value="Submit">' +
			'<button class="btn btn-primary float-left changepassword-button" onclick="onCancelButtonClick()">Cancel</button>' +
			'</div>' +
			'</form>' +
			'</div>';
		changePasswordView = document.createElement('changePasswordView');
		changePasswordView.setAttribute('id', 'changePasswordView');
		changePasswordView.setAttribute('class', 'changePasswordDiv');
		changePasswordView.innerHTML = changePasswordViewHtml;
	} else {
		changePasswordView.style.display = DISPLAY_BLOCK;
	}
	var body = document.body;
	body.appendChild(changePasswordView);
}

function onChangePasswordButtonClick() {
	var form = document.getElementById("changePasswordForm");
	$('#changePasswordForm').submit(function (e) {
		e.preventDefault();
		var url = "/user/changepassword",
			newPassword = form.elements["newPassword"].value,
			confirmPassword = form.elements["confirmPassword"].value;

		if (newPassword == confirmPassword) {
			var data = {
				"password": form.elements["password"].value,
				"newPassword": newPassword
			};
			ajaxCallWrapper(url, data, onChangePasswordResponse, showErrorMessage);
		}
		else {
			showErrorMessage("New and confirm passwords should match.", "divfloatErrorMessage");
		}
	});
}

function onCancelButtonClick() {
	changePasswordView.style.display = DISPLAY_NONE;
	//onErrorClick();
}

function onChangePasswordResponse(response, status) {
	if (SUCCESS == status && response && response.hasOwnProperty("status") && SUCCESS == response.status) {
		var body = document.body,
			form = document.getElementById("addEmployeeForm");

		showSuccessMessage("Password changed successfully.", "divfloatErrorMessage");
		alert("Password changed successfully. You will be logged out of application.");
		body.removeChild(changePasswordView);
		onLogoutClick();
	} else {
		showErrorMessage(response.data, "divfloatErrorMessage");
	}
}

function showErrorMessage(id, message) {
	if (id) {
		var errorElement = document.getElementById(id);
		if (message) {
			errorElement.innerHTML = message;
		}
		errorElement.style.display = DISPLAY_BLOCK;
	}
}

function onErrorClick() {
	var errorElement = document.getElementById(CHANGE_PASSWORD_ERROR_MESSAGE_ELEMENT);
	errorElement.style.display = DISPLAY_NONE;
}

function showErrorMessage(strMessage, elementName) {
	var currentElement;
	if (elementName) {
		currentElement = document.getElementsByName(elementName)[0];
	} else if (currentElement == null && document.getElementsByName("diverrormessage")) {
		currentElement = document.getElementsByName("diverrormessage")[0];
	} else {
		currentElement = document.getElementById("errormessage")[0];
	}
	currentElement.style.display = "block";
	currentElement.classList.add("clsErrorMessage");
	currentElement.classList.remove("clsSuccessMessage");
	updateMessage(strMessage, true, elementName);
}

function showSuccessMessage(strMessage, elementName) {
	var currentElement;
	if (elementName) {
		currentElement = document.getElementsByName(elementName)[0];
	} else if (currentElement == null && document.getElementsByName("diverrormessage")) {
		currentElement = document.getElementsByName("diverrormessage")[0];
	} else {
		currentElement = document.getElementById("errormessage")[0];
	}
	currentElement.style.display = "block";
	currentElement.classList.add("clsSuccessMessage");
	currentElement.classList.remove("clsErrorMessage");
	updateMessage(strMessage, false, elementName);
}

function onErrorBtnCloseClick(currentElement) {
	if (currentElement && currentElement.parentNode) {
		currentElement.parentNode.style["display"] = "none";
	}
}

function updateMessage(strMessage, isError, elementName) {
	if (strMessage == null || strMessage == undefined || strMessage == "") {
		if (isError) {
			strMessage = "Oops! Something went wrong. Contact system administrator.";
		} else {
			strMessage = "Success.";
		}
	}
	var element;
	if (elementName) {
		element = document.getElementsByName(elementName)[0];
		element = element.getElementsByTagName("span");
	} else {
		element = document.getElementsByName("errorMesssage");
	}
	element[0].innerText = strMessage;
}