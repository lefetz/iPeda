package fr.epsi.ipeda.dao.entity;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
	private LocalDateTime dateHeureDebut;

	@Type(type = "org.hibernate.type.DurationType")
	@Basic(optional = false)
	private Duration duree;

	@ManyToMany(mappedBy = "listeCours")
	private List<Salle> listeSalles = new ArrayList<>();

	@ManyToOne
	private Module module;

	public Cours() {
	}

	public Cours(LocalDateTime dateHeureDebut, Duration duree, Module module, List<Salle> listeSalles) {
		this.dateHeureDebut = dateHeureDebut;
		this.duree = duree;
		this.module = module;
		addSalle(listeSalles);
	}

	public Cours(LocalDateTime dateHeureDebut, Duration duree, Module module, Salle salle) {
		this.dateHeureDebut = dateHeureDebut;
		this.duree = duree;
		this.module = module;
		addSalle(salle);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	public LocalDateTime getDateHeureDebut() {
		return dateHeureDebut;
	}

	public void setDateHeureDebut(LocalDateTime dateHeureDebut) {
		this.dateHeureDebut = dateHeureDebut;
	}

	public void addSalle(Salle salle) {
		if (null != listeSalles && null != salle) {
			listeSalles.add(salle);
			salle.getListeCours().add(this);
		}
	}

	public void addSalle(List<Salle> listeSalles) {
		if (null != listeSalles) {
			for (Salle salle : listeSalles) {
				listeSalles.add(salle);
				salle.getListeCours().add(this);
			}
		}
	}

}
