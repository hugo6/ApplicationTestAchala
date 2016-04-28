package modules.publication.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import achala.datamanager.bdd.Create;
import achala.datamanager.bdd.DAOTable;
import achala.datamanager.bdd.Delete;
import achala.datamanager.bdd.Drop;
import achala.datamanager.bdd.Insert;
import achala.datamanager.bdd.Requete;
import achala.datamanager.bdd.Select;
import achala.datamanager.bdd.TypeBD;
import achala.datamanager.bdd.Update;

public class DAOCommentaire extends DAOTable {

	protected DAOCommentaire(Map<DAOTable, String> jointures) {
		super(jointures);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void initialisation() {
		this.setNomTable("Commentaire");
		
		Map<String, TypeBD> lstAttrs = new HashMap<>();
		lstAttrs.put("id", TypeBD.INTEGER);
		lstAttrs.put("dateC", TypeBD.DATE);
		lstAttrs.put("contenu", TypeBD.STRING);
		lstAttrs.put("auteur", TypeBD.STRING);
		lstAttrs.put("article", TypeBD.INTEGER);
		
		this.setAttributs(lstAttrs);
	}
	
	
	/** Creation de la requete */
	@Override
	public Requete createTable() {
		return new Create("CREATE TABLE Commentaire ("
							+ "id INTEGER PRIMARY KEY,"
							+ "dateC DATE,"
							+ "contenu VARCHAR2(250),"
							+ "auteur VARCHAR2(250), "
							+ "article INTEGER NOT NULL, "
							+ "CONSTRAINT fk FOREIGN KEY (article) REFERENCES Article(id)"
						+ ")");
	}

	
	
	/**
	 * Permet de modifier une ou plusieurs lignes dans la table "Commentaire"
	 * @param lstAttrsValues La liste des attributs et leurs valeurs pour modification
	 * @param where La clause WHERE de la requete
	 * @return La requete a executer (objet)
	 */
	public Requete update(HashMap<String, String> lstAttrsValues, String where) {		
		return new Update(lstAttrsValues, ManagerDAO.getDAOCommentaire(), where);
	}
	
	
	
	/**
	 * Permet d'inserer une ligne dans la table "Commentaire"
	 * @param id Le premier attribut
	 * @param contenu Le second attribut
	 * @param auteur Le troisieme attributt
	 * @return La requete a executer (objet)
	 */
	public Requete insert(int id, String date, String contenu, String auteur, int idArticle) {
		HashMap<String, String> lstAttrsValue = new HashMap<>();
		lstAttrsValue.put("id", String.valueOf(id));
		lstAttrsValue.put("dateC", date);
		lstAttrsValue.put("contenu", contenu);
		lstAttrsValue.put("auteur", auteur);
		lstAttrsValue.put("article", String.valueOf(idArticle));
		
		return new Insert(lstAttrsValue, ManagerDAO.getDAOCommentaire());
	}

	
	/**
	 * Permet de supprimer la table en base
	 * @return La requete a executer (objet)
	 */
	public Requete drop() {		
		return new Drop(ManagerDAO.getDAOCommentaire());
	}

	
	/**
	 * Permet de supprimer la ligne en base
	 * @return La requete a executer (objet)
	 */
	public Requete delete(int id) {		
		return new Delete(ManagerDAO.getDAOCommentaire(), "WHERE id = " + id);
	}
	
	
	/**
	 * Permet de recuperer tout les commentaires
	 * @return La requete a executer (objet)
	 */
	public Requete selectAll() {
		List<DAOTable> lstTables = new ArrayList<>();
		lstTables.add(ManagerDAO.getDAOCommentaire());
		
		List<String> lstAttrs = new ArrayList<>();
		lstAttrs.add("*");
		
		return new Select(lstAttrs, lstTables);
	}
	
	/**
	 * Permet de recuperer l'id max courante des commentaires
	 * @return La requete a executer (objet)
	 */
	public Requete selectMaxId() {
		List<DAOTable> lstTables = new ArrayList<>();
		lstTables.add(ManagerDAO.getDAOCommentaire());
		
		List<String> lstAttrs = new ArrayList<>();
		lstAttrs.add("MAX(id)");
		
		return new Select(lstAttrs, lstTables);
	}
	
}
