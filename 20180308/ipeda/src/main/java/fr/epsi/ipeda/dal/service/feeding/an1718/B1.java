package fr.epsi.ipeda.dal.service.feeding.an1718;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import fr.epsi.ipeda.dal.entity.AnneeScolaire;
import fr.epsi.ipeda.dal.entity.BlocCompetences;
import fr.epsi.ipeda.dal.entity.Formation;
import fr.epsi.ipeda.dal.entity.Module;
import fr.epsi.ipeda.dal.entity.Parcours;
import fr.epsi.ipeda.dal.entity.Planning;
import fr.epsi.ipeda.dal.entity.UniteEnseignement;
import fr.epsi.ipeda.dal.entity.periode.Periode;
import fr.epsi.ipeda.dal.entity.periode.PeriodeType;
import fr.epsi.ipeda.dal.service.feeding.Feeding;

@Component
public class B1 extends Feeding {

	@Override
	public void initialiserFormation() {

		// ················································
		// Semestres
		// ················································
		//
		// mapSemestres = new HashMap<NumeroSemestre, Semestre>() {
		// {
		// put(NumeroSemestre.SEMESTRE1, new Semestre(NumeroSemestre.SEMESTRE1, LocalDate.of(2017, 9, 22), LocalDate.of(2018, 1, 5)));
		// put(NumeroSemestre.SEMESTRE2, new Semestre(NumeroSemestre.SEMESTRE2, LocalDate.of(2018, 1, 8), LocalDate.of(2018, 7, 31)));
		// }
		// };

		// ················································
		// Formation
		// ················································

		// formation = formationRepository.save(new Formation("BACHELOR 1", mapSemestres.get(NumeroSemestre.SEMESTRE1), mapSemestres.get(NumeroSemestre.SEMESTRE2)));

		AnneeScolaire anneeScolaire = anneeScolaireRepository.findByDateDebut(LocalDate.of(2018, 9, 1));
		LocalDate dateFinSemestre1 = LocalDate.of(2019, 2, 1);

		formation = formationRepository.save(new Formation("BACHELOR 1 18/19", anneeScolaire, dateFinSemestre1));

		// ================================================
		// PARCOURS SOCLE
		// ================================================

		// parcours = parcoursRepository.save(new Parcours(Parcours.TypeParcours.SOCLE, false, formation));
		parcours = parcoursRepository.save(new Parcours(formation, "SOCLE DEVOPS"));

		// ················································
		// UE learning
		// ················································

		ue = uniteEnseignementRepository.save(new UniteEnseignement("Learning by doing", parcours));
		databaseService.createModule("TPTE100", "Workshop 1 - Création Gaming", 10, 0, intervenantRepository.findByNom("lefetz"), ue);
		databaseService.createModule("TPTE136", "Atelier HEP OnBoarding", 0, 8, intervenantRepository.findByNom("lefetz"), ue);
		databaseService.createModule("TPTE200", "Workshop 2 - Créativité & Innovation / TagCloud : Humanisme - Citoyenneté - Culture", 10, 0, intervenantRepository.findByNom("lefetz"), ue);

		// ················································
		// bloc compétences 1
		// ················································

		blocCompetences = blocCompetencesRepository.save(new BlocCompetences(parcours, 1, "Développement d'applications informatiques"));
		databaseService.createProjetTransversal("TPTE215", "Projet transversal Développement d'une application objet : Utilisation de NAO", 40, 0,
				intervenantRepository.findByNomAndPrenom("gaber", "khalid"), blocCompetences);
		ue = uniteEnseignementRepository.save(new UniteEnseignement("Apprendre à développer", blocCompetences));
		databaseService.createModule("DEVE101", "Algorithmique (notions de base)", 18, 2, intervenantRepository.findByNom("labis"), ue);
		databaseService.createModule("DEVE102", "Algorithmique et langage C", 18, 2, intervenantRepository.findByNom("labis"), ue);
		databaseService.createModule("DEVE203", "Concepts Objet / Langage C++", 18, 2, intervenantRepository.findByNom("labis"), ue);
		databaseService.createModule("DEVE206", "Langage C++ (Les Fondamentaux)", 18, 2, intervenantRepository.findByNom("lefetz"), ue);

		databaseService.createProjetTransversal("TPTE120", "Projet transversal Développement d'une application Web", 54, 0, intervenantRepository.findByNom("le gales"), blocCompetences);
		ue = uniteEnseignementRepository.save(new UniteEnseignement("Développement d'applications web", blocCompetences));
		databaseService.createModule("DEVE104", "HTML5 / CSS3", 18, 2, intervenantRepository.findByNom("lefetz"), ue);
		databaseService.createModule("DEVE105", "PHP et MySQL", 18, 2, intervenantRepository.findByNom("sintive"), ue);

		// ················································
		// bloc compétences 2
		// ················································

		blocCompetences = blocCompetencesRepository.save(new BlocCompetences(parcours, 2, "Administration Infrastructure Système & Réseau"));
		databaseService.createProjetTransversal("TPTE121", "Projet transversal Installation d'une Infrastructure Système sous l'environnement linux ou windows (au choix)", 40, 0,
				intervenantRepository.findByNom("briki"), blocCompetences);
		databaseService.createProjetTransversal("TPTE222", "Projet transversal Installation d'une Infrastructure Réseau", 40, 0, intervenantRepository.findByNom("dudek"), blocCompetences);
		ue = uniteEnseignementRepository.save(new UniteEnseignement("Infrastructure Systèmes et Réseaux", blocCompetences));
		databaseService.createModule("SYSE108", "Env. Windows : Installation et configuration Poste Travail", 18, 2, intervenantRepository.findByNom("briki"), ue);
		databaseService.createModule("SYSE109", "Env. Linux : Installation et configuration Poste Travail", 18, 2, intervenantRepository.findByNom("briki"), ue);
		databaseService.createModule("RESE107", "Reseau ethernet", 18, 2, intervenantRepository.findByNom("briki"), ue);
		databaseService.createModule("RESE210", "Administration réseau Notions (IPV4 -IPV6 - firewalling)", 18, 2, intervenantRepository.findByNomAndPrenom("dubois", "julien"), ue);

		// ················································
		// bloc compétences 3
		// ················································

		blocCompetences = blocCompetencesRepository.save(new BlocCompetences(parcours, 3, "Gestion des Données"));
		ue = uniteEnseignementRepository.save(new UniteEnseignement("Bases de Données", blocCompetences));
		databaseService.createModule("BDOE111", "Exploitation d'une BD : Langage SQL sous SQL Server", 18, 2, intervenantRepository.findByNomAndPrenom("gaber", "khalid"), ue);
		databaseService.createModule("BDOE212", "Conception d'une BD : Langage SQL sous SQL Server", 18, 2, intervenantRepository.findByNomAndPrenom("gaber", "khalid"), ue);
		databaseService.createModule("BDOE213", "Modélisation d'une BD : Merise", 18, 2, intervenantRepository.findByNomAndPrenom("gaber", "khalid"), ue);
		databaseService.createModule("BDOE214", "Modéliser avec le Langage UML / Diagr. Classes", 18, 2, intervenantRepository.findByNom("lefetz"), ue);

		// ················································
		// bloc compétences 4
		// ················································

		blocCompetences = blocCompetencesRepository.save(new BlocCompetences(parcours, 4, "Méthodes & Projet"));
		ue = uniteEnseignementRepository.save(new UniteEnseignement("Environnement  Juridique & Financier", blocCompetences));
		databaseService.createModule("ECOE116", "Outils de gestion d'une entreprise / Les fondamentaux", 18, 2, intervenantRepository.findByNom("frénéa"), ue);
		databaseService.createModule("DRTE217", "Droit du travail", 18, 2, intervenantRepository.findByNom("vassiliou"), ue);

		ue = uniteEnseignementRepository.save(new UniteEnseignement("Mathématiques pour l'informatique", blocCompetences));
		databaseService.createModule("TQGE218", "Logique, Ensembles, Calculs Booléens", 18, 2, intervenantRepository.findByNom("delanoy"), ue);
		databaseService.createModule("TQGE219", "Suites et Etudes de Fonction", 18, 2, intervenantRepository.findByNom("delanoy"), ue);

		// ················································
		// bloc compétences 5
		// ················································

		blocCompetences = blocCompetencesRepository.save(new BlocCompetences(parcours, 5, "Communication"));
		ue = uniteEnseignementRepository.save(new UniteEnseignement("Anglais", blocCompetences));
		databaseService.createModule("LNGE223", "Méthodologie / compréhension orale et écrite", 18, 2, intervenantRepository.findByNom("duplouich"), ue);
		databaseService.createModule("LNGE224", "Presse informatique professionnelle", 18, 2, intervenantRepository.findByNom("duplouich"), ue);

		ue = uniteEnseignementRepository.save(new UniteEnseignement("Communication & Techniques d'expression", blocCompetences));
		databaseService.createModule("COME225", "Outils de la communication écrite : Structurer son écrit", 18, 2, intervenantRepository.findByNom("boutonnet"), ue);
		databaseService.createModule("COME226", "Communication orale : Exposés thématiques", 18, 2, intervenantRepository.findByNom("boutonnet"), ue);

		// ================================================
		// PARCOURS PROFESSIONNEL
		// ================================================

		parcours = parcoursRepository.save(new Parcours(formation, "PROFESSIONNEL"));

		ue = uniteEnseignementRepository.save(new UniteEnseignement("Ateliers Développement professionnel & Active learning", parcours));
		databaseService.createModule("DEPE127", "Atelier Apprendre à apprendre", 4, 0, intervenantRepository.findByNom("frénéa"), ue);
		databaseService.createModule("DEPE228", "Développement professionnel : Portefeuille de compétences", 4, 0, intervenantRepository.findByNom("frénéa"), ue);
		databaseService.createModule("DEPE235", "Active member / Participation Vie Ecole", 0, 0, intervenantRepository.findByNom("lefetz"), ue);

		ue = uniteEnseignementRepository.save(new UniteEnseignement("Stage en entreprise", blocCompetences));
		databaseService.createModule("TPRE429", "Rapport d'activité", 0, 0, intervenantRepository.findByNom("lefetz"), ue);
		databaseService.createModule("TPRE430", "Soutenance Rapport d'activité", 1, 0, intervenantRepository.findByNom("lefetz"), ue);

		// ================================================
		// PARCOURS BTS SIO
		// ================================================

		parcours = parcoursRepository.save(new Parcours(formation, "BTS SIO", specificiteRepository.findByLibelle("BTS")));

		ue = uniteEnseignementRepository.save(new UniteEnseignement("Préparation Epreuves écrites", parcours));

		databaseService.createModule("BTSE131", "Environnement juridique des services informatiques", 10, 0, intervenantRepository.findByNom("frénéa"), ue);
		databaseService.createModule("BTSE231", "Environnement juridique des services informatiques", 10, 0, intervenantRepository.findByNom("frénéa"), ue);
		databaseService.createModule("BTSE132", "Environnement managériale et économique des services informatiques", 10, 0, intervenantRepository.findByNom("frénéa"), ue);
		databaseService.createModule("BTSE232", "Environnement managériale et économique des services informatiques", 10, 0, intervenantRepository.findByNom("frénéa"), ue);

		ue = uniteEnseignementRepository.save(new UniteEnseignement("Méthodes & Techniques informatiques", parcours));

		// modules composés
		Module moduleParent, moduleEnfant1, moduleEnfant2 = null;

		moduleParent = databaseService.createModule("BTSE134", "Projet PPE 1", 0, 0, null, ue);
		moduleEnfant1 = databaseService.createModule(moduleParent, "Projet PPE 1 (dev)", 10, 0, intervenantRepository.findByNom("le gales"));
		moduleEnfant2 = databaseService.createModule(moduleParent, "Projet PPE 1 (réseau)", 10, 0, intervenantRepository.findByNom("dudek"));
		databaseService.createModuleCompose(moduleParent, moduleEnfant1, moduleEnfant2);

		moduleParent = databaseService.createModule("BTSE235", "Projet PPE 2", 0, 0, null, ue);
		moduleEnfant1 = databaseService.createModule(moduleParent, "Projet PPE 2 (dev)", 20, 0, intervenantRepository.findByNom("le gales"));
		moduleEnfant2 = databaseService.createModule(moduleParent, "Projet PPE 2 (réseau)", 20, 0, intervenantRepository.findByNom("dudek"));
		databaseService.createModuleCompose(moduleParent, moduleEnfant1, moduleEnfant2);

	}

