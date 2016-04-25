package application.panels;

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
import application.utils.Article;

public class PanelAppercuArticle extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelAppercuArticle(Article a, String nomUser, String prenomUser) {
		setBorder(new LineBorder(new Color(0, 0, 0), 0));
		String titre = a.getTitre();
		String premierMots = a.getContenu();
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				setBorder(new LineBorder(new Color(0, 0, 0), 3));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				setBorder(null);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				FrameAffichageArticle faa = new FrameAffichageArticle(a, nomUser, prenomUser);

			}
		});
		setLayout(new BorderLayout(0, 0));

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
		
		JLabel lblNewLabel = new JLabel("New label");
		panelPremiers.add(lblNewLabel);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		panelPremiers.add(verticalStrut_1);
		

		JPanel panelAuteur = new JPanel();
		panelTitre.add(panelAuteur);
		panelAuteur.setLayout(new BoxLayout(panelAuteur, BoxLayout.Y_AXIS));
		
				JLabel lblAuteur = new JLabel("Auteur : " + a.getNomAuteur() + " " + a.getPrenomAuteur());
				panelAuteur.add(lblAuteur);
				lblAuteur.setHorizontalAlignment(SwingConstants.CENTER);
				
						Component verticalStrut_2 = Box.createVerticalStrut(20);
						panelAuteur.add(verticalStrut_2);

		if (premierMots.length() < 30) {
			premierMots = premierMots.substring(0);
			// remplisage par des espaces ... Question esthetique mais en fait
			// c'est tjrs un peu moche
			for (int i = premierMots.length(); i < 30; i++)
				premierMots += " ";
		} else
			premierMots = premierMots.substring(0, 30);
		lblNewLabel.setText(premierMots);
		this.setPreferredSize(new Dimension(175, 110));

	}

}
