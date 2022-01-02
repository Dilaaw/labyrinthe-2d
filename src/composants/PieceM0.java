package composants;

/**
 *
 * Cette classe permet de représenter les piéces du jeu de modèle 0.
 *
 */
public class PieceM0 extends Piece {

    /**
     * A Faire (Quand Qui Statut)
     *
     * Constructeur permettant de construire une pièce de modèle 0 et d'orientation 0.
     */
    public PieceM0() {
        // A Modifier !!!
        super(0,
                false,
                true,
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
