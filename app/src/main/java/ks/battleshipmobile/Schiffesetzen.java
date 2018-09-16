package ks.battleshipmobile;

import android.content.ClipData;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.widget.Button;

public class Schiffesetzen extends AppCompatActivity implements View.OnClickListener {


    /**
     * Speichert die Button ID´s in einem Array, um sie später den Buttons leichter zuweisen zu koennen
     */
    int [] idArray =   {R.id.a1, R.id.a2, R.id.a3, R.id.a4, R.id.a5, R.id.a6, R.id.a7, R.id.a8,
                        R.id.b1, R.id.b2, R.id.b3, R.id.b4, R.id.b5, R.id.b6, R.id.b7, R.id.b8,
                        R.id.c1, R.id.c2, R.id.c3, R.id.c4, R.id.c5, R.id.c6, R.id.c7, R.id.c8,
                        R.id.d1, R.id.d2, R.id.d3, R.id.d4, R.id.d5, R.id.d6, R.id.d7, R.id.d8,
                        R.id.e1, R.id.e2, R.id.e3, R.id.e4, R.id.e5, R.id.e6, R.id.e7, R.id.e8,
                        R.id.f1, R.id.f2, R.id.f3, R.id.f4, R.id.f5, R.id.f6, R.id.f7, R.id.f8,
                        R.id.g1, R.id.g2, R.id.g3, R.id.g4, R.id.g5, R.id.g6, R.id.g7, R.id.g8,
                        R.id.h1, R.id.h2, R.id.h3, R.id.h4, R.id.h5, R.id.h6, R.id.h7, R.id.h8};
    Button [][] spielfeld = new Button[8][8];
    int [][] spielfeldbesetzung = new int [8][8];
    Button zweierSchiff;
    Button dreierSchiff;
    Button viererSchiff;

    Spiellogik logik = new Spiellogik();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); // sorgt dafür, dass die Titelleiste mit dem Appnamen nicht mehr oben angezeigt wird.
        setContentView(R.layout.activity_schiffesetzen);

        buttonListenerHinzufuegen();

        zweierSchiff = findViewById(R.id.button_zweier);
        dreierSchiff = findViewById(R.id.button_dreier);
        viererSchiff = findViewById(R.id.button_vierer);

        zweierSchiff.setOnLongClickListener(longClickListener);
        dreierSchiff.setOnLongClickListener(longClickListener);
        viererSchiff.setOnLongClickListener(longClickListener);

    }

    public void buttonListenerHinzufuegen() {
        int k=0;
        for (int i=0; i<8; i++) {
            for (int j=0; j<8; j++) {
                spielfeld[i][j] = (Button) findViewById(idArray[k]);
                spielfeld[i][j].setOnDragListener(dragListener);
                k++;
            }
        }
    }



    View.OnLongClickListener longClickListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View view) {
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
                    final View v = (View) dragEvent.getLocalState();

                    if (v.getId() == R.id.button_zweier) {
                        logik.schiffeSetzen(2, true, 1, 1);
                    }
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DROP:
                    break;
            }
            return true;
        }
    };
    @Override
    public void onClick(View view) { // TODO: Wenn das Feld mit 3 Markiert ist, muss ausgegeben werden, dass keine Schiffe nebeneinander gesetzt werden dürfen.

        /*for (int i=0; i<8; i++) {
            for (int j=0; j<8; j++) {
                if (view.equals(spielfeld[i][j])) {
                    System.out.println(spielfeldbesetzung[i][j]);
                    if (spielfeldbesetzung[i][j] == 0 && logik.platzIstBesetzbar(i, j, spielfeldbesetzung)) {
                        spielfeldbesetzung[i][j] = 1;
                        //spielfeld[i][j].setBackgroundColor(1);
                        logik.umgebungMarkieren(i, j, spielfeldbesetzung);
                        logik.farbeAnpassen(spielfeldbesetzung, spielfeld);
                    }
                    else if (spielfeldbesetzung[i][j] == 1) {
                        spielfeldbesetzung[i][j] = 0;
                        //spielfeld[i][j].setBackgroundColor(0xFF33B5E5);
                        logik.markierungenEntfernen(i, j, spielfeldbesetzung);
                        logik.farbeAnpassen(spielfeldbesetzung, spielfeld);
                    }

                }
            }
        }*/
    }


}
