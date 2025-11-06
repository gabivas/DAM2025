package ro.ase.seminar03;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class NameActivity extends AppCompatActivity {
    EditText etInfo;
    Button btnSave;
    boolean isEditing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_name);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        etInfo = findViewById(R.id.etInfo);
        btnSave = findViewById(R.id.btnSave);

        Intent editIntent = getIntent();
        if (editIntent.hasExtra("editInfo")) {
            isEditing = true;
            String info = editIntent.getStringExtra("editInfo");
            etInfo.setText(info);
        }

        btnSave.setOnClickListener(view -> {
            String info = etInfo.getText().toString();
            Intent intent = getIntent();
            if (isEditing) {
                intent.putExtra("editInfo", info);
            } else {
                intent.putExtra("info", info);

            }

            setResult(RESULT_OK, intent);
            finish();
        });
    }
}