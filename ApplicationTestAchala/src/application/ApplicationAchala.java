package application;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTextField;

import application.panels.*;

public class ApplicationAchala {

	private JFrame frame;
	private JPanel panelConnexion = new JPanel();
	private JTextField textFieldNom;
	private JTextField textFieldPrenom;
	private String nom, prenom;

	private JButton btnValider;
	/**
	 * @wbp.nonvisual location=250,99
	 */


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ApplicationAchala window = new ApplicationAchala();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ApplicationAchala() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Application temoin Framework");
		frame.setBounds(100, 100, 955, 460);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Panel qui permet la connection d'un nouvel utilisateur
		panelConnexion.setLayout(null);
		
		JLabel lblBienvenue = new JLabel("Bienvenue dans l'application ACHALA");
		lblBienvenue.setBounds(283, 0, 335, 22);
		lblBienvenue.setFont(new Font("Tahoma", Font.BOLD, 18));
		panelConnexion.add(lblBienvenue);


		JLabel lblNom = new JLabel("NOM");
		lblNom.setBounds(308, 110, 29, 17);
		lblNom.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelConnexion.add(lblNom);

		textFieldNom = new JTextField();
		textFieldNom.setBounds(303, 138, 315, 20);
		lblNom.setLabelFor(textFieldNom);
		panelConnexion.add(textFieldNom);
		textFieldNom.setColumns(10);

		JLabel lblPrenom = new JLabel("PRENOM");
		lblPrenom.setBounds(302, 183, 54, 17);
		lblPrenom.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelConnexion.add(lblPrenom);

		textFieldPrenom = new JTextField();
		textFieldPrenom.setBounds(303, 211, 315, 20);
		lblPrenom.setLabelFor(textFieldPrenom);
		panelConnexion.add(textFieldPrenom);
		textFieldPrenom.setColumns(10);

		btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Creation d'un nouvel utilisateur
				prenom = textFieldPrenom.getText();
				nom = textFieldNom.getText();
		PanelPrincipal pp = new PanelPrincipal(nom, prenom);
		frame.getContentPane().add(pp);
		frame.setContentPane(pp);
		frame.getContentPane();
				frame.validate();
				
			}
		});
		
		btnValider.setBounds(398, 290, 81, 23);
		panelConnexion.add(btnValider);
	
		frame.getContentPane().add(panelConnexion);
		frame.getContentPane();
		
		
	}
}	
