package ro.ase.seminar03;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddFoodDeliveryActivity extends AppCompatActivity {
    EditText etNume, etCantitate, etPret, etAdresa, etDataLivrare, etLivrator;
    Spinner spnProduse;
    RadioButton rbEur;
    Button btnSalveaza;
    boolean isEditing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_food_delivery);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etNume = findViewById(R.id.etNume);
        etLivrator = findViewById(R.id.etLivrator);
        etAdresa = findViewById(R.id.etAdresa);
        etDataLivrare = findViewById(R.id.etDataLivrare);
        etPret = findViewById(R.id.etPret);
        etCantitate = findViewById(R.id.etCantitate);
        spnProduse = findViewById(R.id.spnProduse);
        btnSalveaza = findViewById(R.id.btnSalveaza);
        etCantitate = findViewById(R.id.etCantitate);
        rbEur = findViewById(R.id.rbEur);
        RadioGroup rgValuta = findViewById(R.id.rgValuta);

        String[] produseSpn = {"APA", "CARTOFI", "SUC"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplication(), android.R.layout.simple_spinner_dropdown_item, produseSpn);
        spnProduse.setAdapter(adapter);

        Intent editIntent = getIntent();
        if (editIntent.hasExtra("edit")) {
            isEditing = true;
            Food editFood = editIntent.getSerializableExtra("edit", Food.class);
            etNume.setText(editFood.getNume());
            etAdresa.setText(editFood.getAdresa());
            etCantitate.setText(String.valueOf(editFood.getCantitate()));
            etPret.setText(String.valueOf(editFood.getPret()));
            etDataLivrare.setText(new SimpleDateFormat("dd/MM/yyyy").format(editFood.getDataLivrare()));
            ArrayAdapter<String> editedAdapter = (ArrayAdapter<String>) spnProduse.getAdapter();
            for (int i = 0; i < editedAdapter.getCount(); i++) {
                if (editFood.getProduse().toString().equals(editedAdapter.getItem(i))) {
                    spnProduse.setSelection(i);
                }
            }
            switch (editFood.getValuta().toString()) {
                case "RON": {
                    rgValuta.check(R.id.rbRon);
                    break;
                }
                case "EUR": {
                    rgValuta.check(R.id.rbEur);
                    break;
                }
            }

        }

        btnSalveaza.setOnClickListener(view -> {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String nume = etNume.getText().toString();
            String adresa = etAdresa.getText().toString();
            String livrator = etLivrator.getText().toString();
            Date dataLivrare = null;
            try {
                dataLivrare = sdf.parse(etDataLivrare.getText().toString());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            float pret = Float.valueOf(etPret.getText().toString());
            int cantitate = Integer.valueOf(etCantitate.getText().toString());
            Produse produse = Produse.valueOf(spnProduse.getSelectedItem().toString());
            Valuta valuta = rbEur.isChecked() ? Valuta.EUR : Valuta.RON;
            Food food = new Food(nume, livrator, pret, cantitate, adresa, dataLivrare, produse, valuta);
            //Log.i("food", food.toString());
            Intent intent = getIntent();

            if (isEditing) {
                intent.putExtra("edit", food);
                isEditing = false;

            } else {
                intent.putExtra("foodForIntent", food);
            }

            setResult(RESULT_OK, intent);
            finish();
        });
    }
}