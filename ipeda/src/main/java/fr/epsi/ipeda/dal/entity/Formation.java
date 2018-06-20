package fr.epsi.ipeda.dal.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "formation")
public class Formation {

	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	private String libelle;

	@NotNull
	private String libelleCourt;

	@NotNull
	@Column(name = "date_debut")
	// @DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate dateDebut;

	@NotNull
	@Column(name = "date_fin")
	// @DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate dateFin;

	@NotNull
	@Column(name = "date_fin_semestre1")
	// @DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate dateFinSemestre1;

	@NotNull
	@ManyToOne
	private AnneeScolaire anneeScolaire;

	@OneToMany(mappedBy = "formation", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Parcours> listeParcours = new ArrayList<Parcours>();

	@ManyToMany(mappedBy = "listeFormations", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Module> listeModules = new ArrayList<Module>(); // cas UDEV

	@OneToOne(mappedBy = "formation", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private Planning planning;

	public Formation() {
	}

	public Formation(String libelle) {
		this.libelle = libelle;

	}

	public Formation(String libelle, List<Module> listeModules) {
		this.libelle = libelle;
		addModule(listeModules);
	}

	public Formation(String libelle, String libelleCourt, AnneeScolaire anneeScolaire, LocalDate dateDebut,
			LocalDate dateFin, LocalDate dateFinSemestre1) {
		this.libelle = libelle;
		this.libelleCourt = libelleCourt;
		this.anneeScolaire = anneeScolaire;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.dateFinSemestre1 = dateFinSemestre1;
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

	public List<Parcours> getListeParcours() {
		return listeParcours;
	}

	public void setListeParcours(List<Parcours> listeParcours) {
		this.listeParcours = listeParcours;
	}

	public void addParcours(Parcours parcours) {
		if (null != parcours) {
			listeParcours.add(parcours);
		}
	}

	public void addModule(List<Module> listeModules) {
		if (null != listeModules) {
			for (Module module : listeModules) {
				listeModules.add(module);
				module.getListeFormations().add(this);
			}
		}
	}

	public AnneeScolaire getAnneeScolaire() {
		return anneeScolaire;
	}

	public void setAnneeScolaire(AnneeScolaire anneeScolaire) {
		this.anneeScolaire = anneeScolaire;
	}

	public LocalDate getDateFinSemestre1() {
		return dateFinSemestre1;
	}

	public void setDateFinSemestre1(LocalDate dateFinSemestre1) {
		this.dateFinSemestre1 = dateFinSemestre1;
	}

	public Planning getPlanning() {
		return planning;
	}

	public void setPlanning(Planning planning) {
		this.planning = planning;
	}

	public String getLibelleCourt() {
		return libelleCourt;
	}

	public void setLibelleCourt(String libelleCourt) {
		this.libelleCourt = libelleCourt;
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

}
