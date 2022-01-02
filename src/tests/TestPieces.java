package tests;
import grafix.interfaceGraphique.IG;
import composants.Piece;

import java.util.Arrays;

public class TestPieces {
    /**
     *
     * Fait (19/05/2021 Walid)
     *
     */
    public static void main(String[] args) {
        // Saisie des différents paramètres
        Object parametres[];
        parametres= IG.saisirParametres(); //Cette commande ouvre la fenêtre des paramètres

        System.err.println("Il y a eu " + parametres.length + " paramètres stockés.");

        for (Object parametre : parametres) {
            System.out.println(parametre);
        }
        //On créer la fenêtre de jeu

        int nbjoueur = (int) parametres[0];
        IG.creerFenetreJeu("Ma Démo du labyrinthe", nbjoueur);
        IG.rendreVisibleFenetreJeu();
        IG.miseAJourAffichage();


        String message[]= {
                "",
                "Cliquez pour continuer ...",
                ""
        };
        IG.afficherMessage(message);
        IG.miseAJourAffichage();
        IG.attendreClic();

        // On initialise le chemin aléatoire
        Piece[] plateau = Piece.nouvellesPieces();
        System.out.println(Arrays.toString(plateau));

        int incPiece = 0;
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                IG.changerPiecePlateau(i, j, plateau[incPiece].getModelePiece(), plateau[incPiece].getOrientationPiece());
                incPiece++;
            }
        }
        IG.changerPieceHorsPlateau(plateau[49].getModelePiece(), plateau[49].getOrientationPiece());
        IG.miseAJourAffichage();
        IG.attendreClic();

        // On rotate 4 fois les pièces
        for(int nbRotations = 0;nbRotations<4;nbRotations++){
            for(int i = 0;i<plateau.length;i++){
                plateau[i].rotation();
                IG.miseAJourAffichage();
            }
            IG.changerPieceHorsPlateau(plateau[49].getModelePiece(),plateau[49].getOrientationPiece());
            IG.miseAJourAffichage();
            IG.attendreClic();
        }
        IG.attendreClic();
        IG.fermerFenetreJeu();
    }
}
