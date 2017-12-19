package fr.epsi.ipeda.dao.repository;

import org.springframework.data.repository.CrudRepository;

import fr.epsi.ipeda.dao.entity.BlocCompetences;
import fr.epsi.ipeda.dao.entity.UniteEnseignement;

public interface UniteEnseignementRepository extends CrudRepository<UniteEnseignement, Long> {

	UniteEnseignement findByBlocCompetences(BlocCompetences blocCompetences);

}
