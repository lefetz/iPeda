package fr.epsi.ipeda.dal.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import fr.epsi.ipeda.dal.entity.Formation;
import fr.epsi.ipeda.dal.entity.Parcours;

public interface ParcoursRepository extends CrudRepository<Parcours, Long> {

	List<Parcours> findByFormation(Formation formation);

	Parcours findByFormationAndLibelleContaining(Formation formation, String libelle);

}
