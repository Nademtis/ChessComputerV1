import java.util.Arrays;

public class MoveType {


    public MoveType(){

    }

    public int[] newSpace;
    public int[] oldSpace;
    public char piece;
    public char content;

    @Override
    public String toString() {
        return "MoveType{" +
                "newSpace=" + Arrays.toString(newSpace) +
                ", oldSpace=" + Arrays.toString(oldSpace) +
                ", piece=" + piece +
                ", content=" + content +
                '}';
    }
}
