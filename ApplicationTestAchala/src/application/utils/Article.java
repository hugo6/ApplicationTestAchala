package application.utils;

import java.util.ArrayList;
import java.util.List;

public class Article {

	private String nomAuteur, prenomAuteur;
	private String titre;
	private String contenu;
	private String date;
	private List<Commentaire> commentaireList;
	

	public void setArticlesList(List<Article> articlesList) {
		this.articlesList = articlesList;
	}
	private List<Article> articlesList = new ArrayList<Article>();


	public Article(String nom, String prenom, String titre, String contenu,String date, List<Commentaire> commentaireList) {
		this.setCommentaireList(new ArrayList<Commentaire>());
		this.setCommentaireList(commentaireList);
		nomAuteur = nom;
		prenomAuteur = prenom;
		this.titre = titre;
		this.contenu = contenu;
		this.date = date ; 
	}
	public Article() {}

	public String getNomAuteur() {
		return nomAuteur;
	}

	public String getPrenomAuteur() {
		return prenomAuteur;
	}

	public String getTitre() {
		return titre;
	}

	public String getContenu() {
		return contenu;
	}

	public List<Commentaire> getCommentaireList() {
		return commentaireList;
	}
	public String getDate() {
		return date;
	}
	public List<Article> getArticlesList() {
		return articlesList;
	}
	public void setCommentaireList(List<Commentaire> commentaireList) {
		this.commentaireList = commentaireList;
	}
}
