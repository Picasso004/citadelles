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
    public int nbPieces() {
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
        return this.main.size();
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
    public boolean quartierPresentDansCite(String v) {
        for(Quartier quartier :  this.cite) {
            if(quartier != null && v.equals(quartier.getNom())) {
                return true;}
        }
        return false;
    }
    public Quartier retirerQuartierDansCite(String q) {
        for(int i = 0; i < this.cite.length; i++){
            Quartier quartier = this.cite[i];
            if(quartier != null && q.equals(quartier.getNom())){
                this.cite[i]=null;
                this.nbQuartiers--;
                return quartier;
            }
        }
        return null;
    }

    public void ajouterQuartierDansMain(Quartier q){
        this.main.add(q);
    }
    public Quartier retirerQuartierDansMain(){
        if(!this.main.isEmpty()){
            Random generateur = new Random();
            int numeroHasard = generateur.nextInt(this.nbQuartiersDansMain());
            return this.main.remove(numeroHasard);
        }
        return null;
    }
    public void reinitialiser(){
        this.tresor =0;
        this.main.clear();
        this.nbQuartiers = 0;
    }


}