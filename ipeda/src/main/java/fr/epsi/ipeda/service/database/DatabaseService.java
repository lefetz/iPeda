package fr.epsi.ipeda.service.database;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.epsi.ipeda.dao.entity.BlocCompetences;
import fr.epsi.ipeda.dao.entity.Formation;
import fr.epsi.ipeda.dao.entity.Formation.TypeFormation;
import fr.epsi.ipeda.dao.entity.Intervenant;
import fr.epsi.ipeda.dao.entity.Module;
import fr.epsi.ipeda.dao.entity.Parcours;
import fr.epsi.ipeda.dao.entity.ProjetTransversal;
import fr.epsi.ipeda.dao.entity.Salle;
import fr.epsi.ipeda.dao.entity.Semestre;
import fr.epsi.ipeda.dao.entity.Semestre.NumeroSemestre;
import fr.epsi.ipeda.dao.entity.UniteEnseignement;
import fr.epsi.ipeda.dao.repository.BlocCompetencesRepository;
import fr.epsi.ipeda.dao.repository.FormationRepository;
import fr.epsi.ipeda.dao.repository.IntervenantRepository;
import fr.epsi.ipeda.dao.repository.ModuleRepository;
import fr.epsi.ipeda.dao.repository.ParcoursRepository;
import fr.epsi.ipeda.dao.repository.ProjetTransversalRepository;
import fr.epsi.ipeda.dao.repository.SalleRepository;
import fr.epsi.ipeda.dao.repository.SemestreRepository;
import fr.epsi.ipeda.dao.repository.UniteEnseignementRepository;
import fr.epsi.ipeda.service.businesslogic.INomenclatureService;

@Service
public class DatabaseService implements IDatabaseService {

	@Autowired
	private SemestreRepository semestreRepository;

	@Autowired
	private FormationRepository formationRepository;

	@Autowired
	private SalleRepository salleRepository;

	@Autowired
	private IntervenantRepository intervenantRepository;

	@Autowired
	private ModuleRepository moduleRepository;

	@Autowired
	private UniteEnseignementRepository uniteEnseignementRepository;

	@Autowired
	private ParcoursRepository parcoursRepository;

	@Autowired
	private BlocCompetencesRepository blocCompetencesRepository;

	@Autowired
	private ProjetTransversalRepository projetTransversalRepository;

	@Autowired
	private INomenclatureService nomenclatureService;

