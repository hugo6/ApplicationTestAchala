package modules.chat;

import java.rmi.RemoteException;

import achala.communication._Correspondance;

public interface _SecureCorrespondance extends _Correspondance {

	/**
	 * Obtient le mot de passe de la zone securise
	 * @return String : mot de passe
	 * @throws RemoteException leve une exception en cas d'echec de communication
	 */
	public String getPassword() throws RemoteException;
	
	/**
	 * Defini le mot de passe de la zone securise
	 * @param password String : mot de passe de la zone
	 * @throws RemoteException leve une exception en cas d'echec de communication
	 */
	public void setPassword(String password) throws RemoteException;
}
