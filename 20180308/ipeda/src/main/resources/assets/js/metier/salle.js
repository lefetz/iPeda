function openSalleModal() {

	$.ajax({
		url: "/rest/salles/add",
		success: function(data) {
			$("#salleModalHolder").html(data),
			$("#salleModal-container").modal("show");
		}
	});
	
}