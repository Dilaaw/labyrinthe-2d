package composants;
import java.util.ArrayList;

/**
 *
 * Cette classe permet de représenter chacun des objets du jeu.
 *
 */
public class Objet {

    private int numObjet; // Le numéro de l'objet (un entier entre 0 et 17).
    private int posLignePlateau; // La ligne du plateau sur laquelle est éventuellement posé l'objet (un entier entre -1 et 6, -1:lorsqu'il n'est pas sur le plateau).
    private int posColonnePlateau; // La colonne du plateau sur laquelle est éventuellement posé l'objet (un entier entre -1 et 6, -1:lorsqu'il n'est pas sur le plateau).
    private boolean surPlateau; // Indique si l'objet est sur le plateau ou non (true : sur le plateau, false : hors du plateau).
    private static final int NB_OBJETS = 18;


    /**
     *
     * A Faire (14/05/2021 Tristan Terminée)
     *
     * Constructeur permettant de construire un objet qui est initialement hors du plateau.
     *
     * @param numObjet Le numéro de l'objet.
     */
    public Objet(int numObjet) {
        this.numObjet = numObjet;
        this.posLignePlateau = -1;
        this.posColonnePlateau = -1;
        this.surPlateau = false;
    }

    /**
     *
     * A Faire (17/05/2021 Guillaume Statut)
     *
     * Méthode permettant de générer un tableau contenant les 18 objets du jeu.
     * Les objets seront positionnées aléatoirement sur le plateau.  Deux objets ne pourront pas être sur une même case (même ligne et même colonne).
     *
     * @return Un tableau de 18 objets initialisés pour une partie du jeu. Chaque objet a une position générée aléatoirement. Les positions sont différentes pour deux objets distincts.
     *
     */
    public static Objet[] nouveauxObjets(){
        Objet[] objets = new Objet[NB_OBJETS];
        int nbObjetInitialises = 0;
        ArrayList<int[]> coordonneesObjets = new ArrayList<>();
        for (int i = 0; i < objets.length; i++) {
            objets[i] = new Objet(i);
        }
        while (coordonneesObjets.size() < 18) {
            int[] coordonneesRandom = new int[2];
            coordonneesRandom[0] = Utils.genererEntier(7);// Pour la ligne du plateau
            coordonneesRandom[1] = Utils.genererEntier(7);// Pour la colonne du plateau
            if (!coordonneesObjets.contains(coordonneesRandom)) {
                objets[nbObjetInitialises].positionneObjet(coordonneesRandom[0], coordonneesRandom[1]);
                nbObjetInitialises++;
                coordonneesObjets.add(coordonneesRandom);
            }
        }
        return objets;
    }


    /**
     *
     * A Faire (17/05/2021 Guillaume Statut)
     *
     * MÃ©thode retournant le numÃ©ro de l'objet.
     *
     * @return Le numÃ©ro de l'objet.
     */
    public int getNumeroObjet() {
        return numObjet; // A Modifier
    }


    /**
     *
     * A Faire (17/05/2021 Guillaume Statut)
     *
     * MÃ©thode retournant le numÃ©ro de la ligne sur laquelle se trouve l'objet.
     *
     * @return Le numÃ©ro de la ligne sur laquelle se trouve l'objet.
     */
    public int getPosLignePlateau() {
        return posLignePlateau; // A Modifier
    }

    /**
     *
     * A Faire (17/05/2021 Guillaume Statut)
     *
     * MÃ©thode retournant le numÃ©ro de la colonne sur laquelle se trouve l'objet.
     *
     * @return Le numÃ©ro de la colonne sur laquelle se trouve l'objet.
     */
    public int getPosColonnePlateau() {
        return posColonnePlateau; // A Modifier
    }

    /**
     *
     * A Faire (17/05/2021 Guillaume Statut)
     *
     * MÃ©thode permettant de positionner l'objet sur une ligne et une colonne donnÃ©es en paramÃ¨tre.
     *
     * @param lignePlateau Un entier compris entre 0 et 6.
     * @param colonnePlateau Un entier compris entre 0 et 6.
     */
    public void positionneObjet(int lignePlateau,int colonnePlateau){
        if (lignePlateau >= 0 && lignePlateau <= 6) this.posLignePlateau = lignePlateau;
        if (colonnePlateau >= 0 && colonnePlateau <= 6) this.posColonnePlateau = colonnePlateau;
        this.surPlateau = true;
    }

    /**
     *
     * A Faire (17/05/2021 Guillaume Statut)
     *
     * MÃ©thode permettant d'enlever l'objet du plateau.
     *
     */
    public void enleveDuPlateau(){
        this.posLignePlateau = -1;
        this.posColonnePlateau= -1;
        this.surPlateau = false;
    }

    /**
     *
     * A Faire (17/05/2021 Guillaume Statut)
     *
     * MÃ©thode indiquant si l'objet est sur le plateau au non.
     *
     * @return true si l'objet est sur le plateau, false sinon.
     */
    public boolean surPlateau() {
        return this.surPlateau;
    }

    /**
     * MÃ©thode permettant d'obtenir une reprÃ©sentation d'un objet sous forme de chaÃ®ne de caractÃ¨res.
     */
    @Override
    public String toString() {
        return "Objet [numObjet=" + numObjet + ", posLignePlateau=" + posLignePlateau + ", posColonnePlateau="
                + posColonnePlateau + ", surPlateau=" + surPlateau + "]";
    }

    /**
     *
     * Méthode permettant de copier l'objet.
     *
     * @return Une copie de l'objet.
     */
    public Objet copy(){
        Objet objet=new Objet(numObjet);
        objet.posLignePlateau=posLignePlateau;
        objet.posColonnePlateau=posColonnePlateau;
        objet.surPlateau=surPlateau;
        return objet;
    }

    /**
     * Programme testant quelques mÃ©thodes de la classe Objet.
     * @param args arguments du programme
     */
    public static void main(String[] args) {
        // Un petit test ...
        System.out.println("*** Génération et affichage des 18 objets ... ***");
        Objet[] objetsJeu=nouveauxObjets();
        for (Objet item : objetsJeu) System.out.println(item);
        System.out.println("*** On enlÃ¨ve les 18 objets du plateau ... ***");
        for (Objet value : objetsJeu) value.enleveDuPlateau();
        System.out.println("*** On affiche de nouveau les 18 objets ... ***");
        for (Objet objet : objetsJeu) System.out.println(objet);
    }

}