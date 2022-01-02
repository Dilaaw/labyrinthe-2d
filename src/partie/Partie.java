package partie;

import composants.Objet;
import composants.Piece;
import composants.Plateau;
import grafix.interfaceGraphique.IG;
import joueurs.Joueur;
import joueurs.JoueurOrdinateur;

import java.util.Arrays;

public class Partie {
    static double version=0.0;


    private ElementsPartie elementsPartie; // Les éléments de la partie.

    /**
     *
     * A Faire (Quand Qui Statut)
     *
     * Constructeur permettant de créer et d'initialiser une nouvelle partie.
     */
    public Partie(){

        // Saisie des différents paramètres
        Object[] parametres;
        parametres = IG.saisirParametres(); //Cette commande ouvre la fenêtre des paramètres
        //On créer la fenêtre de jeu

        // Initialisation des joueurs et affichage de la fenêtre
        Joueur[] joueurs=Joueur.nouveauxJoueurs(parametres);
        IG.creerFenetreJeu("Ma Démo du labyrinthe", (Integer) parametres[0]);
        IG.rendreVisibleFenetreJeu();
        for (Joueur joueur : joueurs) {
            IG.changerNomJoueur(joueur.getNumJoueur(), joueur.getNomJoueur() + " (" + joueur.getCategorie() + ")");
            IG.changerImageJoueur(joueur.getNumJoueur(), joueur.getNumeroImagePersonnage());
            IG.placerJoueurPrecis(joueur.getNumJoueur(), joueur.getPosLigne(), joueur.getPosColonne(), 0, 0);
        }

        int nbjoueur = (int) parametres[0];
        IG.creerFenetreJeu("première version du labyrinthe", nbjoueur);
        IG.rendreVisibleFenetreJeu();
        IG.miseAJourAffichage();
    }

    /**
     * Méthode permettant de paramètrer et initialiser les éléments de la partie.
     */
    private void parametrerEtInitialiser(){
        // Saisie des différents paramÃ¨tres
        Object parametresJeu[];
        parametresJeu=IG.saisirParametres();
        int nombreJoueurs=((Integer)parametresJeu[0]).intValue();
        IG.creerFenetreJeu("- Version "+version, nombreJoueurs);
        // Création des joueurs
        Joueur joueurs[]=Joueur.nouveauxJoueurs(parametresJeu);
        // Création des éléments de la partie
        elementsPartie=new ElementsPartie(joueurs);
    }


    /**
     *
     * A Faire (Quand Qui Statut)
     *
     * Méthode permettant de lancer une partie.
     */
    public void lancer(){
        //on initialise les pièces du plateau
        String message[]= {
                "",
                "Cliquez pour initialiser les pièces...",
                ""
        };
        IG.afficherMessage(message);
        IG.miseAJourAffichage();
        IG.attendreClic();
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

        //on place les objets sur le plateau
        message = new String[]{
                "",
                "Cliquez pour initialiser les objets",
                "sur le plateau...",
                ""
        };
        IG.afficherMessage(message);
        IG.miseAJourAffichage();
        IG.attendreClic();
        Objet.nouveauxObjets();

        int incObjet = 0;
        Objet[] objetsJeu=Objet.nouveauxObjets();
        System.out.println(Arrays.toString(objetsJeu));
        for (int i = 0; i < 18; i++) {

            IG.placerObjetPlateau(objetsJeu[incObjet].getNumeroObjet(), objetsJeu[incObjet].getPosLignePlateau(), objetsJeu[incObjet].getPosColonnePlateau());
            incObjet++;

        }

        IG.miseAJourAffichage();

        //donner les objets aux joueurs
        message = new String[]{
                "",
                "Cliquez pour donner les objets",
                "aux joueurs...",
                ""
        };
        IG.afficherMessage(message);
        IG.miseAJourAffichage();
        IG.attendreClic();



        IG.miseAJourAffichage();
    }

    /**
     *
     * Programme principal permettant de lancer le jeu.
     *
     * @param args Les arguments du programmes.
     */
    public static void main(String[] args) {

        Partie partie=new Partie();
        partie.lancer();

    }

}
