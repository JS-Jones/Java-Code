/* Name: Joshau Jones
 * Email: joshua.jones@stonybrook.edu
 * ID: 114195108
 * Recitation: 01
 */

public class Playlist {
    // Maximum allowed songs equal 50
    private final int MAX_SONGS = 50;
    private SongRecord[] playlist;

    // Used to track total songs in a playlist
    private int songCount;

    // Constructor
    public Playlist() {
        playlist = new SongRecord[MAX_SONGS];
        songCount = 0;
    }

    // Clones the playlist
    public Object clone() {
        Playlist copyPlaylist = new Playlist();
        for (int i = 0; i < songCount; i++) {
            copyPlaylist.addSong(this.playlist[i], i + 1);
        }
        return copyPlaylist;
    }

    // Checks if the playlist are the same
    public boolean equals(Object obj) {
        // Preconditions
        if (obj == null || !(obj instanceof Playlist)) {
            return false;
        }
        Playlist otherPlaylist = (Playlist) obj;
        // Check if the number of songs in both playlists is the same
        if (this.songCount != otherPlaylist.songCount) {
            return false;
        }
        // Check if each song in both playlists is the same, using an equals method instead of checking each individual property
        for (int i = 0; i < this.songCount; i++) {
            if (!this.playlist[i].equals(otherPlaylist.playlist[i])) {
                return false;
            }

        }
        return true;
    }

    // Returns playlist size
    public int size() {
        return songCount;
    }

    // Adds a song to the playlist
    public void addSong(SongRecord song, int position) throws IllegalArgumentException {
        // (Position must be between 1 and songCount)
        if (position > songCount + 1 || position <= 0) {
            throw new IllegalArgumentException("Invalid position for adding the new song."); 
        }
        if (songCount >= MAX_SONGS) {
            throw new FullPlaylistException("Playlist is full. Cannot add more songs.");
        }
        // Shift the other songs in the playlist by working backwards
        for (int i = songCount - 1; i >= position - 1; i--) {
            playlist[i + 1] = playlist[i];
        }
        // Adds the song
        playlist[position - 1] = song;
        songCount++;
    }

    // Removes a song from the playlist at a specific position
    public void removeSong(int position) throws IllegalArgumentException {
        if (position > songCount || position <= 0) {
            throw new IllegalArgumentException("Invalid position for adding the new song.");
        }
        // Shift other songs
        for (int i = position - 1; i < songCount - 1; i++) {
            playlist[i] = playlist[i + 1];
        }
        songCount--;
    }

    // Returns the song at a specific postion
    public SongRecord getSong(int position) throws IllegalArgumentException {
        if (position <= 0 || position > songCount) {
            throw new IllegalArgumentException("Invalid position for adding the new song.");
        }
        // Note, index = postion -1
        return playlist[position - 1];
    }
    // Prints a table with each song 
    public void printAllSongs() {
        System.out.println("Song#   Title                 Artist                Length");
        System.out.println("----------------------------------------------------------");
        for (int i = 0; i < songCount; i++) {
            SongRecord song = playlist[i];
            System.out.printf("%-7d %-21s %-21s %d:%02d%n", (i + 1), song.getTitle(), song.getArtist(), song.getMinutes(), song.getSeconds());
        }
        System.out.println();
    }

    // Create a new playlist to only track songs from a specific artist
    public static Playlist getSongsByArtist(Playlist originalList, String artist) {
        if (originalList == null || artist == null) {
            return null;
        }
        Playlist newPlaylist = new Playlist();
        for (int i = 0; i < originalList.size(); i++) {
            SongRecord song = originalList.getSong(i + 1);
            // Checks if the artist is the same as the parameter and then adds it to the newPlaylist
            if (song.getArtist().equals(artist)) {
                newPlaylist.addSong(song, newPlaylist.size() + 1);
            }
        }
        return newPlaylist;
    }

    // Not to sure how to return multiple lines, so using string builder in this case for the playlist
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < songCount; i++) {
            sb.append(String.format("%-21s%s%n", (i + 1) + ":", playlist[i]));
        }
        return sb.toString();
    }
}
