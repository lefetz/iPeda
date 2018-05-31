package fr.epsi.ipeda.dal.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
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

	private LocalDate dateFinSemestre1;

	@NotNull
	@ManyToOne
	private AnneeScolaire anneeScolaire;

	@OneToMany(mappedBy = "formation", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Parcours> listeParcours = new ArrayList<Parcours>();

	@ManyToMany(mappedBy = "listeFormations")
	private List<Module> listeModules = new ArrayList<Module>(); // cas UDEV

	@OneToOne(mappedBy = "formation", cascade = CascadeType.ALL, orphanRemoval = true)
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

	public Formation(String libelle, AnneeScolaire anneeScolaire, LocalDate dateFinSemestre1) {
		this.libelle = libelle;
		this.anneeScolaire = anneeScolaire;
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

}
