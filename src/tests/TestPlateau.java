package tests;
import composants.Plateau;
import grafix.interfaceGraphique.IG;
import composants.Piece;

import java.util.Arrays;

public class TestPlateau {
    // Main permettant de tester testPlateau
    public static void main(String[] args) {
        // Saisie des différents paramètres
        Object[] parametres;
        parametres = IG.saisirParametres(); //Cette commande ouvre la fenêtre des paramètres
        //On créer la fenêtre de jeu

        int nbjoueur = (int) parametres[0];
        IG.creerFenetreJeu("Ma Démo du labyrinthe", nbjoueur);
        IG.rendreVisibleFenetreJeu();
        IG.miseAJourAffichage();


        String[] message = {
                "",
                "Cliquez pour continuer ...",
                ""
        };
        IG.afficherMessage(message);
        IG.miseAJourAffichage();
        IG.attendreClic();

        //On créé le plateau
        Plateau plateau=new Plateau();
        Piece pieceHorsPlateau = plateau.placerPiecesAleatoierment();

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                IG.changerPiecePlateau(i, j, plateau.getPiece(i, j).getModelePiece(), plateau.getPiece(i, j).getOrientationPiece());
            }
        }
        IG.changerPieceHorsPlateau(pieceHorsPlateau.getModelePiece(), pieceHorsPlateau.getOrientationPiece());
        message = new String[]{
                "",
                "Cliquez pour continuer ...",
                ""
        };
        IG.afficherMessage(message);
        IG.miseAJourAffichage();
        IG.attendreClic();

        System.out.println("La liste des chemins trouvés à partir de la case (3,3) : \n");
        int [][] cheminLePlusLong = new int[][]{null};
        for (int ligne = 0; ligne < 7; ligne++) {
            for (int colonne = 0; colonne < 7; colonne++) {
                int[][] chemin = plateau.calculeChemin(3, 3, ligne, colonne);
                if (chemin != null) {
                    if (chemin.length >= cheminLePlusLong.length) {
                        cheminLePlusLong = chemin;
                    }
                    System.out.println("Chemin entre les cases (3,3) et ("+ligne+","+colonne+") : " + Arrays.deepToString(chemin));
                }
            }
        }
        for (int i = 0; i < cheminLePlusLong.length; i++) {
                IG.placerBilleSurPlateau(cheminLePlusLong[i][0], cheminLePlusLong[i][1], 1, 1, 2);
        }


        IG.miseAJourAffichage();
        IG.attendreClic();
        IG.fermerFenetreJeu();
        System.exit(0); // Pour vraiment stopper le programme
    }
}

