package fr.epsi.ipeda.dal.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.epsi.ipeda.dal.entity.AnneeScolaire;

@Repository
public interface AnneeScolaireRepository extends CrudRepository<AnneeScolaire, Long> {

	List<AnneeScolaire> findAll();

	AnneeScolaire findById(Long id);

	AnneeScolaire findByDateDebut(LocalDate dateDebut);

	Page<AnneeScolaire> findAll(Pageable pageable);

	Page<AnneeScolaire> findByDateDebutOrDateFin(LocalDate dateDebut, LocalDate dateFin, Pageable pageable);

}
