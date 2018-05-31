package fr.epsi.ipeda.dal.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "parcours")
public class Parcours {

	@Id
	@GeneratedValue
	private Long id;

	@Basic(optional = false)
	private boolean optionnel = false;

	@ManyToOne
	private Formation formation;

	@NotNull
	private String libelle;

	@OneToMany(mappedBy = "parcours", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<UniteEnseignement> listeUnitesEnseignement = new ArrayList<UniteEnseignement>();

	@OneToMany(mappedBy = "parcours", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<BlocCompetences> listeBlocsCompetences = new ArrayList<BlocCompetences>();

	@ManyToOne
	private Specificite specificite;

	public Parcours() {
	}

	public Parcours(String libelle, boolean optionnel, Formation formation) {
		this.optionnel = optionnel;
		this.formation = formation;
		this.libelle = libelle;
	}

	public Parcours(Formation formation, String libelle) {
		this.formation = formation;
		this.libelle = libelle;
	}

	public Parcours(Formation formation, String libelle, Specificite specificite) {
		this.formation = formation;
		this.libelle = libelle;
		this.specificite = specificite;
	}

	public Parcours(Formation formation, String libelle, Specificite specificite, boolean optionnel) {
		this.formation = formation;
		this.libelle = libelle;
		this.optionnel = optionnel;
		this.specificite = specificite;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isOptionnel() {
		return optionnel;
	}

	public void setOptionnel(boolean optionnel) {
		this.optionnel = optionnel;
	}

	public Formation getFormation() {
		return formation;
	}

	public void setFormation(Formation formation) {
		this.formation = formation;
	}

	public List<UniteEnseignement> getListeUnitesEnseignement() {
		return listeUnitesEnseignement;
	}

	public void setListeUnitesEnseignement(List<UniteEnseignement> listeUnitesEnseignement) {
		this.listeUnitesEnseignement = listeUnitesEnseignement;
	}

	public List<BlocCompetences> getListeBlocsCompetences() {
		return listeBlocsCompetences;
	}

	public void setListeBlocsCompetences(List<BlocCompetences> listeBlocsCompetences) {
		this.listeBlocsCompetences = listeBlocsCompetences;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public Specificite getSpecificite() {
		return specificite;
	}

	public void setSpecificite(Specificite specificite) {
		this.specificite = specificite;
	}

}
