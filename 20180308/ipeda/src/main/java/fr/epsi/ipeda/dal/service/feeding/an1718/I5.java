package fr.epsi.ipeda.dal.service.feeding.an1718;

import java.time.LocalDate;
import java.util.HashMap;

import org.springframework.stereotype.Component;

import fr.epsi.ipeda.dal.entity.BlocCompetences;
import fr.epsi.ipeda.dal.entity.Formation;
import fr.epsi.ipeda.dal.entity.Parcours;
import fr.epsi.ipeda.dal.entity.Semestre;
import fr.epsi.ipeda.dal.entity.UniteEnseignement;
import fr.epsi.ipeda.dal.entity.BlocCompetences.TypeBloc;
import fr.epsi.ipeda.dal.entity.Formation.TypeFormation;
import fr.epsi.ipeda.dal.entity.Semestre.NumeroSemestre;
import fr.epsi.ipeda.dal.entity.UniteEnseignement.TypeUE;
import fr.epsi.ipeda.dal.service.feeding.Feeding;

@Component
public class I5 extends Feeding {

	@SuppressWarnings("serial")
	@Override
	public void initialiserFormation() {

		// ················································
		// Semestres
		// ················································

		mapSemestres = new HashMap<NumeroSemestre, Semestre>() {
			{
				put(NumeroSemestre.SEMESTRE1, new Semestre(NumeroSemestre.SEMESTRE1, LocalDate.of(2017, 9, 11), LocalDate.of(2017, 11, 24)));
				put(NumeroSemestre.SEMESTRE2, new Semestre(NumeroSemestre.SEMESTRE2, LocalDate.of(2017, 11, 27), LocalDate.of(2018, 3, 2)));
			}
		};

		// ················································
		// Formation
		// ················································

		formation = formationRepository.save(new Formation(TypeFormation.I5, "INGENIERIE 5", mapSemestres.get(NumeroSemestre.SEMESTRE1), mapSemestres.get(NumeroSemestre.SEMESTRE2)));

		// ================================================
		// PARCOURS SOCLE
		// ================================================

		parcours = parcoursRepository.save(new Parcours(Parcours.TypeParcours.SOCLE, false, formation));

		// ················································
		// UE learning
		// ················································

		ue = uniteEnseignementRepository.save(new UniteEnseignement(null, "Learning by doing", parcours));
		databaseService.createModule("TPTE900", "Workshop \"Out-of-the-box thinking\" / Créativité & Innovation", 40, 0, mapSemestres, intervenantRepository.findByNom("lefetz"), ue);

		// ················································
		// bloc compétences 1
		// ················································

		blocCompetences = blocCompetencesRepository.save(new BlocCompetences(parcours, 1, "Fonction d'Encadrement"));

		databaseService.createProjetTransversal("TPTE010", "Projet transversal Manager un système d'information (Blocs de compétences n° 1 et 2)", 40, 0, mapSemestres,
				intervenantRepository.findByNom("spella"), blocCompetences);

		ue = uniteEnseignementRepository.save(new UniteEnseignement(null, "Management des Systèmes d'Information", blocCompetences));
		databaseService.createModule("SYSE903", "Gouvernance et Performance d'un S.I.", 18, 2, mapSemestres, intervenantRepository.findByNom("regost"), ue);
		databaseService.createModule("SYSE902", "Architecture Processus Métier", 18, 2, mapSemestres, intervenantRepository.findByNomAndPrenom("gaber", "oussama"), ue);
		databaseService.createModule("SYSE904", "Sécurité de l'information et des échanges", 18, 2, mapSemestres, intervenantRepository.findByNom("seifert"), ue);

		ue = uniteEnseignementRepository.save(new UniteEnseignement(null, "Management des Ressources", blocCompetences));
		databaseService.createModule("PROE905", "Gestion des Contrats de services", 18, 2, mapSemestres, intervenantRepository.findByNomAndPrenom("dubois", "patrick"), ue);
		databaseService.createModule("MGTE906", "Gestion des Compétences RH", 18, 2, mapSemestres, intervenantRepository.findByNom("vermersch"), ue);
		databaseService.createModule("ENVE907", "Green IT & RSE", 18, 2, mapSemestres, intervenantRepository.findByNomAndPrenom("gaber", "oussama"), ue);
		databaseService.createModule("PROE908", "Lean Management", 18, 2, mapSemestres, intervenantRepository.findByNomAndPrenom("gaber", "oussama"), ue);

		ue = uniteEnseignementRepository.save(new UniteEnseignement(null, "Anglais", blocCompetences));
		databaseService.createModule("LNGE909", "Enews", 18, 2, mapSemestres, intervenantRepository.findByNom("hardstaff"), ue);

		// ················································
		// bloc compétences 2
		// ················································

		blocCompetences = blocCompetencesRepository.save(new BlocCompetences(parcours, 2, "Méthodes & Projet"));

		ue = uniteEnseignementRepository.save(new UniteEnseignement(null, "Management de Projet", blocCompetences));
		databaseService.createModule("PROE901", "Pilotage de Projet", 18, 2, mapSemestres, intervenantRepository.findByNomAndPrenom("gaber", "oussama"), ue);

		// ················································
		// bloc compétences 3
		// ················································

		blocCompetences = blocCompetencesRepository.save(new BlocCompetences(parcours, 3, "Gestion de Données & Business Intelligence"));

		ue = uniteEnseignementRepository.save(new UniteEnseignement(null, "Business Intelligence", blocCompetences));
		databaseService.createModule("BDOE929", "Qualité des données", 18, 2, mapSemestres, intervenantRepository.findByNom("nakache"), ue);

		// ================================================
		// PARCOURS COMPLEMENTAIRE
		// ================================================

		parcours = parcoursRepository.save(new Parcours(Parcours.TypeParcours.COMPLEMENTAIRE, true, formation));

		ue = uniteEnseignementRepository.save(new UniteEnseignement(TypeUE.SECU, "Management de la Sécurité informatique", blocCompetences));
		databaseService.createModule("SYSE930", "La Continuité de services (PCA / PRA)", 18, 2, mapSemestres, intervenantRepository.findByNom("deliessche"), ue);
		databaseService.createModule("SYSE927", "Cybersécurité : Mise en oeuvre et respect de la sécurité", 18, 2, mapSemestres, intervenantRepository.findByNom("deliessche"), ue);

		ue = uniteEnseignementRepository.save(new UniteEnseignement(TypeUE.CLOUD, "Management du Cloud Computing", blocCompetences));
		databaseService.createModule("RESE925", "Management et déploiement de solutions Cloud", 18, 2, mapSemestres, intervenantRepository.findByNom("rombeaut"), ue);
		databaseService.createModule("RESE926", "La Sécurité du Cloud", 18, 2, mapSemestres, intervenantRepository.findByNom("rombeaut"), ue);

		ue = uniteEnseignementRepository.save(new UniteEnseignement(TypeUE.DATA, "Big Data & L'internet des Objets", blocCompetences));
		databaseService.createModule("BDOE928", "Management et déploiement de solutions Big Data", 18, 2, mapSemestres, intervenantRepository.findByNom("nakache"), ue);
		databaseService.createModule("BDOE933", "La sécurité des objets connectés", 18, 2, mapSemestres, intervenantRepository.findByNomAndPrenom("dubois", "julien"), ue);

		ue = uniteEnseignementRepository.save(new UniteEnseignement(TypeUE.MOBILITE, "Management des environnements nomades", blocCompetences));
		databaseService.createModule("DEVE931", "Développement WebApplications & Framework mobiles", 18, 2, mapSemestres, intervenantRepository.findByNom("fritsch"), ue);
		databaseService.createModule("SYSE932", "La sécurité des systèmes nomades", 18, 2, mapSemestres, intervenantRepository.findByNom("deliessche"), ue);

		// ================================================
		// PARCOURS METIER
		// ================================================

		parcours = parcoursRepository.save(new Parcours(Parcours.TypeParcours.METIER, true, formation));

		// ················································
		// bloc compétences 4
		// ················································

		blocCompetences = blocCompetencesRepository.save(new BlocCompetences(TypeBloc.DEV, parcours, 4, "Etudes & Développement"));

		databaseService.createProjetTransversal("TPTE011", "Projet transversal UE Réalité augmentée et Tps Réel", 40, 0, mapSemestres, null, blocCompetences);

		ue = uniteEnseignementRepository.save(new UniteEnseignement(null, "Réalité augmentée", blocCompetences));
		databaseService.createModule("DEVE915", "Modélisation et visualisation 3D", 18, 2, mapSemestres, intervenantRepository.findByNom("maati"), ue);
		databaseService.createModule("DEVE914", "Gamification", 18, 2, mapSemestres, intervenantRepository.findByNom("flasque"), ue);

		ue = uniteEnseignementRepository.save(new UniteEnseignement(null, "Développement Temps réel", blocCompetences));
		databaseService.createModule("DEVE913", "Architecture générale et logicielle d'un système embarqué", 18, 2, mapSemestres, intervenantRepository.findByNomAndPrenom("gaber", "jaafar"), ue);
		databaseService.createModule("DEVE912", "Conception d'une solution embarquée en temps réel", 18, 2, mapSemestres, intervenantRepository.findByNomAndPrenom("gaber", "jaafar"), ue);

		// ················································
		// bloc compétences 5
		// ················································

		blocCompetences = blocCompetencesRepository.save(new BlocCompetences(TypeBloc.RESEAUX, parcours, 5, "Infrastructure Systèmes & Réseaux"));

		databaseService.createProjetTransversal("TPTE034", "Projet transversal UE Réseaux et Systèmes", 40, 0, mapSemestres, intervenantRepository.findByNom("rombeaut"), blocCompetences);

		ue = uniteEnseignementRepository.save(new UniteEnseignement(null, "Réseau & Télécommunications", blocCompetences));
		databaseService.createModule("RESE916", "Technologie des réseaux intégrant voix, images et données", 18, 2, mapSemestres, intervenantRepository.findByNom("deliessche"), ue);
		databaseService.createModule("RESE917", "Gestion et Optimisation des réseaux", 18, 2, mapSemestres, intervenantRepository.findByNom("rombeaut"), ue);

		ue = uniteEnseignementRepository.save(new UniteEnseignement(null, "Systèmes", blocCompetences));
		databaseService.createModule("SYSE918", "Ingénierie des systèmes", 18, 2, mapSemestres, intervenantRepository.findByNom("rombeaut"), ue);
		databaseService.createModule("SYSE919", "Management de la Virtualisation", 18, 2, mapSemestres, intervenantRepository.findByNom("rombeaut"), ue);

		// ················································
		// bloc compétences 6
		// ················································

		blocCompetences = blocCompetencesRepository.save(new BlocCompetences(TypeBloc.ERP, parcours, 6, "Management et Conseil ERP", true));

		databaseService.createProjetTransversal("TPTE024", "Projet transversal Projet ERP", 40, 0, mapSemestres, null, blocCompetences);

		ue = uniteEnseignementRepository.save(new UniteEnseignement(null, "Management de Projet ERP", blocCompetences));
		databaseService.createModule("PROE920", "Pilotage de Projet ERP", 18, 2, mapSemestres, null, ue);
		databaseService.createModule("PROE921", "Management du risque", 18, 2, mapSemestres, null, ue);

		ue = uniteEnseignementRepository.save(new UniteEnseignement(null, "Prise en Main ERP SAP", blocCompetences));
		databaseService.createModule("TECE922", "Environnement Général SAP", 18, 2, mapSemestres, null, ue);
		databaseService.createModule("TECE923", "Prise en Main SAP - Flux et Processus (GBI - IDES)", 18, 2, mapSemestres, null, ue);

		// ================================================
		// PARCOURS PROFESSIONNEL
		// ================================================

		parcours = parcoursRepository.save(new Parcours(Parcours.TypeParcours.PROFESSIONNEL, false, formation));

		ue = uniteEnseignementRepository.save(new UniteEnseignement(null, "Mémoire Professionnel", parcours));
		databaseService.createModule("xxxE000", "Conseils & Explications Mémoire professionnel", 5.5, 0, mapSemestres, intervenantRepository.findByNom("lefetz"), ue);
		databaseService.createModule("TPRE038", "Mémoire professionnel", 3.5, 0, mapSemestres, intervenantRepository.findByNom("lefetz"), ue);
		databaseService.createModule("TPRE039", "Soutenance Mémoire", 1, 0, mapSemestres, intervenantRepository.findByNom("lefetz"), ue);

		ue = uniteEnseignementRepository.save(new UniteEnseignement(null, "Active Learning & Innovation", parcours));
		databaseService.createModule("DEPE040", "Labo/atelier (activités liées au Campus)", 0, 0, mapSemestres, intervenantRepository.findByNom("lefetz"), ue);
		databaseService.createModule("DEPE041", "Active Member /Participation Vie Ecole", 0, 0, mapSemestres, intervenantRepository.findByNom("lefetz"), ue);

	}

	@Override
	public void initialiserCours() {
		// TODO Auto-generated method stub

	}

}
