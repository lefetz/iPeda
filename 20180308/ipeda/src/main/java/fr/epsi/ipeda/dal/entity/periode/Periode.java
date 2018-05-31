package fr.epsi.ipeda.dal.entity.periode;

import java.time.LocalDate;

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
	private LocalDate dateDebut;

	private LocalDate dateFin;

	@ManyToOne
	private PeriodeType periodeType;

	public Periode() {
	}

	public Periode(PeriodeType periodeType, LocalDate dateDebut) {
		this.periodeType = periodeType;
		this.dateDebut = dateDebut;
	}

	public Periode(PeriodeType periodeType, LocalDate dateDebut, LocalDate dateFin) {
		this.periodeType = periodeType;
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

	public PeriodeType getPeriodeType() {
		return periodeType;
	}

	public void setPeriodeType(PeriodeType periodeType) {
		this.periodeType = periodeType;
	}

}
