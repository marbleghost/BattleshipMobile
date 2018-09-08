package ks.battleshipmobile;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;
import android.net.Uri;

public class Startbildschirm extends AppCompatActivity implements View.OnClickListener {

    FloatingActionButton start, anleitung, leichteki, mittlereki, schwierigeki;
    TextView textleichteki, textmittlereki, textschwierigeki;
    boolean schwierigkeitsauswahloffen = false;

    Animation fab_oeffnen;
    Animation fab_schliessen;
    VideoView hganimview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); // sorgt dafür, dass die Titelleiste mit dem Appnamen nicht mehr oben angezeigt wird.
        setContentView(R.layout.activity_startbildschirm);

        /**
         * Achtung! AndroidStudio markiert die "(FloatingActionButton)" Anweisung (und die der anderen) als ueberfluessig, ist diese
         *jedoch nicht angegeben, wirft der Logcat Fehlermeldungen aus
         */
        start = (FloatingActionButton) findViewById(R.id.button_spielauswahl);
        anleitung = (FloatingActionButton) findViewById(R.id.button_anleitung);

        leichteki = (FloatingActionButton) findViewById(R.id.button_kileicht);
        mittlereki = (FloatingActionButton) findViewById(R.id.button_kimittel);
        schwierigeki = (FloatingActionButton) findViewById(R.id.button_kischwer);

        textleichteki = (TextView) findViewById(R.id.textkileicht);
        textmittlereki = (TextView) findViewById(R.id.textkimittel);
        textschwierigeki = (TextView) findViewById(R.id.textkischwer);

        //OnClickListener fuer jeden Button initialisieren
        start.setOnClickListener(this);
        anleitung.setOnClickListener(this);
        leichteki.setOnClickListener(this);
        mittlereki.setOnClickListener(this);
        schwierigeki.setOnClickListener(this);

        //Animationsdateien
        fab_oeffnen = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_oeffnen);
        fab_schliessen = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_schliessen);

        //VideoViewer fuer die Hintergundanimation hinzufuegen und Animation starten
        hganimview = (VideoView) findViewById(R.id.hg_animation);
        Uri startanim = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.animationsdatei);

        hganimview.setVideoURI(startanim);
        hganimview.start();

        hganimview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                //auf false stellen, damit das Video nur einmal abgespielt wird
                mp.setLooping(true);
            }
        });

    }

    @Override
    public void onClick(View e) {
        if (e.equals(start)) {
            //Snackbar.make(e, "was auch immer das ist", Snackbar.LENGTH_LONG).setAction("action", null).show();
            if (schwierigkeitsauswahloffen == false) { // Oeffnet das Auswahlfenster für den Schwierigkeitsgrad.
                leichteki.startAnimation(fab_oeffnen);
                textleichteki.startAnimation(fab_oeffnen);
                leichteki.setClickable(true);
                mittlereki.startAnimation(fab_oeffnen);
                textmittlereki.startAnimation(fab_oeffnen);
                mittlereki.setClickable(true);
                schwierigeki.startAnimation(fab_oeffnen);
                textschwierigeki.startAnimation(fab_oeffnen);
                schwierigeki.setClickable(true);
                anleitung.setVisibility(View.GONE);
                anleitung.setClickable(false);
                schwierigkeitsauswahloffen = true;
            }
            else { //Schließt das Auswahlfenster, wenn es bereits offen ist.
                leichteki.startAnimation(fab_schliessen);
                textleichteki.startAnimation(fab_schliessen);
                leichteki.setClickable(false);
                mittlereki.startAnimation(fab_schliessen);
                textmittlereki.startAnimation(fab_schliessen);
                mittlereki.setClickable(false);
                schwierigeki.startAnimation(fab_schliessen);
                textschwierigeki.startAnimation(fab_schliessen);
                schwierigeki.setClickable(false);
                anleitung.setVisibility(View.VISIBLE);
                anleitung.setClickable(true);
                schwierigkeitsauswahloffen = false;
            }
        }
        if (e.equals(anleitung)) {

        }
        if (e.equals(leichteki)) {

        }
        if (e.equals(mittlereki)) {
            Intent intent = new Intent(this, Schiffesetzen.class);
            startActivity(intent);
        }
        if (e.equals(schwierigeki)) {

        }

    }

}
