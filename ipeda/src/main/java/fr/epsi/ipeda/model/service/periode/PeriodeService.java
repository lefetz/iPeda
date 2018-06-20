package fr.epsi.ipeda.model.service.periode;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.epsi.ipeda.dal.entity.Seance;
import fr.epsi.ipeda.dal.entity.periode.Periode;
import fr.epsi.ipeda.dal.repository.periode.PeriodeRepository;

@Service
public class PeriodeService implements IPeriodeService {

	@Autowired
	private PeriodeRepository periodeRepository;

	@Override
	public Periode findPeriode(Seance seance) {
		LocalDateTime dateTimeDebut = LocalDateTime.of(seance.getDate(), seance.getHeureDebut());
		LocalDateTime dateTimeFin = LocalDateTime.of(seance.getDate(), seance.getHeureFin());
		return periodeRepository.findByDatetime(dateTimeDebut, dateTimeFin);
	}

	@Override
	public Periode testbidule() {
		return periodeRepository.testbidule();
	}

}
