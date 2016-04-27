package modules.chat.thread;

import java.util.Scanner;

import achala.communication.Message;
import achala.communication._RemotableObject;
import achala.communication._Shared;
import achala.communication.utilisateur._Utilisateur;
import achala.modules.chat.util.Util.Cmd;

public class SenderThread extends Thread {

	private _Utilisateur u;
	private _Shared s;
	private Cmd escape;
	
	/**
	 * Construit un thread d'envoi de message
	 * @param sender _Utilisateur : utilisateur qui envoi les messages
	 * @param s _Shared : zone dans laquelle les messages sont envoye
	 * @param escape String : chaine de caracteres mettant fin a la communication
	 */
	public SenderThread(_Utilisateur sender, _Shared s, Cmd escape) {
		this.setU(sender);
		this.setS(s);
		this.setEscape(escape);
	}
	
	/**
	 * Lance le thread permettant de demander a l'utilisateur de saisir un message
	 */
	public void run(){
		
		Scanner read = new Scanner(System.in);
		String message = "";
		try
		{
			while(!message.equals(this.getEscape().toString()))
			{
				sleep(2000);
				message = read.nextLine();
				
				if(message.equals(Cmd.HELP.toString())){
					System.out.println(Cmd.message(Cmd.HELP, this.getU()));
				} else if(message.equals(Cmd.IP.toString())) {
					System.out.println(Cmd.message(Cmd.IP, this.getU()));
				} else {
					_RemotableObject msg = new Message(this.getU(), message);
					this.getU().send(this.getS(), msg);
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		System.out.println("Exit Sender");
		read.close();
	}

	private _Utilisateur getU() {
		return this.u;
	}

	private void setU(_Utilisateur u) {
		this.u = u;
	}

	private _Shared getS() {
		return this.s;
	}

	private void setS(_Shared s) {
		this.s = s;
	}

	private Cmd getEscape() {
		return this.escape;
	}

	private void setEscape(Cmd escape) {
		this.escape = escape;
	}
}
