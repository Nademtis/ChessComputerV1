import java.util.Arrays;

public class MoveType {


    public MoveType(){

    }

    public int[] newSpace;
    public int[] oldSpace;
    public char piece;
    public char content;

    public boolean promotion = false;
    public char promotionPiece;

    @Override
    public String toString() {
        if(promotion){
            return "MoveType{" +
                    "newSpace=" + Arrays.toString(newSpace) +
                    ", oldSpace=" + Arrays.toString(oldSpace) +
                    ", piece=" + piece +
                    ", content=" + content +
                    ", promotionPiece=" + promotionPiece +
                    '}';
        } else {
            return "MoveType{" +
                    "newSpace=" + Arrays.toString(newSpace) +
                    ", oldSpace=" + Arrays.toString(oldSpace) +
                    ", piece=" + piece +
                    ", content=" + content +
                    '}';
        }
    }
}
