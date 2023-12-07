package application;

import controleur.Interaction;
import modele.*;
import java.util.List;

import java.util.*;

import static controleur.Interaction.lireOuiOuNon;

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
        do {
            tourDeJeu();
            gestionCouronne();
            reinitialisationPersonnages();
        }while (!partieFinie());

        System.out.println("La partie est terminée !");
    }
    private void initialisation(){
        // Initialiser la pioche avec les 54 cartes Quartier
        Pioche pioche = Configuration.nouvellePioche();

        // Initialiser le plateau de jeu avec la configuration de base
        this.plateauDeJeu = Configuration.configurationDeBase(pioche);

        // Attribuer les ressources et la couronne aux joueurs
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
    }

    private void choixPersonnages() {

        //Initialisation personnages restants
        Personnage[] personnagesArray = plateauDeJeu.getListePersonnages();
        this.personnagesRestants = new ArrayList<>(Arrays.asList(Arrays.copyOfRange(personnagesArray, 0, plateauDeJeu.getNombrePersonnages())));

        System.out.println("Choix des personnages :");

        Collections.shuffle(personnagesRestants);

        // Écarte deux cartes face cachée et une face visible
        Personnage carteVisible = personnagesRestants.remove(0);
        Personnage carteCachee1 = personnagesRestants.remove(0);
        Personnage carteCachee2 = personnagesRestants.remove(0);

        Joueur[] listeJoueur = this.plateauDeJeu.getListeJoueurs();

        for (int i = 0; i<this.plateauDeJeu.getNombreJoueurs(); i++) {
            Joueur joueur = listeJoueur[i];
            System.out.println("\nTour de "+joueur.getNom() + " de choisir ");

            System.out.println("Le personnage \"" + carteVisible.getNom() + "\" est écarté face visible");
            System.out.println("Le personnage \"" + carteCachee1.getNom() + "\" est écarté face cachée");
            System.out.println("Le personnage \"" + carteCachee2.getNom() + "\" est écarté face cachée");

            Personnage p = choisirPersonnage(personnagesRestants);
            joueur.setPersonnage(p);
            personnagesRestants.remove(p);

            System.out.println(joueur.getNom() + " a choisit " + p.getNom() );
        }
    }

    private Personnage choisirPersonnage(List<Personnage> personnagesRestants) {
        System.out.println("Personnages restants :");
        for (int i = 0; i < personnagesRestants.size(); i++) {
            System.out.println((i + 1) + ". " + personnagesRestants.get(i).getNom());
        }

        Scanner scanner = new Scanner(System.in);
        int choix;
        do {
            System.out.print("Choisissez un personnage (1-" + personnagesRestants.size() + ") : ");
            choix = scanner.nextInt();
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
        for (Joueur joueur : plateauDeJeu.getListeJoueurs()){
            if (joueur.getPersonnage() == roi){
                joueurCouronne = joueur;
                break;
            }
        }
        if (joueurCouronne != null){
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
        //TODO VERIFIER
        for ( Joueur joueur : plateauDeJeu.getListeJoueurs()){
            if (joueur.nbQuartiersDansCite() >= 7){
                System.out.println(joueur.getNom() + " a une cité complète. La partie est terminée !");
                return true;
            }
        }
        return false;
    }
    private void tourDeJeu(){
        //TODO implémenter la méthode
        System.out.println("Tour de jeu.");

        // 1 - Choix des Personnages
        choixPersonnages();

        System.out.println("\nDébut du tour");

        // 2 - Choisir le premier joueur
        //Joueur premierJoueur = choisirPremierJoueur();
        // Boucle pour le tour de chaque joueur
        for (int i = 0; i < plateauDeJeu.getNombreJoueurs(); i++) {
            Joueur joueurCourant = plateauDeJeu.getJoueur(i);

            // 3 - Appeler un personnage
            do {
                for (int j=0; j<9; j++){

                Personnage personnageCourant = plateauDeJeu.getPersonnage(j);
                System.out.println("\n" + " Le personnage "  + personnageCourant.getNom() + " est appellé ") ;

                // 3a - Si le personnage est assassiné, changer de personnage
                if (personnageCourant.getAssassine()) {
                    System.out.println("S'est fait assassiné.");
                    changerDePersonnage(joueurCourant, personnagesRestants);
                } else {
                    // 3b - Si le personnage est volé, donner de l'argent au voleur et percevoir les ressources
                    if (personnageCourant.getVole()) {
                        System.out.println("S'est fait voler ses ressources.");
                    }

                    percevoirRessource(joueurCourant);

                    // 4 - Percevoir les ressources spécifiques
                    //percevoirRessourcesSpecifiques(personnageCourant); // Méthode à adapter selon votre modèle

                    // 5 - Si le joueur décide d'utiliser son pouvoir, utiliser le pouvoir
                    if (lireOuiOuNon()) {
                        personnageCourant.utiliserPouvoir();
                        System.out.println("Vous avez utilisé votre pouvoir.");
                    }

                    // 5b - Si le joueur veut construire, construire
                    if (lireOuiOuNon()) {
                        Quartier quartierChoisi = joueurCourant.retirerQuartierDansMain();
                        if (quartierChoisi != null) {
                            joueurCourant.ajouterQuartierDansCite(quartierChoisi);
                            System.out.println("Vous avez construit un quartier dans votre cité.");
                        } else {
                            System.out.println("Votre main est vide. Vous ne pouvez pas construire de quartier.");
                        }
                    }

                    // 5c - Après les choix précédents, changer de personnage
                    changerDePersonnage(joueurCourant, personnagesRestants);
                }
                }
            } while (!tousLesPersonnagesOntJoue()); // Méthode à adapter selon votre modèle
        }
    }

    public void percevoirRessource(Joueur joueur) {
        System.out.println("Choisissez une action :");
        System.out.println("1) Prendre deux pièces d'or");
        System.out.println("2) Piocher deux cartes de la pioche");

        int choix = Interaction.lireUnEntier(1, 3);

        switch (choix) {
            case 1:
                joueur.ajouterPieces(2);
                break;
            case 2:
                for (int i = 0; i < 2; i++) {
                    Quartier carte = plateauDeJeu.getPioche().piocher();
                    joueur.ajouterQuartierDansMain(carte);
                }

                System.out.println("Voici vos deux cartes. Choisissez celle que vous voulez garder :");
                for (int i = 0; i < joueur.nbQuartiersDansMain(); i++) {
                    Quartier carte = joueur.retirerQuartierDansMain();
                    System.out.println((i + 1) + ") " + carte.getNom());
                    joueur.ajouterQuartierDansMain(carte);
                }

                int carteGardee = Interaction.lireUnEntier(1, joueur.nbQuartiersDansMain() + 1);

                for (int i = 0; i < joueur.nbQuartiersDansMain(); i++) {
                    if (i + 1 != carteGardee) {
                        Quartier carte = joueur.retirerQuartierDansMain();
                        plateauDeJeu.getPioche().ajouter(carte);
                    }
                }
                break;
            default:
                System.out.println("Choix invalide. Veuillez choisir à nouveau.");
                percevoirRessource(joueur);
                break;
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

            //Ajout des bonus éventuels des Merveilles de la cité

        }
    }

    //Methode auxiliaire pour vérifier la présence d'au moins un quartier de chaque type
    private boolean aCinqTypesDifferents(Quartier[] cite){
        int[] types =new int[5]; //NOBLE, COMMERCANT, RELIGIEUX, MILITAIRE, MERVEILLE
        return false;
    }


    // Méthodes à implémenter
    /*private Joueur choisirPremierJoueur() {
        // Choisir le premier joueur en fonction de celui qui a la couronne
        // Méthode à implémenter
        return null;
    }*/

    private void changerDePersonnage(Joueur joueur, List<Personnage> personnagesRestants) {
        // Changer de personnage pour le joueur donné
        Personnage nouveauPersonnage = choisirPersonnage(personnagesRestants);
        joueur.setPersonnage(nouveauPersonnage);
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

    public static void main(String[] args){
        Jeu jeu = new Jeu();
        jeu.jouer();
    }

}