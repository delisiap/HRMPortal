$(document).ready(function () {
	$('#removeEmployeeForm').submit(function (e) {
		e.preventDefault();
		var employeeId = document.getElementById("employeeId").value,
			data = "employeeId=" + employeeId.trim();
		ajaxCallWrapper("/admin/removeemployee", data, onRemoveResponse, showErrorMessage);
	});
});

function onRemoveResponse(response, status) {
	if (SUCCESS == status && response && response.hasOwnProperty(RESPONSE_STATUS_KEY) && SUCCESS == response.status) {
		var form = document.getElementById("removeEmployeeForm");
		form.reset();
		form.elements[REMOVE_EMPLOYEE_BUTTON].disabled = true;
		showSuccessMessage("Employee removed successfully.");
	} else {
		showErrorMessage(response.data);
	}
}

function onGetEmployeeButtonClick() {
	var employeeId = document.getElementById("employeeId").value,
		data = "employeeId=" + employeeId.trim();
	ajaxCallWrapper("/profile/getemployeeinfo", data, onGetEmployeeResponse, showErrorMessage);
}

function onGetEmployeeResponse(response, status) {
	var form = document.getElementById("removeEmployeeForm");
	if (SUCCESS == status && response && response.hasOwnProperty(RESPONSE_STATUS_KEY) && SUCCESS == response.status) {
		var data = response.data, i;
		if (data) {
			$("#removeEmployeeForm input").each(function () {
				var input =  $(this),
					name = input.attr('name'),
					value = data[name];
				if (value) {
					input.val(value);
				}
			});
			form.elements[REMOVE_EMPLOYEE_BUTTON].disabled = false;
		}
	} else {
		showErrorMessage(response.data);
		form.elements[REMOVE_EMPLOYEE_BUTTON].disabled = true;
	}
}