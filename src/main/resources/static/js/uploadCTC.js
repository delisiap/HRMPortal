$(document).ready(function () {
	$('#uploadDocBody').submit(function (e) {
		e.preventDefault();

		var $form = $("#uploadDocBody");
		var fd = new FormData($(this)[0]);

		$.ajax({
			type: 'POST',
			url: $form.attr('action'),
			data: fd,
			cache: false,
			processData: false,
			contentType: false,
			success: function (response) {
				if(response && response.status && response.status=="success"){
					showSuccessMessage("Uploaded Successfully");
				}else{
					showErrorMessage("Error. Please try again later.");
				}
			},
			error: function (XMLHttpRequest, textStatus, errorThrown) {
				showErrorMessage("Error: " + errorThrown);
			}
		});
	});
});