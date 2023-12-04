package application;

import modele.PlateauDeJeu;

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
        System.out.println("Voici les règles du jeu citadelles.");
    }
    private void jouerPartie(){
        System.out.println("Méthode pour jouer une partie.");
    }
    private void initialisation(){}
    private void gestionCouronne(){}
    private void reinitialisationPersonnages(){}
    private boolean partieFinie(){return false;}
    private void tourDeJeu(){}
    private void choixPersonnages(){}
    private void percevoirRessource(){}
    private void calculDesPoints(){}

    public static void main(String[] args){
        Jeu jeu = new Jeu(new PlateauDeJeu(), 0, new Random());
        jeu.jouer();
    }
}
