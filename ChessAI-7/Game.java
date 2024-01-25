import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.lang.Math;

public class Game {
    Player player;
    Player ai;
    State currentState;
    String trace;

    String currentPlayer = "white";
    int turnNum = 0;

    // Constructor for Game
    public Game(Player player, Player ai, State currentState, String traceChoice){
        this.player = player;
        this.ai = ai;
        this.currentState = currentState;
        this.trace = traceChoice;
    }

    // Checks if a move is possible in the game state board
    public static boolean check(int fromRow, int fromCol, int toRow, int toCol, State currentState){

       // ArrayList<Integer> functions = new ArrayList<Integer>(); ----> Return an array maybe??????? look at example
        int size = currentState.getBoardSize();

        // Checks if it fits the bounds
        if (toRow < 0 || toRow >= size || toCol < 0 || toCol >= size) {
            //System.out.println("Out of Bounds");
            return false;
        } 
        // Checks if you are actually moving
        if ((toRow - fromRow) == 0) {
            //System.out.println("not moving");
            return false;
        // Can't jump more than 2 spaces up or more than 1 to the side
        } else if (Math.abs(toRow - fromRow) > 2) {
            //System.out.println("Moving too much up");
            return false;
        } else if (Math.abs(toCol - fromCol) > 1){
            //System.out.println("Moving too much to the side");
            return false;
        } 

        // Checks for a pawn to play
        if (currentState.getBoard()[fromRow][fromCol] != null){
            Pawn pawn = currentState.getBoardAtPos(fromRow, fromCol); 

            if (pawn.getColor().equals("white")) {
                if ((toRow - fromRow) < 1) {
                    //System.out.println("can't move backwards");
                    return false;

                } else if ((toRow - fromRow) == 2) {
                    if (toCol- fromCol == 0){
                        if (fromRow == 1) {
                            if ((currentState.getBoardAtPos(toRow, toCol) != null) || (currentState.getBoardAtPos(toRow-1, toCol) != null)){
                                //System.out.println("pawn in that place");
                                return false;
                            } else {
                                return true;
                            }
                        } else {
                            //System.out.println("can't jump 2 places cause not on starting square");
                            return false;
                        }
                    } else{
                        //System.out.println("can't jump 2 places and shift column");
                        return false;
                    }
                // Checking for moving up one place now
                } else if ((toRow - fromRow) == 1) {
                    if (toCol- fromCol == 0){
                        if (currentState.getBoardAtPos(toRow, toCol) == null){
                            return true;
                        } else {
                            //System.out.println("pawn in that place");
                            return false;
                        }
                    } else if (Math.abs(toCol- fromCol) == 1) {
                        if (currentState.getBoardAtPos(toRow, toCol) != null){
                            if (currentState.getBoardAtPos(toRow, toCol).getColor().equals("white")){
                                //System.out.println("pawn in that place is the same color as you");
                                return false;
                            } else {
                                return true;
                            }
                        } else {
                            //System.out.println("no pawn to take in that place");
                            return false;
                        }
                    } else {
                        //System.out.println("Not a vaid one space up jump");
                        return false;
                    }
                } else {
                    //System.out.println("Moving too many spaces");
                    return false;
                }
            } else { // Cases for black pawns (basically reverese from and to since black plays backwards)
                if ((fromRow-toRow) < 1) {
                    //System.out.println("can't move backwards");
                    return false;

                } else if ((fromRow- toRow) == 2) {
                    if (fromCol - toCol == 0){
                        if (fromRow == (size - 2)) {
                            if ((currentState.getBoardAtPos(toRow, toCol) != null) || (currentState.getBoardAtPos(toRow+1, toCol) != null)){
                                //System.out.println("pawn in that place");
                                return false;
                            } else {
                                return true;
                            }
                        } else {
                            //System.out.println("can't jump 2 places cause not on starting square");
                            return false;
                        }
                    } else{
                        //System.out.println("can't jump 2 places and shift column");
                        return false;
                    }
                // Checking for moving up one place now
                } else if ((fromRow-toRow) == 1) {
                    if (fromCol-toCol == 0){
                        if (currentState.getBoardAtPos(toRow, toCol) == null){
                            return true;
                        } else {
                            //System.out.println("pawn in that place");
                            return false;
                        }
                    } else if ((Math.abs(toCol- fromCol) == 1)) {
                        if (currentState.getBoardAtPos(toRow, toCol) != null){
                            if (currentState.getBoardAtPos(toRow, toCol).getColor().equals("black")){
                                //System.out.println("pawn in that place is the same color as you");
                                return false;
                            } else {
                                return true;
                            }
                        } else {
                            //System.out.println("no pawn to take in that place");
                            return false;
                        }
                    } else {
                        //System.out.println("Not a vaid one space up jump");
                        return false;
                    }
                } else {
                    //System.out.println("Moving too many spaces");
                    return false;
                }
            }
        }
        //System.out.println("Case not considered");
        return false;
    }

