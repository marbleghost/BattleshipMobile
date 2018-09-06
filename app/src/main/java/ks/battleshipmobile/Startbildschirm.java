package ks.battleshipmobile;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Startbildschirm extends AppCompatActivity implements View.OnClickListener {

    FloatingActionButton start;
    FloatingActionButton anleitung;

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

        start.setOnClickListener(this);
        anleitung.setOnClickListener(this);
    }

    @Override
    public void onClick(View e) {
        if (e.equals(start)) {
            System.out.println("starten");
            //TODO: Button soll weitere Buttons öffnen um ein bestimmten Spielmodus zu starten
        }
        if (e.equals(anleitung)) {
            System.out.println("anleitung");
            //TODO: Button soll Anleitungsbidlschirm öffnen
        }
    }

}
