package application.panels;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DropMode;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class PanelChat extends JPanel {
	private JTextField txtSaisissezVotreMessage;

	private List<Contact> ListContacts = new ArrayList<Contact>();
	private List<PanelCommentaire> PanelListContact = new ArrayList<PanelContact>();

	/**
	 * Create the panel.
	 */

	public PanelChat() {
		setLayout(null);
		
		/*Titre du Panel*/
		JLabel lblChat = new JLabel("CHAT");
		lblChat.setFont(new Font("HelveticaNeueLT Com 35 Th", Font.PLAIN, 20));
		lblChat.setHorizontalAlignment(SwingConstants.CENTER);
		lblChat.setBounds(309, 47, 84, 25);
		add(lblChat);
		
		/*Panel scrollable*/
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(57, 83, 624, 201);
		add(scrollPane);
		
		/*Text Field saisie message*/
		txtSaisissezVotreMessage = new JTextField();
		txtSaisissezVotreMessage.setDropMode(DropMode.ON);
		txtSaisissezVotreMessage.setHorizontalAlignment(SwingConstants.CENTER);
		txtSaisissezVotreMessage.setText("Saisissez votre message ici ...");
		txtSaisissezVotreMessage.setBounds(57, 284, 426, 71);
		add(txtSaisissezVotreMessage);
		txtSaisissezVotreMessage.setColumns(10);
		
		/*bouton ajouter un fichier*/
		JButton btnNewButton = new JButton("Fichier");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(493, 295, 63, 45);
		add(btnNewButton);
		
		/*bouton envoyer un message*/
		JButton btnEnvoyer = new JButton("Envoyer");
		btnEnvoyer.addActionListener(new ActionListener()) {
			public void actionPerformed(ActionEvent e) {
				/*TODO envoye(r)*/
			}
		}
		btnEnvoyer.setBounds(566, 289, 115, 60);
		add(btnEnvoyer);
		
		/*bouton connecter*/
		JButton btnConnecter = new JButton("Connecter");
		btnConnecter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*TODO seconnecter()*/
			}
		});
		btnConnecter.setBounds(839, 98, 98, 34);
		add(btnConnecter);
		
		/*ComboBox des ip server*/
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"192.168.12.55", "127.0.0.1"}));
		/*TODO remplir la combox avec une list d'ip*/
		comboBox.setBounds(722, 98, 107, 34);
		add(comboBox);
		
		/*label contact en ligne*/
		JLabel lblContactsEnLigne = new JLabel("Contacts en ligne");
		lblContactsEnLigne.setBounds(783, 172, 92, 14);
		add(lblContactsEnLigne);
		
		/*ComboBox des contacts en ligne*/
		JComboBox comboBox_1 = new JComboBox();
		/*TODO remplir la combox avec une list d'utilisateurs*/
																			PanelContact pc;
																			for (Contact c : ListContacts) {
																				pc = new PanelContact(c);
																				PanelListContact.add(pc);
																				panelContacts.add(pc);
																			}
		comboBox_1.setEditable(true);
		comboBox_1.setBounds(691, 190, 282, 150);
		add(comboBox_1);
		
	}
}
