package fr.epsi.ipeda.dal.service;

import java.util.ArrayList;

import fr.epsi.ipeda.dal.entity.BlocCompetences;
import fr.epsi.ipeda.dal.entity.Intervenant;
import fr.epsi.ipeda.dal.entity.Module;
import fr.epsi.ipeda.dal.entity.ProjetTransversal;
import fr.epsi.ipeda.dal.entity.UniteEnseignement;

public interface IDatabaseService {

	public void initialiserSalles();

	public void initialiserIntervenants();

	public void afficheCours();

	Module createModule(String codeModule, String libelle, double dureeFFP, double dureeTE, Intervenant intervenant, UniteEnseignement ue);

	Module createModule(Module moduleParent, String libelle, double dureeFFP, double dureeTE, Intervenant intervenant);

	void createModuleCompose(Module moduleParent, Module... modules);

	void createModuleMutualise(String libelle, ArrayList<Module> listeModulesMutualises);

	ProjetTransversal createProjetTransversal(String codeModule, String libelle, double dureeFFP, double dureeTE, Intervenant intervenant, BlocCompetences blocCompetences);

	void createCours(String dateHeureDebut, double duree, String codeModule, Long salleId);

	public void initialiserAnneeScolaire();

	public void initialiserFormation();

	public void initialiserSpecificite();

	public void initialiserParcours();

	public void initialiserBlocDeCompetence();

	public void initialiserPeriodeType();

}
