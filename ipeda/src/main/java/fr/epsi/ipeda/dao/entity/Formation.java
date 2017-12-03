package fr.epsi.ipeda.dao.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "formation")
public class Formation {

	@Id
	@GeneratedValue
	private Long id;

	@Basic(optional = false)
	private String libelle;

	public enum TypeFormation {
		B1, B2, B3, I4, I5, UDEV
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "type_formation")
	private TypeFormation typeFormation;

	@Basic(optional = false)
	@Column(name = "date_debut")
	private LocalDate dateDebut;

	@Basic(optional = false)
	@Column(name = "date_fin")
	private LocalDate dateFin;

	@OneToOne(mappedBy = "formation", cascade = CascadeType.ALL, orphanRemoval = true)
	private Semestre semestre1;

	@OneToOne(mappedBy = "formation", cascade = CascadeType.ALL, orphanRemoval = true)
	private Semestre semestre2;

	@OneToMany(mappedBy = "formation", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Specialite> listeSpecialites;

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

	public LocalDate getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}

	public LocalDate getDateFin() {
		return dateFin;
	}

	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}

	public Semestre getSemestre1() {
		return semestre1;
	}

	public void addSemestre1(Semestre semestre) {
		semestre.setFormation(this);
		this.semestre1 = semestre;
	}

	public void removeSemestre1() {
		if (semestre1 != null) {
			semestre1.setFormation(null);
			this.semestre1 = null;
		}
	}

	public Semestre getSemestre2() {
		return semestre2;
	}

	public void addSemestre2(Semestre semestre) {
		semestre.setFormation(this);
		this.semestre2 = semestre;
	}

	public void removeSemestre2() {
		if (semestre2 != null) {
			semestre2.setFormation(null);
			this.semestre2 = null;
		}
	}

	public List<Specialite> getListeSpecialites() {
		return listeSpecialites;
	}

	public void setListeSpecialites(List<Specialite> listeSpecialites) {
		this.listeSpecialites = listeSpecialites;
	}

	public TypeFormation getTypeFormation() {
		return typeFormation;
	}

	public void setTypeFormation(TypeFormation typeFormation) {
		this.typeFormation = typeFormation;
	}

}
