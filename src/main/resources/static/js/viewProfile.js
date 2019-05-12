$(window).on('load', function () {
	ajaxCallWrapper("/profile/getemployeeinfo", {}, getProfileResponse, showErrorMessage);
});

function getProfileResponse(response, status) {
	var data, updateProfileButton;
	if (SUCCESS == status && response && response.hasOwnProperty(RESPONSE_STATUS_KEY) && SUCCESS == response.status) {
		data = response.data;

		$("#profileForm input").each(function () {
			var input = $(this),
				name = input.attr('name'),
				value = data[name];
			if (value) {
				input.val(value);
			}
		});
		updateProfileButton = $("button[name=updateProfileButton]");
		$("input[name=phoneNo], input[name=alternateEmailId], input[name=address]").one("keyup", function () {
			updateProfileButton.prop('disabled', false);
			updateProfileButton.click(onUpdateProfileButtonClick);
		});
	} else {
		showErrorMessage(data);
	}
}

function onUpdateProfileButtonClick() {
	var data = {};
	$("input[name=phoneNo], input[name=alternateEmailId], input[name=address]").each(function () {
		var input = $(this),
			name = input.attr('name')
		value = input.val();
		data[name] = value;
	});

	ajaxCallWrapper("/profile/updateemployeeinfo", data, callbackFn, failureFn);
}

function callbackFn(backendResponse) {
	if (backendResponse && backendResponse.status && backendResponse.status == "success") {
		showSuccessMessage("Profile updated successfully.");
	}
	else {
		showErrorMessage(backendResponse.data);
	}

}

function failureFn() 
{
	showErrorMessage("Failed to update profile infomation. Try again later.");
}

function onErrorClick() 
{
	document.getElementById('viewProfileErrorMessage').style["display"] = DISPLAY_NONE;
}