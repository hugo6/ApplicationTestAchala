package modules.chat.clients;

import achala.communication.server.Server;
import achala.communication.server._Server;
import achala.communication.utilisateur.Utilisateur;
import achala.communication.utilisateur._Utilisateur;

public class LanceServer {

	public static void main(String[] args) {
		Server.startServer("src/achala/modules/chat/clients/policy");
		
		try
		{
			_Utilisateur user = new Utilisateur("Server", "Test");
			_Server srv = Server.getServer("192.168.43.84");
			
			user.connect(srv);
			
			srv.getSharedZone(user, "zoneTest");
			srv.getSharedZone(user, "chatRoom");
		}
		catch(Exception ex)
		{
			ex.printStackTrace();	
		}
	}

}