    // Method to find all valid moves for a player's pawns
    private ArrayList<int[]> findAllValidMoves(State currentState, String currentPlayer) {
        ArrayList<int[]> validMoves = new ArrayList<>();
        int boardSize = currentState.getBoardSize();

        if (currentPlayer.equals("white")){
            int[][] directions = {
                {1, 0},     // Move one space straight up
                {1, -1},    // Move one space up to the left
                {1, 1},     // Move one space up to the right
                {2, 0}      // Move two spaces straight up (initial move)
            };
        
            for (int fromRow = 1; fromRow < boardSize-1; fromRow++) {
                for (int fromCol = 0; fromCol < boardSize; fromCol++) {
                    if (currentState.getBoardAtPos(fromRow, fromCol) != null &&
                        currentState.getBoardAtPos(fromRow, fromCol).getColor().equals(currentPlayer)) {
                        for (int[] dir : directions) {
                            int toRow = fromRow + dir[0];
                            int toCol = fromCol + dir[1];
        
                            if (check(fromRow, fromCol, toRow, toCol, currentState)) {
                                if (trace.equals("y")){
                                    System.out.println(fromRow + "" + fromCol + " "+toRow+ "" + toCol) ;
                                }
                                int[] move = { fromRow, fromCol, toRow, toCol };
                                validMoves.add(move);
                            }
                        }
                    }
                }
            }
        } else {
            int[][] directions = {
                {-1, 0},     // Move one space down up
                {-1, 1},    // Move one down up to the left
                {-1, -1},     // Move one down up to the right
                {-2, 0}      // Move two spaces down up (initial move)
            };
        
            for (int fromRow = 1; fromRow < boardSize-1; fromRow++) {
                for (int fromCol = 0; fromCol < boardSize; fromCol++) {
                    if (currentState.getBoardAtPos(fromRow, fromCol) != null &&
                        currentState.getBoardAtPos(fromRow, fromCol).getColor().equals(currentPlayer)) {
                        for (int[] dir : directions) {
                            int toRow = fromRow + dir[0];
                            int toCol = fromCol + dir[1];
        
                            if (check(fromRow, fromCol, toRow, toCol, currentState)) {
                                if(trace.equals("y")){
                                    System.out.println(fromRow + "" + fromCol + " "+toRow+ "" + toCol) ;
                                }
                                int[] move = { fromRow, fromCol, toRow, toCol };
                                validMoves.add(move);
                            }
                        }
                    }
                }
            }
        }
        

        return validMoves;
    }


    // checks and moves a pawn. Returns a boolean in moved
    public boolean move(int fromRow, int fromCol, int toRow, int toCol, String currentPlayer) {
        
        Boolean check = check(fromRow, fromCol, toRow, toCol, currentState);

        if (check){
            if (this.currentPlayer.equals(currentPlayer)){
                this.currentState.setBoardAtPos(fromRow,fromCol,toRow,toCol);
                return true;
            } else{
                return false;
            }
        }
        return false;
    }

