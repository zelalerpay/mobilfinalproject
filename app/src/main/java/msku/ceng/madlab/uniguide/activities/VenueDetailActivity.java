package msku.ceng.madlab.uniguide.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import msku.ceng.madlab.uniguide.R;
import msku.ceng.madlab.uniguide.data.AppDatabase;
import msku.ceng.madlab.uniguide.data.FavoriteVenue;
import msku.ceng.madlab.uniguide.data.FavoriteVenueDao;

public class VenueDetailActivity extends AppCompatActivity {

    private ImageView imgVenue, imgLike, imgDislike, favoritesButton;
    private TextView tvName, tvLocation, tvComments, tvRating;
    private String venueLocation;

    private String venueName, venueImgUrl, venueComment;
    private float venueRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue_detail);

        imgVenue = findViewById(R.id.imgVenue);
        imgLike = findViewById(R.id.imgLike);
        imgDislike = findViewById(R.id.imgDislike);
        favoritesButton = findViewById(R.id.favoritesButton);  // Favorites Button

        tvName = findViewById(R.id.tvVenueName);
        tvLocation = findViewById(R.id.tvVenueLocation);
        tvComments = findViewById(R.id.tvComments);
        tvRating = findViewById(R.id.tvVenueRating);

        // Intent ile gelen verileri al
        venueName = getIntent().getStringExtra("venue_name");
        venueImgUrl = getIntent().getStringExtra("venue_image");
        venueRating = getIntent().getFloatExtra("venue_rating", 4.0f);
        venueComment = getIntent().getStringExtra("venue_comment");
        venueLocation = getIntent().getStringExtra("venue_location");

        // Ekranda göster
        tvName.setText(venueName);
        tvRating.setText("Rating: " + venueRating);
        tvLocation.setText("Location: " + venueLocation);
        tvComments.setText("User Comments:\n" + venueComment);

        // Dislike tıklanınca bir önceki ekrana dön
        imgDislike.setOnClickListener(v -> finish());

        // Like tıklanınca favorilere ekle
        imgLike.setOnClickListener(view -> addToFavorites(venueName, venueImgUrl, venueRating, venueComment));

        // Favorites Button tıklanınca favoriler ekranına git
        favoritesButton.setOnClickListener(view -> {
            Intent intent = new Intent(VenueDetailActivity.this, FavoritesActivity.class);
            startActivity(intent);
        });
    }

    private void addToFavorites(String name, String imageUrl, float rating, String comment) {
        new Thread(() -> {
            FavoriteVenueDao dao = AppDatabase.getInstance(getApplicationContext()).favoriteVenueDao();
            int count = dao.countVenueByName(name);
            if (count == 0) {
                FavoriteVenue fv = new FavoriteVenue(name, imageUrl, rating, comment);
                dao.insertFavorite(fv);
            } else {
                runOnUiThread(() -> Toast.makeText(this, "This venue is already in your favorites.", Toast.LENGTH_SHORT).show());
            }
        }).start();
    }
}
