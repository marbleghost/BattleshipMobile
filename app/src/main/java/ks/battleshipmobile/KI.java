package ks.battleshipmobile;

import android.os.health.SystemHealthManager;
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
            int n = randomInt();
            int m = randomInt();

            if (tempn == -1 && tempm == -1) {
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
            else {
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
                                n = randomInt();
                                m = randomInt();
                            }
                            else {
                                tempn = tempn-1;
                            }

                            if (logik.gewonnen(tempInt)) {
                                System.out.println("KI hat gewonnen");
                            }
                        }
                    }
                    else if (umgebungBeschießbar(tempn-1, tempm, tempInt) == false) {
                        tempn = -1;
                        tempm = -1;
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
                                n = randomInt();
                                m = randomInt();
                            }
                            else {
                                tempn = tempn+1;
                            }

                            if (logik.gewonnen(tempInt)) {
                                System.out.println("KI hat gewonnen");
                            }
                        }
                    }
                    else if (umgebungBeschießbar(tempn+1, tempm, tempInt) == false) {
                        tempn = -1;
                        tempm = -1;
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
                                n = randomInt();
                                m = randomInt();
                            }
                            else {
                                tempm = tempm-1;
                            }

                            if (logik.gewonnen(tempInt)) {
                                System.out.println("KI hat gewonnen");
                            }
                        }
                    }
                    else if (umgebungBeschießbar(tempn, tempm-1, tempInt) == false) {
                        tempn = -1;
                        tempm = -1;
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
                                n = randomInt();
                                m = randomInt();
                            }
                            else {
                                tempm = tempm+1;
                            }

                            if (logik.gewonnen(tempInt)) {
                                System.out.println("KI hat gewonnen");
                            }
                        }
                    }
                    else if (umgebungBeschießbar(tempn, tempm+1, tempInt) == false) {
                        tempn = -1;
                        tempm = -1;
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


                if (umgebungBeschießbar(n, m, tempInt)) {
                    logik.schussAbgefeuert(n, m, tempInt, tempButton);

                    if (logik.treffer) {
                        tempn = n;
                        tempm = m;
                    }
                    else {
                        versuche--;
                    }
                }

            }
            else {
                if (tempn > 0 && horizontal == false) {
                    if (umgebungBeschießbar(tempn - 1, tempm, tempInt)) {
                        if (logik.treffer) {
                            if (logik.testeSchiffVersenkt(tempn - 1, tempm, tempInt, tempButton)) {
                                tempn = -1;
                                tempm = -1;
                                vertikal = false;

                                if (logik.gewonnen(tempInt)) {
                                    System.out.println("KI hat gewonnen");
                                }
                            }
                            else {
                                ursprungsn = tempn;
                                tempn = tempn - 1;
                                vertikal = true;
                            }

                        }
                        else {
                            versuche--;
                        }
                    }
                    else if (vertikal) {
                        tempn = ursprungsn;
                    }
                }
            }
            if (tempn < logik.feldgroesse - 1 && horizontal == false) {
                if (umgebungBeschießbar(tempn + 1, tempm, tempInt)) {
                    if (logik.treffer) {
                        if (logik.testeSchiffVersenkt(tempn + 1, tempm, tempInt, tempButton)) {
                            tempn = -1;
                            tempm = -1;
                            vertikal = false;

                            if (logik.gewonnen(tempInt)) {
                                System.out.println("KI hat gewonnen");
                            }
                        }
                        else {
                            ursprungsn = tempn;
                            tempn = tempn + 1;
                            vertikal = true;
                        }

                    }
                    else {
                        versuche--;
                    }
                }
                else if (vertikal) {
                    tempn = ursprungsn;
                }
            }
            if (tempm > 0 && vertikal == false) {
                if (umgebungBeschießbar(tempn, tempm-1, tempInt)) {
                    if (logik.treffer) {
                        if (logik.testeSchiffVersenkt(tempn, tempm-1, tempInt, tempButton)) {
                            tempn = -1;
                            tempm = -1;
                            horizontal = false;

                            if (logik.gewonnen(tempInt)) {
                                System.out.println("KI hat gewonnen");
                            }
                        }
                        else {
                            ursprungsm = tempm;
                            tempm = tempm - 1;
                            horizontal = true;
                        }

                    }
                    else {
                        versuche--;
                    }
                }
                else if (horizontal) {
                    tempm = ursprungsm;
                }
            }
            if (tempm<logik.feldgroesse-1 && vertikal == false) {
                if (umgebungBeschießbar(tempn, tempm+1, tempInt)) {
                    if (logik.treffer) {
                        if (logik.testeSchiffVersenkt(tempn, tempm+1, tempInt, tempButton)) {
                            tempn = -1;
                            tempm = -1;
                            horizontal = false;

                            if (logik.gewonnen(tempInt)) {
                                System.out.println("KI hat gewonnen");
                            }
                        }
                        else {
                            ursprungsm = tempm;
                            tempm = tempm+1;
                            horizontal = true;
                        }

                    }
                    else {
                        versuche--;
                    }
                }
                else if (horizontal) {
                    tempm = ursprungsm;
                }
            }
        }
    }


    public boolean umgebungBeschießbar(int n, int m, int [][] temp) {
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
}
