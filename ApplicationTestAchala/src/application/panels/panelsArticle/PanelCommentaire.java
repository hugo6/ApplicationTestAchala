package application.panels.panelsArticle;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;

import modules.publication.metier.Commentaire;



public class PanelCommentaire extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Representation graphique d'un Commentaire
	 */
	public PanelCommentaire(Commentaire c) {
		setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		setLayout(new BorderLayout(0, 0));

		JLabel lblDeNom = new JLabel("De : " + c.getAuteur() +  " , le " + c.getDate() + " a ecrit : ");
		add(lblDeNom, BorderLayout.NORTH);

		JTextPane textPaneContenu = new JTextPane();
		textPaneContenu.setEditable(false);
		textPaneContenu.setText(c.getContenu());

		setPreferredSize(new Dimension(500,75));
		add(textPaneContenu, BorderLayout.CENTER);

	}

}
