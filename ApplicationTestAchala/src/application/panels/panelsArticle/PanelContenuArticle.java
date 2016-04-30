package application.panels.panelsArticle;

import java.awt.BorderLayout;

import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelContenuArticle extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Panel qui va afficher le contenu de l article ( titre + contenu + auteur + date )
	 */
	public PanelContenuArticle(String nom, String titre, String contenu, String date) {
		setLayout(new BorderLayout(0, 0));
		
		JLabel lblTitre = new JLabel(titre);
		add(lblTitre, BorderLayout.NORTH);
		
		JLabel lblDe = new JLabel("De : " + nom + " le " + date);
		add(lblDe, BorderLayout.SOUTH);
		
		JEditorPane editorPaneContenu = new JEditorPane();
		editorPaneContenu.setText(contenu);
		editorPaneContenu.setEditable(false);
		add(editorPaneContenu, BorderLayout.CENTER);
		

		

	}

}
