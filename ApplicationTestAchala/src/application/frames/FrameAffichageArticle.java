package application.frames;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import application.panels.panelsArticle.PanelAfficheCommentaires;
import application.panels.panelsArticle.PanelContenuArticle;
import modules.publication.metier.Article;

public class FrameAffichageArticle extends JFrame {
	private JFrame affichage;


	/**
	 * Create the frame.
	 */
	public FrameAffichageArticle(Article a,String nomUser, String prenomUser) {
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		affichage = new JFrame(" Affichage de l'article : " + a.getTitre());
		affichage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		affichage.setBounds(900, 100, 600, 450);

		
		PanelContenuArticle panelArticle = new PanelContenuArticle(a.getNomAuteur(),a.getTitre(),a.getContenu(),a.getDate());
		JScrollPane scroll = new JScrollPane(panelArticle, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		tabbedPane.add(scroll, "Article");
		
		affichage.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		PanelAfficheCommentaires panelAfficheCom = new PanelAfficheCommentaires(prenomUser,nomUser,a);
		
		tabbedPane.addTab("Commentaires", panelAfficheCom );
		affichage.getContentPane();
		affichage.setVisible(true);
	}

}
