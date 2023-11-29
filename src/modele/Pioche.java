package modele;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
public class Pioche {
    private ArrayList<Quartier> liste; // Variable de type ArrayList pour stocker les cartes Quartier

    // Constructeur vide qui initialise la liste
    public Pioche() {
        this.liste = new ArrayList<>();
    }

    // Retourne et retire la carte du sommet de la pioche
    public Quartier piocher() {
        if (!liste.isEmpty()) {
            return liste.remove(0);
        } else {
            System.out.println("La pioche est vide.");
            return null;
        }
    }

    // Ajoute un Quartier au bas de la pioche
    public void ajouter(Quartier nouveau) {
        liste.add(nouveau);
    }

    // Retourne le nombre d'éléments dans la pioche
    public int nombreElements() {
        return liste.size();
    }

    // Mélange les cartes dans la pioche de manière aléatoire
    public void melanger() {
        long seed = System.nanoTime(); // Utilisation du nanoTime pour une meilleure randomisation
        Collections.shuffle(liste, new Random(seed));
    }
}