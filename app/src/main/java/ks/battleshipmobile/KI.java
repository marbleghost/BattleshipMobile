package ks.battleshipmobile;

import android.widget.Button;

import java.util.Random;

/**
 * Klasse welche die drei KIs des Spiels beinhaltet
 */
public class KI {

    Spiellogik logik = new Spiellogik();
    Random random = new Random();

    int tempn = -1;
    int tempm = -1;
    int ursprungsn = -1;
    int ursprungsm = -1;

    boolean vertikal = false;
    boolean horizontal = false;

    //Diese Methode zieht die Zufallszahlen
    public int randomInt() {
        return random.nextInt(8);
    }

    /**
     * Die normale KI.
     * Setzt zufaellig ihre Schuesse und merk sich nicht ob bereits ein Teil getroffen ist
     * @param tempInt
     * @param tempButton
     * @param versuche
     */
    public void kiLeicht(int [][] tempInt, Button [][] tempButton, int versuche) {

        while (versuche != 0) {
            int n = randomInt();
            int m = randomInt();

            if (tempInt[n][m] != 2 && tempInt[n][m] != 3 && tempInt[n][m] != 6) {
                logik.schussAbgefeuert(n, m, tempInt, tempButton);
                if (logik.treffer == false) {
                    versuche--;
                }
                else {
                    logik.testeSchiffVersenkt(n, m, tempInt, tempButton);
                }
            }
        }
    }

    /**
     * Schwierige KI.
     * Merkt sich wo sie getroffe hat und laueft danach die Stellen drum herum ab.
     * Sie erkennt ob ein Schiff vertikal oder horizontal gesetzt worden ist, aber erst
     * wenn sie wie ein menschlicher Spieler auch, mindestens ein weiteres Teil des Schiffes
     * dazu gefunden hat.
     * @param tempInt
     * @param tempButton
     * @param versuche
     */
    public void kiMittel(int [][] tempInt, Button [][] tempButton, int versuche) {
        int n = randomInt();
        int m = randomInt();

        while (versuche != 0) {

            if (tempn == -1 && tempm == -1) {
                if (bereichBeschiessbar(n, m, tempInt)) {
                    logik.schussAbgefeuert(n, m, tempInt, tempButton);
                    System.out.println(n + " " + m);
                    System.out.println(tempInt[n][m]);

                    if (logik.treffer) {
                        tempn = n;
                        tempm = m;
                        ursprungsn = n;
                        ursprungsm = m;
                    }
                    else {
                        versuche--;
                        break;
                    }
                }
                else {
                    n = randomInt();
                    m = randomInt();
                }

            }
            else {
                if (tempn > 0 && horizontal == false) {
                    if (bereichBeschiessbar(tempn-1, tempm, tempInt)) {
                        logik.schussAbgefeuert(tempn-1, tempm, tempInt, tempButton);
                        System.out.println(tempn-1 + " " + tempm);
                        System.out.println(tempInt[tempn-1][tempm]);
                        if (logik.treffer) {
                            if (logik.testeSchiffVersenkt(tempn - 1, tempm, tempInt, tempButton)) {
                                System.out.println("schiff versenkt");
                                tempn = -1;
                                tempm = -1;
                                ursprungsn = -1;
                                ursprungsm = -1;
                                vertikal = false;

                                if (logik.gewonnen(tempInt)) {
                                    System.out.println("KI hat gewonnen");
                                }

                            }
                            else {
                                if (tempn != 1) {//Wird benoetigt, da er sonst in eine Schleife geraet, wenn oben links ein Schiff versenkt werden muss.
                                    tempn = tempn - 1;
                                }

                                vertikal = true;
                            }

                        }
                        else {
                            versuche--;
                            break;
                        }
                    }
                    else if ((vertikal && tempInt[tempn-1][tempm] == 3) || (vertikal && tempn == 1 && tempInt[tempn-1][tempm] == 2 && bereichBeschiessbar(ursprungsn+1, tempm, tempInt))) {
                        tempn = ursprungsn;
                        tempm = ursprungsm;
                    }
                }
                if (tempn < logik.feldgroesse - 1 && horizontal == false && tempn != -1) {
                    if (bereichBeschiessbar(tempn + 1, tempm, tempInt)) {
                        logik.schussAbgefeuert(tempn+1, tempm, tempInt, tempButton);
                        System.out.println(tempn+1 + " " + tempm);
                        System.out.println(tempInt[tempn+1][tempm]);
                        if (logik.treffer) {
                            if (logik.testeSchiffVersenkt(tempn + 1, tempm, tempInt, tempButton)) {
                                System.out.println("schiff versenkt");
                                tempn = -1;
                                tempm = -1;
                                ursprungsn = -1;
                                ursprungsm = -1;
                                vertikal = false;

                                if (logik.gewonnen(tempInt)) {
                                    System.out.println("KI hat gewonnen");
                                }

                            }
                            else {
                                tempn = tempn + 1;
                                vertikal = true;
                            }

                        }
                        else {
                            versuche--;
                            break;
                        }
                    }
                    else if ((vertikal && tempInt[tempn+1][tempm] == 3) || tempn == 7) {
                        tempn = ursprungsn;
                        tempm = ursprungsm;
                    }
                }
                if (tempm > 0 && vertikal == false) {
                    if (bereichBeschiessbar(tempn, tempm-1, tempInt)) {
                        logik.schussAbgefeuert(tempn, tempm-1, tempInt, tempButton);
                        System.out.println(tempn + " " + (tempm-1));
                        System.out.println(tempInt[tempn][tempm-1]);
                        if (logik.treffer) {
                            if (logik.testeSchiffVersenkt(tempn, tempm-1, tempInt, tempButton)) {
                                System.out.println("schiff versenkt");
                                tempn = -1;
                                tempm = -1;
                                ursprungsn = -1;
                                ursprungsm = -1;
                                horizontal = false;

                                if (logik.gewonnen(tempInt)) {
                                    System.out.println("KI hat gewonnen");
                                }

                            }
                            else {
                                if (tempm != 1) {
                                    tempm = tempm - 1;
                                }
                                horizontal = true;
                            }

                        }
                        else {
                            versuche--;
                            break;
                        }
                    }
                    else if ((horizontal && tempInt[tempn][tempm-1] == 3) || (horizontal && tempm == 1 && tempInt[tempn][tempm-1] == 2 && bereichBeschiessbar(tempn, ursprungsm+1, tempInt))) {
                        tempm = ursprungsm;
                        tempn = ursprungsn;
                    }
                }
                if (tempm<logik.feldgroesse-1 && vertikal == false && tempm != -1) {
                    if (bereichBeschiessbar(tempn, tempm+1, tempInt)) {
                        logik.schussAbgefeuert(tempn, tempm+1, tempInt, tempButton);
                        System.out.println(tempn + " " + (tempm+1));
                        System.out.println(tempInt[tempn][tempm+1]);
                        if (logik.treffer) {
                            if (logik.testeSchiffVersenkt(tempn, tempm+1, tempInt, tempButton)) {
                                System.out.println("schiff versenkt");
                                tempn = -1;
                                tempm = -1;
                                ursprungsn = -1;
                                ursprungsm = -1;
                                horizontal = false;

                                if (logik.gewonnen(tempInt)) {
                                    System.out.println("KI hat gewonnen");
                                }

                            }
                            else {
                                tempm = tempm+1;
                                horizontal = true;
                            }

                        }
                        else {
                            versuche--;
                            break;
                        }
                    }
                    else if ((horizontal && tempInt[tempn][tempm+1] == 3) || tempm == 7) {
                        tempm = ursprungsm;
                        tempn = ursprungsn;
                    }
                }
            }
        }
    }

