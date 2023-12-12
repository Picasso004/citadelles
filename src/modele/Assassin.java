package modele;

import controleur.Interaction;

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
        PlateauDeJeu plateau = this.getPlateau();

        for (int i = 0; i < plateau.getNombrePersonnages(); i++) {
            Personnage personnage = plateau.getPersonnage(i);
            if(personnage.getJoueur() != null){
                System.out.println((i + 1) + "- " + personnage.getNom());
            }
        }

        int choix;
        do {
            System.out.print("Votre choix : ");
            choix = Interaction.lireUnEntier(1,plateau.getNombrePersonnages()+1);
            choix--; // Convertir le choix de l'utilisateur en indice

            if (plateau.getPersonnage(choix) == this) {
                System.out.println("\nVous ne pouvez pas vous assassiner.");
            } else {
                plateau.getPersonnage(choix).setAssassine();
                System.out.println("Le personnage " + plateau.getPersonnage(choix).getNom() + " a été assassiné.");
                break;
            }
        } while (true);
    }

    @Override
    public void utiliserPouvoirAvatar() {
        PlateauDeJeu plateau = this.getPlateau();
        if (!getAssassine()) {
            System.out.println("Le pouvoir de l'Assassin est activé!");
            // Simulate a random choice for the avatar power
            Random random =new Random();
            int choice;
            do {
                choice = random.nextInt(plateau.getNombrePersonnages()); // Assuming there are 3 choices, modify as needed
            }while (plateau.getPersonnage(choice) == this || plateau.getPersonnage(choice).getJoueur()==null);
            plateau.getPersonnage(choice).setAssassine();
            System.out.println("Le personnage " + plateau.getPersonnage(choice).getNom() + " a été assassiné.");
        } else {
            System.out.println("L'Assassin ne peut pas utiliser son pouvoir Avatar car il est assassiné.");
        }
    }

}

