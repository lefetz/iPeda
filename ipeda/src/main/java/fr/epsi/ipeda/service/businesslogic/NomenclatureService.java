package fr.epsi.ipeda.service.businesslogic;

import java.lang.invoke.MethodHandles;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import fr.epsi.ipeda.dao.entity.Semestre;
import fr.epsi.ipeda.dao.entity.Semestre.NumeroSemestre;

@Service
public class NomenclatureService implements INomenclatureService {

	final static Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Value("${module.nomenclature.longueur}")
	private int moduleNomenclatureLongueur;

	private boolean isCodeModuleValide(String codeModule) {
		boolean result = true;
		if (null == codeModule) {
			result = false;
			logger.error("codeModule est NULL");
		} else if (codeModule.length() != moduleNomenclatureLongueur) {
			result = false;
			logger.error("La longueur du codeModule '" + codeModule + "' est différente de la longueur attendue (" + moduleNomenclatureLongueur + ")");
		}
		return result;
	}

	@Override
	public Semestre autoselectSemestre(String codeModule, Map<NumeroSemestre, Semestre> mapSemestres) {
		int tailleMapSemestres = 2;
		if (isCodeModuleValide(codeModule) && null != mapSemestres && mapSemestres.size() == tailleMapSemestres) {
			int semestre = Integer.parseInt(codeModule.substring(4, 5));
			if (semestre % 2 != 0) {
				return mapSemestres.get(Semestre.NumeroSemestre.SEMESTRE1);
			} else {
				return mapSemestres.get(Semestre.NumeroSemestre.SEMESTRE2);
			}
		} else {
			logger.error("'mapSemestres' est NULL ou n'a pas la longueur requise (" + tailleMapSemestres + ")");
		}
		return null;
	}

}
