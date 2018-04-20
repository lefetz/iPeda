package fr.epsi.ipeda.dal.entity;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bloc_competences")
public class BlocCompetences {

	@Id
	@GeneratedValue
	private Long id;

	@Basic(optional = false)
	private int numero;

	@Basic(optional = false)
	private String libelle;

	@ManyToOne
	@Basic(optional = false)
	private Parcours parcours;

	@OneToMany(mappedBy = "parcours", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<UniteEnseignement> listeUnitesEnseignement = new ArrayList<UniteEnseignement>();

	@OneToOne(mappedBy = "blocCompetences", cascade = CascadeType.ALL, orphanRemoval = true)
	private ProjetTransversal projetTransversal;

	@Column(name = "ferme")
	private boolean isFerme;

	public enum TypeBloc {
		DEV, RESEAUX, ERP
	}

	@Column(name = "type_bloc")
	@Enumerated(EnumType.STRING)
	private TypeBloc TypeBloc;

	public BlocCompetences() {

	}

	public BlocCompetences(Parcours parcours, int numero, String libelle) {
		this.parcours = parcours;
		this.numero = numero;
		this.libelle = libelle;
	}

	public BlocCompetences(TypeBloc typeBloc, Parcours parcours, int numero, String libelle) {
		this.TypeBloc = typeBloc;
		this.parcours = parcours;
		this.numero = numero;
		this.libelle = libelle;
	}

	public BlocCompetences(TypeBloc typeBloc, Parcours parcours, int numero, String libelle, boolean isFerme) {
		this.TypeBloc = typeBloc;
		this.parcours = parcours;
		this.numero = numero;
		this.libelle = libelle;
		this.isFerme = isFerme;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public Parcours getParcours() {
		return parcours;
	}

	public void setParcours(Parcours parcours) {
		this.parcours = parcours;
	}

	public List<UniteEnseignement> getListeUnitesEnseignement() {
		return listeUnitesEnseignement;
	}

	public void setListeUnitesEnseignement(List<UniteEnseignement> listeUnitesEnseignement) {
		this.listeUnitesEnseignement = listeUnitesEnseignement;
	}

	public ProjetTransversal getProjetTransversal() {
		return projetTransversal;
	}

	public void setProjetTransversal(ProjetTransversal projetTransversal) {
		this.projetTransversal = projetTransversal;
	}

	public TypeBloc getTypeBloc() {
		return TypeBloc;
	}

	public void setTypeBloc(TypeBloc typeBloc) {
		TypeBloc = typeBloc;
	}

	public boolean isFerme() {
		return isFerme;
	}

	public void setFerme(boolean isFerme) {
		this.isFerme = isFerme;
	}

}
