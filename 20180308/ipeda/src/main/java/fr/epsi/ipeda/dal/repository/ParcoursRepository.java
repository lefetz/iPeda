package fr.epsi.ipeda.dal.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import fr.epsi.ipeda.dal.entity.Formation;
import fr.epsi.ipeda.dal.entity.Parcours;
import fr.epsi.ipeda.dal.entity.Parcours.TypeParcours;

public interface ParcoursRepository extends CrudRepository<Parcours, Long> {

	List<Parcours> findByFormation(Formation formation);

	Parcours findByFormationAndTypeParcours(Formation formation, TypeParcours typeParcours);

}