    /**
     * Sehr schwierige KI.
     * Merkt sich wo sie getroffe hat und laueft danach die Stellen drum herum ab.
     * Sie erkennt ob ein Schiff vertikal oder horizontal gesetzt worden ist, aber erst
     * wenn sie wie ein menschlicher Spieler auch, mindestens einen weiteren punkt des Schiffes
     * dazu gefunden hat.
     * Zusaetlich zur mittleren KI, sieht der Spieler hier seine und die Fehltreffer der KI nicht
     * @param tempInt
     * @param tempButton
     * @param versuche
     */
    public void kiSchwer(int [][] tempInt, Button [][] tempButton, int versuche) {
        int n = randomInt();
        int m = randomInt();

        while (versuche != 0) {

            if (tempn == -1 && tempm == -1) {
                if (bereichBeschiessbar(n, m, tempInt)) {
                    logik.schussAbgefeuertSchwer(n, m, tempInt, tempButton);
                    System.out.println(n + " " + m);
                    System.out.println(tempInt[n][m]);

                    if (logik.treffer) {
                        tempn = n;
                        tempm = m;
                        ursprungsn = n;
                        ursprungsm = m;
                    }
                    else {
                        versuche--;
                        break;
                    }
                }
                else {
                    n = randomInt();
                    m = randomInt();
                }

            }
            else {
                if (tempn > 0 && horizontal == false) {
                    if (bereichBeschiessbar(tempn-1, tempm, tempInt)) {
                        logik.schussAbgefeuertSchwer(tempn-1, tempm, tempInt, tempButton);
                        System.out.println(tempn-1 + " " + tempm);
                        System.out.println(tempInt[tempn-1][tempm]);
                        if (logik.treffer) {
                            if (logik.testeSchiffVersenkt(tempn - 1, tempm, tempInt, tempButton)) {
                                System.out.println("schiff versenkt");
                                tempn = -1;
                                tempm = -1;
                                ursprungsn = -1;
                                ursprungsm = -1;
                                vertikal = false;

                                if (logik.gewonnen(tempInt)) {
                                    System.out.println("KI hat gewonnen");
                                }

                            }
                            else {
                                if (tempn != 1) {//Wird benoetigt, da er sonst in eine Schleife geraet, wenn oben links ein Schiff versenkt werden muss.
                                    tempn = tempn - 1;
                                }

                                vertikal = true;
                            }

                        }
                        else {
                            versuche--;
                            break;
                        }
                    }
                    else if ((vertikal && tempInt[tempn-1][tempm] == 3) || (vertikal && tempn == 1 && tempInt[tempn-1][tempm] == 2 && bereichBeschiessbar(ursprungsn+1, tempm, tempInt))) {
                        tempn = ursprungsn;
                        tempm = ursprungsm;
                    }
                }
                if (tempn < logik.feldgroesse - 1 && horizontal == false && tempn != -1) {
                    if (bereichBeschiessbar(tempn + 1, tempm, tempInt)) {
                        logik.schussAbgefeuertSchwer(tempn+1, tempm, tempInt, tempButton);
                        System.out.println(tempn+1 + " " + tempm);
                        System.out.println(tempInt[tempn+1][tempm]);
                        if (logik.treffer) {
                            if (logik.testeSchiffVersenkt(tempn + 1, tempm, tempInt, tempButton)) {
                                System.out.println("schiff versenkt");
                                tempn = -1;
                                tempm = -1;
                                ursprungsn = -1;
                                ursprungsm = -1;
                                vertikal = false;

                                if (logik.gewonnen(tempInt)) {
                                    System.out.println("KI hat gewonnen");
                                }

                            }
                            else {
                                tempn = tempn + 1;
                                vertikal = true;
                            }

                        }
                        else {
                            versuche--;
                            break;
                        }
                    }
                    else if ((vertikal && tempInt[tempn+1][tempm] == 3) || tempn == 7) {
                        tempn = ursprungsn;
                        tempm = ursprungsm;
                    }
                }
                if (tempm > 0 && vertikal == false) {
                    if (bereichBeschiessbar(tempn, tempm-1, tempInt)) {
                        logik.schussAbgefeuertSchwer(tempn, tempm-1, tempInt, tempButton);
                        System.out.println(tempn + " " + (tempm-1));
                        System.out.println(tempInt[tempn][tempm-1]);
                        if (logik.treffer) {
                            if (logik.testeSchiffVersenkt(tempn, tempm-1, tempInt, tempButton)) {
                                System.out.println("schiff versenkt");
                                tempn = -1;
                                tempm = -1;
                                ursprungsn = -1;
                                ursprungsm = -1;
                                horizontal = false;

                                if (logik.gewonnen(tempInt)) {
                                    System.out.println("KI hat gewonnen");
                                }

                            }
                            else {
                                if (tempm != 1) {
                                    tempm = tempm - 1;
                                }
                                horizontal = true;
                            }

                        }
                        else {
                            versuche--;
                            break;
                        }
                    }
                    else if ((horizontal && tempInt[tempn][tempm-1] == 3) || (horizontal && tempm == 1 && tempInt[tempn][tempm-1] == 2 && bereichBeschiessbar(tempn, ursprungsm+1, tempInt))) {
                        tempm = ursprungsm;
                        tempn = ursprungsn;
                    }
                }
                if (tempm<logik.feldgroesse-1 && vertikal == false && tempm != -1) {
                    if (bereichBeschiessbar(tempn, tempm+1, tempInt)) {
                        logik.schussAbgefeuertSchwer(tempn, tempm+1, tempInt, tempButton);
                        System.out.println(tempn + " " + (tempm+1));
                        System.out.println(tempInt[tempn][tempm+1]);
                        if (logik.treffer) {
                            if (logik.testeSchiffVersenkt(tempn, tempm+1, tempInt, tempButton)) {
                                System.out.println("schiff versenkt");
                                tempn = -1;
                                tempm = -1;
                                ursprungsn = -1;
                                ursprungsm = -1;
                                horizontal = false;

                                if (logik.gewonnen(tempInt)) {
                                    System.out.println("KI hat gewonnen");
                                }

                            }
                            else {
                                tempm = tempm+1;
                                horizontal = true;
                            }

                        }
                        else {
                            versuche--;
                            break;
                        }
                    }
                    else if ((horizontal && tempInt[tempn][tempm+1] == 3) || tempm == 7) {
                        tempm = ursprungsm;
                        tempn = ursprungsn;
                    }
                }
            }
        }
    }

    /**
     * Diese Methode uerberpreuft ob vom ausgewaehlten Feld aus, ein anliegendes Feld
     * noch beschiessbar ist
     * @param n
     * @param m
     * @param temp
     * @return
     */
    public boolean bereichBeschiessbar(int n, int m, int [][] temp) {
        if (temp[n][m] != 2 && temp[n][m] != 3 && temp[n][m] != 6) {
            return true;
        }
        else {
            return false;
        }
    }
}
