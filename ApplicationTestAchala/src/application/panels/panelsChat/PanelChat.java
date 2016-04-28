package application.panels.panelsChat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.List;
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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import achala.communication.Message;
import achala.communication._RemotableObject;
import achala.communication._Shared;
import achala.communication.server.Server;
import achala.communication.server._Server;
import achala.communication.utilisateur.Utilisateur;
import achala.communication.utilisateur._Utilisateur;
import application.frames.FrameAjouterChatroom;
import modules.chat.Chat;


public class PanelChat extends JPanel {
	
	/* TODO TDL
	 * 
	 */
	
	private static final long serialVersionUID = 1L;

	private JTextField txtMessage;
	
	private _Utilisateur connectedUser;
	private _Server server;
	private Map<String, Chat> chatRooms;
	private Chat currentChat;
	
	/**
	 * graphical components
	 */
	 private JList<Component> jlistRoomchat;
	 
	/**
	 * panel chat constructor
	 * @param nom
	 * @param prenom
	 */
	public PanelChat(String nom, String prenom) {
		
		try
		{
			connectedUser = new Utilisateur(nom, prenom);
			server = null;
			chatRooms = new HashMap<String, Chat>();
			currentChat = null;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		
		
		
		
		
		
		
		
		
		/**
		 * graphical panelChat settings
		 */
		setBackground(Color.LIGHT_GRAY);
		setLayout(null);
		
		
		/**
		 * CHAT Section---------------------------------------
		*/
		
		/*CHAT LABEL*/
		JLabel lblChat = new JLabel("CHAT :");
		lblChat.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblChat.setHorizontalAlignment(SwingConstants.CENTER);
		lblChat.setBounds(110, 13, 84, 25);
		add(lblChat);
		
		
		/*CHAT NAME LABEL*/
		JLabel lblNametoChat = new JLabel("Please select a chat room...");
		lblNametoChat.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNametoChat.setHorizontalAlignment(SwingConstants.CENTER);
		lblNametoChat.setBounds(200, 13, 318, 25);
		add(lblNametoChat);
		
		
		/*CHAT PANEL*/
		JPanel panelChat = new JPanel();
		panelChat.setBounds(10, 11, 459, 345);
		//TODO correction affichage + scroll automatique
		panelChat.setLayout(new BoxLayout(panelChat, BoxLayout.Y_AXIS));
		add(panelChat, BorderLayout.CENTER);
		
		
		/*CHAT SCROLL*/
		JScrollPane scrollPaneChat = new JScrollPane(panelChat);
		
		panelChat.setLayout(new BoxLayout(panelChat, BoxLayout.Y_AXIS));
		scrollPaneChat.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		//scrollPaneChat.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneChat.setBounds(40, 47, 478, 201);
		add(scrollPaneChat);
	
		
		/*FIELD MESSAGE WRITING*/
		txtMessage = new JTextField();
		txtMessage.setText("Please enter your message here...");
		txtMessage.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtMessage.setText("");
			}
		});
		txtMessage.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10){//press enter
					//TODO Send message
				}
			}
		});
		txtMessage.setHorizontalAlignment(SwingConstants.CENTER);
		txtMessage.setBounds(40, 259, 282, 71);
		txtMessage.setColumns(10);
		add(txtMessage);
		
		/*FILE BUTTON*/
		JButton btnFichier = new JButton("Fichier");
		btnFichier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO envoi de fichier a implémenter
			}
		});
		btnFichier.setBounds(338, 264, 84, 60);
		add(btnFichier);
		
		
		/*BOUTON ENVOYER*/
		JButton btnEnvoyer = new JButton("Envoyer");
		btnEnvoyer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO Send message
				sendMessage(txtMessage.getText());
			}
		});
		
		btnEnvoyer.setBounds(434, 264, 84, 60);
		add(btnEnvoyer);
		
		
		
		/**
		 * ROOMCHAT section---------------------------------------
		*/
		
		/*LABEL ROOMCHAT*/
		JLabel lblRoomchat = new JLabel("Online chat rooms");
		lblRoomchat.setHorizontalAlignment(SwingConstants.CENTER);
		lblRoomchat.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblRoomchat.setBounds(570, 144, 265, 25);
		add(lblRoomchat);
		
		jlistRoomchat = new JList<Component>();
		jlistRoomchat.addListSelectionListener(new ListSelectionListener() {
			//open new window to paswword
//			public void valueChanged(ListSelectionEvent arg0) {
//				if (!arg0.getValueIsAdjusting())
//				{
//					String s = "" + jlistRoomchat.getModel().getElementAt(jlistRoomchat.getSelectedIndex());
//					FrameConnexionChatroom frame_co = new FrameConnexionChatroom(s);
//				}
//			}
			@Override
			public void valueChanged(ListSelectionEvent e) {
				try
				{
					//Recuperation du chat + listener sur celui-ci
					String zoneName = "" + jlistRoomchat.getModel().getElementAt(jlistRoomchat.getSelectedIndex());
					
					if(currentChat == null || !currentChat.getShared().getZoneName().equals(zoneName))
					{
						currentChat = chatRooms.get(zoneName);
						currentChat.listener(panelChat);
					}
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
		});
			
		jlistRoomchat.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jlistRoomchat.setBounds(568, 180, 265, 150);
		add(jlistRoomchat);
		
		
		/**
		 * BUTTON ADD ROOMCHAT
		 */
		JButton button = new JButton("+");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrameAjouterChatroom frame_add = new FrameAjouterChatroom();
				//TODO faire la méthode getMapChat() et add des users ?
			}
		});
		button.setFont(new Font("Tahoma", Font.BOLD, 11));
		button.setBounds(845, 144, 57, 29);
		add(button);
		
		
		/**
		 * SERVER
		*/
		/*LABEL Server*/ 
		JLabel lblipServer = new JLabel("Server choice");
		lblipServer.setHorizontalAlignment(SwingConstants.CENTER);
		lblipServer.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblipServer.setBounds(570, 31, 260, 25);
		add(lblipServer);

		/*COMBOX SERVER*/
		JComboBox<String> comboxIpServer = new JComboBox<String>();
		comboxIpServer.setEditable(true);
		comboxIpServer.setModel(new DefaultComboBoxModel<String>(new String[] {"192.168.43.52", "192.168.43.84", "147.171.167.198"})); // prend une liste d'objets
		comboxIpServer.setBounds(568, 79, 154, 34);
		add(comboxIpServer);
		
		
		/*BOUTON CONNECTER*/	
		JButton btnConnecter = new JButton("Connecter");
		btnConnecter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Connection au server ip :" + comboxIpServer.getSelectedItem().toString());

				try
				{
					//Recuperation du serveur
					server = Server.getServer(comboxIpServer.getSelectedItem().toString());
					//Connexion du client au serveur
					connectedUser.connect(server);
					
					//Gestion de toutes les Rooms sur le serveur
					for(_Shared s : server.getShares())
					{
						//Creation d'un chat avec la zone de partage
						Chat c = new Chat(server, connectedUser, server.getUtilisateurs(), s.getZoneName());
						chatRooms.put(s.getZoneName(), c);
					}
					
					
					jlistRoomchat.setCellRenderer(new MyRenderer());
					jlistRoomchat.setModel(new AbstractListModel() {
						//Recupere le nom des zones de la HashMap
						Object[] shares = chatRooms.keySet().toArray();
						public int getSize() {
							return shares.length;
						}
						public Object getElementAt(int index) {
							return shares[index];
						}
					});
					
					validate();
				}
				catch (Exception ex)
				{
					ex.printStackTrace();
				}
				
				
			}
		});
		btnConnecter.setBounds(732, 79, 98, 34);
		add(btnConnecter);	
		
	}
	
	private void sendMessage(String message)
	{
		_RemotableObject objet = null;
		try
		{
			objet = new Message(connectedUser, message);
			currentChat.send(objet);
			//PanelMessage m = new PanelMessage(objet);
			System.out.println(objet.getSender().toStringRemote() +  " a envoye sur " + currentChat.getShared().getZoneName() + " " + objet.getObject().toString());
		}
		catch(Exception ex)
		{
			ex.printStackTrace();	
		}
	}
}
