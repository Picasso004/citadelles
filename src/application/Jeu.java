package application;

import controleur.Interaction;
import modele.*;

import java.util.*;

public class Jeu {
    private PlateauDeJeu plateauDeJeu;
    private int numeroConfiguration;
    private Random generateur;

    public Jeu() {
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
    private void choixPersonnages() {
        System.out.println("Choix des personnages :");

        for (Joueur joueur : plateauDeJeu.getListeJoueurs()) {
            ArrayList<Personnage> personnagesRestants = new ArrayList<>(List.of(plateauDeJeu.getListePersonnages()));

            // Écarte deux cartes face cachée et une face visible
            Collections.shuffle(personnagesRestants);
            Personnage carteVisible = personnagesRestants.remove(0);
            Personnage carteCachee1 = personnagesRestants.remove(0);
            Personnage carteCachee2 = personnagesRestants.remove(0);

            System.out.println("Le personnage \"" + carteVisible.getNom() + "\" est écarté face visible");
            System.out.println("Le personnage \"" + carteCachee1.getNom() + "\" est écarté face cachée");
            System.out.println("Le personnage \"" + carteCachee2.getNom() + "\" est écarté face cachée");

            joueur.setPersonnage(choisirPersonnage(personnagesRestants));
        }
    }

    private Personnage choisirPersonnage(ArrayList<Personnage> personnagesRestants) {
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
    }

    public static void main(String[] args){
        Jeu jeu = new Jeu();
        jeu.jouer();
    }
}