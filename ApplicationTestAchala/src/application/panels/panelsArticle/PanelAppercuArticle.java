package application.panels.panelsArticle;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import application.frames.FrameAffichageArticle;
import modules.publication.metier.Article;
import javax.swing.JButton;

public class PanelAppercuArticle extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Paenel qui affiche l'apercu d'un article 
	 */
	public PanelAppercuArticle(Article a, String nomUser, String prenomUser) {
		setBorder(new LineBorder(Color.LIGHT_GRAY, 3));
		String titre = a.getTitre();
		String premierMots = a.getContenu(); //
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				setBorder(new LineBorder(new Color(0, 0, 0), 3));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				setBorder(new LineBorder(new Color(240, 240, 240), 3));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				FrameAffichageArticle faa = new FrameAffichageArticle(a, nomUser, prenomUser);

			}
		});
		setLayout(new BorderLayout(0, 0));

		// affichage des appercu du titre de l'article , n'afiche que les 15
		// premiers caracteres
		JPanel panelTitre = new JPanel();
		panelTitre.setAlignmentX(Component.LEFT_ALIGNMENT);
		add(panelTitre);
		panelTitre.setLayout(new BoxLayout(panelTitre, BoxLayout.Y_AXIS));

		if (titre.length() < 16)
			titre = titre.substring(0);
		else
			titre = titre.substring(0, 15);
		JLabel lblTitre = new JLabel("Titre : " + titre);
		panelTitre.add(lblTitre);
		lblTitre.setHorizontalAlignment(SwingConstants.CENTER);

		Component verticalStrut = Box.createVerticalStrut(20);
		panelTitre.add(verticalStrut);

		JPanel panelPremiers = new JPanel();
		panelTitre.add(panelPremiers);
		panelPremiers.setLayout(new BoxLayout(panelPremiers, BoxLayout.Y_AXIS));
		// affiche le debut du contenu de l article.
		JLabel lblContenu = new JLabel("New label");
		panelPremiers.add(lblContenu);

		Component verticalStrut_1 = Box.createVerticalStrut(20);
		panelPremiers.add(verticalStrut_1);
		
		// affiche l'auteur de l article
		JPanel panelAuteur = new JPanel();
		panelTitre.add(panelAuteur);
		panelAuteur.setLayout(new BoxLayout(panelAuteur, BoxLayout.Y_AXIS));

		JLabel lblAuteur = new JLabel("Auteur : " + a.getAuteur());
		panelAuteur.add(lblAuteur);
		lblAuteur.setHorizontalAlignment(SwingConstants.CENTER);
		
		// affichage des 29 premiers caractere de l'article + "..."
		if (premierMots.length() < 30) {
			premierMots = premierMots.substring(0);

			// remplisage par des espaces ... Question esthetique
			for (int i = premierMots.length(); i < 30; i++)
				premierMots += " ";
		} else
			premierMots = premierMots.substring(0, 30);
		lblContenu.setText(premierMots);
		this.setPreferredSize(new Dimension(175, 125));

	}

}
