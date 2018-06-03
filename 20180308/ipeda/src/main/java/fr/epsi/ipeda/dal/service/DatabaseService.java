package fr.epsi.ipeda.dal.service;

import java.lang.invoke.MethodHandles;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.epsi.ipeda.dal.entity.AnneeScolaire;
import fr.epsi.ipeda.dal.entity.BlocCompetences;
import fr.epsi.ipeda.dal.entity.Formation;
import fr.epsi.ipeda.dal.entity.Intervenant;
import fr.epsi.ipeda.dal.entity.Module;
import fr.epsi.ipeda.dal.entity.Parcours;
import fr.epsi.ipeda.dal.entity.ProjetTransversal;
import fr.epsi.ipeda.dal.entity.Salle;
import fr.epsi.ipeda.dal.entity.Specificite;
import fr.epsi.ipeda.dal.entity.UniteEnseignement;
import fr.epsi.ipeda.dal.entity.periode.PeriodeType;
import fr.epsi.ipeda.dal.repository.AnneeScolaireRepository;
import fr.epsi.ipeda.dal.repository.BlocCompetencesRepository;
import fr.epsi.ipeda.dal.repository.FormationRepository;
import fr.epsi.ipeda.dal.repository.IntervenantRepository;
import fr.epsi.ipeda.dal.repository.ModuleRepository;
import fr.epsi.ipeda.dal.repository.ParcoursRepository;
import fr.epsi.ipeda.dal.repository.ProjetTransversalRepository;
import fr.epsi.ipeda.dal.repository.SalleRepository;
import fr.epsi.ipeda.dal.repository.SpecificiteRepository;
import fr.epsi.ipeda.dal.repository.periode.PeriodeTypeRepository;

@Service
public class DatabaseService implements IDatabaseService {

	final static Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Autowired
	private SalleRepository salleRepository;

	@Autowired
	private IntervenantRepository intervenantRepository;

	@Autowired
	private ModuleRepository moduleRepository;

	@Autowired
	private ProjetTransversalRepository projetTransversalRepository;

	@Autowired
	private AnneeScolaireRepository anneeScolaireRepository;

	@Autowired
	private SpecificiteRepository specificiteRepository;

	@Autowired
	private FormationRepository formationRepository;

	@Autowired
	private ParcoursRepository parcoursRepository;

	@Autowired
	private BlocCompetencesRepository blocCompetencesRepository;

	@Autowired
	private PeriodeTypeRepository periodeTypeRepository;

