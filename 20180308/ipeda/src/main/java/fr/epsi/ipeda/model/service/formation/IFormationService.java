package fr.epsi.ipeda.model.service.formation;

import fr.epsi.ipeda.dal.entity.Formation;
import fr.epsi.ipeda.dal.entity.Formation.TypeFormation;

public interface IFormationService {
	
	public Formation getFormation(TypeFormation typeFormation); 
	public Formation getFormation(TypeFormation typeFormation, int annee); 

}
