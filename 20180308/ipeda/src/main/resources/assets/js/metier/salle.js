function openSalleModal() {
	$("#salleModal-container").modal("show");
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
			$("#salleModalHolder").html(data),
			$("#salleModal-container").modal("show");
		}
	});
	
}