package modele;

import controleur.Interaction;

import java.util.Random;

public class Voleur extends Personnage {

    public Voleur() {
        super("Voleur", 2, Caracteristiques.VOLEUR);
    }

    @Override
    public void utiliserPouvoir() {
        System.out.println("Quel personnage voulez-vous voler ?");

        PlateauDeJeu plateau = this.getPlateau();

        for (int i = 0; i < plateau.getNombrePersonnages(); i++) {
            Personnage personnage = plateau.getPersonnage(i);
            System.out.println((i + 1) + " " + personnage.getNom());
        }

        int choix;
        do {
            System.out.print("Votre choix : ");
            choix = Interaction.lireUnEntier() - 1; // Convertir le choix de l'utilisateur en indice de tableau

            if (choix >= 0 && choix < plateau.getNombrePersonnages()) {
                if (plateau.getPersonnage(choix) == this || plateau.getPersonnage(choix).getRang() == 1) {
                    System.out.println("Vous ne pouvez pas vous choisir ou choisir un personnage de rang 1.");
                } else {
                    Joueur joueurCible = plateau.getPersonnage(choix).getJoueur();
                    if (joueurCible != null) {
                        int piecesVolees = joueurCible.nbPieces();
                        joueurCible.retirerPieces(piecesVolees);
                        this.getJoueur().ajouterPieces(piecesVolees); // Le voleur reçoit toutes les pièces volées
                        plateau.getPersonnage(choix).setVole();
                        System.out.println("Le personnage " + plateau.getPersonnage(choix).getNom() + " a été complètement dépouillé.");
                    } else {
                        System.out.println("Ce personnage n'est pas attribué à un joueur, rien n'a été volé.");
                    }
                    break;
                }
            } else {
                System.out.println("Choix invalide. Réessayez.");
            }
        } while (true);
    }

    @Override
    public void utiliserPouvoirAvatar() {
        PlateauDeJeu plateau = this.getPlateau();
        if (!getAssassine()) {
            System.out.println("Le pouvoir du Voleur est activé !");

            // Simulate a random choice for the avatar power
            Random random = new Random();
            int choice;
            do {
                choice = random.nextInt(plateau.getNombrePersonnages());
            }while (plateau.getPersonnage(choice) == this  || plateau.getPersonnage(choice).getJoueur() == null || plateau.getPersonnage(choice).getRang()==1 || plateau.getPersonnage(choice).getAssassine());
            Joueur joueurCible = plateau.getPersonnage(choice).getJoueur();
            if (joueurCible != null){
                int piecesVolees = joueurCible.nbPieces();
                joueurCible.retirerPieces(piecesVolees);
                this.getJoueur().ajouterPieces(piecesVolees);
                plateau.getPersonnage(choice).setVole();
                System.out.println("Le personnage " + plateau.getPersonnage(choice).getNom() + " a été dépouillé.");
            }

        } else {
            System.out.println("Le Voleur ne peut pas utiliser son pouvoir Avatar car il est assassiné.");
        }
    }
}
