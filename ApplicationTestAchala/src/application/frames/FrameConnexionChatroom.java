package application.frames;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class FrameConnexionChatroom extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JFrame frameConnexion;
	private JTextField textField;
	
	public FrameConnexionChatroom(String s) {
		getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(78, 96, 219, 69);
		getContentPane().add(textField);

		textField.setText(s);
		
		textField.setColumns(10);
		frameConnexion = new JFrame(" Connect to a Room Chat : ");
		frameConnexion.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		frameConnexion.getContentPane();
		frameConnexion.setVisible(true);
	}
	
	
}