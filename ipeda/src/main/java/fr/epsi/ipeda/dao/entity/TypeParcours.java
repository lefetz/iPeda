package model;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "type_parcours")
public class TypeParcours {

	@Id
	@GeneratedValue
	private Long id;

	@Basic(optional = false)
	private String libelle;

	@OneToMany(mappedBy = "typeParcours", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Specialite> listeSpecialites;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public List<Specialite> getListeSpecialites() {
		return listeSpecialites;
	}

	public void setListeSpecialites(List<Specialite> listeSpecialites) {
		this.listeSpecialites = listeSpecialites;
	}

}
