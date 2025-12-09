package ro.ase.seminar08;

import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private static final String urlBNR = "https://www.bnr.ro/nbrfxrates.xml";
    private TextView tvCursValutar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initComponente();
        incarcareCursValutarDinRetea();
    }

    private void incarcareCursValutarDinRetea() {
        Thread thread = new Thread(() -> {
            HttpsManager httpsManager = new HttpsManager(urlBNR);
            String rezultat = httpsManager.procesare();
            new Handler(getMainLooper()).post(() -> {
                preluareCursValutarDinXml(rezultat);
            });
        });
        thread.start();
    }

    private void preluareCursValutarDinXml(String xml) {
        CursValutar cursValutar = CursValutarParser.parsareXml(xml);
        tvCursValutar.setText(cursValutar.toString());
    }

    private void initComponente() {
        tvCursValutar = findViewById(R.id.tvCursValutar);
    }
}