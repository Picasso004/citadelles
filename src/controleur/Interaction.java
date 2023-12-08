package controleur;

import java.util.InputMismatchException;
import java.util.Scanner;


public class Interaction {
    private static Scanner sc = new Scanner(System.in);

    // Pour l'affichage des couleurs dans le terminal
    public static String RESET = "\u001B[0m";
    public static String RED = "\u001B[31m";
    public static String GREEN = "\u001B[32m";
    public static String YELLOW = "\u001B[33m";
    public static String BLUE = "\u001B[34m";
    public static String MAGENTA = "\u001B[35m";


    public static int lireUnEntier() {
        int i = 0;
        boolean continu = true;
        do {
            try {
                i = sc.nextInt();
                //sc.nextLine(); // Nettoie le retour à la ligne restant dans le buffer
                continu = false;
            } catch (InputMismatchException e) {
                System.out.print("Veuillez rentrer un chiffre : ");
                sc.next(); // passe l'entier pour éviter de boucler
            }
        } while(continu);
        return i;
    }

    // renvoie un entier lu au clavier compris dans l'intervalle [borneMin, borneMax[
    public static int lireUnEntier(int borneMin, int borneMax) {
        int i = 0;
        boolean valide = false;

        // Tant que l'entrée n'est pas valide, demander à l'utilisateur de saisir un entier
        while (!valide) {
            i = lireUnEntier(); // Utilisation de la méthode lireUnEntier() précédemment définie

            if (i >= borneMin && i < borneMax) {
                valide = true; // Sortir de la boucle si l'entier est dans la plage spécifiée
            } else {
                System.out.println("Nombre hors de la plage spécifiée. Réessayez.");
            }
        }
        return i;
    }


    // lit les réponses "oui", "non", "o" ou "n" et renvoie un booléen
    public static boolean lireOuiOuNon() {
        boolean retour = true;
        boolean continu = true;
        do {
            String i = lireUneChaine().toLowerCase();
            if (i.equals("oui") || i.equals("o")){
                continu = false;
            }
            else if (i.equals("non") || i.equals("n")){
                retour = false;
                continu = false;
            }
            else
                System.out.print("Veuillez rentrer oui (o) ou non (n) : ");
        }while(continu);
        return retour;
    }

    // renvoie une cha�ne de caractère lue au clavier:
    public static String lireUneChaine() {
        String retour = null;
        do {
            retour = sc.next();
        } while(retour==null);
        return retour;
    }
}
