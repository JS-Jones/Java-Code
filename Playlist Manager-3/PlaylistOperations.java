/* Name: Joshau Jones
 * Email: joshua.jones@stonybrook.edu
 * ID: 114195108
 * Recitation: 01
 */

import java.util.Scanner;

public class PlaylistOperations {
    private static Playlist playlist;
    private static Scanner scanner;

    public static void main(String[] args) {
        playlist = new Playlist();
        scanner = new Scanner(System.in);
        
        // Repeats until Q is entered
        while (true) {
            System.out.println("A: Add Song");
            System.out.println("B: Print Songs By Artist");
            System.out.println("G: Get Song");
            System.out.println("R: Remove Song");
            System.out.println("P: Print All Songs");
            System.out.println("S: Size");
            System.out.println("Q: Quit");
            System.out.println();
            System.out.print("Enter your choice: ");

            // Ignores letter case when scanning selection
            String input = scanner.nextLine().toUpperCase();
            System.out.println();

            // Could use Case/Switch, but will use If/Else if Statements to do the same
            if (input.equals("A")){
                // Option to Add Song
                try{
                    System.out.print("Enter the song title: ");
                    String title = scanner.nextLine();

                    System.out.print("Enter the song artist: ");
                    String artist = scanner.nextLine();

                    System.out.print("Enter the song length (minutes): ");
                    int minutes = Integer.parseInt(scanner.nextLine());

                    System.out.print("Enter the song length (seconds): ");
                    int seconds = Integer.parseInt(scanner.nextLine());

                    System.out.print("Enter the position: ");
                    int position = Integer.parseInt(scanner.nextLine());

                    SongRecord song = new SongRecord(title, artist, minutes, seconds);
                    playlist.addSong(song, position);

                    System.out.println("Song Added: " + title + " By " + artist);
                    System.out.println();
                
                } catch (IllegalArgumentException e) {
                    // Checks for either song lenght or invalid postion error
                    if (e.getMessage().equals("Invalid song length.")){
                        System.out.println(e.getMessage());
                    } else{
                        System.out.println("Invalid position for adding the new song.");
                    }
                    System.out.println();

                } catch (FullPlaylistException e) {
                    // Full Playlist error
                    System.out.println(e.getMessage());
                    System.out.println();
                } 

            } else if(input.equals("B")){
                // Option to print songs by a specific artist
                System.out.print("Enter the artist: ");
                String artist = scanner.nextLine();
                System.out.println();
                // Creates a new playlist and then prints it out
                Playlist newList = Playlist.getSongsByArtist(playlist, artist);
                newList.printAllSongs();
                System.out.println();

            } else if(input.equals("G")){
                // Option to Get Song at a specific position
                System.out.print("Enter the position: ");
                int position = Integer.parseInt(scanner.nextLine());
                System.out.println();
                try{
                    SongRecord song = playlist.getSong(position);
                    System.out.println("Song#   Title                 Artist                Length");
                    System.out.println("----------------------------------------------------------");
                    System.out.printf("%-7d %-21s %-21s %d:%02d%n", (position), song.getTitle(), song.getArtist(), song.getMinutes(), song.getSeconds());
                    System.out.println();
                } catch (IllegalArgumentException e){
                    // Catches position errors
                    System.out.println("No song found at position " + position + ".");
                    System.out.println();
                }

            } else if(input.equals("R")){
                // Option to Remove Song at a specific position
                System.out.print("Enter the position: ");
                int position = Integer.parseInt(scanner.nextLine());
                try{
                    playlist.removeSong(position);
                    System.out.println("Song Removed at position " + position);
                    System.out.println();
                } catch (IllegalArgumentException e){
                    System.out.println("No song at position " + position + " to remove.");
                    System.out.println();
                }
            
            } else if(input.equals("P")){
                // Option to Print all songs
                playlist.printAllSongs();

            } else if(input.equals("S")){
                // Option to print size of playlist
                System.out.println("There are " + playlist.size() + " song(s) in the current playlist.");
                System.out.println();

            } else if(input.equals("Q")){
                // Option to quit program
                System.out.println("Program terminating normally...");
                break;

            } else{
                // Default Case
                System.out.println("Invalid input, try again.");
                System.out.println();
            }
        }
       scanner.close(); 
    }

}
