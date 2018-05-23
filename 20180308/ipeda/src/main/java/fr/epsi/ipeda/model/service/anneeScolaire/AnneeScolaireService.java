package fr.epsi.ipeda.model.service.anneeScolaire;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import fr.epsi.ipeda.dal.entity.AnneeScolaire;
import fr.epsi.ipeda.dal.repository.AnneeScolaireRepository;

@Service
public class AnneeScolaireService implements IAnneeScolaireService {

	@Autowired
	private AnneeScolaireRepository anneeScolaireRepository;

	@Override
	public List<AnneeScolaire> findAll() {
		return anneeScolaireRepository.findAll();
	}

	@Override
	public Page<AnneeScolaire> findAll(PageRequest pageRequest) {
		return anneeScolaireRepository.findAll(pageRequest);
	}

	@Override
	public Page<AnneeScolaire> findByDateDebutOrDateFin(LocalDate dateDebut, LocalDate dateFin, PageRequest pageRequest) {
		return anneeScolaireRepository.findByDateDebutOrDateFin(dateDebut, dateFin, pageRequest);
	}

	@Override
	public AnneeScolaire save(AnneeScolaire object) {
		return anneeScolaireRepository.save(object);
	}

	@Override
	public AnneeScolaire findById(String id) {
		return anneeScolaireRepository.findById(Long.valueOf(id));
	}

	@Override
	public void delete(AnneeScolaire object) {
		anneeScolaireRepository.delete(object);
	}

}
