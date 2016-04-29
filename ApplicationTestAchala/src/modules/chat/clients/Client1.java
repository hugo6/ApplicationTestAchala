package modules.chat.clients;

import java.rmi.RMISecurityManager;
import java.util.Scanner;

import achala.communication.server.Server;
import achala.communication.server._Server;
import achala.communication.utilisateur.Utilisateur;
import achala.communication.utilisateur._Utilisateur;
import modules.chat.Chat;
import modules.chat.util.Util.Cmd;

@SuppressWarnings("deprecation")
public class Client1 {

	public static void main(String[] args) {

		Scanner read = new Scanner(System.in);
		try
		{
			System.setProperty("java.security.policy", "src/achala/modules/chat/clients/policy");
			System.setProperty("java.net.SocketPermission", "src/achala/modules/chat/clients/policy");
			if (System.getSecurityManager() == null) {
				System.setSecurityManager(new RMISecurityManager());
			}
			
			_Utilisateur user = new Utilisateur("Claude", "Audrey");
//			_Utilisateur user = new Utilisateur("Robinson", "Carl");
//			_Utilisateur user = new Utilisateur("Vaillant", "Hugo");
//			_Utilisateur user = new Utilisateur("Fernandes", "Aurelien");
//			_Utilisateur user = new Utilisateur("Ortiz", "Luc");
//			_Utilisateur user = new Utilisateur("Martinier", "Alexis");

			_Server srv = Server.getServer("192.168.43.84");
			user.connect(srv);
			
			System.out.println("Sur le serveur :");
			for(_Utilisateur u : srv.getUtilisateurs())
			{
				System.out.println(u.toStringRemote());
			}
			
			System.out.println("Start ?");
			read.next();
			
			Chat c = new Chat(srv, user, "zoneTest");
			
			System.out.println("Dans le chat :");
			for(_Utilisateur u : c.getShared().getUtilisateurs())
			{
				System.out.println(u.toStringRemote());
			}
			
			c.listener();
			c.sender(Cmd.EXIT);
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			read.close();
		}
		
		//read.close();

	}

}
