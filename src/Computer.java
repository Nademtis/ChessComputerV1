import java.util.ArrayList;

public class Computer {

    public static void main(String[] args) {
        char[][] tempBoard = {
                {'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
                {'p', ' ', 'p', 'p', 'p', 'p', 'p', 'p'},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', 'p', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', 'P', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {'P', 'P', ' ', 'P', 'P', 'P', 'P', 'P'},
                {'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
        };

        Computer computer = new Computer(tempBoard);

        computer.generateMoves();
        //System.out.println(computer.possibleMoves);
        for (int i = 0; i < computer.possibleMoves.size(); i++) {
            System.out.println(computer.possibleMoves.get(i));
        }
        System.out.println(computer.possibleMoves.size());
    }

    //Generate moves

    //Sort moves

    //Only generate legal moves

    public Computer(char[][] board){
        this.board = board;
    }

    public char[][] board;

    public ArrayList<MoveType> possibleMoves = new ArrayList<>();

    public void generateMoves(){
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

    public void pawnMoves(int row, int col, boolean isWhite) {
        //TODO make sure pawn cannot move through another piece
        //TODO split method into black and white, so a method for black and a method for white pawn
        //TODO maybe instead reverse array.
        //black pawn starting position to move 2 spaces forward
        if (row == 1 && !isWhite && board[row+2][col] == ' ') {
            MoveType move = new MoveType();
            move.oldSpace = new int[]{row, col};
            move.newSpace = new int[]{row + 2, col};
            move.piece = board[row][col];
            move.content = board[row + 2][col];
            possibleMoves.add(move);
        }
        // black pawn moving 1 space forward
        if (!isWhite && row < 7  && board[row+1][col] == ' ') {
            MoveType move = new MoveType();
            move.oldSpace = new int[]{row, col};
            move.newSpace = new int[]{row + 1, col};
            move.piece = board[row][col];
            move.content = board[row + 1][col];
            possibleMoves.add(move);
        }
        // white pawn starting position to move 2 spaces forward
        if (row == 6 && isWhite  && board[row-2][col] == ' ') {
            MoveType move = new MoveType();
            move.oldSpace = new int[]{row, col};
            move.newSpace = new int[]{row - 2, col};
            move.piece = board[row][col];
            move.content = board[row - 2][col];
            possibleMoves.add(move);
        }
        // white pawn moving 1 space forward
        if (isWhite && row > 0  && board[row-1][col] == ' ') {
            MoveType move = new MoveType();
            move.oldSpace = new int[]{row, col};
            move.newSpace = new int[]{row - 1, col};
            move.piece = board[row][col];
            move.content = board[row - 1][col];
            possibleMoves.add(move);
        }

        // black pawn capturing piece left
        if(col - 1  < 0){

        } else if(!isWhite && board[row+1][col-1] != ' ' && Character.isLowerCase(board[row][col])){
            MoveType move = new MoveType();
            move.oldSpace = new int[]{row, col};
            move.newSpace = new int[]{row+1, col+1};
            move.piece = board[row][col];
            move.content = board[row+1][col+1];
            possibleMoves.add(move);
        }

        // black pawn capturing piece right
        if(col + 1  > 7){

        } else if(!isWhite && board[row+1][col+1] != ' ' && Character.isLowerCase(board[row][col])){
            MoveType move = new MoveType();
            move.oldSpace = new int[]{row, col};
            move.newSpace = new int[]{row+1, col+1};
            move.piece = board[row][col];
            move.content = board[row+1][col+1];
            possibleMoves.add(move);
        }

        // white pawn capturing piece left
        if(col - 1  < 0){

        } else if(isWhite && board[row-1][col-1] != ' ' && Character.isUpperCase(board[row][col])){
            MoveType move = new MoveType();
            move.oldSpace = new int[]{row, col};
            move.newSpace = new int[]{row+1, col-1};
            move.piece = board[row][col];
            move.content = board[row+1][col-1];
            possibleMoves.add(move);
        }

        // white pawn capturing piece right
        if(col + 1  > 7){

        } else if(isWhite && board[row-1][col+1] != ' ' && Character.isUpperCase(board[row][col])){
            MoveType move = new MoveType();
            move.oldSpace = new int[]{row, col};
            move.newSpace = new int[]{row+1, col+1};
            move.piece = board[row][col];
            move.content = board[row+1][col+1];
            possibleMoves.add(move);
        }


        //todo promotion of pawn
    }

    public void rookMoves(int i, int j, boolean isWhite) {
        char currentPiece = board[i][j];

        // Rook moves horizontally to the right
        for (int k = j + 1; k < 8; k++) {
            char content = board[i][k];
            if (content == ' ') {
                // Empty space, add move
                MoveType move = new MoveType();
                move.oldSpace = new int[]{i, j};
                move.newSpace = new int[]{i, k};
                move.piece = currentPiece;
                move.content = content;
                possibleMoves.add(move);
            } else {
                // Opponent's piece, check if capture is possible
                if (Character.isLowerCase(currentPiece)) {
                    // Current piece is black
                    if (Character.isUpperCase(content)) {
                        // Can capture opponent's piece
                        MoveType move = new MoveType();
                        move.oldSpace = new int[]{i, j};
                        move.newSpace = new int[]{i, k};
                        move.piece = currentPiece;
                        move.content = content;
                        possibleMoves.add(move);
                    }
                    // Stop considering moves in this direction
                } else {
                    // Current piece is white
                    if (Character.isLowerCase(content)) {
                        // Can capture opponent's piece
                        MoveType move = new MoveType();
                        move.oldSpace = new int[]{i, j};
                        move.newSpace = new int[]{i, k};
                        move.piece = currentPiece;
                        move.content = content;
                        possibleMoves.add(move);
                    }
                    // Stop considering moves in this direction
                }
                break;
            }
        }

        // Rook moves horizontally to the left
        for (int k = j - 1; k >= 0; k--) {
            char content = board[i][k];
            if (content == ' ') {
                // Empty space, add move
                MoveType move = new MoveType();
                move.oldSpace = new int[]{i, j};
                move.newSpace = new int[]{i, k};
                move.piece = currentPiece;
                move.content = content;
                possibleMoves.add(move);
            } else {
                // Opponent's piece, check if capture is possible
                if (Character.isLowerCase(currentPiece)) {
                    // Current piece is black
                    if (Character.isUpperCase(content)) {
                        // Can capture opponent's piece
                        MoveType move = new MoveType();
                        move.oldSpace = new int[]{i, j};
                        move.newSpace = new int[]{i, k};
                        move.piece = currentPiece;
                        move.content = content;
                        possibleMoves.add(move);
                    }
                    // Stop considering moves in this direction
                    break;
                } else {
                    // Current piece is white
                    if (Character.isLowerCase(content)) {
                        // Can capture opponent's piece
                        MoveType move = new MoveType();
                        move.oldSpace = new int[]{i, j};
                        move.newSpace = new int[]{i, k};
                        move.piece = currentPiece;
                        move.content = content;
                        possibleMoves.add(move);
                    }
                    // Stop considering moves in this direction
                    break;
                }
            }
        }

        // Rook moves vertically upwards
        for (int k = i - 1; k >= 0; k--) {
            char content = board[k][j];
            if (content == ' ') {
                // Empty space, add move
                MoveType move = new MoveType();
                move.oldSpace = new int[]{i, j};
                move.newSpace = new int[]{k, j};
                move.piece = currentPiece;
                move.content = content;
                possibleMoves.add(move);
            } else {
                // Opponent's piece, check if capture is possible
                if (Character.isLowerCase(currentPiece)) {
                    // Current piece is black
                    if (Character.isUpperCase(content)) {
                        // Can capture opponent's piece
                        MoveType move = new MoveType();
                        move.oldSpace = new int[]{i, j};
                        move.newSpace = new int[]{k, j};
                        move.piece = currentPiece;
                        move.content = content;
                        possibleMoves.add(move);
                    }
                    // Stop considering moves in this direction
                    break;
                } else {
                    // Current piece is white
                    if (Character.isLowerCase(content)) {
                        // Can capture opponent's piece
                        MoveType move = new MoveType();
                        move.oldSpace = new int[]{i, j};
                        move.newSpace = new int[]{k, j};
                        move.piece = currentPiece;
                        move.content = content;
                        possibleMoves.add(move);
                    }
                    // Stop considering moves in this direction
                    break;
                }
            }
        }

        // Rook moves vertically downwards
        for (int k = i + 1; k < 8; k++) {
            char content = board[k][j];
            if (content == ' ') {
                // Empty space, add move
                MoveType move = new MoveType();
                move.oldSpace = new int[]{i, j};
                move.newSpace = new int[]{k, j};
                move.piece = currentPiece;
                move.content = content;
                possibleMoves.add(move);
            } else {
                // Opponent's piece, check if capture is possible
                if (Character.isLowerCase(currentPiece)) {
                    // Current piece is black
                    if (Character.isUpperCase(content)) {
                        // Can capture opponent's piece
                        MoveType move = new MoveType();
                        move.oldSpace = new int[]{i, j};
                        move.newSpace = new int[]{k, j};
                        move.piece = currentPiece;
                        move.content = content;
                        possibleMoves.add(move);
                    }
                    // Stop considering moves in this direction
                    break;
                } else {
                    // Current piece is white
                    if (Character.isLowerCase(content)) {
                        // Can capture opponent's piece
                        MoveType move = new MoveType();
                        move.oldSpace = new int[]{i, j};
                        move.newSpace = new int[]{k, j};
                        move.piece = currentPiece;
                        move.content = content;
                        possibleMoves.add(move);
                    }
                    // Stop considering moves in this direction
                    break;
                }
            }
        }
    }
}
