package application.panels.panelsChat;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.swing.AbstractListModel;
import javax.swing.BoxLayout;
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

import modules.chat.Chat;
import achala.communication._RemotableObject;
import achala.communication.exception.CommunicationException;
import achala.communication.server.Server;
import achala.communication.server._Server;
import achala.communication.utilisateur.Utilisateur;
import achala.communication.utilisateur._Utilisateur;

public class PanelChat extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private JTextField txtmessage;
	private List<_RemotableObject> _listMessage;
	private List<_Utilisateur> _userList;
	private _Server server;
	private _Utilisateur user;
	private Map<_Utilisateur, Chat> _chats;
	
	/*Graphical component*/
	 JList<Component> jlistContact;
		
		/**
		 * Create the panel.
		 * **/ 
	
	public PanelChat(String nom, String prenom) {
		try {
			user = new Utilisateur(nom, prenom);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		setBackground(Color.LIGHT_GRAY);
		setLayout(null);
		
		/*Initialisation de la liste des messages*/
		//TODO LIRE DANS UN FICHIER les messages de la personne connectée
		_listMessage = new ArrayList<_RemotableObject>();
		
		/*Initialisation de la liste des contacts*/
		//TODO charger les contacts de la personne connectée
		_userList = new ArrayList<_Utilisateur>();
		
		_chats = new HashMap<_Utilisateur, Chat>();
		
		
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
		panelChat.addKeyListener(new KeyAdapter() {
		});
		panelChat.setBounds(10, 11, 459, 345);
		//panelChat.setPreferredSize(new Dimension(500, 3000));
		panelChat.setLayout(new BoxLayout(panelChat, BoxLayout.Y_AXIS));
		add(panelChat);
		
		/*SCROLL CHAT*/
		JScrollPane scrollPaneChat = new JScrollPane(panelChat);
		scrollPaneChat.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneChat.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneChat.setBounds(40, 47, 478, 201);
		add(scrollPaneChat);
	
		
		/*FIELD SAISIE MESSAGE */
		txtmessage = new JTextField();
		txtmessage.setText("Veuillez saisir votre message...");
		txtmessage.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtmessage.setText("");
			}
		});
		
		txtmessage.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10){
					envoyerMessage(panelChat);
				}
			}
		});
		txtmessage.setHorizontalAlignment(SwingConstants.CENTER);
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
				envoyerMessage(panelChat);
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
		jlistContact = new JList();
		jlistContact.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == 10){
					String s = "" + jlistContact.getModel().getElementAt(jlistContact.getSelectedIndex());
					_Utilisateur u = _userList.get(jlistContact.getSelectedIndex());
					try {
						lblNametoChat.setText(u.toStringRemote());
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
						Chat c;						
						try {
							List<_Utilisateur> users = new ArrayList<_Utilisateur>();
							users.add(server.getUtilisateur(u.getNom(), u.getPrenom()));
							c = new Chat(server, user, users, "...");
							_chats.put(u, c);
							_chats.get(u).listener(panelChat);
							//c.sender(user, Cmd.EXIT);
						} catch (RemoteException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}
						
			}
			
		});
		jlistContact.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
		comboxIpServer.setModel(new DefaultComboBoxModel(new Object[] {"147.171.167.132", "192.168.56.1", "192.168.12.55", "127.0.0.1"})); // prend une liste d'objets
		comboxIpServer.setBounds(568, 79, 154, 34);
		add(comboxIpServer);
		
		
		/*BOUTON CONNECTER*/		
		JButton btnConnecter = new JButton("Connecter");
		btnConnecter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Connection au server ip :" + comboxIpServer.getSelectedItem().toString());
				//listServeurip.add(comboxIpServer.getModel().getSelectedItem());
				//seConnecter(comboxIpServer.getModel().getSelectedItem());
				try {
					server = (_Server)Server.getServer(comboxIpServer.getSelectedItem().toString());
					user.connect(server);
					_userList = server.getUtilisateurs();
					for(_Utilisateur user : _userList){
						System.out.println(user.getNom());
					}
					
					jlistContact.setCellRenderer(new MyRenderer());
					jlistContact.setModel(new AbstractListModel() {
						List<_Utilisateur> values = new ArrayList<_Utilisateur>(_userList);
						public int getSize() {
							return values.size();
						}
						public Object getElementAt(int index) {
							return values.get(index);
						}
					});
					
					validate();
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NotBoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnConnecter.setBounds(732, 79, 98, 34);
		add(btnConnecter);	
		
	}
	
	public void envoyerMessage(JPanel panelChat){
		if(txtmessage.getText().isEmpty())return;
		String txtDate = new SimpleDateFormat("dd MMMM yyyy", Locale.FRANCE).format(new Date());
		_RemotableObject message = null;
		try {
			Chat c = _chats.get(_userList.get(jlistContact.getSelectedIndex()));
			message = new achala.communication.Message(user, txtmessage.getText());
			c.send(message);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (CommunicationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		_listMessage.add(message);
		PanelMessage p1 = new PanelMessage(message);
		panelChat.add(p1);
		txtmessage.setText("");
		validate();
	}
	
}
