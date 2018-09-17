package ks.battleshipmobile;

import android.widget.Button;

/**
 * 0-unbelegt/frei
 * 1-Schiff gesetzt
 * 2-Gegnerisches Schiffteil getroffen
 * 3-Kein Treffer
 * 4-Platz um ein gesetztes Schiff drumherum, in welchem kein Schiff gesetzt werden darf
 * 6-Schiff versenkt
 */

public class Spiellogik {

    int spieler = 1;
    int feldgroesse = 8;

    int [][] spieler1 = new int[8][8];
    int [][] spieler2 = new int[8][8];


    //Groesse der Schiffe
    final int viererSchiff = 4;
    final int dreierSchiff = 3;
    final int zweierSchiff = 2;

    //Anzahl der Schiffe
    int anzahlVierer = 1;
    int anzahlDreier = 2;
    int anzahlZweier = 3;

    boolean passt;

    public boolean getPasst() {
        return passt;
    }

    public int getSpieler() {
        return spieler;
    }

    /**
     * Fuer moeglichen lokalen Zweispieler oder KI Nutzung,
     * Wechsel des Spielers.
     * Bei KI Nutzung: spieler = 1 ist der physikalische Spieler, spieler = 2 die KI
     */
    public void spielerWechsel() {
        if (getSpieler() < 2) {
            spieler++;
        }
        else {
            spieler = 1;
        }

    }

    /**
     * Schiffsanzahl wird wieder auf den Ausgangswert zurueck gesetzt
     */
    public void setSchiffsanzahl() {
        anzahlVierer = 1;
        anzahlDreier = 2;
        anzahlZweier = 3;
    }

    /**
     * Methode ueberprueft vor jedem Spielerwechsel, ob auf dem Spielfeld des Gegners noch Schiffteile sind,
     * die noch nicht getroffen wurden.
     * Es wird jedes einzelne Feld untersucht und immer, wenn ein Feld nicht den Wert 1 hat,
     * also sich auf dem Feld kein ungetroffenes Schiff befindet, wird zaehler inkrementiert.
     * Da wir 64 Felder haben, wird geschaut, ob zaehler den Wert 64 hat.
     * Wenn ja, wird gewonnen auf true gesetzt.
     * Wenn nicht, bleibt es auf false.
     */
    public boolean gewonnen(int[][] temp) {
        boolean gewonnen = false;
        int zaehler = 0;
        for (int n=0; n<feldgroesse; n++) {
            for (int m=0; m<feldgroesse; m++) {
                if (temp[n][m] != 1) {
                    zaehler++;
                }
            }
        }
        if (zaehler == feldgroesse*feldgroesse) {
            gewonnen = true;
        }
        return gewonnen;
    }

    /**
     * Methode, um die Schiffe je nach Groeße und Richtung zu setzen.
     */
    public void schiffeSetzen(int groesse, boolean vertikal, int n, int m, int[][] temp) {

        if (schiffPasst(groesse, vertikal, n, m, temp)) {
            for (int i = 0; i<groesse;i++) {
                if (vertikal == true) {

                    temp[n][m] = 1;
                    testeSchiffUmgebung(n, m, temp);
                    n++;

                }
                else {
                    temp[n][m] = 1;
                    testeSchiffUmgebung(n,m, temp);
                    m++;
                }
            }
        }
        else if (schiffPasst(groesse, vertikal, n, m, temp) == false) {
            System.err.println("Nicht genug Platz!");
        }
    }

    public void schiffSchatten(int markierung, int groesse, boolean vertikal, int n, int m, int [][] temp) {

        if (schiffPasst(groesse, vertikal, n, m, temp)) {
            for (int i = 0; i<groesse;i++) {
                if (vertikal == true) {
                    temp[n][m] = markierung;
                    n++;

                }
                else {
                    temp[n][m] = markierung;
                    m++;
                }
            }
        }
    }

    public void farbeAnpassen(int [][] tempInt, Button[][] tempButton) {
        for (int i=0; i<feldgroesse; i++) {
            for (int j=0; j<feldgroesse; j++) {
                if (tempInt[i][j] == 1) {
                    tempButton[i][j].setBackgroundColor(1);
                }
                else if (tempInt[i][j] == 9) {
                    tempButton[i][j].setBackgroundColor(0xFFDF33E5);
                }
                else if (tempInt[i][j] == 0) {
                    tempButton[i][j].setBackgroundColor(0xFF33B5E5);
                }
            }
        }
    }