	@SuppressWarnings("serial")
	@Override
	public void initialiserFormations() {

		Map<NumeroSemestre, Semestre> mapSemestres = null;
		// List<Specialite> listeSpecialites = null;
		List<Module> listeModules = new ArrayList<Module>();
		UniteEnseignement ue = null;
		Formation formation = null;
		Parcours parcours = null;
		BlocCompetences blocCompetences = null;
		// ProjetTransversal projetTransversal = null;
		// String codeModule = "";
		// Semestre semestre = null;
		// Intervenant intervenant = null;
		// String libelle = "";

		// ------------------------------------------------
		// FORMATION B1
		// ------------------------------------------------

		// semestres
		// semestre1 = new Semestre(NumeroSemestre.SEMESTRE1, LocalDate.of(2017, 9, 22), LocalDate.of(2017, 12, 31));
		// semestre2 = new Semestre(NumeroSemestre.SEMESTRE2, LocalDate.of(2018, 1, 1), LocalDate.of(2018, 6, 22));

		// spécialités
		// listeSpecialites = new ArrayList<Specialite>();
		// listeSpecialites.add(new Specialite(TypeParcours.SOCLE, "SOCLE"));
		// listeSpecialites.add(new Specialite(TypeParcours.PROFESSIONNEL, "PROFESSIONNEL"));
		// listeSpecialites.add(new Specialite(TypeParcours.BTS_SIO, "BTS SIO"));

		// formation
		// formationRepository.save(new Formation(TypeFormation.B1, "BACHELOR 1", semestre1, semestre2, listeSpecialites));

		// ------------------------------------------------
		// FORMATION B2
		// ------------------------------------------------

		// semestres
		// semestre1 = new Semestre(NumeroSemestre.SEMESTRE1, LocalDate.of(2017, 9, 8), LocalDate.of(2017, 12, 31));
		// semestre2 = new Semestre(NumeroSemestre.SEMESTRE2, LocalDate.of(2018, 1, 1), LocalDate.of(2018, 6, 30));

		// spécialités
		// listeSpecialites = new ArrayList<Specialite>();
		// listeSpecialites.add(new Specialite(TypeParcours.SOCLE, "SOCLE"));
		// listeSpecialites.add(new Specialite(TypeParcours.PROFESSIONNEL, "PROFESSIONNEL"));
		// listeSpecialites.add(new Specialite(TypeParcours.BTS_SIO, "BTS SIO"));

		// formation
		// formationRepository.save(new Formation(TypeFormation.B2, "BACHELOR 2", semestre1, semestre2, listeSpecialites));

		// ================================================================================================
		//
		//
		// FORMATION B3
		//
		//
		// ================================================================================================

		// semestres
		mapSemestres = new HashMap<NumeroSemestre, Semestre>() {
			{
				put(NumeroSemestre.SEMESTRE1, new Semestre(NumeroSemestre.SEMESTRE1, LocalDate.of(2017, 9, 28), LocalDate.of(2017, 12, 31)));
				put(NumeroSemestre.SEMESTRE2, new Semestre(NumeroSemestre.SEMESTRE2, LocalDate.of(2018, 1, 1), LocalDate.of(2018, 7, 20)));
			}
		};

		// formation
		formation = formationRepository.save(new Formation(TypeFormation.B3, "BACHELOR 3", mapSemestres.get(NumeroSemestre.SEMESTRE1), mapSemestres.get(NumeroSemestre.SEMESTRE2)));

		// ================================================
		//
		// PARCOURS SOCLE
		//
		// ================================================
		parcours = parcoursRepository.save(new Parcours(Parcours.TypeParcours.SOCLE, false, formation));

		// ------------------------------------------------
		// UE learning
		// ------------------------------------------------
		ue = uniteEnseignementRepository.save(new UniteEnseignement(null, "Learning by doing", parcours));
		saveModule("TPTE500", "Workshop 1 - Hackaton / Développement d'une application objet", 30, 0, mapSemestres, intervenantRepository.findByNom("lefetz"), ue);
		saveModule("TPTE600", "Workshop 2 - Créativité & Innovation / TagCloud : Sécurité - Professionnalisme - Domotique", 30, 0, mapSemestres, intervenantRepository.findByNom("lefetz"), ue);

		// ------------------------------------------------
		// bloc compétences 1
		// ------------------------------------------------
		blocCompetences = blocCompetencesRepository.save(new BlocCompetences(parcours, 1, "Développement d'applications informatiques"));

		// projet transversal
		saveProjetTransversal("TPTE511", "Projet transversal Développement d'une application en langage Java", 40, 0, mapSemestres, intervenantRepository.findByNom("lefetz"), blocCompetences);

		// UE
		ue = uniteEnseignementRepository.save(new UniteEnseignement(null, "Développement d'applications Objet", blocCompetences));

		// modules
		saveModule("DEVE501", "Langage Java", 18, 2, mapSemestres, intervenantRepository.findByNomAndPrenom("gaber", "jaafar"), ue);
		saveModule("DEVE602", "Intégration continue (Java & Jenkins)", 18, 2, mapSemestres, intervenantRepository.findByNom("chinchole"), ue);
		saveModule("DEVE604", "Langage J2EE", 18, 2, mapSemestres, intervenantRepository.findByNomAndPrenom("gaber", "jaafar"), ue);

		// ................................................
		// bloc compétences 2
		// ................................................
		blocCompetences = blocCompetencesRepository.save(new BlocCompetences(parcours, 2, "Administration Infrastructure Système & Réseau"));
		saveProjetTransversal("TPTE516", "Projet transversal Administration & Sécurité d'une Infrastructure", 40, 0, mapSemestres, intervenantRepository.findByNom("lefetz"), blocCompetences);
		ue = uniteEnseignementRepository.save(new UniteEnseignement(null, "UE  Réseaux & Systèmes", blocCompetences));
		saveModule("DEVE507", "IP, Techno. et Services réseaux sans fil", 18, 2, mapSemestres, intervenantRepository.findByNomAndPrenom("dubois", "julien"), ue);

		// ------------------------------------------------
		// PARCOURS METIER
		// ------------------------------------------------

		// ------------------------------------------------
		// PARCOURS COMPLEMENTAIRE
		// ------------------------------------------------

		// ------------------------------------------------
		// PARCOURS PROFESSIONNEL
		// ------------------------------------------------

		// ================================================
		//
		// FORMATION I4
		//
		// ================================================

		// semestres
		// semestre1 = new Semestre(NumeroSemestre.SEMESTRE1, LocalDate.of(2017, 10, 13), LocalDate.of(2017, 12, 31));
		// semestre2 = new Semestre(NumeroSemestre.SEMESTRE2, LocalDate.of(2018, 1, 1), LocalDate.of(2018, 7, 27));

		// spécialités
		// listeSpecialites = new ArrayList<Specialite>();
		// listeSpecialites.add(new Specialite(TypeParcours.SOCLE, "SOCLE"));
		// listeSpecialites.add(new Specialite(TypeParcours.METIER, "DEV"));
		// listeSpecialites.add(new Specialite(TypeParcours.METIER, "RESEAUX"));
		// listeSpecialites.add(new Specialite(TypeParcours.METIER, "ERP"));
		// listeSpecialites.add(new Specialite(TypeParcours.COMPLEMENTAIRE, "SECU"));
		// listeSpecialites.add(new Specialite(TypeParcours.COMPLEMENTAIRE, "CLOUD"));
		// listeSpecialites.add(new Specialite(TypeParcours.COMPLEMENTAIRE, "DATA"));
		// listeSpecialites.add(new Specialite(TypeParcours.COMPLEMENTAIRE, "MOBILITE"));
		// listeSpecialites.add(new Specialite(TypeParcours.PROFESSIONNEL, "PROFESSIONNEL"));

		// formation
		// formationRepository.save(new Formation(TypeFormation.I4, "INGENIERIE 4", semestre1, semestre2, listeSpecialites));

		// ================================================
		//
		// FORMATION I5
		//
		// ================================================

		// semestres
		// semestre1 = new Semestre(NumeroSemestre.SEMESTRE1, LocalDate.of(2017, 9, 11), LocalDate.of(2018, 3, 2));
		// semestre2 = null;

		// spécialités
		// listeSpecialites = new ArrayList<Specialite>();
		// listeSpecialites.add(new Specialite(TypeParcours.SOCLE, "SOCLE"));
		// listeSpecialites.add(new Specialite(TypeParcours.METIER, "DEV"));
		// listeSpecialites.add(new Specialite(TypeParcours.METIER, "RESEAUX"));
		// listeSpecialites.add(new Specialite(TypeParcours.METIER, "ERP"));
		// listeSpecialites.add(new Specialite(TypeParcours.COMPLEMENTAIRE, "SECU"));
		// listeSpecialites.add(new Specialite(TypeParcours.COMPLEMENTAIRE, "CLOUD"));
		// listeSpecialites.add(new Specialite(TypeParcours.COMPLEMENTAIRE, "DATA"));
		// listeSpecialites.add(new Specialite(TypeParcours.COMPLEMENTAIRE, "MOBILITE"));
		// listeSpecialites.add(new Specialite(TypeParcours.PROFESSIONNEL, "PROFESSIONNEL"));

		// formation
		// formationRepository.save(new Formation(TypeFormation.I5, "INGENIERIE 5", semestre1, semestre2, listeSpecialites));

	}

