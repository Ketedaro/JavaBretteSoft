package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.Scanner;

public class Client {

	public static void main(String[] zero) {

		Socket socket;
		final String serverIP = "localhost";
		final int serverPORT = 3000;
		
		try {
			// Initialisation du socket
			System.out.println("Connexion à "+serverIP+":"+serverPORT+" ...");
			socket = new Socket(serverIP, serverPORT);
			System.out.println("[+] Connexion établie avec le serveur."+System.getProperty("line.separator"));
			
			// Initialisation du reader du buffer (lecture depuis le socket)
			BufferedReader sin = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			// Initialisation du writer (écriture dans le socket)
			PrintWriter sout = new PrintWriter(socket.getOutputStream(), true);
			
			// Scanner pour la lecture des entrées clavier
			@SuppressWarnings("resource")
			Scanner clavier = new Scanner(System.in);
			
			// Boucle qui reçoit les output du serveur et qui lui envoie les entrées clavier
			while (true) {
				System.out.println(sin.readLine());
				String entréeClavier = clavier.nextLine();
				if(!entréeClavier.equals("")) {
					sout.println(entréeClavier);
				}
				
			}
		} catch (IOException e) {
			System.out.print("[-] Erreur : ");
			e.printStackTrace();
		}
	}
}
