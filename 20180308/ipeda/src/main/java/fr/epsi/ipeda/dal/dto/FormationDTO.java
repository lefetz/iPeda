package fr.epsi.ipeda.dal.dto;

public class FormationDTO {

	private String id;

	private String libelle;

	private String semestre1DateDebut;

	private String semestre1DateFin;

	private String semestre2DateDebut;

	private String semestre2DateFin;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getSemestre1DateDebut() {
		return semestre1DateDebut;
	}

	public void setSemestre1DateDebut(String semestre1DateDebut) {
		this.semestre1DateDebut = semestre1DateDebut;
	}

	public String getSemestre1DateFin() {
		return semestre1DateFin;
	}

	public void setSemestre1DateFin(String semestre1DateFin) {
		this.semestre1DateFin = semestre1DateFin;
	}

	public String getSemestre2DateDebut() {
		return semestre2DateDebut;
	}

	public void setSemestre2DateDebut(String semestre2DateDebut) {
		this.semestre2DateDebut = semestre2DateDebut;
	}

	public String getSemestre2DateFin() {
		return semestre2DateFin;
	}

	public void setSemestre2DateFin(String semestre2DateFin) {
		this.semestre2DateFin = semestre2DateFin;
	}

}
