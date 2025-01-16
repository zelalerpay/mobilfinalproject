package msku.ceng.madlab.uniguide.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import msku.ceng.madlab.uniguide.R;
import msku.ceng.madlab.uniguide.model.Venue;
import msku.ceng.madlab.uniguide.util.FakeDataLoader;

public class VenueListActivity extends AppCompatActivity {

    private ListView listView;
    private ImageView favoritesButton;
    private String category;
    private List<Venue> venueList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue_list);

        // ListView ve Favorites Button'u bul
        listView = findViewById(R.id.listViewVenues);
        favoritesButton = findViewById(R.id.favoritesButton);

        // Favorites Button tıklanınca favoriler ekranına git
        favoritesButton.setOnClickListener(view -> {
            Intent intent = new Intent(VenueListActivity.this, FavoritesActivity.class);
            startActivity(intent);
        });

        // Intent ile gelen kategoriyi al
        category = getIntent().getStringExtra("category");
        setTitle("Category: " + category);

        // Arka planda verileri yüklüyoruz
        new LoadVenuesTask().execute(category);

        // ListView item tıklama eventi
        listView.setOnItemClickListener((parent, view, position, id) -> {
            if (venueList != null && !venueList.isEmpty()) {
                Venue selectedVenue = venueList.get(position);

                // VenueDetailActivity'e geçiş
                Intent intent = new Intent(VenueListActivity.this, VenueDetailActivity.class);
                intent.putExtra("venue_name", selectedVenue.getName());
                intent.putExtra("venue_image", selectedVenue.getImageUrl());
                intent.putExtra("venue_rating", selectedVenue.getRating());
                intent.putExtra("venue_comment", selectedVenue.getComment());
                intent.putExtra("venue_location", selectedVenue.getLocation());

                startActivity(intent);
            }
        });
    }

    /**
     * Basit bir AsyncTask örneği ile background thread kullanıyoruz.
     * Arka planda verilerimizi FakeDataLoader'dan çekiyoruz.
     */
    private class LoadVenuesTask extends AsyncTask<String, Void, List<Venue>> {
        @Override
        protected List<Venue> doInBackground(String... categories) {
            String cat = categories[0];
            // Fake veriler döndüren bir helper sınıf
            return FakeDataLoader.getVenuesByCategory(cat);
        }

        @Override
        protected void onPostExecute(List<Venue> venues) {
            super.onPostExecute(venues);
            venueList = venues;

            if (venueList != null && !venueList.isEmpty()) {
                // Custom adapter ile listede göster
                VenueListAdapter adapter = new VenueListAdapter(VenueListActivity.this, venueList);
                listView.setAdapter(adapter);
            } else {
                Toast.makeText(VenueListActivity.this,
                        "No venues found for " + category, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
