package ks.battleshipmobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Spielbildschirm extends AppCompatActivity implements View.OnClickListener {

    TextView spielername_textfeld;

    String spielername;

    Button[][] spielfeld = new Button[8][8];
    int [][] spielfeldbesetzung = new int [8][8];

    Spiellogik logik = new Spiellogik();

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

        //Name und Spielfeldwerte werden uebergeben
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            spielfeldbesetzung = (int [][]) extras.getSerializable("SPIELFELD_UEBERGABE");
            spielername = intent.getStringExtra("SPIELERNAME");
        }

        spielername_textfeld = findViewById(R.id.Spielername_Anzeige);
        spielername_textfeld.setText(spielername);

        buttonListenerHinzufuegen();

    }

    public void buttonListenerHinzufuegen() {
        int k=0;
        for (int i=0; i<8; i++) {
            for (int j=0; j<8; j++) {
                spielfeld[i][j] = (Button) findViewById(idArray[k]);
                spielfeld[i][j].setOnClickListener(this);
                k++;
            }
        }
    }

    @Override
    public void onClick(View view) {
        for (int i=0; i<8; i++) {
            for (int j=0; j<8; j++) {
                if (view.equals(spielfeld[i][j])) {
                    logik.schiffsteilGetroffen(i, j, spielfeldbesetzung, spielfeld);
                }
            }
        }
    }

}
