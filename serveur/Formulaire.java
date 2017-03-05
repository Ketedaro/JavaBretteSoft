package serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Formulaire implements Runnable {

	private Socket socket;

	public Formulaire(Socket s) {
		// Injection du socket dans le formulaire
		socket = s;
	}

	public void run() {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			
			// Interactions avec l'utilisateur (qui crée des réservations)
			creerReservation(in, out);
			
		} catch (IOException e) {
			System.err.println("Fin du formulaire");
		}

	}

	public void lancer() {
		// Lance le thread
		(new Thread(this)).start();
	}

	private void creerReservation(BufferedReader in, PrintWriter out) {
		try {
			// Initialisation de la compagnie
			Compagnie.initCompagnie();
			
			out.println("Tous Au Soleil ! Réservez vos voyages !  <Appuyez sur Entrée>");
			
			// Boucle pour faire plusieurs réservations (si souhaité)
			while(true) {
				// Variables qui stockeront les entrées utilisateur
				String dest, dateD, continuerRep;
				int nbPlaces;
				
				// Interactions (entrer 0 arrete le service)
				out.println("Entrez votre destination :");
				dest = in.readLine();
				if (!dest.equals("0")) {
					out.println("Entrez votre date de départ (Format jj/mm/aaaa):");
					dateD = in.readLine();
					if (!dateD.equals("0")) {
						out.println("Entrez votre nombre de place(s) à réserver :");
						nbPlaces = Integer.parseInt(in.readLine());
						if (nbPlaces != 0) {
							Reservation r = new Reservation(dest, dateD, nbPlaces);
							proposerVol(r, in, out);
						}
					}
				}
				
				// Demande pour faire d'autres réservations (!= non ferme le service)
				out.println("Voulez vous continuer vos réservations ? (oui/non) : ");
				continuerRep = in.readLine();
				if(!continuerRep.equals("oui")) {
					throw new IOException();
				}
			}
		} catch (java.lang.NumberFormatException e) {
			System.err.println("Entrée invalide. Fin du service.");
		} catch (IOException e) {
			System.err.println("Fin du service");
		}
	}

	private void proposerVol(Reservation r, BufferedReader in, PrintWriter out) {
		try {
			// Tri et affichage des vols correspondants à la description entrée
			out.println("Liste des vols disponibles :   <Appuyez sur Entrée>");
			for (int i = 0; i < Compagnie.getNbVols(); ++i) {
				if (Compagnie.getVol(i).getDestination().equals(r.getDestination())
						&& Compagnie.getVol(i).getDateDepart().equals(r.getDateDepart())
						&& Compagnie.getVol(i).getNbrePlaceDispo() >= r.getNbrePlaceReserv()) {
					out.println("- "+Compagnie.getVol(i).toString() + "   <Appuyez sur Entrée>   ");
				}
			}
			
			// Choix du vol par l'utilisateur et traitement du nombre de places disponibles
			out.println("Choisissez votre numéro de vol (si aucun, entrez 0) : ");
			int numVol = Integer.parseInt(in.readLine());
			if(numVol != 0) {
				for (int i = 0; i < Compagnie.getNbVols(); ++i) {
					if (Compagnie.getVol(i).getNumero() == numVol) {
						Compagnie.getVol(i).enleverPlaces(r.getNbrePlaceReserv());
						out.println("Vous avez choisi le vol n°" + Compagnie.getVol(i).getNumero() + ". Départ le "
								+ Compagnie.getVol(i).getDateDepart() + " vers " + Compagnie.getVol(i).getDestination()
								+ ". " + Compagnie.getVol(i).getNbrePlaceDispo() + " places restantes.");
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
