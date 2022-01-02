package tests;

import composants.Objet;
import composants.Piece;
import composants.Plateau;
import joueurs.Joueur;
import grafix.interfaceGraphique.IG;
import partie.ElementsPartie;

import javax.swing.text.PlainDocument;
import java.util.Arrays;

public class TestElementPartie {
    public static void main(String[] args) throws CloneNotSupportedException {
        Object parametresJeu[];
        parametresJeu=IG.saisirParametres();
        int  nbJoueurs=((Integer)parametresJeu[0]).intValue();
        IG.creerFenetreJeu("- TestElementsPartie",nbJoueurs);
        Joueur joueurs[]=Joueur.nouveauxJoueurs(parametresJeu);
        ElementsPartie elementsPartie=new ElementsPartie(joueurs);

        for (Joueur joueur : joueurs) {
            IG.changerNomJoueur(joueur.getNumJoueur(), joueur.getNomJoueur() + " (" + joueur.getCategorie() + ")");
            IG.changerImageJoueur(joueur.getNumJoueur(), joueur.getNumeroImagePersonnage());
            IG.placerJoueurPrecis(joueur.getNumJoueur(), joueur.getPosLigne(), joueur.getPosColonne(), 0, 0);
        }

        IG.rendreVisibleFenetreJeu();


        //Piece[] plateau = Piece.nouvellesPieces();
        //System.out.println(Arrays.toString(plateau));

        Plateau plateau = elementsPartie.getPlateau();
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                IG.changerPiecePlateau(i, j, plateau.getPiece(i, j).getModelePiece(), plateau.getPiece(i, j).getOrientationPiece());
            }
        }
        IG.changerPieceHorsPlateau(elementsPartie.getPieceLibre().getModelePiece(), elementsPartie.getPieceLibre().getOrientationPiece());
        IG.miseAJourAffichage();

        //on place les objets sur le plateau
        String[] message = new String[]{
                "",
                "Cliquez pour continuer...",
                "",
                ""
        };
        IG.afficherMessage(message);
        IG.miseAJourAffichage();
        IG.attendreClic();
        Objet.nouveauxObjets();

        int incObjet = 0;
        Objet[] objetsJeu=Objet.nouveauxObjets();

        for (int i = 0; i < 18; i++) {

            IG.placerObjetPlateau(objetsJeu[incObjet].getNumeroObjet(), objetsJeu[incObjet].getPosLignePlateau(), objetsJeu[incObjet].getPosColonnePlateau());
            incObjet++;

        }
        //ElementsPartie elementsPartie=new ElementsPartie(joueurs, objetsJeu, plateau, );
        elementsPartie.attribuerObjetsAuxJoueurs(); // J'ai mis la méthode en public car sinon je ne pouvais pas l'utiliser.
        /*for (int i = 0; i < nbJoueurs; i++) {
            System.out.println("--------------------------------------- Joueur" + i + "--------------------------");
            System.out.println(Arrays.toString(joueurs[i].getObjetsJoueur()));
        }*/
        for (int i = 0; i < nbJoueurs; i++) {
            for (int j = 0; j < joueurs[i].getObjetsJoueur().length; j++) {
                IG.changerObjetJoueur(i, joueurs[i].getObjetsJoueur()[j].getNumeroObjet(), j);
            }
        }
        message = new String[]{
                "",
                "Choisissez une entrée.",
                "",
                ""
        };
        IG.afficherMessage(message);
        IG.miseAJourAffichage();

        while (true) {
            int choixEntree = IG.attendreChoixEntree();
            int choixOrientation = IG.recupererOrientationPieceHorsPlateau();
            elementsPartie.insertionPieceLibre(choixEntree);
            IG.deselectionnerFleche();
            IG.miseAJourAffichage();
        }

        /*
        //int choixEntree = IG.attendreChoixEntree();
        elementsPartie.insertionPieceLibre(IG.attendreChoixEntree());

        IG.miseAJourAffichage();
        //choixEntree = IG.attendreChoixEntree();
        elementsPartie.insertionPieceLibre(IG.attendreChoixEntree());
        message = new String[]{
                "",
                "Choisissez une entrée.",
                "",
                ""
        };
        IG.afficherMessage(message);
        IG.miseAJourAffichage();
        //choixEntree = IG.attendreChoixEntree();
        elementsPartie.insertionPieceLibre(IG.attendreChoixEntree());
        message = new String[]{
                "",
                "Choisissez une entrée.",
                "",
                ""
        };
        IG.afficherMessage(message);
        IG.miseAJourAffichage();
        //choixEntree = IG.attendreChoixEntree();
        elementsPartie.insertionPieceLibre(IG.attendreChoixEntree());
        message = new String[]{
                "",
                "C'est terminé ! ",
                "",
                ""
        };
        IG.afficherMessage(message);
        IG.miseAJourAffichage();*/



    }
}
