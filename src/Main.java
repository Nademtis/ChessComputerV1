public class Main {
    public static void main(String[] args) {
        Board board = new Board();

        Menu menu = new Menu("CHESS MENU\n", "Enter an option: ", new String[]{"" +
                "1. Start Game",
                "2. Computer vs Computer",
                "3. Option 3",
                "4. Option 4",
                "9. Exit\n"} );

        menu.fullMenuPrint();
        menu.readChoice();
    }
}
