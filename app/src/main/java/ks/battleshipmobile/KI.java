package ks.battleshipmobile;

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
                    if (logik.gewonnen(tempInt)) {
                        System.out.println("KI hat gewonnen");
                    }
                }
            }
        }
    }

    public void kiMittel(int [][] tempInt, Button [][] tempButton, int versuche) {


        while (versuche != 0) {
            if (tempn == -1 && tempm == -1) {
                int n = randomInt();
                int m = randomInt();
                if (tempInt[n][m] != 2 && tempInt[n][m] != 3 && tempInt[n][m] != 6) {
                    logik.schussAbgefeuert(n, m, tempInt, tempButton);
                    System.out.println(n + " " + m);
                    System.out.println(tempInt[n][m]);
                    if (logik.treffer == false) {
                        versuche--;
                    }
                    else {
                        logik.testeSchiffVersenkt(n, m, tempInt, tempButton);

                        if (logik.testeSchiffVersenkt(n, m, tempInt, tempButton) == false) {
                            tempn = n;
                            tempm = m;
                        }

                        if (logik.gewonnen(tempInt)) {
                            System.out.println("KI hat gewonnen");
                        }
                    }
                }
            }
            else if (versuche == 1) {
                if (tempn>0 ) {
                    if (tempInt[tempn-1][tempm] != 2 && tempInt[tempn-1][tempm] != 3 && tempInt[tempn-1][tempm] != 6) {
                        logik.schussAbgefeuert(tempn-1, tempm, tempInt, tempButton);
                        System.out.println(tempn-1 + " " + tempm);
                        System.out.println(tempInt[tempn-1][tempm]);
                        if (logik.treffer == false) {
                            versuche--;
                            break;
                        }
                        else {

                            if (logik.testeSchiffVersenkt(tempn, tempm, tempInt, tempButton)) {
                                System.out.println("vor dem zuruecksetzen" + tempn + tempm);
                                tempn = -1;
                                tempm = -1;
                                System.out.println("nach dem zuruecksetzen" + tempn + tempm);
                            }
                            else {
                                tempn = tempn-1;
                            }

                            if (logik.gewonnen(tempInt)) {
                                System.out.println("KI hat gewonnen");
                            }
                        }
                    }
                }
                if (tempn<logik.feldgroesse-1 && tempn != -1) {
                    if (tempInt[tempn+1][tempm] != 2 && tempInt[tempn+1][tempm] != 3 && tempInt[tempn+1][tempm] != 6) {
                        logik.schussAbgefeuert(tempn+1, tempm, tempInt, tempButton);
                        System.out.println(tempn+1 + " " + tempm);
                        System.out.println(tempInt[tempn+1][tempm]);
                        if (logik.treffer == false) {
                            versuche--;
                            break;
                        }
                        else {

                            if (logik.testeSchiffVersenkt(tempn, tempm, tempInt, tempButton)) {
                                System.out.println("vor dem zuruecksetzen" + tempn + tempm);
                                tempn = -1;
                                tempm = -1;
                                System.out.println("nach dem zuruecksetzen" + tempn + tempm);
                            }
                            else {
                                tempn = tempn+1;
                            }

                            if (logik.gewonnen(tempInt)) {
                                System.out.println("KI hat gewonnen");
                            }
                        }
                    }
                }
                if (tempm>0) {
                    if (tempInt[tempn][tempm-1] != 2 && tempInt[tempn][tempm-1] != 3 && tempInt[tempn][tempm-1] != 6) {
                        logik.schussAbgefeuert(tempn, tempm-1, tempInt, tempButton);
                        System.out.println(tempn + " " + (tempm-1));
                        System.out.println(tempInt[tempn][tempm-1]);
                        if (logik.treffer == false) {
                            versuche--;
                            break;
                        }
                        else {

                            if (logik.testeSchiffVersenkt(tempn, tempm, tempInt, tempButton)) {
                                System.out.println("vor dem zuruecksetzen" + tempn + tempm);
                                tempn = -1;
                                tempm = -1;
                                System.out.println("nach dem zuruecksetzen" + tempn + tempm);
                            }
                            else {
                                tempm = tempm-1;
                            }

                            if (logik.gewonnen(tempInt)) {
                                System.out.println("KI hat gewonnen");
                            }
                        }
                    }
                }
                if (tempm<logik.feldgroesse-1 && tempm != -1) {
                    if (tempInt[tempn][tempm+1] != 2 && tempInt[tempn][tempm+1] != 3 && tempInt[tempn][tempm+1] != 6) {
                        logik.schussAbgefeuert(tempn, tempm+1, tempInt, tempButton);
                        System.out.println(tempn + " " + (tempm+1));
                        System.out.println(tempInt[tempn][tempm+1]);
                        if (logik.treffer == false) {
                            versuche--;
                            break;
                        }
                        else {

                            if (logik.testeSchiffVersenkt(tempn, tempm, tempInt, tempButton)) {
                                System.out.println("vor dem zuruecksetzen" + tempn + tempm);
                                tempn = -1;
                                tempm = -1;
                                System.out.println("nach dem zuruecksetzen" + tempn + tempm);
                            }
                            else {
                                tempm = tempm+1;
                            }

                            if (logik.gewonnen(tempInt)) {
                                System.out.println("KI hat gewonnen");
                            }
                        }
                    }
                }
                if (umgebungBeschiessbar(tempn, tempm, tempInt) == false) {
                    tempn = -1;
                    tempm = -1;
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
                    logik.schussAbgefeuert(n, m, tempInt, tempButton);
                    System.out.println(n + " " + m);
                    System.out.println(tempInt[n][m]);

                    if (logik.treffer) {
                        tempn = n;
                        tempm = m;
                    }
                    else {
                        versuche--;
                        break;
                    }
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
                                ursprungsn = tempn;
                                ursprungsm = tempm;
                                tempn = tempn - 1;
                                vertikal = true;
                            }

                        }
                        else {
                            versuche--;
                            break;
                        }
                    }
                    else if (vertikal && tempInt[tempn-1][tempm] != 2 && bereichBeschiessbar(tempn-1, tempm, tempInt) == false) {
                        tempn = ursprungsn;
                        tempm = ursprungsm;
                    }
                    else if (vertikal && tempInt[tempn-1][tempm] == 2) {
                        tempn = tempn-1;
                        kiMittel(tempInt, tempButton, versuche);
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
                                ursprungsn = tempn;
                                ursprungsm = tempm;
                                tempn = tempn + 1;
                                vertikal = true;
                            }

                        }
                        else {
                            versuche--;
                            break;
                        }
                    }
                    else if (vertikal && tempInt[tempn+1][tempm] != 2 && bereichBeschiessbar(tempn+1, tempm, tempInt) == false) {
                        tempn = ursprungsn;
                        tempm = ursprungsm;
                    }
                    else if (vertikal && tempInt[tempn+1][tempm] == 2) {
                        tempn = tempn+1;
                        kiMittel(tempInt, tempButton, versuche);
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
                                ursprungsm = tempm;
                                ursprungsn = tempn;
                                tempm = tempm - 1;
                                horizontal = true;
                            }

                        }
                        else {
                            versuche--;
                            break;
                        }
                    }
                    else if (horizontal && tempInt[tempn][tempm-1] != 2 && bereichBeschiessbar(tempn, tempm-1, tempInt) == false) {
                        tempm = ursprungsm;
                        tempn = ursprungsn;
                    }
                    else if (horizontal && tempInt[tempn][tempm-1] == 2) {
                        tempm = tempm-1;
                        kiMittel(tempInt, tempButton, versuche);
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
                                ursprungsm = tempm;
                                ursprungsn = tempn;
                                tempm = tempm+1;
                                horizontal = true;
                            }

                        }
                        else {
                            versuche--;
                            break;
                        }
                    }
                    else if (horizontal && tempInt[tempn][tempm+1] != 2 && bereichBeschiessbar(tempn, tempm+1, tempInt) == false) {
                        tempm = ursprungsm;
                        tempn = ursprungsn;
                    }
                    else if (horizontal && tempInt[tempn][tempm+1] == 2) {
                        tempm = tempm+1;
                        kiMittel(tempInt, tempButton, versuche);
                    }
                }
            }
        }
    }

    public void kiSchwerVersionKiki(int [][] tempInt, Button [][] tempButton, int versuche) {

        while (versuche != 0) {

            if (tempn == -1 && tempm == -1) {
                int n = randomInt();
                int m = randomInt();
                if (tempInt[n][m] != 2 && tempInt[n][m] != 3 && tempInt[n][m] != 6) {
                    logik.schussAbgefeuert(n, m, tempInt, tempButton);

                    if (logik.treffer == false) {
                        versuche--;
                        break;
                    }
                    else {
                        logik.testeSchiffVersenkt(n, m, tempInt, tempButton);

                        if (logik.testeSchiffVersenkt(n, m, tempInt, tempButton) == false) {
                            tempn = n;
                            tempm = m;
                            ursprungsn = n;
                            ursprungsm = m;
                        }
                    }
                }
            }
            else if (versuche == 1 && ignorev == false && ignoreh == false) {
                if (tempn>0) {
                    if (bereichBeschiessbar(tempn-1, tempm, tempInt)) {
                        logik.schussAbgefeuert(tempn-1, tempm, tempInt, tempButton);
                        if (logik.treffer == false) {
                            versuche--;
                            break;
                        }
                        else {
                            tempn = tempn-1;
                            vertikal = true;
                            ignoreh = true;
                            break;
                        }
                    }
                    else {
                        tempn = ursprungsn;
                        tempm = ursprungsm;
                    }
                }
                if (tempn<logik.feldgroesse-1 && tempn != -1) {
                    if (bereichBeschiessbar(tempn+1, tempm, tempInt)) {
                        logik.schussAbgefeuert(tempn+1, tempm, tempInt, tempButton);

                        if (logik.treffer == false) {
                            versuche--;
                            break;
                        }
                        else {
                            tempn = tempn+1;
                            vertikal = true;
                            ignoreh = true;
                            break;
                        }
                    }
                    else {
                        tempn = ursprungsn;
                        tempm = ursprungsm;
                    }
                }
                if (tempm>0) {
                    if (bereichBeschiessbar(tempn, tempm-1, tempInt)) {
                        logik.schussAbgefeuert(tempn, tempm-1, tempInt, tempButton);
                        if (logik.treffer == false) {
                            versuche--;
                            break;
                        }
                        else {
                            tempm = tempm-1;
                            horizontal = true;
                            ignorev = true;
                            break;
                        }
                    }
                    else {
                        tempn = ursprungsn;
                        tempm = ursprungsm;
                    }
                }
                if (tempm<logik.feldgroesse-1 && tempm != -1) {
                    if (bereichBeschiessbar(tempn, tempm+1, tempInt)) {
                        logik.schussAbgefeuert(tempn, tempm+1, tempInt, tempButton);
                        if (logik.treffer == false) {
                            versuche--;
                            break;
                        }
                        else {
                            tempm = tempm+1;
                            horizontal = true;
                            ignorev = true;
                            break;
                        }
                    }
                    else {
                        tempn = ursprungsn;
                        tempm = ursprungsm;
                    }
                }
            }
            else if (versuche == 1 && ignorev == true || ignoreh == true) {
                if (tempn>0 && vertikal == true && ignoreh == true) {
                    if (bereichBeschiessbar(tempn-1, tempm, tempInt)) {
                        logik.schussAbgefeuert(tempn-1, tempm, tempInt, tempButton);
                        if (logik.treffer == false) {
                            versuche--;
                            break;
                        }
                        else {
                            if (logik.testeSchiffVersenkt(tempn, tempm, tempInt, tempButton)) {
                                tempn = -1;
                                tempm = -1;
                                ursprungsn = -1;
                                ursprungsm = -1;
                            }
                            else {
                                tempn = tempn-1;
                            }
                        }
                    }
                    else {
                        tempn = ursprungsn;
                        tempm = ursprungsm;
                        zaehler++;
                        if (zaehler < 3) {
                            tempn = -1;
                            tempm = -1;
                            ursprungsn = -1;
                            ursprungsm = -1;
                            zaehler = 0;
                        }
                    }
                }
                if (tempn<logik.feldgroesse-1 && tempn != -1 && vertikal == true && ignoreh == true) {
                    if (bereichBeschiessbar(tempn+1, tempm, tempInt)) {
                        logik.schussAbgefeuert(tempn+1, tempm, tempInt, tempButton);

                        if (logik.treffer == false) {
                            versuche--;
                            break;
                        }
                        else {

                            if (logik.testeSchiffVersenkt(tempn, tempm, tempInt, tempButton)) {
                                tempn = -1;
                                tempm = -1;
                                ursprungsn = -1;
                                ursprungsm = -1;
                            }
                            else {
                                tempn = tempn+1;
                            }
                        }
                    }
                    else {
                        tempn = ursprungsn;
                        tempm = ursprungsm;
                        zaehler++;
                        if (zaehler < 3) {
                            tempn = -1;
                            tempm = -1;
                            ursprungsn = -1;
                            ursprungsm = -1;
                            zaehler = 0;

                        }
                    }
                }
                if (tempm>0 && horizontal == true && ignorev == true) {
                    if (bereichBeschiessbar(tempn, tempm-1, tempInt)) {
                        logik.schussAbgefeuert(tempn, tempm-1, tempInt, tempButton);
                        if (logik.treffer == false) {
                            versuche--;
                            break;
                        }
                        else {

                            if (logik.testeSchiffVersenkt(tempn, tempm, tempInt, tempButton)) {
                                tempn = -1;
                                tempm = -1;
                                ursprungsn = -1;
                                ursprungsm = -1;
                            }
                            else {
                                tempm = tempm-1;
                            }
                        }
                    }
                    else {
                        tempn = ursprungsn;
                        tempm = ursprungsm;
                        zaehler++;
                        if (zaehler < 3) {
                            tempn = -1;
                            tempm = -1;
                            ursprungsn = -1;
                            ursprungsm = -1;
                            zaehler = 0;

                        }
                    }
                }
                if (tempm<logik.feldgroesse-1 && tempm != -1 && horizontal == true && ignorev == true) {
                    if (bereichBeschiessbar(tempn, tempm+1, tempInt)) {
                        logik.schussAbgefeuert(tempn, tempm+1, tempInt, tempButton);
                        if (logik.treffer == false) {
                            versuche--;
                            break;
                        }
                        else {

                            if (logik.testeSchiffVersenkt(tempn, tempm, tempInt, tempButton)) {
                                tempn = -1;
                                tempm = -1;
                                ursprungsn = -1;
                                ursprungsm = -1;
                            }
                            else {
                                tempm = tempm+1;
                            }
                        }
                    }
                    else {
                        tempn = ursprungsn;
                        tempm = ursprungsm;
                        zaehler++;
                        if (zaehler < 3) {
                            tempn = -1;
                            tempm = -1;
                            ursprungsn = -1;
                            ursprungsm = -1;
                            zaehler = 0;

                        }
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