	@Override
	public void initialiserSalles() {
		salleRepository.save(new Salle("jaune"));
		salleRepository.save(new Salle("rouge"));
		salleRepository.save(new Salle("verte"));
		salleRepository.save(new Salle("grise"));
		salleRepository.save(new Salle("tp-1"));
		salleRepository.save(new Salle("tp-2"));
		salleRepository.save(new Salle("bde"));
		salleRepository.save(new Salle("conférence"));
		salleRepository.save(new Salle("normandie"));
	}

	@Override
	public void initialiserIntervenants() {
		intervenantRepository.save(new Intervenant("deliessche", "christian"));
		intervenantRepository.save(new Intervenant("hardstaff", "debra"));
		intervenantRepository.save(new Intervenant("gaber", "khalid"));
		intervenantRepository.save(new Intervenant("lefetz", "guillaume"));
		intervenantRepository.save(new Intervenant("frénéa", "marylène"));
		intervenantRepository.save(new Intervenant("le gales", "julien"));
		intervenantRepository.save(new Intervenant("boutonnet", "jean-pierre"));
		intervenantRepository.save(new Intervenant("labis", "solveig"));
		intervenantRepository.save(new Intervenant("sintive", "xavier"));
		intervenantRepository.save(new Intervenant("vassiliou", "poly"));
		intervenantRepository.save(new Intervenant("duplouich", "audrey"));
		intervenantRepository.save(new Intervenant("briki", "rachid"));
		intervenantRepository.save(new Intervenant("dudek", "cédric"));
		intervenantRepository.save(new Intervenant("delanoy", "géry"));
		intervenantRepository.save(new Intervenant("dubois", "julien"));
		intervenantRepository.save(new Intervenant("gaber", "jaafar"));
		intervenantRepository.save(new Intervenant("chinchole", "michael"));
	}

