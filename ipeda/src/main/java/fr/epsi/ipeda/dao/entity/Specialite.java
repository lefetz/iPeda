package fr.epsi.ipeda.dao.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "specialite")
public class Specialite {

	@Id
	@GeneratedValue
	private Long id;

	@Basic(optional = false)
	private String libelle;

	@ManyToOne
	private Formation formation;

	public enum TypeParcours {
		SOCLE, COMPLEMENTAIRE, METIER, PROFESSIONNEL, BTS_SIO
	}

	@Column(name = "type_parcours")
	@Enumerated(EnumType.STRING)
	private TypeParcours typeParcours;

	public Specialite(TypeParcours typeParcours, String libelle) {
		this.typeParcours = typeParcours;
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

	public Formation getFormation() {
		return formation;
	}

	public void setFormation(Formation formation) {
		this.formation = formation;
	}

	public TypeParcours getTypeParcours() {
		return typeParcours;
	}

	public void setTypeParcours(TypeParcours typeParcours) {
		this.typeParcours = typeParcours;
	}

}
