package ks.battleshipmobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Schiffesetzen extends AppCompatActivity implements View.OnClickListener {

    int [] idArray =    {R.id.a1, R.id.a2, R.id.a3, R.id.a4, R.id.a5, R.id.a6, R.id.a7, R.id.a8,
                        R.id.b1, R.id.b2, R.id.b3, R.id.b4, R.id.b5, R.id.b6, R.id.b7, R.id.b8,
                        R.id.c1, R.id.c2, R.id.c3, R.id.c4, R.id.c5, R.id.c6, R.id.c7, R.id.c8,
                        R.id.d1, R.id.d2, R.id.d3, R.id.d4, R.id.d5, R.id.d6, R.id.d7, R.id.d8,
                        R.id.e1, R.id.e2, R.id.e3, R.id.e4, R.id.e5, R.id.e6, R.id.e7, R.id.e8,
                        R.id.f1, R.id.f2, R.id.f3, R.id.f4, R.id.f5, R.id.f6, R.id.f7, R.id.f8,
                        R.id.g1, R.id.g2, R.id.g3, R.id.g4, R.id.g5, R.id.g6, R.id.g7, R.id.g8,
                        R.id.h1, R.id.h2, R.id.h3, R.id.h4, R.id.h5, R.id.h6, R.id.h7, R.id.h8};
    Button [][] spielfeld = new Button[8][8];

    /**
    Button  a1, a2, a3, a4, a5, a6, a7, a8,
            b1, b2, b3, b4, b5, b6, b7, b8,
            c1, c2, c3, c4, c5, c6, c7, c8,
            d1, d2, d3, d4, d5, d6, d7, d8,
            e1, e2, e3, e4, e5, e6, e7, e8,
            f1, f2, f3, f4, f5, f6, f7, f8,
            g1, g2, g3, g4, g5, g6, g7, g8,
            h1, h2, h3, h4, h5, h6, h7, h8;
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); // sorgt dafür, dass die Titelleiste mit dem Appnamen nicht mehr oben angezeigt wird.
        setContentView(R.layout.activity_schiffesetzen);

        for (int i=0; i<8; i++) {
            for (int j=0; j<8; j++) {
                int k=0;
                spielfeld[i][j] = (Button) findViewById(idArray[k]);
                spielfeld[i][j].setOnClickListener(this);
                k++;
            }
        }

        /**
        spielfeld [0][0] = (Button) findViewById(R.id.a1);
        a1.setOnClickListener(this);
        a2 = (Button) findViewById(R.id.a2);
        a3 = (Button) findViewById(R.id.a3);
        a4 = (Button) findViewById(R.id.a4);
        a5 = (Button) findViewById(R.id.a5);
        a6 = (Button) findViewById(R.id.a6);
        a7 = (Button) findViewById(R.id.a7);
        a8 = (Button) findViewById(R.id.a8);

        b1 = (Button) findViewById(R.id.b1);
        b2 = (Button) findViewById(R.id.b2);
        b3 = (Button) findViewById(R.id.b3);
        b4 = (Button) findViewById(R.id.b4);
        b5 = (Button) findViewById(R.id.b5);
        b6 = (Button) findViewById(R.id.b6);
        b7 = (Button) findViewById(R.id.b7);
        b8 = (Button) findViewById(R.id.b8);

        c1 = (Button) findViewById(R.id.c1);
        c2 = (Button) findViewById(R.id.c2);
        c3 = (Button) findViewById(R.id.c3);
        c4 = (Button) findViewById(R.id.c4);
        c5 = (Button) findViewById(R.id.c5);
        c6 = (Button) findViewById(R.id.c6);
        c7 = (Button) findViewById(R.id.c7);
        c8 = (Button) findViewById(R.id.c8);

        d1 = (Button) findViewById(R.id.d1);
        d2 = (Button) findViewById(R.id.d2);
        d3 = (Button) findViewById(R.id.d3);
        d4 = (Button) findViewById(R.id.d4);
        d5 = (Button) findViewById(R.id.d5);
        d6 = (Button) findViewById(R.id.d6);
        d7 = (Button) findViewById(R.id.d7);
        d8 = (Button) findViewById(R.id.d8);

        e1 = (Button) findViewById(R.id.e1);
        e2 = (Button) findViewById(R.id.e2);
        e3 = (Button) findViewById(R.id.e3);
        e4 = (Button) findViewById(R.id.e4);
        e5 = (Button) findViewById(R.id.e5);
        e6 = (Button) findViewById(R.id.e6);
        e7 = (Button) findViewById(R.id.e7);
        e8 = (Button) findViewById(R.id.e8);

        f1 = (Button) findViewById(R.id.f1);
        f2 = (Button) findViewById(R.id.f2);
        f3 = (Button) findViewById(R.id.f3);
        f4 = (Button) findViewById(R.id.f4);
        f5 = (Button) findViewById(R.id.f5);
        f6 = (Button) findViewById(R.id.f6);
        f7 = (Button) findViewById(R.id.f7);
        f8 = (Button) findViewById(R.id.f8);

        g1 = (Button) findViewById(R.id.g1);
        g2 = (Button) findViewById(R.id.g2);
        g3 = (Button) findViewById(R.id.g3);
        g4 = (Button) findViewById(R.id.g4);
        g5 = (Button) findViewById(R.id.g5);
        g6 = (Button) findViewById(R.id.g6);
        g7 = (Button) findViewById(R.id.g7);
        g8 = (Button) findViewById(R.id.g8);

        h1 = (Button) findViewById(R.id.h1);
        h2 = (Button) findViewById(R.id.h2);
        h3 = (Button) findViewById(R.id.h3);
        h4 = (Button) findViewById(R.id.h4);
        h5 = (Button) findViewById(R.id.h5);
        h6 = (Button) findViewById(R.id.h6);
        h7 = (Button) findViewById(R.id.h7);
        h8 = (Button) findViewById(R.id.h8);
         */

    }

    @Override
    public void onClick(View view) {

        if (view.equals(spielfeld[0][0])) {
            System.out.println("oben links");
        }

        if (view.equals(spielfeld[7][0])) {
            System.out.println("unten links");
        }

        if (view.equals(spielfeld[7][7])) {
            System.out.println("unten rechts");
        }
    }
}
