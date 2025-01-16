package msku.ceng.madlab.uniguide.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * Room veritabanı için singleton sınıf.
 * Versiyon ve tablo(entity) tanımları burada yapılır.
 */
@Database(entities = {FavoriteVenue.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;

    public abstract FavoriteVenueDao favoriteVenueDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "FakeDataLoader")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}

