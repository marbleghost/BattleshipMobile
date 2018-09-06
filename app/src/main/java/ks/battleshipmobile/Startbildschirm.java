package ks.battleshipmobile;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class Startbildschirm extends AppCompatActivity implements View.OnClickListener {

    FloatingActionButton start;
    FloatingActionButton anleitung;
    FloatingActionButton leichteki, mittlereki, schwierigeki;
    boolean schwierigkeitsauswahloffen = false;

    Animation fab_oeffnen;
    Animation fab_schliessen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); // sorgt dafür, dass die Titelleiste mit dem Appnamen nicht mehr oben angezeigt wird.
        setContentView(R.layout.activity_startbildschirm);

        /**
         * Achtung! AndroidStudio markiert die "(FloatingActionButton)" Anweisung als ueberfluessig, ist diese
         *jedoch nicht angegeben, wirft der Logcat Fehlermeldungen aus
         */
        start = (FloatingActionButton) findViewById(R.id.button_spielauswahl);
        anleitung = (FloatingActionButton) findViewById(R.id.button_anleitung);
        leichteki = (FloatingActionButton) findViewById(R.id.button_kileicht);
        mittlereki = (FloatingActionButton) findViewById(R.id.button_kimittel);
        schwierigeki = (FloatingActionButton) findViewById(R.id.button_kischwer);

        start.setOnClickListener(this);
        anleitung.setOnClickListener(this);
        leichteki.setOnClickListener(this);
        mittlereki.setOnClickListener(this);
        schwierigeki.setOnClickListener(this);

        fab_oeffnen = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_oeffnen);
        fab_schliessen = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_schliessen);

    }

    @Override
    public void onClick(View e) {
        if (e.equals(start)) {
            //Snackbar.make(e, "was auch immer das ist", Snackbar.LENGTH_LONG).setAction("action", null).show();
            if (schwierigkeitsauswahloffen == false) { // Oeffnet das Auswahlfenster für den Schwierigkeitsgrad.
                leichteki.startAnimation(fab_oeffnen);
                leichteki.setClickable(true);
                mittlereki.startAnimation(fab_oeffnen);
                mittlereki.setClickable(true);
                schwierigeki.startAnimation(fab_oeffnen);
                schwierigeki.setClickable(true);
                schwierigkeitsauswahloffen = true;
            }
            else { //Schließt das Auswahlfenster, wenn es bereits offen ist.
                leichteki.startAnimation(fab_schliessen);
                leichteki.setClickable(false);
                mittlereki.startAnimation(fab_schliessen);
                mittlereki.setClickable(false);
                schwierigeki.startAnimation(fab_schliessen);
                schwierigeki.setClickable(false);
                schwierigkeitsauswahloffen = false;
            }
        }
        if (e.equals(anleitung)) {
            System.out.println("anleitung");
            //TODO: Button soll Anleitungsbidlschirm öffnen
        }
        if (e.equals(mittlereki)) {
            System.out.println("mittel");
        }
    }

}
