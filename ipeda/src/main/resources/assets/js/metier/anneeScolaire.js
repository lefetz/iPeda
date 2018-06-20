
function openModalCreate(modalId) {
	var container = getModalContainer(modalId);
	container.find("#dateDebut").val(""); // reset
	container.find("#dateFin").val(""); // reset
	container.find("small").text(""); // reset
	container.modal("show");
}

function openModalUpdate(modalId, id) {
	var container = getModalContainer(modalId);
	var tr = findTrFromIdInDatatable(id);
	if(tr != false) {
		container.find("#id").val(tr.find("td:eq(0)").text());
		container.find("#dateDebut").val(tr.find("td:eq(2)").text());
		container.find("#dateFin").val(tr.find("td:eq(3)").text());
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


