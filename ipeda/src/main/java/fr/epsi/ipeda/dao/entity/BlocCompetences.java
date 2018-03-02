package fr.epsi.ipeda.dao.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bloc_competences")
public class BlocCompetences {

	@Id
	@GeneratedValue
	private Long id;

	@Basic(optional = false)
	private int numero;

	@Basic(optional = false)
	private String libelle;

	@ManyToOne
	@Basic(optional = false)
	private Parcours parcours;

	@OneToMany(mappedBy = "parcours", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<UniteEnseignement> listeUnitesEnseignement = new ArrayList<UniteEnseignement>();

	@OneToOne(mappedBy = "blocCompetences", cascade = CascadeType.ALL, orphanRemoval = true)
	private ProjetTransversal projetTransversal;

	public BlocCompetences() {

	}

	public BlocCompetences(Parcours parcours, int numero, String libelle) {
		this.parcours = parcours;
		this.numero = numero;
		this.libelle = libelle;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public Parcours getParcours() {
		return parcours;
	}

	public void setParcours(Parcours parcours) {
		this.parcours = parcours;
	}

	public List<UniteEnseignement> getListeUnitesEnseignement() {
		return listeUnitesEnseignement;
	}

	public void setListeUnitesEnseignement(List<UniteEnseignement> listeUnitesEnseignement) {
		this.listeUnitesEnseignement = listeUnitesEnseignement;
	}

	public ProjetTransversal getProjetTransversal() {
		return projetTransversal;
	}

	public void setProjetTransversal(ProjetTransversal projetTransversal) {
		this.projetTransversal = projetTransversal;
	}

}
