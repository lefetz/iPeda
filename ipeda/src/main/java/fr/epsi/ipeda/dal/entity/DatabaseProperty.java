package fr.epsi.ipeda.dal.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "database_properties")
public class DatabaseProperty extends AbstractPersistable<Long> {

	private static final long serialVersionUID = 2154438526007389639L;

	private String name;
	private String value;

	public DatabaseProperty() {
	}

	public DatabaseProperty(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setValue(String value) {
		this.value = value;
	}
}