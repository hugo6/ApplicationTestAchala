package application.panels.panelsChat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;

import achala.communication.Message;
import achala.communication._RemotableObject;

public class PanelMessage extends JPanel {

	private static final long serialVersionUID = -9176361023262059756L;
	/**
	 * Representation graphique d'un Message
	 */
	public PanelMessage(Message m) {
		setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		setLayout(new BorderLayout(0, 0));

		String dateheure = new SimpleDateFormat("dd MMMM yyyy hh:mm:ss", Locale.FRANCE).format(new Date());
		JLabel lblDeNom = new JLabel("  le " + dateheure + " " + m.getSender() + " a ecrit : ");
		add(lblDeNom, BorderLayout.NORTH);

		JTextPane textPaneContenu = new JTextPane();
		textPaneContenu.setEditable(false);
		textPaneContenu.setText(m.getObject().toString());
		
		setPreferredSize(new Dimension(500,75));
		add(textPaneContenu, BorderLayout.CENTER);
	}
	
	public PanelMessage(_RemotableObject m) {
		try
		{
			//setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
			setLayout(new BorderLayout(0, 0));
	
			String dateheure = new SimpleDateFormat("dd MMMM yyyy hh:mm:ss", Locale.FRANCE).format(m.getDate());
			JLabel lblDeNom = new JLabel("  le " +dateheure+ " " +m.getSender().toStringRemote() + " a ecrit : ");
			add(lblDeNom, BorderLayout.NORTH);
	
			JTextPane textPaneContenu = new JTextPane();
			textPaneContenu.setEditable(false);
			textPaneContenu.setText(m.getObject().toString());
	
			textPaneContenu.setSize(500, Integer.MAX_VALUE);
			//setPreferredSize(new Dimension(500,75));
			
			add(textPaneContenu, BorderLayout.CENTER);
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	


}
