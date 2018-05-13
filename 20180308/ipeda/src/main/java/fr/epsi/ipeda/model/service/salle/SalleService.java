package fr.epsi.ipeda.model.service.salle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import fr.epsi.ipeda.dal.entity.Salle;
import fr.epsi.ipeda.dal.repository.SalleRepository;

@Service
public class SalleService implements ISalleService {

	@Autowired
	private SalleRepository salleRepository;

	@Override
	public List<Salle> getAllSalles() {
		return salleRepository.findAll();
	}

	@Override
	public Page<Salle> getAllSalles(PageRequest pageRequest) {
		return salleRepository.findAll(pageRequest);
	}

	@Override
	public Page<Salle> getSallesByLibelle(String libelle, PageRequest pageRequest) {
		return salleRepository.findByLibelleContainingIgnoreCase(libelle, pageRequest);
	}

	@Override
	public Salle save(Salle salle) {
		return salleRepository.save(salle);
	}

}
