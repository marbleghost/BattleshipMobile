package ks.battleshipmobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Schiffesetzen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); // sorgt dafür, dass die Titelleiste mit dem Appnamen nicht mehr oben angezeigt wird.
        setContentView(R.layout.activity_schiffesetzen);
    }
}
