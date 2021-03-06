package ks.battleshipmobile;

import android.content.ClipData;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Objects;

/**
 * Source Code der Klasse fuer das Schiffe setzen.
 */
public class Schiffesetzen extends AppCompatActivity implements View.OnClickListener {

    //Speichert die Button ID´s in einem Array, um sie später den Buttons leichter zuweisen zu koennen
    int [] idArray =   {R.id.a1, R.id.a2, R.id.a3, R.id.a4, R.id.a5, R.id.a6, R.id.a7, R.id.a8,
                        R.id.b1, R.id.b2, R.id.b3, R.id.b4, R.id.b5, R.id.b6, R.id.b7, R.id.b8,
                        R.id.c1, R.id.c2, R.id.c3, R.id.c4, R.id.c5, R.id.c6, R.id.c7, R.id.c8,
                        R.id.d1, R.id.d2, R.id.d3, R.id.d4, R.id.d5, R.id.d6, R.id.d7, R.id.d8,
                        R.id.e1, R.id.e2, R.id.e3, R.id.e4, R.id.e5, R.id.e6, R.id.e7, R.id.e8,
                        R.id.f1, R.id.f2, R.id.f3, R.id.f4, R.id.f5, R.id.f6, R.id.f7, R.id.f8,
                        R.id.g1, R.id.g2, R.id.g3, R.id.g4, R.id.g5, R.id.g6, R.id.g7, R.id.g8,
                        R.id.h1, R.id.h2, R.id.h3, R.id.h4, R.id.h5, R.id.h6, R.id.h7, R.id.h8};

    Animation weiter_button_oeffnen;
    Animation weiter_button_schliessen;

    Button [][] spielfeld = new Button[8][8];
    int [][] spielfeldbesetzung = new int [8][8];
    int [][] spielfeldbesetzungspieler1 = new int [8][8];
    int [][] spielfeldbesetzungspieler2 = new int [8][8];
    String spielername;
    TextView spielernameAnzeige;
    int ki;

    Button zweierSchiff;
    Button dreierSchiff;
    Button viererSchiff;
    FloatingActionButton spielfeldZuruecksetzen, spielfeldRandomSetzen;
    FloatingActionButton weiter;
    Switch auswahl_schiffsrichtung;

    boolean vertikal; //Wird benoetigt, damit der Spieler die Schiffe vertikal oder Horizontal setzen kann.

    Spiellogik logik = new Spiellogik();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide(); // sorgt dafür, dass die Titelleiste mit dem Appnamen nicht mehr oben angezeigt wird.
        setContentView(R.layout.activity_schiffesetzen);

