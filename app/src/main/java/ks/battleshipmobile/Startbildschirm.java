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
    FloatingActionButton kimittel, kileicht, kischwer;
    boolean spielauswahloffen = false;

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
        kileicht = (FloatingActionButton) findViewById(R.id.button_kileicht);
        kimittel = (FloatingActionButton) findViewById(R.id.button_kimittel);
        kischwer = (FloatingActionButton) findViewById(R.id.button_kischwer);

        start.setOnClickListener(this);
        anleitung.setOnClickListener(this);
        kimittel.setOnClickListener(this);
        kileicht.setOnClickListener(this);
        kischwer.setOnClickListener(this);

        fab_oeffnen = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_oeffnen);
        fab_schliessen = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_schliessen);

    }

    @Override
    public void onClick(View e) {
        if (e.equals(start)) {
            //Snackbar.make(e, "was auch immer das ist", Snackbar.LENGTH_LONG).setAction("action", null).show();
            if (spielauswahloffen == false) {
                kileicht.startAnimation(fab_oeffnen);
                kileicht.setClickable(true);
                kimittel.startAnimation(fab_oeffnen);
                kimittel.setClickable(true);
                kischwer.startAnimation(fab_oeffnen);
                kischwer.setClickable(true);
                spielauswahloffen = true;
            }
            else {
                kileicht.startAnimation(fab_schliessen);
                kileicht.setClickable(false);
                kimittel.startAnimation(fab_schliessen);
                kimittel.setClickable(false);
                kischwer.startAnimation(fab_schliessen);
                kischwer.setClickable(false);
                spielauswahloffen = false;
            }
        }
        if (e.equals(anleitung)) {
            System.out.println("anleitung");
            //TODO: Button soll Anleitungsbidlschirm öffnen
        }
        if (e.equals(kimittel)) {
            System.out.println("mittel");
        }
    }

}
