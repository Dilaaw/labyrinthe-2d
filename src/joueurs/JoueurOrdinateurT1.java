package joueurs;

import composants.Objet;
import composants.Utils;
import grafix.interfaceGraphique.IG;
import partie.ElementsPartie;

import java.util.Vector;

/**
 *
 * Cette classe permet de reprÃ©senter un joueur ordinateur de type T1.
 *
 * @author Jean-FranÃ§ois Condotta - 2021
 *
 */

public class JoueurOrdinateurT1 extends JoueurOrdinateur {

    /**
     *
     * Constructeur permettant de crÃ©er un joueur.
     *
     * @param numJoueur Le numÃ©ro du joueur.
     * @param nomJoueur Le nom du joueur.
     * @param numeroImagePersonnage Le numÃ©ro de l'image reprÃ©sentant le joueur.
     * @param posLignePlateau La ligne du plateau sur laquelle est positionnÃ©e le joueur.
     * @param posColonnePlateau La colonne du plateau sur laquelle est positionnÃ©e le joueur.

     */
    public JoueurOrdinateurT1(int numJoueur,String nomJoueur, int numeroImagePersonnage,int posLignePlateau,int posColonnePlateau) {
        super(numJoueur,nomJoueur, numeroImagePersonnage,posLignePlateau,posColonnePlateau);
    }

    @Override
    public String getCategorie() {
        return "OrdiType1";
    }

    /**Tristan Fumiere 07/06/2021
     * Saisies de l'orientation de la pièce hors plateau et de l'entrée dans le plateau : on retourne un nombre random entre 0 et 27 pour l'entrée du plateau (la flèche du haut)
     * et l'orientation courante de la pièce hors plateau de l'interface graphique.
     */
    @Override
    public int[] choisirOrientationEntree(ElementsPartie elementsPartie) {
        int[] resultat = new int[2];
        resultat[0] = IG.recupererOrientationPieceHorsPlateau();
        resultat[1] = Utils.genererEntier(27);
        return resultat;
    }

    /**Tristan Fumiere 07/06/2021
     * Saisie de la case d'arrivée réalisée : retourne les coordonnées de l'objet à récupérer s'il est récupérable. Sinon prends un chemin random
     */
    @Override
    public int[] choisirCaseArrivee(ElementsPartie elementsPartie) {
        int[] resultat =new int[2];
        int[] PO = new int[]{this.getProchainObjet().getPosLignePlateau(), this.getProchainObjet().getPosColonnePlateau()};
        if (elementsPartie.getPlateau().calculeChemin(this.getPosLigne(), this.getPosColonne(), PO[0], PO[1]) != null) { // Si le chemin est possible, on y va
            resultat[0] = this.getProchainObjet().getPosLignePlateau();
            resultat[1] = this.getProchainObjet().getPosColonnePlateau();
        } else { // Sinon, case random accessible
            Vector<int[]> caseInaccessible = new Vector<>();
            Vector<int[]> caseAccessible = new Vector<>();
            for (int i = 0; i < 7; i++) {
                for (int j = 0; j < 7; j++) {
                    int[] cV = new int[]{i, j};
                    int[][] verifAccessible = elementsPartie.getPlateau().calculeChemin(this.getPosLigne(), this.getPosColonne(), cV[0], cV[1]);
                    if ( verifAccessible == null) {
                        caseInaccessible.add(cV);
                    } else {
                        caseAccessible.add(cV);
                    }
                }
            }
            int randomInt = Utils.genererEntier(caseAccessible.size());
            resultat[0] = caseAccessible.elementAt(randomInt)[0];
            resultat[1] = caseAccessible.elementAt(randomInt)[1];
        }
        return resultat;
    }

    @Override
    public Joueur copy(Objet[] objets){
        Joueur nouveauJoueur=new JoueurOrdinateurT1(getNumJoueur(),getNomJoueur(), getNumeroImagePersonnage(),getPosLigne(),getPosColonne());
        nouveauJoueur.setObjetsJoueur(this.getObjetsJoueurGeneral(objets));
        while (nouveauJoueur.getNombreObjetsRecuperes()!=this.getNombreObjetsRecuperes())
            nouveauJoueur.recupererObjet();
        return nouveauJoueur;
    }

}
