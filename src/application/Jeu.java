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
