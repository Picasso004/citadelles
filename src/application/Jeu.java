package application;

import controleur.Interaction;
import static controleur.Interaction.*;
import modele.*;
import java.util.List;

import java.util.*;



public class Jeu {
    private PlateauDeJeu plateauDeJeu;
    private int numeroConfiguration;
    private Random generateur;
    private List<Personnage> personnagesRestants;

    public Jeu() {
        this.plateauDeJeu = new PlateauDeJeu();
        this.numeroConfiguration = 0;
        this.generateur = new Random();
    }

    public void jouer(){
        //Affichage message de bienvenue
        System.out.println("BIENVENUE SUR CITADELLES v1.0 !!!!");
        System.out.println("\n" +
                " ██████╗██╗████████╗ █████╗ ██████╗ ███████╗██╗     ██╗     ███████╗███████╗\n" +
                "██╔════╝██║╚══██╔══╝██╔══██╗██╔══██╗██╔════╝██║     ██║     ██╔════╝██╔════╝\n" +
                "██║     ██║   ██║   ███████║██║  ██║█████╗  ██║     ██║     █████╗  ███████╗\n" +
                "██║     ██║   ██║   ██╔══██║██║  ██║██╔══╝  ██║     ██║     ██╔══╝  ╚════██║\n" +
                "╚██████╗██║   ██║   ██║  ██║██████╔╝███████╗███████╗███████╗███████╗███████║\n" +
                " ╚═════╝╚═╝   ╚═╝   ╚═╝  ╚═╝╚═════╝ ╚══════╝╚══════╝╚══════╝╚══════╝╚══════╝\n" +
                "                                                                            \n");
        //Affichage du menu
        Scanner scanner = new Scanner(System.in);
        int choix;
        do{
            System.out.println("\nMENU PRINCIPAL");
            System.out.println("1. JOUER UNE PARTIE");
            System.out.println("2. AFFICHER LES REGLES");
            System.out.println("3. QUITTER");

            System.out.println("Choisissez une option (1-3) : ");
            choix = scanner.nextInt();

            switch (choix){
                case 1:
                    jouerPartie();
                    break;
                case 2:
                    afficherLesRegles();
                    break;
                case 3:
                    System.out.println("AU REVOIR !!!");
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez choisir une option valide (1-3).");
            }
        }while (choix != 3);

    }
    private void afficherLesRegles() {
        System.out.println("Bienvenue dans Citadelle !");
        System.out.println("Objectif du jeu : Construisez une cité de Sept quartiers avant vos adversaires.");

        // Ajout des règles de distribution des cartes Personnage en début de partie
        System.out.println("\nDistribution des cartes Personnage en début de partie :");
        System.out.println("- Le joueur qui détient la Couronne mélange les 8 cartes Personnage choisies.");
        System.out.println("- Il écarte une carte face cachée.");
        System.out.println("- Il écarte 2 cartes face visible en fonction du nombre de joueurs.");
        System.out.println("- Les cartes écartées ne seront pas utilisées pour le tour.");
        System.out.println("- Le joueur possédant la Couronne choisit secrètement une des cartes parmi les cartes Personnage restantes.");
        System.out.println("- Il place cette carte devant lui face cachée.");
        System.out.println("- Il passe ensuite les cartes Personnage restantes à son voisin de gauche qui procède de la même manière.");
        System.out.println("- Le dernier joueur place la dernière carte restante face cachée, avec la première carte écartée face cachée.");

        // Description des 14 merveilles
        System.out.println("\nDéscription des 14 merveilles :");
        System.out.println("1. Bibliothèque effet : revenu (cartes quartier), coût de construction : 6\n" +
                "Si vous choisissez de piocher des cartes au début du tour, concervez-les toutes");

        System.out.println("2.Carrière effet : construction, coût de construction : 5\n" +
                "Vous pouvez bâtir des quartiers identiques à d’autres quartiers de votre cité. Le propriétaire\n" +
                "de la carrière peut bâtir autant de quartiers identiques qu’il le souhaite, mais ne peut pas\n" +
                "utiliser le pouvoir de l’´Echevin, du Diplomate ou du Capitaine pour acquérir des quartiers\n" +
                "identiques.");

        System.out.println("3.Cours des Miracles effet : calcul des points, coût de construction : 2\n" +
                "Pour le calcul du score final, la Cour des Miracles est considérée comme un quartier de type\n" +
                "(couleur) de votre choix. Dans la cas ou le propriétaire la considère comme un quartier noble,\n" +
                "militaire, marchant ou religieux, la Cour des Miracles ne peut plus ˆetre consid´er´ee comme\n" +
                "une merveille.");

        System.out.println("4. Donjon effet : pouvoir, coût de construction : 3\n" +
                            "Le Donjon ne peut être affecté par les pouvoirs des personnages de rang 8");

        System.out.println("5. Dracoport effet : calcul des points, coût de construction : 6\n" +
                            "Marquez 2 points supplémentaires à la fin de la partie");

        System.out.println("6.Ecole de Magie effet : revenu (or), coût de construction : 6\n" +
                            "Pour la perception des revenus des personnages, l’école de Magie est considérée comme un\n" +
                            "quartier du type (couleur) de votre choix.");

        System.out.println("7. Fontaine aux Souhaits effet : calcul des points, coût de construction : 5\n" +
                            "`A la fin de la partie, marquez 1 point supplméntaire par merveille dans votre cité, y compris\n" +
                             "la Fontaine aux Souhaits.");

        System.out.println("8.Forge effet : revenu (cartes quartier), coût de construction : 5\n" +
                            "Une fois par tour, vous pouvez payez 2 pièces d’or pour piocher 3 cartes.");

        System.out.println("9. Laboratoire effet : fin tour, coût de construction : 5\n" +
                            "Une fois par tour, vous pouvez d´efausser 1 carte pour recevoir 2 pièces d’or.");

        System.out.println("10.Manufacture effet : construction, coût de construction : 5\n" +
                            "Payez 1 pièce d’or de moins lorsque vous bâtissez une autre merveille.");

        System.out.println("11. Salles des Cartes effet : calcul des points, coût de construction : 5\n" +
                            "`A la fin de la partie, marquez 1 point supplémentaire par carte dans votre main.");

        System.out.println("12. Statue Equestre effet : calcul des points, coût de construction : 3\n" +
                            "Si vous d´etenez le Couronne à la fin de la partie, marquez 5 points supplémentaires.");

        System.out.println("13. Trésor Impérial effet : calcul des points, coût de construction : 5\n" +
                            "`A la fin de la partie, marquez 1 point supplémentaire par pièce d’or dans votre trésor.");

        System.out.println("14. Tripot effet : construction, coût de construction : 6\n" +
                            "Vous pouvez payer tout ou partie du coût de construction du Tripot en cartes de votre main,\n" +
                            "au prix de 1 carte pour 1 pièce d’or. Si le Tripot est confisqué par l’Echevin, le joueur n’est\n" +
                            "remboursé que de l’or qu’il a dépensé, pas des cartes.");

        //

        System.out.println("\nDéscription des personnages :");

        System.out.println("1- Assassin : Annoncez le personnage que vous assassinez. Si un joueur a ce personnage, il ne doit\n" +
                "pas réagir tout de suite, mais lorsque le personnage assassiné sera appelé. Le personnage\n" +
                "assassiné passe son tour.");

        System.out.println("2- Voleur:  Annoncez le personnage que vous volez. Si un joueur a ce personnage, il ne doit pas\n" +
                "réagir. Lorsque le personnage volé sera appelé et révélé, vous lui prendrez toutes ces pièces\n" +
                "d’or. Vous ne pouvez voler ni le personnage de rang 1 (Assassin, Sorcière ou Echevin), ni le\n" +
                "personnage assassiné ou ensorcelé.");

        System.out.println("3- Magicienne : Vous pouvez au choix :\n" +
                "— soit échanger toutes les cartes de votre main avec celles d’un autre joueur ; si vous n’avez\n" +
                "aucune carte en main, vous prenez simplement les cartes de l’autre joueur,\n" +
                "— soit d´efausser un certain nombre de cartes de votre main, les placer sous la pioche et\n" +
                "piocher le même nombre de cartes du dessus de la pioche.");

        System.out.println("4- Roi: Recevez une pièce d’or pour chaque quartier Noble dans votre cité.\n" +
                "Vous devez prendre la Couronne. C’est désormais vous qui appelerez les personnages. En\n" +
                "outre, au prochain tour, et aux tours suivants jusqu’à ce qu’un autre joueur révèle le Roi,\n" +
                "vous choisirez votre personnage en premier.\n" +
                "Si vous ˆetes assassiné, vous passez votre tour comme n’importe quel personnage. Néanmoins,\n" +
                "après que tous les autres joueurs ont joué, vous révélez que vous aviez choisi le Roi et, en\n" +
                "tant qu’héritier du Roi assassiné, prenez la Couronne.\n" +
                "Si vous êtes ensorcelé, vous prenez quand même la Couronne.\n" +
                "Si le Roi est écarté face visible en début de tour, remplacez-le par le personnage suivant et\n" +
                "mélangez-le avec les personnages restants.");


        System.out.println("5- Evêque: Recevez 1 pièce d’or pour chaque quartier religieux dans votre cité.\n" +
                "Vos quartiers ne peuvent pas être affectés par les pouvoirs des personnages de rang 8 \n" +
                "(Condottiere, Diplomate, Capitaine).\n" +
                "Si vous êtes assassiné, vous n’êtes plus ´Evêque ; vous êtes mort, et vos Quartiers peuvent\n" +
                "donc être la cible des personnages de rang 8. De même, si vous êtes ensorcelé, les quartiers\n" +
                "de la Sorcière sont protégés contre les personnages de rang 8, mais pas les vôtres..");

        System.out.println("6- Marchande : Recevez 1 pièce d’or pour chaque quartier Commerçant dans votre cité.\n" +
                "Recevez 1 pièce d’or supplémentaire quel que soit le type de ressources (pièce d’or ou carte)\n" +
                "que vous avez prises au début de votre tour.");

        System.out.println("7- Architecte :Piochez 2 cartes Quartier et ajoutez-les à votre main.\n" +
                "Vous pouvez bâtir jusqu’à 3 quartiers durant votre tour.");

        System.out.println("8- Condottiere: Recevez 1 pièce d’or pour chaque quartier Militaire dans votre cité.\n" +
                "Vous pouvez détruire un quartier de votre choix dans une cité en payant son coût de construction\n" +
                " moins 1. Vous pouvez donc détruire gratuitement un quartier de coût 1, ou payer 1 pièce\n" +
                "d’or pour détruire un quartier de coût 2, etc.\n" +
                "Vous ne pouvez détruire un quartier d’une cité déjà complète. Vous pouvez détruire l’un de\n" +
                "vos propres quartiers. Les quartiers détruits sont défaussés, face cachée, en dessous de la\n" +
                "pioche.");






        System.out.println("\nDéroulement du tour de jeu :");
        System.out.println("1. Choisissez un personnage parmi ceux disponibles.");
        System.out.println("2. Réalisez les actions spécifiques à ce personnage.");
        System.out.println("3. Obtenez des pièces d'or ou piochez des cartes en fonction de votre choix.");
        System.out.println("4. Construisez des quartiers pour compléter votre cité.");


        System.out.println("\nGestion de la couronne :");
        System.out.println("- Le joueur qui choisit le personnage Roi reçoit la couronne.");
        System.out.println("- La couronne confère des avantages pendant le tour.");

        System.out.println("\nFin de la partie :");
        System.out.println("- La partie se termine lorsque quelqu'un a une cité de Sept quartiers.");
        System.out.println("- Le joueur avec la cité complète gagne la partie 4 points supplementaire.");
        // Ajout des règles de calcul des points à la fin de la partie
        System.out.println("\nCalcul des points à la fin de la partie :");
        System.out.println("- Somme totale des coûts de construction des quartiers de votre cité.");
        System.out.println("- 3 points supplémentaires si votre cité comprend au moins un quartier de cinq types différents (Noble, Commerçant, Religieux, Militaire et Merveille).");
        System.out.println("- 4 points supplémentaires si vous êtes le premier joueur ayant complété votre cité.");
        System.out.println("- 2 points supplémentaires si votre cité est complète mais que vous n'avez pas été le premier à la compléter.");
        System.out.println("- Somme des différents bonus éventuels des Merveilles de votre cité.");

        System.out.println("\nLe joueur avec le score le plus élevé est le vainqueur.");
        System.out.println("En cas d'égalité, la victoire revient à celui qui a révélé le personnage de rang le plus élevé au dernier tour.");

        // Ajoutez ici d'autres instructions pour expliquer des règles spécifiques, des cartes spéciales, etc.
    }

