package fr.epsi.ipeda.dal.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "salle")
public class Salle {

	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	@Size(min = 1, max = 255)
	private String libelle;

	@ManyToMany
	private List<Seance> listSeances = new ArrayList<Seance>();

	public Salle() {
	}

	public Salle(String libelle) {
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
