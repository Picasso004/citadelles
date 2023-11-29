package modele;

public abstract class Personnage {
    // Attributs
    private String nom;
    private int rang;
    private String caracteristiques;
    private Joueur joueur;
    private boolean assassine;
    private boolean vole;

    // Constructeur
    public Personnage(String nom, int rang, String caracteristiques) {
        this.nom = nom;
        this.rang = rang;
        this.caracteristiques = caracteristiques;
        this.joueur = null;
        this.assassine = false;
        this.vole = false;
    }

    // Accesseurs en lecture
    public String getNom() {
        return this.nom;
    }

    public int getRang() {
        return this.rang;
    }

    public String getCaracteristiques() {
        return this.caracteristiques;
    }

    public Joueur getJoueur() {
        return  this.joueur;
    }

    public boolean getAssassine() {
        return this.assassine;
    }

    public boolean getVole() {
        return this.vole;
    }

    // Accesseurs en écriture
    public void setJoueur(Joueur j) {
        joueur = j;
    }

    public void setVole() {
        vole = true;
    }

    public void setAssassine() {
        assassine = true;
    }

    // Méthodes
    public void ajouterPieces() {
        if (joueur != null && !assassine) {
            joueur.ajouterPieces(2);
        }
    }

    public void ajouterQuartier(Quartier nouveau) {
        if (joueur != null && !assassine) {
            joueur.ajouterQuartierDansMain(nouveau);
        }
    }

    public void construire(Quartier nouveau) {
        if (joueur != null && !assassine) {
            joueur.ajouterQuartierDansCite(nouveau);
        }
    }

    public void percevoirRessourcesSpecifiques() {
        System.out.println("Aucune ressource spécifique.");
    }

    public abstract void utiliserPouvoir();

    public void reinitialiser() {
        joueur = null;
        vole = false;
        assassine = false;
    }
}
