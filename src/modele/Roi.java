package modele;

public class Roi extends Personnage {
    // Constructeur
    public Roi() {
        super("Roi", 4, Caracteristiques.ROI);
    }

    // Méthode utiliserPouvoir
    @Override
    public void utiliserPouvoir() {
        System.out.println("Je prends la couronne");
        if (getJoueur() != null) {
            getJoueur().setPossedeCouronne(true);
        }
    }

    // Surcharge de percevoirRessourcesSpecifiques
    @Override
    public void percevoirRessourcesSpecifiques() {
        int nbQuartiersNobles = compterQuartiersNobles();
        if (getJoueur() != null) {
            getJoueur().ajouterPieces(nbQuartiersNobles);
            System.out.println("J'ai ajouté " + nbQuartiersNobles + " pièces à mon trésor.");
        }
    }

    // Méthode privée pour compter les quartiers nobles dans la cité du joueur associé
    private int compterQuartiersNobles() {
        int nbQuartiersNobles = 0;
        if (getJoueur() != null) {
            for (Quartier quartier : getJoueur().getCite()) {
                if (quartier != null && quartier.getType().equals("NOBLE")) {
                    nbQuartiersNobles++;
                }
            }
        }
        return nbQuartiersNobles;
    }
}