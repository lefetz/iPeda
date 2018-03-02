package fr.epsi.ipeda.dao.entity;

public class Semaine {

	private int annee;
	private int numero;

	public Semaine() {
	}

	public Semaine(int annee, int numero) {
		this.annee = annee;
		this.numero = numero;
	}

	public int getAnnee() {
		return annee;
	}

	public void setAnnee(int annee) {
		this.annee = annee;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

}
