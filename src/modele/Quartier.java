package modele;

public class Quartier {
    private int coutConstruction;
    private String nom, type, caracteristiques;
    public boolean embelli;
    public static final String[] TYPE_QUARTIERS = {"RELIGIEUX", "MILITAIRE", "NOBLE", "COMMERCANT", "MERVEILLE"};
    public Quartier() {
        this.nom = "";
        this.setType("");
        this.coutConstruction = 0;
        this.caracteristiques = "";
        this.embelli = false;
    }
    public Quartier(String nom, String type, int cout) {
        this.nom = nom;
        this.setType(type);
        this.coutConstruction = cout;
        this.caracteristiques = "";
    }
    public Quartier(String nom, String type, int cout, String caracteristiques) {
        this.nom = nom;
        this.setType(type);
        this.coutConstruction = cout;
        this.caracteristiques = caracteristiques;
    }
    public String getNom() {
        return this.nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return this.type;
    }
    public void setType(String type) {
        for(int i=0; i<TYPE_QUARTIERS.length; i++) {
            if(TYPE_QUARTIERS[i].equals(type)) {
                this.type = TYPE_QUARTIERS[i];
                return;
            }
        }
        this.type = "";

    }
    public int getCout() {
        return this.coutConstruction;
    }
    public void setCout(int x) {
        if(1<x && 6>x) {
            this.coutConstruction = x;
        }
        else {
            this.coutConstruction = 0;
        }
    }
    public String getCaracteristiques() {
        return this.caracteristiques;
    }
    public void setCaracteristiques(String c) {
        this.caracteristiques = c;
    }

}
