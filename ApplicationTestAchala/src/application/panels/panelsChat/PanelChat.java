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
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
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
import javax.swing.text.DefaultCaret;

import achala.communication._RemotableObject;
import achala.communication._Shared;
import achala.communication.exception.CommunicationException;
import achala.communication.server.Server;
import achala.communication.server._Server;
import achala.communication.utilisateur.Utilisateur;
import achala.communication.utilisateur._Utilisateur;
import application.frames.FrameAjouterChatroom;
import application.frames.FrameConnexionChatroom;
import modules.chat.Chat;


public class PanelChat extends JPanel {
	
	private static final long serialVersionUID = 1L;
	/*field for message sending*/
	private JTextField txtmessage;
	/*message list*/
	private List<_RemotableObject> _listMessage;
	/*user list*/
	private List<_Utilisateur> _userList;
	/*shared list on server*/
	private List<_Shared> _sharedList;
	/*server*/
	private _Server server;
	/*user*/
	private _Utilisateur connectedUser;
	/*chat map*/
	private Map<String, Chat> _chats;
	
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
			//Creating the connected user
			connectedUser = new Utilisateur(nom, prenom);
			_listMessage = new ArrayList<_RemotableObject>();
		} 
		catch (RemoteException e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		catch (UnknownHostException e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		/**
		 * graphical panelChat settings
		 */
		setBackground(Color.LIGHT_GRAY);
		setLayout(null);
		
		//TODO read from a file the user messages
		
		
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
		txtmessage = new JTextField();
		txtmessage.setText("Please enter your message here...");
		txtmessage.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtmessage.setText("");
			}
		});
		txtmessage.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10){//press enter
					sendMessage(panelChat);
				}
			}
		});
		txtmessage.setHorizontalAlignment(SwingConstants.CENTER);
		txtmessage.setBounds(40, 259, 282, 71);
		txtmessage.setColumns(10);//TODO test setColumns useless?
		add(txtmessage);
		
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
				sendMessage(panelChat);
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
		
		/*LISTE ROOMCHAT*/
		_chats = new HashMap<String, Chat>();
		
		jlistRoomchat = new JList<Component>();
		jlistRoomchat.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				/**
				 * connexion chatRoom frame oppening
				 */
				 if (!arg0.getValueIsAdjusting())
				 {
					 String s = "" + jlistRoomchat.getModel().getElementAt(jlistRoomchat.getSelectedIndex());
					 FrameConnexionChatroom frame_co = new FrameConnexionChatroom(s);
				 }
				//String s = "" + jlistRoomchat.getModel().getElementAt(jlistRoomchat.getSelectedIndex());
			 	//_userList = new ArrayList<_Utilisateur>();
			 	//_Utilisateur u = _userList.get(jlistRoomchat.getSelectedIndex());
			 	
				try
				{
					String chatName = "" + jlistRoomchat.getModel().getElementAt(jlistRoomchat.getSelectedIndex());
				 	currentChat = _chats.get(chatName);
				 	
					lblNametoChat.setText(currentChat.getShared().getZoneName());
					
					currentChat.listener(panelChat);
				}
				catch (RemoteException e)
				{
					e.printStackTrace();
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
		comboxIpServer.setModel(new DefaultComboBoxModel<String>(new String[] {"147.171.167.198"})); // prend une liste d'objets
		comboxIpServer.setBounds(568, 79, 154, 34);
		add(comboxIpServer);
		
		
		/*BOUTON CONNECTER*/	
		JButton btnConnecter = new JButton("Connecter");
		btnConnecter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Connection au server ip :" + comboxIpServer.getSelectedItem().toString());
				//listServeurip.add(comboxIpServer.getModel().getSelectedItem());
				//seConnecter(comboxIpServer.getModel().getSelectedItem());
				try
				{
					server = (_Server)Server.getServer(comboxIpServer.getSelectedItem().toString());
					connectedUser.connect(server);
					_sharedList = server.getShares();
					List<String> zonesNames = new ArrayList<String>();
					for(_Shared s : _sharedList)
					{
						Chat c = new Chat(server, connectedUser, server.getUtilisateurs(), s.getZoneName());
						if(!_chats.containsKey(s.getZoneName()))
						{
							zonesNames.add(s.getZoneName());
							_chats.put(s.getZoneName(), c);
							System.out.println("Added : " + s.getZoneName());
						}
					}
					
					jlistRoomchat.setCellRenderer(new MyRenderer());
					jlistRoomchat.setModel(new AbstractListModel() {
						
						List<String> shares = zonesNames;
						public int getSize() {
							return shares.size();
						}
						public Object getElementAt(int index) {
							return shares.get(index);
						}
					});
					
					validate();
				}
				catch (MalformedURLException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				catch (RemoteException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				catch (NotBoundException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnConnecter.setBounds(732, 79, 98, 34);
		add(btnConnecter);	
		
	}
	
	public void sendMessage(JPanel panelChat){
		if(txtmessage.getText().isEmpty())return;

		_RemotableObject message = null;
		try 
		{
			message = new achala.communication.Message(connectedUser, txtmessage.getText());
			currentChat.send(message);
		} 
		catch (RemoteException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		catch (CommunicationException e1)
		{
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
