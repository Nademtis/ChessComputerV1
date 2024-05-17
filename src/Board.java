import java.util.Arrays;
import java.util.Scanner;

public class Board {
    //Fen String generator

    public Board(int depth){
        this.depth = depth;
    }

    int depth = 4;

    Computer computer = new Computer();

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
    /*char[][] board = {
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
            {'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
    };*/


    //En måde på at tjekke om et træk er lovligt

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


    public boolean makeMove(String move, boolean isWhiteTurn) {
        int fromChar = Character.getNumericValue(move.charAt(0)) - 10;
        int fromNum = 9 - Character.getNumericValue(move.charAt(1)) - 1;
        int toChar = Character.getNumericValue(move.charAt(4)) - 10;
        int toNum = 9 - Character.getNumericValue(move.charAt(5)) - 1;

        System.out.println(fromChar + " " + fromNum + " " + toChar + " " + toNum);

        char piece = board[fromNum][fromChar];

        if (isWhiteTurn && Character.isLowerCase(piece) ||
                !isWhiteTurn && Character.isUpperCase(piece)) {
            System.out.println("Invalid move: Cannot move opponent's piece.");
            return false;
        } else {
            board[fromNum][fromChar] = ' ';
            board[toNum][toChar] = piece;
            return true;
        }
    }

    public void gameLoop() {
        System.out.println("Welcome to Chess");
        Scanner in = new Scanner(System.in);

        System.out.println("Do you want to start from FEN string: (yes/no)");
        String startFromFen = in.nextLine();
        if (startFromFen.equals("yes")) {
            System.out.println("Enter FEN string:");
            String fenString = in.nextLine();
            char[][] fenBoard = FenGenerator.interpretFEN(fenString);
            for (int i = 0; i < fenBoard.length; i++) {
                System.out.println(Arrays.toString(fenBoard[i]));
            }
            board = FenGenerator.interpretFEN(fenString);
        }

        //Choosing sides
        String side = chooseSide();
        boolean isWhiteSide = side.equals("WHITE");

        //Game loop
        String move;
        boolean validMove = false;
        boolean exit = false;

        do {
            drawBoard(board);

            if (isWhiteSide) {
                System.out.println("White turn. Enter move (pieceCoord, move to Coord) Example: e2, e4");
                move = in.nextLine();
                if (move.equals("exit")) {
                    exit = true;
                    break;
                }
                if (move.equals("fen")){
                    System.out.println(FenGenerator.generateFen(board, true, 0, 1));
                    break;
                }
                while (!validMove) {
                    validMove = makeMove(move, true);
                    if (!validMove) {
                        System.out.println("Invalid move. Please enter a valid move:");
                        move = in.nextLine();
                    }
                }
                validMove = false; // Reset validMove flag
                drawBoard(board);
                System.out.println("-------------------------------------------");
                System.out.println("Black/computer is thinking... hold on");
                //Computer computer = new Computer(board); //maybe not instantiate every time
                board = computer.computerMakeMove(depth, false, board);
                System.out.println("Computer is done.");
            } else {
                System.out.println("You chose Black. Computer (White) will make the first move.");
                //Computer computer = new Computer(board);
                board = computer.computerMakeMoveMeasure(depth, true, board);
                drawBoard(board);
                System.out.println("-------------------------------------------");
                System.out.println("Black turn. Enter move (pieceCoord, move to Coord) Example: e7, e5");
                move = in.nextLine();
                if (move.equals("exit")) {
                    exit = true;
                    break;
                }
                while (!validMove) {
                    validMove = makeMove(move, false);
                    if (!validMove) {
                        System.out.println("Invalid move. Please enter a valid move:");
                        move = in.nextLine();
                    }
                }
                validMove = false;
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
            /*try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/


            computer = new Computer(board);
            System.out.println("Black/computer is thinking... hold on");
            board = computer.computerMakeMoveMeasure(depth, false, board);
            System.out.println("black computer is done");
            drawBoard(board);

            /*try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/

            //code below is for manually moving black
            //move = in.nextLine();
            /*makeMove(move);
            if(move.equals("exit")){
                break;
            }*/

            //System.out.println("staticEval for Black " + new StaticEvaluator().evaluate(board,false));
        }
    }

    public String chooseSide(){
        Scanner in = new Scanner(System.in);
        System.out.println("Choose side: White or Black");
        String side = in.nextLine();
        while (!side.equals("White") && !side.equals("Black")) {
            System.out.println("Please enter a valid side: White or Black");
            side = in.nextLine();
        }
        return side.toUpperCase();
    }

}

