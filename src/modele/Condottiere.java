package modele;

import controleur.Interaction;

import java.util.Random;

import static controleur.Interaction.*;

public class Condottiere extends Personnage {

    public Condottiere() {
        super("Condottiere", 8, Caracteristiques.CONDOTTIERE);
    }
    @Override
    public void utiliserPouvoir() {
        System.out.println("Vous utilisez votre pouvoir de destruction ");

        PlateauDeJeu plateau = this.getPlateau();
        Joueur[] joueurs = new Joueur[plateau.getNombreJoueurs()];



        System.out.println(YELLOW + "\nPour information, vous avez " + this.getJoueur().nbPieces() + " pièces d'or dans votre trésor."+RESET);

        // Initialisation du choix du joueur
        int choixJoueur;
        Joueur joueurCible;
        Quartier quartierCible = null;

        // Demande de choix de joueur et quartier à détruire jusqu'à ce que l'utilisateur choisisse 0 ou un quartier
        do {
            // Affichage des joueurs et leur cité
            System.out.println("Voici la liste des joueurs et le contenu de leur cite :");
            for (int i = 0; i < plateau.getNombreJoueurs(); i++) {
                Joueur joueur = plateau.getJoueur(i);
                joueurs[i] = joueur;
                System.out.print("\n"+ (i + 1) + " " + joueur.getNom() + ": ");
                for (int j = 0; j < joueur.nbQuartiersDansCite(); j++) {
                    Quartier quartier = joueur.getCite()[j];
                    System.out.print((j + 1) + " " + quartier.getNom() + "(cout de destruction " + (quartier.getCout()-1) + "), ");
                }
                System.out.println();
            }

            System.out.println("Quel joueur choisissez-vous ? (0 pour ne rien faire)");
            choixJoueur = Interaction.lireUnEntier(0, plateau.getNombreJoueurs() + 1) - 1;

            if (choixJoueur >= 0 && choixJoueur < plateau.getNombreJoueurs()) {
                joueurCible = joueurs[choixJoueur];
                System.out.println("Quel quartier choisissez-vous ? (0 pour ne rien faire)");
                for (int i = 0; i < joueurCible.nbQuartiersDansCite(); i++) {
                    Quartier quartier = joueurCible.getCite()[i];
                    System.out.println((i + 1) + " " + quartier.getNom() + "(coût de destruction " + (quartier.getCout()-1) + ")");
                }

                int choixQuartier = Interaction.lireUnEntier(0, joueurCible.nbQuartiersDansCite() + 1) - 1;

                if (choixQuartier > -1) {
                    quartierCible = joueurCible.getCite()[choixQuartier];
                    int coutDestruction = quartierCible.getCout() - 1;

                    if (!(joueurCible.nbQuartiersDansCite() >= 7)) {
                        if (this.getJoueur().nbPieces() >= coutDestruction) {
                            joueurCible.retirerQuartierDansCite(quartierCible.getNom());
                            this.getJoueur().retirerPieces(coutDestruction);
                            System.out.println("=> On retire " + quartierCible.getNom() + " à " + joueurCible.getNom());
                            System.out.println(YELLOW + "\nPour information, votre trésor est maintenant de " + this.getJoueur().nbPieces() + " pieces" + RESET);
                            break;
                        } else {
                            System.out.println(RED + "Votre trésor n'est pas suffisant pour détruire ce quartier." + RESET + "\nVeuillez reessayer");
                            continue;
                        }
                    } else {
                        System.out.println("Vous ne pouvez pas détruire ce quartier. Le joueur cible a une cité complète.");
                    }
                } else if (choixQuartier == -1) {
                    System.out.println("Annulation de la destruction");
                    break;
                }
            } else if (choixJoueur != -1) {
                System.out.println("Annulation de la destruction");
                break;
            }
        } while (choixJoueur != -1 && (choixJoueur != 0 || quartierCible == null) || this.getJoueur().nbPieces() <= quartierCible.getCout() - 1);
    }

    @Override
    public void utiliserPouvoirAvatar() {
        PlateauDeJeu plateau = this.getPlateau();
        Joueur[] joueurs = new Joueur[plateau.getNombreJoueurs()];

        // Récupération de la liste des joueurs
        for (int i = 0; i < plateau.getNombreJoueurs(); i++) {
            joueurs[i] = plateau.getJoueur(i);
        }

        // Choix aléatoire du joueur à attaquer
        Random random = new Random();
        int choixJoueur = random.nextInt(plateau.getNombreJoueurs() + 1) - 1;

        if (choixJoueur >= 0 && choixJoueur < plateau.getNombreJoueurs()) {
            Joueur joueurCible = joueurs[choixJoueur];

            // Choix aléatoire du quartier à détruire
            int choixQuartier;
            Quartier quartierCible;

            do {
                choixQuartier = random.nextInt(joueurCible.nbQuartiersDansCite());
                quartierCible = joueurCible.getCite()[choixQuartier];
            } while (quartierCible.getCout() - 1 > this.getJoueur().nbPieces());

            int coutDestruction = quartierCible.getCout() - 1;

            if (this.getJoueur().nbQuartiersDansCite() >= 7) {
                if (this.getJoueur().nbPieces() >= coutDestruction) {
                    joueurCible.retirerQuartierDansCite(quartierCible.getNom());
                    this.getJoueur().retirerPieces(coutDestruction);
                    System.out.println("=> On retire " + quartierCible.getNom() + " a " + joueurCible.getNom());
                    System.out.println("Pour information, votre tresor est constitue d'une piece d'or");
                } else {
                    System.out.println("Votre tresor n'est pas suffisant pour detruire ce quartier.");
                }
            } else {
                System.out.println("Vous ne pouvez pas détruire ce quartier. Le joueur cible a une cité complète");
            }

        } else {
            System.out.println("Aucun joueur choisi.");
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
