package fr.epsi.ipeda.dal.dto;

public class ModuleDTO {

	// id, code, libelle, semestre, intervenant, dureeFFP, dureeTE,

	private String id;
	private String code;
	private String libelle;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

}
