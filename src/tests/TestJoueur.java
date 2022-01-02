package tests;

import composants.Piece;
import composants.Plateau;
import grafix.interfaceGraphique.IG;
import joueurs.Joueur;

public class TestJoueur {
    public static void main(String[] args) {
        // Saisie des différents paramètres
        Object[] parametres;
        parametres= IG.saisirParametres();

        // Création du plateau
        Plateau plateau=new Plateau();
        Piece pieceHorsPlateau = plateau.placerPiecesAleatoierment();


        // Initialisation des joueurs et affichage de la fenêtre
        Joueur[] joueurs=Joueur.nouveauxJoueurs(parametres);
        IG.creerFenetreJeu("Ma Démo du labyrinthe", (Integer) parametres[0]);
        IG.rendreVisibleFenetreJeu();
        for (Joueur joueur : joueurs) {
            IG.changerNomJoueur(joueur.getNumJoueur(), joueur.getNomJoueur() + " (" + joueur.getCategorie() + ")");
            IG.changerImageJoueur(joueur.getNumJoueur(), joueur.getNumeroImagePersonnage());
            IG.placerJoueurPrecis(joueur.getNumJoueur(), joueur.getPosLigne(), joueur.getPosColonne(), 0, 0);
        }

        // Mise en place des pièces du plateau
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                IG.changerPiecePlateau(i, j, plateau.getPiece(i, j).getModelePiece(), plateau.getPiece(i, j).getOrientationPiece());
            }
        }
        IG.changerPieceHorsPlateau(pieceHorsPlateau.getModelePiece(), pieceHorsPlateau.getOrientationPiece());

        String[] message= {
                "",
                "",
                "Cliquez pour continuer ...",
                ""
        };
        IG.afficherMessage(message);
        IG.miseAJourAffichage();
        IG.attendreClic();


        // Déplacement des joueurs
        for (int i = 0; i < joueurs.length; i++) {
            message = new String[]{
                    "",
                    "Au tour de " + joueurs[i].getNomJoueur(),
                    "Sélectionner une case ...",
                    ""
            };
            IG.afficherMessage(message);
            IG.miseAJourAffichage();
            int[] test = joueurs[i].choisirCaseArrivee(null);
            while (plateau.calculeChemin(joueurs[i].getPosLigne(), joueurs[i].getPosColonne(), test[0], test[1]) == null) {
                test = joueurs[i].choisirCaseArrivee(null);
            }
            int[][] chemin = plateau.calculeChemin(joueurs[i].getPosLigne(), joueurs[i].getPosColonne(), test[0], test[1]);
            for (int j = 0; j < chemin.length; j++) {
                IG.placerBilleSurPlateau(chemin[j][0], chemin[j][1], 1, 1, 1);
            }
            IG.placerJoueurPrecis( i, test[0], test[1], 0, 0);
            IG.miseAJourAffichage();
        }


        IG.attendreClic();



    }
}
