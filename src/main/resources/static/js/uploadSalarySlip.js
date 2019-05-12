$(document).ready(function () {
	$('#uploadDocBody').submit(function (e) {
		e.preventDefault();

		var $form = $("#uploadDocBody");
		var fd = new FormData($(this)[0]);
		console.info(fd);

		$.ajax({
			type: 'POST',
			url: $form.attr('action'),
			data: fd,
			cache: false,
			processData: false,
			contentType: false,
			success: function (response) {
				showSuccessMessage("Uploaded Successfully");
			},
			error: function (XMLHttpRequest, textStatus, errorThrown) {
				showErrorMessage("Error: " + errorThrown);
			}
		});
	});
});