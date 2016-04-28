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
	
	private static final ManagerApp instance = new ManagerApp();
	private String typeTri = "id";
	private List<Article> listArticles = new ArrayList<>();
	private List<Commentaire> listCommentaires = new ArrayList<>();
	
	private ManagerApp() {}
	
	public void initialisation() {
		
		/**______________Partie obligatoire______________**/
		
		
		/*************************************************************/
		/**				Création des objets Metier					**/
		/*************************************************************/
		ResultSet rs = ManagerDAO.getBd().request(ManagerDAO.getDAOArticle().selectAll());
		
		try {
			while (rs.next()) {
				listArticles.add(new Article(rs.getInt("id"),rs.getString("titre"),rs.getString("contenu"),rs.getString("auteur"),new SimpleDateFormat("dd MMMM yyyy").format(rs.getDate("dateA"))));
			}
		
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void tri() {
		Collections.sort(listArticles);
	}
	
	//Méthodes de tri
	public String getTypeTri() {
		return typeTri;
	}
	
	public void triParId() {
		typeTri = "id";
		this.tri();
	}
	
	public void triParTitre() {
		typeTri = "titre";
		this.tri();
	}
	
	public void triParAuteur() {
		typeTri = "auteur";
		this.tri();
	}

	public static ManagerApp Instance() { return instance;	}

	public List<Article> getListArticles() { return listArticles; }

	public List<Commentaire> getListCommentaires() { return listCommentaires; }
}
