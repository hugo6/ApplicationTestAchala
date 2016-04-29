package application.panels.panelsChat;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;

import modules.chat.Chat;

class MyRenderer extends DefaultListCellRenderer {

	private static final long serialVersionUID = 1L;

	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
			boolean hasFocus) {
		JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, hasFocus);

		try {
			Chat c = (Chat) value;
			String name = c.getShared().getZoneName();
			label.setText(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return label;
	}
}