package modules.chat.clients;

import achala.communication.server.Server;
import achala.communication.server._Server;
import achala.communication.utilisateur.Utilisateur;
import achala.communication.utilisateur._Utilisateur;

public class LanceServer {

	public static void main(String[] args) {
		Server.startServer("src/modules/chat/clients/policy");
		
		try
		{
			_Server srv = Server.getServer("147.171.167.198");
			_Utilisateur user = new Utilisateur("test", "test1");
			user.connect(srv);
			srv.getSharedZone(user, "chatTest");
			srv.getSharedZone(user, "zoneTest");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
