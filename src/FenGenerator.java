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
}
