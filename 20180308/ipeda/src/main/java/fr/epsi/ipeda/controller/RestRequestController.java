package fr.epsi.ipeda.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.epsi.ipeda.dal.dto.SalleDTO;
import fr.epsi.ipeda.dal.dto.datatables.DatatablesRequestDTO;
import fr.epsi.ipeda.dal.dto.datatables.DatatablesResponseDTO;
import fr.epsi.ipeda.dal.dto.modelmapper.ModelMapperManager;
import fr.epsi.ipeda.dal.entity.Salle;
import fr.epsi.ipeda.helpers.EmptyUtils;
import fr.epsi.ipeda.model.service.salle.SalleService;

@RestController
public class RestRequestController {

	@Autowired
	private SalleService salleService;

	@Autowired
	private ModelMapperManager modelMapperManager;

	@RequestMapping("/rest/salles")
	public DatatablesResponseDTO getAllSalles(HttpServletRequest request, HttpServletResponse response, Model model) {

		// init
		DatatablesRequestDTO datatablesRequestDTO = new DatatablesRequestDTO(request);
		DatatablesResponseDTO wrapper = new DatatablesResponseDTO(datatablesRequestDTO);
		ModelMapper modelMapper = modelMapperManager.getModelMapper();
		int pageLength = datatablesRequestDTO.getLength();
		int startIndex = (int) (Math.ceil((datatablesRequestDTO.getStart() + 1) / pageLength));
		Direction direction = datatablesRequestDTO.getOrder().getSortDir().equals("asc") ? Direction.ASC : Direction.DESC;

		// récupère la liste des salles
		List<Salle> listeTotaleSalles = salleService.getAllSalles();
		Page<Salle> listeSalles = null;
		PageRequest pageRequest = new PageRequest(startIndex, pageLength, direction, datatablesRequestDTO.getOrder().getData());
		if (!EmptyUtils.isObjectEmpty(datatablesRequestDTO.getSearch())) {
			listeSalles = salleService.getSallesByLibelle(datatablesRequestDTO.getSearch(), pageRequest);
			wrapper.setRecordsFiltered((int) listeSalles.getTotalElements());
		} else {
			listeSalles = salleService.getAllSalles(pageRequest);
			wrapper.setRecordsFiltered(listeTotaleSalles.size());
		}

		// map la liste en liste de DTO
		java.lang.reflect.Type targetListType = new TypeToken<List<SalleDTO>>() {
		}.getType();
		List<SalleDTO> listeSallesDTO = modelMapper.map(listeSalles.getContent(), targetListType);
		modelMapper.validate();

		// set les données dans le wrapper
		wrapper.setData(listeSallesDTO);
		wrapper.setRecordsTotal(listeTotaleSalles.size());

		return wrapper;
	}

	@RequestMapping("/rest/salles/add")
	public String addSalle() {
		return "fragments/modal/normal :: salle";
	}

}
