package modules.chat.util;

import java.rmi.RemoteException;
import java.rmi.server.RemoteObject;
import java.util.ArrayList;

import achala.communication.Message;
import achala.communication.server._Server;
import achala.communication.utilisateur._Utilisateur;
import application.panels.panelsChat.PanelChat;

public class Commande extends RemoteObject {

	/**	**/
	private static final long serialVersionUID = 8678030778443404596L;

	/** Liste des commandes **/
	public static final Commande EXIT = new Commande("/exit", new String[] {}); // Sortir du chat
	public static final Commande HELP = new Commande("/help", new String[] {}); // Affiche l'aide
	public static final Commande IP = new Commande("/ip", new String[] {}); // Affiche adresse IP
	public static final Commande USERS = new Commande("/users", new String[] {}); // Affiche les utilisateurs


	/** Liste des attributs **/
	private String cmdLine;
	private String[] args;
	
	private _Server srv;
	private _Utilisateur userSrv;

	/**
	 * Constructeur private -> Singleton (Enum Like)
	 * 
	 * @param cmdLine
	 *            La valeur de la commande
	 * @param args
	 *            Les arguments de la commandes
	 */
	private Commande(String cmdLine, String[] args) {
		this.cmdLine = cmdLine;
		this.args = args;
		
		try {
			srv = PanelChat.getServer();
			userSrv = srv.getUtilisateur("Serveur", "Admin");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Permet d'executer le code correspondant à la commande Attention :
	 * execution du sender (lors de l'envoi)
	 * 
	 * @param cmd
	 *            La commande a executer
	 * @param user
	 *            L'utilisateur qui l'a envoyée
	 */
	public void executeSender(Commande cmd, _Utilisateur user) {

		

		if (cmd.equals(Commande.EXIT)) {
			PanelChat.currentChat.stopListener();
			PanelChat.clearRoom(PanelChat.currentChat);
		} else if (cmd.equals(Commande.HELP)) {
			String help = "La liste des commandes est :\n" 
						+ "\t-/exit : Quitte le chat.\n"
						+ "\t-/help : Affiche les aides pour commandes.\n" 
						+ "\t-/ip : Affiche l'adresse IP du poste\n"
						+ "\t-/users : Affiche le nom de tous les utilisateurs connectes sur le chat.\n";
			try {
				PanelChat.addMessage(new Message(userSrv, help), PanelChat.currentChat);
			} catch (RemoteException e) {
				System.out.println(help);
				e.printStackTrace();
			}
		} else if (cmd.equals(Commande.IP)) {
			String ip = "";
			try {
				ip = "Votre adresse IP est " + user.getIp() + ".";
				PanelChat.addMessage(new Message(userSrv, ip), PanelChat.currentChat);
			} catch (RemoteException e) {
				ip = "Votre adresse IP n'est pas accessible.";
				e.printStackTrace();
			}
			System.out.println(ip);
		} else if (cmd.equals(Commande.USERS)) {
			StringBuilder users = new StringBuilder();
			users.append("Les utilisateurs du salon sont :\n");
			try {
				for (_Utilisateur u : PanelChat.currentChat.getShared().getUtilisateurs()) {
					users.append("\t").append(u.toStringRemote()).append("\n");
				}
				PanelChat.addMessage(new Message(userSrv, users.toString()), PanelChat.currentChat);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Permet d'executer le code correspondant à la commande Attention :
	 * execution du sender (lors de la reception)
	 * 
	 * @param cmd
	 *            La commande a executer
	 * @param user
	 *            L'utilisateur qui l'a envoye
	 */
	public void executeReciever(Commande cmd, _Utilisateur user) {
		if (cmd.equals(Commande.EXIT)) {
			String msg = "";
			try {
				msg = user.toStringRemote() + " a quitte le salon";
				PanelChat.addMessage(new Message(userSrv, msg), PanelChat.currentChat);
			} catch (RemoteException e) {
				msg = "";
				e.printStackTrace();
			}
			System.out.println(msg);
		} else if (cmd.equals(Commande.HELP)) {
			// Nothing, local only
		} else if (cmd.equals(Commande.IP)) {
			// Nothing, local only
		} else if (cmd.equals(Commande.USERS)) {
			// Nothing, local only
		}
	}

	/**
	 * Permet d'obtenir la commande avec le string de cette derniere
	 * 
	 * @param cmd
	 *            Le string de la commande
	 * @return L'objet Commande correspondant
	 */
	public static Commande getCommandeByString(String cmd) {
		for (Commande commande : Commande.getAllCommandes()) {
			if (commande.getCmdLine().equals(cmd)) {
				return commande;
			}
		}
		return null;
	}

	/**
	 * Permet d'obtenir toutes les commandes
	 * 
	 * @return La liste de toutes les commandes
	 */
	public static ArrayList<Commande> getAllCommandes() {
		ArrayList<Commande> lst = new ArrayList<Commande>();
		lst.add(Commande.EXIT);
		lst.add(Commande.HELP);
		lst.add(Commande.IP);
		lst.add(Commande.USERS);
		return lst;
	}

	/**
	 * Permet d'obtenir l'attribut cmdLine de la commande
	 * 
	 * @return Le string de la commande
	 */
	public String getCmdLine() {
		return this.cmdLine;
	}

	/**
	 * Permet d'obtenir l'attribut args de la commande
	 * 
	 * @return Le tableau de string (arguments) de la commande
	 */
	public String[] getArgs() {
		return args;
	}
}
