package modules.chat.clients;

import java.rmi.RMISecurityManager;
import java.util.Scanner;

import modules.chat.Chat;
import modules.chat.util.Util.Cmd;
import achala.communication.server.Server;
import achala.communication.server._Server;
import achala.communication.utilisateur.Utilisateur;
import achala.communication.utilisateur._Utilisateur;

@SuppressWarnings("deprecation")
public class Client2 {

	public static void main(String[] args) {

		Scanner read = new Scanner(System.in);
		try
		{
			System.setProperty("java.security.policy", "src/achala/modules/chat/clients/policy");
			System.setProperty("java.net.SocketPermission", "src/achala/modules/chat/clients/policy");
			if (System.getSecurityManager() == null) {
				System.setSecurityManager(new RMISecurityManager());
			}
			
			_Utilisateur luc = new Utilisateur("Ortiz", "Luc");
			
			_Server srv = Server.getServer("192.168.56.1");
			luc.connect(srv);
			
			System.out.println("Start ?");
			read.next();
			
			_Utilisateur alexis = srv.getUtilisateur("Martinier", "Alexis");
			
			Chat c = new Chat(srv, luc, alexis);
			
			c.listener(luc);
			c.sender(luc, Cmd.EXIT);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			read.close();
		}
		
		//read.close();

	}
}
