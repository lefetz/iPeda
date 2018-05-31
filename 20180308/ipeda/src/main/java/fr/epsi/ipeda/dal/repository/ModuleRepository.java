package fr.epsi.ipeda.dal.repository;

import java.time.Duration;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.epsi.ipeda.dal.entity.Module;

@Repository
public interface ModuleRepository extends CrudRepository<Module, Long> {

	List<Module> findAll();

	Module findByCode(String code);

	List<Module> findByLibelle(String libelle);

	List<Module> findByDureeFFP(Duration duree);

	List<Module> findByDureeTE(Duration duree);

	List<Module> findByModuleParentCompose(Module moduleparentcompose);

	List<Module> findByModuleParentMutualise(Module moduleparentmutualise);

	Page<Module> findByLibelleContainingIgnoreCase(String libelle, Pageable pageable);

	Page<Module> findAll(Pageable pageable);

}
