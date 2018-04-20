package fr.epsi.ipeda.dal.entity;

import java.time.Duration;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "projet_transversal")
public class ProjetTransversal extends Module {

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bloc_comptences_id")
	@Basic(optional = false)
	private BlocCompetences blocCompetences;

	public ProjetTransversal() {
		super();
	}

	public ProjetTransversal(String code, String libelle, Semestre semestre, Duration dureeFFP, Duration dureeTE, Intervenant intervenant) {
		super(code, libelle, semestre, dureeFFP, dureeTE, intervenant);
	}

	public ProjetTransversal(String code, String libelle, Semestre semestre, Duration dureeFFP, Duration dureeTE) {
		super(code, libelle, semestre, dureeFFP, dureeTE);
	}

	// public ProjetTransversal(Module moduleParent, String libelle, Duration dureeFFP, Duration dureeTE, Intervenant intervenant) {
	// super(moduleParent, libelle, dureeFFP, dureeTE, intervenant);
	// }

	public ProjetTransversal(String code, String libelle, Semestre semestre, Duration dureeFFP, Duration dureeTE, Intervenant intervenant, BlocCompetences blocCompetences) {
		super(code, libelle, semestre, dureeFFP, dureeTE, intervenant);
		this.blocCompetences = blocCompetences;
	}

	public BlocCompetences getBlocCompetences() {
		return blocCompetences;
	}

	public void setBlocCompetences(BlocCompetences blocCompetences) {
		this.blocCompetences = blocCompetences;
	}

}
