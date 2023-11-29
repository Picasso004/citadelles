package modele;
import java.util.*;
public class Joueur extends Quartier {
    private String nom;
    private int trésor, nbQuartiers;
    private Quartier[] cité;
    private boolean possedeCouronne;
    private ArrayList<Quartier> main;
    public Joueur(String nom) {
        this.nom = nom;
        this.trésor = 0;
        this.nbQuartiers = 0;
        this.possedeCouronne = false;
        this.cité = new Quartier[8];
        this.main = new ArrayList<Quartier>();
    }
    public String getNom() {
        return this.nom;
    }
    public int nbPiece() {
        return this.trésor;
    }
    public int nbQuartiersDansCite() {
        return this.cité.length;
    }
    public Quartier[] getCite() {
        return this.cité;
    }
    public ArrayList<Quartier> getMain() {
        return this.main;
    }
    public int nbQuartiersDansMain() {
        return ((CharSequence) this.main).length();
    }
    public boolean getPossedeCouronne() {
        return this.possedeCouronne;
    }
    public void setPossedeCouronne(boolean b){
        this.possedeCouronne = b;
    }
    public void ajouterPieces(int t) {
        if(t>0) {
            this.trésor+=t;
        }
    }
    public void retirerPieces(int p) {
        if(p>0 && p<=this.trésor) {
            this.trésor-=p;
        }
    }

    public void ajouterQuartierDansCite(Quartier q) {
        if(this.cité.length<8) {
            this.cité[this.cité.length + 1] = q;
        }
    }
    public boolean PresentDansCite(Quartier v) {
        for(Quartier element :  this.cité) {
            if(v == element) {
                return true;}
        }
        return possedeCouronne;
    }
    public void retirerQuartierDansCite(String quartier) {

    }

}












//public int ajouterPiece(int nbPieces) {

//}
