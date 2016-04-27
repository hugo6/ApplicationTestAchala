package application.publication.metier;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import achala.datamanager.bdd.TypeBD;
import application.publication.dao.ManagerDAO;
import application.publication.exception.PublicationException;

public class Commentaire {
	
	/**
	 * Attributs privés
	 */
	private int id;
	private String contenu;
	private String nomAuteur;
	private String date;
	private int idArticle;
	
	/**
	 * Constructeur
	 */
	public Commentaire(String unContenu, String unAuteur, String uneDate, int idArticle) {
		this.id = Commentaire.getIdCourant();
		this.contenu = unContenu;
		this.nomAuteur = unAuteur;
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
		this.nomAuteur = unAuteur;
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
			ManagerDAO.getBd().request(ManagerDAO.getDAOCommentaire().insert(this.id, this.date, this.contenu, this.nomAuteur, this.idArticle));
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
		return nomAuteur;
	}

	public void setAuteur(String auteur) {
		HashMap<String, String> lstAttrsValues = new HashMap<>();
		lstAttrsValues.put("auteur", auteur);
		ManagerDAO.getBd().request(ManagerDAO.getDAOCommentaire().update(lstAttrsValues, "WHERE id = " + this.id));
		this.nomAuteur = auteur;
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
