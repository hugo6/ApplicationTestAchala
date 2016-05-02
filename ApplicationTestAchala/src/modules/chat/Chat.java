package modules.chat;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

import javax.swing.JPanel;

import achala.communication.Message;
import achala.communication._RemotableObject;
import achala.communication._Shared;
import achala.communication.exception.CommunicationException;
import achala.communication.server.Server;
import achala.communication.server._Server;
import achala.communication.utilisateur._Utilisateur;
import modules.chat.thread.ListenerThread;
import modules.chat.thread.NotificationThread;
import modules.chat.thread.SenderThread;
import modules.chat.util.Util.Cmd;

public class Chat {

	private _Utilisateur current;
	private List<_Utilisateur> others;
	private _Server server;
	private _Shared shared;
	private boolean threadRun;
	
	private ListenerThread listen;
	private NotificationThread notifs;

	/**
	 * Constructeur d'un chat entre utilisateurs u1 et u2 sur le serveur
	 * 
	 * @param srv
	 *            _Server : serveur de communication
	 * @param current
	 *            _Utilisateur : utilisateur souhaitant communiquer avec u2
	 * @param chatName
	 *            String : nom du chat
	 */
	public Chat(_Server srv, _Utilisateur current, String chatName) {
		try {
			this.setServer(srv);
			this.setCurrent(current);
			this.setOthers(this.getServer().getUtilisateurs());
			
			_Shared correspondance = this.getServer().getSharedZone(this.getCurrent(), chatName);
			correspondance.addUsers(others);
			this.setShared(correspondance);
			
			this.setThreadRun(false);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Constructeur d'un chat entre utilisateurs u1 et u2 sur le serveur
	 * 
	 * @param srv
	 *            _Server : serveur de communication
	 * @param current
	 *            _Utilisateur : utilisateur souhaitant communiquer avec u2
	 * @param others
	 *            List<_Utilisateur> : utilisateur a contacter
	 * @param chatName
	 *            String : nom du chat
	 */
	public Chat(_Server srv, _Utilisateur current, List<_Utilisateur> others, String chatName) {
		this(srv, current, chatName);
		this.setOthers(others);
	}

	/**
	 * Constructeur d'un chat entre utilisateurs u1 et u2 sur le serveur
	 * 
	 * @require connected : u1 & u2 connecte
	 * @param ipSrv
	 *            String : ip du serveur
	 * @param current
	 *            _Utilisateur : utilisateur souhaitant communiquer avec u2
	 * @param other
	 *            List<_Utilisateur> : utilisateur a contacter
	 * @throws NotBoundException
	 *             leve une exception en cas d'impossibilite de bind
	 * @throws RemoteException
	 *             leve une exception en cas d'echec de communication
	 * @throws MalformedURLException
	 *             leve une excpetion en cas d'URL mal formee
	 */
	public Chat(String ipSrv, _Utilisateur current, List<_Utilisateur> others, String chatName)
			throws MalformedURLException, RemoteException, NotBoundException {
		this(Server.getServer(ipSrv), current, others, chatName);
	}

	/**
	 * Copie le chat
	 * 
	 * @param c
	 *            Chat : chat a copier
	 * @throws RemoteException
	 *             leve une excpetion en cas d'echec de communication
	 */
	public Chat(Chat c) throws RemoteException {
		this(c.getServer(), c.getCurrent(), c.getShared().getZoneName());
	}

	public _Utilisateur getCurrent() {
		return current;
	}

	private void setCurrent(_Utilisateur current) {
		this.current = current;
	}

	public List<_Utilisateur> getOthers() {
		return others;
	}

	private void setOthers(List<_Utilisateur> others) {
		this.others = others;
	}

	public _Server getServer() {
		return server;
	}

	private void setServer(_Server server) {
		this.server = server;
	}

	public _Shared getShared() {
		return shared;
	}

	private void setShared(_Shared shared) {
		this.shared = shared;
	}

	public boolean isThreadRun() {
		return threadRun;
	}

	public void setThreadRun(boolean threadRun) {
		this.threadRun = threadRun;
	}
	
	private ListenerThread getListen() {
		return listen;
	}

	private void setListen(ListenerThread listen) {
		this.listen = listen;
	}

	private NotificationThread getNotifs() {
		return notifs;
	}

	private void setNotifs(NotificationThread notifs) {
		this.notifs = notifs;
	}

	/**
	 * Lance le thread d'ecoute du chat
	 * 
	 * @throws RemoteException
	 *             leve une exception en cas d'echec de communication
	 */
	public void listener() throws RemoteException {

		this.setListen(new ListenerThread(this.getCurrent(), this.getShared(), this));
		this.getListen().start();
		
		this.setNotifs(new NotificationThread(this.getShared(), this.getCurrent()));
		this.getNotifs().start();
		
		this.setThreadRun(true);
	}

	public void stopListener() {
		this.getListen().setRun(false);
		this.setThreadRun(false);
	}

	/**
	 * Lance le thread d'envoi sur le chat
	 * 
	 * @param escape
	 *            String : chaine de caractere mettant fin a la communication
	 * @throws RemoteException
	 *             leve une exception en cas d'echec de communication
	 */
	public void sender(Cmd escape) throws RemoteException {

		SenderThread sender = new SenderThread(this.getCurrent(), this.getShared(), escape);
		sender.start();
	}

	/**
	 * Envoi l'objet rObject sur le chat
	 * 
	 * @param rObject
	 *            _RemotableObject : objet a envoyer sur le chat
	 * @throws RemoteException
	 *             leve une exception en cas d'echec de communication
	 * @throws CommunicationException
	 *             leve une exception en cas d'acces refuse
	 */
	public void send(_RemotableObject rObject) throws RemoteException, CommunicationException {
		this.getCurrent().send(this.getShared(), rObject);
	}

	/**
	 * Envoi le message sur le chat
	 * 
	 * @param message
	 *            String : message a envoyer
	 * @throws RemoteException
	 *             leve une exception en cas d'echec de communication
	 * @throws CommunicationException
	 *             leve une exception en cas d'acces refuse
	 */
	public void send(String message) throws RemoteException, CommunicationException {
		_RemotableObject msg = new Message(this.getCurrent(), message);
		this.getCurrent().send(this.getShared(), msg);
	}
	
	/**
	 * Verifie l'egalite des chats
	 * @param chat
	 * @return boolean : true si les chats sont les meme, false dans le cas contraire
	 * @throws RemoteException leve une exception en cas d'echec de communication
	 */
	public boolean equals(Chat chat) throws RemoteException {
		if(this.getShared().getZoneName().equals(chat.getShared().getZoneName())) return true;
		return false;
	}

}