    private void jouerPartie(){
        initialisation();
        int i = 1;
        do {
            System.out.println("-----------------------------------------");
            System.out.println("\nTour de jeu " + i);
            tourDeJeu();
            gestionCouronne();
            reinitialisationPersonnages();
            i++;
        }while (!partieFinie());

        System.out.println("La partie est terminée !");
        calculDesPoints();
    }
    private void initialisation(){
        // Initialiser la pioche avec les 54 cartes Quartier
        Pioche pioche = Configuration.nouvellePioche();

        // Initialiser le plateau de jeu avec la configuration de base
        this.plateauDeJeu = Configuration.configurationDeBase(pioche);

        // Attribuer les ressources
        for (int i = 0; i < this.plateauDeJeu.getNombreJoueurs(); i++) {
            Joueur joueur = this.plateauDeJeu.getJoueur(i);

            // Distribuer deux pièces d'or à chaque joueur
            joueur.ajouterPieces(2);

            // Piocher quatre cartes Quartier pour chaque joueur
            for (int j = 0; j < 4; j++) {
                Quartier carte = this.plateauDeJeu.getPioche().piocher();
                if (carte != null) {
                    joueur.ajouterQuartierDansMain(carte);
                }
            }
        }

        // Attribution de la couronne aléatoirement
        Random generateur = new Random();
        int numeroHasard = generateur.nextInt(this.plateauDeJeu.getNombreJoueurs());
        this.plateauDeJeu.getJoueur(numeroHasard).setPossedeCouronne(true);
    }

