package modele;

import java.util.Random;

public class Roi extends Personnage {
    // Constructeur
    public Roi() {
        super("Roi", 4, Caracteristiques.ROI);
    }

    // Méthode utiliserPouvoir
    @Override
    public void utiliserPouvoir() {
        System.out.println("Je prends la couronne");
        Joueur[] joueurs = this.getPlateau().getListeJoueurs();

        for (Joueur joueur : joueurs){
            if(joueur != null)
                joueur.setPossedeCouronne(false);
        }
        if (getJoueur() != null) {
            getJoueur().setPossedeCouronne(true);
        }
    }

    @Override
    public void utiliserPouvoirAvatar() {

            if (!getAssassine()) {
                System.out.println("Le pouvoir du Roi est activé!");
                System.out.println("Le joueur " + getJoueur().getNom() + " a pris la couronne.");
                Joueur[] joueurs = this.getPlateau().getListeJoueurs();

                for (Joueur joueur : joueurs){
                    if (joueur != null){
                        joueur.setPossedeCouronne(false);
                    }
                }
                if (getJoueur() != null){
                    getJoueur().setPossedeCouronne(true);
                }
            }
            else {
                System.out.println("Le Roi ne peut pas utiliser son pouvoir Avatar car il est assassiné.");
            }
        }



    // Surcharge de percevoirRessourcesSpecifiques
    @Override
    public void percevoirRessourcesSpecifiques() {
        int nbQuartiersNobles = compterQuartiersNobles();
        if (getJoueur() != null) {
            getJoueur().ajouterPieces(nbQuartiersNobles);
            System.out.println("J'ai ajouté " + nbQuartiersNobles + " pièces à mon trésor.");
        }
    }

    // Méthode privée pour compter les quartiers nobles dans la cité du joueur associé
    private int compterQuartiersNobles() {
        int nbQuartiersNobles = 0;
        if (getJoueur() != null) {
            for (Quartier quartier : getJoueur().getCite()) {
                if (quartier != null && quartier.getType().equals("NOBLE")) {
                    nbQuartiersNobles++;
                }
            }
        }
        return nbQuartiersNobles;
    }
}