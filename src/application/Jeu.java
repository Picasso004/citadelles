package application;

import modele.Joueur;
import modele.PlateauDeJeu;
import modele.Personnage;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Jeu {
    private PlateauDeJeu PlateauDeJeu;
    private int numeroConfiguration;
    private Random generateur;

    public Jeu(modele.PlateauDeJeu plateauDeJeu, int numeroConfiguration, Random generateur) {
        PlateauDeJeu = new PlateauDeJeu();
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
        Configuration.nouvellePioche();
        Configuration.configurationDeBase(modele.PlateauDeJeu);

        ArrayList<Joueur> joueurs = new ArrayList<>();
        Joueur joueurUtilisateur = new Joueur("Utilissateur");
        joueurs.add(joueurUtilisateur);
    }
    private void gestionCouronne(){
        //TODO implémenter la méthode
    }
    private void reinitialisationPersonnages(){}
    private boolean partieFinie(){
        //TODO implémenter la méthode
        return false;}
    private void tourDeJeu(){

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
