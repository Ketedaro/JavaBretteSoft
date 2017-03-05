package serveur;

import java.io.IOException;
import java.net.ServerSocket;

class Service implements Runnable {
	private Thread thread;
	private boolean temp = false;

	private ServerSocket socketserver;

	public Service(ServerSocket s) {
		this.socketserver = s;
		
		this.thread = new Thread(this);
		this.thread.start();
	}

	public void lancer() {
		(new Thread(this)).start();

	}

	public void finDeService() {
		this.thread.interrupt();
		try {
			this.socketserver.close();
		} catch (IOException e) {
			System.err.println("Fin du formulaire");
		}		
	}

	public void run() {

		try {
			while (true) {

				Formulaire formulaire = new Formulaire(socketserver.accept());

				formulaire.lancer();
				if (temp) {
					return;
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
