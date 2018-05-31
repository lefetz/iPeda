package fr.epsi.ipeda.dal.dto;

public class CreneauDTO {

	private String groupe;
	private String semaine;
	private String date;
	private String jour;
	private String heureDebut;
	private String heureFin;
	private String dureePrevue;
	private String dureeModule;
	private String specialite;
	private String libelleModule;
	private String intervenant;
	private String salle;
	private String remarque;
	private boolean isWeekEnd;

	public String getGroupe() {
		return groupe;
	}

	public void setGroupe(String groupe) {
		this.groupe = groupe;
	}

	public String getSemaine() {
		return semaine;
	}

	public void setSemaine(String semaine) {
		this.semaine = semaine;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getJour() {
		return jour;
	}

	public void setJour(String jour) {
		this.jour = jour;
	}

	public String getHeureDebut() {
		return heureDebut;
	}

	public void setHeureDebut(String heureDebut) {
		this.heureDebut = heureDebut;
	}

	public String getHeureFin() {
		return heureFin;
	}

	public void setHeureFin(String heureFin) {
		this.heureFin = heureFin;
	}

	public String getDureePrevue() {
		return dureePrevue;
	}

	public void setDureePrevue(String dureePrevue) {
		this.dureePrevue = dureePrevue;
	}

	public String getDureeModule() {
		return dureeModule;
	}

	public void setDureeModule(String dureeModule) {
		this.dureeModule = dureeModule;
	}

	public String getSpecialite() {
		return specialite;
	}

	public void setSpecialite(String specialite) {
		this.specialite = specialite;
	}

	public String getLibelleModule() {
		return libelleModule;
	}

	public void setLibelleModule(String libelleModule) {
		this.libelleModule = libelleModule;
	}

	public String getIntervenant() {
		return intervenant;
	}

	public void setIntervenant(String intervenant) {
		this.intervenant = intervenant;
	}

	public String getSalle() {
		return salle;
	}

	public void setSalle(String salle) {
		this.salle = salle;
	}

	public String getRemarque() {
		return remarque;
	}

	public void setRemarque(String remarque) {
		this.remarque = remarque;
	}

	public boolean getWeekEnd() {
		return isWeekEnd;
	}

	public void setWeekEnd(boolean isWeekEnd) {
		this.isWeekEnd = isWeekEnd;
	}

}
