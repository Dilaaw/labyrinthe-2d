package composants;

import grafix.interfaceGraphique.IG;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Vector;

/**
 * Cette classe permet de gérer un plateau de jeu constitué d'une grille de pièces (grille de 7 lignes sur 7 colonnes).
 *
 */
public class Plateau {

    private Piece[][] plateau; // La grille des pièces.

    /**
     * A Faire (20/05/2021 Tristan Terminé)
     *
     * Constructeur permettant de construire un plateau vide (sans pièces) et d'une taille de 7 lignes sur 7 colonnes.
     */
    public Plateau() {
        this.plateau = new Piece[7][7];
    }

    /**
     * A Faire (20/05/2021 Gabriel Terminé)
     *
     * Méthode permettant de placer une pièce sur le plateau.
     *
     * @param piece La pièce à placer.
     * @param lignePlateau La ligne du plateau sur laquelle sera placée la pièce (un entier entre 0 et 6).
     * @param colonnePlateau La colonne du plateau sur laquelle sera placée la pièce (une entier entre 0 et 6).
     */
    public void positionnePiece(Piece piece,int lignePlateau,int colonnePlateau){
        this.plateau[lignePlateau][colonnePlateau] = piece;
    }

    /**
     * A Faire (20/05/2021 Tristan Terminé)
     *
     * Méthode retournant une pièce se trouvant sur le plateau à un emplacement spécifique.
     *
     * @param lignePlateau La ligne du plateau  (un entier entre 0 et 6).
     * @param colonnePlateau La colonne du plateau (un entier entre 0 et 6).
     * @return La pièce se trouvant sur la ligne lignePlateau et la colonne colonnePlateau. Si il n'y a pas de pièce, null est retourné.
     */
    public Piece getPiece(int lignePlateau,int colonnePlateau){
        return plateau[lignePlateau][colonnePlateau];
    }

    /**
     *
     * A Faire (20/05/2021 Tristan Terminé)
     *
     * Méthode permettant de placer aléatoirment 49 pièces du jeu sur le plateau.
     * L'orientation des pièces est aléatoire. Les pièces utilisées doivent être des nouvelles pièces générées à partir de la méthode Piece.nouvellesPieces.
     * Parmi les 50 pièces du jeu, la pièce qui n'a pas été placée sur le plateau est retournée par la méthode.
     *
     * @return La seule pièce qui n'a pas été placée sur le plateau
     */
    public Piece placerPiecesAleatoierment(){
        Piece[] stockDePieces = Piece.nouvellesPieces();
        ArrayList<Integer> randTab = Utils.genereTabIntAleatoirement(49);
        for (int i = 0; i < 49; i++) {
            positionnePiece(stockDePieces[i], randTab.get(i) / 7, randTab.get(i) % 7);
        }
        return stockDePieces[49];
    }

    /**
     *
     * Méthode utilitaire permettant de tester si les positions passées en paramètre sont les positions de deux cases différentes et adjacentes d'une grille de 7 lignes sur 7 colonnes.
     *
     * @param posLigCase1 Un entier quelconque.
     * @param posColCase1 Un entier quelconque.
     * @param posLigCase2 Un entier quelconque.
     * @param posColCase2 Un entier quelconque.
     * @return true si les les positions passées en paramètre sont les positions de deux cases différentes et adjacentes d'une grille de 7 lignes sur 7 colonnes, false sinon.
     */
    private static boolean casesAdjacentes(int posLigCase1,int posColCase1,int posLigCase2,int posColCase2){
        if ((posLigCase1<0)||(posLigCase2<0)||(posLigCase1>6)||(posLigCase2>6)) return false;
        if ((posColCase1<0)||(posColCase2<0)||(posColCase1>6)||(posColCase2>6)) return false;
        int distLigne=posLigCase1-posLigCase2;
        if (distLigne<0) distLigne=-distLigne;
        int distColonne=posColCase1-posColCase2;
        if (distColonne<0) distColonne=-distColonne;
        if ((distLigne>1)||(distColonne>1)||((distColonne+distLigne)!=1))
            return false;
        return true;
    }

