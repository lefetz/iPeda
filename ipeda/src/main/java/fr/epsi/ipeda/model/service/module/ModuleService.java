package fr.epsi.ipeda.model.service.module;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import fr.epsi.ipeda.dal.entity.Module;
import fr.epsi.ipeda.dal.repository.ModuleRepository;

@Service
public class ModuleService implements IModuleService {

	@Autowired
	private ModuleRepository moduleRepository;

	@Override
	public List<Module> getAllModules() {
		return moduleRepository.findAll();
	}

	public Page<Module> getModulesByLibelle(String libelle, PageRequest pageRequest) {
		return moduleRepository.findByLibelleContainingIgnoreCase(libelle, pageRequest);
	}

	@Override
	public Page<Module> getAllModules(PageRequest pageRequest) {
		return moduleRepository.findAll(pageRequest);
	}

	@Override
	public Module save(Module module) {
		return moduleRepository.save(module);
	}

	public void delete(Module module) {
		moduleRepository.delete(module);
	}

}
