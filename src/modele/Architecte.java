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

            // Simulate a random choice for the avatar power
            Random random=new Random();
            int choice = random.nextInt(3);  // Assuming there are 3 choices, modify as needed

            switch (choice) {
                case 0:
                    // Perform action for choice 0
                    System.out.println("Action spéciale pour le choix 0 de l'Architecte Avatar.");
                    break;
                case 1:
                    // Perform action for choice 1
                    System.out.println("Action spéciale pour le choix 1 de l'Architecte  Avatar.");
                    break;
                case 2:
                    // Perform action for choice 2
                    System.out.println("Action spéciale pour le choix 2 de l'Architecte Avatar.");
                    break;
                default:
                    // Handle unexpected choice
                    System.out.println("Choix invalide pour l'Architecte Avatar.");
            }
        } else {
            System.out.println("L'Architecte ne peut pas utiliser son pouvoir Avatar car il est assassiné.");
        }
    }
}

