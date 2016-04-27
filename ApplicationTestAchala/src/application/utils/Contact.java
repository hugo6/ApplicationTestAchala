package application.utils;

public class Contact {
	private String pseudo;

	public Contact(String pseudo) {
		super();
		this.pseudo = pseudo;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	
	@Override
	public String toString(){
		return this.pseudo;
		
	}

}
