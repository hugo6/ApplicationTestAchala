package application.frames;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import achala.communication.Correspondance;
import achala.communication.utilisateur._Utilisateur;
import application.panels.panelsChat.PanelChat;
import modules.chat.SecureCorrespondance;

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
				if (!PanelChat.nameAlreadyTaken(textChatroomName.getText())) {
					try
					{
						List<_Utilisateur> users = PanelChat.getServer().getUtilisateurs();
						String zoneName = textChatroomName.getText();
						String rmiAdresse = PanelChat.getServer().getRMIAdresse(zoneName);
						String password = textPassword.getText();
						
						if(chckbxPassword.isSelected() && !textPassword.getText().equals(""))
							PanelChat.getServer().addSharedZone(PanelChat.getConnectedUser(), new SecureCorrespondance(users, rmiAdresse, zoneName, password));
						else
							PanelChat.getServer().addSharedZone(PanelChat.getConnectedUser(), new Correspondance(users, rmiAdresse, zoneName));
						
						PanelChat.refreshRooms(PanelChat.getServer());
					}
					catch(Exception ex)
					{
						ex.printStackTrace();
					}
					textChatroomName.setText("");
					textPassword.setText("");
					chckbxPassword.setSelected(false);
				} else {
					JOptionPane.showMessageDialog(frameAjouter, "Ce nom est deja pris sur ce serveur.\nMerci d'en choisir un autre.");
				}
			}
		});
		frameAjouter.getContentPane();
		frameAjouter.getContentPane().add(panel);
		frameAjouter.setVisible(true);
	}
}
