$(window).on('load', function () {
	ajaxCallWrapper("/leave/viewLeaveStatus", {}, loadLeavesFn, errorFn);
});

function loadLeavesFn(data, status, jqXHR) {
	var leavesContainer = $("#table-body");
	var leaves = data.data;

	if (leaves.length > 0) {
		for (var i = 0; i < leaves.length; i++) {
			var leave = leaves[i];
			leavesContainer.append(createLeaveForm(leave));
		}
	} else {
		var provessLeavesDiv = $("#process-leave-div");
		provessLeavesDiv.empty();
		provessLeavesDiv.append("<p class=\"empty-p\">No leaves applied.<p>");
	}
}

function createLeaveForm(leave) {
	var ret = "";
	ret += "<tr>";
	ret += "<td scope=\"row\" id=\"leave-startdate-" + leave.ID + "\">" + leave.START_DATE + "</td>";
	ret += "<td scope=\"row\" id=\"leave-enddate-" + leave.ID + "\">" + leave.END_DATE + "</td>";
	ret += "<td scope=\"row\" data-employee-id=\"" + leave.STATUS + "\" id=\"leave-employeeid-" + leave.ID + "\">" + leave.STATUS + "</td>";
	ret += "</tr>";
	return ret;
}

function callbackFn(status) {
	alert("History Of leaves displayed below.");
	location.reload();
}

function errorFn(status) {
	alert("Failed to retrive history of leaves.")
	location.reload();
}