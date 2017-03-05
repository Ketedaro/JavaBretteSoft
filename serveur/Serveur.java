package serveur;

import java.io.IOException;
import java.net.*;
import java.util.Timer;

public class Serveur {

	public static void main(String[] zero) {
		final int port = 3000;
		ServerSocket socket;
		try {
			
			socket = new ServerSocket(port);
			Service service = new Service(socket);
			//new Timer().schedule(new FinService(service), 3000);
			service.lancer();
			System.out.println("[+] Serveur lancé sur le port "+port);

		} catch (java.net.BindException e) {
			System.out.println("[-] Erreur : serveur déjà lancé.");
		} catch (IOException e) {
			System.out.print("[-] Erreur : ");
			e.printStackTrace();
		}
	}
}
