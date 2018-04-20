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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "formation")
public class Formation {

	@Id
	@GeneratedValue
	private Long id;

	@Basic(optional = false)
	private String libelle;

	public enum TypeFormation {
		B1, B2, B3, I4, I5, UDEV
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "type_formation")
	private TypeFormation typeFormation;

	@OneToOne(mappedBy = "formation", cascade = CascadeType.ALL, orphanRemoval = true)
	private Semestre semestre1;

	@OneToOne(mappedBy = "formation", cascade = CascadeType.ALL, orphanRemoval = true)
	private Semestre semestre2;

	@OneToMany(mappedBy = "formation", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Parcours> listeParcours = new ArrayList<Parcours>();

	@ManyToMany(mappedBy = "listeFormations")
	private List<Module> listeModules = new ArrayList<Module>(); // cas UDEV

	public Formation() {
	}

	public Formation(TypeFormation typeFormation, String libelle, Semestre semestre1, Semestre semestre2) {
		this.typeFormation = typeFormation;
		this.libelle = libelle;

		semestre1.setFormation(this);
		this.semestre1 = semestre1;

		semestre2.setFormation(this);
		this.semestre2 = semestre2;
	}

	public Formation(TypeFormation typeFormation, String libelle, List<Module> listeModules) {
		this.typeFormation = typeFormation;
		this.libelle = libelle;
		addModule(listeModules);
	}

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

	public Semestre getSemestre1() {
		return semestre1;
	}

	public void addSemestre1(Semestre semestre) {
		semestre.setFormation(this);
		this.semestre1 = semestre;
	}

	public void removeSemestre1() {
		if (semestre1 != null) {
			semestre1.setFormation(null);
			this.semestre1 = null;
		}
	}

	public Semestre getSemestre2() {
		return semestre2;
	}

	public void addSemestre2(Semestre semestre) {
		semestre.setFormation(this);
		this.semestre2 = semestre;
	}

	public void removeSemestre2() {
		if (semestre2 != null) {
			semestre2.setFormation(null);
			this.semestre2 = null;
		}
	}

	public TypeFormation getTypeFormation() {
		return typeFormation;
	}

	public void setTypeFormation(TypeFormation typeFormation) {
		this.typeFormation = typeFormation;
	}

	public List<Parcours> getListeParcours() {
		return listeParcours;
	}

	public void setListeParcours(List<Parcours> listeParcours) {
		this.listeParcours = listeParcours;
	}

	public void addParcours(Parcours parcours) {
		if (null != parcours) {
			listeParcours.add(parcours);
		}
	}

	public void setSemestre1(Semestre semestre1) {
		this.semestre1 = semestre1;
	}

	public void setSemestre2(Semestre semestre2) {
		this.semestre2 = semestre2;
	}

	public void addModule(List<Module> listeModules) {
		if (null != listeModules) {
			for (Module module : listeModules) {
				listeModules.add(module);
				module.getListeFormations().add(this);
			}
		}
	}

}
