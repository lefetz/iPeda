package fr.epsi.ipeda.service.database.feeding;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.epsi.ipeda.dao.entity.BlocCompetences;
import fr.epsi.ipeda.dao.entity.Formation;
import fr.epsi.ipeda.dao.entity.Parcours;
import fr.epsi.ipeda.dao.entity.Semestre;
import fr.epsi.ipeda.dao.entity.Semestre.NumeroSemestre;
import fr.epsi.ipeda.dao.entity.UniteEnseignement;
import fr.epsi.ipeda.dao.repository.BlocCompetencesRepository;
import fr.epsi.ipeda.dao.repository.FormationRepository;
import fr.epsi.ipeda.dao.repository.IntervenantRepository;
import fr.epsi.ipeda.dao.repository.ParcoursRepository;
import fr.epsi.ipeda.dao.repository.UniteEnseignementRepository;
import fr.epsi.ipeda.service.businesslogic.formation.IFormationService;
import fr.epsi.ipeda.service.businesslogic.planning.IPlanningService;
import fr.epsi.ipeda.service.database.IDatabaseService;

@Component
public abstract class Feeding implements IFeeding {

	@Autowired
	protected FormationRepository formationRepository;

	@Autowired
	protected UniteEnseignementRepository uniteEnseignementRepository;

	@Autowired
	protected ParcoursRepository parcoursRepository;

	@Autowired
	protected BlocCompetencesRepository blocCompetencesRepository;

	@Autowired
	protected IntervenantRepository intervenantRepository;

	@Autowired
	protected IDatabaseService databaseService;

	@Autowired
	protected IPlanningService planningService;

	@Autowired
	protected IFormationService formationService;

	protected Map<NumeroSemestre, Semestre> mapSemestres = null;
	protected UniteEnseignement ue = null;
	protected Formation formation = null;
	protected Parcours parcours = null;
	protected BlocCompetences blocCompetences = null;

}
