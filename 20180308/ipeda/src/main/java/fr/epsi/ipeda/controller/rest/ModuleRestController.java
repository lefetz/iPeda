package fr.epsi.ipeda.controller.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.epsi.ipeda.dal.dto.ModuleDTO;
import fr.epsi.ipeda.dal.dto.ajax.AjaxResponse;
import fr.epsi.ipeda.dal.dto.datatables.DatatablesRequestDTO;
import fr.epsi.ipeda.dal.dto.datatables.DatatablesResponseDTO;
import fr.epsi.ipeda.dal.dto.modelmapper.ModelMapperManager;
import fr.epsi.ipeda.dal.entity.Module;
import fr.epsi.ipeda.helpers.EmptyUtils;
import fr.epsi.ipeda.model.service.module.ModuleService;

@RestController
public class ModuleRestController {

	@Autowired
	private ModuleService moduleService;

	@Autowired
	private ModelMapperManager modelMapperManager;

	@RequestMapping("/rest/modules")
	public DatatablesResponseDTO getAllModules(HttpServletRequest request, HttpServletResponse response, Model model) {

		// init
		DatatablesRequestDTO datatablesRequestDTO = new DatatablesRequestDTO(request);
		DatatablesResponseDTO wrapper = new DatatablesResponseDTO(datatablesRequestDTO);
		ModelMapper modelMapper = modelMapperManager.getModelMapper();
		int pageLength = datatablesRequestDTO.getLength();
		int startIndex = (int) (Math.ceil((datatablesRequestDTO.getStart() + 1) / pageLength));
		Direction direction = datatablesRequestDTO.getOrder().getSortDir().equals("asc") ? Direction.ASC : Direction.DESC;

		// récupère la liste des modules
		List<Module> listeTotaleModules = moduleService.getAllModules();
		Page<Module> listeModules = null;
		PageRequest pageRequest = new PageRequest(startIndex, pageLength, direction, datatablesRequestDTO.getOrder().getData());
		if (!EmptyUtils.isObjectEmpty(datatablesRequestDTO.getSearch())) {
			listeModules = moduleService.getModulesByLibelle(datatablesRequestDTO.getSearch(), pageRequest);
			wrapper.setRecordsFiltered((int) listeModules.getTotalElements());
		} else {
			listeModules = moduleService.getAllModules(pageRequest);
			wrapper.setRecordsFiltered(listeTotaleModules.size());
		}

		// map la liste en liste de DTO
		java.lang.reflect.Type targetListType = new TypeToken<List<ModuleDTO>>() {
		}.getType();
		List<ModuleDTO> listeModulesDTO = modelMapper.map(listeModules.getContent(), targetListType);
		modelMapper.validate();

		// set les données dans le wrapper
		wrapper.setData(listeModulesDTO);
		wrapper.setRecordsTotal(listeTotaleModules.size());

		return wrapper;
	}

	@RequestMapping("/rest/modules/save")
	public AjaxResponse saveModule(@Valid @ModelAttribute Module module, BindingResult bindingResult) {

		// init
		AjaxResponse ajaxResponse = new AjaxResponse(AjaxResponse.STATUS.SUCCESS);

		// erreur ?
		if (bindingResult.hasErrors()) {
			ajaxResponse.setStatus(AjaxResponse.STATUS.ERROR);
			ajaxResponse.setListError(bindingResult);
		}

		// ok
		else {
			moduleService.save(module);
			ajaxResponse.setReturnUrl("/module/read.html");
		}

		// retourne l'objet de réponse ajax
		return ajaxResponse;
	}

	@RequestMapping("/rest/modules/delete")
	public AjaxResponse deleteModule(@ModelAttribute Module module, BindingResult bindingResult) {
		AjaxResponse ajaxResponse = new AjaxResponse(AjaxResponse.STATUS.SUCCESS);
		moduleService.delete(module);
		ajaxResponse.setReturnUrl("/salle/read.html");
		return ajaxResponse;
	}

}
