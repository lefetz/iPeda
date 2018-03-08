package fr.epsi.ipeda.dao.entity;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "module")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Module {

	@Id
	@GeneratedValue
	private Long id;

	private String code;

	@ManyToOne
	private Semestre semestre;

	@Basic(optional = false)
	private String libelle;

	@Column(name = "duree_ffp")
	@Type(type = "org.hibernate.type.DurationType")
	private Duration dureeFFP;

	@Column(name = "duree_te")
	@Type(type = "org.hibernate.type.DurationType")
	private Duration dureeTE;

	@OneToMany(mappedBy = "module", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Cours> listeCours = new ArrayList<Cours>();

	@ManyToOne
	@Basic(optional = false)
	private Intervenant intervenant;

	@ManyToOne
	private Module moduleParentMutualise;

	@OneToMany(mappedBy = "moduleParentMutualise", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Module> listeModulesMutualises = new ArrayList<Module>();

	@ManyToOne
	private Module moduleParentCompose;

	@OneToMany(mappedBy = "moduleParentCompose", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Module> listeModulesComposes = new ArrayList<Module>();

	@ManyToOne
	@Basic(optional = false)
	private UniteEnseignement uniteEnseignement;

	@ManyToMany
	private List<Formation> listeFormations = new ArrayList<Formation>(); // cas UDEV

	public Module() {
	}

	public Module(String code, String libelle, Semestre semestre, Duration dureeFFP, Duration dureeTE, Intervenant intervenant) {
		this.code = code;
		this.libelle = libelle;
		this.semestre = semestre;
		this.dureeFFP = dureeFFP;
		this.dureeTE = dureeTE;
		this.intervenant = intervenant;
	}

	public Module(String code, String libelle, Semestre semestre, Duration dureeFFP, Duration dureeTE, Intervenant intervenant, UniteEnseignement uniteEnseignement) {
		this.code = code;
		this.libelle = libelle;
		this.semestre = semestre;
		this.dureeFFP = dureeFFP;
		this.dureeTE = dureeTE;
		this.intervenant = intervenant;
		this.uniteEnseignement = uniteEnseignement;
	}

	public Module(String code, String libelle, Semestre semestre, Duration dureeFFP, Duration dureeTE) {
		this.code = code;
		this.libelle = libelle;
		this.semestre = semestre;
		this.dureeFFP = dureeFFP;
		this.dureeTE = dureeTE;
	}

	public Module(Module moduleParent, String libelle, Duration dureeFFP, Duration dureeTE, Intervenant intervenant) {
		this.libelle = libelle;
		this.dureeFFP = dureeFFP;
		this.dureeTE = dureeTE;
		this.intervenant = intervenant;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Semestre getSemestre() {
		return semestre;
	}

	public void setSemestre(Semestre semestre) {
		this.semestre = semestre;
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

	public Duration getDureeFFP() {
		return dureeFFP;
	}

	public void setDureeFFP(Duration dureeFFP) {
		this.dureeFFP = dureeFFP;
	}

	public Duration getDureeTE() {
		return dureeTE;
	}

	public void setDureeTE(Duration dureeTE) {
		this.dureeTE = dureeTE;
	}

	public UniteEnseignement getUniteEnseignement() {
		return uniteEnseignement;
	}

	public void setUniteEnseignement(UniteEnseignement uniteEnseignement) {
		this.uniteEnseignement = uniteEnseignement;
	}

	public List<Formation> getListeFormations() {
		return listeFormations;
	}

	public void setListeFormations(List<Formation> listeFormations) {
		this.listeFormations = listeFormations;
	}

	public Intervenant getIntervenant() {
		return intervenant;
	}

	public void setIntervenant(Intervenant intervenant) {
		this.intervenant = intervenant;
	}

	public Module getModuleParentMutualise() {
		return moduleParentMutualise;
	}

	public void setModuleParentMutualise(Module moduleParentMutualise) {
		this.moduleParentMutualise = moduleParentMutualise;
	}

	public List<Module> getListeModulesMutualises() {
		return listeModulesMutualises;
	}

	public void setListeModulesMutualises(List<Module> listeModulesMutualises) {
		this.listeModulesMutualises = listeModulesMutualises;
	}

	public Module getModuleParentCompose() {
		return moduleParentCompose;
	}

	public void setModuleParentCompose(Module moduleParentCompose) {
		this.moduleParentCompose = moduleParentCompose;
	}

	public List<Module> getListeModulesComposes() {
		return listeModulesComposes;
	}

	public void setListeModulesComposes(List<Module> listeModulesComposes) {
		this.listeModulesComposes = listeModulesComposes;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Module)) {
			return false;
		}
		return this.dureeFFP == ((Module) obj).dureeFFP && this.dureeTE == ((Module) obj).dureeTE && this.intervenant.equals(((Module) obj).intervenant);
	}

}
