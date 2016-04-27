package application.publication.metier;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import achala.datamanager.bdd.TypeBD;
import application.publication.dao.ManagerDAO;
import application.publication.exception.PublicationException;


public class Article {

	/**
	 * Attributs privés
	 */
	private int id;
	private String titre;
	private String contenu;
	private String nomAuteur;
	private String date;
	private ArrayList<Commentaire> lesCommentaires;

	/**
	 * Constructeur sans id
	 */
	public Article(String unTitre, String unContenu, String unNomAuteur, String uneDate) {
		this.id = Article.getIdCourant();
		this.titre = unTitre;
		this.contenu = unContenu;
		this.nomAuteur = unNomAuteur;
		this.date = uneDate;
		this.lesCommentaires = new ArrayList<Commentaire>();
		creer();
	}
	
	/**
	 * Constructeur complet
	 */
	public Article(int unId, String unTitre, String unContenu, String unNomAuteur, String uneDate) {
		this.id = unId;
		this.titre = unTitre;
		this.contenu = unContenu;
		this.nomAuteur = unNomAuteur;
		this.date = uneDate;
		this.lesCommentaires = new ArrayList<Commentaire>();
	}
	
	/**
	 * Methodes
	 */
	public void creer() {
		//Mise à jour des données BD + Context
		try {
			ManagerDAO.getBd().request(ManagerDAO.getDAOArticle().insert(this.id, this.date, this.titre, this.contenu, this.nomAuteur));
			ManagerApp.Instance().getListArticles().add(this);
		} catch(Exception e) {
			e.getMessage();
		}
	}
	
	public void supprimer() {
		//Mise à jour des données BD + Context
		try {
			for(Commentaire com : lesCommentaires) {
				com.supprimer();
			}
			ManagerDAO.getBd().request(ManagerDAO.getDAOArticle().delete(this.id));
			ManagerApp.Instance().getListArticles().remove(this);
		} catch(Exception e) {
			e.getMessage();
		}
	}
	
	//Ajout du commentaire en base puis ajout dans la liste
	public void ajouterCommentaire(Commentaire com) {
		this.lesCommentaires.add(com);
	}
	
	//Suppression du commentaire en base puis suppression dans la liste
	public void supprimerCommentaire(Commentaire com) {
		com.supprimer();
		this.lesCommentaires.remove(com);
	}
	
	/**
	 * Getteurs / Accesseurs
	 */
	private static int getIdCourant() {
		ResultSet rs = ManagerDAO.getBd().request(ManagerDAO.getDAOArticle().selectMaxId());
		try {
			if(rs.next()) {
				return rs.getInt(1)+1;
			}
		} catch(SQLException e){
			e.getMessage();
		}
		return 1;
	}
	
	//Pas de setId possible
	public int getId() {
		return id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		HashMap<String, String> lstAttrsValues = new HashMap<>();
		lstAttrsValues.put("titre", titre);
		ManagerDAO.getBd().request(ManagerDAO.getDAOArticle().update(lstAttrsValues, "WHERE id = " + this.id));
		this.titre = titre;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		HashMap<String, String> lstAttrsValues = new HashMap<>();
		lstAttrsValues.put("contenu", contenu);
		ManagerDAO.getBd().request(ManagerDAO.getDAOArticle().update(lstAttrsValues, "WHERE id = " + this.id));
		this.contenu = contenu;
	}

	public String getNomAuteur() {
		return nomAuteur;
	}

	public void setNomAuteur(String nomAuteur) {
		HashMap<String, String> lstAttrsValues = new HashMap<>();
		lstAttrsValues.put("nomAuteur", nomAuteur);
		ManagerDAO.getBd().request(ManagerDAO.getDAOArticle().update(lstAttrsValues, "WHERE id = " + this.id));
		this.nomAuteur = nomAuteur;
	}
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		HashMap<String, String> lstAttrsValues = new HashMap<>();
		lstAttrsValues.put("date", TypeBD.syntaxe(date, TypeBD.DATE));
		ManagerDAO.getBd().request(ManagerDAO.getDAOArticle().update(lstAttrsValues, "WHERE id = " + this.id));
		this.date = date;
	}

	//Pas de set commentaires possible
	public ArrayList<Commentaire> getLesCommentaires() {
		return lesCommentaires;
	}
	
	public static Article getArticleById(int id) throws PublicationException {
		for(Article art:ManagerApp.Instance().getListArticles()) {
			if(id == art.getId()) {
				return art;
			}
		}
		throw new PublicationException("L'article n'existe pas.");
	}
}