    /**
     * Ueberprueft, ob das Schiff auf die ausgewaehlten Felder passt.
     * Variable passt wird auf true gesetzt, wenn das Schiff gesetzt werden kann.
     * Variable passt wird auf false gesetzt, wenn das Schiff nicht gesetzt werden kann.
     * Es werden verschiedene boolsche Werte gesetzt, damit fuer jeden Teil des Schiffes
     * getestet werden kann, ob das Feld belegbar ist.
     * Eine kuerzere Variante mit mehreren Schleifen war leider nicht moeglich, da die
     * Werte nicht korrekt gesetzt wurden.
     * @param groesse
     * @param vertikal
     * @param n
     * @param m
     * @return
     * @author Kirsten und Serdar
     */
    public boolean schiffPasst(int groesse, boolean vertikal, int n, int m, int[][] temp) {

        passt = true;
        boolean eins;
        boolean zwei;
        boolean drei;
        boolean vier;
        boolean fuenf;

        if (vertikal == true) {
            if (groesse == 4 && groesse + n <= feldgroesse) {
                if (temp[n][m] == 0 || temp[n][m] == 9) {
                    eins = true;
                    n++;
                }
                else eins = false;

                if (temp[n][m] == 0 || temp[n][m] == 9) {
                    zwei = true;
                    n++;
                }
                else zwei = false;

                if (temp[n][m] == 0 || temp[n][m] == 9) {
                    drei = true;
                    n++;
                }
                else drei = false;

                if (temp[n][m] == 0 || temp[n][m] == 9) {
                    vier = true;
                }
                else vier = false;

                if (eins == true && zwei == true && drei == true && vier == true) {
                    passt = true;
                }
                else passt = false;
            }

            else if (groesse == 3 && groesse + n <= feldgroesse) {
                if (temp[n][m] == 0 || temp[n][m] == 9) {
                    eins = true;
                    n++;
                }
                else eins = false;

                if (temp[n][m] == 0 || temp[n][m] == 9) {
                    zwei = true;
                    n++;
                }
                else zwei = false;

                if (temp[n][m] == 0 || temp[n][m] == 9) {
                    drei = true;
                }
                else drei = false;

                if (eins == true && zwei == true && drei == true) {
                    passt = true;
                }
                else passt = false;
            }

            else if (groesse == 2 && groesse + n <= feldgroesse) {
                if (temp[n][m] == 0 || temp[n][m] == 9) {
                    eins = true;
                    n++;
                }
                else eins = false;

                if (temp[n][m] == 0 || temp[n][m] == 9) {
                    zwei = true;
                }
                else zwei = false;

                if (eins == true && zwei == true) {
                    passt = true;
                }
                else passt = false;
            }

            else {
                passt = false;
            }
        }

        if (vertikal == false) {
            if (groesse == 4 && groesse + m <= feldgroesse) {
                if (temp[n][m] == 0 || temp[n][m] == 9) {
                    eins = true;
                    m++;
                }
                else eins = false;

                if (temp[n][m] == 0 || temp[n][m] == 9) {
                    zwei = true;
                    m++;
                }
                else zwei = false;

                if (temp[n][m] == 0 || temp[n][m] == 9) {
                    drei = true;
                    m++;
                }
                else drei = false;

                if (temp[n][m] == 0 || temp[n][m] == 9) {
                    vier = true;
                }
                else vier = false;

                if (eins == true && zwei == true && drei == true && vier == true) {
                    passt = true;
                }
                else passt = false;
            }

            else if (groesse == 3 && groesse + m <= feldgroesse) {
                if (temp[n][m] == 0 || temp[n][m] == 9) {
                    eins = true;
                    m++;
                }
                else eins = false;

                if (temp[n][m] == 0 || temp[n][m] == 9) {
                    zwei = true;
                    m++;
                }
                else zwei = false;

                if (temp[n][m] == 0 || temp[n][m] == 9) {
                    drei = true;
                }
                else drei = false;

                if (eins == true && zwei == true && drei == true) {
                    passt = true;
                }
                else passt = false;
            }

            else if (groesse == 2 && groesse + m <= feldgroesse) {
                if (temp[n][m] == 0 || temp[n][m] == 9) {
                    eins = true;
                    m++;
                }
                else eins = false;

                if (temp[n][m] == 0 || temp[n][m] == 9) {
                    zwei = true;
                }
                else zwei = false;

                if (eins == true && zwei == true) {
                    passt = true;
                }
                else passt = false;
            }

            else {
                passt = false;
            }
        }

        return passt;
    }

