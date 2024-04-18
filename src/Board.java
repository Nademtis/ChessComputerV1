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


    public void makeMove(String move){
        int fromChar = Character.getNumericValue(move.charAt(0)) - 10;
        int fromNum = 9 - Character.getNumericValue(move.charAt(1)) - 1;
        int toChar = Character.getNumericValue(move.charAt(4)) - 10;
        int toNum = 9 - Character.getNumericValue(move.charAt(5)) - 1;

        System.out.println(fromChar + " " + fromNum + " " + toChar + " " + toNum);

        char piece = board[fromNum][fromChar];
        board[fromNum][fromChar] = ' ';
        board[toNum][toChar] = piece;
    }

    public void gameLoop(){
        System.out.println("Welcome to Chess");
        Scanner in = new Scanner(System.in);
        String move;
        while(true){
            drawBoard(board);
            System.out.println("White turn. Enter move(pieceCoord, move to Coord) Example: b2, b4");
            move = in.nextLine();
            if(move.equals("exit")){
                break;
            }
            //System.out.println("staticEval for White " + new StaticEvaluator().evaluate(board,true));
            makeMove(move);
            drawBoard(board);
            System.out.println("Black turn. Enter move(pieceCoord, move to Coord) Example: b2, b4");
            move = in.nextLine();
            makeMove(move);
            if(move.equals("exit")){
                break;
            }
            //System.out.println("staticEval for Black " + new StaticEvaluator().evaluate(board,false));
        }
    }

    public static void main(String[] args) {
        Board board = new Board();
        board.gameLoop();
    }
}
