import java.util.Arrays;
import java.util.Scanner;

public class Board {
    //Fen String generator

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

    public boolean isChessmate(char[][] board) {
        boolean whiteKingFound = false;
        boolean blackKingFound = false;

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                char piece = board[row][col];
                if (piece == 'k') {
                    blackKingFound = true;
                } else if (piece == 'K') {
                    whiteKingFound = true;
                }
            }
        }

        if (!whiteKingFound) {
            System.out.println("Chessmate: White king is dead!");
            return true;
        } else if (!blackKingFound) {
            System.out.println("Chessmate: Black king is dead!");
            return true;
        }

        return false;
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

        //Choosing sides
        String side;
        while (true) {
            System.out.println("Choose your side (White or Black): ");
            side = in.nextLine().toUpperCase();
            if (side.equals("WHITE") || side.equals("BLACK")) {
                break;
            } else {
                System.out.println("Invalid choice. Please enter 'White' or 'Black'.");
            }
        }
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
                while (!validMove) {
                    validMove = makeMove(move, true);
                    if (!validMove) {
                        System.out.println("Invalid move. Please enter a valid move:");
                        move = in.nextLine();
                    }
                }
                validMove = false; // Reset validMove flag
                drawBoard(board);
                if (isChessmate(board)) {
                    break;
                }
                System.out.println("-------------------------------------------");
                System.out.println("Black/computer is thinking... hold on");
                Computer computer = new Computer(board);
                board = computer.computerMakeMove(6, false);
                System.out.println("Computer is done.");
                if (isChessmate(board)) {
                    drawBoard(board);
                    break;
                }
            } else {
                System.out.println("You chose Black. Computer (White) will make the first move.");
                Computer computer = new Computer(board);
                board = computer.computerMakeMove(6, true);
                System.out.println("Computer is done.");
                drawBoard(board);
                if (isChessmate(board)) {
                    break;
                }
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
                if (isChessmate(board)) {
                    drawBoard(board);
                    break;
                }
            }
        } while (!exit);
    }

    public void computerVSComputer() {
        System.out.println("Robot vs Robot... start!");
        Computer computer;
        while (true) {
            computer = new Computer(board);
            System.out.println("White/computer is thinking... hold on");
            board = computer.computerMakeMove(6, true);
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
            board = computer.computerMakeMove(5, false);
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

}