	@Override
	public void initialiserModules() {

		Formation formation = null;
		Semestre semestre1 = null;
		Semestre semestre2 = null;
		Module moduleParent = null;

		// récupération des intervenants
		Intervenant deliessche = intervenantRepository.findByNom("deliessche");
		Intervenant hardstaff = intervenantRepository.findByNom("hardstaff");
		Intervenant gaberk = intervenantRepository.findByNomAndPrenom("gaber", "khalid");
		Intervenant lefetz = intervenantRepository.findByNom("lefetz");
		Intervenant frenea = intervenantRepository.findByNom("frenea");
		Intervenant legales = intervenantRepository.findByNom("legales");
		Intervenant boutonnet = intervenantRepository.findByNom("boutonnet");
		Intervenant labis = intervenantRepository.findByNom("labis");
		Intervenant sintive = intervenantRepository.findByNom("sintive");
		Intervenant vassiliou = intervenantRepository.findByNom("vassiliou");
		Intervenant duplouich = intervenantRepository.findByNom("duplouich");
		Intervenant briki = intervenantRepository.findByNom("briki");
		Intervenant dudek = intervenantRepository.findByNom("dudek");
		Intervenant delanoy = intervenantRepository.findByNom("delanoy");
		Intervenant duboisj = intervenantRepository.findByNomAndPrenom("dubois", "julien");

		// ------------------------------------------------
		// FORMATION B1
		// ------------------------------------------------

		formation = formationRepository.findByTypeFormation(TypeFormation.B1);
		semestre1 = semestreRepository.findByFormationAndNumeroSemestre(formation, NumeroSemestre.SEMESTRE1);
		semestre2 = semestreRepository.findByFormationAndNumeroSemestre(formation, NumeroSemestre.SEMESTRE2);

		// modules standards
		moduleRepository.save(new Module("BDOE111", "Exploitation d'une BD : Langage SQL sous SQL Server", semestre1, Duration.ofHours(18), Duration.ofHours(2), gaberk));
		moduleRepository.save(new Module("BDOE212", "Conception d'une BD : Langage SQL sous SQL Server", semestre2, Duration.ofHours(18), Duration.ofHours(2), gaberk));
		moduleRepository.save(new Module("BDOE213", "Modélisation d'une BD : Merise", semestre2, Duration.ofHours(18), Duration.ofHours(2), gaberk));
		moduleRepository.save(new Module("BDOE214", "Modéliser avec le Langage UML / Diagr. Classes", semestre2, Duration.ofHours(18), Duration.ofHours(2), lefetz));
		moduleRepository.save(new Module("BTSE131", "Environnement juridique des services informatiques", semestre1, Duration.ofHours(18), Duration.ofHours(2), frenea));
		moduleRepository.save(new Module("BTSE132", "Environnement managérial et économique des services informatiques", semestre1, Duration.ofHours(18), Duration.ofHours(2), frenea));
		moduleRepository.save(new Module("COME225", "Outils de la communication écrite : Structurer son écrit", semestre2, Duration.ofHours(18), Duration.ofHours(2), boutonnet));
		moduleRepository.save(new Module("COME226", "Communication orale : Exposés thématiques", semestre2, Duration.ofHours(18), Duration.ofHours(2), boutonnet));
		moduleRepository.save(new Module("DEPE127", "Atelier Apprendre à apprendre", semestre1, Duration.ofHours(4), Duration.ofHours(0), frenea));
		moduleRepository.save(new Module("DEPE228", "Développement professionnel : Portefeuille de compétences", semestre2, Duration.ofHours(18), Duration.ofHours(2), frenea));
		moduleRepository.save(new Module("DEVE101", "Algorithmique (notions de base)", semestre1, Duration.ofHours(18), Duration.ofHours(2), labis));
		moduleRepository.save(new Module("DEVE102", "Algorithmique et langage C", semestre1, Duration.ofHours(18), Duration.ofHours(2), labis));
		moduleRepository.save(new Module("DEVE104", "HTML5 / CSS3", semestre1, Duration.ofHours(18), Duration.ofHours(2), lefetz));
		moduleRepository.save(new Module("DEVE105", "PHP et MySQL", semestre1, Duration.ofHours(18), Duration.ofHours(2), sintive));
		moduleRepository.save(new Module("DEVE203", "Concepts Objet / Langage C++", semestre2, Duration.ofHours(18), Duration.ofHours(2), labis));
		moduleRepository.save(new Module("DEVE206", "Langage C++ (Les Fondamentaux)", semestre2, Duration.ofHours(18), Duration.ofHours(2), lefetz));
		moduleRepository.save(new Module("DRTE217", "Droit du travail", semestre2, Duration.ofHours(18), Duration.ofHours(2), vassiliou));
		moduleRepository.save(new Module("ECOE116", "Outils de gestion d'une entreprise / Les fondamentaux", semestre1, Duration.ofHours(18), Duration.ofHours(2), frenea));
		moduleRepository.save(new Module("LNGE223", "Méthodologie / compréhension orale et écrite", semestre2, Duration.ofHours(18), Duration.ofHours(2), duplouich));
		moduleRepository.save(new Module("LNGE224", "Presse informatique professionnelle", semestre2, Duration.ofHours(18), Duration.ofHours(2), duplouich));
		moduleRepository.save(new Module("RESE107", "Réseau Ethernet ", semestre1, Duration.ofHours(18), Duration.ofHours(2), briki));
		moduleRepository.save(new Module("RESE210", "Administration réseau Notions (IPV4 -IPV6 - firewalling)", semestre2, Duration.ofHours(18), Duration.ofHours(2), duboisj));
		moduleRepository.save(new Module("SYSE108", "Env. Windows : Installation et configuration Poste Travail", semestre1, Duration.ofHours(18), Duration.ofHours(2), briki));
		moduleRepository.save(new Module("SYSE109", "Env. Linux : Installation et configuration Poste Travail", semestre1, Duration.ofHours(18), Duration.ofHours(2), briki));
		moduleRepository.save(new Module("TPRE229", "Rapport d'activité", semestre2, Duration.ofHours(18), Duration.ofHours(2), lefetz));
		moduleRepository.save(new Module("TPRE230", "Soutenance Rapport d'activité", semestre2, Duration.ofHours(18), Duration.ofHours(2), lefetz));
		moduleRepository.save(new Module("TPTE100", "Workshop national 1", semestre1, Duration.ofHours(18), Duration.ofHours(2), lefetz));
		moduleRepository.save(new Module("TPTE120", "Projet transversal Développement d'une application Web", semestre1, Duration.ofHours(18), Duration.ofHours(2), legales));
		moduleRepository.save(new Module("TPTE120", "Projet transversal Développement d'une application Web", semestre1, Duration.ofHours(18), Duration.ofHours(2), legales));
		moduleRepository.save(new Module("TPTE121", "Projet transversal Installation d'une Infrastructure Système sous l'environnement linux ou windows (au choix)", semestre1, Duration.ofHours(18),
				Duration.ofHours(2), briki));
		moduleRepository.save(new Module("TPTE136", "Atelier HEP OnBoarding", semestre1, Duration.ofHours(18), Duration.ofHours(2), lefetz));
		moduleRepository.save(new Module("TPTE200", "Workshop national 2", semestre2, Duration.ofHours(18), Duration.ofHours(2), lefetz));
		moduleRepository.save(new Module("TPTE215", "Projet transversal Développement d'une application objet : NAO & le langage C++ ou Python (au choix)", semestre2, Duration.ofHours(18),
				Duration.ofHours(2), legales));
		moduleRepository.save(new Module("TPTE222", "Projet transversal Installation d'une Infrastructure Réseau", semestre2, Duration.ofHours(18), Duration.ofHours(2), dudek));
		moduleRepository.save(new Module("TQGE218", "Logique, Ensembles, Calculs Booléens", semestre2, Duration.ofHours(18), Duration.ofHours(2), delanoy));
		moduleRepository.save(new Module("TQGE219", "Suites et Etudes de Fonction", semestre2, Duration.ofHours(18), Duration.ofHours(2), delanoy));
		moduleRepository.save(new Module("BTSE235", "TE - Projet PPE 2", semestre2, Duration.ofHours(18), Duration.ofHours(2), lefetz));

		// ppe1
		moduleParent = moduleRepository.save(new Module("BTSE134", "Projet PPE 1", semestre1, Duration.ofHours(20), Duration.ofHours(0)));
		moduleRepository.save(new Module(moduleParent, "Projet PPE 1 (dev)", Duration.ofHours(10), Duration.ofHours(0), legales));
		moduleRepository.save(new Module(moduleParent, "Projet PPE 1 (res)", Duration.ofHours(10), Duration.ofHours(0), dudek));

		// ppe2
		moduleParent = moduleRepository.save(new Module("BTSE235", "Projet PPE 2", semestre2, Duration.ofHours(40), Duration.ofHours(0)));
		moduleRepository.save(new Module(moduleParent, "Projet PPE 2 (dev)", Duration.ofHours(20), Duration.ofHours(0), legales));
		moduleRepository.save(new Module(moduleParent, "Projet PPE 2 (res)", Duration.ofHours(20), Duration.ofHours(0), dudek));

		// ------------------------------------------------
		// FORMATION B2
		// ------------------------------------------------

		formation = formationRepository.findByTypeFormation(TypeFormation.B2);
		semestre1 = semestreRepository.findByFormationAndNumeroSemestre(formation, NumeroSemestre.SEMESTRE1);

		// ------------------------------------------------
		// FORMATION B3
		// ------------------------------------------------

		formation = formationRepository.findByTypeFormation(TypeFormation.B3);
		semestre1 = semestreRepository.findByFormationAndNumeroSemestre(formation, NumeroSemestre.SEMESTRE1);
		moduleRepository.save(new Module("TPTE500", "Workshop 1 - Hackaton / Développement d'une application objet", semestre1, Duration.ofHours(30), Duration.ofHours(0), lefetz));
		moduleRepository.save(
				new Module("TPTE600", "Workshop 2 - Créativité & Innovation / TagCloud : Sécurité - Professionnalisme - Domotique", semestre2, Duration.ofHours(30), Duration.ofHours(0), lefetz));
		moduleRepository.save(new Module("DEVE539", "Sécurité Système et Réseaux - les fondamentaux", semestre1, Duration.ofHours(18), Duration.ofHours(2), deliessche));
		moduleRepository.save(new Module("LNGE627", "Case Study (usual English)", semestre1, Duration.ofHours(20), Duration.ofHours(0), hardstaff));

		// ------------------------------------------------
		// FORMATION I4
		// ------------------------------------------------

		formation = formationRepository.findByTypeFormation(TypeFormation.I4);
		semestre1 = semestreRepository.findByFormationAndNumeroSemestre(formation, NumeroSemestre.SEMESTRE1);

		// ------------------------------------------------
		// FORMATION I5
		// ------------------------------------------------

		formation = formationRepository.findByTypeFormation(TypeFormation.I5);
		semestre1 = semestreRepository.findByFormationAndNumeroSemestre(formation, NumeroSemestre.SEMESTRE1);

	}

