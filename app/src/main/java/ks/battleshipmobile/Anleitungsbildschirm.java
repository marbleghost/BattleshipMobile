package ks.battleshipmobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Objects;

public class Anleitungsbildschirm extends AppCompatActivity {

    Button hauptmenue;
    TextView anleitungstart;
    TextView schiffesetzen, schiffesetzen2, schiffesetzen3;
    TextView dasspiel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide(); // sorgt dafür, dass die Titelleiste mit dem Appnamen nicht mehr oben angezeigt wird.
        setContentView(R.layout.activity_anleitungsbildschirm);

        //ANleitungstexte
        anleitungstart = findViewById(R.id.text_start);
        anleitungstart.setText(R.string.anleitungtext);

        schiffesetzen = findViewById(R.id.text_schiffesetzen);
        schiffesetzen.setText(R.string.anleitung_schiffesetzentext);
        schiffesetzen2 = findViewById(R.id.text_schiffesetzen2);
        schiffesetzen2.setText(R.string.anleitung_schiffesetzentext2);
        schiffesetzen3 = findViewById(R.id.text_schiffesetzen3);
        schiffesetzen3.setText(R.string.anleitung_schiffesetzentext3);
        dasspiel = findViewById(R.id.text_dasspiel);
        dasspiel.setText(R.string.dasspieltext);

        //Button um zurueck ins Hauptmenue zu gelangen
        hauptmenue = findViewById(R.id.zurueck);
        hauptmenue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Anleitungsbildschirm.this, Startbildschirm.class);
                startActivity(intent);
            }
        });
    }
}
