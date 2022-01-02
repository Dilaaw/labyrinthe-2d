package composants;

/**
 *
 * Cette classe permet de représenter les pièces du jeu de modèle 1.
 *
 */
public class PieceM1 extends Piece {

    /**
     * A Faire (Quand Qui Statut)
     *
     * Constructeur permettant de construire une pièce de modèle 1 et d'orientation 0.
     */
    public PieceM1() {
        // A Modifier !!!
        super(1,
                true,
                false,
                true,
                false);
    }
    /**
     * A Faire (Quand Qui Statut)
     *
     * Méthode permettant de créer une copie de la pièce (un nouvelle objet Java).
     * @return Une copie de la pièce.
     */
    public Piece copy() throws CloneNotSupportedException {
        Piece p = (Piece) this.clone();
        return p;
    }
}