    private void choixPersonnages() {
        //Initialisation personnages restants
        Personnage[] personnagesArray = plateauDeJeu.getListePersonnages();
        this.personnagesRestants = new ArrayList<>(Arrays.asList(Arrays.copyOfRange(personnagesArray, 0, plateauDeJeu.getNombrePersonnages())));

        System.out.println(Interaction.GREEN + "Choix des personnages :" + Interaction.RESET);

        Collections.shuffle(personnagesRestants);

        // Écarte deux cartes face cachée et une face visible
        Personnage carteVisible = personnagesRestants.remove(0);
        Personnage carteCachee1 = personnagesRestants.remove(0);
        Personnage carteCachee2 = personnagesRestants.remove(0);

        Joueur[] temp = this.plateauDeJeu.getListeJoueurs();
        Joueur[] listeJoueur = new Joueur[this.plateauDeJeu.getNombreJoueurs()];

        for(int i = 0; i< this.plateauDeJeu.getNombreJoueurs(); i++){
            if(temp[i] != null)
                listeJoueur[i] = temp[i];
        }

        // Commencer par le joueur ayant la couronne
        int indexCouronne = -1;

        // Chercher le joueur qui possède la couronne
        for (int i = 0; i < this.plateauDeJeu.getNombreJoueurs(); i++) {
            Joueur joueur = listeJoueur[i];
            if (joueur.getPossedeCouronne()) {
                indexCouronne = i;
                System.out.println(GREEN + "Le joueur " + joueur.getNom() + " possède la couronne" + RESET);
                break;
            }
        }

        // Réorganiser le tableau si la couronne a été trouvée
        if (indexCouronne != -1) {
            Joueur[] nouveauTableau = new Joueur[listeJoueur.length];
            int indexNouveauTableau = 0;

            // Copier à partir du joueur ayant la couronne jusqu'à la fin du tableau initial
            for (int i = indexCouronne; i < listeJoueur.length; i++) {
                nouveauTableau[indexNouveauTableau] = listeJoueur[i];
                indexNouveauTableau++;
            }

            // Copier depuis le début jusqu'au joueur avant celui ayant la couronne
            for (int i = 0; i < indexCouronne; i++) {
                nouveauTableau[indexNouveauTableau] = listeJoueur[i];
                indexNouveauTableau++;
            }

            listeJoueur = nouveauTableau;

        }


        for (int i = 0; i<this.plateauDeJeu.getNombreJoueurs(); i++) {
            Joueur joueur = listeJoueur[i];
            System.out.println(YELLOW + "\nC'est au tour du "+joueur.getNom() + " de choisir :" + RESET);

            System.out.println("Le personnage \"" + carteVisible.getNom() + "\" est écarté face visible");
            System.out.println("Le personnage \"" + carteCachee1.getNom() + "\" est écarté face cachée");
            System.out.println("Un personnage est écarté face cachée");

            Personnage p = choisirPersonnage(personnagesRestants);
            joueur.setPersonnage(p);
            personnagesRestants.remove(p);
            p.setJoueur(joueur);
            p.setPlateau(this.plateauDeJeu);

            System.out.println(joueur.getNom() + " a choisit " + p.getNom() );
        }
    }

