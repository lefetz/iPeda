package fr.epsi.ipeda.model.service.planning;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import fr.epsi.ipeda.dal.dto.SeanceDTO;
import fr.epsi.ipeda.dal.entity.AnneeScolaire;
import fr.epsi.ipeda.dal.entity.Formation;
import fr.epsi.ipeda.dal.entity.Planning;
import fr.epsi.ipeda.dal.entity.Seance;
import fr.epsi.ipeda.helpers.TimeUtils;
import fr.epsi.ipeda.helpers.TimeUtils.TIMEFIELD;
import fr.epsi.ipeda.model.service.anneeScolaire.AnneeScolaireService;
import fr.epsi.ipeda.model.service.formation.FormationService;

@Service
public class PlanningService implements IPlanningService {

	@Autowired
	private Environment env;

	@Autowired
	private AnneeScolaireService anneeScolaireService;

	@Autowired
	private FormationService formationService;

	public LocalTime getHeureFromProperties(String propname) {
		String[] parts = env.getProperty(propname).split(env.getProperty("timeSeparator"));
		return LocalTime.of(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
	}

	public List<SeanceDTO> getListeSeances() {
		SeanceDTO seanceDto = null;
		List<SeanceDTO> listeSeances = new ArrayList<SeanceDTO>();

		// récupère le planning des B1 (par exemple)
		AnneeScolaire anneeScolaire = anneeScolaireService.findByDateDebut(LocalDate.of(2018, 9, 1));
		Formation formation = formationService.findByLibelleContainingAndAnneeScolaire("BACHELOR 1", anneeScolaire);
		Planning planning = formation.getPlanning();

		// pour chaque séance du planning de la formation
		for (Seance seance : planning.getListSeance()) {

			// nouveau DTO
			seanceDto = new SeanceDTO();

			// groupe
			seanceDto.setGroupe(formation.getLibelleCourt());

			// calcul de la semaine
			EnumMap<TIMEFIELD, Integer> semaine = TimeUtils.getNextWeek(seance.getDate(), 0);
			seanceDto.setSemaine(semaine.get(TIMEFIELD.YEAR).toString() + "-" + semaine.get(TIMEFIELD.WEEK).toString());

			// date
			seanceDto.setDate(seance.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

			// jour
			seanceDto.setJour(seance.getDate().format(DateTimeFormatter.ofPattern("EEE")));

			// heure début
			seanceDto.setHeureDebut(seance.getHeureDebut().format(DateTimeFormatter.ofPattern("HH:mm")));

			// heure fin
			seanceDto.setHeureFin(seance.getHeureFin().format(DateTimeFormatter.ofPattern("HH:mm")));

			// duree séance
			Duration duree = Duration.between(seance.getHeureDebut(), seance.getHeureFin());
			seanceDto.setDureeSeance(String.format("%02d:%02d", duree.getSeconds() / 3600, (duree.getSeconds() % 3600) / 60));
			
			// période

			// s'agit-il d'un jour de week-end ?
			seanceDto.setWeekEnd(seance.getDate().getDayOfWeek() == DayOfWeek.SATURDAY || seance.getDate().getDayOfWeek() == DayOfWeek.SUNDAY);

			// s'agit-il d'un jour pair ?
			seanceDto.setJourPair(seance.getDate().getDayOfWeek().getValue() % 2 == 0);

			// ajoute le DTO à la liste
			listeSeances.add(seanceDto);

		}

		return listeSeances;
	}

}
