package fr.epsi.ipeda.model.service.module;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import fr.epsi.ipeda.dal.entity.Module;

public interface IModuleService {
	
	List<Module> getAllModules();

	Page<Module> getAllModules(PageRequest pageRequest);

	Module save(Module module);

}
