function findTrFromIdInDatatable(id) {
	var result;
	$("table#example tbody tr").each(function(index ) {
		var tdId = $(this).find("td:eq(0)").text();
		if(tdId == id) {
			result = $(this);
			return false;
		}
	});
	return result;
}

function getModalContainer(modalId) {
	return $("#modal-container-" + modalId);
}
