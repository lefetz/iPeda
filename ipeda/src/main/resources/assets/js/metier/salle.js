
function openSalleModalCreate(modalId) {
	getModalContainer(modalId).modal("show");
}

function openSalleModalUpdate(modalId, id) {
	var container = getModalContainer(modalId);
	var tr = findTrFromIdInDatatable(id);
	if(tr != false) {
		container.find("#id").val(tr.find("td:eq(0)").text());
		container.find("#libelle").val(tr.find("td:eq(1)").text());
		container.modal("show");
	}
}

function openSalleModalDelete(modalId, id) {
	var container = getModalContainer(modalId);
	var tr = findTrFromIdInDatatable(id);
	if(tr != false) {
		container.find("#id").val(tr.find("td:eq(0)").text());
		var message = container.find("#message").html();
		container.find("#message").html(message.replace("{0}", tr.find("td:eq(1)").text()));
		container.modal("show");
	}
}
