package modele;

import java.util.Random;

public class Eveque extends Personnage {

    // Constructeur
    public Eveque() {
        super("Eveque", 5, Caracteristiques.EVEQUE);
    }
    public Eveque(String nom, int rang, String caracteristiques) {
        super(nom, rang, caracteristiques);
    }

    // Méthode spécifique à l'Évêque pour utiliser son pouvoir
    @Override
    public void utiliserPouvoirAvatar() {
        if (!getAssassine()) {
            System.out.println("Le pouvoir de l'Évêque Avatar est activé!");

            if (getRang() == 8) {
                protegerDesPouvoirs();
            }
        } else {
            System.out.println("L'Évêque ne peut pas utiliser son pouvoir Avatar car il est assassiné.");
        }
    }
    public void percevoirRessourcesSpecifiques() {
        int nombreQuartiersReligieux =0;
        if (getJoueur() != null){
            Quartier[] cite = getJoueur().getCite();
            for (Quartier quartier : cite){
                if(quartier != null && quartier.getType().equals(Quartier.TYPE_QUARTIERS[0])){
                    nombreQuartiersReligieux ++;
                }
            }
        }
        if(getJoueur() != null && !getAssassine()) {
            getJoueur().ajouterPieces(nombreQuartiersReligieux);
            System.out.println(getNom() + " a reçu " + nombreQuartiersReligieux +
                    " pièce(s) d'or supplémentaire(s) pour ses quartiers commerçants.");
        }
    }

    public void utiliserPouvoir() {
        if (getRang() == 8) {
            protegerDesPouvoirs();
        } else {
            System.out.println("L'évêque n'a pas de pouvoir spécial pour ce rang.");
        }
    }

    // Méthode spécifique à l'Évêque pour se protéger des pouvoirs des personnages de rang 8
    private void protegerDesPouvoirs() {
        System.out.println("L'évêque est protégé des pouvoirs des personnages de rang 8.");
    }

    // Méthode spécifique à l'Évêque pour recevoir une pièce d'or supplémentaire par quartier religieux
    public void recevoirPieceBonus(int nombreQuartiersReligieux) {
        if (nombreQuartiersReligieux > 0) {
            if (getJoueur() != null && !getAssassine()) {
                getJoueur().ajouterPieces(nombreQuartiersReligieux);
                System.out.println("L'évêque a reçu " + nombreQuartiersReligieux + " pièce(s) d'or supplémentaire(s) pour ses quartiers religieux.");
            } else {
                System.out.println("L'évêque ne peut pas recevoir de pièce bonus car il est assassiné ou n'est pas associé à un joueur.");
            }
        } else {
            System.out.println("L'évêque ne peut pas recevoir de pièce bonus car le nombre de quartiers religieux est invalide.");
        }
    }

}

