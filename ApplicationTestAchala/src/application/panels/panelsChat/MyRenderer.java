package application.panels.panelsChat;

import java.awt.Component;
import java.rmi.RemoteException;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;

import achala.communication.utilisateur._Utilisateur;

class MyRenderer extends DefaultListCellRenderer {

	private static final long serialVersionUID = 1L;

	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean hasFocus) {
		JLabel label = (JLabel)super.getListCellRendererComponent(list, value, index, isSelected, hasFocus);
		_Utilisateur utilisateur = (_Utilisateur)value;
		try {
			label.setText (utilisateur.toStringRemote());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return label;
	}
}