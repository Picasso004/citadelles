package modele;

import controleur.Interaction;

import java.util.ArrayList;
import java.util.Random;

import static controleur.Interaction.*;

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
                    System.out.println((i+1) + ". " + plateau.getJoueur(i).getNom());
                }
            }

            int choixJoueur = Interaction.lireUnEntier(1, joueurs.size() + 1) - 1;
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
            System.out.println("Combien de cartes voulez-vous prendre dans la pioche?");
            int nbCartesPioche = Interaction.lireUnEntier(1, getJoueur().nbQuartiersDansMain() + 1);

            System.out.println("Voici votre main");
            for (int i =0; i<this.getJoueur().nbQuartiersDansMain();i++){
                System.out.println(i+1 + " ." +this.getJoueur().getMain().get(i));
            }
            System.out.println("Quel carte voulez vous echanger ? " + " ("+nbCartesPioche + " cartes attendues)");
            for(int i=0;i<nbCartesPioche;i++){
                System.out.println("Carte " + i + ":");
                int carteIndex = Interaction.lireUnEntier(1, getJoueur().nbQuartiersDansMain() + 1)-1;
                Quartier carte = this.getJoueur().retirerQuartierDansMain(carteIndex);
                this.getPlateau().getPioche().ajouter(carte);
            }

            // Ajouter nbCartesPioche cartes de la pioche dans la main originale
            for (int i = 0; i < nbCartesPioche; i++) {
                getJoueur().ajouterQuartierDansMain(getPlateau().getPioche().piocher());
            }

            System.out.println(nbCartesPioche + " cartes échangées avec la pioche.");

        }
        System.out.println(BLUE + "\nVoici votre main :");
        if(this.getJoueur().nbQuartiersDansMain() > 0) {
            ArrayList<Quartier> main = this.getJoueur().getMain();
            for(int k = 0; k < main.size();k++){
                System.out.println((k+1) + ". " + main.get(k).getNom());
            }
            System.out.println(RESET);
        }
        else {
            System.out.println(RED + "Votre main est vide !" + RESET);
        }
    }

    @Override
    public void utiliserPouvoirAvatar() {
        if (!getAssassine()) {
            System.out.println("Le Pouvoir Avatar de la Magicienne est Activé!!");
            Random random = new Random();
            boolean echangerAvecJoueur = random.nextBoolean();

            if (echangerAvecJoueur) {
                System.out.println("La Magicienne a décidé d'echanger avec un joueur");
                // Afficher tous les joueurs sauf la magicienne elle-même
                PlateauDeJeu plateau = getPlateau();
                ArrayList<Joueur> joueurs = new ArrayList<>();
                for (int i = 0; i < plateau.getNombreJoueurs(); i++) {
                    if (plateau.getJoueur(i) != null && plateau.getJoueur(i).getPersonnage() != this) {
                        joueurs.add(plateau.getJoueur(i));
                        System.out.println(joueurs.size() + ". " + plateau.getJoueur(i).getNom());
                    }
                }

                if (!joueurs.isEmpty()) {
                    // Choix aléatoire d'un joueur
                    int choixJoueur = random.nextInt(joueurs.size());
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
                    System.out.println("Il n'y a pas d'autres joueurs pour effectuer un échange.");

                }
            } else {
                System.out.println("La Magicienne a décidé d'echanger avec la pioche");
                int nbCartesPioche = random.nextInt(1,getJoueur().nbQuartiersDansMain() + 1);
                // Copier la main de la magicienne
                ArrayList<Quartier> mainMagicienne = this.getJoueur().getMain();

                // Échanger de manière aléatoire avec la pioche
                for (int i = 0; i < nbCartesPioche; i++) {
                    int indexCarte = random.nextInt(mainMagicienne.size());
                    Quartier carteRetiree = mainMagicienne.remove(indexCarte);
                    getPlateau().getPioche().ajouter(carteRetiree);
                }

                // Ajouter nbCartesPioche cartes de la pioche dans la main originale
                for (int i = 0; i < nbCartesPioche; i++) {
                    getJoueur().ajouterQuartierDansMain(getPlateau().getPioche().piocher());
                }

                System.out.println("La Magicienne a échangée " +  nbCartesPioche + " avec la pioche.");
            }

        }
    }
}
