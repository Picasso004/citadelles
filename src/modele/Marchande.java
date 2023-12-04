package modele;

public class Marchande extends Personnage{
    //CONSTRUCTEUR
    public Marchande (){
        super("Marchande", 6, Caracteristiques.MARCHANDE);
    }

    @Override
    public void percevoirRessourcesSpecifiques() {
        int nbQuartiersCommercants =0;
        if (getJoueur() != null){
            Quartier[] cite = getJoueur().getCite();
            for (Quartier quartier : cite){
                if(quartier != null && quartier.getType().equals(Quartier.TYPE_QUARTIERS[3])){
                    nbQuartiersCommercants ++;
                }
            }
        }
        if(getJoueur() != null && !getAssassine()){
            getJoueur().ajouterPieces(nbQuartiersCommercants);
            System.out.println(getNom() + " a reçu " + nbQuartiersCommercants +
                    " pièce(s) d'or supplémentaire(s) pour ses quartiers commerçants.");
        }
    }

    @Override
    public void utiliserPouvoir() {
        if(getJoueur() != null && !getAssassine()){
            getJoueur().ajouterPieces(1);
            System.out.println(getNom() + " a utilisé son pouvoir et a reçu 1 pièce d'or supplémentaire.");
        }
    }
    
}
