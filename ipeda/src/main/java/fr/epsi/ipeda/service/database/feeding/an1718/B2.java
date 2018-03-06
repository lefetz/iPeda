package fr.epsi.ipeda.service.database.feeding.an1718;

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
import fr.epsi.ipeda.service.database.feeding.Feeding;
import fr.epsi.ipeda.dao.entity.UniteEnseignement;

@Component
public class B2 extends Feeding {

	@SuppressWarnings("serial")
	@Override
	public void initialiserFormation() {

		// ················································
		// Semestres
		// ················································

		mapSemestres = new HashMap<NumeroSemestre, Semestre>() {
			{
				put(NumeroSemestre.SEMESTRE1, new Semestre(NumeroSemestre.SEMESTRE1, LocalDate.of(2017, 9, 8), LocalDate.of(2018, 3, 2)));
				put(NumeroSemestre.SEMESTRE2, new Semestre(NumeroSemestre.SEMESTRE2, LocalDate.of(2018, 3, 5), LocalDate.of(2018, 7, 31)));
			}
		};

		// ················································
		// Formation
		// ················································

		formation = formationRepository.save(new Formation(TypeFormation.B2, "BACHELOR 2", mapSemestres.get(NumeroSemestre.SEMESTRE1), mapSemestres.get(NumeroSemestre.SEMESTRE2)));

		// ================================================
		// PARCOURS SOCLE
		// ================================================

		parcours = parcoursRepository.save(new Parcours(Parcours.TypeParcours.SOCLE, false, formation));

		// ················································
		// UE learning
		// ················································

		ue = uniteEnseignementRepository.save(new UniteEnseignement(null, "Learning by doing", parcours));
		databaseService.createModule("TPTE300", "Workshop 1 - Développement d'une application nomade", 20, 0, mapSemestres, intervenantRepository.findByNom("lefetz"), ue);
		databaseService.createModule("TPTE400", "Workshop 2 - Créativité & Innovation / TagCloud : Nomade - Humanisme - E-Santé", 40, 0, mapSemestres, intervenantRepository.findByNom("lefetz"), ue);

		// ················································
		// bloc compétences 1
		// ················································

		blocCompetences = blocCompetencesRepository.save(new BlocCompetences(parcours, 1, "Développement d'applications informatiques"));
		databaseService.createProjetTransversal("TPTE415", "Projet transversal Développement d'une application objet C#", 60, 0, mapSemestres, intervenantRepository.findByNom("lefetz"),
				blocCompetences);
		ue = uniteEnseignementRepository.save(new UniteEnseignement(null, "Développement d'applications Objet", blocCompetences));
		databaseService.createModule("DEVE316", "Langage C++", 18, 2, mapSemestres, intervenantRepository.findByNom("labis"), ue);
		databaseService.createModule("DEVE301", "Langage C#  - Les fondamentaux", 18, 2, mapSemestres, intervenantRepository.findByNom("decroix"), ue);
		databaseService.createModule("DEVE402", "Langage C#  - Tests Unitaires", 18, 2, mapSemestres, intervenantRepository.findByNom("decroix"), ue);

		ue = uniteEnseignementRepository.save(new UniteEnseignement(null, "UE Conception d'IHM web & nomade", blocCompetences));
		databaseService.createModule("DEVE318", "UX (User eXperience) Design", 18, 2, mapSemestres, intervenantRepository.findByNom("flasque"), ue);
		databaseService.createModule("DEVE303", "Langage JavaScript  - Interface Client Web", 18, 2, mapSemestres, intervenantRepository.findByNom("kaszer"), ue);
		databaseService.createModule("DEVE304", "Développement environnement mobile", 18, 2, mapSemestres, intervenantRepository.findByNom("lenglet"), ue);
		databaseService.createModule("DEVE405", "PHP Framework Symfony", 18, 2, mapSemestres, intervenantRepository.findByNom("fritsch"), ue);

		// ················································
		// bloc compétences 2
		// ················································

		blocCompetences = blocCompetencesRepository.save(new BlocCompetences(parcours, 2, "Administration Infrastructure Système & Réseau"));
		databaseService.createProjetTransversal("TPTE417", "Projet transversal Administration d'une infrastructure système & réseau", 40, 0, mapSemestres, intervenantRepository.findByNom("briki"),
				blocCompetences);
		ue = uniteEnseignementRepository.save(new UniteEnseignement(null, "Administration Réseaux", blocCompetences));
		databaseService.createModule("RESE306", "Adm. Poste Clients Gestion Accès", 18, 2, mapSemestres, intervenantRepository.findByNom("briki"), ue);
		databaseService.createModule("RESE407", "Administration Réseaux", 18, 2, mapSemestres, intervenantRepository.findByNom("briki"), ue);
		databaseService.createModule("RESE409", "Supervision", 18, 2, mapSemestres, intervenantRepository.findByNomAndPrenom("dubois", "julien"), ue);

		ue = uniteEnseignementRepository.save(new UniteEnseignement(null, "Systèmes", blocCompetences));
		databaseService.createModule("SYSE310", "Administration Linux (Shell)", 18, 2, mapSemestres, intervenantRepository.findByNom("briki"), ue);
		databaseService.createModule("SYSE311", "Services d' Infrastructure", 18, 2, mapSemestres, intervenantRepository.findByNom("briki"), ue);
		databaseService.createModule("SYSE412", "Active Directory", 18, 2, mapSemestres, intervenantRepository.findByNom("briki"), ue);

		// ················································
		// bloc compétences 3
		// ················································

		blocCompetences = blocCompetencesRepository.save(new BlocCompetences(parcours, 3, "Gestion des Données"));
		databaseService.createProjetTransversal("TPTE322", "Projet transvesal Conception, Exploitation et Administration d'une Base de Données sous SQL Server", 40, 0, mapSemestres,
				intervenantRepository.findByNom("le gales"), blocCompetences);
		ue = uniteEnseignementRepository.save(new UniteEnseignement(null, "Administration de Bases de données", blocCompetences));
		databaseService.createModule("BDOE308", "Administration sous SQL Server", 18, 2, mapSemestres, intervenantRepository.findByNom("hanot"), ue);

		// ················································
		// bloc compétences 4
		// ················································

		blocCompetences = blocCompetencesRepository.save(new BlocCompetences(parcours, 4, "Méthodes & Projet"));
		ue = uniteEnseignementRepository.save(new UniteEnseignement(null, "Projet S.I. & Recherche opérationnelle", blocCompetences));
		databaseService.createModule("PROE313", "UML  : Diagrammes Use Case et Séquences", 18, 2, mapSemestres, intervenantRepository.findByNom("lefetz"), ue);
		databaseService.createModule("PROE414", "Planification d'un Projet", 18, 2, mapSemestres, intervenantRepository.findByNom("alamasset"), ue);
		databaseService.createModule("TQGE421", "R.O. modélisation : Graphes et Ordonnancement", 18, 2, mapSemestres, intervenantRepository.findByNom("delanoy"), ue);

		ue = uniteEnseignementRepository.save(new UniteEnseignement(null, "Environnement Economique et Juridique", blocCompetences));
		databaseService.createModule("ECOE319", "Economie d'Entreprise", 18, 2, mapSemestres, intervenantRepository.findByNom("frénéa"), ue);
		databaseService.createModule("DRTE420", "Droit des Sociétés et du Numérique", 18, 2, mapSemestres, intervenantRepository.findByNom("frénéa"), ue);

		// ················································
		// bloc compétences 5
		// ················································

		blocCompetences = blocCompetencesRepository.save(new BlocCompetences(parcours, 5, "Communication"));
		ue = uniteEnseignementRepository.save(new UniteEnseignement(null, "Anglais", blocCompetences));
		databaseService.createModule("LNGE423", "Techn. rédactionnelles professionnelles", 18, 2, mapSemestres, intervenantRepository.findByNom("duplouich"), ue);
		databaseService.createModule("LNGE424", "Debate Technology", 18, 2, mapSemestres, intervenantRepository.findByNom("duplouich"), ue);

		ue = uniteEnseignementRepository.save(new UniteEnseignement(null, "Techniques d'Expression et Communication", blocCompetences));
		databaseService.createModule("COME426", "Développer une argumentation", 18, 2, mapSemestres, intervenantRepository.findByNom("boutonnet"), ue);
		databaseService.createModule("COME325", "Projet Voltaire", 18, 2, mapSemestres, intervenantRepository.findByNom("boutonnet"), ue);

		// ================================================
		// PARCOURS PROFESSIONNEL
		// ================================================

		parcours = parcoursRepository.save(new Parcours(Parcours.TypeParcours.PROFESSIONNEL, false, formation));

		ue = uniteEnseignementRepository.save(new UniteEnseignement(null, "Ateliers Développement professionnel & Active learning", parcours));
		databaseService.createModule("DEPE428", "Développement professionnel : Portefeuille de compétences", 0, 0, mapSemestres, intervenantRepository.findByNom("frénéa"), ue);
		databaseService.createModule("DEPE435", "Active member / Participation Vie Ecole", 0, 0, mapSemestres, intervenantRepository.findByNom("lefetz"), ue);

		ue = uniteEnseignementRepository.save(new UniteEnseignement(null, "Stage en entreprise", blocCompetences));
		databaseService.createModule("TPRE429", "Rapport d'activité", 0, 0, mapSemestres, intervenantRepository.findByNom("lefetz"), ue);
		databaseService.createModule("TPRE430", "Soutenance Rapport d'activité", 1, 0, mapSemestres, intervenantRepository.findByNom("lefetz"), ue);

		// ================================================
		// PARCOURS BTS SIO
		// ================================================

		parcours = parcoursRepository.save(new Parcours(Parcours.TypeParcours.BTS_SIO, true, formation));

		ue = uniteEnseignementRepository.save(new UniteEnseignement(null, "Préparation Epreuves écrites", parcours));
		databaseService.createModule("BTSE336", "Environnement managérial, économique et juridique des services informatiques", 10, 0, mapSemestres, intervenantRepository.findByNom("frénéa"), ue);
		databaseService.createModule("BTSE436", "Environnement managérial, économique et juridique des services informatiques", 10, 0, mapSemestres, intervenantRepository.findByNom("frénéa"), ue);
		databaseService.createModule("BTSE337", "Culture générale et expression", 10, 0, mapSemestres, intervenantRepository.findByNom("boutonnet"), ue);
		databaseService.createModule("BTSE437", "Culture générale et expression", 10, 0, mapSemestres, intervenantRepository.findByNom("boutonnet"), ue);
		databaseService.createModule("BTSE338", "Préparation Epreuves SIO : Algo/math  +  Epreuve E6 + TEC (BTS Blanc)", 10, 0, mapSemestres, intervenantRepository.findByNom("maati"), ue);
		databaseService.createModule("BTSE438", "Préparation Epreuves SIO : Algo/math  +  Epreuve E6 + TEC (BTS Blanc)", 10, 0, mapSemestres, intervenantRepository.findByNom("maati"), ue);

		ue = uniteEnseignementRepository.save(new UniteEnseignement(null, "Méthodes & Techniques informatiques", parcours));

		// modules composés
		Module moduleParent, moduleEnfant1, moduleEnfant2 = null;

		moduleParent = databaseService.createModule("BTSE339", "Projet PPE 3", 0, 0, mapSemestres, null, ue);
		moduleEnfant1 = databaseService.createModule(moduleParent, "Projet PPE 3 (dev)", 10, 0, intervenantRepository.findByNom("le gales"));
		moduleEnfant2 = databaseService.createModule(moduleParent, "Projet PPE 3 (réseau)", 10, 0, intervenantRepository.findByNom("dudek"));
		databaseService.createModuleCompose(moduleParent, moduleEnfant1, moduleEnfant2);

		moduleParent = databaseService.createModule("BTSE440", "Projet PPE 4", 0, 0, mapSemestres, null, ue);
		moduleEnfant1 = databaseService.createModule(moduleParent, "Projet PPE 4 (dev)", 20, 0, intervenantRepository.findByNom("le gales"));
		moduleEnfant2 = databaseService.createModule(moduleParent, "Projet PPE 4 (réseau)", 20, 0, intervenantRepository.findByNom("dudek"));
		databaseService.createModuleCompose(moduleParent, moduleEnfant1, moduleEnfant2);

	}

