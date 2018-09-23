package ks.battleshipmobile;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.VideoView;
import android.net.Uri;

/**
 * Source Code des Hauptmenues fuer das Spiel
 *
 * KI Werte:
 * 1-Leichte KI
 * 2-Mittlere KI
 * 3-Schwierige KI
 */
public class Startbildschirm extends AppCompatActivity implements View.OnClickListener {

    FloatingActionButton start, anleitung, leichteki, mittlereki, schwierigeki;
    TextView textleichteki, textmittlereki, textschwierigeki;
    boolean schwierigkeitsauswahloffen = false;

    Animation fab_oeffnen, fab_gross_oeffnen;
    Animation fab_schliessen, fab_gross_schliessen;
    VideoView hganimview;
    private int anim_zaehler = 0;

    EditText spielername;
    String name;
    Button weiter;

    int ki= 0;

    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); // sorgt dafür, dass die Titelleiste mit dem Appnamen nicht mehr oben angezeigt wird.
        setContentView(R.layout.activity_startbildschirm);

         //Achtung! AndroidStudio markiert die "(FloatingActionButton)" Anweisung (und die der anderen) als ueberfluessig, ist diese
         //jedoch nicht angegeben, wirft der Logcat Fehlermeldungen aus

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
        fab_gross_oeffnen = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_gross_oeffnen);
        fab_gross_schliessen = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_gross_schliessen);

        //VideoViewer fuer die Hintergrundanimation hinzufuegen und Animation starten
        hganimview = (VideoView) findViewById(R.id.hg_animation);
        Uri anim_steigendes_wasser = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.anim_ansteigendes_wasser);
        final Uri anim_einfahren_schiff = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.anim_einfahren_schiff);
        final Uri anim_schiff_idle = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.anim_schiff_idle);
        final Uri anim_schiff_sinkend = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.anim_schiff_sinkend);

        //Wenn die Idle Animation gestartet ist, werden der Start und Anleitungsbutton eingeblendet
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                start.startAnimation(fab_gross_oeffnen);
                start.setClickable(true);
                anleitung.startAnimation(fab_gross_oeffnen);
                anleitung.setClickable(true);
            }
        }, 6000);

        hganimview.setVideoURI(anim_steigendes_wasser);

        hganimview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                hganimview.start();
                mp.setLooping(false);
            }
        });

        hganimview.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaplayer) {

                if (anim_zaehler == 0) {
                    hganimview.setVideoURI(anim_einfahren_schiff);
                    anim_zaehler++;
                }
                else if (anim_zaehler == 1) {
                    hganimview.setVideoURI(anim_schiff_idle);
                }
                else if (anim_zaehler == 2) {
                    hganimview.setVideoURI(anim_schiff_sinkend);
                }

            }
        });

    }

    @Override
    public void onClick(View e) {
        if (e.equals(start)) {
            if (schwierigkeitsauswahloffen == false) { // Oeffnet das Auswahlfenster für den Schwierigkeitsgrad.
                schwierigkeitsstufenEinblenden();
            }
            else { //Schließt das Auswahlfenster, wenn es bereits offen ist.
                schwierigkeitsstufenausblenden();
            }
        }
        if (e.equals(anleitung)) {
            Intent intent = new Intent(Startbildschirm.this, Anleitungsbildschirm.class);
            startActivity(intent);
        }
        if (e.equals(leichteki)) {
            ki = 1;
            spielStarten();
        }
        if (e.equals(mittlereki)) {
            ki = 2;
            spielStarten();
        }
        if (e.equals(schwierigeki)) {
            ki = 3;
            spielStarten();
        }

    }

    /**
     * Sobald ein Button zum Spielstart gedrueckt wird, wird diese Methode aufgerufen.
     * Sie oeffnet ein Popup Fenster (AlertDialog) und fordert den Spieler zu der Eingabe
     * seines Namens auf
     */
    public void spielStarten() {
        AlertDialog.Builder popupbuilder = new AlertDialog.Builder(Startbildschirm.this);
        View popupview = getLayoutInflater().inflate(R.layout.popup_spielername, null);
        spielername = (EditText) popupview.findViewById(R.id.label_spielername_eigeben);
        weiter = (Button) popupview.findViewById(R.id.button_spierlername_weiter);

        weiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anim_zaehler = 2;
                spielername.onEditorAction(EditorInfo.IME_ACTION_DONE);

                //Wenn der Weiter-Button geklickt wurde, wird ebendieser unklickbar und das Textfeld uneditierbar (solange Animation laeuft)
                weiter.setEnabled(false);
                spielername.setEnabled(false);
                weiter.setText("Bitte warten");

                //Der Spielername wird ermittelt. Bei keiner Einagbe wird standartmaessig "Spieler" verwendet
                if (spielername.getText().toString().length() == 0) {
                    name = "Spieler";
                }
                else {
                    name = spielername.getText().toString();
                }

                //Der neue Bildschirm wird erst geoeffnet wenn die Animation des sinkenden Schiffes durch ist
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(Startbildschirm.this, Schiffesetzen.class);
                        intent.putExtra("SPIELERNAME_UEBERGABE", name);
                        intent.putExtra("KI", ki);
                        startActivity(intent);
                    }
                }, 3050);

            }
        });

        popupbuilder.setView(popupview);
        AlertDialog dialog = popupbuilder.create();
        dialog.show();
        start.startAnimation(fab_gross_schliessen);
        start.setClickable(false);
    }

    /**
     * Ueber die in den XML Dateien angelegten Animaionen werden die Buttons fuer die Auswahl des
     * Spielmodis eingeblendet und klickbar gemacht
     */
    public void schwierigkeitsstufenEinblenden() {
        leichteki.startAnimation(fab_oeffnen);
        textleichteki.startAnimation(fab_oeffnen);
        leichteki.setClickable(true);
        mittlereki.startAnimation(fab_oeffnen);
        textmittlereki.startAnimation(fab_oeffnen);
        mittlereki.setClickable(true);
        schwierigeki.startAnimation(fab_oeffnen);
        textschwierigeki.startAnimation(fab_oeffnen);
        schwierigeki.setClickable(true);
        anleitung.startAnimation(fab_gross_schliessen);
        anleitung.setClickable(false);
        schwierigkeitsauswahloffen = true;
    }

    /**
     * Ueber die in den XML Dateien angelegten Animaionen werden die Buttons fuer die Auswahl des
     * Spielmodis ausgeblendet und sind nicht mehr klickbar
     */
    public void schwierigkeitsstufenausblenden() {
        leichteki.startAnimation(fab_schliessen);
        textleichteki.startAnimation(fab_schliessen);
        leichteki.setClickable(false);
        mittlereki.startAnimation(fab_schliessen);
        textmittlereki.startAnimation(fab_schliessen);
        mittlereki.setClickable(false);
        schwierigeki.startAnimation(fab_schliessen);
        textschwierigeki.startAnimation(fab_schliessen);
        schwierigeki.setClickable(false);
        anleitung.startAnimation(fab_gross_oeffnen);
        anleitung.setClickable(true);
        schwierigkeitsauswahloffen = false;
    }
}
