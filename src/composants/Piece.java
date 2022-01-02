package composants;

import static java.lang.Math.abs;

/**
 *
 * Cette classe permet de représenter les différentes pièces du jeu.
 *
 */
abstract public class Piece {

    private int modelePiece; 		// Le modéle de la pièce
    private int orientationPiece; 	// L'orientation de la pièce
    private boolean[] pointsEntree; // Les points d'entrée indice 0 pour le haut, 1 pour la droite, 2 pour le bas et 3 pour la gauche.
    private static final int NB_POINTS_ENTREE = 4;

    /**
     * Fait (27/04/2021 GL+Tristan Finalisée)
     *
     * Constructeur permettant de créer une pièce d'un modéle avec l'orientation 0.
     * @param modelePiece Le modèle de la pièce.
     */
    public Piece(int modelePiece,boolean pointEntreeHaut,boolean pointEntreeDroite,boolean pointEntreeBas,boolean pointEntreeGauche){
        this.modelePiece = modelePiece;
        this.pointsEntree = new boolean[NB_POINTS_ENTREE]; // On initialise le tableau à 4 éléments pour ensuite leur donner une valeur
        this.orientationPiece = 0;
        this.pointsEntree[0] = pointEntreeHaut;
        this.pointsEntree[1] = pointEntreeDroite;
        this.pointsEntree[2] = pointEntreeBas;
        this.pointsEntree[3] = pointEntreeGauche;
    }

    /**
     * Méthoide retournant un String représentant la pièce.
     */
    @Override
    public String toString() {
        return "[m:"+modelePiece+"|o:"+orientationPiece+"|pe:"+pointsEntree[0]+" "+pointsEntree[1]+" "+pointsEntree[2]+" "+pointsEntree[3]+"]";
    }

    /**
     * Fait (11/05/2021 Tristan Terminé)
     *
     * Méthode permettant de rotationner une pièce dans le sens d'une horloge.
     */
    public void rotation(){
        boolean tmp;
        boolean tmp2;

        tmp = pointsEntree[1];
        tmp2 = pointsEntree[3];
        pointsEntree[1] = pointsEntree[0];
        pointsEntree[3] = pointsEntree[2];
        pointsEntree[2] = tmp;
        pointsEntree[0] = tmp2;
        this.orientationPiece++;
    }

    /**
     * Fait (12/05/2021 Tristan Terminé)
     *
     * Méthode permettant d'orienter une pièce vers une orientation spécifique.
     * @param orientationPiece Un entier correspondant à  la nouvelle orientation de la pièce.
     */
    public void setOrientation(int orientationPiece){
        int orientationDeBase = this.getOrientationPiece();
        if (orientationPiece == 4) orientationPiece = 0;
        int rotationAFaire = abs(orientationDeBase - orientationPiece);
        for (int rotation = 0; rotation < rotationAFaire; rotation++) {
            this.rotation();
        }
        this.orientationPiece = orientationPiece;
    }

    /**
     * A Faire (27/04/2021 GL Finalisée)
     *
     * Méthode retournant le modèle de la pièce.
     * @return Un entier corrspondant au modèle de la pièce.
     */
    public int getModelePiece() {
        return modelePiece;
    }

    /**
     * A Faire (27/04/2021 GL Finalisée)
     *
     * Méthode retournant l'orientation de la pièce.
     * @return un entier retournant l'orientation de la piéce.
     */
    public int getOrientationPiece() {
        return orientationPiece;
    }

    /**
     * A Faire (27/04/2021 GL Finalisée)
     *
     * Méthode indiquant si il existe un point d'entrée à  une certaine position (0: en haut, 1: Ã  droite, 2: en bas, 3: Ã  gauche).
     * @param pointEntree L'indice/la position du point d'entrée.
     * @return true si il y a un point d'entrée, sinon false.
     */
    public boolean getPointEntree(int pointEntree){
        return this.pointsEntree[pointEntree];
    }

    /**
     * Fait (12/05/2021 Tristan Terminé)
     *
     * Méthode permettant de créer un tableau contenant toutes les pièces du jeu (les 50 pièces).
     * Le tableau contiendra 20 pièces du modèle 0, 12 pièces du modèle 1 et 18 pièces du modèle 2.
     * L'orientation de chaque pièce sera aléatoire.
     * @return Un tableau contenant toutes les pièces du jeu.
     */
    public static Piece[] nouvellesPieces(){
        Piece pieces[]= new Piece[50];
        int[] nbPieceModele = new int[] {20, 12, 18};
        int incPiece = 0;
        for (int i = 0; i < 20; i++) {
            pieces[incPiece] = new PieceM0();
            pieces[incPiece].setOrientation(Utils.genererEntier(4));
            incPiece++;
        }
        for (int i = 0; i < 12; i++) {
            pieces[incPiece] = new PieceM1();
            pieces[incPiece].setOrientation(Utils.genererEntier(4));
            incPiece++;
        }
        for (int i = 0; i < 18; i++) {
            pieces[incPiece] = new PieceM2();
            pieces[incPiece].setOrientation(Utils.genererEntier(4));
            incPiece++;
        }
        return pieces;
    }

    /**
     * Méthode permettant de créer une copie de la pièce (un nouvelle objet Java).
     * @return Une copie de la pièce.
     */
    public abstract Piece copy() throws CloneNotSupportedException;
}
