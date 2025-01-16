package msku.ceng.madlab.uniguide.activities;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import msku.ceng.madlab.uniguide.QrCodeFragment;
import msku.ceng.madlab.uniguide.R;
import msku.ceng.madlab.uniguide.data.AppDatabase;
import msku.ceng.madlab.uniguide.data.FavoriteVenue;
import msku.ceng.madlab.uniguide.data.FavoriteVenueDao;

public class FavoritesAdapter extends BaseAdapter {

    private AppCompatActivity activity;
    private List<FavoriteVenue> favorites;

    public FavoritesAdapter(AppCompatActivity activity, List<FavoriteVenue> favorites) {
        this.activity = activity;
        this.favorites = favorites;
    }

    @Override
    public int getCount() {
        return favorites.size();
    }

    @Override
    public Object getItem(int position) {
        return favorites.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = activity.getLayoutInflater();
            convertView = inflater.inflate(R.layout.item_favorite_list, parent, false);
        }

        TextView tvName = convertView.findViewById(R.id.tvNameFav);
        RatingBar rbFav = convertView.findViewById(R.id.ratingBarFav);
        ImageView imgSmile = convertView.findViewById(R.id.imgSmile);
        ImageView imgSad = convertView.findViewById(R.id.imgSad);

        FavoriteVenue fv = favorites.get(position);

        tvName.setText(fv.getVenueName());
        rbFav.setRating(fv.getVenueRating());

        // Gülücük ikonuna tıklandığında QR Fragment açalım
        imgSmile.setOnClickListener(v -> {
            activity.getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, QrCodeFragment.newInstance())
                    .addToBackStack(null)
                    .commit();
        });
        // YENİ: Üzgün surat ikonuna tıklanınca favorilerden sil
        imgSad.setOnClickListener(v -> {
            // Thread içinde silme işlemi yapalım
            new Thread(() -> {
                // DAO'yu al
                FavoriteVenueDao dao = AppDatabase.getInstance(activity.getApplicationContext()).favoriteVenueDao();
                // Favori kaydı sil
                dao.deleteFavorite(fv);
            }).start();
        });

        return convertView;
    }
}
