package fr.epsi.ipeda.service.database.feeding;

import java.time.LocalDate;
import java.util.HashMap;

import org.springframework.stereotype.Component;

import fr.epsi.ipeda.dao.entity.BlocCompetences;
import fr.epsi.ipeda.dao.entity.Formation;
import fr.epsi.ipeda.dao.entity.Formation.TypeFormation;
import fr.epsi.ipeda.dao.entity.Module;
import fr.epsi.ipeda.dao.entity.Parcours;
import fr.epsi.ipeda.dao.entity.Semestre;
import fr.epsi.ipeda.dao.entity.Semestre.NumeroSemestre;
import fr.epsi.ipeda.dao.entity.UniteEnseignement;

@Component
public class B1 extends Feeding {

	@SuppressWarnings("serial")
	@Override
	public void initialiserFormation() {

		// ················································
		// Semestres
		// ················································

		mapSemestres = new HashMap<NumeroSemestre, Semestre>() {
			{
				put(NumeroSemestre.SEMESTRE1, new Semestre(NumeroSemestre.SEMESTRE1, LocalDate.of(2017, 9, 22), LocalDate.of(2018, 1, 5)));
				put(NumeroSemestre.SEMESTRE2, new Semestre(NumeroSemestre.SEMESTRE2, LocalDate.of(2018, 1, 8), LocalDate.of(2018, 7, 31)));
			}
		};

		// ················································
		// Formation
		// ················································

		formation = formationRepository.save(new Formation(TypeFormation.B1, "BACHELOR 1", mapSemestres.get(NumeroSemestre.SEMESTRE1), mapSemestres.get(NumeroSemestre.SEMESTRE2)));

		// ================================================
		// PARCOURS SOCLE
		// ================================================

		parcours = parcoursRepository.save(new Parcours(Parcours.TypeParcours.SOCLE, false, formation));

		// ················································
		// UE learning
		// ················································

		ue = uniteEnseignementRepository.save(new UniteEnseignement(null, "Learning by doing", parcours));
		databaseService.createModule("TPTE100", "Workshop 1 - Création Gaming", 10, 0, mapSemestres, intervenantRepository.findByNom("lefetz"), ue);
		databaseService.createModule("TPTE136", "Atelier HEP OnBoarding", 0, 8, mapSemestres, intervenantRepository.findByNom("lefetz"), ue);
		databaseService.createModule("TPTE200", "Workshop 2 - Créativité & Innovation / TagCloud : Humanisme - Citoyenneté - Culture", 10, 0, mapSemestres, intervenantRepository.findByNom("lefetz"),
				ue);

		// ················································
		// bloc compétences 1
		// ················································

		blocCompetences = blocCompetencesRepository.save(new BlocCompetences(parcours, 1, "Développement d'applications informatiques"));
		databaseService.createProjetTransversal("TPTE215", "Projet transversal Développement d'une application objet : Utilisation de NAO", 40, 0, mapSemestres,
				intervenantRepository.findByNomAndPrenom("gaber", "khalid"), blocCompetences);
		ue = uniteEnseignementRepository.save(new UniteEnseignement(null, "Apprendre à développer", blocCompetences));
		databaseService.createModule("DEVE101", "Algorithmique (notions de base)", 18, 2, mapSemestres, intervenantRepository.findByNom("labis"), ue);
		databaseService.createModule("DEVE102", "Algorithmique et langage C", 18, 2, mapSemestres, intervenantRepository.findByNom("labis"), ue);
		databaseService.createModule("DEVE203", "Concepts Objet / Langage C++", 18, 2, mapSemestres, intervenantRepository.findByNom("labis"), ue);
		databaseService.createModule("DEVE206", "Langage C++ (Les Fondamentaux)", 18, 2, mapSemestres, intervenantRepository.findByNom("lefetz"), ue);

		databaseService.createProjetTransversal("TPTE120", "Projet transversal Développement d'une application Web", 54, 0, mapSemestres, intervenantRepository.findByNom("le gales"), blocCompetences);
		ue = uniteEnseignementRepository.save(new UniteEnseignement(null, "Développement d'applications web", blocCompetences));
		databaseService.createModule("DEVE104", "HTML5 / CSS3", 18, 2, mapSemestres, intervenantRepository.findByNom("lefetz"), ue);
		databaseService.createModule("DEVE105", "PHP et MySQL", 18, 2, mapSemestres, intervenantRepository.findByNom("sintive"), ue);

		// ················································
		// bloc compétences 2
		// ················································

		blocCompetences = blocCompetencesRepository.save(new BlocCompetences(parcours, 2, "Administration Infrastructure Système & Réseau"));
		databaseService.createProjetTransversal("TPTE121", "Projet transversal Installation d'une Infrastructure Système sous l'environnement linux ou windows (au choix)", 40, 0, mapSemestres,
				intervenantRepository.findByNom("briki"), blocCompetences);
		databaseService.createProjetTransversal("TPTE222", "Projet transversal Installation d'une Infrastructure Réseau", 40, 0, mapSemestres, intervenantRepository.findByNom("dudek"),
				blocCompetences);
		ue = uniteEnseignementRepository.save(new UniteEnseignement(null, "Infrastructure Systèmes et Réseaux", blocCompetences));
		databaseService.createModule("SYSE108", "Env. Windows : Installation et configuration Poste Travail", 18, 2, mapSemestres, intervenantRepository.findByNom("briki"), ue);
		databaseService.createModule("SYSE109", "Env. Linux : Installation et configuration Poste Travail", 18, 2, mapSemestres, intervenantRepository.findByNom("briki"), ue);
		databaseService.createModule("RESE107", "Reseau ethernet", 18, 2, mapSemestres, intervenantRepository.findByNom("briki"), ue);
		databaseService.createModule("RESE210", "Administration réseau Notions (IPV4 -IPV6 - firewalling)", 18, 2, mapSemestres, intervenantRepository.findByNomAndPrenom("dubois", "julien"), ue);

		// ················································
		// bloc compétences 3
		// ················································

		blocCompetences = blocCompetencesRepository.save(new BlocCompetences(parcours, 3, "Gestion des Données"));
		ue = uniteEnseignementRepository.save(new UniteEnseignement(null, "Bases de Données", blocCompetences));
		databaseService.createModule("BDOE111", "Exploitation d'une BD : Langage SQL sous SQL Server", 18, 2, mapSemestres, intervenantRepository.findByNomAndPrenom("gaber", "khalid"), ue);
		databaseService.createModule("BDOE212", "Conception d'une BD : Langage SQL sous SQL Server", 18, 2, mapSemestres, intervenantRepository.findByNomAndPrenom("gaber", "khalid"), ue);
		databaseService.createModule("BDOE213", "Modélisation d'une BD : Merise", 18, 2, mapSemestres, intervenantRepository.findByNomAndPrenom("gaber", "khalid"), ue);
		databaseService.createModule("BDOE214", "Modéliser avec le Langage UML / Diagr. Classes", 18, 2, mapSemestres, intervenantRepository.findByNom("lefetz"), ue);

		// ················································
		// bloc compétences 4
		// ················································

		blocCompetences = blocCompetencesRepository.save(new BlocCompetences(parcours, 4, "Méthodes & Projet"));
		ue = uniteEnseignementRepository.save(new UniteEnseignement(null, "Environnement  Juridique & Financier", blocCompetences));
		databaseService.createModule("ECOE116", "Outils de gestion d'une entreprise / Les fondamentaux", 18, 2, mapSemestres, intervenantRepository.findByNom("frénéa"), ue);
		databaseService.createModule("DRTE217", "Droit du travail", 18, 2, mapSemestres, intervenantRepository.findByNom("vassiliou"), ue);

		ue = uniteEnseignementRepository.save(new UniteEnseignement(null, "Mathématiques pour l'informatique", blocCompetences));
		databaseService.createModule("TQGE218", "Logique, Ensembles, Calculs Booléens", 18, 2, mapSemestres, intervenantRepository.findByNom("delanoy"), ue);
		databaseService.createModule("TQGE219", "Suites et Etudes de Fonction", 18, 2, mapSemestres, intervenantRepository.findByNom("delanoy"), ue);

		// ················································
		// bloc compétences 5
		// ················································

		blocCompetences = blocCompetencesRepository.save(new BlocCompetences(parcours, 5, "Communication"));
		ue = uniteEnseignementRepository.save(new UniteEnseignement(null, "Anglais", blocCompetences));
		databaseService.createModule("LNGE223", "Méthodologie / compréhension orale et écrite", 18, 2, mapSemestres, intervenantRepository.findByNom("duplouich"), ue);
		databaseService.createModule("LNGE224", "Presse informatique professionnelle", 18, 2, mapSemestres, intervenantRepository.findByNom("duplouich"), ue);

		ue = uniteEnseignementRepository.save(new UniteEnseignement(null, "Communication & Techniques d'expression", blocCompetences));
		databaseService.createModule("COME225", "Outils de la communication écrite : Structurer son écrit", 18, 2, mapSemestres, intervenantRepository.findByNom("boutonnet"), ue);
		databaseService.createModule("COME226", "Communication orale : Exposés thématiques", 18, 2, mapSemestres, intervenantRepository.findByNom("boutonnet"), ue);

		// ================================================
		// PARCOURS PROFESSIONNEL
		// ================================================

		parcours = parcoursRepository.save(new Parcours(Parcours.TypeParcours.PROFESSIONNEL, false, formation));

		ue = uniteEnseignementRepository.save(new UniteEnseignement(null, "Ateliers Développement professionnel & Active learning", parcours));
		databaseService.createModule("DEPE127", "Atelier Apprendre à apprendre", 4, 0, mapSemestres, intervenantRepository.findByNom("frénéa"), ue);
		databaseService.createModule("DEPE228", "Développement professionnel : Portefeuille de compétences", 4, 0, mapSemestres, intervenantRepository.findByNom("frénéa"), ue);
		databaseService.createModule("DEPE235", "Active member / Participation Vie Ecole", 0, 0, mapSemestres, intervenantRepository.findByNom("lefetz"), ue);

		ue = uniteEnseignementRepository.save(new UniteEnseignement(null, "Stage en entreprise", blocCompetences));
		databaseService.createModule("TPRE429", "Rapport d'activité", 0, 0, mapSemestres, intervenantRepository.findByNom("lefetz"), ue);
		databaseService.createModule("TPRE430", "Soutenance Rapport d'activité", 1, 0, mapSemestres, intervenantRepository.findByNom("lefetz"), ue);

		// ================================================
		// PARCOURS BTS SIO
		// ================================================

		parcours = parcoursRepository.save(new Parcours(Parcours.TypeParcours.BTS_SIO, true, formation));

		ue = uniteEnseignementRepository.save(new UniteEnseignement(null, "Préparation Epreuves écrites", parcours));
		databaseService.createModule("BTSE131", "Environnement juridique des services informatiques", 10, 0, mapSemestres, intervenantRepository.findByNom("frénéa"), ue);
		databaseService.createModule("BTSE231", "Environnement juridique des services informatiques", 10, 0, mapSemestres, intervenantRepository.findByNom("frénéa"), ue);
		databaseService.createModule("BTSE132", "Environnement managériale et économique des services informatiques", 10, 0, mapSemestres, intervenantRepository.findByNom("frénéa"), ue);
		databaseService.createModule("BTSE232", "Environnement managériale et économique des services informatiques", 10, 0, mapSemestres, intervenantRepository.findByNom("frénéa"), ue);

		ue = uniteEnseignementRepository.save(new UniteEnseignement(null, "Méthodes & Techniques informatiques", parcours));

		// modules composés
		Module moduleParent, moduleEnfant1, moduleEnfant2 = null;

		moduleParent = databaseService.createModule("BTSE134", "Projet PPE 1", 0, 0, mapSemestres, null, ue);
		moduleEnfant1 = databaseService.createModule(moduleParent, "Projet PPE 1 (dev)", 10, 0, intervenantRepository.findByNom("le gales"));
		moduleEnfant2 = databaseService.createModule(moduleParent, "Projet PPE 1 (réseau)", 10, 0, intervenantRepository.findByNom("dudek"));
		databaseService.createModuleCompose(moduleParent, moduleEnfant1, moduleEnfant2);

		moduleParent = databaseService.createModule("BTSE235", "Projet PPE 2", 0, 0, mapSemestres, null, ue);
		moduleEnfant1 = databaseService.createModule(moduleParent, "Projet PPE 2 (dev)", 20, 0, intervenantRepository.findByNom("le gales"));
		moduleEnfant2 = databaseService.createModule(moduleParent, "Projet PPE 2 (réseau)", 20, 0, intervenantRepository.findByNom("dudek"));
		databaseService.createModuleCompose(moduleParent, moduleEnfant1, moduleEnfant2);

	}

	@Override
	public void initialiserCours() {
		// TODO Auto-generated method stub

	}

}
