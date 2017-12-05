package fr.epsi.ipeda.service.database;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.epsi.ipeda.dao.entity.Formation;
import fr.epsi.ipeda.dao.entity.Formation.TypeFormation;
import fr.epsi.ipeda.dao.entity.Intervenant;
import fr.epsi.ipeda.dao.entity.Module;
import fr.epsi.ipeda.dao.entity.Salle;
import fr.epsi.ipeda.dao.entity.Semestre;
import fr.epsi.ipeda.dao.entity.Semestre.NumeroSemestre;
import fr.epsi.ipeda.dao.entity.Specialite;
import fr.epsi.ipeda.dao.entity.Specialite.TypeParcours;
import fr.epsi.ipeda.dao.repository.FormationRepository;
import fr.epsi.ipeda.dao.repository.IntervenantRepository;
import fr.epsi.ipeda.dao.repository.ModuleRepository;
import fr.epsi.ipeda.dao.repository.SalleRepository;
import fr.epsi.ipeda.dao.repository.SemestreRepository;

@Service
public class DatabaseService implements IDatabaseService {

	@Autowired
	private SemestreRepository semestreRepository;

	@Autowired
	private FormationRepository formationRepository;

	@Autowired
	private SalleRepository salleRepository;

	@Autowired
	private IntervenantRepository intervenantRepository;

	@Autowired
	private ModuleRepository moduleRepository;

	@Override
	public void initialiserFormations() {

		Semestre semestre1 = null;
		Semestre semestre2 = null;

		// ------------------------------------------------
		// FORMATION B1
		// ------------------------------------------------
		// formation = new Formation();
		//
		// formation.setTypeFormation(TypeFormation.B1);
		// formation.setLibelle("BACHELOR 1");
		// formation.setDateDebut(LocalDate.of(2017, 9, 28)); // à modifier
		// formation.setDateFin(LocalDate.of(2018, 7, 20)); // à modifier

		// ------------------------------------------------
		// FORMATION B2
		// ------------------------------------------------
		// formation = new Formation();
		//
		// formation.setTypeFormation(TypeFormation.B2);
		// formation.setLibelle("BACHELOR 2");
		// formation.setDateDebut(LocalDate.of(2017, 9, 28)); // à modifier
		// formation.setDateFin(LocalDate.of(2018, 7, 20)); // à modifier

		// ------------------------------------------------
		// FORMATION B3
		// ------------------------------------------------

		// semestres
		semestre1 = new Semestre(NumeroSemestre.SEMESTRE1, LocalDate.of(2017, 9, 28), LocalDate.of(2017, 12, 31));
		semestre2 = new Semestre(NumeroSemestre.SEMESTRE2, LocalDate.of(2018, 1, 1), LocalDate.of(2018, 7, 20));

		// spécialités
		List<Specialite> listeSpecialites = new ArrayList<Specialite>();
		listeSpecialites.add(new Specialite(TypeParcours.SOCLE, "SOCLE"));
		listeSpecialites.add(new Specialite(TypeParcours.METIER, "DEV"));
		listeSpecialites.add(new Specialite(TypeParcours.METIER, "RESEAUX"));
		listeSpecialites.add(new Specialite(TypeParcours.COMPLEMENTAIRE, "SECU"));
		listeSpecialites.add(new Specialite(TypeParcours.COMPLEMENTAIRE, "VIRTU"));
		listeSpecialites.add(new Specialite(TypeParcours.COMPLEMENTAIRE, "DATA"));
		listeSpecialites.add(new Specialite(TypeParcours.PROFESSIONNEL, "PROFESSIONNEL"));

		// formation
		formationRepository.save(new Formation(TypeFormation.B3, "BACHELOR 3", semestre1, semestre2, listeSpecialites));

	}

	@Override
	public void initialiserSalles() {
		salleRepository.save(new Salle("jaune"));
		salleRepository.save(new Salle("rouge"));
		salleRepository.save(new Salle("verte"));
		salleRepository.save(new Salle("grise"));
		salleRepository.save(new Salle("tp-1"));
		salleRepository.save(new Salle("tp-2"));
		salleRepository.save(new Salle("bde"));
		salleRepository.save(new Salle("conférence"));
		salleRepository.save(new Salle("normandie"));
	}

	@Override
	public void initialiserIntervenants() {
		intervenantRepository.save(new Intervenant("deliessche", "christian"));
		intervenantRepository.save(new Intervenant("hardstaff", "debra"));
	}

	@Override
	public void initialiserModules() {

		// récupération des intervenants
		Intervenant aucun = null;
		Intervenant deliescche = intervenantRepository.findByNom("deliessche");
		Intervenant hardstaff = intervenantRepository.findByNom("hardstaff");

		// B3 - Semestre 1
		Formation formation = formationRepository.findByTypeFormation(TypeFormation.B3);
		Semestre semestre1 = semestreRepository.findByFormationAndNumeroSemestre(formation, NumeroSemestre.SEMESTRE1);

		// modules
		moduleRepository.save(new Module("DEVE539", "Sécurité Système et Réseaux - les fondamentaux", semestre1, Duration.ofHours(18), Duration.ofHours(2), deliescche));
		moduleRepository.save(new Module("LNGE627", "Case Study (usual English)", semestre1, Duration.ofHours(20), Duration.ofHours(0), hardstaff));

	}

}
