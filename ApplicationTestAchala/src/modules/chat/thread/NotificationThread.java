package modules.chat.thread;

import achala.communication._RemotableObject;
import achala.communication._Shared;
import achala.communication.utilisateur._Utilisateur;

public class NotificationThread extends Thread {

	private _Shared share;
	private _Utilisateur user;

	public NotificationThread(_Shared s, _Utilisateur u)
	{
		this.setShare(s);
		this.setUser(u);
	}
	
	public void run()
	{
		try
		{
			while(true)
			{
				if(this.getShare().isAllowed(this.getUser()) && this.getShare().isWait())
				{
					for(_RemotableObject rObj : this.getShare().getObjects())
					{
						if(!rObj.getSender().equals(this.getUser()))
							System.out.println("NOTIFICATION : " + rObj.getSender().toStringRemote());
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

	private _Utilisateur getUser() {
		return user;
	}

	private void setUser(_Utilisateur user) {
		this.user = user;
	}

	private _Shared getShare() {
		return share;
	}

	private void setShare(_Shared share) {
		this.share = share;
	}
}
