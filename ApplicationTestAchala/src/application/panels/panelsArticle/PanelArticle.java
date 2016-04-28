package application.panels.panelsArticle;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import modules.publication.metier.Article;
import modules.publication.metier.ManagerApp;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelArticle extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panelArticles;


	/**
	 * Creation d'un panel qui va afficher les appercu des differents articles.
	 * Chaque article ainsi que ces commentaires correspondant sont consultable
	 * en cliquant sur l'appercu
	 */
	public PanelArticle(String nomUser, String prenomUser) {

		this.setBackground(Color.LIGHT_GRAY);
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		JPanel panelTitre = new JPanel();
		panelTitre.setBackground(Color.LIGHT_GRAY);
		add(panelTitre);
		panelTitre.setLayout(new BoxLayout(panelTitre, BoxLayout.X_AXIS));

		JLabel lblArticles = new JLabel("Articles");
		panelTitre.add(lblArticles);
		lblArticles.setHorizontalAlignment(SwingConstants.CENTER);
		lblArticles.setFont(new Font("Tahoma", Font.BOLD, 21));

		JPanel panelGlue = new JPanel();
		panelGlue.setBackground(Color.LIGHT_GRAY);
		add(panelGlue);
		panelGlue.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblTrierLesArticles = new JLabel("Trier les articles par : ");
		panelGlue.add(lblTrierLesArticles);
		
		JButton btnTitre = new JButton("Titre");
		btnTitre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelArticles.removeAll();
				ManagerApp.Instance().triParTitre();
				affichageApercuArticles(nomUser,prenomUser);
				panelArticles.validate();
			}
		});
		panelGlue.add(btnTitre);
		
		JButton btnDate = new JButton("Date");
		btnDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelArticles.removeAll();
				ManagerApp.Instance().triParId();
				affichageApercuArticles(nomUser,prenomUser);
				panelArticles.validate();
			}
		});
		panelGlue.add(btnDate);
		
		JButton btnAuteur = new JButton("Auteur");
		btnAuteur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelArticles.removeAll();
				ManagerApp.Instance().triParAuteur();
				affichageApercuArticles(nomUser,prenomUser);
				panelArticles.validate();
			}
		});
		panelGlue.add(btnAuteur);

		panelArticles = new JPanel();
		add(panelArticles);
		panelArticles.setBackground(Color.LIGHT_GRAY);
		panelArticles.setPreferredSize(new Dimension(0, 3000));

		JScrollPane scrollPane = new JScrollPane(panelArticles);
		panelArticles.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
		add(scrollPane);

		ManagerApp.Instance().initialisation();

		/*
		 * initialisation des Articles
		 */

		/*
		 * 
		 * Article pa = new Article( "Les allumettes",
		 * " JE suis à l recherche d'une voiture volante rouge !!",
		 * "Robinson Clarck", "11-05-2154");
		 * 
		 * Article pa1 = new Article( "Java c'est genial",
		 * "J'aime bien java et je ne sais pas pourquoi... , Ce que j'apprecie le plus c'est le ping dans la console windows"
		 * ,"Vaillant Hugo", "11-05-2154"); Article pa2 = new Article( "Chef",
		 * "Je suis le chef... et taggle ","Ortiz Luc", "11-05-2154"); Article
		 * pa3 = new Article("BTP",
		 * "du 7 au 17 je serai absent car je dois partir pour construire une maison avec ma truelle"
		 * , "FernandeS Aurelien", "11-05-2154"); Article pa4 = new
		 * Article("7h30",
		 * "J'adore l imag .... c'est pour cela que je viens a 7h30",
		 * "Martinier Alexis" ,"11-05-2154");
		 * 
		 */

		/*
		 * initialisation des commentaires
		 * 
		 */
		/*
		 * new Commentaire( "first!", "Blandin Bob", "11-04-2056",pa.getId());
		 * new Commentaire("fake", "Mathias Gandit", "11-04-2056", pa.getId());
		 * new Commentaire("Cette Article n'est pas pertinent","Luc Ortiz",
		 * "11-04-2056",pa2.getId()); new Commentaire(
		 * "Comment on fait pour ping?","Hugo Vaillant",
		 * "11-04-2056",pa3.getId()); new Commentaire("Commentaire inutile!",
		 * "Claude Audrey", "11-04-2556", pa3.getId() ); new Commentaire(
		 * " fake!", "Martinier Alexis", "11-04-2556",pa1.getId());
		 */

		// Recuperation des differents elements de chaques articles + mise en
		// forme grace a Panel AppercuApercuArticle .
	
		affichageApercuArticles(nomUser, prenomUser);

	}
	/**
	 * Permet d'afficher les differents apercu des articles a l ecran 
	 * @param nomUser : nom de l'utilisateur connecte
	 * @param prenomUser : prenom de l'utilisateur connecte
	 * 
	 */
	private void affichageApercuArticles(String nomUser, String prenomUser) {
		PanelAppercuArticle paa = null;
		for (Article a : ManagerApp.Instance().getListArticles()) {
			paa = new PanelAppercuArticle(a, nomUser, prenomUser);
			panelArticles.add(paa);
		}
		
	}
	
	public JPanel getPanelArticles() {
		return panelArticles;
	}
}
