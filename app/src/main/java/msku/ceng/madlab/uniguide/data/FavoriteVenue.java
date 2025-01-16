package msku.ceng.madlab.uniguide.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Favori mekan tablosu için Room Entity sınıfı.
 */
@Entity(tableName = "FavoriteVenue")
public class FavoriteVenue {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String venueName;
    private String imageUrl;
    private float venueRating;
    private String comment;

    // Room'un ihtiyaç duyduğu varsayılan constructor
    public FavoriteVenue() {
    }

    // Uygulamadan yeni favori oluşturmak için pratik constructor
    public FavoriteVenue(String venueName, String imageUrl, float venueRating, String comment) {
        this.venueName = venueName;
        this.imageUrl = imageUrl;
        this.venueRating = venueRating;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public float getVenueRating() {
        return venueRating;
    }

    public void setVenueRating(float venueRating) {
        this.venueRating = venueRating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
