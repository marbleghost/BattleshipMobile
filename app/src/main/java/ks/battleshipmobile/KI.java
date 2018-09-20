package ks.battleshipmobile;

import android.os.health.SystemHealthManager;
import android.widget.Button;

import java.util.Random;

public class KI {

    Spiellogik logik = new Spiellogik();
    Random random = new Random();

    int tempn = -1;
    int tempm = -1;


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
        boolean versenkt = false;

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
                }
            }
        }
    }
}
