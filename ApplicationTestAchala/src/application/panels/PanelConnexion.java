package application.panels;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelConnexion extends JPanel {
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
		lblBienvenue.setBounds(268, 0, 120, 22);
		lblBienvenue.setFont(new Font("Tahoma", Font.BOLD, 18));
		add(lblBienvenue);
		
		JLabel lblNom = new JLabel("NOM");
		lblNom.setBounds(180, 106, 29, 17);
		lblNom.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblNom);
		
		textFieldNom = new JTextField();
		textFieldNom.setBounds(180, 128, 315, 20);
		lblNom.setLabelFor(textFieldNom);
		add(textFieldNom);
		textFieldNom.setColumns(10);
		
		JLabel lblPrenom = new JLabel("PRENOM");
		lblPrenom.setBounds(180, 183, 54, 17);
		lblPrenom.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblPrenom);
		
		textFieldPrenom = new JTextField();
		textFieldPrenom.setBounds(180, 205, 315, 20);
		lblPrenom.setLabelFor(textFieldPrenom);
		add(textFieldPrenom);
		textFieldPrenom.setColumns(10);
	
	}

}
	