    /**
     *
     * A Faire (20/05/2021 Tristan Terminé)
     *
     * Méthode permettant de tester si les positions passées en paramètre sont les positions de deux cases différentes et adjacentes
     * de la grille de jeu et qu'il est possible de passer d'une cas à l'autre compte tenu des deux pièces posées sur les deux cases du plateau.
     *
     * @param posLigCase1 Un entier quelconque.
     * @param posColCase1 Un entier quelconque.
     * @param posLigCase2 Un entier quelconque.
     * @param posColCase2 Un entier quelconque.
     * @return true si les positions passées en paramètre sont les positions de deux cases différentes et adjacentes de la grille de jeu et qu'il est possible de passer d'une case à l'autre compte tenu des deux pièces posées sur les deux cases du plateau, false sinon.
     */
    private boolean passageEntreCases(int posLigCase1,int posColCase1,int posLigCase2,int posColCase2){
        if (casesAdjacentes(posLigCase1, posColCase1, posLigCase2, posColCase2)) { // Vérification si Adjacentes
            Piece piece1 = this.getPiece(posLigCase1, posColCase1); // Si oui, on récup les 2 pièces
            Piece piece2 = this.getPiece(posLigCase2, posColCase2);

            if (posLigCase1 == posLigCase2 + 1 && posColCase1 == posColCase2) {  // Si la case 2 est au dessus
                //System.out.println("Passage entre {"+posLigCase1+","+posColCase1+"} et {"+posLigCase2+","+posColCase2+"} (vers le haut) = " + (piece1.getPointEntree(0) && piece2.getPointEntree(2)));
                return piece1.getPointEntree(0) && piece2.getPointEntree(2);
            }
            if (posLigCase1 == posLigCase2 && posColCase1 == posColCase2 -1) { // Si la case 2 est à droite
                //System.out.println("Passage entre {"+posLigCase1+","+posColCase1+"} et {"+posLigCase2+","+posColCase2+"} (vers la droite) = " + (piece1.getPointEntree(1) && piece2.getPointEntree(3)));
                return piece1.getPointEntree(1) && piece2.getPointEntree(3);
            }
            if (posLigCase1 == posLigCase2 - 1 && posColCase1 == posColCase2) {  // Si la case 2 est en dessous
                //System.out.println("Passage entre {"+posLigCase1+","+posColCase1+"} et {"+posLigCase2+","+posColCase2+"} (vers le bas) = " + (piece1.getPointEntree(2) && piece2.getPointEntree(0)));
                return piece1.getPointEntree(2) && piece2.getPointEntree(0);
            }
            if (posLigCase1 == posLigCase2 && posColCase1 == posColCase2 + 1) { // Si la case 2 est à gauche
                //System.out.println("Passage entre {"+posLigCase1+","+posColCase1+"} et {"+posLigCase2+","+posColCase2+"} (vers la gauche) = " + (piece1.getPointEntree(3) && piece2.getPointEntree(1)));
                return piece1.getPointEntree(3) && piece2.getPointEntree(1);
            }
        }
        return false;
    }