    private Personnage choisirPersonnage(List<Personnage> personnagesRestants) {
        System.out.println("Personnages restants :");
        for (int i = 0; i < personnagesRestants.size(); i++) {
            System.out.println((i + 1) + ". " + personnagesRestants.get(i).getNom());
        }

        int choix;
        do {
            System.out.print("Choisissez un personnage (1-" + personnagesRestants.size() + ") : ");
            choix = Interaction.lireUnEntier(1, personnagesRestants.size() + 1);
        } while (choix < 1 || choix > personnagesRestants.size());

        return personnagesRestants.get(choix - 1);
    }

    private void gestionCouronne(){
        Personnage roi = null;
        Joueur joueurCouronne = null;

        for (Personnage personnage : plateauDeJeu.getListePersonnages()){
            if (personnage instanceof Roi){
                roi = personnage;
                break;
            }
        }
        List<Joueur> joueurs = new ArrayList<>(Arrays.asList(Arrays.copyOfRange(plateauDeJeu.getListeJoueurs(), 0, plateauDeJeu.getNombreJoueurs())));
        for (Joueur joueur : joueurs){
            if (joueur.getPersonnage() == roi){
                joueurCouronne = joueur;
            }
        }
        if (joueurCouronne != null){
            for (Joueur joueur : joueurs){
                joueur.setPossedeCouronne(false);
            }
            joueurCouronne.setPossedeCouronne(true);

            System.out.println("La couronne est attribuée à " + joueurCouronne.getNom());
        }else{
            System.out.println("Personnage du Roi non choisi, la couronne reste au même joueur");
        }
    }
    private void reinitialisationPersonnages(){
        //TODO IMPLEMENTER
            for (Personnage personnage : plateauDeJeu.getListePersonnages()) {
                personnage.reinitialiser();
            }
    }
    private boolean partieFinie(){
        List<Joueur> joueurs = new ArrayList<>(Arrays.asList(Arrays.copyOfRange(plateauDeJeu.getListeJoueurs(), 0, plateauDeJeu.getNombreJoueurs())));
        for ( Joueur joueur : joueurs){
            if (joueur.nbQuartiersDansCite() >= 2){
                System.out.println("\n" + joueur.getNom() + " a une cité complète. La partie est terminée !");
                return true;
            }
        }
        return false;
    }
    private void tourDeJeu(){
        // 1 - Choix des Personnages
        choixPersonnages();

        System.out.println(GREEN + "\nDébut du tour" + RESET);

        for(int i = 0; i<this.plateauDeJeu.getNombrePersonnages();i++){
            Personnage personnageCourant = this.plateauDeJeu.getPersonnage(i);
            Joueur joueurCourant = personnageCourant.getJoueur();

            if(joueurCourant != null){
                System.out.println(YELLOW + "\nLe personnage "  + personnageCourant.getNom() + " est appelé " + RESET);

                // 3a - Si le personnage est assassiné, changer de personnage
                if (personnageCourant.getAssassine()) {
                    System.out.println(RED + personnageCourant.getNom() + " a été assassiné."+RESET);
                }
                else {
                        // 3b - Si le personnage est volé, donner de l'argent au voleur et percevoir les ressources
                        if (personnageCourant.getVole()) {
                            System.out.println(RED + personnageCourant.getNom() + " s'est fait voler ses ressources."+RESET);
                        }

                        //Affichage informations personnage
                        System.out.println(MAGENTA + "Caracteristiques de " + personnageCourant.getNom() + " : ");
                        System.out.println(personnageCourant.getCaracteristiques()+RESET);

                        // Affichage informations joueur
                        System.out.println(BLUE + "\n"+joueurCourant.getNom() + " vous disposez de : ");
                        System.out.println("Trésor : " + joueurCourant.nbPieces() + " pieces");
                        System.out.println("Cité : ");

                        if(joueurCourant.nbQuartiersDansCite() > 0) {
                            List<Quartier> cite = new ArrayList<>(Arrays.asList(Arrays.copyOfRange(joueurCourant.getCite(), 0, joueurCourant.nbQuartiersDansCite())));
                            for(int k = 0; k < cite.size();k++){
                                System.out.println((k+1) + ". " + cite.get(k).getNom());
                            }
                        }
                        else {
                            System.out.println(RED + "Votre cite est vide !" + RESET);
                        }

                        System.out.println(BLUE+ "Votre main : ");


                        if(joueurCourant.nbQuartiersDansMain() > 0) {
                            ArrayList<Quartier> main = joueurCourant.getMain();
                            for(int k = 0; k < main.size();k++){
                                System.out.println((k+1) + ". " + main.get(k).getNom() + " : cout de construction = " + main.get(k).getCout());
                            }
                            System.out.println(RESET);
                        }
                        else {
                            System.out.println(RED + "Votre main est vide !" + RESET);
                        }

                        percevoirRessource(joueurCourant);

                        // 4 - Percevoir les ressources spécifiques
                        //percevoirRessourcesSpecifiques(personnageCourant); // Méthode à adapter selon votre modèle

                        // 5 - Si le joueur décide d'utiliser son pouvoir, utiliser le pouvoir
                        System.out.println("\nVoulez vous utiliser votre pouvoir (oui/o non/n):");
                        if (lireOuiOuNon()) {
                            personnageCourant.utiliserPouvoir();
                            System.out.println("Vous avez utilisé votre pouvoir.");
                        }

                        // 5b - Si le joueur veut construire, construire
                        System.out.println("\nVoulez vous construire ? (oui/o non/n):");
                        if (lireOuiOuNon()) {
                            // Afficher la liste des quartiers dans la main du joueur
                            System.out.println("\nListe des quartiers dans votre main :");
                            ArrayList<Quartier> main = joueurCourant.getMain();
                            System.out.println("0. Ne rien construire");
                            for (int k = 0; k < main.size(); k++) {
                                System.out.println((k + 1) + ". " + main.get(k).getNom() + " : cout de construction = " + main.get(k).getCout());
                            }

                            // Demander au joueur de choisir un quartier
                            boolean choixValide = false;
                            while (!choixValide) {
                                // Demander au joueur de choisir un quartier (ou "0" pour ne rien construire)
                                System.out.print("Choisissez un quartier à construire (0-" + main.size() + ") : ");
                                int choixQuartier = Interaction.lireUnEntier(0, main.size()+1);

                                while (choixQuartier != 0 && main.get(choixQuartier - 1).getCout() > joueurCourant.nbPieces()) {
                                    // Le joueur a choisi un quartier, mais il n'a pas les moyens de le construire
                                    System.out.println("Vous n'avez pas les moyens nécessaires pour construire le quartier \"" + main.get(choixQuartier - 1).getNom() + "\".");

                                    // Proposer de refaire un choix en lui permettant de saisir une nouvelle valeur au clavier
                                    System.out.print("Veuillez choisir à nouveau (0-" + main.size() + ") : ");
                                    choixQuartier = Interaction.lireUnEntier(0, main.size()+1);
                                }

                            if (choixQuartier == 0) {
                                // Le joueur a choisi de ne rien construire
                                System.out.println("Vous avez choisi de ne rien construire.");
                                choixValide = true;
                            } else {
                                // Le choix est valide, procéder comme avant
                                Quartier quartierChoisi = main.get(choixQuartier - 1);
                                int PieceRestant = joueurCourant.nbPieces() - quartierChoisi.getCout();
                                joueurCourant.ajouterQuartierDansCite(quartierChoisi);
                                System.out.println("Vous avez construit le quartier \"" + quartierChoisi.getNom() + "\" dans votre cité.");

                                System.out.println("\nContenu de votre cité après la construction :");
                                Quartier[] cite = joueurCourant.getCite();
                                for (int k = 0; k < cite.length; k++) {
                                    if (cite[k] != null) {
                                        System.out.println((k + 1) + ". " + cite[k].getNom());
                                    }
                                }
                                System.out.println("\nIl vous reste : Trésor : " + PieceRestant + " pièces");

                                choixValide = true;
                            }
                        }
                    }

                }
                }
            }
        }

