package application.panels.panelsChat;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;

class MyRenderer extends DefaultListCellRenderer {

	private static final long serialVersionUID = 1L;

	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
			boolean hasFocus) {
		JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, hasFocus);

		try {
			label.setText(value.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return label;
	}
}