package application.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import application.panels.panelsArticle.PanelAppercuArticle;
import application.panels.panelsArticle.PanelArticle;
import application.panels.panelsArticle.PanelEdition;
import application.panels.panelsChat.PanelChat;
import modules.publication.metier.Article;

public class PanelPrincipal extends JPanel {

	private static final long serialVersionUID = 6762676821660654248L;
	private String nom = "Onyme", prenom = "Anne";

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}
	/**
	 * Panel qui affiche les differents menu ( Article , Edition d'un nouvel article , Chat )
	 *
	 */
	public PanelPrincipal(String nomUser, String prenomUser) {
		this.nom = nomUser;
		this.prenom = prenomUser;
		setLayout(null);
		this.setPreferredSize(new Dimension(1000,460));

		JLabel lblUser = new JLabel("Bonjour " + prenom + " " + nom);
		lblUser.setHorizontalAlignment(SwingConstants.LEFT);
		lblUser.setBounds(10, 16, 526, 14);
		add(lblUser);

		// Recuperation de la date du jour et affichage.
		String txtDate = new SimpleDateFormat("dd MMMM yyyy", Locale.FRANCE).format(new Date());
		
		JLabel lblDate = new JLabel(txtDate);
		lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblDate.setBounds(825, 16, 103, 14);
		add(lblDate);

		JTabbedPane tabbedPaneMenu = new JTabbedPane(JTabbedPane.TOP);
		tabbedPaneMenu.setBounds(10, 41, 918, 368);
		add(tabbedPaneMenu);
	/*	
		// Ajout des differents menu dans le tabbed pane menu
		PanelArticle panelArticle = new PanelArticle(nomUser,prenomUser);
		tabbedPaneMenu.addTab("Article",panelArticle);
		
		PanelEdition panelEdition = new PanelEdition(nom, prenom);
		JScrollPane scrollEdition = new JScrollPane(panelEdition, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		// Bouton permettant de publier un article dans le panelEdition
		JButton Publier = new JButton("Publier");
		Publier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Ajout de l'article en base 
				Article nouvelArticle = new Article(panelEdition.getTextFieldTitre().getText(), panelEdition.getTextAreaArticle().getText(), nomUser + " "+ prenomUser,txtDate);
				
				PanelAppercuArticle paa = new PanelAppercuArticle(nouvelArticle, nomUser, prenomUser);
				panelArticle.getPanelArticles().add(paa);
				panelArticle.validate();
				
				panelEdition.getTextFieldTitre().setText(null);
				panelEdition.getTextAreaArticle().setText(null);
				panelEdition.validate();
				tabbedPaneMenu.validate();
			
			}
		});
		panelEdition.add(Publier, BorderLayout.SOUTH);
		tabbedPaneMenu.add(scrollEdition,"Edition");

*/

		tabbedPaneMenu.addTab("Chat", new PanelChat(nomUser, prenomUser));
		tabbedPaneMenu.setPreferredSize(new Dimension(1000,460));
	}
}