    /**
     *
     * A Faire (26/05/2021 Tristan Terminé)
     *
     * Méthode permettant de retourner un éventuel chemin entre deux cases du plateau compte tenu des pièces posées sur le plateau.
     * Dans le cas où il n'y a pas de chemin entre les deux cases, la valeur null est retournée.
     * Dans le cas où il existe un chemin, un chemin possible est retourné sous forme d'un tableau d'entiers à deux dimensions.
     * La première dimension correspond aux cases du plateau à emprunter pour aller de la case de départ à la case d'arrivée.
     * Dans ce tableau, chaque case est un tableau de deux entiers avec le premier entier qui correspond à la ligne de la case et
     * le second entier qui correspond à la colonne de la case. La première case d'un chemin retourné correspond toujours
     * à la case (posLigCaseDep,posColCaseDep) et la dernière case correspond toujours à la case (posLigCaseArr,posColCaseArr).
     *
     * @param posLigCaseDep La ligne de la case de départ (un entier compris entre 0 et 6).
     * @param posColCaseDep La colonne de la case de départ (un entier compris entre 0 et 6).
     * @param posLigCaseArr La ligne de la case d'arrivée (un entier compris entre 0 et 6).
     * @param posColCaseArr La colonne de la case d'arrivée (un entier compris entre 0 et 6).
     * @return null si il n'existe pas de chemin entre les deux case, un chemin sinon.
     */
    public int[][] calculeChemin(int posLigCaseDep,int posColCaseDep,int posLigCaseArr,int posColCaseArr){
        int[][] resultat = null;

        if (posLigCaseDep == posLigCaseArr && posColCaseDep == posColCaseArr) { // Si c'est la même case on return juste la case
            resultat = new int[][]{{posLigCaseDep, posColCaseDep}};
            return resultat;
        }


        boolean chemin = false;
        LinkedList<int[]> cheminPasEncoreTeste = new LinkedList<>();
        // Matrice entière de 99
        int[][] matrice = new int[7][7];
        for (int i = 0; i < matrice.length; i++) {
            for (int j = 0; j < matrice.length; j++) {
                matrice[i][j] = 99;
            }
        }

        //On set l'arrivée à 0 (pour sa distance)
        matrice[posLigCaseArr][posColCaseArr] = 0;
        int[][] test = possibleCheminAdjacents(posLigCaseArr, posColCaseArr);
        int incDistance = 0;
        int[] cooActu = new int[]{posLigCaseArr, posColCaseArr};
        while (chemin != true && (test[0]!=null||test[1]!=null||test[2]!=null||test[3]!=null)) {

            for (int i = 0; i < test.length; i++) {
                if (test[i] != null && (matrice[test[i][0]][test[i][1]] == 99)) {
                    matrice[test[i][0]][test[i][1]] = incDistance +1;
                    cheminPasEncoreTeste.add(new int[]{test[i][0], test[i][1], incDistance+1});
                    if (test[i][0] == posLigCaseDep && test[i][1] == posColCaseDep) {
                        chemin = true;
                        //System.out.println("Arrivé sur la case Cible ! Arrêt du PathFinder.");
                    }
                }
            }
            if (!cheminPasEncoreTeste.isEmpty()) { // Si on a testé toutes les cases en stock, on arrête la boucle while
                cooActu = new int[]{cheminPasEncoreTeste.getFirst()[0], cheminPasEncoreTeste.getFirst()[1]};
                incDistance = cheminPasEncoreTeste.getFirst()[2];
                cheminPasEncoreTeste.removeFirst();
                test = possibleCheminAdjacents(cooActu[0], cooActu[1]);
            } else {
                break;
            }

        }

        // Début de la création du véritable chemin
        if (chemin) {
            cooActu = new int[]{posLigCaseDep, posColCaseDep};
            LinkedList<int[]> resultatTemp = new LinkedList<>();
            resultatTemp.add(new int[]{posLigCaseDep, posColCaseDep});

            int decDistance = matrice[cooActu[0]][cooActu[1]];

            for (int i = 0; i < decDistance; i++) {
                test = possibleCheminAdjacents(cooActu[0], cooActu[1]);
                int indiceDistanceAPrendre = Integer.MAX_VALUE;
                int[] prochaineCase = null;

                for (int j = 0; j < test.length; j++) { // On récupère le chemin le plus court à partir des cases adjacentes à la case actuelle
                    if (test[j] != null) {
                        if (matrice[test[j][0]][test[j][1]] < indiceDistanceAPrendre) {
                            indiceDistanceAPrendre = matrice[test[j][0]][test[j][1]];
                            prochaineCase = new int[]{test[j][0], test[j][1]};
                        }
                    }
                }
                cooActu = prochaineCase;
                resultatTemp.add(new int[]{prochaineCase[0], prochaineCase[1]});
            }
            resultat = resultatTemp.toArray(new int[0][]);
        }

        return resultat;
    }

    /**
     * Fait (20/05/2021 Tristan Terminé)
     *
     * Méthode permettant de récupérer les cases adjacentes où l'on peut se déplacer.
     *
     * @param posLig
     * @param posCol
     *
     * @return un vecteur contenant les cases adjacentes où l'on peut se déplacer.
     */
    public int[][] possibleCheminAdjacents(int posLig, int posCol) {
        int[][] liste = new int[4][];

        boolean passage = passageEntreCases(posLig, posCol, posLig-1, posCol);
        if (passage) liste[0] = new int[]{posLig-1, posCol}; // On vérifie la case du dessus
        else liste[0] = null;


        passage = passageEntreCases(posLig, posCol, posLig, posCol+1);
        if (passage) liste[1] = new int[]{posLig, posCol+1}; // On vérifie la case à droite
        else liste[1] = null;


        passage = passageEntreCases(posLig, posCol, posLig+1, posCol);
        if (passage) liste[2] = new int[]{posLig+1, posCol}; // On vérifie la case du bas
        else liste[2] = null;


        passage = passageEntreCases(posLig, posCol, posLig, posCol-1);
        if (passage) liste[3] = new int[]{posLig, posCol-1}; // On vérifie la case du bas
        else liste[3] = null;


        return liste;
    }


