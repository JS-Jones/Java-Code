/* Name: Joshau Jones
 * Email: joshua.jones@stonybrook.edu
 * ID: 114195108
 * Recitation: 01
 */

public class SongRecord {
    private String title;
    private String artist;
    private int minutes;
    private int seconds;

    // Constructors
    public SongRecord() {
    }

    public SongRecord(String title, String artist, int minutes, int seconds) {
        this.title = title;
        this.artist = artist;
        this.setMinutes(minutes);
        this.setSeconds(seconds);
    }

    // Accessor
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        // Checks if minutes is negative
        if (minutes < 0) {
            throw new IllegalArgumentException("Invalid song length.");
        }
        this.minutes = minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        // Checks if seconds is below 0 or above 59
        if (seconds < 0 || seconds > 59) {
            throw new IllegalArgumentException("Invalid song length.");
        }
        this.seconds = seconds;
    }

    @Override
    public String toString() {
        return String.format("%-21s %-21s %d:%02d", title, artist, minutes, seconds);
    }
    
}
