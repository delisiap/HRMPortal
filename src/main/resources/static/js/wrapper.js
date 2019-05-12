function ajaxCallWrapper(url, data, callbackFn, errorcallback) {
	$.ajax({
		url: url,
		type: 'post',
		data: data,
		success: function (data, status, jqXHR) {
			var redirectUrl = jqXHR.getResponseHeader("redirect-url");
			callbackFn.apply(this, arguments);
			if (redirectUrl != "" && redirectUrl != null) {
				window.location = redirectUrl;
			}
		},
		error: function (jqXHR) {
			var redirectUrl = jqXHR.getResponseHeader("redirect-url");
			errorcallback.apply(this, arguments);
			if (redirectUrl != "" && redirectUrl != null) {
				window.location = redirectUrl;
			}
		}
	});
}

function ajaxCallJSONWrapper(url, data, callbackFn, errorcallback) {
	$.ajax({
		url: url,
		contentType: "application/json; charset=utf-8",
		type: 'post',
		data: JSON.stringify(data),
		success: function (data, status, jqXHR) {
			var redirectUrl = jqXHR.getResponseHeader("redirect-url");
			callbackFn.apply(this, arguments);
			if (redirectUrl != "" && redirectUrl != null) {
				window.location = redirectUrl;
			}
		},
		error: function (jqXHR) {
			var redirectUrl = jqXHR.getResponseHeader("redirect-url");
			errorcallback.apply(this, arguments);
			if (redirectUrl != "" && redirectUrl != null) {
				window.location = redirectUrl;
			}
		}
	});
}

function encodeJSON({ data }) {
	var keys = Object.getOwnPropertyNames(data),
		encodedData = "",
		field, value, i;

	for (i = 0; i < keys.length; i++) {
		field = form.elements[keys[i]];
		value = field.value;
		encodedData += fields[i] + "=" + value;
		if (i + 1 != fields.length) {
			encodedData += "&"
		}
	}
	return encodedData;
}