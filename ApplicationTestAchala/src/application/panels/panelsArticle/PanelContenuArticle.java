package application.panels.panelsArticle;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import javax.swing.JTabbedPane;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JEditorPane;

public class PanelContenuArticle extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelContenuArticle(String nom, String prenom, String titre, String contenu, String date) {
		setLayout(new BorderLayout(0, 0));
		
		JLabel lblTitre = new JLabel(titre);
		add(lblTitre, BorderLayout.NORTH);
		
		JLabel lblDe = new JLabel("De : " +nom + " " + prenom + "le " + date);
		add(lblDe, BorderLayout.SOUTH);
		
		JEditorPane editorPaneContenu = new JEditorPane();
		editorPaneContenu.setText(contenu);
		editorPaneContenu.setEditable(false);
		add(editorPaneContenu, BorderLayout.CENTER);
		

		

	}

}
