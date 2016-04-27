package modules.chat.clients;

import java.rmi.RMISecurityManager;
import java.util.Scanner;

import achala.communication.server.Server;
import achala.communication.server._Server;
import achala.communication.utilisateur.Utilisateur;
import achala.communication.utilisateur._Utilisateur;
import achala.modules.chat.Chat;
import achala.modules.chat.util.Util.Cmd;

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

			_Utilisateur alexis = new Utilisateur("Martinier", "Alexis");
			
			_Server srv = Server.getServer("192.168.56.1");
			alexis.connect(srv);
			
			System.out.println("Start ?");
			read.next();
			
			_Utilisateur luc = srv.getUtilisateur("Ortiz", "Luc");
			
			Chat c = new Chat(srv, alexis, luc);
			
			c.listener(alexis);
			c.sender(alexis, Cmd.EXIT);
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			read.close();
		}
		
		//read.close();

	}

}
