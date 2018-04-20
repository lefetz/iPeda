package fr.epsi.ipeda.model.service.nomenclature;

import java.util.Map;

import fr.epsi.ipeda.dal.entity.Semestre;
import fr.epsi.ipeda.dal.entity.Semestre.NumeroSemestre;

public interface INomenclatureService {

	/**
	 * Calcule le semestre correspondant au module spécifié en paramètres.
	 * 
	 * @param codeModule
	 *            Code du module concerné.
	 * @param mapSemestres
	 *            Map contenant les semestres de la formation concernée par le module.
	 * @return Le semestre sélectionné.
	 */
	Semestre autoselectSemestre(String codeModule, Map<NumeroSemestre, Semestre> mapSemestres);

}
