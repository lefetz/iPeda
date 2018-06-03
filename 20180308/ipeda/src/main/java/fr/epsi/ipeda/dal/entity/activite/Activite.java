package fr.epsi.ipeda.dal.entity.activite;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "activite")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Activite {

	@Id
	@GeneratedValue
	private Long id;

}
