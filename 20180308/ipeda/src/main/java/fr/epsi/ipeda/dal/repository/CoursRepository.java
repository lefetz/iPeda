package fr.epsi.ipeda.dal.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import fr.epsi.ipeda.dal.entity.Cours;
import fr.epsi.ipeda.dal.entity.Module;

public interface CoursRepository extends CrudRepository<Cours, Long> {

	List<Cours> findByDateHeureDebut(LocalDateTime dateHeureDebut);

	List<Cours> findByModule(Module module);

}
