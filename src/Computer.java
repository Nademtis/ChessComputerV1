import java.util.ArrayList;

public class Computer {

    //Generate moves

    //Sort moves

    //Only generate legal moves

    public Computer(char[][] board){
        this.board = board;
    }

    public char[][] board;

    public ArrayList<MoveType> possibleMoves = new ArrayList<>();

    public void GenerateMoves(){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                switch (board[i][j]){
                    case 'p':
                        //pawn
                        pawnMoves(i, j, false);
                        break;
                    case 'P':
                        //pawn
                        pawnMoves(i, j, true);
                        break;
                    case 'r':
                        //rook
                        break;
                    case 'R':
                        //rook
                        break;
                    case 'n':
                        //knight
                        break;
                    case 'N':
                        //knight
                        break;
                    case 'b':
                        //bishop
                        break;
                    case 'B':
                        //bishop
                        break;
                    case 'q':
                        //queen
                        break;
                    case 'Q':
                        //queen
                        break;
                    case 'k':
                        //king
                        break;
                    case 'K':
                        //king
                        break;
                    default:
                        break;
                }
            }
        }
    }

    public void pawnMoves(int i, int j, boolean isWhite){
        //TODO make sure pawn cannot move through another piece
        if(i == 1 && !isWhite){
            MoveType move = new MoveType();
            move.oldSpace = new int[]{i, j};
            move.newSpace = new int[]{i+2, j};
            move.piece = board[i][j];
            move.content = board[i+2][j];
            possibleMoves.add(move);
        } else if(!isWhite && i< 7) {
            MoveType move = new MoveType();
            move.oldSpace = new int[]{i, j};
            move.newSpace = new int[]{i+1, j};
            move.piece = board[i][j];
            move.content = board[i+1][j];
            possibleMoves.add(move);
        } else if(i == 6 && isWhite){
            MoveType move = new MoveType();
            move.oldSpace = new int[]{i, j};
            move.newSpace = new int[]{i-2, j};
            move.piece = board[i][j];
            move.content = board[i-2][j];
            possibleMoves.add(move);
        } else if(isWhite && i > 0) {
            MoveType move = new MoveType();
            move.oldSpace = new int[]{i, j};
            move.newSpace = new int[]{i-1, j};
            move.piece = board[i][j];
            move.content = board[i-1][j];
            possibleMoves.add(move);
        }

        if(!isWhite && board[i+1][j+1] != ' ' && Character.isLowerCase(board[i][j])){
            MoveType move = new MoveType();
            move.oldSpace = new int[]{i, j};
            move.newSpace = new int[]{i+1, j+1};
            move.piece = board[i][j];
            move.content = board[i+1][j+1];
            possibleMoves.add(move);
        }
        if(isWhite && board[i+1][j-1] != ' ' && Character.isUpperCase(board[i][j])){
            MoveType move = new MoveType();
            move.oldSpace = new int[]{i, j};
            move.newSpace = new int[]{i+1, j-1};
            move.piece = board[i][j];
            move.content = board[i+1][j-1];
            possibleMoves.add(move);
        }
        //todo promotion of pawn
    }

}
