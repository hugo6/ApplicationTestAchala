package application.panels.panelsChat;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.AbstractListModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import application.utils.Contact;
import application.utils.Message;

public class PanelChat extends JPanel {
	
	private JTextField txtmessage;
	private List<Message> _listMessage;
	private List<Contact> _listContact = new ArrayList<Contact>();
	/**
	* Create the panel.
	*/
	public PanelChat(String nom, String prenom, List<Message> ListMessage) {
		setBackground(Color.LIGHT_GRAY);
		setLayout(null);
		
		/*Initialisation de la liste des messages*/
		//TODO LIRE DANS UN FICHIER
		_listMessage = new ArrayList<Message>();
		_listMessage.add(new Message("Luc_ortiz", "18/12/2016 15h12", "Salut =)"));
		_listMessage.add(new Message("Hugo_Vaillant", "18/12/2016 15h16", "Salut =)"));
		_listMessage.add(new Message("Luc_ortiz", "18/12/2016 15h18", "comment vas tu ?!"));
		_listMessage.add(new Message("Hugo_Vaillant", "18/12/2016 15h18", "Bien, Merci Luc !"));
		
		
		/*Initialisation de la liste des contacts*/
		//TODO charger les contacts de la personne connectée
		_listContact = new ArrayList<Contact>();
		_listContact.add(new Contact("Luc_ortiz"));
		
		
		
		
		/**
		 * CHAT
		*/
		
		/*LABEL CHAT*/
		JLabel lblChat = new JLabel("CHAT :");
		lblChat.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblChat.setHorizontalAlignment(SwingConstants.CENTER);
		lblChat.setBounds(110, 13, 84, 25);
		add(lblChat);
		
		/*LABEL NAME TO CHAT*/
		JLabel lblNametoChat = new JLabel("Veuillez selectionner un contact");
		lblNametoChat.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNametoChat.setHorizontalAlignment(SwingConstants.CENTER);
		lblNametoChat.setBounds(200, 13, 318, 25);
		add(lblNametoChat);
		
		/*PANEL CHAT*/
		JPanel panelChat = new JPanel();
		panelChat.setBounds(10, 11, 459, 345);
		panelChat.setPreferredSize(new Dimension(500, 3000));
		add(panelChat);
		
		/*SCROLL CHAT*/
		JScrollPane scrollPaneChat = new JScrollPane(panelChat);
		scrollPaneChat.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneChat.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneChat.setBounds(40, 47, 478, 201);
		add(scrollPaneChat);

		/*INITIALISATION MESSAGES*/
		PanelMessage pmsg;
		for (Message m : _listMessage) {
			pmsg = new PanelMessage(m);
			panelChat.add(pmsg);
		}
		
		/*FIELD SAISIE MESSAGE */
		txtmessage = new JTextField();
		txtmessage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txtmessage.setText("");
			}
		});
		txtmessage.setHorizontalAlignment(SwingConstants.CENTER);
		txtmessage.setText("Saisissez votre message ici ...");
		txtmessage.setBounds(40, 259, 282, 71);
		add(txtmessage);
		txtmessage.setColumns(10);
		
		/*BOUTON FICHIER*/
		JButton btnFichier = new JButton("Fichier");
		btnFichier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Ouverture d'une fenêtre de selection fichier");
			}
		});
		btnFichier.setBounds(338, 264, 84, 60);
		add(btnFichier);

		/*BOUTON ENVOYER*/
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
		btnEnvoyer.setBounds(434, 264, 84, 60);
		add(btnEnvoyer);
		
		
		
		/**
		 * CONTACT
		*/
		/*LABEL Contacts*/
		JLabel lblContact = new JLabel("Contacts en ligne");
		lblContact.setHorizontalAlignment(SwingConstants.CENTER);
		lblContact.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblContact.setBounds(570, 144, 265, 25);
		add(lblContact);
		
		/*LISTE CONTACT*/
		JList<Component> jlistContact = new JList();
		jlistContact.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getID() == 401){
					System.out.println("touche entrée préssée");
					String s = "" + jlistContact.getModel().getElementAt(jlistContact.getSelectedIndex());
					lblNametoChat.setText(s);
					//charger messages du contact(modifier une liste locale)
				}
						
			}
			
		});
		jlistContact.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jlistContact.setModel(new AbstractListModel() {
			Object[] values = new Object[] {"Luc_Ortiz", "Hugo_Vaillant", "Clarck_robinson", "Audrey_Claude", "Aur\u00E9lien_Fernandez"};
			//list d'objets
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		jlistContact.setBounds(568, 180, 265, 150);
		add(jlistContact);
		
		
		/**
		 * SERVER
		*/
		/*LABEL Server*/
		JLabel lblipServer = new JLabel("Choix du serveur");
		lblipServer.setHorizontalAlignment(SwingConstants.CENTER);
		lblipServer.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblipServer.setBounds(570, 31, 260, 25);
		add(lblipServer);

		/*COMBOX SERVER*/
		JComboBox comboxIpServer = new JComboBox();
		comboxIpServer.setEditable(true);
		comboxIpServer.setModel(new DefaultComboBoxModel(new Object[] {"192.168.12.55", "127.0.0.1"})); // prend une liste d'objets
		comboxIpServer.setBounds(568, 79, 154, 34);
		add(comboxIpServer);
		
		
		/*BOUTON CONNECTER*/		
		JButton btnConnecter = new JButton("Connecter");
		btnConnecter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Connection au server ip :" + comboxIpServer.getSelectedItem().toString());
				//listServeurip.add(comboxIpServer.getModel().getSelectedItem());
				//seConnecter(comboxIpServer.getModel().getSelectedItem());
			}
		});
		btnConnecter.setBounds(732, 79, 98, 34);
		add(btnConnecter);
		
	}
	
}
