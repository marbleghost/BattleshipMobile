package ks.battleshipmobile;

import android.util.Log;
import android.widget.Button;

import java.util.Random;

public class KI {

    Spiellogik logik = new Spiellogik();
    Random random = new Random();

    int tempn = -1;
    int tempm = -1;
    int ursprungsn = -1;
    int ursprungsm = -1;

    boolean vertikal = false;
    boolean horizontal = false;
    boolean ignoreh = false;
    boolean ignorev = false;

    int zaehler = 0;


    public int randomInt() {
        return random.nextInt(8);
    }


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
                    else if ((vertikal && tempInt[tempn-1][tempm] == 3) || tempn == 0) {
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
                                tempm = tempm - 1;
                                horizontal = true;
                            }

                        }
                        else {
                            versuche--;
                            break;
                        }
                    }
                    else if ((horizontal && tempInt[tempn][tempm-1] == 3) || tempm == 0) {
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
                    else if ((vertikal && tempInt[tempn-1][tempm] == 3) || tempn == 0) {
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
                                tempm = tempm - 1;
                                horizontal = true;
                            }

                        }
                        else {
                            versuche--;
                            break;
                        }
                    }
                    else if ((horizontal && tempInt[tempn][tempm-1] == 3) || tempm == 0) {
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

    public boolean umgebungBeschiessbar(int n, int m, int [][] temp) {
        boolean oben = false;
        boolean unten = false;
        boolean rechts = false;
        boolean links = false;

        if (n > 0) {
            n--;
            if (temp[n][m] != 2 && temp[n][m] != 3 && temp[n][m] != 6) {
                oben = true;
            }
            n++;
        }
        if (m < logik.feldgroesse - 1 && m != -1) {
            m++;
            if (temp[n][m] != 2 && temp[n][m] != 3 && temp[n][m] != 6) {
                rechts = true;
            }
            m--;
        }
        if (n < logik.feldgroesse - 1 && n != -1) {
            n++;
            if (temp[n][m] != 2 && temp[n][m] != 3 && temp[n][m] != 6) {
                unten = true;
            }
            n--;
        }
        if (m > 0) {
            m--;
            if (temp[n][m] != 2 && temp[n][m] != 3 && temp[n][m] != 6) {
                links = true;
            }
            m++;
        }

        if (oben == true || unten == true || rechts == true || links == true) {
            return true;
        }
        else {
            return false;
        }

    }

    public boolean bereichBeschiessbar(int n, int m, int [][] temp) {
        if (temp[n][m] != 2 && temp[n][m] != 3 && temp[n][m] != 6) {
            return true;
        }
        else {
            return false;
        }
    }
}
