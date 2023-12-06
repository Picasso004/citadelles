package modele;

import controleur.Interaction;

import java.util.Random;

public class Condottiere extends Personnage {

    public Condottiere() {
        super("Condottiere", 8, Caracteristiques.CONDOTTIERE);
    }

    @Override
    public void utiliserPouvoir() {
        System.out.println("Voulez-vous utiliser votre pouvoir de destruction ? (o/n)");
        boolean choix = Interaction.lireOuiOuNon();

        if (choix) {
            PlateauDeJeu plateau = this.getPlateau();
            Joueur[] joueurs = new Joueur[plateau.getNombreJoueurs()];

            // Affichage des joueurs et leur cité
            System.out.println("Voici la liste des joueurs et le contenu de leur cite :");
            for (int i = 0; i < plateau.getNombreJoueurs(); i++) {
                Joueur joueur = plateau.getJoueur(i);
                joueurs[i] = joueur;
                System.out.print((i + 1) + " " + joueur.getNom() + ": ");
                for (int j = 0; j < joueur.nbQuartiersDansCite(); j++) {
                    Quartier quartier = joueur.getCite()[j];
                    System.out.print((j + 1) + " " + quartier.getNom() + "(cout " + quartier.getCout() + "), ");
                }
                System.out.println();
            }

            System.out.println("Pour information, vous avez " + this.getJoueur().nbPieces() + " pieces d'or dans votre tresor.");

            // Choix du joueur à attaquer
            System.out.println("Quel joueur choisissez-vous ? (0 pour ne rien faire)");
            int choixJoueur = Interaction.lireUnEntier(0, plateau.getNombreJoueurs() + 1) - 1;

            if (choixJoueur >= 0 && choixJoueur < plateau.getNombreJoueurs()) {
                Joueur joueurCible = joueurs[choixJoueur];
                System.out.println("Quel quartier choisissez-vous ?");
                for (int i = 0; i < joueurCible.nbQuartiersDansCite(); i++) {
                    Quartier quartier = joueurCible.getCite()[i];
                    System.out.println((i + 1) + " " + quartier.getNom() + "(cout " + quartier.getCout() + ")");
                }

                int choixQuartier = Interaction.lireUnEntier(1, joueurCible.nbQuartiersDansCite() + 1) - 1;
                Quartier quartierCible = joueurCible.getCite()[choixQuartier];

                int coutDestruction = quartierCible.getCout() - 1;

                if (this.getJoueur().nbPieces() >= coutDestruction) {
                    joueurCible.retirerQuartierDansCite(quartierCible.getNom());
                    this.getJoueur().retirerPieces(coutDestruction);
                    System.out.println("=> On retire " + quartierCible.getNom() + " a " + joueurCible.getNom());
                    System.out.println("Pour information, votre tresor est constitue d'une piece d'or");
                } else {
                    System.out.println("Votre tresor n'est pas suffisant pour detruire ce quartier.");
                }
            } else {
                System.out.println("Aucun joueur choisi.");
            }
        } else {
            System.out.println("Vous avez decide de ne pas utiliser votre pouvoir.");
        }
    }

    @Override
    public void utiliserPouvoirAvatar() {

        if (!getAssassine()) {
            System.out.println("Le pouvoir de Condottiere Avatar est activé!");

            // Simulate a random choice for the avatar power
            Random random=new Random();
            int choice = random.nextInt(3);  // Assuming there are 3 choices, modify as needed

            switch (choice) {
                case 0:
                    // Perform action for choice 0
                    System.out.println("Action spéciale pour le choix 0 de condottiere  Avatar.");
                    break;
                case 1:
                    // Perform action for choice 1
                    System.out.println("Action spéciale pour le choix 1 de condottiere Avatar.");
                    break;
                case 2:
                    // Perform action for choice 2
                    System.out.println("Action spéciale pour le choix 2 de condottiere  Avatar.");
                    break;
                default:
                    // Handle unexpected choice
                    System.out.println("Choix invalide pour condottiere Avatar.");
            }
        } else {
            System.out.println("Condottiere ne peut pas utiliser son pouvoir Avatar car il est assassiné.");
        }
    }

    @Override
    public void percevoirRessourcesSpecifiques() {
        int piecesExtra = 0;
        Quartier[] cite = this.getJoueur().getCite();

        for (Quartier quartier : cite) {
            if (quartier != null && quartier.getType().equals(Quartier.TYPE_QUARTIERS[1])) { // Vérifier si c'est un quartier militaire
                piecesExtra++; // Pour chaque quartier militaire, ajouter 1 pièce
            }
        }

        this.getJoueur().ajouterPieces(piecesExtra);
        System.out.println("Le Condottiere a recu " + piecesExtra + " piece(s) d'or pour ses quartiers militaires.");
    }
}
