package fr.epsi.ipeda.dao.entity;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "cours")
public class Cours {

	@Id
	@GeneratedValue
	private Long id;

	@Basic(optional = false)
	private LocalDateTime dateDebut;

	@Type(type = "org.hibernate.type.DurationType")
	@Basic(optional = false)
	private Duration duree;

	@ManyToMany
	private List<Salle> listeSalles;

	@ManyToOne
	private Intervenant intervenant;

	@ManyToOne
	private Module module;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(LocalDateTime dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Duration getDuree() {
		return duree;
	}

	public void setDuree(Duration duree) {
		this.duree = duree;
	}

	public List<Salle> getListeSalles() {
		return listeSalles;
	}

	public void setListeSalles(List<Salle> listeSalles) {
		this.listeSalles = listeSalles;
	}

	public Intervenant getIntervenant() {
		return intervenant;
	}

	public void setIntervenant(Intervenant intervenant) {
		this.intervenant = intervenant;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

}
