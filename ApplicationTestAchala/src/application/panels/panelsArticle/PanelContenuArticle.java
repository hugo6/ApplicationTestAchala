package application.panels.panelsArticle;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import javax.swing.JTabbedPane;
import java.awt.Color;
import java.sql.Date;

import javax.swing.JLabel;
import javax.swing.JEditorPane;

public class PanelContenuArticle extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelContenuArticle(String nom, String titre, String contenu, Date date) {
		setLayout(new BorderLayout(0, 0));
		
		JLabel lblTitre = new JLabel(titre);
		add(lblTitre, BorderLayout.NORTH);
		
		JLabel lblDe = new JLabel("De : " + nom + "le " + date);
		add(lblDe, BorderLayout.SOUTH);
		
		JEditorPane editorPaneContenu = new JEditorPane();
		editorPaneContenu.setText(contenu);
		editorPaneContenu.setEditable(false);
		add(editorPaneContenu, BorderLayout.CENTER);
		

		

	}

}
