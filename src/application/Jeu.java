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
    private void tourDeJeu(){
        // Étape 1: Choix des personnages
        choixPersonnages();

        // Étape 2: Choisir le premier personnage
        // (Pour simplifier, nous supposons que chaque joueur doit choisir un personnage à tour de rôle)
        for (int i = 0; i < plateauDeJeu.getNombreDeJoueurs(); i++) {
            System.out.println("Tour du joueur " + (i + 1));

            // Étape 3: Appeler un personnage
            Personnage personnageCourant = plateauDeJeu.getJoueur(i).getPersonnage();

            // Vérifier si le personnage est assassiné
            if (!personnageCourant.estAssassine()) {
                // Vérifier si le personnage est volé
                if (personnageCourant.estVole()) {
                    // Si le personnage est volé, choisir le voleur
                    Personnage voleur = choisirVoleur();
                    plateauDeJeu.getJoueur(i).donnerArgentAuVoleur(voleur);
                    // Étape 4: Percevoir les ressources
                    percevoirRessources(plateauDeJeu.getJoueur(i), personnageCourant);
                } else {
                    // Si le personnage n'est pas volé, percevoir directement les ressources
                    // Étape 4: Percevoir les ressources
                    percevoirRessources(plateauDeJeu.getJoueur(i), personnageCourant);
                }

                // Étape 5: Percevoir les ressources spécifiques
                percevoirRessourcesSpecifiques(plateauDeJeu.getJoueur(i), personnageCourant);

                // Étape 6: Utiliser le pouvoir
                if (utiliserPouvoir(personnageCourant)) {
                    // Si le pouvoir est utilisé, implémenter la logique correspondante
                }

                // Étape 7: Décider de construire
                if (deciderConstruire(plateauDeJeu.getJoueur(i))) {
                    // Si le joueur décide de construire, implémenter la logique de construction
                    construire(plateauDeJeu.getJoueur(i));
                    // Fin de l'activité
                    return;
                }
            }

            // Retour à l'étape 3 pour changer de personnage
        }
    }
    private void choixPersonnages(){}
    private void percevoirRessource(){}
    private void calculDesPoints(){}

    public static void main(String[] args){
        Jeu jeu = new Jeu(new PlateauDeJeu(), 0, new Random());
        jeu.jouer();
    }



}