    /**
     *
     * Méthode permettant de calculer un chemin détaillé (chemin entre sous-cases) à partir d'un chemin entre cases.
     *
     * @param chemin Un tableau représentant un chemin de cases.
     * @param numJoueur Le numéro du joueur pour lequel nous souhaitons construire un chemin détaillé.
     *
     * @return Le chemin détaillé correspondant au chemin de positions de pièces données en paramètre et pour le numéro de joueur donné.
     */
    public int[][] calculeCheminDetaille(int[][] chemin,int numJoueur){
        if (chemin.length==1)
            return new int[0][0];
        int[][] cheminDetaille=new int[chemin.length*5][4];
        int pos=0;
        int col,lig,colS,ligS;
        for (int i=0;i<chemin.length-1;i++){
            lig=chemin[i][0];
            col=chemin[i][1];
            ligS=chemin[i+1][0];
            colS=chemin[i+1][1];
            if (ligS<lig){
                cheminDetaille[pos][0]=lig;
                cheminDetaille[pos][1]=col;
                cheminDetaille[pos][2]=1;
                cheminDetaille[pos++][3]=1;
                cheminDetaille[pos][0]=lig;
                cheminDetaille[pos][1]=col;
                cheminDetaille[pos][2]=0;
                cheminDetaille[pos++][3]=1;
                cheminDetaille[pos][0]=ligS;
                cheminDetaille[pos][1]=colS;
                cheminDetaille[pos][2]=2;
                cheminDetaille[pos++][3]=1;
            }
            else if (ligS>lig){
                cheminDetaille[pos][0]=lig;
                cheminDetaille[pos][1]=col;
                cheminDetaille[pos][2]=1;
                cheminDetaille[pos++][3]=1;
                cheminDetaille[pos][0]=lig;
                cheminDetaille[pos][1]=col;
                cheminDetaille[pos][2]=2;
                cheminDetaille[pos++][3]=1;
                cheminDetaille[pos][0]=ligS;
                cheminDetaille[pos][1]=colS;
                cheminDetaille[pos][2]=0;
                cheminDetaille[pos++][3]=1;
            } else if (colS<col){
                cheminDetaille[pos][0]=lig;
                cheminDetaille[pos][1]=col;
                cheminDetaille[pos][2]=1;
                cheminDetaille[pos++][3]=1;
                cheminDetaille[pos][0]=lig;
                cheminDetaille[pos][1]=col;
                cheminDetaille[pos][2]=1;
                cheminDetaille[pos++][3]=0;
                cheminDetaille[pos][0]=ligS;
                cheminDetaille[pos][1]=colS;
                cheminDetaille[pos][2]=1;
                cheminDetaille[pos++][3]=2;
            } else if (colS>col){
                cheminDetaille[pos][0]=lig;
                cheminDetaille[pos][1]=col;
                cheminDetaille[pos][2]=1;
                cheminDetaille[pos++][3]=1;
                cheminDetaille[pos][0]=lig;
                cheminDetaille[pos][1]=col;
                cheminDetaille[pos][2]=1;
                cheminDetaille[pos++][3]=2;
                cheminDetaille[pos][0]=ligS;
                cheminDetaille[pos][1]=colS;
                cheminDetaille[pos][2]=1;
                cheminDetaille[pos++][3]=0;
            }
        }
        cheminDetaille[pos][0]=chemin[chemin.length-1][0];
        cheminDetaille[pos][1]=chemin[chemin.length-1][1];
        cheminDetaille[pos][2]=1;
        cheminDetaille[pos++][3]=1;

        int debut=0;
        if ((numJoueur==0)&&((cheminDetaille[pos-2][2]==0)||(cheminDetaille[pos-2][3]==2))) pos--;
        if ((numJoueur==1)&&((cheminDetaille[pos-2][2]==2)||(cheminDetaille[pos-2][3]==2))) pos--;
        if ((numJoueur==2)&&((cheminDetaille[pos-2][2]==2)||(cheminDetaille[pos-2][3]==0))) pos--;
        if ((numJoueur==0)&&((cheminDetaille[1][2]==0)||(cheminDetaille[0][3]==2))) debut++;
        if ((numJoueur==1)&&((cheminDetaille[1][2]==2)||(cheminDetaille[0][3]==2))) debut++;
        if ((numJoueur==2)&&((cheminDetaille[1][2]==2)||(cheminDetaille[0][3]==0))) debut++;

        int[][] resultat=new int[pos-debut][4];
        for (int i=debut;i<pos;i++)
            for (int j=0;j<4;j++)
                resultat[i-debut][j]=cheminDetaille[i][j];
        return resultat;
    }

    /**
     *
     * Méthode retournant une copie du plateau avec des copies de ses pièces.
     *
     * @return Une copie du plateau avec une copie de toutes ses pièces.
     */
    public Plateau copy() throws Exception{
        Plateau plateau=new Plateau();
        for (int ligne=0;ligne<7;ligne++)
            for (int colonne=0;colonne<7;colonne++)
                plateau.positionnePiece((this.plateau[ligne][colonne]).copy(), ligne, colonne);
        return plateau;
    }
}