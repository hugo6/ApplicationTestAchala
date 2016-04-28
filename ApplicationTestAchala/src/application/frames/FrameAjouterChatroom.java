package application.frames;

import javax.swing.JFrame;

import modules.publication.metier.Article;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JCheckBox;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;

public class FrameAjouterChatroom extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JFrame frameAjouter;
	private JTextField textChatroomName;
	private JTextField textPassword;
	
	public FrameAjouterChatroom() {
		/**
		 * set the frame properties
		 */
		frameAjouter = new JFrame("Add a new Chat Room");
		frameAjouter.setResizable(false);
		frameAjouter.setAlwaysOnTop(true);
		frameAjouter.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		frameAjouter.setBounds(200, 200, 446, 277);
		
		/**
		 * CHATROOM PANEL
		 */
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 434, 239);
		panel.setLayout(null);
		
		/**
		 * CHATROOM LABEL
		 */
		JLabel lblChatroomName = new JLabel("ChatRoom Name");
		lblChatroomName.setBounds(39, 43, 123, 17);
		panel.add(lblChatroomName);
		lblChatroomName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblChatroomName.setHorizontalAlignment(SwingConstants.CENTER);
		
		/**
		 * CHATROOM FIELD
		 */
		textChatroomName = new JTextField();
		textChatroomName.setBounds(211, 43, 109, 20);
		panel.add(textChatroomName);
		textChatroomName.setColumns(10);
		
		/**
		 * CHATROOM CHECKBOX
		 */
		JCheckBox chckbxPassword = new JCheckBox("Password ?");
		chckbxPassword.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxPassword.setBounds(39, 112, 123, 25);
		panel.add(chckbxPassword);
		chckbxPassword.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(chckbxPassword.isSelected())
				{
					textPassword.setEnabled(true);//allow the field only if the box is checked
				}else
				{
					textPassword.setEnabled(false);//disallow the field if the box is unchecked
				}
				
			}
		});
		chckbxPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		/**
		 * PASSWORD FIELD
		 */
		textPassword = new JTextField();
		textPassword.setBounds(211, 116, 109, 20);
		panel.add(textPassword);
		textPassword.setEnabled(false);
		textPassword.setColumns(10);
		
		/**
		 * BUTTON ADD CHATROOM
		 */
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(324, 197, 100, 31);
		panel.add(btnAdd);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO add the chatroom
			}
		});
		frameAjouter.getContentPane();
		frameAjouter.getContentPane().add(panel);
		frameAjouter.setVisible(true);
	}
}
