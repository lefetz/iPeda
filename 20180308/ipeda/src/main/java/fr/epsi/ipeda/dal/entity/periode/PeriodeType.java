package fr.epsi.ipeda.dal.entity.periode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "periode_type")
public class PeriodeType {

	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	private String libelle;

	public PeriodeType() {
	}

	public PeriodeType(String libelle) {
		this.libelle = libelle;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

}
