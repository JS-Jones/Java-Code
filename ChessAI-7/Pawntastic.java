//import java.util.ArrayList;
//import java.util.List;
import java.util.Scanner;

public class Pawntastic {
    public static void main(String[] args) {
        // Inputs
        Scanner scanner = new Scanner(System.in);
        System.out.println("Pawntastic by Joshua Jones and Kevin Yang\n");

        System.out.println("Choose your game:\n4. Tiny 4x4 Pawntastic\n5. Very small 5x5 Pawntastic\n6. Small 6x6 Pawntastic\n8. Standard 8x8 Pawntastic\n10. Jumbo 10x10 Pawntastic");
        System.out.print("Or enter any size >=4 to play that size game: ");
        int boardSize = Integer.parseInt(scanner.nextLine());
        
        System.out.println("");

        System.out.println("Choose your opponent:\n1. An agent that plays randomly\n2. An agent that uses MINIMAX\n3.An agent that uses H-MINIMAX with a fixed depth cutoff and alpha-beta pruning");
        System.out.print("Your Choice: ");
        System.out.println("");

        int opponentChoice = Integer.parseInt(scanner.nextLine());
        
        System.out.print("Trace opponent agent? (y/n): ");
        String traceChoice = scanner.nextLine();

        System.out.print("Choose your color (white or black): ");
        String playerColor = scanner.nextLine().toLowerCase();

        // Creates 2 players and then sets the player to one and the ai to the other
        Player whitePlayer = new Player("white", false);
        Player blackPlayer = new Player("black", false);

        Player userplayer;
        Player aiPlayer;

        if (playerColor.equals("white")){
            whitePlayer.setHuman(true);
            userplayer = whitePlayer;
            aiPlayer = blackPlayer;
        } else {
            blackPlayer.setHuman(true);
            userplayer = blackPlayer;
            aiPlayer = whitePlayer;
        }

        // Create the inital state of the game
        State initialGameState = new State(userplayer, boardSize, opponentChoice);

        // Create a game and run the playGame(game loop)
        Game game = new Game(userplayer, aiPlayer, initialGameState, traceChoice);
        game.playGame(scanner, boardSize, opponentChoice);

        System.out.println("Type anything/enter to quit");
        if(scanner.nextLine().equals("quit")){
            //break;
        }

        //scanner.close();
    }
}
