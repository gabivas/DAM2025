package ro.ase.seminar03;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PreiaDateActivity extends AppCompatActivity {
    EditText etAdresa, etDataLivrare;
    Button btnSalveaza;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_preia_date);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etAdresa = findViewById(R.id.etAdress);
        etDataLivrare = findViewById(R.id.etData);
        btnSalveaza = findViewById(R.id.btnSave);

        btnSalveaza.setOnClickListener(view -> {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String adresa = etAdresa.getText().toString();
            Date dataLivrare = null;
            try {
                dataLivrare = sdf.parse(etDataLivrare.getText().toString());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            Food food = new Food("De baza", 3, 2, adresa, dataLivrare, Produse.APA, Valuta.RON);
            Log.i("food", food.toString());
            finish();
        });
    }
}