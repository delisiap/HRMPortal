$(window).on('load', function () {
	onLoadFetchRemainingLeaves();
});

function onLoadFetchRemainingLeaves() {
	ajaxCallWrapper("/leave/viewRemainingLeave", {}, onLoadApplyLeavePage, errorFn);
}

function onLoadApplyLeavePage(response) {
	var remainingleaveCount = -"";
	if (response.hasOwnProperty("status") && response["status"] == "success") {
		remainingleaveCount = 0;
		if (response.data && response.data != null && response.data != "") {
			remainingleaveCount = parseInt(response.data);
		}
	}
	document.getElementById("remainingleavecount").value = remainingleaveCount;
}

function errorFn() {
	//alert("OOPS");
}

$(document).ready(function () {
	$('#applyLeave').submit(function (e) {
		e.preventDefault();
		var fromdate = document.getElementById("fromdate").value;
		var endDate = document.getElementById("todate").value;
		var data = {
			"employeeId": localStorage.getItem("empID"),
			"startDate": fromdate,
			"endDate": endDate
		};
		var url = "/leave/applyLeave";
		ajaxCallJSONWrapper(url, data, onApplyLeaveResponse, showErrorMessageLeaveClass);
	});
});

function onApplyLeaveResponse(response, status) {
	if (SUCCESS == status && response && response.hasOwnProperty(RESPONSE_STATUS_KEY) && SUCCESS == response.status) {
		var form = document.getElementById("applyLeave");
		form.reset();
		showSuccessMessage("Leave Applied Successfully.");
		onLoadFetchRemainingLeaves();
	} else {
		showErrorMessage(response.error);
	}
}

function showErrorMessageLeaveClass() {
	showErrorMessage("Error Applying Leave.");
}