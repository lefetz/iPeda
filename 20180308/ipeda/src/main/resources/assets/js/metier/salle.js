function openSalleModal() {
	$("#modal-container").modal("show");
}

function saveSalleModal() {

	$.ajax({
		url: "/rest/salles/save",
		success: function(data) {

		}
	});
	
}

function openSalleModalUpdate() {

	$.ajax({
		url: "/rest/salles/update",
		success: function(data) {
			$("#modalHolder").html(data),
			$("#modal-container").modal("show");
		}
	});
	
}