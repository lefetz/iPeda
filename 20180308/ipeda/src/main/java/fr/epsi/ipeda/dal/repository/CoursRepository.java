package fr.epsi.ipeda.dal.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import fr.epsi.ipeda.dal.entity.Module;
import fr.epsi.ipeda.dal.entity.activite.Cours;

public interface CoursRepository extends CrudRepository<Cours, Long> {

	List<Cours> findByModule(Module module);

}
