package application;

import modele.*;

public class Configuration {
    public static Pioche nouvellePioche() {
        Pioche pioche = new Pioche();

        // Ajout des 54 cartes Quartier de types religieux, militaires, nobles et commerçants
        for (int i = 1; i <= 54; i++) {
            String typeQuartier = Quartier.TYPE_QUARTIERS[(i - 1) / 13];
            Quartier quartier = new Quartier("Quartier " + i, typeQuartier, i % 13 + 1);
            pioche.ajouter(quartier);
        }

        // Mélange les cartes de la pioche
        pioche.melanger();

        return pioche;
    }

    public static PlateauDeJeu configurationDeBase(Pioche p) {
        // Créer et initialiser les personnages avec leur nom, rang et caractéristiques
        Personnage assassin = new Assassin();
        Personnage voleur = new Voleur();
        Personnage magicienne = new Magicienne();
        Personnage roi = new Roi();
        Personnage eveque = new Eveque();
        Personnage marchande = new Marchande();
        Personnage architecte = new Architecte();
        Personnage condottiere = new Condottiere();

        // Créer quatre joueurs avec leur nom
        Joueur joueur1 = new Joueur("Joueur 1");
        Joueur joueur2 = new Joueur("Joueur 2");
        Joueur joueur3 = new Joueur("Joueur 3");
        Joueur joueur4 = new Joueur("Joueur 4");


        // Créer un nouveau plateau de jeu
        PlateauDeJeu plateau = new PlateauDeJeu();

        // Ajouter les personnages et les joueurs au plateau de jeu
        plateau.ajouterPersonnage(assassin);
        plateau.ajouterPersonnage(voleur);
        plateau.ajouterPersonnage(magicienne);
        plateau.ajouterPersonnage(roi);
        plateau.ajouterPersonnage(eveque);
        plateau.ajouterPersonnage(marchande);
        plateau.ajouterPersonnage(architecte);
        plateau.ajouterPersonnage(condottiere);

        plateau.ajouterJoueur(joueur1);
        plateau.ajouterJoueur(joueur2);
        plateau.ajouterJoueur(joueur3);
        plateau.ajouterJoueur(joueur4);

        plateau.setPioche(p);

        return plateau;
    }
}
