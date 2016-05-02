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
import java.io.File;
import java.io.FileInputStream;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.swing.AbstractListModel;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
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
import application.frames.FrameConnexionChatroom;
import modules.chat.Chat;
import modules.chat.SecureCorrespondance;
import modules.chat._SecureCorrespondance;
import modules.chat.util.Commande;


public class PanelChat extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private static Map<Chat, List<_RemotableObject>> messageList = new HashMap<Chat, List<_RemotableObject>>();

	private JTextField txtMessage;
	private static JLabel lblNametoChat;
	
	public static FrameConnexionChatroom frameConnexion = null;
	
	private static _Utilisateur connectedUser;
	private static _Server server;
//	private Map<String, Chat> chatRooms;
	public static Chat currentChat;
	
	/**
	 * graphical components
	 */
	 private static JList<Component> jlistRoomchat;
	 private static JPanel panelChat;
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
//			chatRooms = new HashMap<String, Chat>();
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
		lblNametoChat = new JLabel("Selectionner une salle de chat ...");
		lblNametoChat.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNametoChat.setHorizontalAlignment(SwingConstants.CENTER);
		lblNametoChat.setBounds(200, 13, 318, 25);
		add(lblNametoChat);
		
		
		/*CHAT PANEL*/
		panelChat = new JPanel();
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
		txtMessage.setText(" Entrer votre message ici");
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
					sendMessage(txtMessage.getText());
				}
			}
		});
		txtMessage.setHorizontalAlignment(SwingConstants.LEFT);
		txtMessage.setBounds(40, 259, 384, 71);
		txtMessage.setColumns(10);
		add(txtMessage);
		
		
		/*BOUTON ENVOYER*/
		JButton btnEnvoyer = new JButton("Envoyer");
		btnEnvoyer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendMessage(txtMessage.getText());
			}
		});
		
		btnEnvoyer.setBounds(434, 264, 84, 60);
		add(btnEnvoyer);
		
		
		
		/**
		 * ROOMCHAT section---------------------------------------
		*/
		
		/*LABEL ROOMCHAT*/
		JLabel lblRoomchat = new JLabel("Salle de chat en ligne");
		lblRoomchat.setHorizontalAlignment(SwingConstants.CENTER);
		lblRoomchat.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblRoomchat.setBounds(570, 144, 265, 25);
		add(lblRoomchat);
		
		jlistRoomchat = new JList<Component>();
		jlistRoomchat.addListSelectionListener(new ListSelectionListener() {
			//open new window to paswword
			@Override
			public void valueChanged(ListSelectionEvent e) {
				try
				{
					//Recuperation du chat + listener sur celui-ci
					String zoneName = "" + jlistRoomchat.getModel().getElementAt(jlistRoomchat.getSelectedIndex());

					//avec password
					if(getSelectedChat(zoneName).getShared().getClassShared() == SecureCorrespondance.class && !getSelectedChat(zoneName).isThreadRun()){
						String password = ((_SecureCorrespondance)getSelectedChat(zoneName).getShared()).getPassword();
						if(frameConnexion == null)
							frameConnexion = new FrameConnexionChatroom(zoneName, password);
					} else {				
						//sans password
						changeChat(zoneName);
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
		button.setBounds(843, 146, 49, 29);
		add(button);
		
		/**
		 * REFRESH BUTTON
		 */
		JButton btnRefresh = new JButton();
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				refreshRooms(server);
			}
		});
		btnRefresh.setIcon(new ImageIcon(PanelChat.class.getResource("/com/sun/javafx/scene/web/skin/Undo_16x16_JFX.png")));
		btnRefresh.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnRefresh.setBounds(843, 180, 49, 29);
		add(btnRefresh);
		
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
		String[] ip = new String[10];
		JComboBox<String> comboxIpServer = new JComboBox<String>();
		comboxIpServer.setEditable(true);
		comboxIpServer.setModel(new DefaultComboBoxModel<String>(new String[] {"192.168.43.138", "192.168.43.84", "147.171.167.198"})); // prend une liste d'objets
		//comboxIpServer.setModel(new DefaultComboBoxModel<String>(ip()));
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
					
					refreshRooms(server);
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
	
	public static _Server getServer() {
		return server;
	}

	public static _Utilisateur getConnectedUser() {
		return connectedUser;
	}

	/**
	 * 
	 * @param message : message to send
	 */
	private void sendMessage(String message)
	{
		_RemotableObject objet = null;
		try
		{
			objet = new Message(connectedUser, message);
			currentChat.send(objet);
			System.out.println(objet.getSender().toStringRemote() +  " a envoye sur " + currentChat.getShared().getZoneName() + " " + objet.getObject().toString());
			addMessageSend(objet, currentChat);
			this.txtMessage.setText("");
		}
		catch(Exception ex)
		{
			ex.printStackTrace();	
		}
	}
	
	public static void affichagePanel(Chat chat)
	{
		if(chat != currentChat) return;
		
		panelChat.removeAll();
		try
		{
			for(_RemotableObject o : messageList.get(chat))
			{
				PanelMessage pm = new PanelMessage(o);
				panelChat.add(pm);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}

		panelChat.revalidate();
	}
	
	public static void addMessage(_RemotableObject objet, Chat chat)
	{
		try {

			if(messageList.containsKey(chat)){
				messageList.get(chat).add(objet);
				affichagePanel(chat);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void addMessageSend(_RemotableObject objet, Chat chat)
	{
		try {

			if(messageList.containsKey(chat)){
				if (Commande.getCommandeByString(objet.getObject().toString()) != null)
					Commande.getCommandeByString(objet.getObject().toString()).executeSender(Commande.getCommandeByString(objet.getObject().toString()), connectedUser);
				else{
					addMessage(objet, chat);
				}
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public static void addMessageRecieve(_RemotableObject objet, Chat chat)
	{
		try {

			if(messageList.containsKey(chat)){
				if (Commande.getCommandeByString(objet.getObject().toString()) != null)
					Commande.getCommandeByString(objet.getObject().toString()).executeReciever(Commande.getCommandeByString(objet.getObject().toString()), connectedUser);
				else{
					addMessage(objet, chat);
				}
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean nameAlreadyTaken(String name)
	{
		try {
			return server.alreadyExist(name);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	public static void changeChat(String zoneName)
	{
		try
		{
			currentChat = getSelectedChat(zoneName);
			lblNametoChat.setText(zoneName);
			if(!currentChat.isThreadRun())
				currentChat.listener();
			affichagePanel(currentChat);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	public static Chat getSelectedChat(String zoneName)
	{
		try
		{
			if(currentChat == null || !currentChat.getShared().getZoneName().equals(zoneName))
			{
				for (Chat chat : messageList.keySet()) {
					if (chat.getShared().getZoneName().equals(zoneName)) {
						return chat;
					}
				}
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		return currentChat;
	}
	
	/**
	 * Rafraichi la liste des rooms sur le serveur
	 * @param server _Server : serveur sur lequel chercher les rooms
	 */
	public static void refreshRooms(_Server server)
	{
		try
		{
			List<String> zonesNames = new ArrayList<String>();
			
			//Gestion de toutes les Rooms sur le serveur
			for(_Shared s : server.getShares())
			{
				//Creation d'un chat avec la zone de partage
				Chat c = new Chat(server, connectedUser, server.getUtilisateurs(), s.getZoneName());
				
				if(!messageList.containsKey(c))
				{
					c.getShared().addUsers(server.getUtilisateurs());
					messageList.put(c, new ArrayList<_RemotableObject>());
				}
				
				zonesNames.add(s.getZoneName());
			}
			
			
			jlistRoomchat.setCellRenderer(new MyRenderer());
			jlistRoomchat.setModel(new AbstractListModel() {
				//Recupere le nom des zones de la HashMap
				Object[] shares = zonesNames.toArray();
				public int getSize() {
					return shares.length;
				}
				public Object getElementAt(int index) {
					return shares[index];
				}
			});
			
			jlistRoomchat.revalidate();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	
	public String[] ip() {

		HashSet<String> adresses = new HashSet<String>();
		try
		{
			Enumeration<NetworkInterface> e = NetworkInterface.getNetworkInterfaces();
			while (e.hasMoreElements()) {
				Enumeration<InetAddress> i = e.nextElement().getInetAddresses();
				while (i.hasMoreElements()) {
					InetAddress a = i.nextElement();
					if (a instanceof Inet4Address && !a.isLoopbackAddress()) {
						adresses.add(a.getHostAddress());
					}
				}
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		String[] ip = new String[adresses.size()];
		int i = 0;
		for(String str : adresses)
		{
			ip[i] = str;
			i++;
		}
		
		return ip;
	}

	public static void clearRoom(Chat chat) {
		messageList.get(chat).clear();
		affichagePanel(chat);
	}
}
