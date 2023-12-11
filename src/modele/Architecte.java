package modele;

import java.util.List;
import java.util.Random;

public class Architecte extends Personnage{
    public Architecte(){
        super("Architecte",7,Caracteristiques.ARCHITECTE);
    }

    public void utiliserPouvoir() {
        if (getJoueur() != null && !getAssassine()) {
            // L'architecte pioche deux cartes quartier supplémentaires
            for (int i = 0; i < 2; i++) {
                Quartier cartePiochee = getPlateau().getPioche().piocher();
                if (cartePiochee != null) {
                    getJoueur().ajouterQuartierDansMain(cartePiochee);
                }
            }
            System.out.println(getNom() + " a utilisé son pouvoir et a pioché deux cartes quartier supplémentaires.");
        }
    }

    @Override
    public void utiliserPouvoirAvatar() {
        if (!getAssassine()) {
            System.out.println("Le pouvoir de l'Architecte Avatar est activé!");

            // Construire trois quartiers dans la cité
            for (int i = 0; i < 3; i++) {
                if (!getJoueur().getMain().isEmpty()) {
                    Quartier quartierChoisi = getJoueur().retirerQuartierDansMain();
                    getJoueur().ajouterQuartierDansCite(quartierChoisi);
                    System.out.println("Quartier ajouté à la cité: " + quartierChoisi.getNom());
                } else {
                    System.out.println("La main du joueur est vide. Impossible de construire plus de quartiers.");
                    break;
                }
            }

            // Afficher la cité avec tous les quartiers existants
            Quartier[] cite = getJoueur().getCite();
            System.out.println("Cité de " + getJoueur().getNom() + " :");
            for (Quartier quartier : cite) {
                if (quartier != null) {
                    System.out.println("- " + quartier.getNom());
                }
            }
        } else {
            System.out.println("L'Architecte ne peut pas utiliser son pouvoir Avatar car il est assassiné.");
        }
    }
}
