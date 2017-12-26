package fr.epsi.ipeda.dao.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import fr.epsi.ipeda.dao.entity.Cours;
import fr.epsi.ipeda.dao.entity.Intervenant;
import fr.epsi.ipeda.dao.entity.Module;

public interface CoursRepository extends CrudRepository<Cours, Long> {

	List<Cours> findByDateHeureDebut(LocalDateTime dateHeureDebut);

	List<Cours> findByIntervenant(Intervenant intervenant);

	List<Cours> findByModule(Module module);

}
