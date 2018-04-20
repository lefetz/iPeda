package fr.epsi.ipeda.dal.repository;

import org.springframework.data.repository.CrudRepository;

import fr.epsi.ipeda.dal.entity.BlocCompetences;
import fr.epsi.ipeda.dal.entity.UniteEnseignement;

public interface UniteEnseignementRepository extends CrudRepository<UniteEnseignement, Long> {

	UniteEnseignement findByBlocCompetences(BlocCompetences blocCompetences);

}
