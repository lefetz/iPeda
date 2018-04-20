package fr.epsi.ipeda.dal.service;

import java.util.ArrayList;
import java.util.Map;

import fr.epsi.ipeda.dal.entity.BlocCompetences;
import fr.epsi.ipeda.dal.entity.Intervenant;
import fr.epsi.ipeda.dal.entity.Module;
import fr.epsi.ipeda.dal.entity.ProjetTransversal;
import fr.epsi.ipeda.dal.entity.Semaine;
import fr.epsi.ipeda.dal.entity.Semestre;
import fr.epsi.ipeda.dal.entity.UniteEnseignement;
import fr.epsi.ipeda.dal.entity.Formation.TypeFormation;
import fr.epsi.ipeda.dal.entity.Semestre.NumeroSemestre;

public interface IDatabaseService {

	public void initialiserSalles();

	public void initialiserIntervenants();

	public void afficheCours();

	public void getPlanningBySemaine(TypeFormation typeFormation, Semaine semaine);

	Module createModule(String codeModule, String libelle, double dureeFFP, double dureeTE, Map<NumeroSemestre, Semestre> mapSemestres, Intervenant intervenant, UniteEnseignement ue);

	void createModuleCompose(Module moduleParent, Module... modules);

	void createCours(String dateHeureDebut, double duree, String codeModule, Long salleId);

	void createModuleMutualise(String libelle, ArrayList<Module> listeModulesMutualises);

	public Module createModule(Module moduleParent, String libelle, double dureeFFP, double dureeTE, Intervenant intervenant);

	ProjetTransversal createProjetTransversal(String codeModule, String libelle, double dureeFFP, double dureeTE, Map<NumeroSemestre, Semestre> mapSemestres, Intervenant intervenant,
			BlocCompetences blocCompetences);

}
