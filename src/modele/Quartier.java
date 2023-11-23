package modele;

public class Quartier {
    private int coutConstruction;
    private String nom, type, caractéristiques;
    public boolean embelli;
    public static final String[] TYPE_QUARTIERS = {"Religieux", "Militaire", "Noble", "Commerçant", "Merveille"};
    public Quartier() {
        this.nom = "";
        this.type = "";
        this.coutConstruction = 0;
        this.caractéristiques = "";
        this.embelli = false;
    }
    public Quartier(String nom, String type, int cout) {
        this.nom = nom;
        this.type = type;
        this.coutConstruction = cout;
        this.caractéristiques = "";
    }
    public Quartier(String nom, String type, int cout, String caractéristiques) {
        this.nom = nom;
        this.type = type;
        this.coutConstruction = cout;
        this.caractéristiques = caractéristiques;
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
    public void setType(String Type) {
        String val = "";
        for(String element :  Quartier.TYPE_QUARTIERS) {
            if(Type == element) {
                this.type = Type;
            }
        }
        this.type = val;
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
    public String getCaractéristiques() {
        return this.caractéristiques;
    }
    public void setCaractéristiques(String c) {
        this.caractéristiques = c;
    }

}