	@Override
	public void initialiserCours() {

		// // semaine 2018-16
		// createCours("16/04/2018 08:15", 4, "RESE631", CodeSalle.CONF);
		// createCours("16/04/2018 13:45", 4, "RESE631", CodeSalle.CONF);
		// createCours("17/04/2018 08:15", 4, "RESE631", CodeSalle.CONF);
		// createCours("17/04/2018 13:45", 4, "RESE631", CodeSalle.CONF);
		// createCours("18/04/2018 08:15", 4, "TPTE615", CodeSalle.CONF);
		// createCours("18/04/2018 13:45", 4, "LNGE628", CodeSalle.CONF);
		// createCours("19/04/2018 08:15", 2, "RESE631", CodeSalle.CONF);
		// createCours("19/04/2018 10:15", 2, "SYSE635", CodeSalle.CONF);
		// createCours("19/04/2018 13:45", 4, "PROE509", CodeSalle.CONF);
		// createCours("20/04/2018 08:15", 4, "PROE509", CodeSalle.CONF);
		// createCours("20/04/2018 13:45", 4, "PROE509", CodeSalle.CONF);
		//
		// // semaine 2018-17
		// createCours("23/04/2018 08:15", 4, "TQGE625", CodeSalle.CONF);
		// createCours("23/04/2018 13:45", 4, "TQGE625", CodeSalle.CONF);
		// createCours("24/04/2018 08:15", 4, "TQGE625", CodeSalle.CONF);
		// createCours("24/04/2018 13:45", 4, "TQGE625", CodeSalle.CONF);
		// createCours("25/04/2018 08:15", 4, "TQGE625", CodeSalle.CONF);
		// createCours("25/04/2018 13:45", 4, "TQGE625", CodeSalle.CONF);
		// createCours("26/04/2018 08:15", 4, "TQGE625", CodeSalle.CONF);
		// createCours("26/04/2018 13:45", 4, "TQGE625", CodeSalle.CONF);
		// createCours("27/04/2018 08:15", 4, "TQGE625", CodeSalle.CONF);
		// createCours("27/04/2018 13:45", 4, "TQGE625", CodeSalle.CONF);
	}

}