package modele;

import controleur.Interaction;

import java.util.ArrayList;

public class Magicienne extends Personnage {

    public Magicienne() {
        super("Magicienne", 3, "Peut échanger des cartes de sa main avec un autre joueur ou les remplacer par des cartes de la pioche.");
    }

    @Override
    public void utiliserPouvoir() {
        System.out.println("Voulez-vous échanger vos cartes avec celles d'un autre joueur? (o/n)");
        boolean echangerAvecJoueur = Interaction.lireOuiOuNon();

        if (echangerAvecJoueur) {
            // Afficher tous les joueurs sauf la magicienne elle-même
            System.out.println("Choisissez un joueur (indiquez le numéro) :");
            PlateauDeJeu plateau = getPlateau();
            ArrayList<Joueur> joueurs = new ArrayList<>();
            for (int i = 0; i < plateau.getNombreJoueurs(); i++) {
                if (plateau.getJoueur(i) != null && plateau.getJoueur(i).getPersonnage() != this) {
                    joueurs.add(plateau.getJoueur(i));
                    System.out.println(joueurs.size() + ". " + plateau.getJoueur(i).getNom());
                }
            }

            int choixJoueur = Interaction.lireUnEntier(1, joueurs.size()+1)-1;
            Joueur joueurChoisi = joueurs.get(choixJoueur);

            // Copier les mains des deux joueurs
            ArrayList<Quartier> copieMainMagicienne = new ArrayList<>(getJoueur().getMain());
            ArrayList<Quartier> copieMainJoueur = new ArrayList<>(joueurChoisi.getMain());

            // Vider les mains originales
            getJoueur().getMain().clear();
            joueurChoisi.getMain().clear();

            // Ajouter le contenu des copies aux mains originales
            getJoueur().getMain().addAll(copieMainJoueur);
            joueurChoisi.getMain().addAll(copieMainMagicienne);

            System.out.println("Échange effectué avec " + joueurChoisi.getNom() + ".");
        } else {
            System.out.println("Combien de cartes voulez-vous prendre dans la pioche? (0 pour ne rien faire)");
            int nbCartesPioche = Interaction.lireUnEntier(0, getJoueur().nbQuartiersDansMain()+1)-1;

            if (nbCartesPioche > 0) {
                // Copier la main de la magicienne
                ArrayList<Quartier> copieMainMagicienne = new ArrayList<>(getJoueur().getMain());

                if (nbCartesPioche == getJoueur().nbQuartiersDansMain()) {
                    // Retirer un à un les quartiers de la main originale et les ajouter dans la pioche
                    for (Quartier quartier : copieMainMagicienne) {
                        getPlateau().getPioche().ajouter(quartier);
                    }

                    // Vider la main originale
                    getJoueur().getMain().clear();
                } else {
                    // Faire une copie de la main de la magicienne
                    //ArrayList<Quartier> copieMainMagicienne = new ArrayList<>(getJoueur().getMain());

                    // Demander à l'utilisateur de choisir des cartes à échanger avec la pioche
                    for (int i = 0; i < nbCartesPioche; i++) {
                        System.out.println("Veuillez choisir une carte à retirer de votre main :");
                        int indexCarte = Interaction.lireUnEntier(1, copieMainMagicienne.size()) - 1;

                        Quartier carteRetiree = copieMainMagicienne.remove(indexCarte);
                        getPlateau().getPioche().ajouter(carteRetiree);
                    }

                    // Ajouter nbCartesPioche cartes de la pioche dans la main originale
                    for (int i = 0; i < nbCartesPioche; i++) {
                        getJoueur().ajouterQuartierDansMain(getPlateau().getPioche().piocher());
                    }
                }

                System.out.println("Cartes échangées avec la pioche.");
            }
        }
    }
}