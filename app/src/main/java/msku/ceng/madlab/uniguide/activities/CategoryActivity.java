package msku.ceng.madlab.uniguide.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import msku.ceng.madlab.uniguide.R;

public class CategoryActivity extends AppCompatActivity {

    private Button btnWineDate, btnBoardGames, btnNightLife, btnCoffee, btnChillNight;
    private FloatingActionButton fabLogout;
    private ImageView favoritesButton;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        // SharedPreferences başlat
        sharedPreferences = getSharedPreferences("UserPref", Context.MODE_PRIVATE);

        // Butonları bul
        btnWineDate = findViewById(R.id.btnWineDate);
        btnBoardGames = findViewById(R.id.btnBoardGames);
        btnNightLife = findViewById(R.id.btnNightLife);
        btnCoffee = findViewById(R.id.btnCoffee);
        btnChillNight = findViewById(R.id.btnChillNight);
        fabLogout = findViewById(R.id.fabLogout);
        favoritesButton = findViewById(R.id.favoritesButton);

        // Logout butonuna tıklama işlemi
        fabLogout.setOnClickListener(view -> {
            // SharedPreferences'taki giriş durumunu sıfırla
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("isLoggedIn", false);
            editor.apply();

            // MainActivity'ye yönlendir
            Intent intent = new Intent(CategoryActivity.this, MainActivity.class);
            startActivity(intent);
            finish(); // CategoryActivity'yi kapat
        });

        // Favorites Button tıklanınca favoriler ekranına git
        favoritesButton.setOnClickListener(view -> {
            Intent intent = new Intent(CategoryActivity.this, FavoritesActivity.class);
            startActivity(intent);
        });

        // Wine Date tıklama
        btnWineDate.setOnClickListener(view -> {
            Intent intent = new Intent(CategoryActivity.this, VenueListActivity.class);
            intent.putExtra("category", "Wine Date");
            startActivity(intent);
        });

        // Board Games tıklama
        btnBoardGames.setOnClickListener(view -> {
            Intent intent = new Intent(CategoryActivity.this, VenueListActivity.class);
            intent.putExtra("category", "Board Games");
            startActivity(intent);
        });
    }
}
