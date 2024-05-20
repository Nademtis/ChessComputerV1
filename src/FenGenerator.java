public class FenGenerator {
    public static void main(String[] args) {
        char[][] board = new char[8][8];

        StringBuilder fenBuilder = new StringBuilder();
        for (int row = 7; row >= 0; row--) {
            int emptyCount = 0;
            for (int col = 0; col < 8; col++) {
                char piece = board[row][col];
                if (piece == ' ') {
                    emptyCount++;
                } else {
                    if (emptyCount > 0) {
                        fenBuilder.append(emptyCount);
                        emptyCount = 0;
                    }
                    fenBuilder.append(piece);
                }
            }
            if (emptyCount > 0) {
                fenBuilder.append(emptyCount);
            }
            if (row > 0) {
                fenBuilder.append('/');
            }
        }

        String fenBoard = fenBuilder.toString();
        String activeColor = "w"; // Hvis hvis er aktiv
        String castlingAvailability = "KQkq"; // Til Castling
        String enPassantTarget = "-"; // til Croissant
        int halfMoveClock = 0;
        int fullMoveNumber = 1;

        String fenString = String.format("%s %s %s %s %d %d", fenBoard, activeColor, castlingAvailability,
                enPassantTarget, halfMoveClock, fullMoveNumber);
        System.out.println("FEN String: " + fenString);
    }

    public static String generateFen(char[][] board, boolean isWhite, int halvMoveCounter, int fullMoveCounter){
        StringBuilder fenString = new StringBuilder();

        for (int row = 0; row < 8; row++) {
            int emptyCount = 0;
            for (int col = 0; col < 8; col++) {
                char piece = board[row][col];
                if (piece == ' ') {
                    emptyCount++;
                } else {
                    if (emptyCount > 0) {
                        fenString.append(emptyCount);
                        emptyCount = 0;
                    }
                    fenString.append(piece);
                }
            }
            if (emptyCount > 0) {
                fenString.append(emptyCount);
            }
            if(row < 7) {
                fenString.append('/');
            }
        }

        fenString.append(" ");
        fenString.append(isWhite ? "w" : "b");
        fenString.append(" ");
        fenString.append("KQkq"); //todo Castling availability
        fenString.append(" ");
        fenString.append("-"); //todo En passant target
        fenString.append(" ");
        fenString.append(halvMoveCounter); //todo halvMoveCounter
        fenString.append(" ");
        fenString.append(fullMoveCounter);  //todo fullMoveCounter
        return fenString.toString();
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

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if(board[i][j] == '\u0000'){
                    board[i][j] = ' ';
                }
            }
        }

        return board;
    }


}
