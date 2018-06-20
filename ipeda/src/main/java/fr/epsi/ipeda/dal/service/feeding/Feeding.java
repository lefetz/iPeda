package fr.epsi.ipeda.dal.service.feeding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import fr.epsi.ipeda.dal.entity.BlocCompetences;
import fr.epsi.ipeda.dal.entity.Formation;
import fr.epsi.ipeda.dal.entity.Parcours;
import fr.epsi.ipeda.dal.entity.UniteEnseignement;
import fr.epsi.ipeda.dal.repository.AnneeScolaireRepository;
import fr.epsi.ipeda.dal.repository.BlocCompetencesRepository;
import fr.epsi.ipeda.dal.repository.FormationRepository;
import fr.epsi.ipeda.dal.repository.IntervenantRepository;
import fr.epsi.ipeda.dal.repository.ParcoursRepository;
import fr.epsi.ipeda.dal.repository.PlanningRepository;
import fr.epsi.ipeda.dal.repository.SeanceRepository;
import fr.epsi.ipeda.dal.repository.SpecificiteRepository;
import fr.epsi.ipeda.dal.repository.UniteEnseignementRepository;
import fr.epsi.ipeda.dal.repository.periode.PeriodeRepository;
import fr.epsi.ipeda.dal.repository.periode.PeriodeTypeRepository;
import fr.epsi.ipeda.dal.service.IDatabaseService;
import fr.epsi.ipeda.model.service.anneeScolaire.IAnneeScolaireService;
import fr.epsi.ipeda.model.service.formation.IFormationService;
import fr.epsi.ipeda.model.service.planning.IPlanningService;

@Component
public abstract class Feeding implements IFeeding {

	@Autowired
	protected Environment env;

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
	protected SpecificiteRepository specificiteRepository;

	@Autowired
	protected AnneeScolaireRepository anneeScolaireRepository;

	@Autowired
	protected PlanningRepository planningRepository;

	@Autowired
	protected PeriodeRepository periodeRepository;

	@Autowired
	protected PeriodeTypeRepository periodeTypeRepository;

	@Autowired
	protected SeanceRepository seanceRepository;

	@Autowired
	protected IDatabaseService databaseService;

	@Autowired
	protected IPlanningService planningService;

	@Autowired
	protected IFormationService formationService;

	@Autowired
	protected IAnneeScolaireService anneeScolaireService;

	// protected Map<NumeroSemestre, Semestre> mapSemestres = null;
	protected UniteEnseignement ue = null;
	protected Formation formation = null;
	protected Parcours parcours = null;
	protected BlocCompetences blocCompetences = null;

}
