package modules.chat.clients;

import java.rmi.RemoteException;

import achala.communication._Shared;
import achala.communication.server.Server;
import achala.communication.server._Server;
import achala.communication.utilisateur.Utilisateur;
import achala.communication.utilisateur._Utilisateur;

public class LanceServer {

	public static void main(String[] args) {
		Server.startServer("src/modules/chat/clients/policy");
		
		try
		{
			_Utilisateur user = new Utilisateur("Server", "Test");
			_Server srv = Server.getServer("192.168.43.84");
			
			user.connect(srv);
			
			srv.getSharedZone(user, "zoneTest");
			srv.getSharedZone(user, "chatRoom");
			
//			new Thread(){
//				public void run(){
//					try {
//						while(srv.getShares().size() != 5)
//						{
//							sleep(5000);
//							for(_Shared s : srv.getShares())
//							{
//								System.out.println(s.getZoneName());
//							}
//						}
//					} catch (RemoteException | InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
//			}.start();
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();	
		}
	}

}
