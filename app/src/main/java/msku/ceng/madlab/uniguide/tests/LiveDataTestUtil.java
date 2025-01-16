package msku.ceng.madlab.uniguide.tests;

import androidx.lifecycle.LiveData;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class LiveDataTestUtil {
    public static <T> T getOrAwaitValue(final LiveData<T> liveData) throws InterruptedException, TimeoutException {
        final Object[] data = new Object[1];
        final CountDownLatch latch = new CountDownLatch(1);
        liveData.observeForever(o -> {
            data[0] = o;
            latch.countDown();
        });
        if (!latch.await(2, TimeUnit.SECONDS)) {
            throw new TimeoutException("LiveData value was never set.");
        }
        return (T) data[0];
    }
}
