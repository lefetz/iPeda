package fr.epsi.ipeda.dao.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "parcours")
public class Parcours {

	@Id
	@GeneratedValue
	private Long id;

	@Basic(optional = false)
	private boolean optionnel = false;

	@ManyToOne
	private Formation formation;

	public enum TypeParcours {
		SOCLE, COMPLEMENTAIRE, METIER, PROFESSIONNEL, BTS_SIO
	}

	@Column(name = "type_parcours")
	@Enumerated(EnumType.STRING)
	private TypeParcours typeParcours;

	@OneToMany(mappedBy = "parcours", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<UniteEnseignement> listeUnitesEnseignement = new ArrayList<UniteEnseignement>();

	@OneToMany(mappedBy = "parcours", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<BlocCompetences> listeBlocsCompetences = new ArrayList<BlocCompetences>();

	public Parcours() {
	}

	public Parcours(TypeParcours typeParcours, boolean optionnel, Formation formation) {
		this.optionnel = optionnel;
		this.formation = formation;
		this.typeParcours = typeParcours;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isOptionnel() {
		return optionnel;
	}

	public void setOptionnel(boolean optionnel) {
		this.optionnel = optionnel;
	}

	public Formation getFormation() {
		return formation;
	}

	public void setFormation(Formation formation) {
		this.formation = formation;
	}

	public TypeParcours getTypeParcours() {
		return typeParcours;
	}

	public void setTypeParcours(TypeParcours typeParcours) {
		this.typeParcours = typeParcours;
	}

	public List<UniteEnseignement> getListeUnitesEnseignement() {
		return listeUnitesEnseignement;
	}

	public void setListeUnitesEnseignement(List<UniteEnseignement> listeUnitesEnseignement) {
		this.listeUnitesEnseignement = listeUnitesEnseignement;
	}

	public List<BlocCompetences> getListeBlocsCompetences() {
		return listeBlocsCompetences;
	}

	public void setListeBlocsCompetences(List<BlocCompetences> listeBlocsCompetences) {
		this.listeBlocsCompetences = listeBlocsCompetences;
	}

}
