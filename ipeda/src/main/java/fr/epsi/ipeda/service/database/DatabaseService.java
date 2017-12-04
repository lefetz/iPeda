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

		Formation formation = null;

		// ------------------------------------------------
		// FORMATION B1
		// ------------------------------------------------
		formation = new Formation();

		formation.setTypeFormation(TypeFormation.B1);
		formation.setLibelle("BACHELOR 1");
		formation.setDateDebut(LocalDate.of(2017, 9, 28)); // à modifier
		formation.setDateFin(LocalDate.of(2018, 7, 20)); // à modifier

		// ------------------------------------------------
		// FORMATION B2
		// ------------------------------------------------
		formation = new Formation();

		formation.setTypeFormation(TypeFormation.B2);
		formation.setLibelle("BACHELOR 2");
		formation.setDateDebut(LocalDate.of(2017, 9, 28)); // à modifier
		formation.setDateFin(LocalDate.of(2018, 7, 20)); // à modifier

		// ------------------------------------------------
		// FORMATION B3
		// ------------------------------------------------
		formation = new Formation();

		formation.setTypeFormation(TypeFormation.B3);
		formation.setLibelle("BACHELOR 3");
		formation.setDateDebut(LocalDate.of(2017, 9, 28));
		formation.setDateFin(LocalDate.of(2018, 7, 20));

		// SPECIALITES
		// ------------------------------------------------
		List<Specialite> listeSpecialites = new ArrayList<Specialite>();

		// SOCLE
		Specialite specialite = new Specialite();
		specialite.setTypeParcours(TypeParcours.SOCLE);
		specialite.setFormation(formation);
		specialite.setLibelle("SOCLE");
		listeSpecialites.add(specialite);

		// M-DEV
		specialite = new Specialite();
		specialite.setTypeParcours(TypeParcours.METIER);
		specialite.setFormation(formation);
		specialite.setLibelle("DEV");
		listeSpecialites.add(specialite);

		// M-RESEAUX
		specialite = new Specialite();
		specialite.setTypeParcours(TypeParcours.METIER);
		specialite.setFormation(formation);
		specialite.setLibelle("RESEAUX");
		listeSpecialites.add(specialite);

		// C-SECU
		specialite = new Specialite();
		specialite.setTypeParcours(TypeParcours.COMPLEMENTAIRE);
		specialite.setFormation(formation);
		specialite.setLibelle("SECU");
		listeSpecialites.add(specialite);

		// C-VIRTU
		specialite = new Specialite();
		specialite.setTypeParcours(TypeParcours.COMPLEMENTAIRE);
		specialite.setFormation(formation);
		specialite.setLibelle("VIRTU");
		listeSpecialites.add(specialite);

		// C-DATA
		specialite = new Specialite();
		specialite.setTypeParcours(TypeParcours.COMPLEMENTAIRE);
		specialite.setFormation(formation);
		specialite.setLibelle("DATA");
		listeSpecialites.add(specialite);

		// PROFESSIONNEL
		specialite = new Specialite();
		specialite.setTypeParcours(TypeParcours.PROFESSIONNEL);
		specialite.setFormation(formation);
		specialite.setLibelle("PROFESSIONNEL");
		listeSpecialites.add(specialite);

		formation.setListeSpecialites(listeSpecialites);

		// SEMESTRES
		// ------------------------------------------------
		Semestre semestre = new Semestre();
		semestre.setNumeroSemestre(NumeroSemestre.SEMESTRE1);
		semestre.setDateDebut(LocalDate.of(2017, 9, 28));
		semestre.setDateFin(LocalDate.of(2017, 12, 31));
		semestre = semestreRepository.save(semestre);
		formation.addSemestre1(semestre);

		semestre = new Semestre();
		semestre.setNumeroSemestre(NumeroSemestre.SEMESTRE2);
		semestre.setDateDebut(LocalDate.of(2018, 1, 1));
		semestre.setDateFin(LocalDate.of(2018, 7, 20));
		semestre = semestreRepository.save(semestre);
		formation.addSemestre2(semestre);

		// persistance des données
		formationRepository.save(formation);

	}

	@Override
	public void initialiserSalles() {

		// jaune
		Salle salle = new Salle();
		salle.setLibelle("jaune");
		salleRepository.save(salle);

		// rouge
		salle = new Salle();
		salle.setLibelle("rouge");
		salleRepository.save(salle);

		// verte
		salle = new Salle();
		salle.setLibelle("verte");
		salleRepository.save(salle);

		// grise
		salle = new Salle();
		salle.setLibelle("grise");
		salleRepository.save(salle);

		// tp1
		salle = new Salle();
		salle.setLibelle("tp-1");
		salleRepository.save(salle);

		// bde
		salle = new Salle();
		salle.setLibelle("bde");
		salleRepository.save(salle);

		// tp2
		salle = new Salle();
		salle.setLibelle("tp-2");
		salleRepository.save(salle);

		// conf
		salle = new Salle();
		salle.setLibelle("conférence");
		salleRepository.save(salle);

		// normandie
		salle = new Salle();
		salle.setLibelle("normandie");
		salleRepository.save(salle);

	}

	@Override
	public void initialiserIntervenants() {

		Intervenant intervenant = new Intervenant();
		intervenant.setNom("deliessche");
		intervenant.setPrenom("christian");
		intervenantRepository.save(intervenant);

		intervenant = new Intervenant();
		intervenant.setNom("hardstaff");
		intervenant.setPrenom("debra");
		intervenantRepository.save(intervenant);

	}

	@Override
	public void initialiserModules() {

		// B3 - Semestre 1
		Formation formation = formationRepository.findByTypeFormation(TypeFormation.B3);
		Semestre semestre = semestreRepository.findByFormationAndNumeroSemestre(formation, NumeroSemestre.SEMESTRE1);

		// 539
		Module module = new Module();
		module.setCode("DEVE539");
		module.setLibelle("Sécurité Système et Réseaux - les fondamentaux");
		module.setSemestre(semestre);
		module.setDureeFFP(Duration.ofHours(18));
		module.setDureeTE(Duration.ofHours(2));
		Intervenant intervenant = intervenantRepository.findByNomAndPrenom("deliessche", "christian");
		module.setIntervenant(intervenant);
		moduleRepository.save(module);

		// 627
		module = new Module();
		module.setCode("LNGE627");
		module.setLibelle("Case Study (usual English)");
		module.setSemestre(semestre);
		module.setDureeFFP(Duration.ofHours(20));
		module.setDureeTE(Duration.ofHours(0));
		intervenant = intervenantRepository.findByNomAndPrenom("hardstaff", "debra");
		module.setIntervenant(intervenant);
		moduleRepository.save(module);

	}

}
