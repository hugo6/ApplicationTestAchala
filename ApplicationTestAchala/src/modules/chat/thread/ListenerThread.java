package modules.chat.thread;

import java.awt.Component;
import java.util.List;

import javax.swing.JPanel;

import modules.chat.util.Util.Cmd;
import achala.communication._RemotableObject;
import achala.communication._Shared;
import achala.communication.utilisateur._Utilisateur;
import application.panels.panelsChat.PanelMessage;

public class ListenerThread extends Thread {

	private _Utilisateur u;
	private _Shared s;
	private JPanel component = null;
	
	/**
	 * Construit un thread de reception de messages
	 * @param u _Utilisateur : utilisateur recevant les messages
	 * @param s _Shared : zone dans laquelle lire les messages
	 */
	public ListenerThread(_Utilisateur u, _Shared s) {
		this.setU(u);
		this.setS(s);
	}
	
	public ListenerThread(_Utilisateur u, _Shared s, JPanel c){
		this(u,s);
		this.setComponent(c);
	}
	
	private JPanel getComponent() {
		return component;
	}

	private void setComponent(JPanel component) {
		this.component = component;
	}

	/**
	 * Lance le thread permettant d'affichier les messages
	 */
	public void run(){
		List<_RemotableObject> objs;
		while(true) {
			try
			{
				sleep(2000);
				objs = this.getU().receive(this.getS());
				for(_RemotableObject o : objs) {
					
					if(this.getComponent() != null){
						PanelMessage m = new PanelMessage(o);
						this.getComponent().add(m);
						this.getComponent().validate();
					}
					else{
						if(o.getObject().toString().equals(Cmd.EXIT.toString())){
							System.out.println(o.getDate().toString() + " " + o.getSender().toStringRemote() + " : ");
							System.out.println(Cmd.message(Cmd.EXIT, o.getSender()));
							System.out.println(o.getObject().toString());
						}
						System.out.println(o.getDate().toString() + " " + o.getSender().toStringRemote() + " : ");
						System.out.println(o.getObject().toString());
					}
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
				break;
			}
		}
		System.out.println("Exit Listener");
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
}
