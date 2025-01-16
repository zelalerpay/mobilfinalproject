package msku.ceng.madlab.uniguide.model;

public class Venue {
    private String name;
    private float rating;
    private String imageUrl;
    private String comment;

    // YENİ ALAN
    private String location;

    // Eski 4 parametreli constructor kullanılabilir kalsın istersen
    public Venue(String name, float rating, String imageUrl, String comment) {
        this.name = name;
        this.rating = rating;
        this.imageUrl = imageUrl;
        this.comment = comment;
        this.location = "Unknown Location"; // default
    }

    // YENİ: 5 parametreli constructor (location da ekleyelim)
    public Venue(String name, float rating, String imageUrl, String comment, String location) {
        this.name = name;
        this.rating = rating;
        this.imageUrl = imageUrl;
        this.comment = comment;
        this.location = location;
    }

    // Getter/Setter
    public String getName() {
        return name;
    }

    public float getRating() {
        return rating;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getComment() {
        return comment;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
