package application.utils;

import java.util.Date;

public class Commentaire {

	
	/*
	 * Recuperation des differents elements permettant de creer un commentaire
	 * 
	 */
	private String nom, prenom;
	private String date;
	private String contenu;
	// private Date date0;

	public Commentaire(String nom, String prenom, String date, String contenu) {
		this.nom = nom;
		this.prenom = prenom;
		this.date = date;
		this.contenu = contenu;
	}
	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public String getDate() {
		return date;
	}

	public String getContenu() {
		return contenu;
	}



	/*
	 * public Commentaire(String nom , String prenom, Date date) { this.nom =
	 * nom ; this.prenom = prenom; date0 = date ; }
	 */
}
