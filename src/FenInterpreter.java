public class FenInterpreter {
    public static void main(String[] args) {
        String fenString = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
        char[][] board = interpretFEN(fenString);
        printBoard(board);
    }

    public static char[][] interpretFEN(String fenString) {
        String[] parts = fenString.split(" ");
        String[] rows = parts[0].split("/");
        char[][] board = new char[8][8];

        for (int i = 0; i < 8; i++) {
            int index = 0;
            for (int j = 0; j < rows[i].length(); j++) {
                char c = rows[i].charAt(j);
                if (Character.isDigit(c)) {
                    index += Character.getNumericValue(c);
                } else {
                    board[i][index] = c;
                    index++;
                }
            }
        }

        return board;
    }

    public static void printBoard(char[][] board) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
