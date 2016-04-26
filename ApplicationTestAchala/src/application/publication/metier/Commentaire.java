package achala.modules.publication.metier;

import java.sql.Date;
import java.util.HashMap;

import achala.datamanager.bdd.TypeBD;
import achala.modules.publication.dao.ManagerDAO;
//import achala.modules.publication.exception.PublicationException;
import achala.modules.publication.exception.PublicationException;

public class Commentaire {
	
	/**
	 * Attributs privés
	 */
	private int id;
	private String contenu;
	private String nomAuteur;
	private Date date;
	private int idArticle;
	
	/**
	 * Constructeur
	 */
	public Commentaire(int unId, String unContenu, String unAuteur, Date uneDate, int idArticle) {
		this.id = unId;
		this.contenu = unContenu;
		this.nomAuteur = unAuteur;
		this.date = uneDate;
		this.idArticle = idArticle;
		try{
			Article.getArticleById(idArticle).ajouterCommentaire(this);
		} catch(PublicationException e) {
			e.getMessage();
		}
	}
	
	/**
	 * Méthodes
	 */
	public void creer() {
		//APPEL DAO
		try {
			ManagerDAO.getDAOCommentaire().insert(this.id, this.date, this.contenu, this.nomAuteur, this.idArticle);
			ManagerApp.Instance().getListCommentaires().add(this);
		} catch(Exception e) {
			e.getMessage();
		}
	}

	public void supprimer() {
		//APPEL DAO
		try {
			ManagerDAO.getDAOCommentaire().delete(this.id);
			ManagerApp.Instance().getListCommentaires().remove(this);
		} catch(Exception e) {
			e.getMessage();
		}
	}
	
	/**
	 * Getteurs / Accesseurs
	 */
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
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		HashMap<String, String> lstAttrsValues = new HashMap<>();
		lstAttrsValues.put("date", TypeBD.syntaxe(date.toString(), TypeBD.DATE));
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
