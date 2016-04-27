package modules.chat;

import java.rmi.RemoteException;

import achala.communication._Shared;
import achala.communication.server.Server;
import achala.communication.server._Server;
import achala.communication.utilisateur._Utilisateur;
import achala.modules.chat.exception.ChatException;
import achala.modules.chat.thread.ListenerThread;
import achala.modules.chat.thread.NotificationThread;
import achala.modules.chat.thread.SenderThread;
import achala.modules.chat.util.Util.Cmd;

public class Chat {

	private _Utilisateur user1;
	private _Utilisateur user2;
	private _Server server;
	private _Shared correspondance;
	
	/**
	 * Constructeur d'un chat entre utilisateurs u1 et u2 sur le serveur
	 * @require connected : u1 & u2 connecte
	 * @param ipSrv String : ip du serveur
	 * @param u1 _Utilisateur : utilisateur souhaitant communiquer avec u2
	 * @param u2 _Utilisateur : utilisateur à contacter
	 */
	public Chat(String ipSrv, _Utilisateur u1, _Utilisateur u2) {
		
		try
		{
			this.setServer(Server.getServer(ipSrv));
			this.setUser1(u1);
			this.setUser2(u2);
			
			_Shared correspondance = this.getServer().getSharedZone(this.getUser1(), this.getUser2());
			this.setCorrespondance(correspondance);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Constructeur d'un chat entre utilisateurs u1 et u2 sur le serveur
	 * @param srv _Server : serveur de communication
	 * @param u1 _Utilisateur : utilisateur souhaitant communiquer avec u2
	 * @param u2 _Utilisateur : utilisateur à contacter
	 */
	public Chat(_Server srv, _Utilisateur u1, _Utilisateur u2) {
		
		try
		{
			this.setServer(srv);
			this.setUser1(u1);
			this.setUser2(u2);
			
			_Shared correspondance = this.getServer().getSharedZone(this.getUser1(), this.getUser2());
			this.setCorrespondance(correspondance);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public _Utilisateur getUser1() {
		return user1;
	}

	private void setUser1(_Utilisateur user1) {
		this.user1 = user1;
	}

	public _Utilisateur getUser2() {
		return user2;
	}

	private void setUser2(_Utilisateur user2) {
		this.user2 = user2;
	}

	public _Server getServer() {
		return server;
	}

	private void setServer(_Server server) {
		this.server = server;
	}

	public _Shared getCorrespondance() {
		return correspondance;
	}

	private void setCorrespondance(_Shared correspondance) {
		this.correspondance = correspondance;
	}
	
	/**
	 * Lance le thread d'ecoute du chat
	 * @param u _Utilisateur : utilisateur qui ecoute sur le chat
	 * @throws RemoteException leve une exception en cas d'echec de communication
	 * @throws ChatException leve une exception en cas d'erreur sur le chat
	 */
	public void listener(_Utilisateur u) throws RemoteException, ChatException {
		if(!this.getCorrespondance().isAllowed(u)) throw new ChatException("Utilisateur non autorisé");
		
		ListenerThread listener = new ListenerThread(u, this.getCorrespondance());
		listener.start();
		
		NotificationThread notif = new NotificationThread(this.getServer(), u);
		notif.start();
	}
	
	/**
	 * Lance le thread d'envoi sur le chat
	 * @param u _Utilisateur : utilisateur qui envoi sur le chat
	 * @param escape String : chaine de caractere mettant fin a la communication
	 * @throws RemoteException leve une exception en cas d'echec de communication
	 * @throws ChatException leve une exception en cas d'erreur sur le chat
	 */
	public void sender(_Utilisateur u, Cmd escape) throws RemoteException, ChatException {
		if(!this.getCorrespondance().isAllowed(u)) throw new ChatException("Utilisateur non autorisé");
		
		SenderThread sender = new SenderThread(u, this.getCorrespondance(), escape);
		sender.start();
	}
	
}
