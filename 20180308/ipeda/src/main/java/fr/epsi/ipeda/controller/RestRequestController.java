package fr.epsi.ipeda.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.epsi.ipeda.dal.dto.DatatablesWrapper;
import fr.epsi.ipeda.dal.dto.SalleDTO;
import fr.epsi.ipeda.dal.dto.modelmapper.ModelMapperManager;
import fr.epsi.ipeda.dal.entity.Salle;
import fr.epsi.ipeda.model.service.salle.SalleService;

@RestController
public class RestRequestController {

	@Autowired
	private SalleService salleService;

	@Autowired
	private ModelMapperManager modelMapperManager;

	@RequestMapping("/rest/salles")
	public DatatablesWrapper getAllSalles(Model model) {

		// init
		DatatablesWrapper wrapper = new DatatablesWrapper();
		ModelMapper modelMapper = modelMapperManager.getModelMapper();

		// récupère la liste des salles
		List<Salle> listeSalles = salleService.getAllSalles();
		
		// map la liste en liste de DTO
		java.lang.reflect.Type targetListType = new TypeToken<List<SalleDTO>>() {
		}.getType();
		List<SalleDTO> listeSallesDTO = modelMapper.map(listeSalles, targetListType);
		modelMapper.validate();

		// set les données dans le wrapper
		wrapper.setData(listeSallesDTO);
		wrapper.setRecordsTotal(listeSallesDTO.size());
		wrapper.setRecordsFiltered(listeSallesDTO.size());

		return wrapper;
	}

}
