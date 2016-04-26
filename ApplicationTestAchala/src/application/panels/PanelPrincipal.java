package application.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import application.utils.Article;
import application.utils.Commentaire;
import application.utils.Message;

public class PanelPrincipal extends JPanel {

	private String nom = "inconnu", prenom = "inconnu";

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}
	/**
	 * Create the panel.
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

		String txtDate = new SimpleDateFormat("dd MMMM yyyy", Locale.FRANCE).format(new Date());
		JLabel lblDate = new JLabel(txtDate);
		lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblDate.setBounds(567, 16, 103, 14);
		add(lblDate);

		JTabbedPane tabbedPaneMenu = new JTabbedPane(JTabbedPane.TOP);
		tabbedPaneMenu.setBounds(10, 41, 918, 368);
		add(tabbedPaneMenu);

		PanelArticle panelArticle = new PanelArticle(nomUser,prenomUser);
		JScrollPane scroll = new JScrollPane(panelArticle, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		tabbedPaneMenu.add(scroll, "Article");
		
		PanelEdition panelEdition = new PanelEdition(nom, prenom);
		JScrollPane scrollEdition = new JScrollPane(panelEdition, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		JButton Publier = new JButton("Publier");
		Publier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Article nouvelArticle = new Article(nomUser, prenomUser, panelEdition.getTextFieldTitre().getText(), panelEdition.getTextAreaArticle().getText(),txtDate,new ArrayList<Commentaire>());
		
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



		tabbedPaneMenu.addTab("Chat", new PanelChat(nomUser, prenomUser, new ArrayList<Message>()));
		tabbedPaneMenu.setPreferredSize(new Dimension(1000,460));
	}
}
