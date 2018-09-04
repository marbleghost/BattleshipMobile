package ks.battleshipmobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Startbildschirm extends AppCompatActivity implements View.OnClickListener {

    Button start;
    Button anleitung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startbildschirm);

        start = findViewById(R.id.spielstartenbutton);
        anleitung = findViewById(R.id.anleitungsbutton);

        start.setOnClickListener(this);
        anleitung.setOnClickListener(this);
    }
    @Override
    public void onClick(View e) {
        if (e.equals(start)) {
            System.out.println("starten");
        }
        if (e.equals(anleitung)) {
            System.out.println("anleitung");
        }
    }
}
