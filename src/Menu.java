import java.util.Arrays;
import java.util.Scanner;

public class Menu {

    Scanner in = new Scanner(System.in);
    private String menuHeader;
    private String leadText;
    private String[] menuItems;
    private boolean keepGoing = true;
    Board board = new Board();

    public Menu(String menuHeader, String leadText, String[] menuItems) {
        this.menuHeader = menuHeader;
        this.leadText = leadText;
        this.menuItems = menuItems;
    }

    public void printMenu() {
        System.out.println(menuHeader);
        System.out.println(Arrays.toString(menuItems)
                .replace("[", "")
                .replace("]", "")
                .replace(", ", "\n"));
        System.out.println(leadText);
    }

    public int readChoice() {
        int menuChoice;
        boolean menuIsRunning;

        do {
            menuChoice = Integer.parseInt(in.nextLine());

            if (menuChoice > 0 && menuChoice <= menuItems.length) {
                menuIsRunning = false;
            } else if (menuChoice == 9) {
                System.out.println("Shutting down...");
                menuIsRunning = false;
            } else {
                printMenu();
                System.out.println(">> Please enter valid input <<");
                menuIsRunning = true;
            }
        } while (menuIsRunning);
        return menuChoice;
    }


    public void menuContent() {
        int choice = readChoice();

        switch (choice) {
            case 1:
                System.out.println("Start Game");
                board.gameLoop();
                break;
            case 2:
                System.out.println("Option 2");
                board.computerVSComputer();
                break;
            case 3:
                System.out.println("Option 3");
                break;
            case 4:
                System.out.println("Option 4");
                break;
            case 9:
                keepGoing = false;
                break;
            default:
                System.out.println(">> Input a value from the menu <<");
                break;

        }
    }

    public void fullMenuPrint() {
        while (keepGoing) {
            printMenu();
            menuContent();
        }
    }
}