package fr.epsi.ipeda.dal.entity.activite;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import fr.epsi.ipeda.dal.entity.Module;

@Entity
@Table(name = "cours")
public class Cours extends Activite {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	private Module module;

	public Cours() {
	}

	public Cours(Module module) {
		this.module = module;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

}
