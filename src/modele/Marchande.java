package modele;

import java.util.Random;

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
            int quartiersCommercants = 0;
            for(Quartier quartier : this.getJoueur().getCite()){
                if (quartier != null && quartier.getType().equals("COMMERCANT")){
                    quartiersCommercants += 1;
                }
            }
            getJoueur().ajouterPieces(quartiersCommercants);
            System.out.println("La marchande a utilisé son pouvoir et a reçu " + quartiersCommercants + " pièce(s) d'or supplémentaire.");
        }
    }

    @Override
    public void utiliserPouvoirAvatar() {
        if (!getAssassine()) {
            System.out.println("Le pouvoir de la Marchande est activé!");
            int quartiersCommercants =0;
            for (Quartier quartier : this.getJoueur().getCite()){
                if (quartier != null && quartier.getType().equals("COMMERCANT")){
                    quartiersCommercants +=1;
                }
            }
            getJoueur().ajouterPieces(quartiersCommercants);
            System.out.println("La marchande a utilisé son pouvoir et a reçu " + quartiersCommercants + " pièce(s) d'or supplémentaire.");

        } else {
            System.out.println("La Marchande ne peut pas utiliser son pouvoir Avatar car elle est assassinée.");
        }

    }

}
