package fr.epsi.ipeda.dal.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "intervenant")
public class Intervenant {

	@Id
	@GeneratedValue
	private Long id;

	@Basic(optional = false)
	private String nom;

	@Basic(optional = false)
	private String prenom;

	@OneToMany(mappedBy = "intervenant", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Module> listeModules = new ArrayList<Module>();

	public Intervenant() {
	}

	public Intervenant(String nom, String prenom) {
		this.nom = nom;
		this.prenom = prenom;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public List<Module> getListeModules() {
		return listeModules;
	}

	public void setListeModules(List<Module> listeModules) {
		this.listeModules = listeModules;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Intervenant)) {
			return false;
		}
		return this.id == ((Intervenant) obj).id;
	}

}
