package fr.epsi.ipeda.dal.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "unite_enseignement")
public class UniteEnseignement {

	@Id
	@GeneratedValue
	private Long id;

	@Basic(optional = false)
	private String libelle;

	@ManyToOne
	private Parcours parcours;

	@ManyToOne
	private BlocCompetences blocCompetences;

	@OneToMany(mappedBy = "uniteEnseignement", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Module> listeModules = new ArrayList<Module>();

	@Column(name = "ferme")
	private boolean isFerme;

	@ManyToOne
	private Specificite specificite;

	public UniteEnseignement() {
	}

	public UniteEnseignement(String libelle, Parcours parcours, Specificite specificite) {
		this.specificite = specificite;
		this.libelle = libelle;
		this.parcours = parcours;
	}
	
	public UniteEnseignement(String libelle, Parcours parcours) {
		this.libelle = libelle;
		this.parcours = parcours;
	}

	public UniteEnseignement(String libelle, BlocCompetences blocCompetences, Specificite specificite) {
		this.specificite = specificite;
		this.libelle = libelle;
		this.blocCompetences = blocCompetences;
	}

	public UniteEnseignement(String libelle, BlocCompetences blocCompetences, Specificite specificite, boolean isFerme) {
		this.specificite = specificite;
		this.libelle = libelle;
		this.blocCompetences = blocCompetences;
		this.isFerme = isFerme;
	}

	public UniteEnseignement(String libelle, BlocCompetences blocCompetences) {
		this.libelle = libelle;
		this.blocCompetences = blocCompetences;
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

	public Parcours getParcours() {
		return parcours;
	}

	public void setParcours(Parcours parcours) {
		this.parcours = parcours;
	}

	public BlocCompetences getBlocCompetences() {
		return blocCompetences;
	}

	public void setBlocCompetences(BlocCompetences blocCompetences) {
		this.blocCompetences = blocCompetences;
	}

	public List<Module> getListeModules() {
		return listeModules;
	}

	public void setListeModules(List<Module> listeModules) {
		this.listeModules = listeModules;
	}

	public void addModule(Module module) {
		if (null != listeModules) {
			listeModules.add(module);
		}
	}

	public boolean isFerme() {
		return isFerme;
	}

	public void setFerme(boolean isFerme) {
		this.isFerme = isFerme;
	}

	public Specificite getSpecificite() {
		return specificite;
	}

	public void setSpecificite(Specificite specificite) {
		this.specificite = specificite;
	}

}
