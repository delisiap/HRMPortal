$(window).on('load', function () {
	ajaxCallWrapper("/processLeave/view", {}, loadLeavesFn, errorFn);
});

function loadLeavesFn(data, status, jqXHR) {
	var leavesContainer = $("#table-body");
	var leaves = data.data;

	if (leaves.length > 0) {
		for (var i = 0; i < leaves.length; i++) {
			var leave = leaves[i];
			leavesContainer.append(createLeaveForm(leave));
			$("#leave-approve-" + leave.ID).click(onBtnApproveLeaveClick);
			$("#leave-reject-" + leave.ID).click(onBtnRejectLeaveClick);
		}
	} else {
		var provessLeavesDiv = $("#process-leave-div");
		provessLeavesDiv.empty();
		provessLeavesDiv.append("<p class=\"empty-p\">No leaves to approve or reject.<p>");
	}
}

function createLeaveForm(leave) {
	var ret = "";
	ret += "<tr>";
	ret += "<td scope=\"row\" data-employee-id=\"" + leave.EMPLOYEE_ID + "\" id=\"leave-employeeid-" + leave.ID + "\">" + leave.EMPLOYEE_ID + "</td>";
	ret += "<td scope=\"row\" id=\"leave-employeename-" + leave.ID + "\">" + leave.EMPLOYEE_NAME + "</td>";
	ret += "<td scope=\"row\" id=\"leave-startdate-" + leave.ID + "\">" + leave.START_DATE + "</td>";
	ret += "<td scope=\"row\" id=\"leave-enddate-" + leave.ID + "\">" + leave.END_DATE + "</td>";
	ret += "<td scope=\"row\">";
	ret += "<button class=\"btn btn-success btn-approve\" data-leave-id=\"" + leave.ID + "\" id=\"leave-approve-" + leave.ID + "\">Approve</button>";
	ret += "<button class=\"btn btn-danger btn-reject\" data-leave-id=\"" + leave.ID + "\" id=\"leave-reject-" + leave.ID + "\">Reject</button>";
	ret += "</td>";
	ret += "</tr>";
	return ret;
}

function onBtnApproveLeaveClick() {
	updateStatus.call(this, "ACCEPT");
}

function onBtnRejectLeaveClick() {
	updateStatus.call(this, "REJECT");
}

function updateStatus(status) {
	var leaveId = parseInt(this.getAttribute('data-leave-id'));
	var employeeId = $("#leave-employeeid-" + leaveId).attr("data-employee-id");
	var data = { employeeId: employeeId, id: leaveId, status: status.toUpperCase() };
	ajaxCallJSONWrapper("/processLeave/submit", data, function () {
		var args = Array.prototype.slice.call(arguments);
		args.splice(0, 0, status);
		callbackFn.apply(this, args);
	}, function () {
		var args = Array.prototype.slice.call(arguments);
		args.splice(0, 0, status);
		callbackFn.apply(this, args);
	});
}

function callbackFn(status) {
	if (status == "ACCEPT") {
		alert("Leave approved successfully.");
	} else {
		alert("Leave rejected successfully.")
	}
	location.reload();
}

function errorFn(status) {
	if (status == "APPROVE") {
		alert("Failed to approve leave.");
	} else {
		alert("Failed to reject leave.")
	}
	location.reload();
}