	/**
	 * Méthode utilitaire privée, permettant de persister un module et d'alléger le code
	 * 
	 * @param codeModule
	 * @param libelle
	 * @param dureeFFP
	 * @param dureeTE
	 * @param mapSemestres
	 * @param intervenant
	 * @param ue
	 */
	private void saveModule(String codeModule, String libelle, int dureeFFP, int dureeTE, Map<NumeroSemestre, Semestre> mapSemestres, Intervenant intervenant, UniteEnseignement ue) {
		moduleRepository
				.save(new Module(codeModule, libelle, nomenclatureService.autoselectSemestre(codeModule, mapSemestres), Duration.ofHours(dureeFFP), Duration.ofHours(dureeTE), intervenant, ue));
	}

	/**
	 * Méthode utilitaire privée, permettant de persister un projet transversal et d'alléger le code
	 * 
	 * @param codeModule
	 * @param libelle
	 * @param dureeFFP
	 * @param dureeTE
	 * @param mapSemestres
	 * @param intervenant
	 * @param blocCompetences
	 */
	private void saveProjetTransversal(String codeModule, String libelle, int dureeFFP, int dureeTE, Map<NumeroSemestre, Semestre> mapSemestres, Intervenant intervenant,
			BlocCompetences blocCompetences) {
		projetTransversalRepository.save(new ProjetTransversal(codeModule, libelle, nomenclatureService.autoselectSemestre(codeModule, mapSemestres), Duration.ofHours(dureeFFP),
				Duration.ofHours(dureeTE), intervenant, blocCompetences));

	}

}
