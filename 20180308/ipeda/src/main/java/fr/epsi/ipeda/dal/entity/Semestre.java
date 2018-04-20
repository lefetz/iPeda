package fr.epsi.ipeda.dal.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "semestre")
public class Semestre {

	@Id
	@GeneratedValue
	private Long id;

	public enum NumeroSemestre {
		SEMESTRE1, SEMESTRE2
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "numero_semestre")
	private NumeroSemestre numeroSemestre;

	@Basic(optional = false)
	@Column(name = "date_debut")
	private LocalDate dateDebut;

	@Basic(optional = false)
	@Column(name = "date_fin")
	private LocalDate dateFin;

	@OneToMany(mappedBy = "semestre", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Module> listeModules = new ArrayList<Module>();

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "formation_id")
	private Formation formation;

	public Semestre() {
	}

	public Semestre(NumeroSemestre numeroSemestre, LocalDate dateDebut, LocalDate dateFin) {
		this.numeroSemestre = numeroSemestre;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public List<Module> getListeModules() {
		return listeModules;
	}

	public void setListeModules(List<Module> listeModules) {
		this.listeModules = listeModules;
	}

	public Formation getFormation() {
		return formation;
	}

	public void setFormation(Formation formation) {
		this.formation = formation;
	}

	public NumeroSemestre getNumeroSemestre() {
		return numeroSemestre;
	}

	public void setNumeroSemestre(NumeroSemestre numeroSemestre) {
		this.numeroSemestre = numeroSemestre;
	}

}
