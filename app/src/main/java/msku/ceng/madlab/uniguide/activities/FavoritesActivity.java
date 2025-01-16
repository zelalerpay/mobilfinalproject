package msku.ceng.madlab.uniguide.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import msku.ceng.madlab.uniguide.QrCodeFragment;
import msku.ceng.madlab.uniguide.R;
import msku.ceng.madlab.uniguide.data.AppDatabase;
import msku.ceng.madlab.uniguide.data.FavoriteVenueDao;

public class FavoritesActivity extends AppCompatActivity {

    private ListView listViewFavs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        listViewFavs = findViewById(R.id.listViewFavs);
        FavoriteVenueDao dao = AppDatabase.getInstance(getApplicationContext()).favoriteVenueDao();

        // LiveData kullanımı
        dao.getAllFavorites().observe(this, favList -> {
            if (favList == null) {
                favList = new ArrayList<>();
            }
            FavoritesAdapter adapter = new FavoritesAdapter(this, favList);
            listViewFavs.setAdapter(adapter);
        });


    }


}
