package ks.battleshipmobile;

import android.graphics.Color;
import android.widget.Button;

import java.util.Random;

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

    int[][] spieler1 = new int[8][8];
    int[][] spieler2 = new int[8][8];


    //Groesse der Schiffe
    final int viererSchiff = 4;
    final int dreierSchiff = 3;
    final int zweierSchiff = 2;

    //Anzahl der Schiffe
    int anzahlVierer = 1;
    int anzahlDreier = 2;
    int anzahlZweier = 3;

    Random rand = new Random();


    boolean passt;
    boolean treffer = false;
    boolean versenkt;

    // Werden benoetigt, um die Koordinaten der einzelnen Schiffsteile zu speichern, wenn ein Schiff versenkt wurde
    int an1, an2, an3, an4, an5, an6 = -1;
    int bn1, bn2, bn3, bn4, bn5, bn6 = -1;
    int cn1, cn2, cn3, cn4, cn5, cn6 = -1;
    int dn1, dn2, dn3, dn4, dn5, dn6 = -1;
    int am1, am2, am3, am4, am5, am6 = -1;
    int bm1, bm2, bm3, bm4, bm5, bm6 = -1;
    int cm1, cm2, cm3, cm4, cm5, cm6 = -1;
    int dm1, dm2, dm3, dm4, dm5, dm6 = -1;


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
    public void spielerWechsel(int spieler) {
        if (spieler < 2) {
            spieler++;
        } else {
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
        for (int n = 0; n < feldgroesse; n++) {
            for (int m = 0; m < feldgroesse; m++) {
                if (temp[n][m] != 1) {
                    zaehler++;
                }
            }
        }
        if (zaehler == feldgroesse * feldgroesse) {
            gewonnen = true;
        }
        return gewonnen;
    }

    /**
     * Methode, um die Schiffe je nach Groeße und Richtung zu setzen.
     */
    public void schiffeSetzen(int groesse, boolean vertikal, int n, int m, int[][] temp) {

        if (schiffPasst(groesse, vertikal, n, m, temp)) {
            for (int i = 0; i < groesse; i++) {
                if (vertikal == true) {

                    temp[n][m] = 1;
                    markiereSchiffUmgebung(n, m, temp);
                    n++;

                } else {
                    temp[n][m] = 1;
                    markiereSchiffUmgebung(n, m, temp);
                    m++;
                }
            }
        } else if (schiffPasst(groesse, vertikal, n, m, temp) == false) {
            System.err.println("Nicht genug Platz!");
        }
    }

    public void schiffSchatten(int markierung, int groesse, boolean vertikal, int n, int m, int[][] temp) {

        if (schiffPasst(groesse, vertikal, n, m, temp)) {
            for (int i = 0; i < groesse; i++) {
                if (vertikal == true) {
                    temp[n][m] = markierung;
                    n++;

                } else {
                    temp[n][m] = markierung;
                    m++;
                }
            }
        }
    }

    public void farbeAnpassen(int[][] tempInt, Button[][] tempButton) {
        for (int i = 0; i < feldgroesse; i++) {
            for (int j = 0; j < feldgroesse; j++) {
                if (tempInt[i][j] == 1) {
                    tempButton[i][j].setBackgroundColor(1);
                } else if (tempInt[i][j] == 9) {
                    tempButton[i][j].setBackgroundColor(0xFFDF33E5);
                } else if (tempInt[i][j] == 0) {
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
     *
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
            if (groesse == viererSchiff && groesse + n <= feldgroesse) {
                if (temp[n][m] == 0 || temp[n][m] == 9) {
                    eins = true;
                    n++;
                } else eins = false;

                if (temp[n][m] == 0 || temp[n][m] == 9) {
                    zwei = true;
                    n++;
                } else zwei = false;

                if (temp[n][m] == 0 || temp[n][m] == 9) {
                    drei = true;
                    n++;
                } else drei = false;

                if (temp[n][m] == 0 || temp[n][m] == 9) {
                    vier = true;
                } else vier = false;

                if (eins == true && zwei == true && drei == true && vier == true) {
                    passt = true;
                } else passt = false;
            } else if (groesse == dreierSchiff && groesse + n <= feldgroesse) {
                if (temp[n][m] == 0 || temp[n][m] == 9) {
                    eins = true;
                    n++;
                } else eins = false;

                if (temp[n][m] == 0 || temp[n][m] == 9) {
                    zwei = true;
                    n++;
                } else zwei = false;

                if (temp[n][m] == 0 || temp[n][m] == 9) {
                    drei = true;
                } else drei = false;

                if (eins == true && zwei == true && drei == true) {
                    passt = true;
                } else passt = false;
            } else if (groesse == zweierSchiff && groesse + n <= feldgroesse) {
                if (temp[n][m] == 0 || temp[n][m] == 9) {
                    eins = true;
                    n++;
                } else eins = false;

                if (temp[n][m] == 0 || temp[n][m] == 9) {
                    zwei = true;
                } else zwei = false;

                if (eins == true && zwei == true) {
                    passt = true;
                } else passt = false;
            } else {
                passt = false;
            }
        }

        if (vertikal == false) {
            if (groesse == 4 && groesse + m <= feldgroesse) {
                if (temp[n][m] == 0 || temp[n][m] == 9) {
                    eins = true;
                    m++;
                } else eins = false;

                if (temp[n][m] == 0 || temp[n][m] == 9) {
                    zwei = true;
                    m++;
                } else zwei = false;

                if (temp[n][m] == 0 || temp[n][m] == 9) {
                    drei = true;
                    m++;
                } else drei = false;

                if (temp[n][m] == 0 || temp[n][m] == 9) {
                    vier = true;
                } else vier = false;

                if (eins == true && zwei == true && drei == true && vier == true) {
                    passt = true;
                } else passt = false;
            } else if (groesse == 3 && groesse + m <= feldgroesse) {
                if (temp[n][m] == 0 || temp[n][m] == 9) {
                    eins = true;
                    m++;
                } else eins = false;

                if (temp[n][m] == 0 || temp[n][m] == 9) {
                    zwei = true;
                    m++;
                } else zwei = false;

                if (temp[n][m] == 0 || temp[n][m] == 9) {
                    drei = true;
                } else drei = false;

                if (eins == true && zwei == true && drei == true) {
                    passt = true;
                } else passt = false;
            } else if (groesse == 2 && groesse + m <= feldgroesse) {
                if (temp[n][m] == 0 || temp[n][m] == 9) {
                    eins = true;
                    m++;
                } else eins = false;

                if (temp[n][m] == 0 || temp[n][m] == 9) {
                    zwei = true;
                } else zwei = false;

                if (eins == true && zwei == true) {
                    passt = true;
                } else passt = false;
            } else {
                passt = false;
            }
        }

        return passt;
    }

    /**
     * Diese Methode setzt die Felder um das Schiff auf den Wert 4, sodass spaeter
     * diese Felder nicht mehr besetzt werden koennen (nur Felder mit dem Wert 0
     * koennen besetzt werden).
     *
     * @param n
     * @param m
     * @author Kirsten und Serdar
     */
    public void markiereSchiffUmgebung(int n, int m, int[][] temp) {

        if (n > 0) {
            n--;
            if (temp[n][m] == 0) {
                temp[n][m] = 4;
            }
            n++;
        }
        if (m < feldgroesse - 1) {
            m++;
            if (temp[n][m] == 0) {
                temp[n][m] = 4;
            }
            m--;
        }
        if (n < feldgroesse - 1) {
            n++;
            if (temp[n][m] == 0) {
                temp[n][m] = 4;
            }
            n--;
        }
        if (m > 0) {
            m--;
            if (temp[n][m] == 0) {
                temp[n][m] = 4;
            }
            m++;
        }
    }

    /**
     * Ueberschreibt die Arrays Spieler1 und Spieler2 mit den Daten des Arrays Temp
     */
    public void setzeSpielfeldSpieler1(int[][] temp) {
        for (int n = 0; n < feldgroesse; n++) {
            for (int m = 0; m < feldgroesse; m++) {
                spieler1[n][m] = temp[n][m];
            }
        }
    }

    public void setzeSpielfeldSpieler2(int[][] temp) {
        for (int n = 0; n < feldgroesse; n++) {
            for (int m = 0; m < feldgroesse; m++) {
                spieler2[n][m] = temp[n][m];
            }
        }
    }

    /**
     * Ueberschreibt das Array Temp mit den Daten vom Array des jeweiligen Spielers
     */
    public void setzeTemp(int[][] temp) {
        if (getSpieler() == 1) {
            for (int n = 0; n < feldgroesse; n++) {
                for (int m = 0; m < feldgroesse; m++) {
                    temp[n][m] = spieler2[n][m];
                }
            }
        } else if (getSpieler() == 2) {
            for (int n = 0; n < feldgroesse; n++) {
                for (int m = 0; m < feldgroesse; m++) {
                    temp[n][m] = spieler1[n][m];
                }
            }
        }
    }

    public void spielfeldZuruecksetzen(int[][] temp) {
        for (int i = 0; i < feldgroesse; i++) {
            for (int j = 0; j < feldgroesse; j++) {
                temp[i][j] = 0;
            }
        }
    }

    public boolean alleSchiffeGesetzt() {
        if (anzahlZweier == 0 && anzahlDreier == 0 && anzahlVierer == 0) {
            return true;
        } else return false;
    }


    public void schussAbgefeuert(int n, int m, int[][] temp, Button[][] tempButton) {
        if (temp[n][m] == 1) {
            temp[n][m] = 2;
            tempButton[n][m].setBackgroundColor(0xFF000000); //TODO: Farbe gegen Bild austauschen
            treffer = true;
        } else if (temp[n][m] == 0 || temp[n][m] == 4) {
            temp[n][m] = 3;
            tempButton[n][m].setText("X"); //TODO: String gegen Bild austauschen
            treffer = false;
        }
    }


    public void zufallsPlatzierung(int[][] temp) {
        boolean vertikal;

        while (anzahlVierer > 0 || anzahlDreier > 0 || anzahlZweier > 0) {
            int i = rand.nextInt(2);

            if (i < 1) {
                vertikal = true;
            } else vertikal = false;


            if (anzahlVierer > 0) {
                int n = rand.nextInt(8);
                int m = rand.nextInt(8);

                schiffeSetzen(viererSchiff, vertikal, n, m, temp);

                if (getPasst() == true) {
                    anzahlVierer--;
                }
            }

            if (anzahlDreier > 0) {
                int n = rand.nextInt(8);
                int m = rand.nextInt(8);

                schiffeSetzen(dreierSchiff, vertikal, n, m, temp);

                if (getPasst() == true) {
                    anzahlDreier--;
                }
            }

            if (anzahlZweier > 0) {
                int n = rand.nextInt(8);
                int m = rand.nextInt(8);

                schiffeSetzen(zweierSchiff, vertikal, n, m, temp);

                if (getPasst() == true) {
                    anzahlZweier--;
                }
            }
        }
    }

    public void kiSchiffeSetzen(int[][] temp) {
        setSchiffsanzahl();
        zufallsPlatzierung(temp);
    }

    public void markierungSchiffVersenkt(int n, int m, Button[][] tempButton) {
        tempButton[n][m].setBackgroundColor(0xFF00FF00);
    }

    public void spielfeldStatus(int [][] temp, Button [][] tempButton) {
        for (int i=0; i<feldgroesse; i++) {
            for (int j = 0; j < feldgroesse; j++) {
                if (temp[i][j] == 0 || temp [i][j] == 1 || temp [i][j] == 4) {
                    tempButton[i][j].setBackgroundColor(0xFF33B5E5);
                    tempButton[i][j].setText("");
                }
                else if (temp[i][j] == 2) {
                    tempButton[i][j].setBackgroundColor(0xFF000000);
                    tempButton[i][j].setText("");
                }
                else if (temp[i][j] == 6) {
                    tempButton[i][j].setBackgroundColor(0xFF00FF00);
                    tempButton[i][j].setText("");
                }
                else if (temp[i][j] == 3) {
                    tempButton[i][j].setText("X");
                }
            }
        }
    }

    public boolean testeSchiffVersenkt(int n, int m, int[][] temp, Button[][] tempButton) {
        boolean a = false;
        boolean b = false;
        boolean c = false;
        boolean d = false;
        int tempn = n;
        int tempm = m;

        an1 = -1;
        an2 = -1;
        an3 = -1;
        an4 = -1;
        an5 = -1;
        an6 = -1;
        bn1 = -1;
        bn2 = -1;
        bn3 = -1;
        bn4 = -1;
        bn5 = -1;
        bn6 = -1;
        cn1 = -1;
        cn2 = -1;
        cn3 = -1;
        cn4 = -1;
        cn5 = -1;
        cn6 = -1;
        dn1 = -1;
        dn2 = -1;
        dn3 = -1;
        dn4 = -1;
        dn5 = -1;
        dn6 = -1;
        am1 = -1;
        am2 = -1;
        am3 = -1;
        am4 = -1;
        am5 = -1;
        am6 = -1;
        bm1 = -1;
        bm2 = -1;
        bm3 = -1;
        bm4 = -1;
        bm5 = -1;
        bm6 = -1;
        cm1 = -1;
        cm2 = -1;
        cm3 = -1;
        cm4 = -1;
        cm5 = -1;
        cm6 = -1;
        dm1 = -1;
        dm2 = -1;
        dm3 = -1;
        dm4 = -1;
        dm5 = -1;
        dm6 = -1;

        if (tempn > 0) {
            tempn--; // Das obere Nachbarfeld wird untersucht
            if (temp[tempn][tempm] != 1 && temp[tempn][tempm] != 6) { // Wenn kein ungetroffenes Schiffsteil anliegt und auch kein versenktes Schiff
                if (temp[tempn][tempm] == 2) { // Wenn das Feld auch schon getroffen wurde, wird das Feld untersucht, welches darueber liegt
                    an1 = tempn;
                    am1 = tempm;
                    if (tempn > 0) {
                        tempn--;
                        if (temp[tempn][tempm] == 3 || temp[tempn][tempm] == 4) { // Falls das Feld kein Schiffteil entdeckt, wird die Arbeit nach oben hin beendet
                            a = true;
                            tempn = n;
                        } else if (temp[tempn][tempm] == 2) {
                            an2 = tempn;
                            am2 = tempm;
                            if (tempn > 0) {
                                tempn--;
                                if (temp[tempn][tempm] == 3 || temp[tempn][tempm] == 4) {
                                    a = true;
                                    tempn = n;
                                } else if (temp[tempn][tempm] == 2) {
                                    an3 = tempn;
                                    am3 = tempm;
                                    if (tempn > 0) {
                                        tempn--;
                                        if (temp[tempn][tempm] == 3 || temp[tempn][tempm] == 4) {
                                            a = true;
                                            tempn = n;
                                        } else if (temp[tempn][tempm] == 2) {
                                            an4 = tempn;
                                            am4 = tempm;
                                            if (tempn > 0) {
                                                tempn--;
                                                if (temp[tempn][tempm] == 3 || temp[tempn][tempm] == 4) {
                                                    a = true;
                                                    tempn = n;
                                                } else if (temp[tempn][tempm] == 2) {
                                                    an5 = tempn;
                                                    am5 = tempm;
                                                    if (tempn > 0) {
                                                        tempn--;
                                                        if (temp[tempn][tempm] == 3 || temp[tempn][tempm] == 4) {
                                                            a = true;
                                                            tempn = n;
                                                        } else if (temp[tempn][tempm] == 2) {
                                                            an6 = tempn;
                                                            am6 = tempm;
                                                            if (tempn > 0) {
                                                                tempn--;
                                                                if (temp[tempn][tempm] == 3 || temp[tempn][tempm] == 4) {
                                                                    a = true;
                                                                    tempn = n;
                                                                }
                                                            } else {
                                                                a = true;
                                                            }
                                                        } else a = false;
                                                    } else {
                                                        a = true;
                                                    }
                                                } else a = false;
                                            } else {
                                                a = true;
                                            }
                                        } else a = false;
                                    } else {
                                        a = true;
                                    }
                                } else a = false;
                            } else {
                                a = true;
                            }
                        } else a = false;
                    } else {
                        a = true;
                    }
                } else {
                    a = true;
                }
            } else {
                a = false;
            }

        } else {
            a = true;
        }

        tempn = n;
        if (tempn < feldgroesse-1) {
            tempn++; // Das untere Nachbarfeld wird untersucht
            if (temp[tempn][tempm] != 1 && temp[tempn][tempm] != 6) {
                if (temp[tempn][tempm] == 2) {
                    bn1 = tempn;
                    bm1 = tempm;
                    if (tempn < feldgroesse-1) {
                        tempn++;
                        if (temp[tempn][tempm] == 3 || temp[tempn][tempm] == 4) {
                            b = true;
                            tempn = n;
                        } else if (temp[tempn][tempm] == 2) {
                            bn2 = tempn;
                            bm2 = tempm;
                            if (tempn < feldgroesse-1) {
                                tempn++;
                                if (temp[tempn][tempm] == 3 || temp[tempn][tempm] == 4) {
                                    b = true;
                                    tempn = n;
                                } else if (temp[tempn][tempm] == 2) {
                                    bn3 = tempn;
                                    bm3 = tempm;
                                    if (tempn < feldgroesse-1) {
                                        tempn++;
                                        if (temp[tempn][tempm] == 3 || temp[tempn][tempm] == 4) {
                                            b = true;
                                            tempn = n;
                                        } else if (temp[tempn][tempm] == 2) {
                                            bn4 = tempn;
                                            bm4 = tempm;
                                            if (tempn < feldgroesse-1) {
                                                tempn++;
                                                if (temp[tempn][tempm] == 3 || temp[tempn][tempm] == 4) {
                                                    b = true;
                                                    tempn = n;
                                                } else if (temp[tempn][tempm] == 2) {
                                                    bn5 = tempn;
                                                    bm5 = tempm;
                                                    if (tempn < feldgroesse-1) {
                                                        tempn++;
                                                        if (temp[tempn][tempm] == 3 || temp[tempn][tempm] == 4) {
                                                            b = true;
                                                            tempn = n;
                                                        } else if (temp[tempn][tempm] == 2) {
                                                            bn6 = tempn;
                                                            bm6 = tempm;
                                                            if (tempn < feldgroesse-1) {
                                                                tempn++;
                                                                if (temp[tempn][tempm] == 3 || temp[tempn][tempm] == 4) {
                                                                    b = true;
                                                                    tempn = n;
                                                                }
                                                            } else {
                                                                b = true;
                                                            }
                                                        } else b = false;
                                                    } else {
                                                        b = true;
                                                    }
                                                } else b = false;
                                            } else {
                                                b = true;
                                            }
                                        } else b = false;
                                    } else {
                                        b = true;
                                    }
                                } else b = false;
                            } else {
                                b = true;
                            }
                        } else b = false;
                    } else {
                        b = true;
                    }
                } else {
                    b = true;
                }

            } else {
                b = false;
            }
        } else {
            b = true;
        }

        tempn = n;
        if (tempm > 0) {
            tempm--; // Das linke Nachbarfeld wird untersucht.
            if (temp[tempn][tempm] != 1 && temp[tempn][tempm] != 6) {
                if (temp[tempn][tempm] == 2) {
                    cn1 = tempn;
                    cm1 = tempm;
                    if (tempm > 0) {
                        tempm--;
                        if (temp[tempn][tempm] == 3 || temp[tempn][tempm] == 4) {
                            c = true;
                            tempm = m;
                        } else if (temp[tempn][tempm] == 2) {
                            cn2 = tempn;
                            cm2 = tempm;
                            if (tempm > 0) {
                                tempm--;
                                if (temp[tempn][tempm] == 3 || temp[tempn][tempm] == 4) {
                                    c = true;
                                    tempm = m;
                                } else if (temp[tempn][tempm] == 2) {
                                    cn3 = tempn;
                                    cm3 = tempm;
                                    if (tempm > 0) {
                                        tempm--;
                                        if (temp[tempn][tempm] == 3 || temp[tempn][tempm] == 4) {
                                            c = true;
                                            tempm = m;
                                        } else if (temp[tempn][tempm] == 2) {
                                            cn4 = tempn;
                                            cm4 = tempm;
                                            if (tempm > 0) {
                                                tempm--;
                                                if (temp[tempn][tempm] == 3 || temp[tempn][tempm] == 4) {
                                                    c = true;
                                                    tempm = m;
                                                } else if (temp[tempn][tempm] == 2) {
                                                    cn5 = tempn;
                                                    cm5 = tempm;
                                                    if (tempm > 0) {
                                                        tempm--;
                                                        if (temp[tempn][tempm] == 3 || temp[tempn][tempm] == 4) {
                                                            c = true;
                                                            tempm = m;
                                                        } else if (temp[tempn][tempm] == 2) {
                                                            cn6 = tempn;
                                                            cm6 = tempm;
                                                            if (tempm > 0) {
                                                                tempm--;
                                                                if (temp[tempn][tempm] == 3 || temp[tempn][tempm] == 4) {
                                                                    c = true;
                                                                    tempm = m;
                                                                }
                                                            } else {
                                                                c = true;
                                                            }
                                                        } else c = false;
                                                    } else {
                                                        c = true;
                                                    }
                                                } else c = false;
                                            } else {
                                                c = true;
                                            }
                                        } else c = false;
                                    } else {
                                        c = true;
                                    }
                                } else c = false;
                            } else {
                                c = true;
                            }
                        } else c = false;
                    } else {
                        c = true;
                    }
                } else {
                    c = true;
                }
            } else {
                c = false;
            }
        } else {
            c = true;
        }

        tempm = m;
        if (tempm < feldgroesse-1) {
            tempm++; //Das rechte Nachbarfeld wird untersucht
            if (temp[tempn][tempm] != 1 && temp[tempn][tempm] != 6) {
                if (temp[tempn][tempm] == 2) {
                    dn1 = tempn;
                    dm1 = tempm;
                    if (tempm < feldgroesse-1) {
                        tempm++;
                        if (temp[tempn][tempm] == 3 || temp[tempn][tempm] == 4) {
                            d = true;
                            tempm = m;
                        } else if (temp[tempn][tempm] == 2) {
                            dn2 = tempn;
                            dm2 = tempm;
                            if (tempm < feldgroesse-1) {
                                tempm++;
                                if (temp[tempn][tempm] == 3 || temp[tempn][tempm] == 4) {
                                    d = true;
                                    tempm = m;
                                } else if (temp[tempn][tempm] == 2) {
                                    dn3 = tempn;
                                    dm3 = tempm;
                                    if (tempm < feldgroesse-1) {
                                        tempm++;
                                        if (temp[tempn][tempm] == 3 || temp[tempn][tempm] == 4) {
                                            d = true;
                                            tempm = m;
                                        } else if (temp[tempn][tempm] == 2) {
                                            dn4 = tempn;
                                            dm4 = tempm;
                                            if (tempm < feldgroesse-1) {
                                                tempm++;
                                                if (temp[tempn][tempm] == 3 || temp[tempn][tempm] == 4) {
                                                    d = true;
                                                    tempm = m;
                                                } else if (temp[tempn][tempm] == 2) {
                                                    dn5 = tempn;
                                                    dm5 = tempm;
                                                    if (tempm < feldgroesse-1) {
                                                        tempm++;
                                                        if (temp[tempn][tempm] == 3 || temp[tempn][tempm] == 4) {
                                                            d = true;
                                                            tempm = m;
                                                        } else if (temp[tempn][tempm] == 2) {
                                                            dn6 = tempn;
                                                            dm6 = tempm;
                                                            if (tempm < feldgroesse-1) {
                                                                tempm++;
                                                                if (temp[tempn][tempm] == 3 || temp[tempn][tempm] == 4) {
                                                                    d = true;
                                                                    tempm = m;
                                                                }
                                                            } else {
                                                                d = true;
                                                            }
                                                        } else d = false;
                                                    } else {
                                                        d = true;
                                                    }
                                                } else d = false;
                                            } else {
                                                d = true;
                                            }
                                        } else d = false;
                                    } else {
                                        d = true;
                                    }
                                } else d = false;
                            } else {
                                d = true;
                            }
                        } else d = false;
                    } else {
                        d = true;
                    }
                } else {
                    d = true;
                }
            } else {
                tempm = m;
                d = false;
            }
        } else {
            d = true;
        }


        if (a == true && b == true && c == true && d == true) {
            versenkt = true;
            temp[n][m] = 6;
            markierungSchiffVersenkt(n, m, tempButton );
            if (an1 != -1 && am1 != -1) {
                markierungSchiffVersenkt(an1, am1, tempButton);
                temp[an1][am1] = 6;
                an1 = -1;
                am1 = -1;
            }

            if (an2 != -1 && am2 != -1) {
                markierungSchiffVersenkt(an2, am2, tempButton);
                temp[an2][am2] = 6;
                an2 = -1;
                am2 = -1;
            }

            if (an3 != -1 && am3 != -1) {
                markierungSchiffVersenkt(an3, am3, tempButton);
                temp[an3][am3] = 6;
                an3 = -1;
                am3 = -1;
            }

            if (an4 != -1 && am4 != -1) {
                markierungSchiffVersenkt(an4, am4, tempButton);
                temp[an4][am4] = 6;
                an4 = -1;
                am4 = -1;
            }

            if (an5 != -1 && am5 != -1) {
                markierungSchiffVersenkt(an5, am5, tempButton);
                temp[an5][am5] = 6;
                an5 = -1;
                am5 = -1;
            }

            if (an6 != -1 && am6 != -1) {
                markierungSchiffVersenkt(an6, am6, tempButton);
                temp[an6][am6] = 6;
                an6 = -1;
                am6 = -1;
            }

            if (bn1 != -1 && bm1 != -1) {
                markierungSchiffVersenkt(bn1, bm1, tempButton);
                temp[bn1][bm1] = 6;
                bn1 = -1;
                bm1 = -1;
            }

            if (bn2 != -1 && bm2 != -1) {
                markierungSchiffVersenkt(bn2, bm2, tempButton);
                temp[bn2][bm2] = 6;
                bn2 = -1;
                bm2 = -1;
            }

            if (bn3 != -1 && bm3 != -1) {
                markierungSchiffVersenkt(bn3, bm3, tempButton);
                temp[bn3][bm3] = 6;
                bn3 = -1;
                bm3 = -1;
            }

            if (bn4 != -1 && bm4 != -1) {
                markierungSchiffVersenkt(bn4, bm4, tempButton);
                temp[bn4][bm4] = 6;
                bn4 = -1;
                bm4 = -1;
            }

            if (bn5 != -1 && bm5 != -1) {
                markierungSchiffVersenkt(bn5, bm5, tempButton);
                temp[bn5][bm5] = 6;
                bn5 = -1;
                bm5 = -1;
            }

            if (bn6 != -1 && bm6 != -1) {
                markierungSchiffVersenkt(bn6, bm6, tempButton);
                temp[bn6][bm6] = 6;
                bn6 = -1;
                bm6 = -1;
            }

            if (cn1 != -1 && cm1 != -1) {
                markierungSchiffVersenkt(cn1, cm1, tempButton);
                temp[cn1][cm1] = 6;
                cn1 = -1;
                cm1 = -1;
            }

            if (cn2 != -1 && cm2 != -1) {
                markierungSchiffVersenkt(cn2, cm2, tempButton);
                temp[cn2][cm2] = 6;
                cn2 = -1;
                cm2 = -1;
            }

            if (cn3 != -1 && cm3 != -1) {
                markierungSchiffVersenkt(cn3, cm3, tempButton);
                temp[cn3][cm3] = 6;
                cn3 = -1;
                cm3 = -1;
            }

            if (cn4 != -1 && cm4 != -1) {
                markierungSchiffVersenkt(cn4, cm4, tempButton);
                temp[cn4][cm4] = 6;
                cn4 = -1;
                cm4 = -1;
            }

            if (cn5 != -1 && cm5 != -1) {
                markierungSchiffVersenkt(cn5, cm5, tempButton);
                temp[cn5][cm5] = 6;
                cn5 = -1;
                cm5 = -1;
            }

            if (cn6 != -1 && cm6 != -1) {
                markierungSchiffVersenkt(cn6, cm6, tempButton);
                temp[cn6][cm6] = 6;
                cn6 = -1;
                cm6 = -1;
            }

            if (dn1 != -1 && dm1 != -1) {
                markierungSchiffVersenkt(dn1, dm1, tempButton);
                temp[dn1][dm1] = 6;
                dn1 = -1;
                dm1 = -1;
            }

            if (dn2 != -1 && dm2 != -1) {
                markierungSchiffVersenkt(dn2, dm2, tempButton);
                temp[dn2][dm2] = 6;
                dn2 = -1;
                dm2 = -1;
            }

            if (dn3 != -1 && dm3 != -1) {
                markierungSchiffVersenkt(dn3, dm3, tempButton);
                temp[dn3][dm3] = 6;
                dn3 = -1;
                dm3 = -1;
            }

            if (dn4 != -1 && dm4 != -1) {
                markierungSchiffVersenkt(dn4, dm4, tempButton);
                temp[dn4][dm4] = 6;
                dn4 = -1;
                dm4 = -1;
            }

            if (dn5 != -1 && dm5 != -1) {
                markierungSchiffVersenkt(dn5, dm5, tempButton);
                temp[dn5][dm5] = 6;
                dn5 = -1;
                dm5 = -1;
            }

            if (dn6 != -1 && dm6 != -1) {
                markierungSchiffVersenkt(dn6, dm6, tempButton);
                temp[dn6][dm6] = 6;
                dn6 = -1;
                dm6 = -1;
            }
        } else {
            versenkt = false;
        }
        return versenkt;

    }
}
