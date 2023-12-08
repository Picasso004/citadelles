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
                "_________ .__  __              .___     .__  .__                 \n" +
                "\\_   ___ \\|__|/  |______     __| _/____ |  | |  |   ____   ______\n" +
                "/    \\  \\/|  \\   __\\__  \\   / __ |/ __ \\|  | |  | _/ __ \\ /  ___/\n" +
                "\\     \\___|  ||  |  / __ \\_/ /_/ \\  ___/|  |_|  |_\\  ___/ \\___ \\ \n" +
                " \\______  /__||__| (____  /\\____ |\\___  >____/____/\\___  >____  >\n" +
                "        \\/              \\/      \\/    \\/               \\/     \\/ \n");
        //Affichage du menu
        Scanner scanner = new Scanner(System.in);
        int choix;
        do{
            System.out.println("MENU PRINCIPAL");
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
    private void afficherLesRegles(){
        //TODO implémenter la méthode
        System.out.println("Voici les règles du jeu citadelles.");
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

        Joueur[] listeJoueur = this.plateauDeJeu.getListeJoueurs();

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
        //TODO VERIFIER
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
    }
    private boolean partieFinie(){
        List<Joueur> joueurs = new ArrayList<>(Arrays.asList(Arrays.copyOfRange(plateauDeJeu.getListeJoueurs(), 0, plateauDeJeu.getNombreJoueurs())));
        for ( Joueur joueur : joueurs){
            if (joueur.nbQuartiersDansCite() >= 7){
                System.out.println(joueur.getNom() + " a une cité complète. La partie est terminée !");
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
                } else {
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
                            System.out.println((k+1) + ". " + main.get(k).getNom());
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
                        Quartier quartierChoisi = joueurCourant.retirerQuartierDansMain();
                        if (quartierChoisi != null) {
                            joueurCourant.ajouterQuartierDansCite(quartierChoisi);
                            System.out.println("Vous avez construit un quartier dans votre cité.");
                        } else {
                            System.out.println("Votre main est vide. Vous ne pouvez pas construire de quartier.");
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
        System.out.println("CALCUL DES POINTS");
        System.out.println("******************");

        for (Joueur joueur : plateauDeJeu.getListeJoueurs()){
            int points = 0;

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
            //TODO Verification du premier joueur ayant complété sa cité

            //TODO Ajout des bonus éventuels des Merveilles de la cité

        }
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