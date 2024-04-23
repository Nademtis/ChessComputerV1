import java.util.ArrayList;

public class Computer {

    public static void main(String[] args) {
        char[][] tempBoard = {
                {'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
                {'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
                {'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
        };

        char[][] tempBoard2 = {
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}
        };

        Computer computer = new Computer(tempBoard2);

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
                        blackPawnMove(i, j);
                        break;
                    case 'P':
                        //pawn
                        whitePawnMove(i, j);
                        break;
                    case 'r':
                        //rook
                        blackRookMoves(i, j);
                        break;
                    case 'R':
                        //rook
                        whiteRookMoves(i, j);
                        break;
                    case 'n':
                        //knight
                        blackKnightMoves(i, j);
                        break;
                    case 'N':
                        //knight
                        whiteKnightMoves(i, j);
                        break;
                    case 'b':
                        //bishop
                        blackBishopMoves(i, j);
                        break;
                    case 'B':
                        //bishop
                        whiteBishopMoves(i, j);
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


    //region white knight moves
    public void whiteKnightMoves(int row, int col){
        // white knight moves
        if(row - 2 >= 0 && col - 1 >= 0 && (board[row-2][col-1] == ' ' || Character.isLowerCase(board[row-2][col-1]))){
            whiteKnightMoveForwardLeft(row, col);
        }

        if(row - 2 >= 0 && col + 1 < 8 && (board[row-2][col+1] == ' ' || Character.isLowerCase(board[row-2][col+1]))){
            whiteKnightMoveForwardRight(row, col);
        }

        if(row - 1 >= 0 && col - 2 >= 0 && (board[row-1][col-2] == ' ' || Character.isLowerCase(board[row-1][col-2]))){
            whiteKnightMoveLeftForward(row, col);
        }

        if(row - 1 >= 0 && col + 2 < 8 && (board[row-1][col+2] == ' ' || Character.isLowerCase(board[row-1][col+2]))){
            whiteKnightMoveRightForward(row, col);
        }

        if(row + 1 < 8 && col - 2 >= 0 && (board[row+1][col-2] == ' ' || Character.isLowerCase(board[row+1][col-2]))){
            whiteKnightMoveLeftBack(row, col);
        }

        if(row + 1 < 8 && col + 2 < 8 && (board[row+1][col+2] == ' ' || Character.isLowerCase(board[row+1][col+2]))){
            whiteKnightMoveRightBack(row, col);
        }

        if(row + 2 < 8 && col - 1 >= 0 && (board[row+2][col-1] == ' ' || Character.isLowerCase(board[row+2][col-1]))){
            whiteKnightMoveBackLeft(row, col);
        }

        if(row + 2 < 8 && col + 1 < 8 && (board[row+2][col+1] == ' ' || Character.isLowerCase(board[row+2][col+1]))){
            whiteKnightMoveBackRight(row, col);
        }
    }

    public void whiteKnightMoveForwardLeft(int row, int col){
        MoveType move = new MoveType();
        move.oldSpace = new int[]{row, col};
        move.newSpace = new int[]{row-2, col-1};
        move.piece = board[row][col];
        move.content = board[row-2][col-1];
        possibleMoves.add(move);
    }

    public void whiteKnightMoveForwardRight(int row, int col){
        MoveType move = new MoveType();
        move.oldSpace = new int[]{row, col};
        move.newSpace = new int[]{row-2, col+1};
        move.piece = board[row][col];
        move.content = board[row-2][col+1];
        possibleMoves.add(move);
    }

    public void whiteKnightMoveLeftForward(int row, int col){
        MoveType move = new MoveType();
        move.oldSpace = new int[]{row, col};
        move.newSpace = new int[]{row-1, col-2};
        move.piece = board[row][col];
        move.content = board[row-1][col-2];
        possibleMoves.add(move);
    }

    public void whiteKnightMoveRightForward(int row, int col){
        MoveType move = new MoveType();
        move.oldSpace = new int[]{row, col};
        move.newSpace = new int[]{row-1, col+2};
        move.piece = board[row][col];
        move.content = board[row-1][col+2];
        possibleMoves.add(move);
    }

    public void whiteKnightMoveLeftBack(int row, int col){
        MoveType move = new MoveType();
        move.oldSpace = new int[]{row, col};
        move.newSpace = new int[]{row+1, col-2};
        move.piece = board[row][col];
        move.content = board[row+1][col-2];
        possibleMoves.add(move);
    }

    public void whiteKnightMoveRightBack(int row, int col){
        MoveType move = new MoveType();
        move.oldSpace = new int[]{row, col};
        move.newSpace = new int[]{row+1, col+2};
        move.piece = board[row][col];
        move.content = board[row+1][col+2];
        possibleMoves.add(move);
    }

    public void whiteKnightMoveBackLeft(int row, int col){
        MoveType move = new MoveType();
        move.oldSpace = new int[]{row, col};
        move.newSpace = new int[]{row+2, col-1};
        move.piece = board[row][col];
        move.content = board[row+2][col-1];
        possibleMoves.add(move);
    }

    public void whiteKnightMoveBackRight(int row, int col){
        MoveType move = new MoveType();
        move.oldSpace = new int[]{row, col};
        move.newSpace = new int[]{row+2, col+1};
        move.piece = board[row][col];
        move.content = board[row+2][col+1];
        possibleMoves.add(move);
    }
    //endregion

    //region Black knight moves
    public void blackKnightMoves(int row, int col){
        // black knight moves
        if(row - 2 >= 0 && col - 1 >= 0 && (board[row-2][col-1] == ' ' || Character.isUpperCase(board[row-2][col-1]))){
            blackKnightMoveForwardLeft(row, col);
        }

        if(row - 2 >= 0 && col + 1 < 8 && (board[row-2][col+1] == ' ' || Character.isUpperCase(board[row-2][col+1]))){
            blackKnightMoveForwardRight(row, col);
        }

        if(row - 1 >= 0 && col - 2 >= 0 && (board[row-1][col-2] == ' ' || Character.isUpperCase(board[row-1][col-2]))){
            blackKnightMoveLeftForward(row, col);
        }

        if(row - 1 >= 0 && col + 2 < 8 && (board[row-1][col+2] == ' ' || Character.isUpperCase(board[row-1][col+2]))){
            blackKnightMoveRightForward(row, col);
        }

        if(row + 1 < 8 && col - 2 >= 0 && (board[row+1][col-2] == ' ' || Character.isUpperCase(board[row+1][col-2]))){
            blackKnightMoveLeftBack(row, col);
        }

        if(row + 1 < 8 && col + 2 < 8 && (board[row+1][col+2] == ' ' || Character.isUpperCase(board[row+1][col+2]))){
            blackKnightMoveRightBack(row, col);
        }

        if(row + 2 < 8 && col - 1 >= 0 && (board[row+2][col-1] == ' ' || Character.isUpperCase(board[row+2][col-1]))){
            blackKnightMoveBackLeft(row, col);
        }

        if(row + 2 < 8 && col + 1 < 8 && (board[row+2][col+1] == ' ' || Character.isUpperCase(board[row+2][col+1]))){
            blackKnightMoveBackRight(row, col);
        }
    }

    public void blackKnightMoveForwardLeft(int row, int col){
        MoveType move = new MoveType();
        move.oldSpace = new int[]{row, col};
        move.newSpace = new int[]{row-2, col-1};
        move.piece = board[row][col];
        move.content = board[row-2][col-1];
        possibleMoves.add(move);
    }

    public void blackKnightMoveForwardRight(int row, int col){
        MoveType move = new MoveType();
        move.oldSpace = new int[]{row, col};
        move.newSpace = new int[]{row-2, col+1};
        move.piece = board[row][col];
        move.content = board[row-2][col+1];
        possibleMoves.add(move);
    }

    public void blackKnightMoveLeftForward(int row, int col){
        MoveType move = new MoveType();
        move.oldSpace = new int[]{row, col};
        move.newSpace = new int[]{row-1, col-2};
        move.piece = board[row][col];
        move.content = board[row-1][col-2];
        possibleMoves.add(move);
    }

    public void blackKnightMoveRightForward(int row, int col){
        MoveType move = new MoveType();
        move.oldSpace = new int[]{row, col};
        move.newSpace = new int[]{row-1, col+2};
        move.piece = board[row][col];
        move.content = board[row-1][col+2];
        possibleMoves.add(move);
    }

    public void blackKnightMoveLeftBack(int row, int col){
        MoveType move = new MoveType();
        move.oldSpace = new int[]{row, col};
        move.newSpace = new int[]{row+1, col-2};
        move.piece = board[row][col];
        move.content = board[row+1][col-2];
        possibleMoves.add(move);
    }

    public void blackKnightMoveRightBack(int row, int col){
        MoveType move = new MoveType();
        move.oldSpace = new int[]{row, col};
        move.newSpace = new int[]{row+1, col+2};
        move.piece = board[row][col];
        move.content = board[row+1][col+2];
        possibleMoves.add(move);
    }

    public void blackKnightMoveBackLeft(int row, int col){
        MoveType move = new MoveType();
        move.oldSpace = new int[]{row, col};
        move.newSpace = new int[]{row+2, col-1};
        move.piece = board[row][col];
        move.content = board[row+2][col-1];
        possibleMoves.add(move);
    }

    public void blackKnightMoveBackRight(int row, int col){
        MoveType move = new MoveType();
        move.oldSpace = new int[]{row, col};
        move.newSpace = new int[]{row+2, col+1};
        move.piece = board[row][col];
        move.content = board[row+2][col+1];
        possibleMoves.add(move);
    }
    //endregion''

    //region White Bishop moves
    public void whiteBishopMoves(int row, int col){
        whiteBishopLeftDiagonalForward(row, col);
        whiteBishopRightDiagonalForward(row, col);
        whiteBishopLeftDiagonalBack(row, col);
        whiteBishopRightDiagonalBack(row, col);
    }

    public void whiteBishopLeftDiagonalBack(int row, int col){
        int i = row + 1;
        int j = col - 1;
        while(i < 8 && j >= 0){
            if(board[i][j] == ' '){
                MoveType move = new MoveType();
                move.oldSpace = new int[]{row, col};
                move.newSpace = new int[]{i, j};
                move.piece = board[row][col];
                move.content = board[i][j];
                possibleMoves.add(move);
            } else if(Character.isLowerCase(board[i][j])){
                MoveType move = new MoveType();
                move.oldSpace = new int[]{row, col};
                move.newSpace = new int[]{i, j};
                move.piece = board[row][col];
                move.content = board[i][j];
                possibleMoves.add(move);
                break;
            } else {
                break;
            }
            i++;
            j--;
        }
    }

    public void whiteBishopRightDiagonalBack(int row, int col){
        int i = row + 1;
        int j = col + 1;
        while(i < 8 && j < 8){
            if(board[i][j] == ' '){
                MoveType move = new MoveType();
                move.oldSpace = new int[]{row, col};
                move.newSpace = new int[]{i, j};
                move.piece = board[row][col];
                move.content = board[i][j];
                possibleMoves.add(move);
            } else if(Character.isLowerCase(board[i][j])){
                MoveType move = new MoveType();
                move.oldSpace = new int[]{row, col};
                move.newSpace = new int[]{i, j};
                move.piece = board[row][col];
                move.content = board[i][j];
                possibleMoves.add(move);
                break;
            } else {
                break;
            }
            i++;
            j++;
        }
    }

    public void whiteBishopLeftDiagonalForward(int row, int col){
        int i = row - 1;
        int j = col - 1;
        while(i >= 0 && j >= 0){
            if(board[i][j] == ' '){
                MoveType move = new MoveType();
                move.oldSpace = new int[]{row, col};
                move.newSpace = new int[]{i, j};
                move.piece = board[row][col];
                move.content = board[i][j];
                possibleMoves.add(move);
            } else if(Character.isLowerCase(board[i][j])){
                MoveType move = new MoveType();
                move.oldSpace = new int[]{row, col};
                move.newSpace = new int[]{i, j};
                move.piece = board[row][col];
                move.content = board[i][j];
                possibleMoves.add(move);
                break;
            } else {
                break;
            }
            i--;
            j--;
        }
    }

    public void whiteBishopRightDiagonalForward(int row, int col){
        int i = row - 1;
        int j = col + 1;
        while(i >= 0 && j < 8){
            if(board[i][j] == ' '){
                MoveType move = new MoveType();
                move.oldSpace = new int[]{row, col};
                move.newSpace = new int[]{i, j};
                move.piece = board[row][col];
                move.content = board[i][j];
                possibleMoves.add(move);
            } else if(Character.isLowerCase(board[i][j])){
                MoveType move = new MoveType();
                move.oldSpace = new int[]{row, col};
                move.newSpace = new int[]{i, j};
                move.piece = board[row][col];
                move.content = board[i][j];
                possibleMoves.add(move);
                break;
            } else {
                break;
            }
            i--;
            j++;
        }
    }
    //endregion

    //region Black Bishop moves
    public void blackBishopMoves(int row, int col){
        blackBishopLeftDiagonalForward(row, col);
        blackBishopRightDiagonalForward(row, col);
        blackBishopLeftDiagonalBack(row, col);
        blackBishopRightDiagonalBack(row, col);
    }

    public void blackBishopLeftDiagonalBack(int row, int col){
        int i = row + 1;
        int j = col - 1;
        while(i < 8 && j >= 0){
            if(board[i][j] == ' '){
                MoveType move = new MoveType();
                move.oldSpace = new int[]{row, col};
                move.newSpace = new int[]{i, j};
                move.piece = board[row][col];
                move.content = board[i][j];
                possibleMoves.add(move);
            } else if(Character.isUpperCase(board[i][j])){
                MoveType move = new MoveType();
                move.oldSpace = new int[]{row, col};
                move.newSpace = new int[]{i, j};
                move.piece = board[row][col];
                move.content = board[i][j];
                possibleMoves.add(move);
                break;
            } else {
                break;
            }
            i++;
            j--;
        }
    }

    public void blackBishopRightDiagonalBack(int row, int col){
        int i = row + 1;
        int j = col + 1;
        while(i < 8 && j < 8){
            if(board[i][j] == ' '){
                MoveType move = new MoveType();
                move.oldSpace = new int[]{row, col};
                move.newSpace = new int[]{i, j};
                move.piece = board[row][col];
                move.content = board[i][j];
                possibleMoves.add(move);
            } else if(Character.isUpperCase(board[i][j])){
                MoveType move = new MoveType();
                move.oldSpace = new int[]{row, col};
                move.newSpace = new int[]{i, j};
                move.piece = board[row][col];
                move.content = board[i][j];
                possibleMoves.add(move);
                break;
            } else {
                break;
            }
            i++;
            j++;
        }
    }

    public void blackBishopLeftDiagonalForward(int row, int col){
        int i = row - 1;
        int j = col - 1;
        while(i >= 0 && j >= 0){
            if(board[i][j] == ' '){
                MoveType move = new MoveType();
                move.oldSpace = new int[]{row, col};
                move.newSpace = new int[]{i, j};
                move.piece = board[row][col];
                move.content = board[i][j];
                possibleMoves.add(move);
            } else if(Character.isUpperCase(board[i][j])){
                MoveType move = new MoveType();
                move.oldSpace = new int[]{row, col};
                move.newSpace = new int[]{i, j};
                move.piece = board[row][col];
                move.content = board[i][j];
                possibleMoves.add(move);
                break;
            } else {
                break;
            }
            i--;
            j--;
        }
    }

    public void blackBishopRightDiagonalForward(int row, int col){
        int i = row - 1;
        int j = col + 1;
        while(i >= 0 && j < 8){
            if(board[i][j] == ' '){
                MoveType move = new MoveType();
                move.oldSpace = new int[]{row, col};
                move.newSpace = new int[]{i, j};
                move.piece = board[row][col];
                move.content = board[i][j];
                possibleMoves.add(move);
            } else if(Character.isUpperCase(board[i][j])){
                MoveType move = new MoveType();
                move.oldSpace = new int[]{row, col};
                move.newSpace = new int[]{i, j};
                move.piece = board[row][col];
                move.content = board[i][j];
                possibleMoves.add(move);
                break;
            } else {
                break;
            }
            i--;
            j++;
        }
    }
    //endregion

    //region White Rook moves
    public void whiteRookMoves(int row, int col){
        whiteRookMoveRight(row, col);
        whiteRookMoveLeft(row, col);
        whiteRookMoveForward(row, col);
        whiteRookMoveBack(row, col);
    }

    public void whiteRookMoveRight(int row, int col){
        int i = col + 1;
        while(i < 8){
            if(board[row][i] == ' '){
                MoveType move = new MoveType();
                move.oldSpace = new int[]{row, col};
                move.newSpace = new int[]{row, i};
                move.piece = board[row][col];
                move.content = board[row][i];
                possibleMoves.add(move);
            } else if(Character.isLowerCase(board[row][i])){
                MoveType move = new MoveType();
                move.oldSpace = new int[]{row, col};
                move.newSpace = new int[]{row, i};
                move.piece = board[row][col];
                move.content = board[row][i];
                possibleMoves.add(move);
                break;
            } else {
                break;
            }
            i++;
        }
    }

    public void whiteRookMoveLeft(int row, int col){
        int i = col - 1;
        while(i >= 0){
            if(board[row][i] == ' '){
                MoveType move = new MoveType();
                move.oldSpace = new int[]{row, col};
                move.newSpace = new int[]{row, i};
                move.piece = board[row][col];
                move.content = board[row][i];
                possibleMoves.add(move);
            } else if(Character.isLowerCase(board[row][i])){
                MoveType move = new MoveType();
                move.oldSpace = new int[]{row, col};
                move.newSpace = new int[]{row, i};
                move.piece = board[row][col];
                move.content = board[row][i];
                possibleMoves.add(move);
                break;
            } else {
                break;
            }
            i--;
        }
    }

    public void whiteRookMoveForward(int row, int col){
        int i = row - 1;
        while(i >= 0){
            if(board[i][col] == ' '){
                MoveType move = new MoveType();
                move.oldSpace = new int[]{row, col};
                move.newSpace = new int[]{i, col};
                move.piece = board[row][col];
                move.content = board[i][col];
                possibleMoves.add(move);
            } else if(Character.isLowerCase(board[i][col])){
                MoveType move = new MoveType();
                move.oldSpace = new int[]{row, col};
                move.newSpace = new int[]{i, col};
                move.piece = board[row][col];
                move.content = board[i][col];
                possibleMoves.add(move);
                break;
            } else {
                break;
            }
            i--;
        }
    }

    public void whiteRookMoveBack(int row, int col){
        int i = row + 1;
        while(i < 8){
            if(board[i][col] == ' '){
                MoveType move = new MoveType();
                move.oldSpace = new int[]{row, col};
                move.newSpace = new int[]{i, col};
                move.piece = board[row][col];
                move.content = board[i][col];
                possibleMoves.add(move);
            } else if(Character.isLowerCase(board[i][col])){
                MoveType move = new MoveType();
                move.oldSpace = new int[]{row, col};
                move.newSpace = new int[]{i, col};
                move.piece = board[row][col];
                move.content = board[i][col];
                possibleMoves.add(move);
                break;
            } else {
                break;
            }
            i++;
        }
    }
    //endregion

    //region Black Rook moves
    public void blackRookMoves(int row, int col){
        blackRookMoveRight(row, col);
        blackRookMoveLeft(row, col);
        blackRookMoveForward(row, col);
        blackRookMoveBack(row, col);
    }

    public void blackRookMoveRight(int row, int col){
        int i = col + 1;
        while(i < 8){
            if(board[row][i] == ' '){
                MoveType move = new MoveType();
                move.oldSpace = new int[]{row, col};
                move.newSpace = new int[]{row, i};
                move.piece = board[row][col];
                move.content = board[row][i];
                possibleMoves.add(move);
            } else if(Character.isUpperCase(board[row][i])){
                MoveType move = new MoveType();
                move.oldSpace = new int[]{row, col};
                move.newSpace = new int[]{row, i};
                move.piece = board[row][col];
                move.content = board[row][i];
                possibleMoves.add(move);
                break;
            } else {
                break;
            }
            i++;
        }
    }

    public void blackRookMoveLeft(int row, int col){
        int i = col - 1;
        while(i >= 0){
            if(board[row][i] == ' '){
                MoveType move = new MoveType();
                move.oldSpace = new int[]{row, col};
                move.newSpace = new int[]{row, i};
                move.piece = board[row][col];
                move.content = board[row][i];
                possibleMoves.add(move);
            } else if(Character.isUpperCase(board[row][i])){
                MoveType move = new MoveType();
                move.oldSpace = new int[]{row, col};
                move.newSpace = new int[]{row, i};
                move.piece = board[row][col];
                move.content = board[row][i];
                possibleMoves.add(move);
                break;
            } else {
                break;
            }
            i--;
        }
    }

    public void blackRookMoveForward(int row, int col){
        int i = row - 1;
        while(i >= 0){
            if(board[i][col] == ' '){
                MoveType move = new MoveType();
                move.oldSpace = new int[]{row, col};
                move.newSpace = new int[]{i, col};
                move.piece = board[row][col];
                move.content = board[i][col];
                possibleMoves.add(move);
            } else if(Character.isUpperCase(board[i][col])){
                MoveType move = new MoveType();
                move.oldSpace = new int[]{row, col};
                move.newSpace = new int[]{i, col};
                move.piece = board[row][col];
                move.content = board[i][col];
                possibleMoves.add(move);
                break;
            } else {
                break;
            }
            i--;
        }
    }

    public void blackRookMoveBack(int row, int col){
        int i = row + 1;
        while(i < 8){
            if(board[i][col] == ' '){
                MoveType move = new MoveType();
                move.oldSpace = new int[]{row, col};
                move.newSpace = new int[]{i, col};
                move.piece = board[row][col];
                move.content = board[i][col];
                possibleMoves.add(move);
            } else if(Character.isUpperCase(board[i][col])){
                MoveType move = new MoveType();
                move.oldSpace = new int[]{row, col};
                move.newSpace = new int[]{i, col};
                move.piece = board[row][col];
                move.content = board[i][col];
                possibleMoves.add(move);
                break;
            } else {
                break;
            }
            i++;
        }
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
