package msku.ceng.madlab.uniguide.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

/**
 * Favori mekanlar için DAO (Data Access Object) arayüzü.
 * Veritabanı işlemlerini tanımlar.
 */
@Dao
public interface FavoriteVenueDao {

    @Insert
    void insertFavorite(FavoriteVenue favoriteVenue);

    @Delete
    void deleteFavorite(FavoriteVenue favoriteVenue);

    @Query("SELECT * FROM FavoriteVenue")
    LiveData<List<FavoriteVenue>> getAllFavorites();

    // Varsayılan bir ekleme metodu
    default void insertFavoriteVenue(FavoriteVenue favoriteVenue) {
        insertFavorite(favoriteVenue);
    }

    // <-- Şu metodu ekliyoruz:
    @Query("SELECT COUNT(*) FROM FavoriteVenue WHERE venueName = :name")
    int countVenueByName(String name);
}
