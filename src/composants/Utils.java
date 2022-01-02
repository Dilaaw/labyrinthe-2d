package composants;

import jdk.jshell.execution.Util;

import java.util.ArrayList;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

/**
 *
 * Classe contenant quelques fonctions utilitaires.
 *
 */
public class Utils {

    private static Random generateur=new Random((new Date().getTime()));

    /**
     * A Faire (26/04/2021 Guillaume Finalisée)
     *
     * Méthode permettant de générer aléatoirement un nombre entier.
     *
     * @param max Le nombre entier maximal pouvant être retourné.
     * @return Un nombre entier compris entre 0 et max (inclus).
     *
     */
    public static int genererEntier(int max){
        // A Modifier !!
        int nb;
        nb = generateur.nextInt(max);
        return nb;
    }

    /**
     * A Faire (Quand Qui Statut)
     *
     * Méthode permettant de générer un tableau d'entiers dont la longueur longTab est donnée en paramétre.
     * Le tableau généré doit contenir chaque entier compris entre 0 et longTab-1. La position de ces entiers
     * dans le tableau doit être aléatoire.
     *
     * @param longTab La longueur du tableau.
     * @return Un tableau contenant les entiers 0,...,longTab-1 placés aléatoirement dans le tableau.
     */
    public static ArrayList<Integer> genereTabIntAleatoirement(int longTab) {

        ArrayList<Integer> tab = new ArrayList();

        while (tab.size() < longTab) {
            int random = Utils.genererEntier(longTab);

            if (!tab.contains(random)) {
                tab.add(random);
            }
        }

        return tab;

    }

    /**
     * Programme testant la méthode genereTabIntAleatoirement.
     * @param args arguments du programme
     */
    public static void main(String[] args) {
        // Un petit test ...
        ArrayList<Integer> tab=genereTabIntAleatoirement(18);
        for (int i=0;i<tab.size();i++)
            System.out.print(tab.get(i)+" ");
    }

}
