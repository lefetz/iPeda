package fr.epsi.ipeda.dao.entity;

import java.time.Duration;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "module")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Module {

	@Id
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
	private List<Cours> listeCours;

	@ManyToOne
	@Basic(optional = false)
	private Intervenant intervenant;

	@ManyToOne
	private Module moduleParent;

	@OneToMany(mappedBy = "moduleParent", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Module> listeModulesEnfants;

	@ManyToOne
	@Basic(optional = false)
	private UniteEnseignement uniteEnseignement;

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
		this.moduleParent = moduleParent;
		this.libelle = libelle;
		this.dureeFFP = dureeFFP;
		this.dureeTE = dureeTE;
		this.intervenant = intervenant;
	}

	public Module() {

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

	public Intervenant getIntervenant() {
		return intervenant;
	}

	public void setIntervenant(Intervenant intervenant) {
		this.intervenant = intervenant;
	}

	public Module getModuleParent() {
		return moduleParent;
	}

	public void setModuleParent(Module moduleParent) {
		this.moduleParent = moduleParent;
	}

	public List<Module> getListeModulesEnfants() {
		return listeModulesEnfants;
	}

	public void setListeModulesEnfants(List<Module> listeModulesEnfants) {
		this.listeModulesEnfants = listeModulesEnfants;
	}

	public UniteEnseignement getUniteEnseignement() {
		return uniteEnseignement;
	}

	public void setUniteEnseignement(UniteEnseignement uniteEnseignement) {
		this.uniteEnseignement = uniteEnseignement;
	}

}
