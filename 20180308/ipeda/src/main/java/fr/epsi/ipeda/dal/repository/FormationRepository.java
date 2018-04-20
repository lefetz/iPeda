package fr.epsi.ipeda.dal.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.epsi.ipeda.dal.entity.Formation;
import fr.epsi.ipeda.dal.entity.Formation.TypeFormation;

@Repository
public interface FormationRepository extends CrudRepository<Formation, Long> {

	List<Formation> findByLibelle(String libelle);

	Formation findByTypeFormation(TypeFormation type);

}
