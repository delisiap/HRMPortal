function onLoadApplyLeavePage() {
	var url = "/leave/viewRemainingLeave";
	var data = {
		"employeeId": localStorage.getItem("empID")
	};
	ajaxCallJSONWrapper(url, data, onapplyleaveresponse, showErrorMessagetest);
}

var empID;
function setEmpID(EmpID) {
	empID = EmpID;
}

function getEmpID() {
	return empID;
}

function onapplyleaveresponse(response) {
	if (response && response.hasOwnProperty(RESPONSE_DATA_KEY) && response[RESPONSE_DATA_KEY] && response[RESPONSE_DATA_KEY] != null) {
		window.location.href = "/applyLeave.html"
	}
}

function showErrorMessagetest() {
	console.log("ERROR ALERT");
}