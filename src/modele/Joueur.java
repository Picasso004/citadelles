package modele;
import java.util.*;
public class Joueur extends Quartier {
    private String nom;
    private int tresor, nbQuartiers;
    private Quartier[] cite;
    private boolean possedeCouronne;
    private ArrayList<Quartier> main;
    public Joueur(String nom) {
        this.nom = nom;
        this.tresor = 0;
        this.nbQuartiers = 0;
        this.possedeCouronne = false;
        this.cite = new Quartier[8];
        this.main = new ArrayList<Quartier>();
    }
    public String getNom() {
        return this.nom;
    }
    public int nbPiece() {
        return this.tresor;
    }
    public int nbQuartiersDansCite() {
        return this.cite.length;
    }
    public Quartier[] getCite() {
        return this.cite;
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
            this.tresor+=t;
        }
    }
    public void retirerPieces(int p) {
        if(p>0 && p<=this.tresor) {
            this.tresor-=p;
        }
    }

    public void ajouterQuartierDansCite(Quartier q) {
        if(this.cite.length<8) {
            this.cite[this.cite.length + 1] = q;
        }
    }
    public boolean PresentDansCite(Quartier v) {
        for(Quartier element :  this.cite) {
            if(v == element) {
                return true;}
        }
        return possedeCouronne;
    }
    public void retirerQuartierDansCite(String quartier) {

    }

}

