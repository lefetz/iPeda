package fr.epsi.ipeda.service.database;

import java.lang.invoke.MethodHandles;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.epsi.ipeda.dao.entity.BlocCompetences;
import fr.epsi.ipeda.dao.entity.Cours;
import fr.epsi.ipeda.dao.entity.Formation.TypeFormation;
import fr.epsi.ipeda.dao.entity.Intervenant;
import fr.epsi.ipeda.dao.entity.Module;
import fr.epsi.ipeda.dao.entity.ProjetTransversal;
import fr.epsi.ipeda.dao.entity.Salle;
import fr.epsi.ipeda.dao.entity.Salle.CodeSalle;
import fr.epsi.ipeda.dao.entity.Semaine;
import fr.epsi.ipeda.dao.entity.Semestre;
import fr.epsi.ipeda.dao.entity.Semestre.NumeroSemestre;
import fr.epsi.ipeda.dao.entity.UniteEnseignement;
import fr.epsi.ipeda.dao.repository.CoursRepository;
import fr.epsi.ipeda.dao.repository.IntervenantRepository;
import fr.epsi.ipeda.dao.repository.ModuleRepository;
import fr.epsi.ipeda.dao.repository.ProjetTransversalRepository;
import fr.epsi.ipeda.dao.repository.SalleRepository;
import fr.epsi.ipeda.helpers.FormatterUtils;
import fr.epsi.ipeda.service.businesslogic.nomenclature.INomenclatureService;

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
	private CoursRepository coursRepository;

	@Autowired
	private INomenclatureService nomenclatureService;

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
	 * @return Le module créé
	 */
	@Override
	public Module createModule(String codeModule, String libelle, double dureeFFP, double dureeTE, Map<NumeroSemestre, Semestre> mapSemestres, Intervenant intervenant, UniteEnseignement ue) {
		return moduleRepository.save(new Module(codeModule, libelle, nomenclatureService.autoselectSemestre(codeModule, mapSemestres), Duration.ofMinutes((long) (dureeFFP * 60)),
				Duration.ofMinutes((long) (dureeTE * 60)), intervenant, ue));
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
		return moduleRepository.save(new Module(moduleParent.getCode(), libelle, moduleParent.getSemestre(), Duration.ofMinutes((long) (dureeFFP * 60)), Duration.ofMinutes((long) (dureeTE * 60)),
				intervenant, moduleParent.getUniteEnseignement()));
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
					module.setSemestre(moduleReference.getSemestre());
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
	public ProjetTransversal createProjetTransversal(String codeModule, String libelle, double dureeFFP, double dureeTE, Map<NumeroSemestre, Semestre> mapSemestres, Intervenant intervenant,
			BlocCompetences blocCompetences) {
		return projetTransversalRepository.save(new ProjetTransversal(codeModule, libelle, nomenclatureService.autoselectSemestre(codeModule, mapSemestres), Duration.ofMinutes((long) (dureeFFP * 60)),
				Duration.ofMinutes((long) (dureeTE * 60)), intervenant, blocCompetences));

	}

	@Override
	public void createCours(String dateHeureDebut, double duree, String codeModule, CodeSalle codeSalle) {
		coursRepository.save(new Cours(LocalDateTime.parse(dateHeureDebut, FormatterUtils.getDateTimeFormatterFR()), Duration.ofMinutes((long) (duree * 60)), moduleRepository.findByCode(codeModule),
				salleRepository.findByCodeSalle(codeSalle)));
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
