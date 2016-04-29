package modules.chat;

import java.rmi.RemoteException;
import java.util.List;

import achala.communication.Correspondance;
import achala.communication.utilisateur._Utilisateur;

public class SecureCorrespondance extends Correspondance implements _SecureChat {

	/** **/
	private static final long serialVersionUID = 9146888497847707297L;
	private String password;

	public SecureCorrespondance(List<_Utilisateur> users, String rmiAdresse, String zoneName) throws RemoteException {
		super(users, rmiAdresse, zoneName);
		// TODO Auto-generated constructor stub
	}

	public SecureCorrespondance(List<_Utilisateur> users, String rmiAdresse, String zoneName, String pwd) throws RemoteException {
		super(users, rmiAdresse, zoneName);
		this.password = pwd;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	

}
