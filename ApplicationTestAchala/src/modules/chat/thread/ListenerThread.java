package modules.chat.thread;

import java.util.List;

import achala.communication._RemotableObject;
import achala.communication._Shared;
import achala.communication.exception.CommunicationException;
import achala.communication.utilisateur._Utilisateur;
import modules.chat.util.Util.Cmd;

public class ListenerThread extends Thread {

	private _Utilisateur u;
	private _Shared s;
	private boolean run;

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
					if (o.getObject().toString().equals(Cmd.EXIT.toString())) {
						System.out.println(o.getDate().toString() + " " + o.getSender().toStringRemote() + " : ");
						System.out.println(Cmd.message(Cmd.EXIT, o.getSender()));
						System.out.println(o.getObject().toString());
					}
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

	private boolean isRun() {
		return run;
	}

	private void setRun(boolean run) {
		this.run = run;
	}

	public void stopListener() {
		this.setRun(false);
	}
}
