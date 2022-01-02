package tests;
import com.sun.tools.javac.Main;
import grafix.interfaceGraphique.IG;

public class MaDemoIG {

    public static void main(String[] args) {
        // Saisie des différents paramètres
        Object parametres[];
        parametres=IG.saisirParametres(); //Cette commande ouvre la fenêtre des paramètres

        System.err.println("Il y a eu " + parametres.length + " paramètres stockés.");

        for (Object parametre : parametres) {
            System.out.println(parametre);
        }

        //parametre[0] donne le nombre de joueurs
        //parametre[1] donne le nom du 1er joueur
        //parametre[2] donne le type du 1er joueur
        //parametre[3] donne 0 (sûrement le skin du 1er joueur)
        //parametre[4] donne le nom du 2em joueur
        //parametre[5] donne le type du 2em joueur
        //parametre[6] donne 1 (sûrement le skin du 2em joueur)
        //parametre[7] donne le nom du 3em joueur
        //parametre[8] donne le type du 3em joueur
        //parametre[9] donne 2 (sûrement le skin du 3em joueur)


        //On créer la fenêtre de jeu

        int nbjoueur = (int) parametres[0];
        IG.creerFenetreJeu("Ma Démo du labyrinthe", nbjoueur);
        IG.rendreVisibleFenetreJeu();

        //Affichage des caractéristiques des joueurs
        int numImageJoueur0 = (Integer)parametres[3];
        String nomJoueur0= (String)parametres[1];
        String categorieJoueur0= (String)parametres[2];
        IG.changerImageJoueur(0, numImageJoueur0 );
        IG.changerNomJoueur(0, nomJoueur0+" ("+categorieJoueur0+")");

        int numImageJoueur1 = (Integer)parametres[6];
        String nomJoueur1= (String)parametres[4];
        String categorieJoueur1= (String)parametres[5];
        IG.changerImageJoueur(1, numImageJoueur1);
        IG.changerNomJoueur(1, nomJoueur1 +" ("+categorieJoueur1+")");

        if (nbjoueur == 3) {
            int numImageJoueur2 = (Integer)parametres[9];
            String nomJoueur2= (String)parametres[7];
            String categorieJoueur2= (String)parametres[8];
            IG.changerImageJoueur(2, numImageJoueur2);
            IG.changerNomJoueur(2, nomJoueur2 +" ("+categorieJoueur2+")");
        }
        IG.miseAJourAffichage();

        //Placement des joueurs
        //IG.placerJoueurSurPlateau(0, 3, 0);
        IG.placerJoueurPrecis(0, 3, 0, 1, 0);
        //IG.placerJoueurSurPlateau(1, 3, 6);
        IG.placerJoueurPrecis(1, 3, 6, 1, 2);
        IG.miseAJourAffichage();


        //On charge les objets
        int numObjet=0;
        int c = 0;
        int l = 0;

        while(numObjet<18){
            c = (numObjet / 7);
            l = (numObjet % 7);
            IG.placerObjetPlateau(numObjet, c, l);
            numObjet++;
        }

        //permet de donner les objets aux joueurs
        for (int i=0 ;i<6;i++){
            IG.changerObjetJoueur(0,i,i);
            IG.changerObjetJoueur(1,i+6,i);
            IG.changerObjetJoueur(2,i+12,i);
        }


        IG.miseAJourAffichage();


        String message[]= {
                "",
                "Bonjour !",
                "Cliquez pour continuer ...",
                ""
        };
        IG.afficherMessage(message);
        IG.miseAJourAffichage();
        IG.attendreClic();


        //créer un nouveau message
        message = new String[]{
                "",
                "La partie va bientôt commencer !",
                "Clique ici pour démarrer la partie ...",
                ""
        };
        IG.afficherMessage(message);
        IG.miseAJourAffichage();
        IG.attendreClic();

        message = new String[]{
                "",
                "Après le clique 1",
                "Cliquez pour continuer...",
                ""
        };
        IG.placerJoueurPrecis(0,3,0,1,1);
        IG.placerJoueurPrecis(1,3,6,1,1);
        IG.placerBilleSurPlateau(3,0,1,0,0);
        IG.placerBilleSurPlateau(3,6,1,2,0);
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                IG.changerPiecePlateau(i,j,0,1);
                IG.changerPieceHorsPlateau(0,1);
            }
        };
        IG.enleverObjetPlateau(0,0);
        IG.changerObjetJoueurAvecTransparence(0,0,0);
        IG.afficherMessage(message);
        IG.miseAJourAffichage();         //mettre le maj apres avoir ajouter tout les changements
        IG.attendreClic();              //mettre attendre le clic sinon le programme continue


        message = new String[]{
                "",
                "Après le clique 2",
                "Cliquez pour continuer...",
                ""
        };
        IG.placerJoueurPrecis(0,3,0,1,2);
        IG.placerJoueurPrecis(1,3,6,1,0);
        IG.placerBilleSurPlateau(3,0,1,1,0);
        IG.placerBilleSurPlateau(3,6,1,1,0);
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                IG.changerPiecePlateau(i,j,0,2);
                IG.changerPieceHorsPlateau(0,2);
            }
        };
        IG.changerObjetJoueurAvecTransparence(0,1,1);
        IG.enleverObjetPlateau(0,1);
        IG.afficherMessage(message);
        IG.miseAJourAffichage();
        IG.attendreClic();

        message = new String[]{
                "",
                "Après le clique 3",
                "Cliquez pour continuer...",
                ""
        };
        IG.placerJoueurPrecis(0,3,1,1,0);
        IG.placerJoueurPrecis(1,3,5,1,2);
        IG.placerBilleSurPlateau(3,0,1,2,0);
        IG.placerBilleSurPlateau(3,6,1,0,0);
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                IG.changerPiecePlateau(i,j,0,3);
                IG.changerPieceHorsPlateau(0,3);
            }
        };
        IG.enleverObjetPlateau(0,2);
        IG.changerObjetJoueurAvecTransparence(0,2,2);
        IG.afficherMessage(message);
        IG.miseAJourAffichage();
        IG.attendreClic();

        message = new String[]{
                "",
                "Après le clique 4",
                "Cliquez pour continuer...",
                ""
        };
        IG.placerJoueurPrecis(0,3,1,1,1);
        IG.placerJoueurPrecis(1,3,5,1,1);
        IG.placerBilleSurPlateau(3,1,1,0,0);
        IG.placerBilleSurPlateau(3,5,1,2,0);
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                IG.changerPiecePlateau(i,j,0,0);
                IG.changerPieceHorsPlateau(0,0);
            }
        };
        IG.changerObjetJoueurAvecTransparence(0,3,3);
        IG.enleverObjetPlateau(0,3);
        IG.afficherMessage(message);
        IG.miseAJourAffichage();
        IG.attendreClic();

        message = new String[]{
                "",
                "Cliquez sur une flèche",
                "pour quitter !",
                ""
        };
        IG.afficherGagnant(0);
        IG.afficherMessage(message);
        IG.miseAJourAffichage();
        IG.attendreChoixEntree();

        message = new String[]{
                "",
                "Fermeture de la fenêtre",
                "dans 2 secondes",
                ""
        };
        IG.afficherMessage(message);
        IG.pause(2000);  //mettre le temps en milliseconde
        IG.fermerFenetreJeu();
        IG.miseAJourAffichage();





    }
}

