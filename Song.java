public class Song {
    private String title;
    private double duration;
    private String artist;

    public Song(String title, double duration, String artist) {
        this.title = !title.equals(null)? title.toUpperCase():"unknown";
        this.duration = (duration > 0 && duration <= 10.00d)?duration : 0.0d;
        this.artist = !artist.equals(null)? artist.toUpperCase():"unknown";
    }

    public String getTitle() {
        return title;
    }

    public double getDuration() {
        return duration;
    }

    public String getArtist() {
        return artist;
    }
}
