package fr.epsi.ipeda.service.database;

import java.util.ArrayList;
import java.util.Map;

import fr.epsi.ipeda.dao.entity.BlocCompetences;
import fr.epsi.ipeda.dao.entity.Formation.TypeFormation;
import fr.epsi.ipeda.dao.entity.Intervenant;
import fr.epsi.ipeda.dao.entity.Module;
import fr.epsi.ipeda.dao.entity.ProjetTransversal;
import fr.epsi.ipeda.dao.entity.Salle.CodeSalle;
import fr.epsi.ipeda.dao.entity.Semaine;
import fr.epsi.ipeda.dao.entity.Semestre;
import fr.epsi.ipeda.dao.entity.Semestre.NumeroSemestre;
import fr.epsi.ipeda.dao.entity.UniteEnseignement;

public interface IDatabaseService {

	public void initialiserSalles();

	public void initialiserIntervenants();

	public void afficheCours();

	public void getPlanningBySemaine(TypeFormation typeFormation, Semaine semaine);

	Module createModule(String codeModule, String libelle, double dureeFFP, double dureeTE, Map<NumeroSemestre, Semestre> mapSemestres, Intervenant intervenant, UniteEnseignement ue);

	void createModuleCompose(Module moduleParent, Module... modules);

	void createCours(String dateHeureDebut, double duree, String codeModule, CodeSalle codeSalle);

	void createModuleMutualise(String libelle, ArrayList<Module> listeModulesMutualises);

	public Module createModule(Module moduleParent, String libelle, double dureeFFP, double dureeTE, Intervenant intervenant);

	ProjetTransversal createProjetTransversal(String codeModule, String libelle, double dureeFFP, double dureeTE, Map<NumeroSemestre, Semestre> mapSemestres, Intervenant intervenant,
			BlocCompetences blocCompetences);

}
