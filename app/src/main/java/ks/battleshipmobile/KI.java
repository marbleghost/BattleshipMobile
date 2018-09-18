package ks.battleshipmobile;

import android.widget.Button;

import java.util.Random;

public class KI {

    Spiellogik logik = new Spiellogik();
    Random random = new Random();

    int tempn = 0;
    int tempm = 0;


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

            if (tempn == 0 && tempm == 0) {
                if (tempInt[n][m] != 2 && tempInt[n][m] != 3 && tempInt[n][m] != 6) {
                    logik.schussAbgefeuert(n, m, tempInt, tempButton);
                    if (logik.treffer == false) {
                        versuche--;
                    }
                    else {
                        tempn = n;
                        tempm = m;

                        logik.testeSchiffVersenkt(n, m, tempInt, tempButton);
                        if (logik.gewonnen(tempInt)) {
                            System.out.println("KI hat gewonnen");
                        }
                    }
                }
            }
            else {
                if (tempn>0) {
                    if (tempInt[tempn-1][tempm] != 2 && tempInt[tempn-1][tempm] != 3 && tempInt[tempn-1][tempm] != 6) {
                        logik.schussAbgefeuert(tempn-1, tempm, tempInt, tempButton);
                        if (logik.treffer == false) {
                            versuche--;
                        }
                        else {
                            tempn = tempn-1;

                            logik.testeSchiffVersenkt(tempn, tempm, tempInt, tempButton);
                            if (logik.testeSchiffVersenkt(tempn, tempm, tempInt, tempButton)) {
                                tempn = 0;
                                tempm = 0;
                            }
                            if (logik.gewonnen(tempInt)) {
                                System.out.println("KI hat gewonnen");
                            }
                        }
                    }
                }
                if (tempn<logik.feldgroesse-1) {
                    if (tempInt[tempn+1][tempm] != 2 && tempInt[tempn+1][tempm] != 3 && tempInt[tempn+1][tempm] != 6) {
                        logik.schussAbgefeuert(tempn+1, tempm, tempInt, tempButton);
                        if (logik.treffer == false) {
                            versuche--;
                        }
                        else {
                            tempn = tempn+1;

                            logik.testeSchiffVersenkt(tempn, tempm, tempInt, tempButton);
                            if (logik.testeSchiffVersenkt(tempn, tempm, tempInt, tempButton)) {
                                tempn = 0;
                                tempm = 0;
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
                        if (logik.treffer == false) {
                            versuche--;
                        }
                        else {
                            tempm = tempm-1;

                            logik.testeSchiffVersenkt(tempn, tempm, tempInt, tempButton);
                            if (logik.testeSchiffVersenkt(tempn, tempm, tempInt, tempButton)) {
                                tempn = 0;
                                tempm = 0;
                            }
                            if (logik.gewonnen(tempInt)) {
                                System.out.println("KI hat gewonnen");
                            }
                        }
                    }
                }
                if (tempm<logik.feldgroesse-1) {
                    if (tempInt[tempn][tempm+1] != 2 && tempInt[tempn][tempm+1] != 3 && tempInt[tempn][tempm+1] != 6) {
                        logik.schussAbgefeuert(tempn, tempm+1, tempInt, tempButton);
                        if (logik.treffer == false) {
                            versuche--;
                        }
                        else {
                            tempm = tempm+1;

                            logik.testeSchiffVersenkt(tempn, tempm, tempInt, tempButton);
                            if (logik.testeSchiffVersenkt(tempn, tempm, tempInt, tempButton)) {
                                tempn = 0;
                                tempm = 0;
                            }
                            if (logik.gewonnen(tempInt)) {
                                System.out.println("KI hat gewonnen");
                            }
                        }
                    }
                }
            }
        }
    }
}
