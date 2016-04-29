package modules.chat.util;

import java.rmi.RemoteException;

import achala.communication.utilisateur._Utilisateur;

public class Util {

	private Util instance = new Util();
	
	public enum Cmd
	{
		EXIT("/exit"),
		WIZZ("/wizz"),
		IP("/ip"),
		HELP("/help");
		
		private String cmd;
		Cmd(String cmd) { this.cmd = cmd; }
		
		public String toString()
		{
			return this.cmd;
		}
		public static Cmd getCommande(String cmd)
		{
			for(Cmd c : Cmd.values())
			{
				if(c.toString().equals(cmd))
					return c;
			}
			return null;
		}
		
		public static String message(Cmd cmd, _Utilisateur user) throws RemoteException{
			String strReturn = "";
			
			switch(cmd)
			{
				case EXIT:
					strReturn = user.toStringRemote() + " a quitter la conversation";
					break;
				case HELP:
					strReturn = "Liste des commandes :\n"
								+ "\t-/exit : quitte le chat\n"
								+ "\t-/ip : obtient l'adresse IP du poste\n"
								+ "\t-/wizz : envoyer un wizz a votre correspondant";
					break;
				case IP:
					strReturn = "Votre adresse IP est " + user.getIp();
					break;
				case WIZZ:
					strReturn = user.toStringRemote() + " vous a envoye un wizz";
					break;
				default:
					strReturn = "Command not found";
					break;			
			}
			
			return strReturn;
		}
	}
	
	private Util() {}
	
	public Util getInstance()
	{
		return instance;
	}
}
