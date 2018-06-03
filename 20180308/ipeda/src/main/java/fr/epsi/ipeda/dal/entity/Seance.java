package fr.epsi.ipeda.dal.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import fr.epsi.ipeda.dal.entity.activite.Activite;

@Entity
@Table(name = "seance")
public class Seance {

	@Id
	@GeneratedValue
	private Long id;

	private LocalDate date;

	private LocalTime heureDebut;

	private LocalTime heureFin;

	@ManyToOne
	private Activite activite;

	@ManyToMany(mappedBy = "listSeances")
	private List<Salle> listSalles = new ArrayList<Salle>();

	@ManyToOne
	private Intervenant intervenant;

	@ManyToOne
	private Planning planning;

	private String remarque;

	public Seance() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getHeureDebut() {
		return heureDebut;
	}

	public void setHeureDebut(LocalTime heureDebut) {
		this.heureDebut = heureDebut;
	}

	public LocalTime getHeureFin() {
		return heureFin;
	}

	public void setHeureFin(LocalTime heureFin) {
		this.heureFin = heureFin;
	}

	public Activite getActivite() {
		return activite;
	}

	public void setActivite(Activite activite) {
		this.activite = activite;
	}

	public List<Salle> getListSalles() {
		return listSalles;
	}

	public void setListSalles(List<Salle> listSalles) {
		this.listSalles = listSalles;
	}

	public String getRemarque() {
		return remarque;
	}

	public void setRemarque(String remarque) {
		this.remarque = remarque;
	}

	public Intervenant getIntervenant() {
		return intervenant;
	}

	public void setIntervenant(Intervenant intervenant) {
		this.intervenant = intervenant;
	}

	public Planning getPlanning() {
		return planning;
	}

	public void setPlanning(Planning planning) {
		this.planning = planning;
	}

}
