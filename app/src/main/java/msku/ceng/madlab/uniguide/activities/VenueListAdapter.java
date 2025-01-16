package msku.ceng.madlab.uniguide.activities;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

import msku.ceng.madlab.uniguide.R;
import msku.ceng.madlab.uniguide.model.Venue;

public class VenueListAdapter extends BaseAdapter {

    private Context context;
    private List<Venue> venueList;

    public VenueListAdapter(Context context, List<Venue> venueList) {
        this.context = context;
        this.venueList = venueList;
    }

    @Override
    public int getCount() {
        return venueList.size();
    }

    @Override
    public Object getItem(int position) {
        return venueList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    // Her bir satır (ListView item) için oluşturulan View
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null) {
            // venue_list_item.xml'i inflate ediyoruz
            convertView = LayoutInflater.from(context).inflate(R.layout.item_venue_list, parent, false);

            holder = new ViewHolder();
            holder.imageViewVenue = convertView.findViewById(R.id.imageViewVenue);
            holder.textViewVenueName = convertView.findViewById(R.id.textViewVenueName);
            holder.textViewVenueComment = convertView.findViewById(R.id.textViewVenueComment);
            holder.textViewVenueRating = convertView.findViewById(R.id.textViewVenueRating);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // Mevcut position'daki venue'yu alalım
        Venue venue = venueList.get(position);

        // Görselleri ve metinleri bağlayalım
        holder.textViewVenueName.setText(venue.getName());
        holder.textViewVenueComment.setText(venue.getComment());
        holder.textViewVenueRating.setText("Rating: " + venue.getRating());

        // Eğer imageUrl varsa (örneğin boş değilse), bir image loading kütüphanesiyle yükleyebilirsiniz
        // Şimdilik default ic_launcher ya da placeholder kullanabiliriz
        /*
        if (!venue.getImageUrl().isEmpty()) {
            // Örnek Picasso:
            // Picasso.get().load(venue.getImageUrl()).into(holder.imageViewVenue);
        } else {
            holder.imageViewVenue.setImageResource(R.mipmap.ic_launcher);
        }
        */

        return convertView;
    }

    // ViewHolder pattern: Performansı artırmak için
    static class ViewHolder {
        ImageView imageViewVenue;
        TextView textViewVenueName;
        TextView textViewVenueComment;
        TextView textViewVenueRating;
    }
}