	@Override
	public void initialiserCours() {
		// TODO Auto-generated method stub

	}

	public void initialiserPlanif() {

		AnneeScolaire anneeScolaire = anneeScolaireRepository.findByDateDebut(LocalDate.of(2018, 9, 1));

		formation = formationRepository.findByLibelleContainingAndAnneeScolaire("BACHELOR 1 18/19", anneeScolaire);

		Planning planning = new Planning(formation);

		// périodes
		PeriodeType periodeType = periodeTypeRepository.findByLibelle("JOUR_FERIE");
		planning.addPeriode(new Periode(periodeType, LocalDate.of(2018, 11, 1)));
		planning.addPeriode(new Periode(periodeType, LocalDate.of(2018, 12, 25)));
		planning.addPeriode(new Periode(periodeType, LocalDate.of(2019, 1, 1)));
		planning.addPeriode(new Periode(periodeType, LocalDate.of(2019, 4, 22)));
		planning.addPeriode(new Periode(periodeType, LocalDate.of(2019, 5, 1)));
		planning.addPeriode(new Periode(periodeType, LocalDate.of(2019, 5, 8)));
		planning.addPeriode(new Periode(periodeType, LocalDate.of(2019, 4, 30)));
		planning.addPeriode(new Periode(periodeType, LocalDate.of(2019, 6, 10)));
		planning.addPeriode(new Periode(periodeType, LocalDate.of(2019, 8, 15)));

		periodeType = periodeTypeRepository.findByLibelle("VACANCES");
		planning.addPeriode(new Periode(periodeType, LocalDate.of(2018, 10, 22), LocalDate.of(2018, 11, 2)));
		planning.addPeriode(new Periode(periodeType, LocalDate.of(2019, 2, 11), LocalDate.of(2019, 2, 22)));
		planning.addPeriode(new Periode(periodeType, LocalDate.of(2019, 4, 8), LocalDate.of(2019, 4, 19)));

		periodeType = periodeTypeRepository.findByLibelle("FERMETURE");
		planning.addPeriode(new Periode(periodeType, LocalDate.of(2018, 12, 24), LocalDate.of(2019, 1, 4)));
		planning.addPeriode(new Periode(periodeType, LocalDate.of(2019, 8, 1), LocalDate.of(2019, 8, 31)));

		periodeType = periodeTypeRepository.findByLibelle("RATTRAPAGES");
		planning.addPeriode(new Periode(periodeType, LocalDate.of(2019, 1, 7), LocalDate.of(2019, 1, 8)));
		planning.addPeriode(new Periode(periodeType, LocalDate.of(2019, 7, 8), LocalDate.of(2019, 7, 9)));

		periodeType = periodeTypeRepository.findByLibelle("SALON_ETUDIANT");
		planning.addPeriode(new Periode(periodeType, LocalDate.of(2019, 1, 10), LocalDate.of(2019, 1, 11)));

		periodeType = periodeTypeRepository.findByLibelle("NON_PLANIFIABLE");
		planning.addPeriode(new Periode(periodeType, LocalDate.of(2019, 1, 14), LocalDate.of(2019, 1, 18)));
		planning.addPeriode(new Periode(periodeType, LocalDate.of(2019, 3, 18), LocalDate.of(2019, 3, 22)));
		planning.addPeriode(new Periode(periodeType, LocalDate.of(2019, 6, 17), LocalDate.of(2019, 6, 21)));
		planning.addPeriode(new Periode(periodeType, LocalDate.of(2019, 7, 10), LocalDate.of(2019, 7, 31)));

		periodeType = periodeTypeRepository.findByLibelle("STAGE");
		planning.addPeriode(new Periode(periodeType, LocalDate.of(2019, 4, 23), LocalDate.of(2019, 6, 14)));

		periodeType = periodeTypeRepository.findByLibelle("SOUTENANCES");
		planning.addPeriode(new Periode(periodeType, LocalDate.of(2019, 6, 24), LocalDate.of(2019, 6, 25)));

		periodeRepository.save(planning.getListPeriode());

		planning = planningRepository.save(new Planning(formation));

		formation.setPlanning(planning);
		formationRepository.save(formation);

	}

}
