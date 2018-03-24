package fr.epsi.ipeda.service.businesslogic.formation;

import fr.epsi.ipeda.dao.entity.Formation;
import fr.epsi.ipeda.dao.entity.Formation.TypeFormation;

public interface IFormationService {
	
	public Formation getFormation(TypeFormation typeFormation); 
	public Formation getFormation(TypeFormation typeFormation, int annee); 

}
