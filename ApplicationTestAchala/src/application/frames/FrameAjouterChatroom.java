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

public class FrameAjouterChatroom extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JFrame frameAjouter;
	private JTextField textChatroomName;
	private JTextField textPassword;
	
	public FrameAjouterChatroom() {
		/**
		 * set the frame properties
		 */
		setTitle("Add a new Chat Room");
		getContentPane().setLayout(null);
		
		/**
		 * CHATROOM LABEL
		 */
		JLabel lblChatroomName = new JLabel("ChatRoom Name");
		lblChatroomName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblChatroomName.setHorizontalAlignment(SwingConstants.CENTER);
		lblChatroomName.setBounds(32, 35, 144, 34);
		getContentPane().add(lblChatroomName);
		
		/**
		 * CHATROOM FIELD
		 */
		textChatroomName = new JTextField();
		textChatroomName.setBounds(215, 44, 171, 20);
		getContentPane().add(textChatroomName);
		textChatroomName.setColumns(10);
		
		/**
		 * CHATROOM CHECKBOX
		 */
		JCheckBox chckbxPassword = new JCheckBox("Password ?");
		chckbxPassword.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				System.out.println("item state changed -> allow the textefield");
				textPassword.setEnabled(true);//allow the field only if the box is checked
			}
		});
		chckbxPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxPassword.setBounds(55, 114, 110, 34);
		getContentPane().add(chckbxPassword);
		
		/**
		 * PASSWORD FIELD
		 */
		textPassword = new JTextField();
		textPassword.setEnabled(false);
		textPassword.setBounds(215, 123, 171, 20);
		getContentPane().add(textPassword);
		textPassword.setColumns(10);
		
		/**
		 * BUTTON ADD CHATROOM
		 */
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO add the chatroom
			}
		});
		btnAdd.setBounds(253, 195, 171, 34);
		getContentPane().add(btnAdd);
		frameAjouter = new JFrame(" Ajouter un Chat Room : ");
		frameAjouter.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		//TODO move to the top of the file -> test?
		frameAjouter.getContentPane();
		frameAjouter.setVisible(true);
	}
}
