package modele;
import java.util.ArrayList;
import java.util.Random;

public class Joueur {

    //Attributs
    private String nom;
    private int tresor;
    private int nbQuartiers;
    private Quartier[] cite;
    private boolean possedeCouronne;
    private ArrayList<Quartier> main;

    protected Personnage monPersonnage;
    //test de simulation
    private boolean simule;
    // fin de ligne simulation

    //Constructeur
    public Joueur(String nom) {
        this.nom = nom;
        this.tresor = 0;
        this.nbQuartiers = 0;
        this.possedeCouronne = false;
        this.cite = new Quartier[8];
        this.main = new ArrayList<>();
        this.monPersonnage = null;
        this.simule = false;
    }

    // Accesseurs en lecture
    public String getNom() {
        return this.nom;
    }

    public int nbPieces() {
        return this.tresor;
    }

    public int nbQuartiersDansCite() {
        return this.nbQuartiers;
    }

    public Quartier[] getCite() {
        return this.cite;
    }

    public ArrayList<Quartier> getMain() {
        return this.main;
    }

    public boolean isSimule() {
        return simule;
    }
    public void setSimule(boolean simule) {
        this.simule = simule;
    }



    public int nbQuartiersDansMain() {
        return this.main.size();
    }

    public boolean getPossedeCouronne() {
        return this.possedeCouronne;
    }

    // Accesseur en écriture
    public void setPossedeCouronne(boolean b) {
        this.possedeCouronne = b;
    }


    // Méthodes
    public Personnage getPersonnage(){
        return this.monPersonnage;
    }
    public void ajouterPieces(int t) {
        if (t > 0) {
            this.tresor += t;
        }
    }

    public void retirerPieces(int p) {
        if (p > 0 && p <= this.tresor) {
            this.tresor -= p;
        }
    }

    public void ajouterQuartierDansCite(Quartier q) {
        if (this.nbQuartiers < 8) {
            this.cite[this.nbQuartiers] = q;
            this.nbQuartiers++;
        }
    }

    public boolean quartierPresentDansCite(String v) {
        for (Quartier quartier : this.cite) {
            if (quartier != null && v.equals(quartier.getNom())) {
                return true;
            }
        }
        return false;
    }

    public Quartier retirerQuartierDansCite(String q) {
        for (int i = 0; i < this.nbQuartiers; i++) {
            Quartier quartier = this.cite[i];
            if (quartier != null && q.equals(quartier.getNom())) {
                this.cite[i] = null;
                this.nbQuartiers--;
                return quartier;
            }
        }
        return null;
    }

    public void ajouterQuartierDansMain(Quartier q) {
        this.main.add(q);
    }

    public Quartier retirerQuartierDansMain() {
        if (!this.main.isEmpty()) {
            Random generateur = new Random();
            int numeroHasard = generateur.nextInt(this.nbQuartiersDansMain());
            return this.main.remove(numeroHasard);
        }
        return null;
    }
    public Quartier retirerQuartierDansMain(int index) {
        if (!this.main.isEmpty()) {
            return this.main.remove(index);
        }
        return null;
    }

    public void reinitialiser() {
        this.tresor = 0;
        this.main.clear();
        this.nbQuartiers = 0;
    }

    public void setPersonnage(Personnage personnage) {
        this.monPersonnage=personnage;
    }
}