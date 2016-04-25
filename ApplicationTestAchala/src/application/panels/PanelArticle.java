package jPanels;

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

import utils.Article;
import utils.Commentaire;

public class PanelArticle extends JPanel {

	private JPanel panelArticles;


	public void setPanelArticles(JPanel panelArticles) {
		this.panelArticles = panelArticles;
	}

	private Article articlesList = new Article();
	private List<PanelAppercuArticle> paaList = new ArrayList<PanelAppercuArticle>(30);

	/**
	 * Create the panel qui va afficher les appercu des differents articles .
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
		panelGlue.setLayout(new BoxLayout(panelGlue, BoxLayout.X_AXIS));

		Component verticalStrut = Box.createVerticalStrut(20);
		verticalStrut.setBackground(Color.LIGHT_GRAY);
		panelGlue.add(verticalStrut);

		panelArticles = new JPanel();
		add(panelArticles);
		panelArticles.setBackground(Color.LIGHT_GRAY);
		panelArticles.setPreferredSize(new Dimension(0,3000));
		
		JScrollPane scrollPane = new JScrollPane(panelArticles);
		panelArticles.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
		add(scrollPane);
		
		
		/*
		 * initialisation des commentaires
		 * 
		 */
		List<Commentaire> ListComentaires1 = new ArrayList<Commentaire>();
		Commentaire c = new Commentaire("Robinson", "Clarck", "11/04/2056",
				" first!");
		ListComentaires1.add(c);
		Commentaire c1 = new Commentaire("Robinson", "Clarck", "11/04/2056", "test");
		ListComentaires1.add(c1);
		Commentaire c2 = new Commentaire("Robinson", "Clarck", "11/04/2056", "Cette Article pue la merde");
		ListComentaires1.add(c2);
		c1 = new Commentaire("Vaillant", "Hugo", "11/04/2056", "Comment on fait pour ping?");
		ListComentaires1.add(c1);
		
		List<Commentaire> ListComentaires2 = new ArrayList<Commentaire>();
		c = new Commentaire("ijs", "test", "11/04/2556"," Commentaire inutile!");
		ListComentaires2.add(c);
		c = new Commentaire("moi", "test", "11/04/2556"," fake!");
		ListComentaires2.add(c);
		
		
		/*
		 * initialisation des Articles
		 */
		
		Article pa = new Article("Robinson", "Clarck", "Les allumettes",
				" JE suis à l recherche d'une voiture volante en diagonale + 58 -1 je suis trop bon jai reussi a faire marché cette merde omg!!", ListComentaires1);
		articlesList.getArticlesList().add(pa);
		Article pa1 = new Article("Vaillant", "Hugo", "Java c'est genial",
				"Je kif java et je ne sais pas pourquoi... surtout RMI , mais mon plus grand kif c'est le ping dans la console windows",ListComentaires2);
		articlesList.getArticlesList().add(pa1);
		Article pa2 = new Article("Ortiz", "Luc", "Chef", "Je suis le chef... et taggle",new ArrayList());
		articlesList.getArticlesList().add(pa2);
		Article pa3 = new Article("FernandeS", "Aurelien", "BTP",
				"du 7 au 17 je serai absent car je dois partir pour construire une maison avec ma truelle",new ArrayList());
		articlesList.getArticlesList().add(pa3);
		Article pa4 = new Article("Martinier", "Alexis", "7h30",
				"J'adore l imag .... c'est pour cela que je viens a 7h30",ListComentaires1);
		articlesList.getArticlesList().add(pa4);

		// Recuperation des differents elements de chaques articles + mise en
		// forme grace a Panel AppercuApercuArticle .
		PanelAppercuArticle paa;
		for (Article i : articlesList.getArticlesList()) {
			paa = new PanelAppercuArticle(i, nomUser, prenomUser);
			paaList.add(paa);
			panelArticles.add(paa);

		}

	}

	public JPanel getPanelArticles() {
		return panelArticles;
	}
}
