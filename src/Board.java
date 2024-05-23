import java.util.Arrays;
import java.util.Scanner;

public class Board {
    //Fen String generator

    public Board(int depth){
        this.depth = depth;
        computer.setMaxDepth(depth);
    }

    int depth = 4;

    Computer computer = new Computer();

    SimpleTimer timer = new SimpleTimer(15, computer);

    public static int fullMoveCounter = 1;
    public static int halfMoveCounter = 0;



    //SimpleTimer timer = new SimpleTimer(15);

    char[][] board = {
            {'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
            {'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
            {'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
    };


    //En måde på at tjekke om et træk er lovligt

    boolean exit = false;

    public void gameLoop() {
        startUp();
        //Choosing sides
        boolean isWhiteSide = chooseSide().equals("WHITE");

        //Game loop
        if(!isWhiteSide){
            System.out.println("You chose Black. Computer (White) will make the first move.");
            board = computer.computerMakeMove(depth, true, board);
            drawBoard(board);
            System.out.println("-------------------------------------------");
            System.out.println("Black turn. Enter move (pieceCoord, move to Coord) Example: e7, e5");
            playerMove(false);
            fullMoveCounter++;
        }

        do {
            drawBoard(board);

            if (isWhiteSide) {
                System.out.println("White turn. Enter move (pieceCoord, move to Coord) Example: e2, e4");
                playerMove(true);
                drawBoard(board);
                computerMove(false);

            } else {
                computerMove(true);
                drawBoard(board);
                System.out.println("-------------------------------------------");
                System.out.println("Black turn. Enter move (pieceCoord, move to Coord) Example: e7, e5");
                playerMove(false);

            }
            fullMoveCounter++;
            if(!exit) {
                System.out.println(FenGenerator.generateFen(board, true, halfMoveCounter, fullMoveCounter));
            }
        } while (!exit);
    }

    public void computerVSComputer() {
        System.out.println("Robot vs Robot... start!");
        Computer computer;
        while (true) {
            computer = new Computer(board);
            System.out.println("White/computer is thinking... hold on");
            board = computer. computerMakeMoveMeasure(depth, true, board);
            System.out.println("white computer is done");
            drawBoard(board);

            System.out.println("-------------------------------------------");



            computer = new Computer(board);
            System.out.println("Black/computer is thinking... hold on");
            board = computer.computerMakeMoveMeasure(depth, false, board);
            System.out.println("black computer is done");
            drawBoard(board);

        }
    }

    public void computerVSComputerIterativeDeepening(){
        computer.board = board;
        System.out.println("Robot vs Robot... start!");
        while (true){
            computerMoveIterative(true);
            drawBoard(board);

            System.out.println("-------------------------------------------");

            computerMoveIterative(false);
            drawBoard(board);

        }
    }

    public void computerIterativeVSComputer(){
        computer.board = board;
        System.out.println("Robot vs Robot... start!");
        while (true){
            computerMoveIterative(true);
            drawBoard(board);

            System.out.println("-------------------------------------------");

            computerMove(false);
            drawBoard(board);

        }
    }

    private void playerMove(boolean whitePlay){
        Scanner in = new Scanner(System.in);
        String move = in.nextLine();
        boolean validMove = false;

        if(move.equals("exit")){
            System.out.println("Exiting game...");
            exit = true;
        } else if(move.equals("fen")){
            System.out.println(FenGenerator.generateFen(board, whitePlay, halfMoveCounter, fullMoveCounter));
            exit = true;
        } else {
            while(!validMove){
                validMove = makeMove(move, whitePlay);
                if(!validMove){
                    System.out.println("Invalid move. Please enter a valid move:");
                    move = in.nextLine();
                }
            }}
    }

    private void computerMove(boolean whitePlay){
        if(whitePlay){
            System.out.println("White/computer is thinking... hold on");
            board = computer.computerMakeMove(depth, true, board);
            System.out.println("Computer is done.");
        } else {
            System.out.println("-------------------------------------------");
            System.out.println("Black/computer is thinking... hold on");
            board = computer.computerMakeMove(depth, false, board);
            System.out.println("Computer is done.");
        }
    }

    private void computerMoveIterative(boolean whitePlay) {
        if (whitePlay) {
            System.out.println("White/computer is thinking... hold on");
        } else {
            System.out.println("-------------------------------------------");
            System.out.println("Black/computer is thinking... hold on");
        }

        // Create a new instance of the computer thread
        Computer computer = new Computer();
        computer.setMaxDepth(depth);
        computer.setIsWhiteTurn(whitePlay);
        computer.board = board;

        //Create a new instance of the timer
        SimpleTimer timer = new SimpleTimer(4, computer);


        // Start the timer
        timer.start();
        // Start the computer thread
        computer.start();
        try {
            // Wait for the computer thread to finish
            computer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Stop the timer
        timer.stopTimer();

        // Apply the best move from the computer thread to the board
        board = computer.applyBestMoveSoFar();

        System.out.println("Computer is done.");
    }

    private void startUp(){
        System.out.println("Welcome to Chess");
        Scanner in = new Scanner(System.in);

        System.out.println("Do you want to start from FEN string: (yes/no)");
        String startFromFen = in.nextLine();
        if (startFromFen.equals("yes")) {
            System.out.println("Enter FEN string:");
            String fenString = in.nextLine();
            String[] fenParts = fenString.split(" ");
            System.out.println(Arrays.toString(fenParts));
            char[][] fenBoard = FenGenerator.interpretFEN(fenParts[0]);
            for (int i = 0; i < fenBoard.length; i++) {
                System.out.println(Arrays.toString(fenBoard[i]));
        }
            board = FenGenerator.interpretFEN(fenParts[0]);

            if(fenParts[1].equals("b")){
                System.out.println("Black turn. Should I make the first move? (yes/no)");
                String makeFirstMove = in.nextLine();
                if(makeFirstMove.equals("yes")){
                    board = computer.computerMakeMove(depth, false, board);
                    drawBoard(board);
                } else {
                    System.out.println("Black turn. Enter move (pieceCoord, move to Coord) Example: e7, e5");
                    playerMove(false);
                }
            }
        }
    }

    public boolean makeMove(String move, boolean isWhiteTurn) {
        //todo change how a move is made, idea: move = "pawn e4" where you check what piece can move to that position
        int fromChar = Character.getNumericValue(move.charAt(0)) - 10;
        int fromNum = 9 - Character.getNumericValue(move.charAt(1)) - 1;
        int toChar = Character.getNumericValue(move.charAt(4)) - 10;
        int toNum = 9 - Character.getNumericValue(move.charAt(5)) - 1;

        char piece = board[fromNum][fromChar];

        if (isWhiteTurn && Character.isLowerCase(piece) ||
                !isWhiteTurn && Character.isUpperCase(piece)) {
            System.out.println("Invalid move: Cannot move opponent's piece.");
            return false;
        } else {
            board[fromNum][fromChar] = ' ';
            board[toNum][toChar] = piece;

            if(piece=='P' || piece=='p'){
                halfMoveCounter = 0;
            } else {
                halfMoveCounter++;
            }

            return true;
        }
    }

    public String chooseSide(){
        Scanner in = new Scanner(System.in);
        System.out.println("Choose side: White or Black");
        String side = in.nextLine();
        while (!side.equalsIgnoreCase("White") && !side.equalsIgnoreCase("Black")) {
            System.out.println("Please enter a valid side: White or Black");
            side = in.nextLine();
        }
        return side.toUpperCase();
    }


    //region DrawBoard
    private static String getPieceSymbol(char piece) {
        switch (piece) {
            case 'r':
                return "♜\t";
            case 'n':
                return "♞\t";
            case 'b':
                return "♝\t";
            case 'q':
                return "♛\t";
            case 'k':
                return "♚\t";
            case 'p':
                return "♟\t";
            case 'R':
                return "♖\t";
            case 'N':
                return "♘\t";
            case 'B':
                return "♗\t";
            case 'Q':
                return "♕\t";
            case 'K':
                return "♔\t";
            case 'P':
                return "♙\t";
            default:
                return "\t"; // Two spaces for empty cells
        }
    }

    public static void drawBoard(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            System.out.print(8 - i + "\t");
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(getPieceSymbol(board[i][j]));
            }
            System.out.println();
        }
        System.out.println("    a   b   c   d   e   f   g   h");
    }
    //endregion
}

