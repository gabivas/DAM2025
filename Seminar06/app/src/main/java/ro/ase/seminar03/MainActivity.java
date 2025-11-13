package ro.ase.seminar03;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private

    FloatingActionButton fabOpen, fabOpen2;
    //ListView lvFoodList;
    List<Food> foodList = new ArrayList<>();
    ActivityResultLauncher<Intent> launcher;
    ActivityResultLauncher<Intent> launcher2;
    TextView tvInfo;
    RecyclerView rvFood;
    private int pozitieFoodInLista;

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
        rvFood = findViewById(R.id.rvFood);
        rvFood.setLayoutManager(new LinearLayoutManager(this));
        //lvFoodList = findViewById(R.id.lvFood);
        tvInfo = findViewById(R.id.tvInfo);

        tvInfo.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), NameActivity.class);
            intent.putExtra("editInfo", tvInfo.getText().toString());
            launcher2.launch(intent);
        });

//        lvFoodList.setOnItemClickListener((adapterView, view, position, l) -> {
//            pozitieFoodInLista = position;
//            Intent intent = new Intent(getApplicationContext(), AddFoodDeliveryActivity.class);
//            intent.putExtra("edit", foodList.get(position));
//            launcher.launch(intent);
//        });

        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getData().hasExtra("foodForIntent")) {
                Intent intent = result.getData();
                if (intent != null) {
                    Food food = intent.getSerializableExtra("foodForIntent", Food.class);
                    if (food != null) {
                        Log.i("food", food.toString());
                        foodList.add(food);
                    }
                    FoodAdapter adapter = new FoodAdapter(foodList, (item, pos) -> {
                        pozitieFoodInLista = pos;
                        Intent intent2 = new Intent(getApplicationContext(), AddFoodDeliveryActivity.class);
                        intent2.putExtra("edit", foodList.get(pos));
                        launcher.launch(intent2);
                    });
                    rvFood.setAdapter(adapter);
                    //ArrayAdapter<Food> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, foodList);
                    //lvFoodList.setAdapter(adapter);
                }
            } else if (result.getData().hasExtra("edit")) {
                Intent intent = result.getData();
                Food food = intent.getSerializableExtra("edit", Food.class);
                if (food != null) {
                    Food editedFood = foodList.get(pozitieFoodInLista);
                    editedFood.setNume(food.getNume());
                    editedFood.setAdresa(food.getAdresa());
                    editedFood.setCantitate(food.getCantitate());
                    editedFood.setPret(food.getPret());
                    editedFood.setDataLivrare(food.getDataLivrare());
                    editedFood.setProduse(food.getProduse());
                    editedFood.setValuta(food.getValuta());

                    //ArrayAdapter<Food> adapter = (ArrayAdapter<Food>) lvFoodList.getAdapter();
                    FoodAdapter adapter = (FoodAdapter) rvFood.getAdapter();
                    adapter.notifyDataSetChanged();
                }
            }
        });

        launcher2 = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), res -> {
            if (res.getData().hasExtra("info")) {
                Intent intent = res.getData();
                if (intent != null) {
                    String info = intent.getStringExtra("info");
                    if (info != null) {
                        tvInfo.setText(info);
                    }
                }
            } else if (res.getData().hasExtra("editInfo")) {
                tvInfo.setText(res.getData().getStringExtra("editInfo"));
            }
        });

        fabOpen = findViewById(R.id.fabOpen);
        fabOpen.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), AddFoodDeliveryActivity.class);
            //startActivity(intent);
            launcher.launch(intent);

        });

        fabOpen2 = findViewById(R.id.fabOpen2);
        fabOpen2.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), NameActivity.class);
            //startActivity(intent);
            launcher2.launch(intent);

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.meniu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.idOptiune1) {
            Toast.makeText(this, "OPTIUNE 1", Toast.LENGTH_SHORT).show();
        }
        if (item.getItemId() == R.id.idOptiune2) {
            Intent intent = new Intent(getApplicationContext(), PreiaDateActivity.class);
            startActivity(intent);
        }
        if (item.getItemId() == R.id.idOptiune3) {
            Intent intent = new Intent(getApplicationContext(), NameActivity.class);
            launcher2.launch(intent);
        }
        return true;
    }
}