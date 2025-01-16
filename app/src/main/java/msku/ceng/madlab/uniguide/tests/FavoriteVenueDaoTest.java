package msku.ceng.madlab.uniguide.tests;

import static org.junit.Assert.assertEquals;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import msku.ceng.madlab.uniguide.data.AppDatabase;
import msku.ceng.madlab.uniguide.data.FavoriteVenue;
import msku.ceng.madlab.uniguide.data.FavoriteVenueDao;

/**
 * Basit DAO test örneği
 */
public class FavoriteVenueDaoTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private AppDatabase db;
    private FavoriteVenueDao dao;

    @Before
    public void createDb() {
        db = Room.inMemoryDatabaseBuilder(
                ApplicationProvider.getApplicationContext(),
                AppDatabase.class
        ).allowMainThreadQueries().build();
        dao = db.favoriteVenueDao();
    }

    @After
    public void closeDb() {
        db.close();
    }

    @Test
    public void insertAndGetFavorites() throws Exception {
        FavoriteVenue fv = new FavoriteVenue("Test Cafe", "", 4.0f, " ");
        dao.insertFavorite(fv);

        // Test ortamında LiveData'nın değerini almak için
        List<FavoriteVenue> list = LiveDataTestUtil.getOrAwaitValue(dao.getAllFavorites());
        assertEquals(1, list.size());
        assertEquals("Test Cafe", list.get(0).getVenueName());
    }
}