    //general logic for the game, prompts for scanner input and choices from player
    public void playGame(Scanner scanner, int boardSize, int opponentChoice){
        int whiteEndRow = boardSize - 1;
        int blackEndRow = 0;
        int depth= 5;
        if (opponentChoice == 3) {
            System.out.print("Enter the depth you want to search (Reccomended 6-7): ");
            depth = Integer.parseInt(scanner.nextLine());
        }
    
        while (!terminalTest(currentState)){
            currentState.printBoard();
            System.out.println("It's " + currentPlayer + "'s turn.");
            if (player.getColor().equals(currentPlayer)){
                System.out.print("Enter the pawn location and move (e.g., c2 b3): ");
                String input = scanner.nextLine();

                String[] move = input.split(" ");
                if (move.length != 2) {
                    System.out.println("Invalid input!");
                    continue;
                }

                String[] from = move[0].split("");
                int fromCol = (int) from[0].charAt(0) - 97;
                int fromRow = Integer.parseInt(from[1]) - 1;

                String[] to = move[1].split("");
                int toCol = (int) to[0].charAt(0) - 97;
                int toRow = Integer.parseInt(to[1]) - 1;

                if (currentState.getBoardAtPos(fromRow, fromCol) == null) {
                    continue;
                }

                if (currentState.getBoardAtPos(fromRow, fromCol).getColor().equals(currentPlayer)) {
                    if (check(fromRow, fromCol, toRow, toCol, currentState)) {
                        move(fromRow, fromCol, toRow, toCol,currentPlayer);
                        if (currentPlayer.equals("white")) {
                            if (toRow == whiteEndRow) {
                                System.out.println("WHITE WINS");
                                currentState.printBoard();  
                            }
                            switchCurrentPlayer();

                        } else {
                            if (toRow == blackEndRow) {
                                System.out.println("BLACK WINS");
                                currentState.printBoard();
                            }
                            switchCurrentPlayer();
                        }
                    }
                } else {
                    System.out.println("Please select your pawn color");
                }
            } else if (opponentChoice == 3) {
                // Opponent is a HMinimax agent
                this.currentState = hMinMaxAlphaBeta(currentState, depth, Float.MIN_VALUE, Float.MAX_VALUE, true).getAction();
                switchCurrentPlayer();

            } else if (opponentChoice == 2){
                // Opponent is a Minimax agent
                this.currentState = minimax(currentState);
                switchCurrentPlayer();

            } else if (opponentChoice == 1) {
                // Opponent is a random agent
                makeRandomMove(currentState, currentPlayer);
                switchCurrentPlayer();

            } 
        }
        currentState.printBoard();
    }

    //short method to swap current player who's move it is
    public void switchCurrentPlayer(){
        if (currentPlayer.equals("white")){
            currentPlayer = "black";
        } else{
            currentPlayer = "white";
        }
    }

    //determine if state is a terminal state
    public boolean terminalTest(State state){
        // There is a winner
        if (winner(state, state.getBoardSize()).equals("white") || winner(state, state.getBoardSize()).equals("black")){
            return true;
        // Tie Game
        } else if (findAllValidMoves(state, currentPlayer).isEmpty()) {
            return true;
        } else{
            return false;
        }
    }
    //determine the winner
    private static String winner(State currentState, int size) {
        for (int i = 0; i < size; i++) {
            if (currentState.getBoardAtPos(size-1, i) != null) {
                return "white";
            } 
            if (currentState.getBoardAtPos(0, i) != null) {
                return "black";
            } 
        }
        return "none";
    }
    //measure value of a terminal state in favor of either white black or neither
    public int utility(State terminalState){
        if (winner(terminalState, terminalState.getBoardSize()).equals("white")){
            return 1;
        } else if (winner(terminalState, terminalState.getBoardSize()).equals("black")){
            return -1;
        } else{
            return 0;
        }
    
    }

    //measures materialAdvantage to weigh the advantages of certain moves over others
    // private int materialAdvantage(State state){
    //     int boardSize = state.getBoardSize();
    //     int whitePawnCount = 0;
    //     int blackPawnCount = 0;
    //     for (int row = 0; row < boardSize; row++) {
    //         for (int col = 0; col < boardSize; col++) {
    //             Pawn pawn = state.getBoardAtPos(row, col);
    //             if (pawn != null) {
    //                     if(pawn.getColor().equals("white")){
    //                         whitePawnCount++;
    //                 }
    //                     else if(pawn.getColor().equals("black")){
    //                         blackPawnCount++;
    //                     }
    //             }
    //         }
    //     }

    //     return whitePawnCount - blackPawnCount;

    // }

    //uses the potential for the capturing of opponent pieces to evaluate a position without full terminal depth
    public float heuristicFunction(State state){
        int boardSize = state.getBoardSize();
        int advantage = 0;
        int endRow = (currentPlayer.equals("white")) ? boardSize - 1 : 0;
        int pawnCount = 0;

        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                Pawn pawn = state.getBoardAtPos(row, col);
                if (pawn != null) {
                    pawnCount++;
                    // Calculate the difference between end and current row
                    int rowDifference = Math.abs(endRow - row);

                    advantage += rowDifference;
                    if(row == 4){
                        advantage += 5;
                    }

                    // Check if you can take a pawn
                    if (canTakeOpponentPawn(state, row, col)) {
                        advantage -= 10; // Increase advantage for potential captures
                    }
                }
            }
        }

        // Normalize the advantage based on the total number of pawns on the board
        if (pawnCount > 0) {
            float normalizedAdvantage = (float) advantage / (float) pawnCount;
            return normalizedAdvantage;
        } else {
            return 0; // No pawns on the board, neutral position
        }
    }

    // Checks if it can take a pawn
    private boolean canTakeOpponentPawn(State state, int row, int col) {
        String opponentColor = (currentPlayer.equals("white")) ? "black" : "white";
        int boardSize = state.getBoardSize();

        // Check for possible capture moves
        int[][] captureDirections = {
                {-1, -1}, // Capture to the upper-left
                {-1, 1}   // Capture to the upper-right
        };

        for (int[] dir : captureDirections) {
            int captureRow = row + dir[0];
            int captureCol = col + dir[1];

            // Check if the capture move is within bounds
            if (captureRow >= 0 && captureRow < boardSize && captureCol >= 0 && captureCol < boardSize) {
                Pawn opponentPawn = state.getBoardAtPos(captureRow, captureCol);
                if (opponentPawn != null && opponentPawn.getColor().equals(opponentColor)) {
                    return true; // There's an opponent's pawn that can be captured
                }
            }
        }

        return false;
    }
    
