package modules.chat.clients;

import achala.communication.server.Server;

public class LanceServer {

	public static void main(String[] args) {
		Server.startServer("src/modules/chat/clients/policy");
	}

}