        //Der mit dem Intent aus dem Startbildschirm uebergebene Name wird auf das Textfeld oben geschrieben
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            spielername = extras.getString("SPIELERNAME_UEBERGABE");
            ki = extras.getInt("KI");
        }

        spielernameAnzeige = findViewById(R.id.Spielername_Anzeige);
        spielernameAnzeige.setText(spielername);

        buttonListenerHinzufuegen();

        zweierSchiff = findViewById(R.id.button_zweier);
        dreierSchiff = findViewById(R.id.button_dreier);
        viererSchiff = findViewById(R.id.button_vierer);

        spielfeldZuruecksetzen = findViewById(R.id.spielfeld_zuruecksetzen);
        weiter = findViewById(R.id.weiter_zum_spiel);
        spielfeldRandomSetzen = findViewById(R.id.spielfeld_random_setzen);

        auswahl_schiffsrichtung = findViewById(R.id.auswahl_schiffsrichtung);

        zweierSchiff.setOnTouchListener(touchListener);
        dreierSchiff.setOnTouchListener(touchListener);
        viererSchiff.setOnTouchListener(touchListener);

        weiter_button_oeffnen = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_gross_oeffnen);
        weiter_button_schliessen = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_gross_schliessen);

        spielfeldZuruecksetzen.setOnClickListener(this);
        weiter.setOnClickListener(this);
        weiter.setClickable(false);
        spielfeldRandomSetzen.setOnClickListener(this);

        auswahl_schiffsrichtung.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    vertikal = true;
                    System.out.println(spielername);
                }
                else {
                    vertikal = false;
                }
            }
        });
    }

    public void buttonListenerHinzufuegen() {
        int k=0;
        for (int i=0; i<8; i++) {
            for (int j=0; j<8; j++) {
                spielfeld[i][j] = findViewById(idArray[k]);
                spielfeld[i][j].setOnDragListener(dragListener);
                k++;
            }
        }
    }

    View.OnTouchListener touchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            ClipData data = ClipData.newPlainText("", "");
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
            view.startDrag(data, shadowBuilder, view, 0);
            return true;
        }
    };

    View.OnDragListener dragListener = new View.OnDragListener() {
        @Override
        public boolean onDrag(View view, DragEvent dragEvent) {
            int de = dragEvent.getAction();

            switch (de) {
                case DragEvent.ACTION_DRAG_ENTERED:
                    View v2 = (View) dragEvent.getLocalState();

                    if (v2.getId() == R.id.button_zweier) {
                        for (int i=0; i<8; i++) {
                            for (int j=0; j<8; j++) {
                                if (view.equals(spielfeld[i][j])) {
                                    logik.schiffSchatten(9, logik.zweierSchiff, vertikal, i, j, spielfeldbesetzung);
                                    logik.farbeAnpassen(spielfeldbesetzung, spielfeld);
                                }
                            }
                        }
                    }
                    else if (v2.getId() == R.id.button_dreier) {
                        for (int i=0; i<8; i++) {
                            for (int j=0; j<8; j++) {
                                if (view.equals(spielfeld[i][j])) {
                                    logik.schiffSchatten(9, logik.dreierSchiff, vertikal, i, j, spielfeldbesetzung);
                                    logik.farbeAnpassen(spielfeldbesetzung, spielfeld);
                                }
                            }
                        }
                    }
                    else if (v2.getId() == R.id.button_vierer) {
                        for (int i=0; i<8; i++) {
                            for (int j=0; j<8; j++) {
                                if (view.equals(spielfeld[i][j])) {
                                    logik.schiffSchatten(9, logik.viererSchiff, vertikal, i, j, spielfeldbesetzung);
                                    logik.farbeAnpassen(spielfeldbesetzung, spielfeld);
                                }
                            }
                        }
                    }
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    View v3 = (View) dragEvent.getLocalState();

                    for (int i=0; i<8; i++) {
                        for (int j=0; j<8; j++) {
                            if(v3.getId() == R.id.button_zweier) {
                                logik.schiffSchatten(0,logik.zweierSchiff, vertikal, i, j, spielfeldbesetzung);
                                logik.farbeAnpassen(spielfeldbesetzung, spielfeld);
                            }
                            else if (v3.getId() == R.id.button_dreier) {
                                logik.schiffSchatten(0,logik.dreierSchiff, vertikal, i, j, spielfeldbesetzung);
                                logik.farbeAnpassen(spielfeldbesetzung, spielfeld);
                            }
                            else if (v3.getId() == R.id.button_vierer) {
                                logik.schiffSchatten(0,logik.viererSchiff, vertikal, i, j, spielfeldbesetzung);
                                logik.farbeAnpassen(spielfeldbesetzung, spielfeld);
                            }
                        }
                    }

                    break;
                case DragEvent.ACTION_DROP:
                    final View v = (View) dragEvent.getLocalState();

                    if (v.getId() == R.id.button_zweier) {
                        for (int i=0; i<8; i++) {
                            for (int j=0; j<8; j++) {
                                if (view.equals(spielfeld[i][j])) {
                                    logik.schiffeSetzen(logik.zweierSchiff, vertikal, i, j, spielfeldbesetzung);
                                    logik.farbeAnpassen(spielfeldbesetzung, spielfeld);
                                    if (logik.getPasst()) {
                                        logik.anzahlZweier--;
                                    }
                                    if (logik.anzahlZweier == 0) {
                                        zweierSchiff.setEnabled(false); //graut den Button aus.
                                    }
                                }
                            }
                        }

                    }
                    else if (v.getId() == R.id.button_dreier) {
                        for (int i=0; i<8; i++) {
                            for (int j=0; j<8; j++) {
                                if (view.equals(spielfeld[i][j])) {
                                    logik.schiffeSetzen(logik.dreierSchiff, vertikal, i, j, spielfeldbesetzung);
                                    logik.farbeAnpassen(spielfeldbesetzung, spielfeld);
                                    if (logik.getPasst() == true) {
                                        logik.anzahlDreier--;
                                    }
                                    if (logik.anzahlDreier == 0) {
                                        dreierSchiff.setEnabled(false);
                                    }
                                }
                            }
                        }
                    }
                    else if (v.getId() == R.id.button_vierer) {
                        for (int i=0; i<8; i++) {
                            for (int j=0; j<8; j++) {
                                if (view.equals(spielfeld[i][j])) {
                                    logik.schiffeSetzen(logik.viererSchiff, vertikal, i, j, spielfeldbesetzung);
                                    logik.farbeAnpassen(spielfeldbesetzung, spielfeld);
                                    if (logik.getPasst() == true) {
                                        logik.anzahlVierer--;
                                    }
                                    if (logik.anzahlVierer == 0) {
                                        viererSchiff.setEnabled(false);
                                    }
                                }
                            }
                        }
                    }
                    if (logik.alleSchiffeGesetzt()) {
                        weiter.startAnimation(weiter_button_oeffnen);
                        weiter.setClickable(true);
                    }
                    break;
            }
            return true;

        }
    };
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.spielfeld_zuruecksetzen) {
            logik.setSchiffsanzahl();
            logik.spielfeldZuruecksetzen(spielfeldbesetzung);
            logik.farbeAnpassen(spielfeldbesetzung, spielfeld);
            zweierSchiff.setEnabled(true);
            dreierSchiff.setEnabled(true);
            viererSchiff.setEnabled(true);
            auswahl_schiffsrichtung.setEnabled(true);
            //Animation wird nur abgespielt, wenn das Spielfeld schon fertig gesetzt war
            if (weiter.isClickable() == true) {
                weiter.setClickable(false);
                weiter.startAnimation(weiter_button_schliessen);
            }
        }

        else if (view.getId() == R.id.spielfeld_random_setzen) {
            logik.setSchiffsanzahl();
            logik.spielfeldZuruecksetzen(spielfeldbesetzung);
            logik.farbeAnpassen(spielfeldbesetzung, spielfeld);//muss nach dem testen geloescht werden
            logik.zufallsPlatzierung(spielfeldbesetzung);
            logik.farbeAnpassen(spielfeldbesetzung, spielfeld);
            zweierSchiff.setEnabled(false);
            dreierSchiff.setEnabled(false);
            viererSchiff.setEnabled(false);
            auswahl_schiffsrichtung.setEnabled(false);
            weiter.setClickable(true);
            weiter.startAnimation(weiter_button_oeffnen);
        }

        else if (view.getId() == R.id.weiter_zum_spiel) {
            spielfeldbesetzungspieler1 = spielfeldbesetzung;
            logik.kiSchiffeSetzen(spielfeldbesetzungspieler2);
            Intent intent = new Intent(Schiffesetzen.this, Spielbildschirm.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("SPIELFELD_UEBERGABE", spielfeldbesetzungspieler1);
            bundle.putSerializable("SPIELFELD_UEBERGABE_KI", spielfeldbesetzungspieler2);
            intent.putExtra("SPIELERNAME", spielername);
            intent.putExtra("KI", ki);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
}
