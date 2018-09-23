package ks.battleshipmobile;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Source Code des Spielbildschirms. Hier findet das Spiel statt.
 */
public class Spielbildschirm extends AppCompatActivity implements View.OnClickListener {

    TextView spielername_textfeld;

    String spielername;
    int ki;

    Button weiter;
    Button[][] spielfeld = new Button[8][8];
    int [][] spielfeldbesetzungspieler1 = new int [8][8];
    int [][] spielfeldbesetzungspieler2 = new int [8][8];
    int versuche = 1;
    int spieler;

    private Handler handler = new Handler();

    Spiellogik logik = new Spiellogik();
    KI kilogik = new KI();

    //Speichert die Button ID´s in einem Array, um sie später den Buttons leichter zuweisen zu koennen
    int [] idArray =   {R.id.a1, R.id.a2, R.id.a3, R.id.a4, R.id.a5, R.id.a6, R.id.a7, R.id.a8,
            R.id.b1, R.id.b2, R.id.b3, R.id.b4, R.id.b5, R.id.b6, R.id.b7, R.id.b8,
            R.id.c1, R.id.c2, R.id.c3, R.id.c4, R.id.c5, R.id.c6, R.id.c7, R.id.c8,
            R.id.d1, R.id.d2, R.id.d3, R.id.d4, R.id.d5, R.id.d6, R.id.d7, R.id.d8,
            R.id.e1, R.id.e2, R.id.e3, R.id.e4, R.id.e5, R.id.e6, R.id.e7, R.id.e8,
            R.id.f1, R.id.f2, R.id.f3, R.id.f4, R.id.f5, R.id.f6, R.id.f7, R.id.f8,
            R.id.g1, R.id.g2, R.id.g3, R.id.g4, R.id.g5, R.id.g6, R.id.g7, R.id.g8,
            R.id.h1, R.id.h2, R.id.h3, R.id.h4, R.id.h5, R.id.h6, R.id.h7, R.id.h8};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); // sorgt dafür, dass die Titelleiste mit dem Appnamen nicht mehr oben angezeigt wird.
        setContentView(R.layout.activity_spielbildschirm);

        //Spiel beginnt mit dem Nutzer der App, Spieler 1
        spieler = 1;

        //Name und Spielfeldwerte werden uebergeben
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            spielfeldbesetzungspieler1 = (int [][]) extras.getSerializable("SPIELFELD_UEBERGABE");
            spielfeldbesetzungspieler2 = (int [][]) extras.getSerializable("SPIELFELD_UEBERGABE_KI");
            spielername = intent.getStringExtra("SPIELERNAME");
            ki = extras.getInt("KI");
        }

        spielername_textfeld = findViewById(R.id.Spielername_Anzeige);
        spielername_textfeld.setText(spielername);

        weiter = findViewById(R.id.weiter);
        weiter.setOnClickListener(this);
        weiter.setClickable(false);
        weiter.setVisibility(View.INVISIBLE);

        buttonListenerHinzufuegen();

    }

    public void buttonListenerHinzufuegen() {
        int k=0;
        for (int i=0; i<8; i++) {
            for (int j=0; j<8; j++) {
                spielfeld[i][j] = findViewById(idArray[k]);
                spielfeld[i][j].setOnClickListener(this);
                k++;
            }
        }
    }

    @Override
    public void onClick(View view) {

        //Spieler 1 ist am Zug (Nutzer)
        if (spieler == 1) {
            if (view.getId() == R.id.weiter) {
                spielername_textfeld.setText("Gegner");
                logik.spielfeldStatus(spielfeldbesetzungspieler1, spielfeld);
                if (ki == 1) {
                    kilogik.kiLeicht(spielfeldbesetzungspieler1, spielfeld, 1);
                    if (logik.gewonnen(spielfeldbesetzungspieler1)) {
                        Intent intent = new Intent(Spielbildschirm.this, Verliererbildschirm.class);
                        intent.putExtra("SPIELERNAME_UEBERGABE", spielername);
                        startActivity(intent);
                    }
                }
                else if (ki == 2) {
                    kilogik.kiMittel(spielfeldbesetzungspieler1, spielfeld, 1);
                    if (logik.gewonnen(spielfeldbesetzungspieler1)) {
                        Intent intent = new Intent(Spielbildschirm.this, Verliererbildschirm.class);
                        intent.putExtra("SPIELERNAME_UEBERGABE", spielername);
                        startActivity(intent);
                    }
                }
                else if (ki == 3) {
                    kilogik.kiSchwer(spielfeldbesetzungspieler1, spielfeld, 1);
                    if (logik.gewonnen(spielfeldbesetzungspieler1)) {
                        Intent intent = new Intent(Spielbildschirm.this, Verliererbildschirm.class);
                        intent.putExtra("SPIELERNAME_UEBERGABE", spielername);
                        startActivity(intent);
                    }
                }
                spieler = 2;
            }
            else { //Spieler interagiert mit den Buttons.
                if (versuche == 1) {
                    for (int i=0; i<8; i++) {
                        for (int j=0; j<8; j++) {
                            if (view.equals(spielfeld[i][j])) {
                                logik.schussAbgefeuert(i, j, spielfeldbesetzungspieler2, spielfeld);
                                spielfeld[i][j].setClickable(false);
                                if (logik.treffer == true) {
                                    logik.testeSchiffVersenkt(i, j, spielfeldbesetzungspieler2, spielfeld);
                                    if (logik.gewonnen(spielfeldbesetzungspieler2)) {
                                        Intent intent = new Intent(Spielbildschirm.this, Gewonnenbildschirm.class);
                                        intent.putExtra("SPIELERNAME_UEBERGABE", spielername);
                                        startActivity(intent);
                                    }
                                    versuche = 1;
                                }
                                else versuche = 0;
                            }
                        }
                    }
                }

                if (versuche == 0) {
                    for (int i=0; i<8; i++) {
                        for (int j=0; j<8; j++) {
                            spielfeld[i][j].setClickable(false);
                        }
                    }
                    weiter.setClickable(true);
                    weiter.setVisibility(View.VISIBLE);
                }
            }
        }
        else if (spieler == 2) {
            if (view.getId() == R.id.weiter) {
                versuche = 1;
                spielername_textfeld.setText(spielername);
                spieler = 1;
                for (int i=0; i<8; i++) {
                    for (int j=0; j<8; j++) {
                        spielfeld[i][j].setClickable(true);
                    }
                }
                logik.spielfeldStatus(spielfeldbesetzungspieler2, spielfeld);
                weiter.setClickable(false);
                weiter.setVisibility(View.INVISIBLE);
            }
        }
    }
}
