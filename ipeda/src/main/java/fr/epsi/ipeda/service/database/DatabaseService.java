package fr.epsi.ipeda.service.database;

import java.lang.invoke.MethodHandles;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.epsi.ipeda.dao.entity.BlocCompetences;
import fr.epsi.ipeda.dao.entity.Cours;
import fr.epsi.ipeda.dao.entity.Formation;
import fr.epsi.ipeda.dao.entity.Formation.TypeFormation;
import fr.epsi.ipeda.dao.entity.Intervenant;
import fr.epsi.ipeda.dao.entity.Module;
import fr.epsi.ipeda.dao.entity.Parcours;
import fr.epsi.ipeda.dao.entity.ProjetTransversal;
import fr.epsi.ipeda.dao.entity.Salle;
import fr.epsi.ipeda.dao.entity.Salle.CodeSalle;
import fr.epsi.ipeda.dao.entity.Semaine;
import fr.epsi.ipeda.dao.entity.Semestre;
import fr.epsi.ipeda.dao.entity.Semestre.NumeroSemestre;
import fr.epsi.ipeda.dao.entity.UniteEnseignement;
import fr.epsi.ipeda.dao.entity.UniteEnseignement.TypeUE;
import fr.epsi.ipeda.dao.repository.BlocCompetencesRepository;
import fr.epsi.ipeda.dao.repository.CoursRepository;
import fr.epsi.ipeda.dao.repository.FormationRepository;
import fr.epsi.ipeda.dao.repository.IntervenantRepository;
import fr.epsi.ipeda.dao.repository.ModuleRepository;
import fr.epsi.ipeda.dao.repository.ParcoursRepository;
import fr.epsi.ipeda.dao.repository.ProjetTransversalRepository;
import fr.epsi.ipeda.dao.repository.SalleRepository;
import fr.epsi.ipeda.dao.repository.UniteEnseignementRepository;
import fr.epsi.ipeda.helpers.FormatterUtils;
import fr.epsi.ipeda.service.businesslogic.nomenclature.INomenclatureService;

@Service
public class DatabaseService implements IDatabaseService {

	final static Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

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
	private CoursRepository coursRepository;

	@Autowired
	private INomenclatureService nomenclatureService;

