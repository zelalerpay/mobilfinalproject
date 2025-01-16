package msku.ceng.madlab.uniguide.util;

import java.util.ArrayList;
import java.util.List;
import msku.ceng.madlab.uniguide.model.Venue;

/**
 * Bu sınıf, sahte (dummy) verileri yüklemek için örnek olarak kullanılır.
 */
public class FakeDataLoader {

    // Muğla ilçeleri için basit bir dizi
    private static final String[] MUGLA_LOCATIONS = {
            "Muğla - Bodrum",
            "Muğla - Milas",
            "Muğla - Fethiye",
            "Muğla - Marmaris",
            "Muğla - Datça",
            "Muğla - Ortaca",
            "Muğla - Ula"
    };

    // Daha çeşitli yorumlar için örnek bir dizi
    private static final String[] SAMPLE_COMMENTS = {
            "Harika bir mekan, mutlaka gidin!",
            "Çok sevdim, ortam sıcak ve samimi.",
            "Fiyatlar biraz pahalı ama değiyor.",
            "Servis mükemmel, personel güleryüzlü.",
            "Müzikler biraz gürültülüydü, ama yine de keyifliydi.",
            "Arkadaşlarla güzel vakit geçirdik, tavsiye ederim.",
            "Menüdeki tatlılar efsane, mutlaka deneyin!",
            "Otopark sıkıntısı var, erken gitmekte fayda var."
    };

    /**
     * Verilen kategoriye göre sahte Venue listesi döndürür.
     */
    public static List<Venue> getVenuesByCategory(String category) {
        List<Venue> venues = new ArrayList<>();

        if (category.equalsIgnoreCase("Wine Date")) {
            // Wine Date için 5 parametreli constructor (name, rating, image, comment, location)
            venues.add(new Venue("Mabolla Wine House", 4.5f,
                    "https://example.com/mabolla.jpg",
                    getRandomComment(),
                    getRandomMuglaLocation()));

            venues.add(new Venue("Vino Lounge", 4.0f,
                    "https://example.com/vino.jpg",
                    getRandomComment(),
                    getRandomMuglaLocation()));

            venues.add(new Venue("Kukla Pub", 4.3f,
                    "https://example.com/kukla.jpg",
                    getRandomComment(),
                    getRandomMuglaLocation()));

            venues.add(new Venue("Gufo Gastro", 4.7f,
                    "https://example.com/gufo.jpg",
                    getRandomComment(),
                    getRandomMuglaLocation()));

            venues.add(new Venue("Aura", 4.6f,
                    "https://example.com/aura.jpg",
                    getRandomComment(),
                    getRandomMuglaLocation()));

        } else if (category.equalsIgnoreCase("Board Games")) {
            venues.add(new Venue("Monopoly Cafe", 4.2f,
                    "https://example.com/monopoly.jpg",
                    getRandomComment(),
                    getRandomMuglaLocation()));

            venues.add(new Venue("Gameline", 4.8f,
                    "https://example.com/gameline.jpg",
                    getRandomComment(),
                    getRandomMuglaLocation()));

            venues.add(new Venue("Saygın", 4.4f,
                    "https://example.com/saygin.jpg",
                    getRandomComment(),
                    getRandomMuglaLocation()));

            venues.add(new Venue("Ranj", 4.6f,
                    "https://example.com/ranj.jpg",
                    getRandomComment(),
                    getRandomMuglaLocation()));

            venues.add(new Venue("Baykuş", 4.3f,
                    "https://example.com/baykus.jpg",
                    getRandomComment(),
                    getRandomMuglaLocation()));

            venues.add(new Venue("Time", 4.5f,
                    "https://example.com/time.jpg",
                    getRandomComment(),
                    getRandomMuglaLocation()));

            venues.add(new Venue("Konum", 4.7f,
                    "https://example.com/konum.jpg",
                    getRandomComment(),
                    getRandomMuglaLocation()));

        } else {
            // Diğer kategoriler için basit sahte veriler (5 adet)
            for (int i = 1; i <= 5; i++) {
                venues.add(new Venue(
                        category + " Venue " + i,
                        3.5f + (i % 5),       // random rating
                        "",
                        getRandomComment(),
                        getRandomMuglaLocation()
                ));
            }
        }

        return venues;
    }

    // Rastgele bir yorum seçer
    private static String getRandomComment() {
        int index = (int)(Math.random() * SAMPLE_COMMENTS.length);
        return SAMPLE_COMMENTS[index];
    }

    // Rastgele bir Muğla ilçesi seçer
    private static String getRandomMuglaLocation() {
        int index = (int)(Math.random() * MUGLA_LOCATIONS.length);
        return MUGLA_LOCATIONS[index];
    }
}
