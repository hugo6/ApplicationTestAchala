package application.frames;

import javax.swing.JFrame;

import modules.publication.metier.Article;

public class FrameAjouterChatroom extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JFrame frameAjouter;
	
	public FrameAjouterChatroom() {
		frameAjouter = new JFrame(" Ajouter un Chat Room : ");
		frameAjouter.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		frameAjouter.getContentPane();
		frameAjouter.setVisible(true);
	}
	
	
}
