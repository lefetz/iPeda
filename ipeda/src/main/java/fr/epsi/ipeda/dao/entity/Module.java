package fr.epsi.ipeda.dao.entity;

import java.time.Duration;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "module")
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
	private Intervenant intervenant;

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

}