//finds all possible moves from a specific state
    public ArrayList<State> getActions (State state){
        ArrayList<State> actionsList = new ArrayList<State>();

        ArrayList<int[]> functions = findAllValidMoves(state, currentPlayer);

        if (functions.size() > 0){
            for (int i = 0; i< functions.size(); i++){
                State s = new State(ai, state.getBoard(), state.getBoardSize());
                s.setBoardAtPos(functions.get(i)[0], functions.get(i)[1], functions.get(i)[2], functions.get(i)[3]);
                actionsList.add(s);
            }
        }
        return actionsList;
    }


    // Heuristic minimax
    public Tuple hMinMaxAlphaBeta(State state, int depth, float alpha, float beta, boolean maximizingPlayer){
        if(depth == 0 || terminalTest(state)){
            return new Tuple(heuristicFunction(state), state);
        }
        if(maximizingPlayer){
            float value = Float.MIN_VALUE;
            ArrayList<State> actionsList = getActions(state);
            State moveStateChoice = actionsList.get(0);

            for(State action : actionsList){
                float result = hMinMaxAlphaBeta(action, depth - 1, alpha, beta, false).getValue();
                if(result > value){
                    value = result;
                    moveStateChoice = action;
                }
                if(value > beta){
                    break;
                }
                alpha = Math.max(alpha, value);
            }
            return new Tuple(value, moveStateChoice);
        } else {
            float value = Float.MAX_VALUE;
            ArrayList<State> actionsList = getActions(state);
            State moveStateChoice = actionsList.get(0);

            for(State action : actionsList){
                float result = hMinMaxAlphaBeta(action, depth - 1, alpha, beta, true).getValue();
                if(result < value){
                    value = result;
                    moveStateChoice = action;
                }
                if(value < alpha){
                    break;
                }
                beta = Math.max(beta, value);
            }
            return new Tuple(value, moveStateChoice);
        }
    }

    //minimax implementation to use adversarial search to find the best move as the computer
    public State minimax(State state)
    {
        ArrayList<State> actionsList = getActions(state);
        
        int maxMoveValue = Integer.MIN_VALUE;
        State moveStateChoice = actionsList.get(0);

        for (State action : actionsList)
        {
            int moveValue = minimizeValue(action);
            if (moveValue >= maxMoveValue)
            {
                maxMoveValue = moveValue;
                moveStateChoice = action;

            }
        }


        return moveStateChoice;
    }

    //maximizing part of minimax
    public int maximizeValue(State state)
    {
        int maxMoveValue = Integer.MIN_VALUE;

        if (terminalTest(state))
        {
            return utility(state);
        }

        ArrayList<State> actionsList = getActions(state);

        for (State action : actionsList)
        {
            maxMoveValue = Integer.max(maxMoveValue, minimizeValue(action));
        }
        
        return maxMoveValue;
    }

    //minimizing half of minimax
    public int minimizeValue(State state)
    {
        int minMoveValue = Integer.MAX_VALUE;

        if (terminalTest(state))
        {
            // state.printBoard();
            return utility(state);
        }

        ArrayList<State> actionsList = getActions(state);

        for (State action : actionsList)
        {
            //action.printBoard();
            minMoveValue = Integer.min(minMoveValue, maximizeValue(action));
        }

        return minMoveValue;
    }

    // Method to make a random move for the agent
    private void makeRandomMove(State currentState, String currentPlayer) {
        Random random = new Random();
        ArrayList<int[]> validMoves = findAllValidMoves(currentState, currentPlayer);

        if (!validMoves.isEmpty()) {
            int randomIndex = random.nextInt(validMoves.size());
            int[] move = validMoves.get(randomIndex);
            currentState.setBoardAtPos(move[0], move[1], move[2], move[3]);
            //board.movePawn(move[0], move[1], move[2], move[3]);
        } else {
            System.out.println("TIE GAME");
        }
    }
}
