package serveur;

public class Reservation {
	private String destination;
	private String dateDepart;
	private int nbrePlaceReserv;

	public Reservation(String destination, String dateDepart, int nbrePlaceReserv) {
		this.destination = destination;
		this.dateDepart = dateDepart;
		this.nbrePlaceReserv = nbrePlaceReserv;
	}

	public String getDestination() {
		return destination;
	}

	public String getDateDepart() {
		return dateDepart;
	}

	public int getNbrePlaceReserv() {
		return nbrePlaceReserv;
	}

	public String toString() {
		return "Destination : " + this.destination + " - Date de d�part : " + this.dateDepart + " - Nombre de places : "
				+ this.nbrePlaceReserv + ". Veuillez appuyez sur entr�e pour continuer et valider votre r�servation";
	}


}
