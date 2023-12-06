package application;

import modele.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Jeu {
    private PlateauDeJeu plateauDeJeu;
    private int numeroConfiguration;
    private Random generateur;

    public Jeu(PlateauDeJeu plateauDeJeu, int numeroConfiguration, Random generateur) {
        this.plateauDeJeu = new PlateauDeJeu();
        this.numeroConfiguration = 0;
        this.generateur = new Random();
    }

    public void jouer(){
        //Affichage message de bienvenue
        System.out.println("BIENVENUE !!!!");
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
        Pioche p = Configuration.nouvellePioche();
        this.plateauDeJeu = Configuration.configurationDeBase(p);

        ArrayList<Joueur> joueurs = new ArrayList<>();
        Joueur joueurUtilisateur = new Joueur("Utilisateur");
        joueurs.add(joueurUtilisateur);
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
    private void reinitialisationPersonnages(){}
    private boolean partieFinie(){
        //TODO VERIFIER
        for ( Joueur joueur : plateauDeJeu.getListeJoueurs()){
            if (joueur.nbQuartiersDansCite() >= 8){
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

        // 2 - Choisir le premier personnage
        Joueur premierJoueur = choisirPremierJoueur();

        // 3 - Appeler un personnage
        do {
            Personnage personnageCourant = premierJoueur.getPersonnage(); // Méthode à implémenter dans la classe Joueur

            // 3a - Si le personnage est assassiné, changer de personnage
            if (personnageCourant.getAssassine()) {
                changerDePersonnage(premierJoueur); // Méthode à implémenter
            } else {
                // 3b - Si le personnage est volé, donner de l'argent au voleur et percevoir les ressources
                if (personnageCourant.getVole()) {
                    System.out.println("S'est fait volé ses ressources.");
                    // Méthode à implémenter
                }

                percevoirRessource(personnageCourant); // Méthode à implémenter

                // 4 - Percevoir les ressources spécifiques
                percevoirRessourcesSpecifiques(personnageCourant); // Méthode à implémenter

                // 5 - Si le joueur décide d'utiliser son pouvoir, utiliser le pouvoir
                if (lireOuiOuNon()) {
                    personnageCourant.utiliserPouvoir();
                    System.out.println("Vous avez utilisé votre pouvoir.");
                }

                // 5b - Si le joueur veut construire, construire
                if (lireOuiOuNon()) {
                    construire(premierJoueur);
                }

                // 5c - Après les choix précédents, changer de personnage
                changerDePersonnage(premierJoueur); // Méthode à implémenter
            }

        } while (!tousLesPersonnagesOntJoue()); // Méthode à implémenter
    }

    // Méthodes à implémenter

    private Joueur choisirPremierJoueur() {
        // Choisir le premier joueur en fonction de celui qui a la couronne
        // Méthode à implémenter
        return null;
    }

    private void changerDePersonnage(Joueur joueur) {
        // Changer de personnage pour le joueur donné
        // Méthode à implémenter
    }

    private void donnerArgentAuVoleur(Joueur voleur) {
        // Donner de l'argent au voleur
        // Méthode à implémenter
    }

    private void percevoirRessource(Personnage personnage) {
        // Percevoir les ressources en fonction du personnage
        // Méthode à implémenter
    }

    private void percevoirRessourcesSpecifiques(Personnage personnage) {
        // Percevoir les ressources spécifiques en fonction du personnage
        // Méthode à implémenter
    }

    private void utiliserPouvoir(Personnage personnage) {
        // Utiliser le pouvoir du personnage
        // Méthode à implémenter
    }

    private void construire(Joueur joueur) {

        // Construire une cité
        // Méthode à implémenter
    }

    private boolean tousLesPersonnagesOntJoue() {
        // Vérifier si tous les personnages ont joué
        // Méthode à implémenter
        return false;
    }
    private boolean lireOuiOuNon() {
        Scanner scanner = new Scanner(System.in);
        String reponse = scanner.next().toLowerCase(); // Convertir la réponse en minuscules pour gérer "Oui" ou "Non"
        return reponse.equals("oui");
    }

    private void choixPersonnages(){
        //TODO implémenter la méthode
    }
    private void percevoirRessource(){
        //TODO implémenter la méthode
    }
    private void calculDesPoints(){
        //TODO implémenter la méthode
    }

    public static void main(String[] args){
        Jeu jeu = new Jeu(new PlateauDeJeu(), 0, new Random());
        jeu.jouer();
    }
}
