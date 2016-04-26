package application.publication.dao;
import java.sql.SQLException;
import java.util.HashMap;

import achala.datamanager.Bd;
import achala.datamanager.bdd.DAOTable;
import application.publication.dao.DAOArticle;
import application.publication.dao.DAOCommentaire;
import application.publication.dao.ManagerDAO;


public class ManagerDAO {

		private static ManagerDAO instance = new ManagerDAO();
		private static Bd bd;
		private static DAOArticle DAOArticle ;
		private static DAOCommentaire DAOCommentaire ;
		
		private ManagerDAO() {
			/**______________Partie obligatoire______________**/
			
			
			/*********************************/
			/**		Creation connexion bd	**/
			/*********************************/
			try {
				bd = new Bd("jdbc:oracle:thin:@im2ag-oracle.e.ujf-grenoble.fr:1521:im2ag", "ortizlu", "bd");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
			/*************************************************************/
			/**		Creation des objets DAO avec jointures (ou pas)		**/
			/*************************************************************/
			//Creation des tables sans cles etrangeres en premier
			DAOArticle = new DAOArticle(null);
			
			//Puis les tables avec cles etrangeres (meme systeme BDD)
			HashMap<DAOTable, String> lstJointuresTbl = new HashMap<>();
			lstJointuresTbl.put(DAOArticle, "Commentaire.article = Article.id");
			DAOCommentaire= new DAOCommentaire(lstJointuresTbl);
			
		}

		public static ManagerDAO getInstance() { return instance; }

		public static DAOArticle getDAOArticle() { return DAOArticle; }

		public static DAOCommentaire getDAOCommentaire() { return DAOCommentaire; }
		
		public static Bd getBd() { return bd; }
		
		
}