	@SuppressWarnings("serial")
	@Override
	public void initialiserFormationB3() {

		Map<NumeroSemestre, Semestre> mapSemestres = null;
		UniteEnseignement ue = null;
		Formation formation = null;
		Parcours parcours = null;
		BlocCompetences blocCompetences = null;

		// ================================================================================================
		//
		//
		// FORMATION B3
		//
		//
		// ================================================================================================

		// ------------------------------------------------
		// Semestres
		// ------------------------------------------------

		mapSemestres = new HashMap<NumeroSemestre, Semestre>() {
			{
				put(NumeroSemestre.SEMESTRE1, new Semestre(NumeroSemestre.SEMESTRE1, LocalDate.of(2017, 9, 28), LocalDate.of(2017, 12, 31)));
				put(NumeroSemestre.SEMESTRE2, new Semestre(NumeroSemestre.SEMESTRE2, LocalDate.of(2018, 1, 1), LocalDate.of(2018, 7, 20)));
			}
		};

		// ------------------------------------------------
		// Formation
		// ------------------------------------------------

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
		createModule("TPTE500", "Workshop 1 - Hackaton / Développement d'une application objet", 30, 0, mapSemestres, intervenantRepository.findByNom("lefetz"), ue);
		createModule("TPTE600", "Workshop 2 - Créativité & Innovation / TagCloud : Sécurité - Professionnalisme - Domotique", 30, 0, mapSemestres, intervenantRepository.findByNom("lefetz"), ue);

		// ------------------------------------------------
		// bloc compétences 1
		// ------------------------------------------------

		blocCompetences = blocCompetencesRepository.save(new BlocCompetences(parcours, 1, "Développement d'applications informatiques"));
		createProjetTransversal("TPTE511", "Projet transversal Développement d'une application en langage Java", 40, 0, mapSemestres, intervenantRepository.findByNom("lefetz"), blocCompetences);
		ue = uniteEnseignementRepository.save(new UniteEnseignement(null, "Développement d'applications Objet", blocCompetences));
		createModule("DEVE501", "Langage Java", 18, 2, mapSemestres, intervenantRepository.findByNomAndPrenom("gaber", "jaafar"), ue);
		createModule("DEVE602", "Intégration continue (Java & Jenkins)", 18, 2, mapSemestres, intervenantRepository.findByNom("chinchole"), ue);
		createModule("DEVE604", "Langage J2EE", 18, 2, mapSemestres, intervenantRepository.findByNomAndPrenom("gaber", "jaafar"), ue);

		// ................................................
		// bloc compétences 2
		// ................................................

		blocCompetences = blocCompetencesRepository.save(new BlocCompetences(parcours, 2, "Administration Infrastructure Système & Réseau"));
		createProjetTransversal("TPTE516", "Projet transversal Administration & Sécurité d'une Infrastructure", 40, 0, mapSemestres, intervenantRepository.findByNom("deliessche"), blocCompetences);
		ue = uniteEnseignementRepository.save(new UniteEnseignement(null, "Réseaux & Systèmes", blocCompetences));
		createModule("DEVE507", "IP, Techno. et Services réseaux sans fil", 18, 2, mapSemestres, intervenantRepository.findByNomAndPrenom("dubois", "julien"), ue);
		createModule("DEVE539", "Sécurité Système et Réseaux - les fondamentaux", 18, 2, mapSemestres, intervenantRepository.findByNom("deliessche"), ue);
		createModule("DEVE640", "Administration sous windows  : automatisation des tâches", 18, 2, mapSemestres, intervenantRepository.findByNom("rombeaut"), ue);

		// ................................................
		// bloc compétences 3
		// ................................................

		blocCompetences = blocCompetencesRepository.save(new BlocCompetences(parcours, 3, "Gestion des Données"));
		createProjetTransversal("TPTE621", "Projet transversal Développement & Admnistration d'une BD", 20, 0, mapSemestres, intervenantRepository.findByNomAndPrenom("gaber", "khalid"),
				blocCompetences);
		ue = uniteEnseignementRepository.save(new UniteEnseignement(null, "SGBD  Oracle", blocCompetences));
		createModule("BDOE505", "Conception et exploitation d'une BD", 18, 2, mapSemestres, intervenantRepository.findByNomAndPrenom("gaber", "khalid"), ue);
		createModule("BDOE606", "Administration d'une BD", 18, 2, mapSemestres, intervenantRepository.findByNomAndPrenom("gaber", "khalid"), ue);

		// ................................................
		// bloc compétences 4
		// ................................................

		blocCompetences = blocCompetencesRepository.save(new BlocCompetences(parcours, 4, "Méthodes & Projet"));
		ue = uniteEnseignementRepository.save(new UniteEnseignement(null, "Projet S.I. & Recherche opérationnelle", blocCompetences));
		createModule("PROE509", "UML / Autres diagrammes + Utilisation AGL", 18, 2, mapSemestres, intervenantRepository.findByNomAndPrenom("gaber", "khalid"), ue);
		createModule("PROE510", "Gestion de Projet via ITIL", 18, 2, mapSemestres, intervenantRepository.findByNom("cerisier"), ue);
		createModule("TQGE625", "R.O. : Programmation linéaire", 18, 2, mapSemestres, intervenantRepository.findByNom("maati"), ue);
		ue = uniteEnseignementRepository.save(new UniteEnseignement(null, "Environnement Juridique & Financier", blocCompetences));
		createModule("DRTE522", "Droit de l'Internet", 18, 2, mapSemestres, intervenantRepository.findByNom("quenton"), ue);
		createModule("FINE624", "Finance / Business Plan", 18, 2, mapSemestres, intervenantRepository.findByNom("frénéa"), ue);

		// ................................................
		// bloc compétences 5
		// ................................................

		blocCompetences = blocCompetencesRepository.save(new BlocCompetences(parcours, 5, "Communication"));
		ue = uniteEnseignementRepository.save(new UniteEnseignement(null, "Anglais", blocCompetences));
		createModule("LNGE627", "Case Study (usual English)", 18, 2, mapSemestres, intervenantRepository.findByNom("hardstaff"), ue);
		createModule("LNGE628", "Case Study (technical English)", 18, 2, mapSemestres, intervenantRepository.findByNom("hardstaff"), ue);
		ue = uniteEnseignementRepository.save(new UniteEnseignement(null, "Techniques d'Expression et Communication", blocCompetences));
		createModule("COME629", "Techniques rédactionnelles professionnelles", 18, 2, mapSemestres, intervenantRepository.findByNom("boutonnet"), ue);
		createModule("COME630", "Communication professionnelle", 18, 2, mapSemestres, intervenantRepository.findByNom("boutonnet"), ue);

		// ================================================
		//
		// PARCOURS METIER
		//
		// ================================================

		parcours = parcoursRepository.save(new Parcours(Parcours.TypeParcours.METIER, false, formation));

		// ................................................
		// bloc compétences 6
		// ................................................

		blocCompetences = blocCompetencesRepository.save(new BlocCompetences(parcours, 6, "Conception de Solutions d'infrastructures"));
		ProjetTransversal kcreaInfra = createProjetTransversal("TPTE615", "Projet transversal Conception, développement et intégration d'une Solution d'Infrastructure - (Kcréa)", 40, 0, mapSemestres,
				intervenantRepository.findByNom("dal-pra"), blocCompetences);
		ue = uniteEnseignementRepository.save(new UniteEnseignement(TypeUE.RESEAUX, "Gestion et Performance de solution d'infrastructure", blocCompetences));
		createModule("RESE631", "Conception et optimisation d'une architecture réseaux", 18, 2, mapSemestres, intervenantRepository.findByNom("rombeaut"), ue);
		createModule("RESE613", "Haute Disponibilité", 18, 2, mapSemestres, intervenantRepository.findByNom("deliessche"), ue);
		createModule("RESE612", "Routage dynamique : env. et protocoles", 18, 2, mapSemestres, intervenantRepository.findByNom("deliessche"), ue);

		// ................................................
		// bloc compétences 7
		// ................................................

		blocCompetences = blocCompetencesRepository.save(new BlocCompetences(parcours, 7, "Conception de Solutions applicatives"));
		ProjetTransversal kcreaDev = createProjetTransversal("TPTE620", "Projet transversal Conception, développement et intégration d'une Solution Applicative - (Kcréa)", 40, 0, mapSemestres,
				intervenantRepository.findByNom("dal-pra"), blocCompetences);
		ue = uniteEnseignementRepository.save(new UniteEnseignement(TypeUE.DEV, "Conception et Développement S.I.", blocCompetences));
		createModule("DEVE617", "Design Pattern (Java)", 18, 2, mapSemestres, intervenantRepository.findByNom("chinchole"), ue);
		createModule("DEVE618", "Mapping Objet Relationnel (ORM/Java)", 18, 2, mapSemestres, intervenantRepository.findByNom("chinchole"), ue);
		createModule("DEVE619", "Architecture logicielle", 18, 2, mapSemestres, intervenantRepository.findByNom("chinchole"), ue);

		// création du module mutualisé KCréa
		createModuleMutualise("Projet transversal Conception, développement et intégration d'une Solution (Kcréa)", new ArrayList<Module>() {
			{
				add(kcreaInfra);
				add(kcreaDev);
			}
		});

		// ------------------------------------------------
		// PARCOURS COMPLEMENTAIRE
		// ------------------------------------------------

		parcours = parcoursRepository.save(new Parcours(Parcours.TypeParcours.COMPLEMENTAIRE, false, formation));

		ue = uniteEnseignementRepository.save(new UniteEnseignement(TypeUE.SECU, "Sécurité Informatique", parcours));
		createModule("SYSE634", "Sécurité des applications et services Web", 18, 2, mapSemestres, intervenantRepository.findByNom("deliessche"), ue);
		createModule("SYSE635", "Piratage Ethique", 18, 2, mapSemestres, intervenantRepository.findByNom("deliessche"), ue);

		ue = uniteEnseignementRepository.save(new UniteEnseignement(TypeUE.VIRTU, "Virtualisation", parcours));
		createModule("RESE608", "Stratégie & Outils de virtualisation : Docker et VMWare", 18, 2, mapSemestres, null, ue);
		createModule("RESE614", "Scripting pour la Virtualisation", 18, 2, mapSemestres, null, ue);

		ue = uniteEnseignementRepository.save(new UniteEnseignement(TypeUE.DATA, "Données & Objets Connectés", parcours));
		createModule("BDOE636", "Objets connectés et l'Internet des Objets (les fondamentaux)", 18, 2, mapSemestres, intervenantRepository.findByNom("chinchole"), ue);
		createModule("SYSE637", "Prototypage d'un objet connecté", 18, 2, mapSemestres, intervenantRepository.findByNom("chinchole"), ue);

		// ------------------------------------------------
		// PARCOURS PROFESSIONNEL
		// ------------------------------------------------

		parcours = parcoursRepository.save(new Parcours(Parcours.TypeParcours.PROFESSIONNEL, false, formation));

		ue = uniteEnseignementRepository.save(new UniteEnseignement(null, "Ateliers Développement professionnel & Active learning", parcours));
		createModule("xxxE601", "Atelier Apprendre à apprendre", 6, 0, mapSemestres, intervenantRepository.findByNom("frénéa"), ue);
		createModule("xxxE602", "Développement professionnel : Portefeuille de compétences", 4, 0, mapSemestres, intervenantRepository.findByNom("frénéa"), ue);
		createModule("DEPE638", "Active member / Participation Vie Ecole", 0, 0, mapSemestres, null, ue);

		ue = uniteEnseignementRepository.save(new UniteEnseignement(null, "Stage en entreprise", parcours));
		createModule("TPRE632", "Rapport d'activité", 0, 0, mapSemestres, intervenantRepository.findByNom("lefetz"), ue);
		createModule("TPRE633", "Suivi Mission en alternance & Soutenance Rapport d'activité", 0, 1, mapSemestres, intervenantRepository.findByNom("lefetz"), ue);

	}

