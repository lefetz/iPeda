package fr.epsi.ipeda.dal.service.feeding;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.epsi.ipeda.dal.entity.BlocCompetences;
import fr.epsi.ipeda.dal.entity.Formation;
import fr.epsi.ipeda.dal.entity.Parcours;
import fr.epsi.ipeda.dal.entity.Semestre;
import fr.epsi.ipeda.dal.entity.UniteEnseignement;
import fr.epsi.ipeda.dal.entity.Semestre.NumeroSemestre;
import fr.epsi.ipeda.dal.repository.BlocCompetencesRepository;
import fr.epsi.ipeda.dal.repository.FormationRepository;
import fr.epsi.ipeda.dal.repository.IntervenantRepository;
import fr.epsi.ipeda.dal.repository.ParcoursRepository;
import fr.epsi.ipeda.dal.repository.UniteEnseignementRepository;
import fr.epsi.ipeda.dal.service.IDatabaseService;
import fr.epsi.ipeda.model.service.formation.IFormationService;
import fr.epsi.ipeda.model.service.planning.IPlanningService;

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
