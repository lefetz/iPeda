package fr.epsi.ipeda.dao.repository;

import java.time.Duration;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.epsi.ipeda.dao.entity.Module;
import fr.epsi.ipeda.dao.entity.Semestre;

@Repository
public interface ModuleRepository extends CrudRepository<Module, Long> {

	Module findByCode(String code);

	List<Module> findByLibelle(String libelle);

	List<Module> findByDureeFFP(Duration duree);

	List<Module> findByDureeTE(Duration duree);

	List<Module> findBySemestre(Semestre semestre);

	List<Module> findByModuleParentCompose(Module moduleparentcompose);

	List<Module> findByModuleParentMutualise(Module moduleparentmutualise);

}
