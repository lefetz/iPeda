package fr.epsi.ipeda.dal.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import fr.epsi.ipeda.dal.entity.periode.Periode;

@Entity
@Table(name = "planning")
public class Planning {

	@Id
	@GeneratedValue
	private Long id;

	@OneToMany
	private List<Periode> listPeriode = new ArrayList<Periode>();

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "formation_id")
	private Formation formation;

	public Planning() {
	}

	public Planning(Formation formation) {
		this.formation = formation;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Periode> getListPeriode() {
		return listPeriode;
	}

	public void setListPeriode(List<Periode> listPeriode) {
		this.listPeriode = listPeriode;
	}

	public void addPeriode(Periode periode) {
		listPeriode.add(periode);
	}

}
