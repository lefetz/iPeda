package fr.epsi.ipeda.model.service.formation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import fr.epsi.ipeda.dal.entity.Formation;
import fr.epsi.ipeda.dal.repository.FormationRepository;

@Service
public class FormationService implements IFormationService {

	@Autowired
	private FormationRepository formationRepository;

	@Override
	public List<Formation> findAll() {
		return formationRepository.findAll();
	}

	@Override
	public Page<Formation> findAll(PageRequest pageRequest) {
		return formationRepository.findAll(pageRequest);
	}

	@Override
	public Formation save(Formation object) {
		return formationRepository.save(object);
	}

	@Override
	public void delete(Formation object) {
		formationRepository.delete(object);
	}

}
