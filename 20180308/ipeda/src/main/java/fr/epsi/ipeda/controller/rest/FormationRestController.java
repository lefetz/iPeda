package fr.epsi.ipeda.controller.rest;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.epsi.ipeda.dal.dto.FormationDTO;
import fr.epsi.ipeda.dal.dto.ajax.AjaxResponse;
import fr.epsi.ipeda.dal.dto.datatables.DatatablesRequestDTO;
import fr.epsi.ipeda.dal.dto.datatables.DatatablesResponseDTO;
import fr.epsi.ipeda.dal.entity.Formation;
import fr.epsi.ipeda.dal.entity.Planning;
import fr.epsi.ipeda.helpers.TimeUtils;
import fr.epsi.ipeda.model.service.formation.FormationService;
import fr.epsi.ipeda.model.service.planning.PlanningService;

@RestController
public class FormationRestController {

	@Autowired
	private FormationService formationService;

	@Autowired
	private PlanningService planningService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), true, 10));
	}

	@RequestMapping("/rest/formations")
	public DatatablesResponseDTO getAllFormations(HttpServletRequest request, HttpServletResponse response, Model model) {

		// init
		DatatablesRequestDTO datatablesRequestDTO = new DatatablesRequestDTO(request);
		DatatablesResponseDTO wrapper = new DatatablesResponseDTO(datatablesRequestDTO);
		int tailleTotale = formationService.findAll().size();

		// initialisation de la pagination
		Page<Formation> listeObjets = null;
		int pageLength = datatablesRequestDTO.getLength();
		int startIndex = (int) (Math.ceil((datatablesRequestDTO.getStart() + 1) / pageLength));
		Direction direction = datatablesRequestDTO.getOrder().getSortDir().equals("asc") ? Direction.ASC : Direction.DESC;
		PageRequest pageRequest = new PageRequest(startIndex, pageLength, direction, datatablesRequestDTO.getOrder().getData());

		// récupération des objets sans filtre
		listeObjets = formationService.findAll(pageRequest);
		wrapper.setRecordsFiltered(tailleTotale);

		// map la liste en liste de DTO
		List<FormationDTO> listDto = new ArrayList<FormationDTO>();
		for (Formation o : listeObjets) {
			FormationDTO dto = new FormationDTO();
			dto.setAnneeScolaireId(Long.toString(o.getAnneeScolaire().getId()));
			dto.setAnneeScolaireDateDebut(o.getAnneeScolaire().getDateDebut().format(DateTimeFormatter.ofPattern(TimeUtils.getDateFormatToView())));
			dto.setAnneeScolaireDateFin(o.getAnneeScolaire().getDateFin().format(DateTimeFormatter.ofPattern(TimeUtils.getDateFormatToView())));
			dto.setDateDebut(o.getDateDebut().format(DateTimeFormatter.ofPattern(TimeUtils.getDateFormatToView())));
			dto.setDateFin(o.getDateFin().format(DateTimeFormatter.ofPattern(TimeUtils.getDateFormatToView())));
			if (null != o.getDateFinSemestre1()) {
				dto.setDateFinSemestre1(o.getDateFinSemestre1().format(DateTimeFormatter.ofPattern(TimeUtils.getDateFormatToView())));
			}
			dto.setId(Long.toString(o.getId()));
			dto.setLibelle(o.getLibelle());
			dto.setLibelleCourt(o.getLibelleCourt());

			listDto.add(dto);
		}

		// set les données dans le wrapper
		wrapper.setData(listDto);
		wrapper.setRecordsTotal(tailleTotale);

		return wrapper;
	}

	@RequestMapping("/rest/formations/save")
	public AjaxResponse saveFormations(@Valid @ModelAttribute Formation o, BindingResult bindingResult) {

		// init
		AjaxResponse ajaxResponse = new AjaxResponse(AjaxResponse.STATUS.SUCCESS);

		// erreur ?
		if (bindingResult.hasErrors()) {
			ajaxResponse.setStatus(AjaxResponse.STATUS.ERROR);
			ajaxResponse.setListError(bindingResult);
		}

		// ok
		else {
			Planning planning = planningService.findByFormation(o);
			if (null != planning) {
				planning.setFormation(o);
				o.setPlanning(planning);
			}
			formationService.save(o);
			ajaxResponse.setReturnUrl("/formation/read.html");
		}

		// retourne l'objet de réponse ajax
		return ajaxResponse;
	}

	@RequestMapping("/rest/formations/delete")
	public AjaxResponse deleteFormation(@ModelAttribute Formation objet, BindingResult bindingResult) {
		AjaxResponse ajaxResponse = new AjaxResponse(AjaxResponse.STATUS.SUCCESS);
		formationService.delete(objet);
		ajaxResponse.setReturnUrl("/formation/read.html");
		return ajaxResponse;
	}

}
