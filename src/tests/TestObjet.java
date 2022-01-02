package tests;

import composants.Objet;
import grafix.interfaceGraphique.IG;

import java.util.Arrays;

public class TestObjet {

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

        String message[]= {
                "",
                "Cliquez pour quitter ...",
                ""
        };
        IG.afficherMessage(message);
        Objet.nouveauxObjets();

        int incObjet = 0;
        Objet[] objetsJeu=Objet.nouveauxObjets();
        System.out.println(Arrays.toString(objetsJeu));
        for (int i = 0; i < 18; i++) {

            IG.placerObjetPlateau(objetsJeu[incObjet].getNumeroObjet(), objetsJeu[incObjet].getPosLignePlateau(), objetsJeu[incObjet].getPosColonnePlateau());
            incObjet++;

        }

        IG.miseAJourAffichage();
        IG.attendreClic();
        IG.fermerFenetreJeu();




    }
}
