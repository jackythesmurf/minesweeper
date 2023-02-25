
import java.util.*;

public class Main {
    static class UserInputOutOfBounds extends Exception {
        public UserInputOutOfBounds(String message) {
            super(message);
        }
    }

    static class InvalidOption extends Exception {
        public InvalidOption(String message) {
            super(message);
        }
    }

    private static int round = 1;
    private static int width = 15;
    private static int numOfMines = 30;

    public static int getNumOfMines() {
        return numOfMines;
    }

    public static int getRound() {
        return round;
    }

    public static int getWidth() {
        return width;
    }

    public static void selectDifficulty() throws InvalidOption {
        Scanner scanner = new Scanner(System.in);
        String difficulty = "not yet selected";
        while (!difficulty.equals("easy") && !difficulty.equals("hard")) {
            difficulty = scanner.next();
            difficulty = difficulty.toLowerCase().strip();
            if (!difficulty.equals("easy") && !difficulty.equals("hard")) {
                throw new InvalidOption("Not a valid String, please type 'easy' or 'hard'");
            }
        }
        if (difficulty.equals("easy")) {
            width = 10;
            numOfMines = 20;
        }
        if (difficulty.equals("hard")) {
            width = 15;
            numOfMines = 30;
        }
    }

    public static void onStart() {
        Grid grid = new Grid(width, numOfMines);
        // called once to fill grid and draw grid
        // use drawGrid to redraw subsequent renders !
        System.out.println("\nRound - " + "Start !");
        grid.createGrid();
        System.out.print("\n");
    }

    public static ArrayList<Integer> getCordFromInput() throws InputMismatchException, UserInputOutOfBounds {

        Scanner scanner = new Scanner(System.in);
        System.out.print("\nEnter X cord: ");
        int X = scanner.nextInt();
        System.out.print("Enter Y cord: ");
        int Y = scanner.nextInt();
        if (X > width || Y > width || X <= 0 || Y <= 0) {
            throw new UserInputOutOfBounds("Input number has to stay within boundary of 1 - " + width);
        }
        return new ArrayList<>() {
            {
                add(X);
                add(Y);
            }
        };

    }

    public static boolean checkMine(int X, int Y) {
        if (Tiles.checkRevealMine(new ArrayList<>() {
            {
                add(X);
                add(Y);
            }
        })) {
            Grid.drawGrid(X, Y);
            System.out.println("\n\nBOOM ! You lose");
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println("\nMINESWEEPER IN THE COMMAND LINE");
        System.out.println("Config your Minesweepers, follow the prompts");
        System.out.println("Select level of difficulty (type easy, or hard).");

        while (true) {
            try {
                selectDifficulty();
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        onStart();

        boolean didLose = false;
        while (!didLose) {
            switch (Tiles.checkWin(Grid.getListTilesMap())) {
                case "CONTINUE" -> {

                    // get and validate user coordinate input
                    ArrayList<Integer> cordFromInput;
                    while (true) {
                        try {
                            cordFromInput = getCordFromInput();
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Integers only please !");
                        } catch (UserInputOutOfBounds e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    // prints no of round and a bar for each new round
                    System.out.println("");
                    System.out.println("\u001B[41m" + " * ".repeat(width) + "\u001B[0m");
                    System.out.println("\nRound - " + (round));

                    // check if user has stepped on mine
                    didLose = checkMine(cordFromInput.get(0), cordFromInput.get(1));
                    if (didLose)
                        continue;
                    Grid.drawGrid(cordFromInput.get(0), cordFromInput.get(1));
                    System.out.println("");
                    round++;
                }
                case "WIN" -> {
                    System.out.println("\nYou win !");
                    return;
                }

            }
        }
    }
}
