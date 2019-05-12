$(window).on('load', function () {
	ajaxCallWrapper("/config/getconfig", {}, loadConfigurationItems, errorFn);
});

function errorFn() {
	// TODO-- on error
}

function loadConfigurationItems(data, status, jqXHR) {
	if (data && data.status == "success") {
		var configContainer = $("#table-body");
		var config = data.data.properties;
		var configItem;

		if (config.length > 0) {
			for (var i = 0; i < config.length; i++) {
				configItem = config[i];
				configContainer.append(createConfigureForm(configItem, i));
			}
		} else {
			var provessconfigDiv = $("#process-leave-div");
			provessconfigDiv.empty();
			provessconfigDiv.append("<p class=\"empty-p\">No configurable logic available.<p>");
		}
	} else {
		location.href = "/error.html";
	}
}

function onBtnSaveClick() {
	var arrUnsavedValues = getUnsavedValues();
	if (arrUnsavedValues.length == 0) {
		alert("No values were changed");
	} else {
		var data = {
			properties: arrUnsavedValues
		};
		ajaxCallJSONWrapper("/config/updateconfig", data, successFn, updateErrorFn);
	}
}

function successFn(data) {
	if (data && data.status == "success") {
		alert("Successfully updated the configuration.");
		location.reload();
	} else {
		updateErrorFn();
	}
}

function updateErrorFn() {
	alert("Failed to update the configuration.");
}

function getUnsavedValues() {
	var arrayValues = [];
	var currentElement;
	var currentValue;
	var newValue;
	for (var key in configurableValues) {
		if (configurableValues.hasOwnProperty(key)) {
			currentElement = document.getElementById("idadminconfig-" + key);
			if (currentElement) {
				currentValue = currentElement.value;
				newValue = configurableValues[key];
				if (currentValue != newValue) {
					arrayValues.push({
						propertyName: currentElement.name,
						propertyValue: currentElement.value
					});
				}
			}
		}
	}
	return arrayValues;
}

function onBtnDiscardClick() {
	for (var key in configurableValues) {
		if (configurableValues.hasOwnProperty(key)) {
			document.getElementById("idadminconfig-" + key).value = configurableValues[key];
		}
	}
}
var configurableValues = {};

function createConfigureForm(data, index) {
	var ret = "";
	ret += "<tr>";
	ret += "<td scope=\"row\" id=\"adminconfig-property-" + index + "\">" + data.propertyName + "</td>";
	ret += "<td scope=\"row\" id=\"adminconfig-value-" + index + "\"><input type='text' id='idadminconfig-" + index + "' value='" + data.propertyValue + "' name='" + data.propertyName + "' ></td>";
	ret += "</tr>";

	configurableValues[index] = data.propertyValue;

	return ret;
}
