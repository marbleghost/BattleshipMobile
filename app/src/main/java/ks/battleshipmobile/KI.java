package ks.battleshipmobile;

import android.widget.Button;

import java.util.Random;

public class KI {

    Spiellogik logik = new Spiellogik();
    Random random = new Random();


    public int randomInt() {
        return random.nextInt(8);
    }


    public void kiDumm(int [][] tempInt, Button [][] tempButton, int versuche) {

        while (versuche != 0) {
            int n = randomInt();
            int m = randomInt();

            if (tempInt[n][m] != 2 && tempInt[n][m] != 3 && tempInt[n][m] != 6) {
                logik.schussAbgefeuert(n, m, tempInt, tempButton);
            }
        }




    }

    public void kiMittel(int [][] tempInt, Button [][] tempButton, int versuche) {
        int n = randomInt();
        int m = randomInt();

        while (versuche != 0) {

        }
    }
}
