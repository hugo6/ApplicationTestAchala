package modules.chat.thread;

import java.util.List;

import javax.swing.JPanel;

import achala.communication._RemotableObject;
import achala.communication._Shared;
import achala.communication.exception.CommunicationException;
import achala.communication.utilisateur._Utilisateur;
import application.panels.panelsChat.PanelChat;
import modules.chat.Chat;

public class ListenerThread extends Thread {

	private _Utilisateur u;
	private _Shared s;
	private boolean run;
	private JPanel panel;
	private Chat c;

	/**
	 * Construit un thread de reception de messages
	 * 
	 * @param u
	 *            _Utilisateur : utilisateur recevant les messages
	 * @param s
	 *            _Shared : zone dans laquelle lire les messages
	 */
	public ListenerThread(_Utilisateur u, _Shared s) {
		this.setU(u);
		this.setS(s);
		this.setRun(true);
		this.setPanel(null);
	}

	public ListenerThread(_Utilisateur u, _Shared s, JPanel panel) {
		this(u, s);
		this.setPanel(panel);
	}

	public ListenerThread(_Utilisateur u, _Shared s, Chat c) {
		this(u, s);
		this.setC(c);
	}

	/**
	 * Lance le thread permettant d'affichier les messages
	 */
	public void run() {
		List<_RemotableObject> objs;
		while (this.isRun()) {
			try {
				sleep(2000);
				objs = this.getU().receive(this.getS());
				for (_RemotableObject o : objs) {

					PanelChat.addMessageRecieve(o, this.getC());

					System.out.println(o.getDate().toString() + " " + o.getSender().toStringRemote() + " : ");
					System.out.println(o.getObject().toString());
				}
			} catch (CommunicationException c) {
				this.setRun(false);
			} catch (Exception e) {
				e.printStackTrace();
				break;
			}
		}
		System.out.println("Exit Listener");
		this.stop();
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

	private JPanel getPanel() {
		return panel;
	}

	private void setPanel(JPanel panel) {
		this.panel = panel;
	}

	private Chat getC() {
		return c;
	}

	private void setC(Chat c) {
		this.c = c;
	}

	public boolean isRun() {
		return run;
	}

	public void setRun(boolean run) {
		this.run = run;
	}

	public void stopListener() {
		this.setRun(false);
	}
}
