package ks.battleshipmobile;

/**
 * 0 = frei
 * 1 = Schiff gesetzt
 * 2 = Schiffsumgebung, wo nichts gesetzt werden darf
 * 4 = kein Treffer gelanden
 * 5 = Treffer gelandet
 * 6 = Schiff versenkt
 */

public class Spiellogik {

    int [][] spieler1 = new int[8][8];
    int [][] spieler2 = new int[8][8];
    //int [][] temp = new int[8][8];

    final int viererSchiff = 4;
    final int dreierSchiff = 3;
    final int zweierSchiff = 2;

    int anzahlVierer = 1;
    int anzahlDreier = 2;
    int anzahlZweier = 3;



    /**
     * Diese Methode setzt die Felder um das Schiff auf den Wert 2, sodass spaeter
     * diese Felder nicht mehr besetzt werden koennen (nur Felder mit dem Wert 0
     * koennen besetzt werden).
     * @param n
     * @param m
     * @author Kirsten und Serdar
     */
    public void markiereSchiffUmgebung(int n, int m, int [][] temp) {

        if (n>0) {
            n--;
            if (temp[n][m] == 0) {
                temp[n][m] = 2;
            }
            n++;
        }
        if (m<7) {
            m++;
            if (temp[n][m] == 0) {
                temp[n][m] = 2;
            }
            m--;
        }
        if (n<7) {
            n++;
            if (temp[n][m] == 0) {
                temp[n][m] = 2;
            }
            n--;
        }
        if (m>0) {
            m--;
            if (temp[n][m] == 0) {
                temp[n][m] = 2;
            }
            m++;
        }
    }

    /**
     * Diese Methode setzt die Felder um das Schiff auf den Wert 2, sodass spaeter
     * diese Felder nicht mehr besetzt werden koennen (nur Felder mit dem Wert 0
     * koennen besetzt werden).
     * @param n
     * @param m
     * @author Kirsten und Serdar
     */
    public void unmarkiereSchiffUmgebung(int n, int m, int [][] temp) {

        if (n>0) {
            n--;
            temp[n][m] = 0;
            n++;
        }
        if (m<7) {
            m++;
            temp[n][m] = 0;
            m--;
        }
        if (n<7) {
            n++;
            temp[n][m] = 0;
            n--;
        }
        if (m>0) {
            m--;
            temp[n][m] = 0;
            m++;
        }
    }



}
