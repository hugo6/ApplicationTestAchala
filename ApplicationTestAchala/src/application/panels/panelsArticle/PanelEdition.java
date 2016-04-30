package application.panels.panelsArticle;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class PanelEdition extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JTextField textFieldTitre;
	private JTextArea textAreaArticle ;
	/**
	 * Create the panel.
	 */
	public PanelEdition(String nomUser, String prenomUser) {
		
		setBackground(Color.LIGHT_GRAY);
		setLayout(new BorderLayout(0, 0));

		JLabel lblEditionDunNouvelle = new JLabel("Edition d'un nouvelle article");
		lblEditionDunNouvelle.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblEditionDunNouvelle.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblEditionDunNouvelle, BorderLayout.NORTH);

		panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panelTitre = new JPanel();
		panelTitre.setBackground(Color.LIGHT_GRAY);
		panel.add(panelTitre, BorderLayout.NORTH);

		JLabel lblTitre = new JLabel("Titre :");
		lblTitre.setLabelFor(lblTitre);
		panelTitre.add(lblTitre);

		textFieldTitre = new JTextField();
		panelTitre.add(textFieldTitre);
		textFieldTitre.setColumns(40);

		textAreaArticle = new JTextArea();
		panel.add(textAreaArticle, BorderLayout.CENTER);

	}
	public JTextField getTextFieldTitre() {
		return textFieldTitre;
	}
	public JTextArea getTextAreaArticle() {
		return textAreaArticle;
	}

}
