package application.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
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

import application.utils.Article;
import application.utils.Commentaire;
import java.awt.Scrollbar;
import javax.swing.JScrollBar;
import java.awt.ScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

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
		for (Commentaire i : a.getCommentaireList() ) {
			pc = new PanelCommentaire(i);
			PanelListCommentaire.add(pc);
			panelCommentaires.add(pc);
		}


		panelNewCom.setLayout(new BoxLayout(panelNewCom, BoxLayout.X_AXIS));

		JTextArea txtrLaisserUnCommentaire = new JTextArea();
		txtrLaisserUnCommentaire.setTabSize(150);
		txtrLaisserUnCommentaire.setRows(4);
		panelNewCom.add(txtrLaisserUnCommentaire);

		// ajout d'un nouveau commentaire
		JButton btnNewButton = new JButton("Envoyer");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String txtDate = new SimpleDateFormat("dd MMMM yyyy", Locale.FRANCE).format(new Date());
				Commentaire nouveauCom = new Commentaire(nomUser,prenomUser,txtDate,txtrLaisserUnCommentaire.getText());
				a.getCommentaireList().add(nouveauCom);				
				PanelCommentaire newPanelCom = new PanelCommentaire(nouveauCom);
				panelCommentaires.add(newPanelCom);
				txtrLaisserUnCommentaire.setText(null);
				validate();
				
			}
		});
		panelNewCom.add(btnNewButton);

	}

}
