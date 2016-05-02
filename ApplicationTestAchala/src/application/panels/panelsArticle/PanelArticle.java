package application.panels.panelsArticle;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import modules.publication.metier.Article;
import modules.publication.metier.ManagerApp;

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

		// Panel qui va afficher tous les boutons de tri
		JPanel panelTri = new JPanel();
		panelTri.setBackground(Color.LIGHT_GRAY);
		add(panelTri);
		panelTri.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblTrierLesArticles = new JLabel("Trier les articles par : ");
		panelTri.add(lblTrierLesArticles);

		// tri des aticles par titre 
		JButton btnTitre = new JButton("Titre");
		btnTitre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelArticles.removeAll();
				ManagerApp.Instance().triParTitre();
				affichageApercuArticles(nomUser, prenomUser);
				panelArticles.validate();
			}
		});
		panelTri.add(btnTitre);
		
		// tri des aticles par date
		JButton btnDate = new JButton("Date");
		btnDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelArticles.removeAll();
				ManagerApp.Instance().triParId();
				affichageApercuArticles(nomUser, prenomUser);
				panelArticles.validate();
			}
		});
		panelTri.add(btnDate);
		
		// tri des articles par nom d'auteur
		JButton btnAuteur = new JButton("Auteur");
		btnAuteur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelArticles.removeAll();
				ManagerApp.Instance().triParAuteur();
				affichageApercuArticles(nomUser, prenomUser);
				panelArticles.validate();
			}
		});
		panelTri.add(btnAuteur);
		
		panelArticles = new JPanel();
		add(panelArticles);
		panelArticles.setBackground(Color.LIGHT_GRAY);
		panelArticles.setPreferredSize(new Dimension(0, 3000));

		JScrollPane scrollPane = new JScrollPane(panelArticles);
		panelArticles.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
		add(scrollPane);
		
		//Initialisation du stockage en bdd
		ManagerApp.Instance().initialisation();
		affichageApercuArticles(nomUser, prenomUser);

	}

	/**
	 * Permet d'afficher les differents apercu des articles a l ecran
	 * 
	 * @param nomUser
	 *            : nom de l'utilisateur connecte
	 * @param prenomUser
	 *            : prenom de l'utilisateur connecte
	 * 
	 */
	public void affichageApercuArticles(String nomUser, String prenomUser) {
		PanelAppercuArticle paa = null;
		for (Article a : ManagerApp.Instance().getListArticles()) {
			paa = new PanelAppercuArticle(a, nomUser, prenomUser);
			if(a.getAuteur().equalsIgnoreCase(nomUser+" "+prenomUser) || a.getAuteur().equalsIgnoreCase(prenomUser+" "+nomUser)) {
				JButton btnSuppr = new JButton("Supprimer");
				btnSuppr.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						a.supprimer();
						panelArticles.removeAll();
						panelArticles.repaint();
						affichageApercuArticles(nomUser, prenomUser);
					}
				});
				paa.add(btnSuppr, BorderLayout.PAGE_END);
			}
			panelArticles.add(paa);
		}
	}

	public JPanel getPanelArticles() {
		return panelArticles;
	}
}
