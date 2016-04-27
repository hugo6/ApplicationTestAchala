package modules.chat.thread;

import achala.communication._RemotableObject;
import achala.communication._Shared;
import achala.communication.server._Server;
import achala.communication.utilisateur._Utilisateur;

public class NotificationThread extends Thread {

	private _Server server;
	private _Utilisateur user;

	public NotificationThread(_Server s, _Utilisateur u)
	{
		this.setServer(s);
		this.setUser(u);
	}
	
	public void run()
	{
		try
		{
			while(true)
			{
				for(_Shared s : this.getServer().getShares())
				{
					if(s.isAllowed(this.getUser()) && s.isWait())
					{
						for(_RemotableObject rObj : s.getObjects())
						{
							if(!rObj.getSender().equals(this.getUser()))
								System.out.println("NOTIFICATION : " + rObj.getSender().toStringRemote());
						}
					}
				}
				sleep(5000);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private _Server getServer() {
		return server;
	}

	private void setServer(_Server server) {
		this.server = server;
	}

	private _Utilisateur getUser() {
		return user;
	}

	private void setUser(_Utilisateur user) {
		this.user = user;
	}
}
