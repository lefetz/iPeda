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

import fr.epsi.ipeda.dal.dto.AnneeScolaireDTO;
import fr.epsi.ipeda.dal.dto.ajax.AjaxResponse;
import fr.epsi.ipeda.dal.dto.datatables.DatatablesRequestDTO;
import fr.epsi.ipeda.dal.dto.datatables.DatatablesResponseDTO;
import fr.epsi.ipeda.dal.dto.modelmapper.ModelMapperManager;
import fr.epsi.ipeda.dal.entity.AnneeScolaire;
import fr.epsi.ipeda.model.service.anneeScolaire.AnneeScolaireService;

@RestController
public class AnneeScolaireRestController {

	@Autowired
	private AnneeScolaireService anneeScolaireService;

	@Autowired
	private ModelMapperManager modelMapperManager;

	@RequestMapping("/rest/anneesScolaires")
	public DatatablesResponseDTO getAllAnneesScolaires(HttpServletRequest request, HttpServletResponse response, Model model) {

		// init
		DatatablesRequestDTO datatablesRequestDTO = new DatatablesRequestDTO(request);
		DatatablesResponseDTO wrapper = new DatatablesResponseDTO(datatablesRequestDTO);
		ModelMapper modelMapper = modelMapperManager.getModelMapper();
		int tailleTotale = anneeScolaireService.findAll().size();

		// initialisation de la pagination
		Page<AnneeScolaire> listeObjets = null;
		int pageLength = datatablesRequestDTO.getLength();
		int startIndex = (int) (Math.ceil((datatablesRequestDTO.getStart() + 1) / pageLength));
		Direction direction = datatablesRequestDTO.getOrder().getSortDir().equals("asc") ? Direction.ASC : Direction.DESC;
		PageRequest pageRequest = new PageRequest(startIndex, pageLength, direction, datatablesRequestDTO.getOrder().getData());

		// récupération des objets sans filtre
		listeObjets = anneeScolaireService.findAll(pageRequest);
		wrapper.setRecordsFiltered(tailleTotale);

		// map la liste en liste de DTO
		java.lang.reflect.Type targetListType = new TypeToken<List<AnneeScolaireDTO>>() {
		}.getType();
		List<AnneeScolaireDTO> listeSallesDTO = modelMapper.map(listeObjets.getContent(), targetListType);
		modelMapper.validate();

		// set les données dans le wrapper
		wrapper.setData(listeSallesDTO);
		wrapper.setRecordsTotal(tailleTotale);

		return wrapper;
	}

	@RequestMapping("/rest/anneesScolaires/save")
	public AjaxResponse saveAnneesScolaires(@Valid @ModelAttribute AnneeScolaire objet, BindingResult bindingResult) {

		// init
		AjaxResponse ajaxResponse = new AjaxResponse(AjaxResponse.STATUS.SUCCESS);

		// erreur ?
		if (bindingResult.hasErrors()) {
			ajaxResponse.setStatus(AjaxResponse.STATUS.ERROR);
			ajaxResponse.setListError(bindingResult);
		}

		// ok
		else {
			anneeScolaireService.save(objet);
			ajaxResponse.setReturnUrl("/anneeScolaire/read.html");
		}

		// retourne l'objet de réponse ajax
		return ajaxResponse;
	}

	@RequestMapping("/rest/anneesScolaires/delete")
	public AjaxResponse deleteAnneeScolaire(@ModelAttribute AnneeScolaire objet, BindingResult bindingResult) {
		AjaxResponse ajaxResponse = new AjaxResponse(AjaxResponse.STATUS.SUCCESS);
		anneeScolaireService.delete(objet);
		ajaxResponse.setReturnUrl("/anneeScolaire/read.html");
		return ajaxResponse;
	}

}
