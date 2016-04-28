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
			System.setProperty("java.security.policy", "src/modules/chat/clients/policy");
			System.setProperty("java.net.SocketPermission", "src/modules/chat/clients/policy");
			if (System.getSecurityManager() == null) {
				System.setSecurityManager(new RMISecurityManager());
			}

			_Utilisateur alexis = new Utilisateur("Martinier", "Alexis");
			
			_Server srv = Server.getServer("192.168.43.84");
			alexis.connect(srv);
			
			System.out.println("Start ?");
			read.next();
			
			/*_Utilisateur luc = srv.getUtilisateur("Ortiz", "Luc");
			List<_Utilisateur> users = new ArrayList<_Utilisateur>();
			users.add(luc);*/
			Chat c = new Chat(srv, alexis, srv.getUtilisateurs(), "...");
			
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
