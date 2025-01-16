package msku.ceng.madlab.uniguide.data;


import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import msku.ceng.madlab.uniguide.model.Venue;
import msku.ceng.madlab.uniguide.util.FakeDataLoader;

public class VenueRepository {

    private final FavoriteVenueDao favoriteVenueDao;
    private final ExecutorService executorService;

    public VenueRepository(Context context) {
        AppDatabase database = AppDatabase.getInstance(context);
        favoriteVenueDao = database.favoriteVenueDao();
        executorService = Executors.newSingleThreadExecutor();
    }

    /**
     * Yerel veritabanından favori mekanları alır.
     */
    public LiveData<List<FavoriteVenue>> getAllFavorites() {
        return favoriteVenueDao.getAllFavorites();
    }

    /**
     * Yeni bir favori mekan ekler.
     */
    public void insertFavorite(FavoriteVenue favoriteVenue) {
        executorService.execute(() -> favoriteVenueDao.insertFavorite(favoriteVenue));
    }

    /**
     * Favori mekanları siler.
     */
    public void deleteFavorite(FavoriteVenue favoriteVenue) {
        executorService.execute(() -> favoriteVenueDao.deleteFavorite(favoriteVenue));
    }

    /**
     * FakeDataLoader'dan mekanları kategoriye göre alır.
     */
    public List<Venue> getVenuesByCategory(String category) {
        return FakeDataLoader.getVenuesByCategory(category);
    }
}
