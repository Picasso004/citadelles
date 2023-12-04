package modele;

import java.util.Random;
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

    @Override
    public void utiliserPouvoirAvatar() {
        if (!getAssassine()) {
            System.out.println("Le pouvoir de l'Assassin Avatar est activé!");

            // Simulate a random choice for the avatar power
            Random random =new Random();
            int choice = random.nextInt(3);  // Assuming there are 3 choices, modify as needed

            switch (choice) {
                case 0:
                    // Perform action for choice 0
                    System.out.println("Action spéciale pour le choix 0 de l'Assassin Avatar.");
                    break;
                case 1:
                    // Perform action for choice 1
                    System.out.println("Action spéciale pour le choix 1 de l'Assassin Avatar.");
                    break;
                case 2:
                    // Perform action for choice 2
                    System.out.println("Action spéciale pour le choix 2 de l'Assassin Avatar.");
                    break;
                default:
                    // Handle unexpected choice
                    System.out.println("Choix invalide pour l'Assassin Avatar.");
            }
        } else {
            System.out.println("L'Assassin ne peut pas utiliser son pouvoir Avatar car il est assassiné.");
        }
    }

}

