package ks.battleshipmobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

public class Verliererbildschirm extends AppCompatActivity {

    String spielername;
    Button hauptmenue;
    TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); // sorgt dafür, dass die Titelleiste mit dem Appnamen nicht mehr oben angezeigt wird.
        setContentView(R.layout.activity_verliererbildschirm);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            spielername = intent.getStringExtra("SPIELERNAME_UEBERGABE");
        }

        name = findViewById(R.id.verlierername);
        hauptmenue = findViewById(R.id.hauptmenue);

        name.setText(spielername);

        hauptmenue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent neu = new Intent(Verliererbildschirm.this, Startbildschirm.class);
                startActivity(neu);
            }
        });
    }
}
