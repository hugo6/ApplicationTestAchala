package application.frames;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

import application.panels.panelsChat.PanelChat;

public class FrameConnexionChatroom extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JFrame frameConnexion;
	private String pwdEntered;
	private  JPasswordField textPassword;
	
	public FrameConnexionChatroom(String zoneName, String validPwd) {
		/**
		 * set the frame properties
		 */
		frameConnexion = new JFrame("Connexion to a Chat Room");
		frameConnexion.setResizable(false);
		frameConnexion.setAlwaysOnTop(true);
		frameConnexion.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		frameConnexion.setBounds(200, 200, 370, 170);
		
		/**
		 * CHATROOM PANEL
		 */
		getContentPane().setLayout(null);
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 366, 147);
		panel.setLayout(null);
		
		/**
		 * PASSWORD LABEL
		 */
		JLabel lblPassword = new JLabel("Chat Room Password");
		lblPassword.setBounds(21, 49, 144, 27);
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblPassword);
		
		/**
		 * PASSWORD FIELD
		 */
		textPassword = new JPasswordField();
		textPassword.setBounds(202, 51, 136, 27);
		textPassword.setColumns(10);
		panel.add(textPassword);
		/**
		 * CONNECT BUTTON
		 */
		JButton btnConnect = new JButton("Connect");
		btnConnect.setBounds(248, 104, 108, 32);
		panel.add(btnConnect);
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pwdEntered = String.valueOf(textPassword.getPassword());
				
				//Recuperation du chat + listener sur celui-ci
				if(pwdEntered.equals(validPwd))
				{
					PanelChat.changeChat(zoneName);
					frameConnexion.dispose();
				} else {
					JOptionPane.showMessageDialog(frameConnexion, "Mauvais mot de passe");
				}
				PanelChat.frameConnexion = null;
			}
		});
		
		frameConnexion.getContentPane();
		frameConnexion.getContentPane().add(panel);
		frameConnexion.setVisible(true);
	}
	
	
}