	@Override
	public void initialiserSalles() {
		salleRepository.save(new Salle("jaune"));
		salleRepository.save(new Salle("rouge"));
		salleRepository.save(new Salle("verte"));
		salleRepository.save(new Salle("grise"));
		salleRepository.save(new Salle("tp-2"));
		salleRepository.save(new Salle("conférence"));
		salleRepository.save(new Salle("normandie"));
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
	 * @return Le module créé
	 */
	@Override
	public Module createModule(String codeModule, String libelle, double dureeFFP, double dureeTE, Intervenant intervenant, UniteEnseignement ue) {
		return moduleRepository.save(new Module(codeModule, libelle, Duration.ofMinutes((long) (dureeFFP * 60)), Duration.ofMinutes((long) (dureeTE * 60)), intervenant, ue));
	}

	/**
	 * Méthode utilitaire privée, permettant de persister un module "composé" et d'alléger le code
	 * 
	 * @param moduleParent
	 *            Le module parent composé auquel est rattaché le module
	 * @param libelle
	 * @param dureeFFP
	 * @param dureeTE
	 * @param mapSemestres
	 * @param intervenant
	 * @return Le module créé
	 */
	@Override
	public Module createModule(Module moduleParent, String libelle, double dureeFFP, double dureeTE, Intervenant intervenant) {
		return moduleRepository.save(
				new Module(moduleParent.getCode(), libelle, Duration.ofMinutes((long) (dureeFFP * 60)), Duration.ofMinutes((long) (dureeTE * 60)), intervenant, moduleParent.getUniteEnseignement()));
	}

	/**
	 * Permet de lier les modules enfants au module parent composé
	 * 
	 * @param moduleParent
	 *            Module parent composé
	 * @param modules
	 *            Liste des modules enfants
	 */
	@Override
	public void createModuleCompose(Module moduleParent, Module... modules) {
		for (Module module : modules) {
			module.setModuleParentCompose(moduleParent);
			moduleRepository.save(module);
		}
	}

	/**
	 * Méthode utilitaire privée, permettant de persister un module mutualisé et d'alléger le code. Un module mutualisé est un module "parent" qui n'a pas de code, et qui est composé de 2 ou plus
	 * modules enfants. Il doit être dispensé par le même intervenant. Les modules doivent avoir des volumes horaires identiques.
	 * 
	 * @param libelle
	 * @param listeModulesMutualises
	 */
	@Override
	public void createModuleMutualise(String libelle, ArrayList<Module> listeModulesMutualises) {

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
					if (!module.getDureeFFP().equals(moduleReference.getDureeFFP()) || !module.getDureeTE().equals(moduleReference.getDureeTE())
							|| !module.getIntervenant().equals(moduleReference.getIntervenant())) {
						isValide = false;
						logger.error("Les modules à mutualiser ne sont pas compatibles, impossible de mutualiser.");
						break;
					}
				}

				if (isValide) {

					// sauvegarde du module mutualisé parent
					Module module = new Module();
					module.setDureeFFP(moduleReference.getDureeFFP());
					module.setDureeTE(moduleReference.getDureeTE());
					module.setIntervenant(moduleReference.getIntervenant());
					module.setLibelle(libelle);
					module.setListeModulesMutualises(listeModulesMutualises);
					module.setUniteEnseignement(moduleReference.getUniteEnseignement());
					module = moduleRepository.save(module);

					// ajout des références dans les modules enfants
					listeModulesMutualises.add(moduleReference);
					for (Module moduleEnfant : listeModulesMutualises) {
						moduleEnfant.setModuleParentMutualise(module);
						moduleRepository.save(moduleEnfant);
					}

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
	@Override
	public ProjetTransversal createProjetTransversal(String codeModule, String libelle, double dureeFFP, double dureeTE, Intervenant intervenant, BlocCompetences blocCompetences) {
		return projetTransversalRepository
				.save(new ProjetTransversal(codeModule, libelle, Duration.ofMinutes((long) (dureeFFP * 60)), Duration.ofMinutes((long) (dureeTE * 60)), intervenant, blocCompetences));

	}

	// @Override
	// public void createCours(String dateHeureDebut, double duree, String codeModule, Long salleId) {
	// coursRepository.save(new Cours(moduleRepository.findByCode(codeModule)));
	// }
	//
	// @Override
	// public void afficheCours() {
	// List<Cours> listeCours = coursRepository.findByModule(moduleRepository.findByCode("RESE631"));
	// for (Cours cours : listeCours) {
	// System.out.println("cours : " + cours.getModule().getCode() + " - " + cours.getModule().getLibelle() + " - " + cours.getListeSalles().get(0).getLibelle() + " - "
	// + cours.getModule().getIntervenant().getNom());
	// }
	// }

	@Override
	public void initialiserAnneeScolaire() {
		AnneeScolaire anneeScolaire = new AnneeScolaire();
		anneeScolaire.setDateDebut(LocalDate.of(2017, 9, 1));
		anneeScolaire.setDateFin(LocalDate.of(2018, 8, 31));
		anneeScolaireRepository.save(anneeScolaire);

		anneeScolaire = new AnneeScolaire();
		anneeScolaire.setDateDebut(LocalDate.of(2018, 9, 1));
		anneeScolaire.setDateFin(LocalDate.of(2019, 8, 31));
		anneeScolaireRepository.save(anneeScolaire);

	}

	@Override
	public void initialiserFormation() {

		AnneeScolaire anneeScolaire = anneeScolaireRepository.findByDateDebut(LocalDate.of(2018, 9, 1));
		LocalDate dateFinSemestre1 = LocalDate.of(2019, 2, 1);

		formationRepository.save(new Formation("BACHELOR 1 18/19", "B1 18/19", anneeScolaire, dateFinSemestre1));
		formationRepository.save(new Formation("BACHELOR 2 18/19", "B2 18/19", anneeScolaire, dateFinSemestre1));
		formationRepository.save(new Formation("BACHELOR 3 18/19", "B3 18/19", anneeScolaire, dateFinSemestre1));
		formationRepository.save(new Formation("INGENIERIE 4 18/19", "I4 18/19", anneeScolaire, dateFinSemestre1));
		formationRepository.save(new Formation("INGENIERIE 5 18/19", "I5 18/19", anneeScolaire, dateFinSemestre1));

	}

	@Override
	public void initialiserSpecificite() {

		Specificite specificite = new Specificite();
		specificite.setLibelle("BTS");
		specificiteRepository.save(specificite);

		specificite = new Specificite();
		specificite.setLibelle("BTS_SLAM");
		specificiteRepository.save(specificite);

		specificite = new Specificite();
		specificite.setLibelle("BTS_SISR");
		specificiteRepository.save(specificite);

		specificite = new Specificite();
		specificite.setLibelle("M-DEV");
		specificiteRepository.save(specificite);

		specificite = new Specificite();
		specificite.setLibelle("M-RES");
		specificiteRepository.save(specificite);

		specificite = new Specificite();
		specificite.setLibelle("M-ERP");
		specificiteRepository.save(specificite);

		specificite = new Specificite();
		specificite.setLibelle("S-DATA");
		specificiteRepository.save(specificite);

		specificite = new Specificite();
		specificite.setLibelle("S-SECU");
		specificiteRepository.save(specificite);

		specificite = new Specificite();
		specificite.setLibelle("C-DATA");
		specificiteRepository.save(specificite);

		specificite = new Specificite();
		specificite.setLibelle("C-SECU");
		specificiteRepository.save(specificite);

		specificite = new Specificite();
		specificite.setLibelle("C-MOB");
		specificiteRepository.save(specificite);

		specificite = new Specificite();
		specificite.setLibelle("C-IOT");
		specificiteRepository.save(specificite);

		specificite = new Specificite();
		specificite.setLibelle("C-CLOUD");
		specificiteRepository.save(specificite);

	}

	@Override
	public void initialiserParcours() {

		AnneeScolaire anneeScolaire = anneeScolaireRepository.findByDateDebut(LocalDate.of(2018, 9, 1));

		// b1
		Formation formation = formationRepository.findByLibelleContainingAndAnneeScolaire("BACHELOR 1", anneeScolaire);
		parcoursRepository.save(new Parcours(formation, "SOCLE DEVOPS"));
		parcoursRepository.save(new Parcours(formation, "PROFESSIONNEL"));
		parcoursRepository.save(new Parcours(formation, "BTS SIO", specificiteRepository.findByLibelle("BTS")));

		// b2
		formation = formationRepository.findByLibelleContainingAndAnneeScolaire("BACHELOR 2", anneeScolaire);
		parcoursRepository.save(new Parcours(formation, "SOCLE DEVOPS"));
		parcoursRepository.save(new Parcours(formation, "PROFESSIONNEL"));
		parcoursRepository.save(new Parcours(formation, "BTS SIO", specificiteRepository.findByLibelle("BTS")));

		// b3
		formation = formationRepository.findByLibelleContainingAndAnneeScolaire("BACHELOR 3", anneeScolaire);
		parcoursRepository.save(new Parcours(formation, "SOCLE DEVOPS"));
		parcoursRepository.save(new Parcours(formation, "METIER - RESEAU", specificiteRepository.findByLibelle("M-RES"), true));
		parcoursRepository.save(new Parcours(formation, "METIER - DEVELOPPEMENT", specificiteRepository.findByLibelle("M-DEV"), true));
		parcoursRepository.save(new Parcours(formation, "COMPLEMENTAIRE"));
		parcoursRepository.save(new Parcours(formation, "PROFESSIONNEL"));

		// i4
		formation = formationRepository.findByLibelleContainingAndAnneeScolaire("INGENIERIE 4", anneeScolaire);
		parcoursRepository.save(new Parcours(formation, "SOCLE EXPERTISE INFORMATIQUE & SYSTEME D'INFORMATION"));
		parcoursRepository.save(new Parcours(formation, "SPECIALITE - SECURITE", specificiteRepository.findByLibelle("S-SECU"), true));
		parcoursRepository.save(new Parcours(formation, "SPECIALITE - DATA", specificiteRepository.findByLibelle("S-DATA"), true));
		parcoursRepository.save(new Parcours(formation, "PROFESSIONNEL"));

		// i5
		formation = formationRepository.findByLibelleContainingAndAnneeScolaire("INGENIERIE 5", anneeScolaire);
		parcoursRepository.save(new Parcours(formation, "SOCLE EXPERTISE INFORMATIQUE & SYSTEME D'INFORMATION"));
		parcoursRepository.save(new Parcours(formation, "METIER"));
		parcoursRepository.save(new Parcours(formation, "COMPLEMENTAIRE"));
		parcoursRepository.save(new Parcours(formation, "PROFESSIONNEL"));

	}

	@Override
	public void initialiserBlocDeCompetence() {

		AnneeScolaire anneeScolaire = anneeScolaireRepository.findByDateDebut(LocalDate.of(2018, 9, 1));

		// b1
		Formation formation = formationRepository.findByLibelleContainingAndAnneeScolaire("BACHELOR 1", anneeScolaire);

		Parcours parcours = parcoursRepository.findByFormationAndLibelleContaining(formation, "SOCLE DEVOPS");
		BlocCompetences bloc = new BlocCompetences();
		bloc.setParcours(parcours);
		bloc.setNumero(0);
		bloc.setLibelle("Développement personnel & professionnel - Ateliers / Workshops");
		// bloc.setSpecificite(specificite);
		blocCompetencesRepository.save(bloc);

		bloc = new BlocCompetences();
		bloc.setParcours(parcours);
		bloc.setNumero(1);
		bloc.setLibelle("Développement d’applications informatiques");
		blocCompetencesRepository.save(bloc);

	}

	@Override
	public void initialiserPeriodeType() {

		periodeTypeRepository.save(new PeriodeType("NON_PLANIFIABLE"));
		periodeTypeRepository.save(new PeriodeType("JOUR_FERIE"));
		periodeTypeRepository.save(new PeriodeType("FERMETURE"));
		periodeTypeRepository.save(new PeriodeType("STAGE"));
		periodeTypeRepository.save(new PeriodeType("SEMAINE_ECOLE"));
		periodeTypeRepository.save(new PeriodeType("SEMAINE_ENTREPRISE"));
		periodeTypeRepository.save(new PeriodeType("RATTRAPAGES"));
		periodeTypeRepository.save(new PeriodeType("REVISIONS"));
		periodeTypeRepository.save(new PeriodeType("BTS_BLANC"));
		periodeTypeRepository.save(new PeriodeType("BTS_REEL"));
		periodeTypeRepository.save(new PeriodeType("SALON_ETUDIANT"));
		periodeTypeRepository.save(new PeriodeType("VACANCES"));
		periodeTypeRepository.save(new PeriodeType("SOUTENANCES"));

	}

}
