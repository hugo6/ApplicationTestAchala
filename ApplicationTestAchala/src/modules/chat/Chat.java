package modules.chat;

import java.rmi.RemoteException;
import java.util.List;

import javax.swing.JPanel;

import modules.chat.thread.ListenerThread;
import modules.chat.thread.NotificationThread;
import modules.chat.thread.SenderThread;
import modules.chat.util.Util.Cmd;
import achala.communication._RemotableObject;
import achala.communication._Shared;
import achala.communication.exception.CommunicationException;
import achala.communication.server.Server;
import achala.communication.server._Server;
import achala.communication.utilisateur._Utilisateur;

public class Chat {

	private _Utilisateur current;
	private List<_Utilisateur> others;
	private _Server server;
	private _Shared shared;
	
	/**
	 * Constructeur d'un chat entre utilisateurs u1 et u2 sur le serveur
	 * @require connected : u1 & u2 connecte
	 * @param ipSrv String : ip du serveur
	 * @param current _Utilisateur : utilisateur souhaitant communiquer avec u2
	 * @param other List<_Utilisateur> : utilisateur a contacter
	 */
	public Chat(String ipSrv, _Utilisateur current, List<_Utilisateur> others, String chatName) {
		
		try
		{
			this.setServer(Server.getServer(ipSrv));
			this.setCurrent(current);
			this.setOther(others);
			this.getOther().add(this.getCurrent());
			
			_Shared correspondance = this.getServer().getSharedZone(this.getCurrent(), chatName);
			this.setShared(correspondance);
			this.getShared().addUsers(getOther());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Constructeur d'un chat entre utilisateurs u1 et u2 sur le serveur
	 * @param srv _Server : serveur de communication
	 * @param curent _Utilisateur : utilisateur souhaitant communiquer avec u2
	 * @param other List<_Utilisateur> : utilisateur a contacter
	 */
	public Chat(_Server srv, _Utilisateur curent, List<_Utilisateur> other, String chatName) {
		
		try
		{
			this.setServer(srv);
			this.setCurrent(curent);
			this.setOther(other);
			
			_Shared correspondance = this.getServer().getSharedZone(this.getCurrent(), chatName);
			correspondance.addUsers(other);
			this.setShared(correspondance);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public _Utilisateur getCurrent() {
		return current;
	}

	private void setCurrent(_Utilisateur current) {
		this.current = current;
	}

	public List<_Utilisateur> getOther() {
		return others;
	}

	private void setOther(List<_Utilisateur> others) {
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
	
	/**
	 * Lance le thread d'ecoute du chat
	 * @throws RemoteException leve une exception en cas d'echec de communication
	 */
	public void listener() throws RemoteException {
		
		ListenerThread listener = new ListenerThread(this.getCurrent(), this.getShared());
		listener.start();
		
		NotificationThread notif = new NotificationThread(this.getServer(), this.getCurrent());
		notif.start();
	}
	
	public void listener(JPanel panel) throws RemoteException {
		
		ListenerThread listener = new ListenerThread(this.getCurrent(), this.getShared(), panel);
		listener.start();
		
		NotificationThread notif = new NotificationThread(this.getServer(), this.getCurrent());
		notif.start();
	}
	/**
	 * Lance le thread d'envoi sur le chat
	 * @param escape String : chaine de caractere mettant fin a la communication
	 * @throws RemoteException leve une exception en cas d'echec de communication
	 */
	public void sender(Cmd escape) throws RemoteException {
		
		SenderThread sender = new SenderThread(this.getCurrent(), this.getShared(), escape);
		sender.start();
	}
	
	/**
	 * Envoi l'objet rObject sur le chat
	 * @param rObject _RemotableObject : objet a envoyer sur le chat
	 * @throws RemoteException leve une exception en cas d'echec de communication
	 * @throws CommunicationException leve une exception en cas d'acces refuse
	 */
	public void send(_RemotableObject rObject) throws RemoteException, CommunicationException {
		this.getCurrent().send(this.getShared(), rObject);
	}
	
}
