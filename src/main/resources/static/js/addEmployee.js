$(window).on('load', function () {
	ajaxCallWrapper("/admin/getrolenames", {}, onGetRolesResponse, showErrorMessage);
});

$(document).ready(function () {
	$('#addEmployeeForm').submit(function (e) {
		e.preventDefault();
		var url = "/admin/addemployee",
			data = {};

		$("#addEmployeeForm input").each(function () {
			var input = $(this),
				name = input.attr('name'),
				value = input.val();
			if (value) {
				data[name] = value;
			}
		});
		data["roleName"] = $('#addEmployeeForm')[0].elements["roleName"].value;
		ajaxCallWrapper(url, data, onAddEmployeeResponse, showErrorMessage);
	});
});

function onAddEmployeeResponse(response, status) {
	if (SUCCESS == status && response && response.hasOwnProperty(RESPONSE_STATUS_KEY) && SUCCESS == response.status) {
		var form = document.getElementById("addEmployeeForm");
		form.reset();
		showSuccessMessage("Employee added successfully.");
	} else {
		showErrorMessage(response.data);
	}
}

function onGetRolesResponse(response, status) {
	if (SUCCESS == status && response && response.hasOwnProperty(RESPONSE_STATUS_KEY) && SUCCESS == response.status) {
		var combobox = document.getElementById("roleName").options,
			data = response.data,
			i;
		for (i = 0, data; i < data.length; i++) {
			combobox[i] = new Option(data[i], data[i]);
		}
	} else {
		showErrorMessage(response.data);
	}
}