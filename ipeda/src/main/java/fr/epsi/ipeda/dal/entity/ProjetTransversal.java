package fr.epsi.ipeda.dal.entity;

import java.time.Duration;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "projet_transversal")
public class ProjetTransversal extends Module {

	@ManyToOne
	@JoinColumn(name = "bloc_competences_id")
	@Basic(optional = false)
	private BlocCompetences blocCompetences;

	public ProjetTransversal() {
		super();
	}

	public ProjetTransversal(String code, String libelle, Duration dureeFFP, Duration dureeTE, Intervenant intervenant) {
		super(code, libelle, dureeFFP, dureeTE, intervenant, null);
	}

	public ProjetTransversal(String code, String libelle, Duration dureeFFP, Duration dureeTE) {
		super(code, libelle, dureeFFP, dureeTE, null);
	}

	public ProjetTransversal(String code, String libelle, Duration dureeFFP, Duration dureeTE, Intervenant intervenant, BlocCompetences blocCompetences) {
		super(code, libelle, dureeFFP, dureeTE, intervenant, null);
		this.blocCompetences = blocCompetences;
	}

	public BlocCompetences getBlocCompetences() {
		return blocCompetences;
	}

	public void setBlocCompetences(BlocCompetences blocCompetences) {
		this.blocCompetences = blocCompetences;
	}

}
