
function openModalCreate(modalId) {
	var container = getModalContainer(modalId);
	container.find("small").text(""); // reset
	container.modal("show");
}

function openModalUpdate(modalId, id, oTable) {
	var container = getModalContainer(modalId);
	var row = findRowFromIdInDatatable(id);
	if(row != null) {
		container.find("#id").val(row.data()["id"]);
		container.find("form#modal-form-formationUpdate select#anneeScolaire").val(row.data()["anneeScolaireId"]);
		container.find("#libelle").val(row.data()["libelle"]);
		container.find("#libelleCourt").val(row.data()["libelleCourt"]);
		
		var dateDebut = moment(row.data()["dateDebut"]);
		$('#modal-form-formationUpdate #dateDebut').datepicker('setDate', dateDebut.toDate());
		$('#modal-form-formationUpdate #dateDebut').datepicker('update');
		$('#modal-form-formationUpdate #dateDebut').val(dateDebut.format('DD/MM/YYYY'));
		
		var dateFin = moment(row.data()["dateFin"]);
		$('#modal-form-formationUpdate #dateFin').datepicker('setDate', dateFin.toDate());
		$('#modal-form-formationUpdate #dateFin').datepicker('update');
		$('#modal-form-formationUpdate #dateFin').val(dateFin.format('DD/MM/YYYY'));
		
		var dateFinSemestre1 = moment(row.data()["dateFinSemestre1"]);
		$('#modal-form-formationUpdate #dateFinSemestre1').datepicker('setDate', dateFinSemestre1.toDate());
		$('#modal-form-formationUpdate #dateFinSemestre1').datepicker('update');
		$('#modal-form-formationUpdate #dateFinSemestre1').val(dateFinSemestre1.format('DD/MM/YYYY'));
		
		container.find("small").text(""); // reset
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
