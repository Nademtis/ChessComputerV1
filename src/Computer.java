import java.util.ArrayList;

public class Computer {

    public static void main(String[] args) {
        char[][] tempBoard = {
                {'r', 'P', 'b', 'q', 'k', 'b', 'n', 'r'},
                {'p', ' ', ' ', 'p', 'p', 'p', 'p', 'p'},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', 'p', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', 'P', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {'P', ' ', ' ', 'P', 'P', 'P', 'P', 'P'},
                {'R', 'p', 'B', 'Q', 'K', 'B', 'N', 'R'}
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
                        //pawnMoves(i, j, false);
                        blackPawnMove(i, j);
                        break;
                    case 'P':
                        //pawn
                        //pawnMoves(i, j, true);
                        whitePawnMove(i, j);
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

    //redundant code, can be removed
    public void pawnMoves(int row, int col, boolean isWhite) {
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

        } else if(!isWhite && board[row+1][col-1] != ' ' && Character.isUpperCase(board[row+1][col-1])){
            MoveType move = new MoveType();
            move.oldSpace = new int[]{row, col};
            move.newSpace = new int[]{row+1, col+1};
            move.piece = board[row][col];
            move.content = board[row+1][col+1];
            possibleMoves.add(move);
        }

        // black pawn capturing piece right
        if(col + 1  > 7){

        } else if(!isWhite && board[row+1][col+1] != ' ' && Character.isUpperCase(board[row+1][col+1])){
            MoveType move = new MoveType();
            move.oldSpace = new int[]{row, col};
            move.newSpace = new int[]{row+1, col+1};
            move.piece = board[row][col];
            move.content = board[row+1][col+1];
            possibleMoves.add(move);
        }

        // white pawn capturing piece left
        if(col - 1  < 0){

        } else if(isWhite && board[row-1][col-1] != ' ' && Character.isLowerCase(board[row-1][col-1])){
            MoveType move = new MoveType();
            move.oldSpace = new int[]{row, col};
            move.newSpace = new int[]{row+1, col-1};
            move.piece = board[row][col];
            move.content = board[row+1][col-1];
            possibleMoves.add(move);
        }

        // white pawn capturing piece right
        if(col + 1  > 7){

        } else if(isWhite && board[row-1][col+1] != ' ' && Character.isLowerCase(board[row-1][col+1])){
            MoveType move = new MoveType();
            move.oldSpace = new int[]{row, col};
            move.newSpace = new int[]{row+1, col+1};
            move.piece = board[row][col];
            move.content = board[row+1][col+1];
            possibleMoves.add(move);
        }


    }

    //region white pawn moves
    public void whitePawnMove(int row, int col){
        if(row == 0){
            whitePawnPromotion(row, col);
        } else {

        if(row > 0 && board[row-1][col] == ' ') {
            whitePawnMoveForwardOne(row, col);
        }

        if (row == 6 && board[row-2][col] == ' ') {
            whitePawnMoveForwardTwo(row, col);
        }

        if(col - 1  < 0){
        } else if(board[row-1][col-1] != ' ' && Character.isLowerCase(board[row-1][col-1])){
            whitePawnCaptureLeft(row, col);
        }

        if(col + 1  > 7){
        } else if(board[row-1][col+1] != ' ' && Character.isLowerCase(board[row-1][col+1])){
            whitePawnCaptureRight(row, col);
        }
        }
    }

    public void whitePawnPromotion(int row, int col){
        // white pawn promotion
        //todo better way to handle promotion
        MoveType move = new MoveType();
        move.oldSpace = new int[]{row, col};
        move.newSpace = new int[]{row, col};
        move.piece = board[row][col];
        move.content = 'Q';
        possibleMoves.add(move);
    }

    public void whitePawnMoveForwardOne(int row, int col){
        // white pawn moving 1 space forward
            MoveType move = new MoveType();
            move.oldSpace = new int[]{row, col};
            move.newSpace = new int[]{row - 1, col};
            move.piece = board[row][col];
            move.content = board[row - 1][col];
            possibleMoves.add(move);
    }

    public void whitePawnMoveForwardTwo(int row, int col){
        // white pawn moving 2 space forward
            MoveType move = new MoveType();
            move.oldSpace = new int[]{row, col};
            move.newSpace = new int[]{row - 2, col};
            move.piece = board[row][col];
            move.content = board[row - 2][col];
            possibleMoves.add(move);
    }

    public void whitePawnCaptureLeft(int row, int col){
        MoveType move = new MoveType();
        move.oldSpace = new int[]{row, col};
        move.newSpace = new int[]{row+1, col-1};
        move.piece = board[row][col];
        move.content = board[row+1][col-1];
        possibleMoves.add(move);
    }

    public void whitePawnCaptureRight(int row, int col){
        MoveType move = new MoveType();
        move.oldSpace = new int[]{row, col};
        move.newSpace = new int[]{row+1, col+1};
        move.piece = board[row][col];
        move.content = board[row+1][col+1];
        possibleMoves.add(move);
    }
    //endregion

    //region black pawn moves
    public void blackPawnMove(int row, int col){
        if(row == 7){
            blackPawnPromotion(row, col);
        } else {

            if (row < 7 && board[row + 1][col] == ' ') {
                blackPawnMoveForwardOne(row, col);
            }

            if (row == 1 && board[row + 2][col] == ' ') {
                blackPawnMoveForwardTwo(row, col);
            }

            if (col - 1 < 0) {
            } else if (board[row + 1][col - 1] != ' ' && Character.isUpperCase(board[row + 1][col - 1])) {
                blackPawnCaptureLeft(row, col);
            }

            if (col + 1 > 7) {
            } else if (board[row + 1][col + 1] != ' ' && Character.isUpperCase(board[row + 1][col + 1])) {
                blackPawnCaptureRight(row, col);
            }
        }
    }

    public void blackPawnPromotion(int row, int col){
        // black pawn promotion
        //todo better way to handle promotion
        MoveType move = new MoveType();
        move.oldSpace = new int[]{row, col};
        move.newSpace = new int[]{row, col};
        move.piece = board[row][col];
        move.content = 'q';
        possibleMoves.add(move);
    }

    public void blackPawnMoveForwardOne(int row, int col){
        MoveType move = new MoveType();
        move.oldSpace = new int[]{row, col};
        move.newSpace = new int[]{row + 1, col};
        move.piece = board[row][col];
        move.content = board[row + 1][col];
        possibleMoves.add(move);
    }

    public void blackPawnMoveForwardTwo(int row, int col){
        MoveType move = new MoveType();
        move.oldSpace = new int[]{row, col};
        move.newSpace = new int[]{row + 2, col};
        move.piece = board[row][col];
        move.content = board[row + 2][col];
        possibleMoves.add(move);
    }

    public void blackPawnCaptureLeft(int row, int col){
        MoveType move = new MoveType();
        move.oldSpace = new int[]{row, col};
        move.newSpace = new int[]{row+1, col-1};
        move.piece = board[row][col];
        move.content = board[row+1][col-1];
        possibleMoves.add(move);
    }

    public void blackPawnCaptureRight(int row, int col){
        MoveType move = new MoveType();
        move.oldSpace = new int[]{row, col};
        move.newSpace = new int[]{row+1, col+1};
        move.piece = board[row][col];
        move.content = board[row+1][col+1];
        possibleMoves.add(move);
    }
    //endregion



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