    public void percevoirRessource(Joueur joueur) {
        System.out.println("\nChoisissez une action :");
        System.out.println("1) Prendre deux pièces d'or");
        System.out.println("2) Piocher deux cartes de la pioche");

        int choix = Interaction.lireUnEntier(1, 3);

        switch (choix) {
            case 1 -> {
                    joueur.ajouterPieces(2);
                    System.out.println(joueur.getNom() + " a reçu 2 pieces d'or.");
                }
            case 2 -> {
                ArrayList<Quartier> cartesPiochees = new ArrayList<>();
                for (int i = 0; i < 2; i++) {
                    Quartier carte = plateauDeJeu.getPioche().piocher();
                    cartesPiochees.add(carte);
                }
                System.out.println("Voici vos deux cartes piochees. Choisissez celle que vous voulez garder :");
                for (int i = 0; i < cartesPiochees.size(); i++) {
                    System.out.println((i + 1) + ") " + cartesPiochees.get(i).getNom());
                }
                choix = Interaction.lireUnEntier(1, cartesPiochees.size() + 1);

                Quartier carteGardee = cartesPiochees.remove(choix-1);
                joueur.ajouterQuartierDansMain(carteGardee);

                Quartier carteNonGardee = cartesPiochees.get(cartesPiochees.size()-1);
                plateauDeJeu.getPioche().ajouter(carteNonGardee);

                System.out.println("La carte "+carteGardee.getNom() + " a ete ajoute a la main du "+joueur.getNom());

            }
            default -> {
                System.out.println("Choix invalide. Veuillez choisir à nouveau.");
                percevoirRessource(joueur);
            }
        }
    }
    private void calculDesPoints(){
        //TODO implémenter la méthode
        System.out.println("\nCALCUL DES POINTS");
        System.out.println("******************");

        HashMap<Joueur, Integer> pointsDesJoueurs = new HashMap<>();

        for (Joueur joueur : plateauDeJeu.getListeJoueurs()){
            int points = 0;
            if(joueur != null){
                //Calcul de la somme total des coûts de construction des quartiers de la cité
                for (Quartier quartier : joueur.getCite()){
                    if (quartier != null){
                        points += quartier.getCout();
                    }
                }
                //Vérification de la présence d'au moins un quartier de chaque type
                boolean typesDifferents = aCinqTypesDifferents(joueur.getCite());
                if (typesDifferents){
                    points += 3;
                }

                //Ajout des bonus éventuels des Merveilles de la cité
                if(joueur.quartierPresentDansCite("Dracoport")){
                    points += 2;
                    System.out.println(joueur.getNom() + " possède la merveille Dracoport dans sa cité. Elle lui rapporte 2 pts.");
                }

                if (joueur.quartierPresentDansCite("Fontaine aux Souhaits")){
                    for(Quartier quartier : joueur.getCite()){
                        if(quartier != null){
                            if (quartier.getType().equals("MERVEILLE")){
                                points += 1;
                            }
                        }
                    }
                    System.out.println(joueur.getNom() + " possède la merveille Fontaine aux Souhaits dans sa cité. Elle lui rapporte 1 pt/merveille dans sa cité.");
                }

                if (joueur.quartierPresentDansCite("Salles des Cartes")){
                    points += joueur.nbQuartiersDansMain();
                    System.out.println(joueur.getNom() + " possède la merveille Salles des Cartes dans sa cité. Elle lui rapporte 1pt/nombre de carte dans sa main.");
                }

                if(joueur.quartierPresentDansCite("Statue Equestre") && joueur.getPossedeCouronne()){
                    points += 5;
                    System.out.println(joueur.getNom() + " possède la merveille Statue Equestre dans sa cité. Elle lui rapporte 5 pts.");
                }

                if (joueur.quartierPresentDansCite("Trésor Impérial")){
                    points += joueur.nbPieces();
                    System.out.println(joueur.getNom() + " possède la merveille Trésor Impérial dans sa cité. Elle lui rapporte 1pt/pièce d'or dans son trésor.");
                }

                // Ajout des points pour ce joueur dans la HashMap
                pointsDesJoueurs.put(joueur, points);
            }
        }

        System.out.println(MAGENTA + "\n RESULTATS"+RESET);

        // Maintenant que tous les points sont calculés, on peut les trier par ordre décroissant
        // En utilisant un TreeMap pour trier la HashMap par les valeurs (points) de manière décroissante
        TreeMap<Joueur, Integer> pointsTries = new TreeMap<>((a, b) -> pointsDesJoueurs.get(b).compareTo(pointsDesJoueurs.get(a)));
        pointsTries.putAll(pointsDesJoueurs);

        // Affichage des points triés
        for (Map.Entry<Joueur, Integer> entry : pointsTries.entrySet()) {
            System.out.println(entry.getKey().getNom() + " : " + entry.getValue() + " points (Dernier personnage : " +
                    entry.getKey().getPersonnage().getNom() + " - rang " + entry.getKey().getPersonnage().getRang()+")");
        }
        System.out.println();
    }

    //Methode auxiliaire pour vérifier la présence d'au moins un quartier de chaque type
    private boolean aCinqTypesDifferents(Quartier[] cite){
        int[] types =new int[5]; //NOBLE, COMMERCANT, RELIGIEUX, MILITAIRE, MERVEILLE

        for (Quartier quartier : cite){
            if (quartier != null){
                String type = quartier.getType();
                switch (type) {
                    case "NOBLE":
                        types[0] = 1;
                        break;
                    case "COMMERCANT":
                        types[1] =1;
                        break;
                    case "RELIGIEUX":
                        types[2]= 1;
                        break;
                    case "MILITAIRE":
                        types[3] = 1;
                        break;
                    case "MERVEILLE":
                        types[4] = 1;
                        break;
                }
            }
        }
        int totalTypes = 0;
        for (int type : types){
            totalTypes += type;
        }
        return totalTypes >= 5;
    }


    /*private void percevoirRessourcesSpecifiques(Personnage personnage) {
        // Percevoir les ressources spécifiques en fonction du personnage
        // Méthode à implémenter
    }*/
    private boolean tousLesPersonnagesOntJoue() {
        // Vérifier si tous les personnages ont joué
        // Méthode à implémenter
        return false;
    }
}