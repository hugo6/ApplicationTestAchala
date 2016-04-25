package application.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import application.utils.Commentaire;
import application.utils.Message;

import javax.swing.JTextPane;
import javax.swing.JList;
import javax.swing.AbstractListModel;

public class PanelChat extends JPanel {
	private JTextField txtmessage;

	//private List<Contact> ListContacts = new ArrayList<Contact>();
	private List<Message> ListMessage = new ArrayList<Message>();
	
	/**
	 * Create the panel.
	 */

	public PanelChat(String nom, String prenom, List<Message> ListMessage) {
		System.out.println(getPreferredSize());
		setBackground(Color.LIGHT_GRAY);
		setLayout(null);
		
		/*Initialisation de la liste des messages*/
		ListMessage.add(new Message("Luc_ortiz", "18/12/2016 15h12", "Salut =)"));
		ListMessage.add(new Message("Hugo_Vaillant", "18/12/2016 15h16", "Salut =)"));
		ListMessage.add(new Message("Luc_ortiz", "18/12/2016 15h18", "comment vas tu ?!"));
		ListMessage.add(new Message("Hugo_Vaillant", "18/12/2016 15h18", "Bien, Merci Luc !"));
		
		/*Titre du Panel*/
		JLabel lblChat = new JLabel("CHAT");
		lblChat.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblChat.setHorizontalAlignment(SwingConstants.CENTER);
		lblChat.setBounds(238, 11, 84, 25);
		add(lblChat);
		
		/*Panel scrollable des Contacts*/
		JLabel lblContacts = new JLabel("Contacts en ligne");
		lblContacts.setHorizontalAlignment(SwingConstants.CENTER);
		lblContacts.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblContacts.setBounds(533, 144, 275, 25);
		add(lblContacts);
		
		/*label ip Server*/
		JLabel lblipServer = new JLabel("Choix du serveur");
		lblipServer.setHorizontalAlignment(SwingConstants.CENTER);
		lblipServer.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblipServer.setBounds(545, 31, 265, 25);
		add(lblipServer);
		
		/*Panel du chat*/
		JPanel panelChat = new JPanel();
		panelChat.setBounds(1, 1, 459, 345);
		panelChat.setPreferredSize(new Dimension(500, 3000));
		add(panelChat);
		
		
		/*Scroll du chat*/
		JScrollPane scrollPaneChat = new JScrollPane(panelChat);
		scrollPaneChat.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneChat.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneChat.setBounds(57, 47, 478, 201);
		add(scrollPaneChat);
		
		
		PanelMessage pmsg;
		for (Message m : ListMessage) {
			pmsg = new PanelMessage(m);
			panelChat.add(pmsg);
		}
		
		
		/*Text Field saisie message*/
		txtmessage = new JTextField();
		txtmessage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txtmessage.setText("");
			}
		});
		txtmessage.setHorizontalAlignment(SwingConstants.CENTER);
		txtmessage.setText("Saisissez votre message ici ...");
		txtmessage.setBounds(36, 259, 286, 71);
		add(txtmessage);
		txtmessage.setColumns(10);
		
		/*bouton ajouter un fichier*/
		JButton btnFichier = new JButton("Fichier");
		btnFichier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Ouverture d'une fenêtre de selection fichier");
			}
		});
		btnFichier.setBounds(332, 272, 101, 45);
		add(btnFichier);
		
		/*bouton envoyer un message*/
		JButton btnEnvoyer = new JButton("Envoyer");
		btnEnvoyer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String txtDate = new SimpleDateFormat("dd MMMM yyyy", Locale.FRANCE).format(new Date());
				Message m = new Message(nom+"_"+prenom, txtDate,txtmessage.getText());
				ListMessage.add(m);
				PanelMessage p1 = new PanelMessage(m);
				panelChat.add(p1);
				txtmessage.setText("");
				validate();
			}
		});
		btnEnvoyer.setBounds(443, 264, 92, 60);
		add(btnEnvoyer);
		
		/*ComboBox des ip server*/
		JComboBox comboBoxIpServer = new JComboBox();
		comboBoxIpServer.setEditable(true);
		comboBoxIpServer.setModel(new DefaultComboBoxModel(new String[] {"192.168.12.55", "127.0.0.1"})); //TODO getAllIp()
		/*TODO remplir la combox avec une list d'ip*/
		comboBoxIpServer.setBounds(568, 79, 132, 34);
		add(comboBoxIpServer);
		
		/*bouton connecter*/
		JButton btnConnecter = new JButton("Connecter");
		btnConnecter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Connection au server ip :" + comboBoxIpServer.getSelectedItem().toString());
			}
		});
		btnConnecter.setBounds(714, 79, 98, 34);
		add(btnConnecter);
		
		
		JList list = new JList();
		String[] values2 = new String[] {"Luc_Ortiz", "Hugo_Vaillant", "Clarck_robinson", "Audrey_Claude", "Aur\u00E9lien_Fernandez"};
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"Luc_Ortiz", "Hugo_Vaillant", "Clarck_robinson", "Audrey_Claude", "Aur\u00E9lien_Fernandez"},
			values2 = values;
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.setBounds(568, 180, 265, 143);
		add(list);
		System.out.println("Numéro du contact selectionné" +list.getSelectedIndex());
		

		
	}
	
}
