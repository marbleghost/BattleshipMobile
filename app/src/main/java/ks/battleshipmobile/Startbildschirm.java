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
        setContentView(R.layout.activity_startbildschirm);

        //start = findViewById(R.id.spielstartenbutton);
        //anleitung = findViewById(R.id.anleitungsbutton);

        /**
         * Achtung! AndroidStudio markiert die "(FloatingActionButton)" Anweisung als ueberfluessig, ist diese
         *jedoch nicht angegeben, wirft der Logcat Fehlermeldungen aus
         */
        start = (FloatingActionButton) findViewById(R.id.button_spielauswahl);
        anleitung = (FloatingActionButton) findViewById(R.id.button_anleitung);

        start.setOnClickListener(this);
        anleitung.setOnClickListener(this);
    }

    //TODO: Wollen wir das wirklich alle in ein OnCklick packen? Das kostet Zeit wenn die App das alles durchlaufen muss
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
