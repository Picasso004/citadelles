package modele;

import java.util.Random;

public class Marchande extends Personnage{
    //CONSTRUCTEUR
    public Marchande (){
        super("Marchande", 6, Caracteristiques.MARCHANDE);
    }

    @Override
    public void percevoirRessourcesSpecifiques() {
        int nbQuartiersCommercants =0;
        if (getJoueur() != null){
            Quartier[] cite = getJoueur().getCite();
            for (Quartier quartier : cite){
                if(quartier != null && quartier.getType().equals(Quartier.TYPE_QUARTIERS[3])){
                    nbQuartiersCommercants ++;
                }
            }
        }
        if(getJoueur() != null && !getAssassine()){
            getJoueur().ajouterPieces(nbQuartiersCommercants);
            System.out.println(getNom() + " a reçu " + nbQuartiersCommercants +
                    " pièce(s) d'or supplémentaire(s) pour ses quartiers commerçants.");
        }
    }

    @Override
    public void utiliserPouvoir() {
        if(getJoueur() != null && !getAssassine()){
            getJoueur().ajouterPieces(1);
            System.out.println(getNom() + " a utilisé son pouvoir et a reçu 1 pièce d'or supplémentaire.");
        }
    }

    @Override
    public void utiliserPouvoirAvatar() {
        if (!getAssassine()) {
            System.out.println("Le pouvoir de la Marchande Avatar est activé!");

            // Simulate a random choice for the avatar power
            Random random=new Random();
            int choice = random.nextInt(3);  // Assuming there are 3 choices, modify as needed

            switch (choice) {
                case 0:
                    // Perform action for choice 0
                    System.out.println("Action spéciale pour le choix 0 de la Marchande Avatar.");
                    break;
                case 1:
                    // Perform action for choice 1
                    System.out.println("Action spéciale pour le choix 1 de la Marchande Avatar.");
                    break;
                case 2:
                    // Perform action for choice 2
                    System.out.println("Action spéciale pour le choix 2 de la Marchande Avatar.");
                    break;
                default:
                    // Handle unexpected choice
                    System.out.println("Choix invalide pour la Marchande Avatar.");
            }
        } else {
            System.out.println("La Marchande ne peut pas utiliser son pouvoir Avatar car elle est assassinée.");
        }

    }

}
