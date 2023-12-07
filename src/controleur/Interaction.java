package controleur;

import java.util.InputMismatchException;
import java.util.Scanner;


public class Interaction {
    private static Scanner sc = new Scanner(System.in);

    public static int lireUnEntier() {
        int i = 0;
        boolean continu = true;
        do {
            try {
                i = sc.nextInt();
                continu = false;
            } catch (InputMismatchException e) {
                System.out.print("Veuillez rentrer un chiffre : ");
                sc.next(); // passe l'entier pour �viter de boucler
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
        boolean retour = false;
        boolean saisieValide = false;
        String saisie;

        while (!saisieValide) {
            saisie = lireUneChaine().toLowerCase(); // Lecture de l'entrée utilisateur en minuscules

            // Comparaison de l'entrée avec les réponses attendues
            if (saisie.equals("oui") || saisie.equals("o")) {
                retour = true;
                saisieValide = true;
            } else if (saisie.equals("non") || saisie.equals("n")) {
                retour = false;
                saisieValide = true;
            } else {
                System.out.println("Réponse non reconnue.");
            }
        }

        return retour;
    }

    // renvoie une cha�ne de caractère lue au clavier:
    public static String lireUneChaine() {
        String retour = sc.next();
        return retour;
    }
}
