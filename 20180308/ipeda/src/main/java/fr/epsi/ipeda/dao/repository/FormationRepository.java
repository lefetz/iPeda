package fr.epsi.ipeda.dao.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.epsi.ipeda.dao.entity.Formation;
import fr.epsi.ipeda.dao.entity.Formation.TypeFormation;

@Repository
public interface FormationRepository extends CrudRepository<Formation, Long> {

	List<Formation> findByLibelle(String libelle);

	Formation findByTypeFormation(TypeFormation type);

}
