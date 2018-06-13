package fr.epsi.ipeda.dal.repository;

import org.springframework.data.repository.CrudRepository;

import fr.epsi.ipeda.dal.entity.Formation;
import fr.epsi.ipeda.dal.entity.Planning;

public interface PlanningRepository extends CrudRepository<Planning, Long> {

	Planning findByFormation(Formation formation);

}
