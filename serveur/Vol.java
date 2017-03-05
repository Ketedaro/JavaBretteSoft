package serveur;

public class Vol {
	private int numero;
	private String dateDepart;
	private String destination;
	private int nbrePlaceDispo;
	private double prix;

	public Vol(int numero, String dateDepart, String destination, int nbrePlaceDispo, double prix) {
		this.numero = numero;
		this.dateDepart = dateDepart;
		this.destination = destination;
		this.nbrePlaceDispo = nbrePlaceDispo;
		this.prix = prix;
	}

	public int getNumero() {
		return numero;
	}

	public String getDateDepart() {
		return dateDepart;
	}

	public String getDestination() {
		return destination;
	}

	public synchronized int getNbrePlaceDispo() {
		return nbrePlaceDispo;
	}

	public double getPrix() {
		return prix;
	}
	
	public void enleverPlaces(int nbPlacesAEnlever) {
		this.nbrePlaceDispo = this.nbrePlaceDispo-nbPlacesAEnlever;
	}

	public String toString() {
		return "Vol n°" + this.numero + " : départ le " + this.dateDepart + " à destination de " + this.destination
				+ ". " + this.nbrePlaceDispo + " places restantes. Prix : " + this.prix + " euros.";
	}

}