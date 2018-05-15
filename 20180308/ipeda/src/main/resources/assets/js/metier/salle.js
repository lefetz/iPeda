function openSalleModal() {
	$("#modal-container").modal("show");
}

function openSalleModalUpdate(id) {

	$.ajax({
		url: "/rest/salles/update",
		data: { id : id },
		success: function(data) {
			$("#id").val(data.id);
			$("#libelle").val(data.libelle);
			$("#modal-container").modal("show");
		}
	});
	
}

function openSalleModalDelete(id) {


	
}
