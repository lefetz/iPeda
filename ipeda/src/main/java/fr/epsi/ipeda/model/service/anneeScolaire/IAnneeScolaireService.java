package fr.epsi.ipeda.model.service.anneeScolaire;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import fr.epsi.ipeda.dal.entity.AnneeScolaire;

public interface IAnneeScolaireService {

	List<AnneeScolaire> findAll();

	Page<AnneeScolaire> findAll(PageRequest pageRequest);

	Page<AnneeScolaire> findByDateDebutOrDateFin(LocalDate dateDebut, LocalDate dateFin, PageRequest pageRequest);

	AnneeScolaire save(AnneeScolaire object);

	AnneeScolaire findById(String id);

	void delete(AnneeScolaire object);

	AnneeScolaire findByDateDebut(LocalDate dateDebut);

}
