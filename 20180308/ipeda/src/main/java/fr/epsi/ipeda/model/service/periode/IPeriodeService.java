package fr.epsi.ipeda.model.service.periode;

import fr.epsi.ipeda.dal.entity.Seance;
import fr.epsi.ipeda.dal.entity.periode.Periode;

public interface IPeriodeService {

	Periode findPeriode(Seance seance);

	Periode testbidule();

}