    /**
     * Diese Methode setzt die Felder um das Schiff auf den Wert 4, sodass spaeter
     * diese Felder nicht mehr besetzt werden koennen (nur Felder mit dem Wert 0
     * koennen besetzt werden).
     * @param n
     * @param m
     * @author Kirsten und Serdar
     */
    public void testeSchiffUmgebung(int n, int m, int[][] temp) {

        if (n>0) {
            n--;
            if (temp[n][m] == 0) {
                temp[n][m] = 4;
            }
            n++;
        }
        if (m<feldgroesse-1) {
            m++;
            if (temp[n][m] == 0) {
                temp[n][m] = 4;
            }
            m--;
        }
        if (n<feldgroesse-1) {
            n++;
            if (temp[n][m] == 0) {
                temp[n][m] = 4;
            }
            n--;
        }
        if (m>0) {
            m--;
            if (temp[n][m] == 0) {
                temp[n][m] = 4;
            }
            m++;
        }
    }

    /**
     * Ueberprueft, ob ein Schiff versenkt wurde, indem von jedem getroffenen Schiffsteil die umliegenden
     * Punkte untersucht werden und daraufhin ueberprueft wird,
     * ob sich dort noch ein ungetroffenes Teil befindet.
     * @param n
     * @param m
     * @return
     */
    public boolean testeSchiffVersenkt(int n, int m, int[][] temp) {
        boolean versenkt;
        boolean a = false;
        boolean b = false;
        boolean c = false;
        boolean d = false;

        if (n>0) {
            n--;
            if (temp[n][m] != 1) {
                n++;
                a = true;
            }
            else n++;
        }
        if (n<9) {
            n++;
            if (temp[n][m] != 1) {
                n--;
                b = true;
            }
            else n--;
        }
        if (m>0) {
            m--;
            if (temp[n][m] != 1) {
                m++;
                c = true;
            }
            else m++;
        }
        if (m<0) {
            m++;
            if (temp[n][m] != 1) {
                m--;
                d = true;
            }
            else m--;
        }
        if (a == true && b == true && c == true && d == true) {
            versenkt = true;
        }
        else {
            versenkt = false;
        }
        return versenkt;
    }

    /**
     * Ueberschreibt die Arrays Spieler1 und Spieler2 mit den Daten des Arrays Temp
     */
    public void setzeSpielfeldSpieler1(int[][] temp) {
        for (int n = 0; n<feldgroesse; n++) {
            for (int m = 0; m<feldgroesse; m++) {
                spieler1[n][m] = temp[n][m];
            }
        }
    }

    public void setzeSpielfeldSpieler2(int[][] temp) {
        for (int n = 0; n<feldgroesse; n++) {
            for (int m = 0; m<feldgroesse; m++) {
                spieler2[n][m] = temp[n][m];
            }
        }
    }

    /**
     * Ueberschreibt das Array Temp mit den Daten vom Array des jeweiligen Spielers
     */
    public void setzeTemp(int[][] temp) {
        if (getSpieler() == 1) {
            for (int n = 0; n<feldgroesse; n++) {
                for (int m = 0; m<feldgroesse; m++) {
                    temp[n][m] = spieler2[n][m];
                }
            }
        }
        else if (getSpieler() == 2) {
            for (int n = 0; n<feldgroesse; n++) {
                for (int m = 0; m<feldgroesse; m++) {
                    temp[n][m] = spieler1[n][m];
                }
            }
        }
    }

    public void spielfeldZuruecksetzen(int [][] temp) {
        for (int i=0; i<feldgroesse; i++) {
            for (int j=0; j <feldgroesse; j++) {
                temp[i][j] = 0;
            }
        }
    }

    public boolean alleSchiffeGesetzt() {
        if (anzahlZweier == 0 && anzahlDreier == 0 && anzahlVierer == 0) {
            return true;
        }
        else return false;
    }

}
