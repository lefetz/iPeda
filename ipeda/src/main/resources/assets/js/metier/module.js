
function openModalCreate(modalId) {
	getModalContainer(modalId).modal("show");
}

function openModalUpdate(modalId, id) {
	var container = getModalContainer(modalId);
	var tr = findTrFromIdInDatatable(id);
	if(tr != false) {
		container.find("#id").val(tr.find("td:eq(0)").text());
		container.find("#libelle").val(tr.find("td:eq(1)").text());
		container.modal("show");
	}
}

function openModalDelete(modalId, id) {
	var container = getModalContainer(modalId);
	var tr = findTrFromIdInDatatable(id);
	if(tr != false) {
		container.find("#id").val(tr.find("td:eq(0)").text());
		var message = container.find("#message").html();
		container.find("#message").html(message.replace("{0}", tr.find("td:eq(1)").text()));
		container.modal("show");
	}
}

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