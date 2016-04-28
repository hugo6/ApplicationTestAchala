package application.frames;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrameConnexionChatroom extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JFrame frameConnexion;
	private JTextField textPassword;
	
	public FrameConnexionChatroom(String s) {
		setTitle("Connexion to a Chat Room");
		getContentPane().setLayout(null);
		
		JLabel lblPassword = new JLabel("Chat Room Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPassword.setBounds(28, 32, 156, 33);
		getContentPane().add(lblPassword);
		
		textPassword = new JTextField();
		textPassword.setBounds(202, 40, 149, 20);
		getContentPane().add(textPassword);
		textPassword.setColumns(10);
		
		JButton btnConnect = new JButton("Connect");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO Connect to the chatroom
			}
		});
		btnConnect.setBounds(202, 103, 149, 32);
		getContentPane().add(btnConnect);
		frameConnexion = new JFrame(" Connect to a Room Chat : ");
		frameConnexion.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		frameConnexion.getContentPane();
		frameConnexion.setVisible(true);
	}
	
	
}