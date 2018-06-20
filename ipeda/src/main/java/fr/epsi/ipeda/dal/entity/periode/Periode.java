package fr.epsi.ipeda.dal.entity.periode;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "periode")
public class Periode {

	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	private LocalDateTime dateHeureDebut;

	private LocalDateTime dateHeureFin;

	@ManyToOne
	private PeriodeType periodeType;

	public Periode() {
	}

	public Periode(PeriodeType periodeType, LocalDateTime dateHeureDebut) {
		this.periodeType = periodeType;
		this.dateHeureDebut = dateHeureDebut;
	}

	public Periode(PeriodeType periodeType, LocalDateTime dateHeureDebut, LocalDateTime dateHeureFin) {
		this.periodeType = periodeType;
		this.dateHeureDebut = dateHeureDebut;
		this.dateHeureFin = dateHeureFin;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PeriodeType getPeriodeType() {
		return periodeType;
	}

	public void setPeriodeType(PeriodeType periodeType) {
		this.periodeType = periodeType;
	}

	public LocalDateTime getDateHeureDebut() {
		return dateHeureDebut;
	}

	public void setDateHeureDebut(LocalDateTime dateHeureDebut) {
		this.dateHeureDebut = dateHeureDebut;
	}

	public LocalDateTime getDateHeureFin() {
		return dateHeureFin;
	}

	public void setDateHeureFin(LocalDateTime dateHeureFin) {
		this.dateHeureFin = dateHeureFin;
	}

}
