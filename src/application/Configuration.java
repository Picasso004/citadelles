package application;

import modele.*;

public class Configuration {
    public static Pioche nouvellePioche() {
        Pioche pioche = new Pioche();

        for (int i = 0; i < 2; i++){
            // Réligieux
            Quartier quartier1 = new Quartier("Cathedrale", Quartier.TYPE_QUARTIERS[0], 5);
            pioche.ajouter(quartier1);

            // Militaires
            Quartier quartier2 = new Quartier("Forteresse", Quartier.TYPE_QUARTIERS[1], 5);
            pioche.ajouter(quartier2);

            // Commerçants
            Quartier quartier3 = new Quartier("Hotel de ville", Quartier.TYPE_QUARTIERS[3], 5);
            pioche.ajouter(quartier3);

        }

        for (int i = 0; i < 3; i++){
            // Réligieux
            Quartier quartier1 = new Quartier("Temple", Quartier.TYPE_QUARTIERS[0], 1);
            pioche.ajouter(quartier1);

            Quartier quartier2 = new Quartier("Eglise", Quartier.TYPE_QUARTIERS[0], 2);
            pioche.ajouter(quartier2);

            Quartier quartier3 = new Quartier("Monastere", Quartier.TYPE_QUARTIERS[0], 3);
            pioche.ajouter(quartier3);

            // Militaires
            Quartier quartier4 = new Quartier("Tour de guet", Quartier.TYPE_QUARTIERS[1], 1);
            pioche.ajouter(quartier4);

            Quartier quartier5 = new Quartier("Prison", Quartier.TYPE_QUARTIERS[1], 2);
            pioche.ajouter(quartier5);

            Quartier quartier6 = new Quartier("Caserne", Quartier.TYPE_QUARTIERS[1], 3);
            pioche.ajouter(quartier6);

            // Nobles
            Quartier quartier7 = new Quartier("Palais", Quartier.TYPE_QUARTIERS[2], 5);
            pioche.ajouter(quartier7);

            // Commerçants
            Quartier quartier8 = new Quartier("Echoppe", Quartier.TYPE_QUARTIERS[3], 1);
            pioche.ajouter(quartier8);

            Quartier quartier9 = new Quartier("Comptoir", Quartier.TYPE_QUARTIERS[3], 3);
            pioche.ajouter(quartier9);

            Quartier quartier10 = new Quartier("Port", Quartier.TYPE_QUARTIERS[3], 4);
            pioche.ajouter(quartier10);
        }

        for (int i = 0; i < 4; i++){
            // Nobles
            Quartier quartier1 = new Quartier("Chateau", Quartier.TYPE_QUARTIERS[2], 4);
            pioche.ajouter(quartier1);

            // Commerçants
            Quartier quartier2 = new Quartier("Marche", Quartier.TYPE_QUARTIERS[3], 2);
            pioche.ajouter(quartier2);
        }

        for (int i = 0; i < 5; i++){
            // Nobles
            Quartier quartier1 = new Quartier("Manoir", Quartier.TYPE_QUARTIERS[2], 3);
            pioche.ajouter(quartier1);

            // Commerçants
            Quartier quartier2 = new Quartier("Taverne", Quartier.TYPE_QUARTIERS[3], 1);
            pioche.ajouter(quartier2);
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


        // Ajouter les 14 quartiers Merveille à la pioche passée en paramètre
        Quartier bibliotheque = new Quartier("Bibliothèque", Quartier.TYPE_QUARTIERS[4], 6, "Si vous choisissez de piocher des cartes au d´ebut du tour, concervez-les toutes.");
        Quartier forge = new Quartier("Forge", Quartier.TYPE_QUARTIERS[4], 5, "Une fois par tour, vous pouvez payez 2 pi`eces d’or pour piocher 3 cartes.\n");
        Quartier carriere = new Quartier("Carrière", Quartier.TYPE_QUARTIERS[4], 5, "Vous pouvez bˆatir des quartiers identiques `a d’autres quartiers de votre cit´e. Le propriétaire\n" +
                "de la carrière peut batir autant de quartiers identiques qu’il le souhaite, mais ne peut pas utiliser le pouvoir de l’Echevin, du Diplomate ou du Capitaine pour acqu´erir des quartiers identiques.\n");
        Quartier laboratoire = new Quartier("Laboratoire", Quartier.TYPE_QUARTIERS[4], 5, "Une fois par tour, vous pouvez d´efausser 1 carte pour recevoir 2 pi`eces d’or.");
        Quartier courDesMiracles = new Quartier("Cour des Miracles", Quartier.TYPE_QUARTIERS[4], 2, "Pour le calcul du score final, la Cour des Miracles est consid´er´ee comme un quartier de type\n" +
                "(couleur) de votre choix. Dans la cas o`u le propri´etaire la consid`ere comme un quartier noble,\n" +
                "militaire, marchant ou religieux, la Cour des Miracles ne peut plus ˆetre consid´er´ee comme\n" +
                "une merveille.\n");
        Quartier manufacture = new Quartier("Manufacture", Quartier.TYPE_QUARTIERS[4], 5, "Payez 1 pi`ece d’or de moins lorsque vous bˆatissez une autre merveille.");
        Quartier donjon = new Quartier("Donjon", Quartier.TYPE_QUARTIERS[4], 3, "Le Donjon ne peut ˆetre affect´e par les pouvoirs des personnages de rang 8.");
        Quartier salleDesCartes = new Quartier("Salles des Cartes", Quartier.TYPE_QUARTIERS[4], 5, "A la fin de la partie, marquez 1 point suppl´ementaire par carte dans votre main.");
        Quartier dracoport = new Quartier("Dracoport", Quartier.TYPE_QUARTIERS[4], 6, "Marquez 2 points suppl´ementaires `a la fin de la partie.\n");
        Quartier statueEquestre = new Quartier("Statue Equestre", Quartier.TYPE_QUARTIERS[4], 3, "Si vous d´etenez le Couronne `a la fin de la partie, marquez 5 points suppl´ementaires.");
        Quartier ecoleDeMagie = new Quartier("Ecole de Magie", Quartier.TYPE_QUARTIERS[4], 6, "Pour la perception des revenus des personnages, l’Ecole de Magie est consid´er´ee comme un ´\n" +
                "quartier du type (couleur) de votre choix.\n");
        Quartier tresorImperial = new Quartier("Trésor Impérial", Quartier.TYPE_QUARTIERS[4], 5, "A la fin de la partie, marquez 1 point suppl´ementaire par pi`ece d’or dans votre tr´esor. ");
        Quartier fontaineAuxSouhaits = new Quartier("Fontaine aux Souhaits", Quartier.TYPE_QUARTIERS[4], 5, "A la fin de la partie, marquez 1 point supplm´entaire par merveille dans votre cit´e, y compris `\n" +
                "la Fontaine aux Souhaits.\n");
        Quartier tripot = new Quartier("Tripot", Quartier.TYPE_QUARTIERS[4],6, "Vous pouvez payer tout ou partie du coˆut de construction du Tripot en cartes de votre main,\n" +
                "au prix de 1 carte pour 1 pi`ece d’or. Si le Tripot est confisqu´e par l’Echevin, le joueur n’est ´\n" +
                "rembours´e que de l’or qu’il a d´epens´e, pas des cartes." );


        p.ajouter(bibliotheque);
        p.ajouter(forge);
        p.ajouter(carriere);
        p.ajouter(laboratoire);
        p.ajouter(courDesMiracles);
        p.ajouter(manufacture);
        p.ajouter(donjon);
        p.ajouter(salleDesCartes);
        p.ajouter(dracoport);
        p.ajouter(statueEquestre);
        p.ajouter(ecoleDeMagie);
        p.ajouter(tresorImperial);
        p.ajouter(fontaineAuxSouhaits);
        p.ajouter(tripot);

        p.melanger();

        plateau.setPioche(p);

        return plateau;
    }
}
