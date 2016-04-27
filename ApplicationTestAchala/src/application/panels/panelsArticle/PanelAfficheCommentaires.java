package application.panels.panelsArticle;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import modules.publication.metier.Article;
import modules.publication.metier.Commentaire;

public class PanelAfficheCommentaires extends JPanel {
	private List<Commentaire> ListComentaires = new ArrayList<Commentaire>();
	private List<PanelCommentaire> PanelListCommentaire = new ArrayList<PanelCommentaire>();

	/**
	 * Create the panel qui va aficher les commentaires.
	 */
	public PanelAfficheCommentaires(String nomUser, String prenomUser, Article a) {
		this.setBackground(Color.LIGHT_GRAY);
		setLayout(new BorderLayout(0, 0));
		setLayout(new BorderLayout(0, 0));

		JPanel panelTitre = new JPanel();
		panelTitre.setBackground(Color.LIGHT_GRAY);
		add(panelTitre, BorderLayout.NORTH);
		panelTitre.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblArticles = new JLabel("Commentaires");
		panelTitre.add(lblArticles);
		lblArticles.setHorizontalAlignment(SwingConstants.CENTER);
		lblArticles.setFont(new Font("Tahoma", Font.BOLD, 21));

		JPanel panelCommentaires = new JPanel();
		add(panelCommentaires, BorderLayout.CENTER);
		panelCommentaires.setBackground(Color.LIGHT_GRAY);

		JScrollPane scrollPaneCommentaires = new JScrollPane(panelCommentaires);
		panelCommentaires.setLayout(new BoxLayout(panelCommentaires, BoxLayout.Y_AXIS));
		add(scrollPaneCommentaires);

		JPanel panelNewCom = new JPanel();
		panelNewCom.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panelNewCom.setBackground(Color.LIGHT_GRAY);
		add(panelNewCom, BorderLayout.SOUTH);



		PanelCommentaire pc;
		for (Commentaire c : a.getLesCommentaires() ) {
			pc = new PanelCommentaire(c);
			panelCommentaires.add(pc);
		}


		panelNewCom.setLayout(new BoxLayout(panelNewCom, BoxLayout.X_AXIS));

		JTextArea txtrLaisserUnCommentaire = new JTextArea();
		txtrLaisserUnCommentaire.setTabSize(150);
		txtrLaisserUnCommentaire.setRows(4);
		panelNewCom.add(txtrLaisserUnCommentaire);
		int id = 20;
		
		// ajout d'un nouveau commentaire
		JButton btnNewButton = new JButton("Envoyer");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String txtDate = new SimpleDateFormat("dd MMMM yyyy", Locale.FRANCE).format(new Date());
				Commentaire nouveauCom = new Commentaire(txtrLaisserUnCommentaire.getText(), nomUser + " "+ prenomUser,txtDate, a.getId());				
				PanelCommentaire newPanelCom = new PanelCommentaire(nouveauCom);
				panelCommentaires.add(newPanelCom);
				txtrLaisserUnCommentaire.setText(null);
				validate();
				
			}
		});
		panelNewCom.add(btnNewButton);

	}

}
