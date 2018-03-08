package fr.epsi.ipeda.service.database.feeding;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.epsi.ipeda.dao.entity.BlocCompetences;
import fr.epsi.ipeda.dao.entity.BlocCompetences.TypeBloc;
import fr.epsi.ipeda.dao.entity.Formation;
import fr.epsi.ipeda.dao.entity.Formation.TypeFormation;
import fr.epsi.ipeda.dao.entity.Module;
import fr.epsi.ipeda.dao.entity.Parcours;
import fr.epsi.ipeda.dao.entity.ProjetTransversal;
import fr.epsi.ipeda.dao.entity.Salle.CodeSalle;
import fr.epsi.ipeda.dao.entity.Semestre;
import fr.epsi.ipeda.dao.entity.Semestre.NumeroSemestre;
import fr.epsi.ipeda.dao.entity.UniteEnseignement;
import fr.epsi.ipeda.dao.entity.UniteEnseignement.TypeUE;
import fr.epsi.ipeda.dao.repository.BlocCompetencesRepository;
import fr.epsi.ipeda.dao.repository.FormationRepository;
import fr.epsi.ipeda.dao.repository.IntervenantRepository;
import fr.epsi.ipeda.dao.repository.ParcoursRepository;
import fr.epsi.ipeda.dao.repository.UniteEnseignementRepository;
import fr.epsi.ipeda.service.database.IDatabaseService;

@Component
public class B3 implements IFeeding {

	@Autowired
	private FormationRepository formationRepository;

	@Autowired
	private UniteEnseignementRepository uniteEnseignementRepository;

	@Autowired
	private ParcoursRepository parcoursRepository;

	@Autowired
	private BlocCompetencesRepository blocCompetencesRepository;

	@Autowired
	private IntervenantRepository intervenantRepository;

	@Autowired
	private IDatabaseService databaseService;

