package modules.publication.metier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import modules.publication.metier.Article;
import modules.publication.metier.Commentaire;
import modules.publication.metier.ManagerApp;
import modules.publication.dao.ManagerDAO;

public class ManagerApp {
	
	// ATTRIBUTS PRIVES
	private static final ManagerApp instance = new ManagerApp();
	private String typeTri = "id";
	private List<Article> listArticles;
	private List<Commentaire> listCommentaires;
	
	/**
	 * Constructeur par défaut
	 */
	private ManagerApp() {}
	
	
	// METHODES
	/**
	 * Methode d'initialisation de l'instance du ManagerApp
	 * Alimente les listes lesArticles et lesCommentaires des enregistrements issus de la BDD
	 */
	public void initialisation() {
		/*************************************************************/
		/**				Création des objets Metier					**/
		/*************************************************************/
		this.listArticles = new ArrayList<>();
		this.listCommentaires = new ArrayList<>();
		
		// Execution requete de selection de tous les articles
		ResultSet rs = ManagerDAO.getBd().request(ManagerDAO.getDAOArticle().selectAll());
		
		try {
			while (rs.next()) {
				listArticles.add(new Article(rs.getInt("id"),rs.getString("titre"),rs.getString("contenu"),rs.getString("auteur"),new SimpleDateFormat("dd MMMM yyyy").format(rs.getDate("dateA"))));
			}
			// Execution requete de selection de tous les commentaires
			rs = ManagerDAO.getBd().request(ManagerDAO.getDAOCommentaire().selectAll());
			
			Commentaire com;
			while (rs.next()) {
				for(Article art : listArticles) {
					// Si l'article correspond bien
					if (art.getId() == rs.getInt("article")) {
						com = new Commentaire(rs.getInt("id"),rs.getString("contenu"),rs.getString("auteur"),new SimpleDateFormat("dd MMMM yyyy").format(rs.getDate("dateC")),rs.getInt("article"));
						if(listCommentaires.add(com)) {
							art.ajouterCommentaire(com);
						}
						break;
					}	
				}
			}
			this.tri();
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//TRIS
	/**
	 * Tri la liste d'articles en fonction de la valeur de l'attribut typeTri grace a la methode compareTo redefinie dans la classe Article
	 */
	private void tri() {
		Collections.sort(listArticles);
	}
	
	/**
	 * Appelle la methode tri ci-dessus en indiquant que le tri se fera sur l'attribut id des articles
	 */
	public void triParId() {
		typeTri = "id";
		this.tri();
	}
	
	/**
	 * Appelle la methode tri ci-dessus en indiquant que le tri se fera sur l'attribut titre des articles
	 */
	public void triParTitre() {
		typeTri = "titre";
		this.tri();
	}
	
	/**
	 * Appelle la methode tri ci-dessus en indiquant que le tri se fera sur l'attribut auteur des articles
	 */
	public void triParAuteur() {
		typeTri = "auteur";
		this.tri();
	}

	// ACCESSEURS
	/**
	 * Accesseur instance ManagerApp
	 * @return l'instance du ManagerApp
	 */
	public static ManagerApp Instance() { return instance;	}

	/**
	 * Accesseur typeTri ManagerApp
	 * @return la chaine de caracteres donnant le type de tri indique
	 */
	public String getTypeTri() {
		return typeTri;
	}
	
	/**
	 * Accesseur listArticles ManagerApp
	 * @return un objet List contenant tous les objets articles
	 */
	public List<Article> getListArticles() { return listArticles; }

	/**
	 * Accesseur listCommentaires ManagerApp
	 * @return un objet List contenant tous les objets commentaires
	 */
	public List<Commentaire> getListCommentaires() { return listCommentaires; }
}
