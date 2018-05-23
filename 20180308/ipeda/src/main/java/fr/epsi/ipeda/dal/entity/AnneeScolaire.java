package fr.epsi.ipeda.dal.entity;

import java.time.LocalDate;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "anneeScolaire")
public class AnneeScolaire {

	@Id
	@GeneratedValue
	private Long id;

	@Basic(optional = false)
	@Column(name = "date_debut")
	private LocalDate dateDebut;

	@Basic(optional = false)
	@Column(name = "date_fin")
	private LocalDate dateFin;

	public AnneeScolaire() {
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

}
