package application.panels;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelConnexion extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2895026096724542283L;

	public JTextField getTextFieldNom() {
		return textFieldNom;
	}

	public JTextField getTextFieldPrenom() {
		return textFieldPrenom;
	}

	private JTextField textFieldNom;
	private JTextField textFieldPrenom;

	/**
	 * Creation du panel de connexion.
	 */
	public PanelConnexion() {
		setLayout(null);
		
		JLabel lblBienvenue = new JLabel("Bienvenue");
		lblBienvenue.setBounds(436, 0, 120, 22);
		lblBienvenue.setFont(new Font("Tahoma", Font.BOLD, 18));
		add(lblBienvenue);
		
		JLabel lblNom = new JLabel("NOM");
		lblNom.setBounds(334, 104, 29, 17);
		lblNom.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblNom);
		
		textFieldNom = new JTextField();
		textFieldNom.setBounds(334, 128, 315, 20);
		lblNom.setLabelFor(textFieldNom);
		add(textFieldNom);
		textFieldNom.setColumns(10);
		
		JLabel lblPrenom = new JLabel("PRENOM");
		lblPrenom.setBounds(334, 182, 54, 17);
		lblPrenom.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblPrenom);
		
		textFieldPrenom = new JTextField();
		textFieldPrenom.setBounds(334, 205, 315, 20);
		lblPrenom.setLabelFor(textFieldPrenom);
		add(textFieldPrenom);
		textFieldPrenom.setColumns(10);
		//677*377
		
		setPreferredSize(new Dimension(955, 460));
	
	}

}
	
