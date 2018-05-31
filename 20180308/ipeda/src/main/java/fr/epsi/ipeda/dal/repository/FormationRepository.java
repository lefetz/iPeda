package fr.epsi.ipeda.dal.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.epsi.ipeda.dal.entity.AnneeScolaire;
import fr.epsi.ipeda.dal.entity.Formation;

@Repository
public interface FormationRepository extends CrudRepository<Formation, Long> {

	List<Formation> findAll();

	Formation findByLibelleContainingAndAnneeScolaire(String libelle, AnneeScolaire anneeScolaire);

	Page<Formation> findAll(Pageable pageable);


}