	@SuppressWarnings("serial")
	@Override
	public void initialiserFormation() {

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
				put(NumeroSemestre.SEMESTRE1, new Semestre(NumeroSemestre.SEMESTRE1, LocalDate.of(2017, 9, 28), LocalDate.of(2018, 02, 16)));
				put(NumeroSemestre.SEMESTRE2, new Semestre(NumeroSemestre.SEMESTRE2, LocalDate.of(2018, 2, 19), LocalDate.of(2018, 7, 31)));
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
		databaseService.createModule("TPTE500", "Workshop 1 - Hackaton / Développement d'une application objet", 30, 0, mapSemestres, intervenantRepository.findByNom("lefetz"), ue);
		databaseService.createModule("TPTE600", "Workshop 2 - Créativité & Innovation / TagCloud : Sécurité - Professionnalisme - Domotique", 30, 0, mapSemestres,
				intervenantRepository.findByNom("lefetz"), ue);

		// ------------------------------------------------
		// bloc compétences 1
		// ------------------------------------------------

		blocCompetences = blocCompetencesRepository.save(new BlocCompetences(parcours, 1, "Développement d'applications informatiques"));
		databaseService.createProjetTransversal("TPTE511", "Projet transversal Développement d'une application en langage Java", 40, 0, mapSemestres, intervenantRepository.findByNom("lefetz"),
				blocCompetences);
		ue = uniteEnseignementRepository.save(new UniteEnseignement(null, "Développement d'applications Objet", blocCompetences));
		databaseService.createModule("DEVE501", "Langage Java", 18, 2, mapSemestres, intervenantRepository.findByNomAndPrenom("gaber", "jaafar"), ue);
		databaseService.createModule("DEVE602", "Intégration continue (Java & Jenkins)", 18, 2, mapSemestres, intervenantRepository.findByNom("chinchole"), ue);
		databaseService.createModule("DEVE604", "Langage J2EE", 18, 2, mapSemestres, intervenantRepository.findByNomAndPrenom("gaber", "jaafar"), ue);

		// ................................................
		// bloc compétences 2
		// ................................................

		blocCompetences = blocCompetencesRepository.save(new BlocCompetences(parcours, 2, "Administration Infrastructure Système & Réseau"));
		databaseService.createProjetTransversal("TPTE516", "Projet transversal Administration & Sécurité d'une Infrastructure", 40, 0, mapSemestres, intervenantRepository.findByNom("deliessche"),
				blocCompetences);
		ue = uniteEnseignementRepository.save(new UniteEnseignement(null, "Réseaux & Systèmes", blocCompetences));
		databaseService.createModule("DEVE507", "IP, Techno. et Services réseaux sans fil", 18, 2, mapSemestres, intervenantRepository.findByNomAndPrenom("dubois", "julien"), ue);
		databaseService.createModule("DEVE539", "Sécurité Système et Réseaux - les fondamentaux", 18, 2, mapSemestres, intervenantRepository.findByNom("deliessche"), ue);
		databaseService.createModule("DEVE640", "Administration sous windows : automatisation des tâches", 18, 2, mapSemestres, intervenantRepository.findByNom("rombeaut"), ue);

		// ................................................
		// bloc compétences 3
		// ................................................

		blocCompetences = blocCompetencesRepository.save(new BlocCompetences(parcours, 3, "Gestion des Données"));
		databaseService.createProjetTransversal("TPTE621", "Projet transversal Développement & Admnistration d'une BD", 20, 0, mapSemestres,
				intervenantRepository.findByNomAndPrenom("gaber", "khalid"), blocCompetences);
		ue = uniteEnseignementRepository.save(new UniteEnseignement(null, "SGBD Oracle", blocCompetences));
		databaseService.createModule("BDOE505", "Conception et exploitation d'une BD", 18, 2, mapSemestres, intervenantRepository.findByNomAndPrenom("gaber", "khalid"), ue);
		databaseService.createModule("BDOE606", "Administration d'une BD", 18, 2, mapSemestres, intervenantRepository.findByNomAndPrenom("gaber", "khalid"), ue);

		// ................................................
		// bloc compétences 4
		// ................................................

		blocCompetences = blocCompetencesRepository.save(new BlocCompetences(parcours, 4, "Méthodes & Projet"));
		ue = uniteEnseignementRepository.save(new UniteEnseignement(null, "Projet S.I. & Recherche opérationnelle", blocCompetences));
		databaseService.createModule("PROE509", "UML / Autres diagrammes + Utilisation AGL", 18, 2, mapSemestres, intervenantRepository.findByNomAndPrenom("gaber", "khalid"), ue);
		databaseService.createModule("PROE510", "Gestion de Projet via ITIL", 18, 2, mapSemestres, intervenantRepository.findByNom("cerisier"), ue);
		databaseService.createModule("TQGE625", "R.O. : Programmation linéaire", 18, 2, mapSemestres, intervenantRepository.findByNom("maati"), ue);
		ue = uniteEnseignementRepository.save(new UniteEnseignement(null, "Environnement Juridique & Financier", blocCompetences));
		databaseService.createModule("DRTE522", "Droit de l'Internet", 18, 2, mapSemestres, intervenantRepository.findByNom("quenton"), ue);
		databaseService.createModule("FINE624", "Finance / Business Plan", 18, 2, mapSemestres, intervenantRepository.findByNom("frénéa"), ue);

		// ................................................
		// bloc compétences 5
		// ................................................

		blocCompetences = blocCompetencesRepository.save(new BlocCompetences(parcours, 5, "Communication"));
		ue = uniteEnseignementRepository.save(new UniteEnseignement(null, "Anglais", blocCompetences));
		databaseService.createModule("LNGE627", "Case Study (usual English)", 18, 2, mapSemestres, intervenantRepository.findByNom("hardstaff"), ue);
		databaseService.createModule("LNGE628", "Case Study (technical English)", 18, 2, mapSemestres, intervenantRepository.findByNom("hardstaff"), ue);
		ue = uniteEnseignementRepository.save(new UniteEnseignement(null, "Techniques d'Expression et Communication", blocCompetences));
		databaseService.createModule("COME629", "Techniques rédactionnelles professionnelles", 18, 2, mapSemestres, intervenantRepository.findByNom("boutonnet"), ue);
		databaseService.createModule("COME630", "Communication professionnelle", 18, 2, mapSemestres, intervenantRepository.findByNom("boutonnet"), ue);

		// ================================================
		//
		// PARCOURS METIER
		//
		// ================================================

		parcours = parcoursRepository.save(new Parcours(Parcours.TypeParcours.METIER, false, formation));

		// ................................................
		// bloc compétences 6
		// ................................................

		blocCompetences = blocCompetencesRepository.save(new BlocCompetences(TypeBloc.RESEAUX, parcours, 6, "Conception de Solutions d'infrastructures"));
		ProjetTransversal kcreaInfra = databaseService.createProjetTransversal("TPTE615", "Projet transversal Conception, développement et intégration d'une Solution d'Infrastructure - (Kcréa)", 40,
				0, mapSemestres, intervenantRepository.findByNom("dal-pra"), blocCompetences);
		ue = uniteEnseignementRepository.save(new UniteEnseignement(null, "Gestion et Performance de solution d'infrastructure", blocCompetences));
		databaseService.createModule("RESE631", "Conception et optimisation d'une architecture réseaux", 18, 2, mapSemestres, intervenantRepository.findByNom("rombeaut"), ue);
		databaseService.createModule("RESE613", "Haute Disponibilité", 18, 2, mapSemestres, intervenantRepository.findByNom("deliessche"), ue);
		databaseService.createModule("RESE612", "Routage dynamique : env. et protocoles", 18, 2, mapSemestres, intervenantRepository.findByNom("deliessche"), ue);

		// ................................................
		// bloc compétences 7
		// ................................................

		blocCompetences = blocCompetencesRepository.save(new BlocCompetences(TypeBloc.DEV, parcours, 7, "Conception de Solutions applicatives"));
		ProjetTransversal kcreaDev = databaseService.createProjetTransversal("TPTE620", "Projet transversal Conception, développement et intégration d'une Solution Applicative - (Kcréa)", 40, 0,
				mapSemestres, intervenantRepository.findByNom("dal-pra"), blocCompetences);
		ue = uniteEnseignementRepository.save(new UniteEnseignement(null, "Conception et Développement S.I.", blocCompetences));
		databaseService.createModule("DEVE617", "Design Pattern (Java)", 18, 2, mapSemestres, intervenantRepository.findByNom("chinchole"), ue);
		databaseService.createModule("DEVE618", "Mapping Objet Relationnel (ORM/Java)", 18, 2, mapSemestres, intervenantRepository.findByNom("chinchole"), ue);
		databaseService.createModule("DEVE619", "Architecture logicielle", 18, 2, mapSemestres, intervenantRepository.findByNom("chinchole"), ue);

		// création du module mutualisé KCréa
		databaseService.createModuleMutualise("Projet transversal Conception, développement et intégration d'une Solution (Kcréa)", new ArrayList<Module>() {
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
		databaseService.createModule("SYSE634", "Sécurité des applications et services Web", 18, 2, mapSemestres, intervenantRepository.findByNom("deliessche"), ue);
		databaseService.createModule("SYSE635", "Piratage Ethique", 18, 2, mapSemestres, intervenantRepository.findByNom("deliessche"), ue);

		ue = uniteEnseignementRepository.save(new UniteEnseignement(TypeUE.VIRTU, "Virtualisation", parcours));
		databaseService.createModule("RESE608", "Stratégie & Outils de virtualisation : Docker et VMWare", 18, 2, mapSemestres, null, ue);
		databaseService.createModule("RESE614", "Scripting pour la Virtualisation", 18, 2, mapSemestres, null, ue);

		ue = uniteEnseignementRepository.save(new UniteEnseignement(TypeUE.DATA, "Données & Objets Connectés", parcours));
		databaseService.createModule("BDOE636", "Objets connectés et l'Internet des Objets (les fondamentaux)", 18, 2, mapSemestres, intervenantRepository.findByNom("chinchole"), ue);
		databaseService.createModule("SYSE637", "Prototypage d'un objet connecté", 18, 2, mapSemestres, intervenantRepository.findByNom("chinchole"), ue);

		// ------------------------------------------------
		// PARCOURS PROFESSIONNEL
		// ------------------------------------------------

		parcours = parcoursRepository.save(new Parcours(Parcours.TypeParcours.PROFESSIONNEL, false, formation));

		ue = uniteEnseignementRepository.save(new UniteEnseignement(null, "Ateliers Développement professionnel & Active learning", parcours));
		databaseService.createModule("xxxE601", "Atelier Apprendre à apprendre", 6, 0, mapSemestres, intervenantRepository.findByNom("frénéa"), ue);
		databaseService.createModule("xxxE602", "Développement professionnel : Portefeuille de compétences", 4, 0, mapSemestres, intervenantRepository.findByNom("frénéa"), ue);
		databaseService.createModule("DEPE638", "Active member / Participation Vie Ecole", 0, 0, mapSemestres, null, ue);

		ue = uniteEnseignementRepository.save(new UniteEnseignement(null, "Stage en entreprise", parcours));
		databaseService.createModule("TPRE632", "Rapport d'activité", 0, 0, mapSemestres, intervenantRepository.findByNom("lefetz"), ue);
		databaseService.createModule("TPRE633", "Suivi Mission en alternance & Soutenance Rapport d'activité", 0, 1, mapSemestres, intervenantRepository.findByNom("lefetz"), ue);

	}

	@Override
	public void initialiserCours() {

		// semaine 2018-16
		databaseService.createCours("16/04/2018 08:15", 4, "RESE631", CodeSalle.CONF);
		databaseService.createCours("16/04/2018 13:45", 4, "RESE631", CodeSalle.CONF);
		databaseService.createCours("17/04/2018 08:15", 4, "RESE631", CodeSalle.CONF);
		databaseService.createCours("17/04/2018 13:45", 4, "RESE631", CodeSalle.CONF);
		databaseService.createCours("18/04/2018 08:15", 4, "TPTE615", CodeSalle.CONF);
		databaseService.createCours("18/04/2018 13:45", 4, "LNGE628", CodeSalle.CONF);
		databaseService.createCours("19/04/2018 08:15", 2, "RESE631", CodeSalle.CONF);
		databaseService.createCours("19/04/2018 10:15", 2, "SYSE635", CodeSalle.CONF);
		databaseService.createCours("19/04/2018 13:45", 4, "PROE509", CodeSalle.CONF);
		databaseService.createCours("20/04/2018 08:15", 4, "PROE509", CodeSalle.CONF);
		databaseService.createCours("20/04/2018 13:45", 4, "PROE509", CodeSalle.CONF);

		// semaine 2018-17
		databaseService.createCours("23/04/2018 08:15", 4, "TQGE625", CodeSalle.CONF);
		databaseService.createCours("23/04/2018 13:45", 4, "TQGE625", CodeSalle.CONF);
		databaseService.createCours("24/04/2018 08:15", 4, "TQGE625", CodeSalle.CONF);
		databaseService.createCours("24/04/2018 13:45", 4, "TQGE625", CodeSalle.CONF);
		databaseService.createCours("25/04/2018 08:15", 4, "TQGE625", CodeSalle.CONF);
		databaseService.createCours("25/04/2018 13:45", 4, "TQGE625", CodeSalle.CONF);
		databaseService.createCours("26/04/2018 08:15", 4, "TQGE625", CodeSalle.CONF);
		databaseService.createCours("26/04/2018 13:45", 4, "TQGE625", CodeSalle.CONF);
		databaseService.createCours("27/04/2018 08:15", 4, "TQGE625", CodeSalle.CONF);
		databaseService.createCours("27/04/2018 13:45", 4, "TQGE625", CodeSalle.CONF);
	}

}
