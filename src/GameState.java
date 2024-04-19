import java.util.ArrayList;

public class GameState {

    BoardState board;

    ArrayList<BoardState> possibleBoards = new ArrayList<>();
    ArrayList<GameState> nextStates = new ArrayList<>();

    boolean max = true;

    int depth = 0;

    public GameState(BoardState board, boolean max, int depth){
        this.board = board;
        this.max = max;
        this.depth = depth;
    }

    public void generatePossibleBoards(){

    }

    public void evaluate(){

    }

    boolean isWhite;
    /*boolean BlackCastle;
    boolean BlackCastleLong;
    boolean WhiteCastle;
    boolean WhiteCastleLong;
    boolean enPassant; */
    int score;

}
