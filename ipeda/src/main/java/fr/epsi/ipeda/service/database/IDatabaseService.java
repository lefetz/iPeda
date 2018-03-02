package fr.epsi.ipeda.service.database;

import fr.epsi.ipeda.dao.entity.Formation.TypeFormation;
import fr.epsi.ipeda.dao.entity.Semaine;

public interface IDatabaseService {

	public void initialiserFormationB3();

	public void initialiserSalles();

	public void initialiserIntervenants();

	public void initialiserCoursB3();

	public void afficheCours();

	public void getPlanningBySemaine(TypeFormation typeFormation, Semaine semaine);

}
