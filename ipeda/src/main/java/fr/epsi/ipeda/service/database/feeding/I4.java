package fr.epsi.ipeda.service.database.feeding;

import java.time.LocalDate;
import java.util.HashMap;

import org.springframework.stereotype.Component;

import fr.epsi.ipeda.dao.entity.BlocCompetences;
import fr.epsi.ipeda.dao.entity.Formation;
import fr.epsi.ipeda.dao.entity.Formation.TypeFormation;
import fr.epsi.ipeda.dao.entity.Parcours;
import fr.epsi.ipeda.dao.entity.Semestre;
import fr.epsi.ipeda.dao.entity.Semestre.NumeroSemestre;
import fr.epsi.ipeda.dao.entity.UniteEnseignement;
import fr.epsi.ipeda.dao.entity.UniteEnseignement.TypeUE;

@Component
public class I4 extends Feeding {

	@SuppressWarnings("serial")
	@Override
	public void initialiserFormation() {

		// ················································
		// Semestres
		// ················································

		mapSemestres = new HashMap<NumeroSemestre, Semestre>() {
			{
				put(NumeroSemestre.SEMESTRE1, new Semestre(NumeroSemestre.SEMESTRE1, LocalDate.of(2017, 10, 13), LocalDate.of(2018, 02, 23)));
				put(NumeroSemestre.SEMESTRE2, new Semestre(NumeroSemestre.SEMESTRE2, LocalDate.of(2018, 2, 26), LocalDate.of(2018, 7, 31)));
			}
		};

		// ················································
		// Formation
		// ················································

		formation = formationRepository.save(new Formation(TypeFormation.I4, "INGENIERIE 4", mapSemestres.get(NumeroSemestre.SEMESTRE1), mapSemestres.get(NumeroSemestre.SEMESTRE2)));

		// ================================================
		// PARCOURS SOCLE
		// ================================================

		parcours = parcoursRepository.save(new Parcours(Parcours.TypeParcours.SOCLE, false, formation));

		// ················································
		// UE learning
		// ················································

		ue = uniteEnseignementRepository.save(new UniteEnseignement(null, "Learning by doing", parcours));
		databaseService.createModule("TPTE700", "Workshop 1 - Développement d'une application avec utilisation de données", 40, 0, mapSemestres, intervenantRepository.findByNom("lefetz"), ue);
		databaseService.createModule("TPTE800", "Worshop 2 - Créativité & Innovation / TagCloud : Embarqué - SmartCity - Entrepreunariat", 40, 0, mapSemestres,
				intervenantRepository.findByNom("lefetz"), ue);

		// ················································
		// bloc compétences 1
		// ················································

		blocCompetences = blocCompetencesRepository.save(new BlocCompetences(parcours, 1, "Fonction d'Encadrement"));

		databaseService.createProjetTransversal("TPTE828", "Projet UE Stratégie financière, Anglais et T.E.C.", 20, 0, mapSemestres, intervenantRepository.findByNom("lefetz"), blocCompetences);

		ue = uniteEnseignementRepository.save(new UniteEnseignement(null, "Stratégie financière", blocCompetences));
		databaseService.createModule("FINE720", "Analyse financière", 18, 2, mapSemestres, intervenantRepository.findByNom("frénéa"), ue);
		databaseService.createModule("FINE821", "Audit et Diagnostic stratégique financier", 18, 2, mapSemestres, intervenantRepository.findByNom("frénéa"), ue);

		ue = uniteEnseignementRepository.save(new UniteEnseignement(null, "Anglais", blocCompetences));
		databaseService.createModule("LNGE824", "Role Play", 18, 2, mapSemestres, intervenantRepository.findByNom("hardstaff"), ue);
		databaseService.createModule("LNGE825", "Préparation TOEIC", 18, 2, mapSemestres, intervenantRepository.findByNom("hardstaff"), ue);

		ue = uniteEnseignementRepository.save(new UniteEnseignement(null, "Techniques d'Expression et Communication", blocCompetences));
		databaseService.createModule("COME826", "Communication d'Entreprise", 18, 2, mapSemestres, intervenantRepository.findByNom("doco"), ue);
		databaseService.createModule("COME827", "Développement professionnel & NetWorking", 18, 2, mapSemestres, intervenantRepository.findByNom("doco"), ue);

		// ················································
		// bloc compétences 2
		// ················································

		blocCompetences = blocCompetencesRepository.save(new BlocCompetences(parcours, 2, "Méthodes & Projet"));

		ue = uniteEnseignementRepository.save(new UniteEnseignement(null, "Méthodes de gestion de projet", blocCompetences));
		databaseService.createModule("PROE815", "WorkShop Agile", 18, 2, mapSemestres, intervenantRepository.findByNom("decroix"), ue);

		ue = uniteEnseignementRepository.save(new UniteEnseignement(null, "Mathématiques & Recherche opérationnelle", blocCompetences));
		databaseService.createModule("TQGE822", "Suites et séries numériques", 18, 2, mapSemestres, intervenantRepository.findByNom("maati"), ue);
		databaseService.createModule("TQGE823", "Workshop R.O. & Aide à la décision", 18, 2, mapSemestres, intervenantRepository.findByNom("maati"), ue);

		// ················································
		// bloc compétences 3
		// ················································

		blocCompetences = blocCompetencesRepository.save(new BlocCompetences(parcours, 3, "Gestion de Données & Business Intelligence"));

		ue = uniteEnseignementRepository.save(new UniteEnseignement(null, "Business Intelligence", blocCompetences));
		databaseService.createModule("BDOE701", "Modélisation Décisionnelle (introduction)", 18, 2, mapSemestres, intervenantRepository.findByNom("nakache"), ue);
		databaseService.createModule("BDOE802", "Module NoSQL", 18, 2, mapSemestres, null, ue);

		// ················································
		// bloc compétences 4
		// ················································

		blocCompetences = blocCompetencesRepository.save(new BlocCompetences(parcours, 4, "Etudes & Développement"));

		databaseService.createProjetTransversal("TPTE817", "Projet transversal NAO & Intelligence artificielle (gestion de projet agile)", 40, 0, mapSemestres,
				intervenantRepository.findByNomAndPrenom("gaber", "khalid"), blocCompetences);

		ue = uniteEnseignementRepository.save(new UniteEnseignement(null, "Mobilité &  Systèmes embarqués", blocCompetences));
		databaseService.createModule("DEVE710", "Systèmes Embarqués", 18, 2, mapSemestres, intervenantRepository.findByNom("rombeaut"), ue);
		databaseService.createModule("DEVE839", "Programmation en temps réel", 18, 2, mapSemestres, intervenantRepository.findByNomAndPrenom("gaber", "jaafar"), ue);
		databaseService.createModule("DEVE705", "Web Services", 18, 2, mapSemestres, intervenantRepository.findByNom("chinchole"), ue);

		ue = uniteEnseignementRepository.save(new UniteEnseignement(null, "Intelligence artificielle", blocCompetences));
		databaseService.createModule("DEVE703", "Apprentissage et Réseaux Neuronaux (Bases)", 18, 2, mapSemestres, intervenantRepository.findByNomAndPrenom("gaber", "khalid"), ue);
		databaseService.createModule("DEVE804", "Résolution de problèmes complexes", 18, 2, mapSemestres, intervenantRepository.findByNomAndPrenom("gaber", "khalid"), ue);

		// ················································
		// bloc compétences 5
		// ················································

		blocCompetences = blocCompetencesRepository.save(new BlocCompetences(parcours, 5, "Infrastructure Systèmes & Réseaux"));

		databaseService.createProjetTransversal("TPTE819", "Projet transversal Gestion de projet agile & UE réseaux et système / Sécurité ", 60, 0, mapSemestres,
				intervenantRepository.findByNom("deliessche"), blocCompetences);

		ue = uniteEnseignementRepository.save(new UniteEnseignement(null, "Virtualisation & Cloud Computing", blocCompetences));
		databaseService.createModule("RESE811", "Mise en œuvre de la Virtualisation", 18, 2, mapSemestres, intervenantRepository.findByNom("rombeaut"), ue);
		databaseService.createModule("RESE825", "Développer une solution Cloud", 18, 2, mapSemestres, intervenantRepository.findByNom("rombeaut"), ue);

		ue = uniteEnseignementRepository.save(new UniteEnseignement(null, "Sécurité Infrastructure", blocCompetences));
		databaseService.createModule("SYSE740", "Outils de chiffrement", 18, 2, mapSemestres, intervenantRepository.findByNom("maati"), ue);
		databaseService.createModule("SYSE741", "Systèmes de sauvegarde", 18, 2, mapSemestres, intervenantRepository.findByNomAndPrenom("dubois", "julien"), ue);

		// ================================================
		// PARCOURS COMPLEMENTAIRE
		// ================================================

		parcours = parcoursRepository.save(new Parcours(Parcours.TypeParcours.COMPLEMENTAIRE, true, formation));

		ue = uniteEnseignementRepository.save(new UniteEnseignement(TypeUE.SECU, "Sécurité Informatique & Systèmes d'information", blocCompetences));
		databaseService.createModule("RESE807", "La sécurité des réseaux", 18, 2, mapSemestres, intervenantRepository.findByNom("deliessche"), ue);
		databaseService.createModule("RESE834", "Cryptographie", 18, 2, mapSemestres, intervenantRepository.findByNom("maati"), ue);

		ue = uniteEnseignementRepository.save(new UniteEnseignement(TypeUE.RESEAUX, "Réseaux & Systèmes", blocCompetences, true));
		databaseService.createModule("RESE809", "Administration d'un système de messagerie", 18, 2, mapSemestres, null, ue);
		databaseService.createModule("RESE808", "Mobiles et VOIP", 18, 2, mapSemestres, null, ue);

		ue = uniteEnseignementRepository.save(new UniteEnseignement(TypeUE.DATA, "Business Intelligence & Big Data", blocCompetences));
		databaseService.createModule("BDOE813", "Conception et planification BI", 18, 2, mapSemestres, null, ue);
		databaseService.createModule("BDOE828", "Datamining & Fouille de données : concepts et techniques", 18, 2, mapSemestres, intervenantRepository.findByNom("nakache"), ue);

		// ================================================
		// PARCOURS PROFESSIONNEL
		// ================================================

		parcours = parcoursRepository.save(new Parcours(Parcours.TypeParcours.PROFESSIONNEL, false, formation));

		ue = uniteEnseignementRepository.save(new UniteEnseignement(null, "Ateliers Développement professionnel & Active learning", parcours));
		databaseService.createModule("xxxE701", "Atelier Apprendre à apprendre", 6, 0, mapSemestres, intervenantRepository.findByNom("frénéa"), ue);
		databaseService.createModule("xxxE702", "Développement professionnel : Portefeuille de compétences", 4, 0, mapSemestres, intervenantRepository.findByNom("frénéa"), ue);
		databaseService.createModule("DEPE838", "Active member / Participation Vie Ecole", 0, 0, mapSemestres, intervenantRepository.findByNom("lefetz"), ue);

		ue = uniteEnseignementRepository.save(new UniteEnseignement(null, "Stage ou Alternance en entreprise", parcours));
		databaseService.createModule("TPRE832", "Rapport d'activité", 0, 0, mapSemestres, intervenantRepository.findByNom("lefetz"), ue);
		databaseService.createModule("TPRE834", "Suivi Stage / Evaluation Entreprise", 0, 0, mapSemestres, intervenantRepository.findByNom("lefetz"), ue);
		databaseService.createModule("TPRE833", "Soutenance Rapport d'activité", 0, 1, mapSemestres, intervenantRepository.findByNom("lefetz"), ue);

	}

	@Override
	public void initialiserCours() {
		// TODO Auto-generated method stub

	}

}
