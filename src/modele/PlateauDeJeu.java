package modele;

public class PlateauDeJeu {
    private int nombrePersonnages;
    private int nombreJoueurs;
    private Pioche pioche;
    private Personnage[] listePersonnages;
    private Joueur[] listeJoueurs;

    public PlateauDeJeu() {
        this.nombrePersonnages = 0;
        this.nombreJoueurs = 0;
        this.pioche = new Pioche();  // Assuming you have a Pioche class
        this.listePersonnages = new Personnage[9];
        this.listeJoueurs = new Joueur[9];
    }

    // Accessors
    public int getNombrePersonnages() {
        return this.nombrePersonnages;
    }

    public int getNombreJoueurs() {
        return this.nombreJoueurs;
    }

    public Pioche getPioche() {
        return this.pioche;
    }

    public void setPioche(Pioche p) {
        this.pioche=p;
    }

    public Personnage getPersonnage(int i) {
        if (i >= 0 && i < this.nombrePersonnages) {
            return this.listePersonnages[i];
        } else {
            return null;
        }
    }

    public Joueur getJoueur(int i) {
        if (i >= 0 && i < this.nombreJoueurs) {
            return this.listeJoueurs[i];
        } else {
            return null;
        }
    }

    // Methods
    public void ajouterPersonnage(Personnage personnage) {
        if (personnage != null && this.nombrePersonnages < 9) {
            this.listePersonnages[this.nombrePersonnages++] = personnage;
            personnage.setPlateau(this);
        }
    }

    public void ajouterJoueur(Joueur joueur) {
        if (joueur != null && this.nombreJoueurs < 9) {
            this.listeJoueurs[this.nombreJoueurs++] = joueur;
        }
    }
}
