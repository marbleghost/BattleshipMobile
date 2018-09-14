package ks.battleshipmobile;

import android.widget.Button;

/**
 * 0 = frei
 * 1 = Schiff gesetzt
 * 2 = Schiffsumgebung, welches an zwei Schiffe grenzt
 * 3 = Schiffsumgebung, wo nichts gesetzt werden darf
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

    int gesamtSchiffsteile = (anzahlVierer * viererSchiff) + (anzahlDreier * dreierSchiff) + (anzahlZweier * zweierSchiff);


    public int getGesamtSchiffsteile() {
        return gesamtSchiffsteile;
    }


    /**
     * Diese Methode sorgt dafuer, dass Schiffe entweder horizontal oder vertikal gesetzt werden koennen.
     * Die Methode guckt sich die Umliegenden Flaechen eines Schiffsteils an und wenn es ein angrenzendes Schiffateil entdeckt,
     * blockiert es je nach dem ob die Schiffsteile horizontal oder vertikal aneinander grenzen die anderen Richtungen.
     * @param n
     * @param m
     * @param temp
     */
    public void umgebungMarkieren(int n, int m, int [][] temp) {

        if (n>0) { //Wenn es nicht an der oberen Kante des Spielfeldes liegt.
            if (temp[n-1][m] == 1) {
                if (m>0) { //Wenn es nicht an der linken Kante des Spielfeldes liegt.
                    if (temp[n-1][m-1] == 3 || temp[n-1][m-1] == 2) {
                        temp[n-1][m-1] = 2;
                    }
                    else temp[n-1][m-1] = 3;
                    if (temp[n][m-1] != 3 && temp[n][m-1] != 2) {
                        temp[n][m-1] = 3;
                    }
                    else if (temp[n][m-1] == 3) {
                        temp[n][m-1] = 2;
                    }
                }
                if (m<7) {
                    if (temp[n-1][m+1] == 3 || temp[n-1][m+1] == 2) {
                        temp[n-1][m+1] = 2;
                    }
                    else temp[n-1][m+1] = 3;
                    if (temp[n][m+1] != 3 && temp[n][m+1] != 2) {
                        temp[n][m+1] = 3;
                    }
                    else if (temp[n][m+1] == 3) {
                        temp[n][m+1] = 2;
                    }
                }
            }
        }
        if (n<7) {
            if (temp[n+1][m] == 1) {
                if (m>0) {
                    if (temp[n+1][m-1] == 3 || temp[n+1][m-1] == 2) {
                        temp[n+1][m-1] = 2;
                    }
                    else temp[n+1][m-1] = 3;
                    if (temp[n][m-1] != 3 && temp[n][m-1] != 2) {
                        temp[n][m-1] = 3;
                    }
                    else if (temp[n][m-1] == 3) {
                        temp[n][m-1] = 2;
                    }
                }
                if (m<7) {
                    if (temp[n+1][m+1] == 3 || temp[n+1][m+1] == 2) {
                        temp[n+1][m+1] = 2;
                    }
                    else temp[n+1][m+1] = 3;
                    if (temp[n][m+1] != 3 && temp[n][m+1] != 2) {
                        temp[n][m+1] = 3;
                    }
                    else if (temp[n][m+1] == 3) {
                        temp[n][m+1] = 2;
                    }
                }
            }
        }
        if (m>0) {
            if (temp[n][m-1] == 1) {
                if (n>0) {
                    if (temp[n-1][m-1] == 3 || temp[n-1][m-1] == 2) {
                        temp[n-1][m-1] = 2;
                    }
                    else temp[n-1][m-1] = 3;
                    if (temp[n-1][m] != 3 && temp[n-1][m] != 2) {
                        temp[n-1][m] = 3;
                    }
                    else if (temp[n-1][m] == 3) {
                        temp[n-1][m] = 2;
                    }
                }
                if (n<7) {
                    if (temp[n+1][m-1] == 3 || temp[n+1][m-1] == 2) {
                        temp[n+1][m-1] = 2;
                    }
                    else temp[n+1][m-1] = 3;
                    if (temp[n+1][m] != 3 && temp[n+1][m] != 2) {
                        temp[n+1][m] = 3;
                    }
                    else if (temp[n+1][m] == 3) {
                        temp[n+1][m] = 2;
                    }
                }
            }
        }
        if (m<7) {
            if (temp[n][m+1] == 1) {
                if (n>0) {
                    if (temp[n-1][m+1] == 3 || temp[n-1][m+1] == 2) {
                        temp[n-1][m+1] = 2;
                    }
                    else temp[n-1][m+1] = 3;
                    if (temp[n-1][m] != 3 && temp[n-1][m] != 2) {
                        temp[n-1][m] = 3;
                    }
                    else if (temp[n-1][m] == 3) {
                        temp[n-1][m] = 2;
                    }
                }
                if (n<7) {
                    if (temp[n+1][m+1] == 3 || temp[n+1][m+1] == 2) {
                        temp[n+1][m+1] = 2;
                    }
                    else temp[n+1][m+1] = 3;
                    if (temp[n+1][m] != 3 && temp[n+1][m] != 2) {
                        temp[n+1][m] = 3;
                    }
                    else if (temp[n+1][m] == 3) {
                        temp[n+1][m] = 2;
                    }
                }
            }
        }
    }

    /**
     * Entfernt die Markierungen, die ein Schiffsteil umgibt.
     * Wird in der Schiffesetzen klasse im OnClick fuer die Buttons aufgerufen.
     * @param n
     * @param m
     * @param temp
     */
    public void markierungenEntfernen(int n, int m, int[][] temp) {

        if (esLiegtEinSchiffsteilAn(n, m, temp)) {
            if (n>0) {
                if (temp[n-1][m] == 3) {
                    temp[n-1][m] = 0;
                }
                else if (temp[n-1][m] == 2) {
                    temp[n-1][m] = 3;
                }
            }
            if (n<7) {
                if (temp[n+1][m] == 3) {
                    temp[n+1][m] = 0;
                }
                else if (temp[n+1][m] == 2) {
                    temp[n+1][m] = 3;
                }
            }
            if (m>0) {
                if (temp[n][m-1] == 3) {
                    temp[n][m-1] = 0;
                }
                else if (temp[n][m-1] == 2) {
                    temp[n][m-1] = 3;
                }
            }
            if (m<7) {
                if (temp[n][m+1] == 3) {
                    temp[n][m+1] = 0;
                }
                else if (temp[n][m+1] == 2) {
                    temp[n][m+1] = 3;
                }
            }
            for (int i=0; i<8; i++) {
                for (int j=0; j<8; j++) {
                    if (einzelnesSchiffsteilMitMarkierterUmgebung(i, j, temp)) {
                        if (i>0) {
                            if (temp[i-1][j] == 3) {
                                temp[i-1][j] = 0;
                            }
                            else if (temp[i-1][j] == 2) {
                                temp[i-1][j] = 0;
                            }
                        }
                        if (i<7) {
                            if (temp[i+1][j] == 3) {
                                temp[i+1][j] = 0;
                            }
                            else if (temp[i+1][j] == 2) {
                                temp[i+1][j] = 0;
                            }
                        }
                        if (j>0) {
                            if (temp[i][j-1] == 3) {
                                temp[i][j-1] = 0;
                            }
                            else if (temp[i][j-1] == 2) {
                                temp[i][j-1] = 0;
                            }
                        }
                        if (j<7) {
                            if (temp[i][j+1] == 3) {
                                temp[i][j+1] = 0;
                            }
                            else if (temp[i][j+1] == 2) {
                                temp[i][j+1] = 0;
                            }
                        }
                    }
                }
            }
        }
    }

    public void farbeAnpassen(int [][] tempInt, Button[][] tempButton) { //TODO: statt der Farbe soll ein Symbol erscheinen
        for (int i = 0; i<8; i++) {
            for (int j = 0; j<8; j++) {
                if (tempInt[i][j] == 0 || tempInt[i][j] == 3) {
                    tempButton[i][j].setBackgroundColor(0xFF33B5E5);
                }
                else if (tempInt[i][j] == 1) {
                    tempButton[i][j].setBackgroundColor(1);
                }
            }
        }
    }

    /**
     * Ueberprueft, ob der ein Schiffsteil platziert werden kann. Wenn an dem feld mehr als nur ein angrenzendes Schiffsteil liegt,
     * wird false zurueckgegeben.
     * @param n
     * @param m
     * @param temp
     * @return
     */
    public boolean platzIstBesetzbar(int n, int m, int[][] temp) {
        int k = 0;
        boolean oben = false; //Felder sind 0.
        boolean unten = false;
        boolean rechts = false;
        boolean links = false;

        if (temp[n][m] == 0) {
            if (n>0) {
                if (temp[n-1][m] == 1) {
                    k++;
                    oben = true;
                }
            }
            if (n<7) {
                if (temp[n+1][m] == 1) {
                    k++;
                    unten = true;
                }
            }
            if (m>0) {
                if (temp[n][m-1] == 1) {
                    k++;
                    links = true;
                }
            }
            if (m<7) {
                if (temp[n][m+1] == 1) {
                    k++;
                    rechts = true;
                }
            }

            if (k>2 || (oben == true && (rechts == true || links == true)) || (unten == true && (rechts == true || links == true))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Ueberprueft, ob ein Schiffsteil an ein anderes Teil grenzt, damit es, wenn es entfernt wird, keine Markierungen loescht,
     * die benoetigt werden.
     * Wird in markierungenEntfernen aufgerufen.
     * @param n
     * @param m
     * @param temp
     * @return
     */
    public boolean esLiegtEinSchiffsteilAn(int n, int m, int[][] temp) {
        if (n>0) {
            if (temp[n-1][m] == 1) {
                return true;
            }
        }
        if (n<7) {
            if (temp[n+1][m] == 1) {
                return true;
            }
        }
        if (m>0) {
            if (temp[n][m-1] == 1) {
                return true;
            }
        }
        if (m<7) {
            if (temp[n][m+1] == 1) {
                return true;
            }
        }
        return false;
    }

    /**
     * Ueberprueft, ob um ein bestimmtes Feld herum Markierungen liegen.
     * Diese Methode wird fuer die Methode einzelnesSchiffMitMarkierterUmgebung aufgerufen, damit man Markierungen von Schiffsteilen,
     * die an keinen anderen Schiffsteilen liegen, entfernen kann.
     * @param n
     * @param m
     * @param temp
     * @return
     */
    public boolean esLiegtEineMarkierungAn(int n, int m, int[][] temp) {
        if (n>0) {
            if (temp[n-1][m] == 3 || temp[n-1][m] == 2) {
                return true;
            }
        }
        if (n<7) {
            if (temp[n+1][m] == 3 || temp[n+1][m] == 2) {
                return true;
            }
        }
        if (m>0) {
            if (temp[n][m-1] == 3 || temp[n][m-1] == 2) {
                return true;
            }
        }
        if (m<7) {
            if (temp[n][m+1] == 3 || temp[n][m+1] == 2) {
                return true;
            }
        }
        return false;
    }

    /**
     * Wird benoetigt, wenn ein mittleres Schiffsteil eines laengeren Schiffs entfernt wird,
     * damit die markierten Felder um die Schiffsteile, welche an keinem anderen Schiffsteil liegen,
     * wieder freigegeben werden koennen.
     */
    public boolean einzelnesSchiffsteilMitMarkierterUmgebung(int n, int m, int[][] temp) {
        if (esLiegtEinSchiffsteilAn(n, m, temp) == false && temp[n][m] == 1) {
            if (esLiegtEineMarkierungAn(n, m, temp)) {
                return true;
            }
        }
        return false;
    }
}
