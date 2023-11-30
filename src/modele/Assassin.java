package modele;

import java.util.Scanner;

public class Assassin extends Personnage {
    // Constructeur
    public Assassin() {
        super("Assassin", 1, Caracteristiques.ASSASSIN);
    }

    @Override
    public void utiliserPouvoir() {
        System.out.println("Quel personnage voulez-vous assassiner ?");

        Scanner scanner = new Scanner(System.in);
        PlateauDeJeu plateau = this.getPlateau();

        for (int i = 0; i < plateau.getNombrePersonnages(); i++) {
            Personnage personnage = plateau.getPersonnage(i);
            System.out.println((i + 1) + " " + personnage.getNom());
        }

        int choix;
        do {
            System.out.print("Votre choix : ");
            choix = scanner.nextInt();
            choix--; // Convertir le choix de l'utilisateur en indice
            if (choix >= 0 && choix < plateau.getNombrePersonnages()) {
                if (plateau.getPersonnage(choix) == this) {
                    System.out.println("Vous ne pouvez pas vous assassiner.");
                } else {
                    plateau.getPersonnage(choix).setAssassine();
                    System.out.println("Le personnage " + plateau.getPersonnage(choix).getNom() + " a été assassiné.");
                    break;
                }
            } else {
                System.out.println("Choix invalide. Réessayez.");
            }
        } while (true);

        scanner.close();
    }
}
