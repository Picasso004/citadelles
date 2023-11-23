package modele;

import java.util.ArrayList;
public class Joueur {
    private String nom;
    private int tresor;
    private Quartier [] cite;
    private int nbQuartiers;
    private ArrayList<Quartier> main;
    private boolean possedeCouronne = false;

    public Joueur(String nom){
        this.nom = nom;
        this.tresor=0;
        this.nbQuartiers = 0;
    }
}
