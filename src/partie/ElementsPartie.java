package partie;

import composants.Objet;
import composants.Piece;
import composants.Plateau;
import composants.Utils;
import grafix.interfaceGraphique.IG;
import joueurs.Joueur;

import java.util.Vector;

/**
 *
 * Cette classe permet de représenter un ensemble d'élements composant une partie de jeu.
 *
 */
public class ElementsPartie {

    private Joueur[] joueurs; 	// Les joueurs de la partie.
    private Objet[] objets; 	// Les 18 objets de la partie dans l'ordre de leurs numéros.
    private Plateau plateau; 	// Le plateau des pièces.
    private Piece pieceLibre; 	// La pièce libre.
    private int nombreJoueurs; 	// Le nombre de joueurs.

    /**
     *
     * A Faire (26/05/2021 Tristan Terminé)
     *
     * Constructeur permettant de générer et d'initialiser l'ensemble des éléments d'une partie (sauf les joueurs qui sont donnés en paramètres).
     *
     * Un plateau est créé en placant 49 pièces de manière aléatoire (utilisation de la méthode placerPiecesAleatoierment de la classe Plateau).
     * La pièce restante (celle non présente sur le plateau) est affectée à la pièce libre.
     * Les 18 objets sont créés avec des positions aléatoires sur le plateau (utilisation de la méthode Objet.nouveauxObjets)
     * et distribuées aux différents joueurs (utilisation de la méthode attribuerObjetsAuxJoueurs).
     *
     * @param joueurs Les joueurs de la partie. Les objets des joueurs ne sont pas encore attribués (c'est au constructeur de le faire).
     */
    public ElementsPartie(Joueur[] joueurs) {
        this.joueurs = joueurs;
        this.objets = Objet.nouveauxObjets();
        this.plateau = new Plateau();
        this.pieceLibre = plateau.placerPiecesAleatoierment();
        this.nombreJoueurs = joueurs.length;
    }

    /**
     * Un simple constructeur.
     *
     * @param joueurs Les joueurs de la partie.
     * @param objets Les 18 objets de la partie.
     * @param plateau Le plateau de jeu.
     * @param pieceLibre La pièce libre (la pièce hors plateau).
     */
    public ElementsPartie(Joueur[] joueurs,Objet[] objets,Plateau plateau,Piece pieceLibre) {
        this.joueurs=joueurs;
        nombreJoueurs=joueurs.length;
        this.objets=objets;
        this.plateau=plateau;
        this.pieceLibre=pieceLibre;
    }

    /**
     * A Faire (04/06/2021 Tristan Terminée)
     *
     * Méthode permettant d'attribuer les objets aux différents joueurs de manière aléatoire.
     */
    public void attribuerObjetsAuxJoueurs(){
        Vector<Integer> objetsDejaSet = new Vector<>(){};
        for (Joueur joueur : joueurs) {
            Vector<Objet> objetsJoueurI = new Vector<>();
            while (objetsJoueurI.size() != 18 / joueurs.length) {
                int indRandom = Utils.genererEntier(18);
                if (!objetsDejaSet.contains(indRandom)) {
                    objetsJoueurI.add(objets[indRandom]);
                    objetsDejaSet.add(indRandom);
                }
            }
            joueur.setObjetsJoueur(objetsJoueurI.toArray(new Objet[18 / joueurs.length]));
        }
    }

    /**
     * A Faire (26/05/2021 Tristan Terminé)
     *
     * Méthode permettant de récupérer les joueurs de la partie.
     * @return Les joueurs de la partie.
     */
    public Joueur[] getJoueurs() {
        return this.joueurs;
    }


    /**
     * A Faire (26/05/2021 Tristan Terminé)
     *
     * Méthode permettant de récupérer les pièces de la partie.
     * @return Les objets de la partie.
     */
    public Objet[] getObjets() {
        return this.objets;
    }


    /**
     * A Faire (26/05/2021 Tristan Terminé)
     *
     * Méthode permettant de récupérer le plateau de pièces de la partie.
     * @return Le plateau de pièces.
     */
    public Plateau getPlateau() {
        return this.plateau;
    }


    /**
     * A Faire (26/05/2021 Tristan Terminé)
     *
     * Méthode permettant de récupérer la pièce libre de la partie.
     * @return La pièce libre.
     */
    public Piece getPieceLibre() {
        return this.pieceLibre;
    }


    /**
     * A Faire (26/05/2021 Tristan Terminé)
     *
     * Méthode permettant de récupérer le nombre de joueurs de la partie.
     * @return Le nombre de joueurs.
     */
    public int getNombreJoueurs() {
        return this.nombreJoueurs;
    }


    /**
     * A Faire (04/06/2021 Tristan inachevé)
     *
     * Méthode modifiant les différents éléments de la partie suite à l'insertion de la pièce libre dans le plateau.
     *
     * @param choixEntree L'entrée choisie pour réaliser l'insertion (un nombre entre 0 et 27).
     * @throws CloneNotSupportedException
     */
    public void insertionPieceLibre(int choixEntree) throws CloneNotSupportedException {
        assert choixEntree >= 0 && choixEntree <= 27;
        this.pieceLibre.setOrientation(IG.recupererOrientationPieceHorsPlateau());
        Piece APL = pieceLibre.copy();
        if (choixEntree < 7) {
            System.out.println("De bas en haut");
            IG.changerPieceHorsPlateau(this.plateau.getPiece(6, choixEntree%7).getModelePiece(), this.plateau.getPiece(6, choixEntree%7).getOrientationPiece());
            this.pieceLibre = this.plateau.getPiece(6, choixEntree%7).copy();
            for (int i = 6; i > 0; i--) {
                IG.changerPiecePlateau(i, choixEntree%7, plateau.getPiece(i-1, choixEntree%7).getModelePiece(), plateau.getPiece(i-1, choixEntree%7).getOrientationPiece());
                Piece PA = plateau.getPiece(i-1, choixEntree%7).copy();
                this.plateau.positionnePiece(PA, i, choixEntree%7);
            }
            this.plateau.positionnePiece(APL, 0, choixEntree%7);
            IG.changerPiecePlateau(0, choixEntree%7, APL.getModelePiece(), APL.getOrientationPiece());
        } else if (choixEntree < 14) {

        } else if (choixEntree < 21) {

        } else {

        }
    }



    /**
     * Méthode retournant une copie.
     *
     * @return Une copie des éléments.
     */
    public ElementsPartie copy() throws Exception{
        Objet[] nouveauxObjets=new Objet[(this.objets).length];
        for (int i=0;i<objets.length;i++)
            nouveauxObjets[i]=(this.objets[i]).copy();
        Joueur[] nouveauxJoueurs=new Joueur[nombreJoueurs];
        for (int i=0;i<nombreJoueurs;i++)
            nouveauxJoueurs[i]=(this.joueurs[i]).copy(objets);
        Plateau nouveauPlateau=(this.plateau).copy();
        Piece nouvellePieceLibre=(this.pieceLibre).copy();
        ElementsPartie nouveauxElements=new  ElementsPartie(nouveauxJoueurs,nouveauxObjets,nouveauPlateau,nouvellePieceLibre);
        return nouveauxElements;
    }

    public static void main(String[] args) {
        System.out.println(6%7);
        System.out.println(7%7);
        System.out.println(8%7);
        System.out.println(0%7);
    }
}
