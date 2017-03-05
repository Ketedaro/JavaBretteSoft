package serveur;

import java.util.ArrayList;

public class Compagnie {
	// Liste de de vols
	private static ArrayList<Vol> listeVols;

	// Méthode d'initialisation de la compagnie. Génère les vols.
	public static void initCompagnie() {
		listeVols = new ArrayList<Vol>();
		listeVols.add(new Vol(6400, "20/04/2019", "Japon", 1, 500.0));
		listeVols.add(new Vol(6500, "24/04/2017", "Nice", 50, 50.0));
		listeVols.add(new Vol(6555, "24/04/2017", "Nice", 100, 80.0));
		listeVols.add(new Vol(6600, "13/02/2017", "Paris", 100, 100.0));
		listeVols.add(new Vol(6700, "15/01/2017", "Lyon", 150, 150.0));
	}
	
	// Méthode qui enlève un certain nombre de places disponibles pour un vol.
	public static void enleverPlacesVol(int numeroVol, int nbPlaces) {
		for(int i = 0; i <listeVols.size(); ++i) {
			if(listeVols.get(i).getNumero() == numeroVol) {
				listeVols.get(i).enleverPlaces(nbPlaces);
			}
		}
	}
	
	public static Vol getVol(int i) {
		return listeVols.get(i);
	}
	
	public static int getNbVols() {
		return listeVols.size();
	}
	

}
