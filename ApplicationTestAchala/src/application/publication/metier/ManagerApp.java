package achala.modules.publication.metier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import achala.modules.publication.dao.ManagerDAO;

public class ManagerApp {
	
	private static final ManagerApp instance = new ManagerApp();
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
				listArticles.add(new Article(rs.getInt("id"),rs.getString("titre"),rs.getString("contenu"),rs.getString("auteur"),rs.getDate("dateA")));
			}
		
			rs = ManagerDAO.getBd().request(ManagerDAO.getDAOCommentaire().selectAll());
			
			Commentaire com;
			while (rs.next()) {
				for(Article art : listArticles) {
					// Si l'article correspond bien
					if (art.getId() == rs.getInt("article")) {
						com = new Commentaire(rs.getInt("id"),rs.getString("contenu"),rs.getString("auteur"),rs.getDate("dateC"),rs.getInt("article"));
						if(listCommentaires.add(com)) {
							art.ajouterCommentaire(com);
						}
						break;
					}	
				}
			}
			
			rs.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static ManagerApp Instance() { return instance;	}

	public List<Article> getListArticles() { return listArticles; }
	
	public Article getArticleById(int id) {
		for(Article art:this.getListArticles()) {
			if(id == art.getId()) {
				return art;
			}
		}
		return null;
	}

	public List<Commentaire> getListCommentaires() { return listCommentaires; }
}
