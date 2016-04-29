package application;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import application.panels.PanelConnexion;
import application.panels.PanelPrincipalArticle;
import application.panels.panelsChat.PanelChat;

public class ApplicationChat {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ApplicationChat window = new ApplicationChat();
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
	public ApplicationChat() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Application de messagerie instantané");
		frame.setBounds(100, 100, 955, 460);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		PanelConnexion pc = new PanelConnexion();
		frame.getContentPane().add(pc, BorderLayout.CENTER);
		
		JButton btnConnexion = new JButton("Connexion");
		btnConnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nomUser = pc.getTextFieldNom().getText();
				String prenomUser = pc.getTextFieldPrenom().getText();
				PanelChat panelChat = new PanelChat(nomUser, prenomUser);
				frame.getContentPane().add(panelChat);
				frame.setContentPane(panelChat);
				frame.getContentPane();
				frame.validate();
			}
		});
		btnConnexion.setBounds(334, 311, 315, 23);
		pc.add(btnConnexion);
		
		
		
		
	}

}
