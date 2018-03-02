package fr.epsi.ipeda.dao.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

	public enum TypeUE {
		DEV, RESEAUX, SECU, DATA, MOBILITE, ERP, CLOUD, VIRTU
	}

	@Column(name = "type_unite_enseignement")
	@Enumerated(EnumType.STRING)
	private TypeUE typeUE;

	public UniteEnseignement() {
	}

	public UniteEnseignement(TypeUE typeUE, String libelle, Parcours parcours) {
		this.typeUE = typeUE;
		this.libelle = libelle;
		this.parcours = parcours;
	}

	public UniteEnseignement(TypeUE typeUE, String libelle, BlocCompetences blocCompetences) {
		this.typeUE = typeUE;
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

	public TypeUE getTypeUE() {
		return typeUE;
	}

	public void setTypeUE(TypeUE typeUE) {
		this.typeUE = typeUE;
	}

	public void addModule(Module module) {
		if (null != listeModules) {
			listeModules.add(module);
		}
	}

}
