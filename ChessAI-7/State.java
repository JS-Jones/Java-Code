public class State {
    private int boardSize;
    private Player player;
    private Pawn[][] board;
    private int opponentChoice;
    private int whitePiecesCount;
    private int blackPiecesCount;
    

    // Constructor taking in player, boardsize, and opponent choice
    public State(Player player, int size, int opponentChoice) {
        this.boardSize = size;
        this.opponentChoice = opponentChoice;
        this.board = new Pawn[size][size];

        this.player = player;
        initializePawns();
    }

    // Constructor taking in the player, the current board state, and the board size
    public State(Player player, Pawn[][] board, int boardSize){
        this.player = player;
        this.board = new Pawn[boardSize][boardSize];
        this.boardSize = boardSize;
        for (int row = 0; row < boardSize; row++){
            for (int col = 0; col < boardSize; col++){
                this.board[row][col] = board[row][col];
            }
        }
    }

    // Creates white pawns on the first row and black pawns on the 2nd to last row of the baord
    private void initializePawns() {
        // Initialize white pawns in the second row
        for (int col = 0; col < boardSize; col++) {
            board[1][col] = new Pawn("white");
            this.whitePiecesCount = boardSize;
        }

         // Initialize Black pawns in the second-to-last row
         for (int col = 0; col < boardSize; col++) {
            board[boardSize - 2][col] = new Pawn("black");
            this.blackPiecesCount = boardSize;
        }
    }

    // Returns the baord size
    public int getBoardSize(){
        return boardSize;
    }

    // Sets the board size
    public void setBoardSize(int size){
        this.boardSize = size;
    }

    // returns the oppoonent choice
    public int getOpponentChoice(){
        return opponentChoice;
    }

    // Sets the opponent choice
    public void setOpponentChoice(int opponentChoice){
        this.opponentChoice = opponentChoice;
    }

    // Returns the player
    public Player getPlayer(){
        return this.player;
    }

    // Sets the player
    public void setPlayer(Player player){
        this.player = player;
    }

    // Returns the state of teh board - 2D array
    public Pawn[][] getBoard(){
        return this.board;
    }

    // sets the board
    public void setBoard(Pawn[][] board){
        for (int row = 0; row < this.boardSize; row++)
        {
            for (int col = 0; col < this.boardSize; col++)
            {
                this.board[row][col] = board[row][col];
            }
        }
    }

    // Returns a pawn at a position (row,col)
    public Pawn getBoardAtPos(int row, int col){
        return this.board[row][col];
    }

    // Sets the pawn position by taking in current pawn location and where it wants to go
    public void setBoardAtPos(int fromRow, int fromCol, int toRow, int toCol){
        this.countPieces();
        String color = getBoardAtPos(fromRow, fromCol).getColor();
        this.board[toRow][toCol] = new Pawn(color);
        this.board[fromRow][fromCol] = null;
    }

    // return the number of white pieces
    public int getWhitePiecesCount(){
        return this.whitePiecesCount;
    }

    // Sets the number of white pieces
    public void setWhitePiecesCount(int pieces){
        this.whitePiecesCount = pieces;
    }

    // return the number of black pieces
    public int getBlackPiecesCount(){
        return this.blackPiecesCount;
    }

    // Set the number of black pieces
    public void setBlackPiecesCount(int pieces){
        this.blackPiecesCount = pieces;
    }

    // returns the total number of pieces = white + black
    public int getTotalPieces(){
        return this.whitePiecesCount + this.blackPiecesCount;
    }

    // Counts the number of pieces on the board
    public void countPieces(){
        int whiteCount = 0;
        int blackCount = 0;

        for (int row = 0; row < boardSize; row++){
            for (int col = 0; col < boardSize; col++){
                if (this.getBoardAtPos(row, col) == null){
                    continue;
                }
                if (this.getBoardAtPos(row, col).getColor().equals("white")){
                    whiteCount+=1;
                }
                else if (this.getBoardAtPos(row, col).getColor().equals("black")){
                    blackCount+=1;
                }
            }
        }

        setWhitePiecesCount(whiteCount);
        setBlackPiecesCount(blackCount);
    }

    // Prints out the current board state
    public void printBoard(){
        System.out.println();
        System.out.print("    ");
        for (char colChar = 'a'; colChar < 'a' + boardSize; colChar++) {
            System.out.print(colChar + "   ");
        }
        System.out.println();
        System.out.print("  +");
            for(int col = 0; col < boardSize; col++) {
                System.out.print("---+");
            }
            System.out.println();
        for (int row = boardSize - 1; row >= 0; row--) {
            System.out.print(row + 1);
            System.out.print(" | ");
            for (int col = 0; col < boardSize; col++) {
                if (board[row][col] != null) {
                    ////System.out.print(row+""+col);
                    System.out.print(board[row][col].getSymbol() + " | ");
                } else {
                    ////System.out.print(row+""+col);
                    System.out.print("  | ");
                }
            }
            System.out.print(row + 1);
            System.out.println();
            System.out.print("  +");
            for(int col = 0; col < boardSize; col++) {
                System.out.print("---+");
            }
            System.out.println();
        }
        System.out.print("    ");
        for (char colChar = 'a'; colChar < 'a' + boardSize; colChar++) {
            System.out.print(colChar + "   ");
        }
        System.out.println("\n");
    }
}