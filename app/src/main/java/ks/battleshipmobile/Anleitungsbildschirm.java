package ks.battleshipmobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;

public class Anleitungsbildschirm extends AppCompatActivity {

    Button hauptmenue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); // sorgt dafür, dass die Titelleiste mit dem Appnamen nicht mehr oben angezeigt wird.
        setContentView(R.layout.activity_anleitungsbildschirm);

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
