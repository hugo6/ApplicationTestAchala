package application;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import application.panels.PanelConnexion;
import application.panels.PanelPrincipalArticle;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ApplicationArticle extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ApplicationArticle frame = new ApplicationArticle();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ApplicationArticle() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 955, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
	
		PanelConnexion pc = new PanelConnexion();
		contentPane.add(pc, BorderLayout.CENTER);
		
		JButton btnConnexion = new JButton("Connexion");
		btnConnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nomUser = pc.getTextFieldNom().getText();
				String prenomUser = pc.getTextFieldPrenom().getText();
				PanelPrincipalArticle ppa = new PanelPrincipalArticle(nomUser, prenomUser);
				getContentPane().add(ppa);
				setContentPane(ppa);
				getContentPane();
				validate();
				
			}
		});
		btnConnexion.setBounds(292, 271, 89, 23);
		pc.add(btnConnexion);
		setContentPane(contentPane);
	}
}
