package modele;

import java.util.List;

public class Architecte extends Personnage{
    public Architecte(){
        super("Architecte",7,Caracteristiques.ARCHITECTE);
    }

    public void utiliserPouvoir() {
        if (getJoueur() != null && !getAssassine()) {
            // L'architecte pioche deux cartes quartier supplémentaires
            for (int i = 0; i < 2; i++) {
                Quartier cartePiochee = getPlateau().getPioche().piocher();
                if (cartePiochee != null) {
                    getJoueur().ajouterQuartierDansMain(cartePiochee);
                }
            }
            System.out.println(getNom() + " a utilisé son pouvoir et a pioché deux cartes quartier supplémentaires.");
        }
    }
}
