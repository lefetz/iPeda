package fr.epsi.ipeda.dao.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "salle")
public class Salle {

	@Id
	@GeneratedValue
	private Long id;

	@Basic(optional = false)
	private String libelle;

	public enum CodeSalle {
		JAUNE, ROUGE, VERTE, GRISE, TP1, TP2, BDE, CONF, NORMANDIE
	}

	@Column(name = "code_salle")
	@Enumerated(EnumType.STRING)
	private CodeSalle codeSalle;

	@ManyToMany
	private List<Cours> listeCours = new ArrayList<>();

	public Salle() {
	}

	public Salle(CodeSalle codeSalle, String libelle) {
		this.codeSalle = codeSalle;
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

	public List<Cours> getListeCours() {
		return listeCours;
	}

	public void setListeCours(List<Cours> listeCours) {
		this.listeCours = listeCours;
	}

	public CodeSalle getCodeSalle() {
		return codeSalle;
	}

	public void setCodeSalle(CodeSalle codeSalle) {
		this.codeSalle = codeSalle;
	}

}