	@Override
	public void initialiserSalles() {
		salleRepository.save(new Salle(CodeSalle.JAUNE, "jaune"));
		salleRepository.save(new Salle(CodeSalle.ROUGE, "rouge"));
		salleRepository.save(new Salle(CodeSalle.VERTE, "verte"));
		salleRepository.save(new Salle(CodeSalle.GRISE, "grise"));
		salleRepository.save(new Salle(CodeSalle.TP2, "tp-2"));
		salleRepository.save(new Salle(CodeSalle.CONF, "conférence"));
		salleRepository.save(new Salle(CodeSalle.NORMANDIE, "normandie"));
	}

	@Override
	public void initialiserIntervenants() {
		intervenantRepository.save(new Intervenant("alamasset", "eddie"));
		intervenantRepository.save(new Intervenant("boutonnet", "jean-pierre"));
		intervenantRepository.save(new Intervenant("briki", "rachid"));
		intervenantRepository.save(new Intervenant("cerisier", "grégory"));
		intervenantRepository.save(new Intervenant("chinchole", "michaël"));
		intervenantRepository.save(new Intervenant("dal-pra", "annie"));
		intervenantRepository.save(new Intervenant("decroix", "sébastien"));
		intervenantRepository.save(new Intervenant("delanoy", "géry"));
		intervenantRepository.save(new Intervenant("deliessche", "christian"));
		intervenantRepository.save(new Intervenant("dewaleyne", "freddie"));
		intervenantRepository.save(new Intervenant("doco", "david"));
		intervenantRepository.save(new Intervenant("dubois", "julien"));
		intervenantRepository.save(new Intervenant("dubois", "patrick"));
		intervenantRepository.save(new Intervenant("dudek", "cédric"));
		intervenantRepository.save(new Intervenant("duplouich", "audrey"));
		intervenantRepository.save(new Intervenant("flasque", "sébastien"));
		intervenantRepository.save(new Intervenant("frenea", "marylène"));
		intervenantRepository.save(new Intervenant("fritsch", "thomas"));
		intervenantRepository.save(new Intervenant("gaber", "jaafar"));
		intervenantRepository.save(new Intervenant("gaber", "khalid"));
		intervenantRepository.save(new Intervenant("gaber", "oussama"));
		intervenantRepository.save(new Intervenant("hanot", "jean-jacques"));
		intervenantRepository.save(new Intervenant("hardstaff", "debra"));
		intervenantRepository.save(new Intervenant("kaszer", "daishi"));
		intervenantRepository.save(new Intervenant("labis", "solveig"));
		intervenantRepository.save(new Intervenant("le gales", "julien"));
		intervenantRepository.save(new Intervenant("lefetz", "guillaume"));
		intervenantRepository.save(new Intervenant("lenglet", "julien"));
		intervenantRepository.save(new Intervenant("lepoutre", "jean-claude"));
		intervenantRepository.save(new Intervenant("maati", "abderrabi"));
		intervenantRepository.save(new Intervenant("nakache", "didier"));
		intervenantRepository.save(new Intervenant("quenton", "amandine"));
		intervenantRepository.save(new Intervenant("regost", "philippe"));
		intervenantRepository.save(new Intervenant("rombeaut", "jean-pierre"));
		intervenantRepository.save(new Intervenant("seifert", "anthony"));
		intervenantRepository.save(new Intervenant("sintive", "xavier"));
		intervenantRepository.save(new Intervenant("spella", "michel"));
		intervenantRepository.save(new Intervenant("vassiliou", "poly"));
		intervenantRepository.save(new Intervenant("vermersch", "olivier"));
		intervenantRepository.save(new Intervenant("vigneron", "jean-baptiste"));

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
	private void createModule(String codeModule, String libelle, int dureeFFP, int dureeTE, Map<NumeroSemestre, Semestre> mapSemestres, Intervenant intervenant, UniteEnseignement ue) {
		moduleRepository
				.save(new Module(codeModule, libelle, nomenclatureService.autoselectSemestre(codeModule, mapSemestres), Duration.ofHours(dureeFFP), Duration.ofHours(dureeTE), intervenant, ue));
	}

	/**
	 * Méthode utilitaire privée, permettant de persister un module mutualisé et d'alléger le code. Un module mutualisé est un module "parent" qui n'a pas de code, et qui est composé de 2 ou plus
	 * modules enfants. Il doit être dispensé par le même intervenant. Les modules doivent avoir des volumes horaires identiques.
	 * 
	 * @param libelle
	 * @param listeModulesMutualises
	 */
	private void createModuleMutualise(String libelle, ArrayList<Module> listeModulesMutualises) {

		int tailleMinimum = 2;
		boolean isValide = true;

		// vérifications : même intervenant, mêmes heures FFP
		Module moduleReference = null;
		if (null != listeModulesMutualises) {
			if (listeModulesMutualises.size() >= tailleMinimum) {

				// récupération module de référence
				moduleReference = listeModulesMutualises.get(0);
				listeModulesMutualises.remove(0);

				for (Module module : listeModulesMutualises) {
					if (module.getDureeFFP() != moduleReference.getDureeFFP() || module.getDureeTE() != moduleReference.getDureeTE()
							|| !module.getIntervenant().equals(moduleReference.getIntervenant())) {
						isValide = false;
						logger.error("Les modules à mutualiser ne sont pas compatibles, impossible de mutualiser.");
						break;
					}
				}

				if (isValide) {
					// sauvegarde du module mutualisé
					Module module = new Module();
					module.setDureeFFP(moduleReference.getDureeFFP());
					module.setDureeTE(moduleReference.getDureeTE());
					module.setIntervenant(moduleReference.getIntervenant());
					module.setLibelle(libelle);
					module.setListeModulesMutualises(listeModulesMutualises);
					module.setSemestre(moduleReference.getSemestre());
					module.setUniteEnseignement(moduleReference.getUniteEnseignement());
					moduleRepository.save(module);
				}
			} else {
				logger.error("La taille minimum requise (" + tailleMinimum + ") pour 'listeModulesMutualises' n'est pas atteinte.");
			}
		} else {
			logger.error("'listeModulesMutualises' est NULL.");
		}

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
	 * @return
	 */
	private ProjetTransversal createProjetTransversal(String codeModule, String libelle, int dureeFFP, int dureeTE, Map<NumeroSemestre, Semestre> mapSemestres, Intervenant intervenant,
			BlocCompetences blocCompetences) {
		return projetTransversalRepository.save(new ProjetTransversal(codeModule, libelle, nomenclatureService.autoselectSemestre(codeModule, mapSemestres), Duration.ofHours(dureeFFP),
				Duration.ofHours(dureeTE), intervenant, blocCompetences));

	}

	private void createCours(String dateHeureDebut, int duree, String codeModule, CodeSalle codeSalle) {
		coursRepository.save(new Cours(LocalDateTime.parse(dateHeureDebut, FormatterUtils.getDateTimeFormatterFR()), Duration.ofHours(duree), moduleRepository.findByCode(codeModule),
				salleRepository.findByCodeSalle(codeSalle)));
	}

	@Override
	public void initialiserCoursB3() {

		// semaine 2018-16
		createCours("16/04/2018 08:15", 4, "RESE631", CodeSalle.CONF);
		createCours("16/04/2018 13:45", 4, "RESE631", CodeSalle.CONF);
		createCours("17/04/2018 08:15", 4, "RESE631", CodeSalle.CONF);
		createCours("17/04/2018 13:45", 4, "RESE631", CodeSalle.CONF);
		createCours("18/04/2018 08:15", 4, "TPTE615", CodeSalle.CONF);
		createCours("18/04/2018 13:45", 4, "LNGE628", CodeSalle.CONF);
		createCours("19/04/2018 08:15", 2, "RESE631", CodeSalle.CONF);
		createCours("19/04/2018 10:15", 2, "SYSE635", CodeSalle.CONF);
		createCours("19/04/2018 13:45", 4, "PROE509", CodeSalle.CONF);
		createCours("20/04/2018 08:15", 4, "PROE509", CodeSalle.CONF);
		createCours("20/04/2018 13:45", 4, "PROE509", CodeSalle.CONF);

		// semaine 2018-17
		createCours("23/04/2018 08:15", 4, "TQGE625", CodeSalle.CONF);
		createCours("23/04/2018 13:45", 4, "TQGE625", CodeSalle.CONF);
		createCours("24/04/2018 08:15", 4, "TQGE625", CodeSalle.CONF);
		createCours("24/04/2018 13:45", 4, "TQGE625", CodeSalle.CONF);
		createCours("25/04/2018 08:15", 4, "TQGE625", CodeSalle.CONF);
		createCours("25/04/2018 13:45", 4, "TQGE625", CodeSalle.CONF);
		createCours("26/04/2018 08:15", 4, "TQGE625", CodeSalle.CONF);
		createCours("26/04/2018 13:45", 4, "TQGE625", CodeSalle.CONF);
		createCours("27/04/2018 08:15", 4, "TQGE625", CodeSalle.CONF);
		createCours("27/04/2018 13:45", 4, "TQGE625", CodeSalle.CONF);
	}

	@Override
	public void afficheCours() {
		List<Cours> listeCours = coursRepository.findByModule(moduleRepository.findByCode("RESE631"));
		for (Cours cours : listeCours) {
			System.out.println("cours : " + cours.getModule().getCode() + " - " + cours.getModule().getLibelle() + " - " + cours.getListeSalles().get(0).getLibelle() + " - "
					+ cours.getModule().getIntervenant().getNom());
		}
	}

	@Override
	public void getPlanningBySemaine(TypeFormation typeFormation, Semaine semaine) {
		// LocalDate dateDebut = semaine.getDateDebut();
		// LocalDate dateFin = semaine.getDateFin();

	}

}
