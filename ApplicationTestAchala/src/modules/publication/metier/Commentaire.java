package modules.publication.metier;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import achala.datamanager.bdd.TypeBD;
import modules.publication.metier.Article;
import modules.publication.metier.Commentaire;
import modules.publication.metier.ManagerApp;
import modules.publication.dao.ManagerDAO;
import modules.publication.exception.PublicationException;

public class Commentaire  {
	
	/**
	 * Attributs privés
	 */
	private int id;
	private String contenu;
	private String auteur;
	private String date;
	private int idArticle;
	
	/**
	 * Constructeur
	 */
	public Commentaire(String unContenu, String unAuteur, String uneDate, int idArticle) {
		this.id = Commentaire.getIdCourant();
		this.contenu = unContenu;
		this.auteur = unAuteur;
		this.date = uneDate;
		this.idArticle = idArticle;
		try{
			this.creer();
			Article.getArticleById(idArticle).ajouterCommentaire(this);
		} catch(PublicationException e) {
			e.getMessage();
		}

	}
	
	public Commentaire(int unId, String unContenu, String unAuteur, String uneDate, int idArticle) {
		this.id = unId;
		this.contenu = unContenu;
		this.auteur = unAuteur;
		this.date = uneDate;
		this.idArticle = idArticle;
	}
	
	/**
	 * Méthodes
	 */
	public void creer() {
		//Mise à jour des données BD + Context
		try {
			//Exécution requête
			ManagerDAO.getBd().request(ManagerDAO.getDAOCommentaire().insert(this.id, this.date, this.contenu, this.auteur, this.idArticle));
			ManagerApp.Instance().getListCommentaires().add(this);
		} catch(Exception e) {
			e.getMessage();
		}
	}

	public void supprimer() {
		//Mise à jour des données BD + Context
		try {
			ManagerDAO.getBd().request(ManagerDAO.getDAOCommentaire().delete(this.id));
			ManagerApp.Instance().getListCommentaires().remove(this);
		} catch(Exception e) {
			e.getMessage();
		}
	}
	
	/**
	 * Getteurs / Accesseurs
	 */
	private static int getIdCourant() {
		ResultSet rs = ManagerDAO.getBd().request(ManagerDAO.getDAOCommentaire().selectMaxId());
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

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		HashMap<String, String> lstAttrsValues = new HashMap<>();
		lstAttrsValues.put("contenu", contenu);
		ManagerDAO.getBd().request(ManagerDAO.getDAOCommentaire().update(lstAttrsValues, "WHERE id = " + this.id));
		this.contenu = contenu;
	}

	public String getAuteur() {
		return auteur;
	}

	public void setAuteur(String auteur) {
		HashMap<String, String> lstAttrsValues = new HashMap<>();
		lstAttrsValues.put("auteur", auteur);
		ManagerDAO.getBd().request(ManagerDAO.getDAOCommentaire().update(lstAttrsValues, "WHERE id = " + this.id));
		this.auteur = auteur;
	}
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		HashMap<String, String> lstAttrsValues = new HashMap<>();
		lstAttrsValues.put("date", TypeBD.syntaxe(date, TypeBD.DATE));
		ManagerDAO.getBd().request(ManagerDAO.getDAOCommentaire().update(lstAttrsValues, "WHERE id = " + this.id));
		this.date = date;
	}
	
	public static Commentaire getCommentaireById(int id) {
		for(Commentaire com:ManagerApp.Instance().getListCommentaires()) {
			if(id == com.getId()) {
				return com;
			}
		}
		return null;
	}
	

}
