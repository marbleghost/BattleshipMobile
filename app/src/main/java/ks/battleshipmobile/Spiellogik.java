package ks.battleshipmobile;

import android.widget.Button;

/**
 * 0 = frei
 * 1 = Schiff gesetzt
 * -1 = Gesetztes Schiffsteil wieder zurueckgezogen (Wird benoetigt, um die umliegenden Flaechen effizient wieder freizugeben.
 * 2 = Schiffsumgebung, wo nichts gesetzt werden darf
 * 3 = Umgebung des Schiffteils bereits als unsetzbar markiert
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
                    temp[n-1][m-1] = 3;
                    if (temp[n][m-1] != 3) {
                        temp[n][m-1] = 3;
                    }
                }
                if (m<7) {
                    temp[n-1][m+1] = 3;
                    if (temp[n][m+1] != 3) {
                        temp[n][m+1] = 3;
                    }
                }
            }
        }
        if (n<7) {
            if (temp[n+1][m] == 1) {
                if (m>0) {
                    temp[n+1][m-1] = 3;
                    if (temp[n][m-1] != 3) {
                        temp[n][m-1] = 3;
                    }
                }
                if (m<7) {
                    temp[n+1][m+1] = 3;
                    if (temp[n][m+1] != 3) {
                        temp[n][m+1] = 3;
                    }
                }
            }
        }
        if (m>0) {
            if (temp[n][m-1] == 1) {
                if (n>0) {
                    temp[n-1][m-1] = 3;
                    if (temp[n-1][m] != 3) {
                        temp[n-1][m] = 3;
                    }
                }
                if (n<7) {
                    temp[n+1][m-1] = 3;
                    if (temp[n+1][m] != 3) {
                        temp[n+1][m] = 3;
                    }
                }
            }
        }
        if (m<7) {
            if (temp[n][m+1] == 1) {
                if (n>0) {
                    temp[n-1][m+1] = 3;
                    if (temp[n-1][m] != 3) {
                        temp[n-1][m] = 3;
                    }
                }
                if (n<7) {
                    temp[n+1][m+1] = 3;
                    if (temp[n+1][m] != 3) {
                        temp[n+1][m] = 3;
                    }
                }
            }
        }
    }

    public void markierungenEntfernen(int n, int m, int[][] temp) {

        if (n>0) {
            if (temp[n-1][m] == 3) {
                temp[n-1][m] = 0;
            }
        }
        if (n<7) {
            if (temp[n+1][m] == 3) {
                temp[n+1][m] = 0;
            }
        }
        if (m>0) {
            if (temp[n][m-1] == 3) {
                temp[n][m-1] = 0;
            }
        }
        if (m<7) {
            if (temp[n][m+1] == 3) {
                temp[n][m+1] = 0;
            }
        }
    }

    public void farbeAnpassen(int [][] tempInt, Button[][] tempButton) {
        for (int i = 0; i<7; i++) {
            for (int j = 0; j<7; j++) {
                if (tempInt[i][j] == 0 || tempInt[i][j] == 3) {
                    tempButton[i][j].setBackgroundColor(0xFF33B5E5);
                }
                else if (tempInt[i][j] == 1) {
                    tempButton[i][j].setBackgroundColor(1);
                }
            }
        }
    }

}
