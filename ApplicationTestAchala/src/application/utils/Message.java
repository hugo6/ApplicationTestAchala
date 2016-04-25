package application.utils;

public class Message {

	private String pseudo;
	private String date;
	private String contenu;
	
	public Message(String pseudo, String date, String contenu){
		this.pseudo=pseudo;
		this.date=date;
		this.contenu=contenu;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
	
}
