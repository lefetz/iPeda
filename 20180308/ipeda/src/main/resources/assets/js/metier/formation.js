
function openModalCreate(modalId) {
	var container = getModalContainer(modalId);
	//container.find("#anneeScolaireDateDebut").val(""); // reset
	//container.find("#anneeScolaireDateDebut").val(""); // reset
	container.find("small").text(""); // reset
	container.modal("show");
}

function openModalUpdate(modalId, id, oTable) {
	var container = getModalContainer(modalId);
	var row = findRowFromIdInDatatable(id); alert(row);
	if(row != null) {
		container.find("#id").val(row.data()["id"]);
		container.find("#libelle").val(row.data()["libelle"]);
		container.find("#libelleCourt").val(row.data()["libelleCourt"]);
		container.find("#dateFinSemestre1").val(row.data()["dateFinSemestre1"]);
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
