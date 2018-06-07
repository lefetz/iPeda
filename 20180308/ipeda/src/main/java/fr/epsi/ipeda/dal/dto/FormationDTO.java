package fr.epsi.ipeda.dal.dto;

public class FormationDTO {

	private String id;

	private String libelle;

	private String anneeScolaireDateDebut;

	private String anneeScolaireDateFin;

	private String dateFinSemestre1;

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

	public String getDateFinSemestre1() {
		return dateFinSemestre1;
	}

	public void setDateFinSemestre1(String dateFinSemestre1) {
		this.dateFinSemestre1 = dateFinSemestre1;
	}

	public String getAnneeScolaireDateDebut() {
		return anneeScolaireDateDebut;
	}

	public void setAnneeScolaireDateDebut(String anneeScolaireDateDebut) {
		this.anneeScolaireDateDebut = anneeScolaireDateDebut;
	}

	public String getAnneeScolaireDateFin() {
		return anneeScolaireDateFin;
	}

	public void setAnneeScolaireDateFin(String anneeScolaireDateFin) {
		this.anneeScolaireDateFin = anneeScolaireDateFin;
	}

}
