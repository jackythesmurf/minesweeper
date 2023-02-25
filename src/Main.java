import java.rmi.UnexpectedException;
import java.sql.SQLOutput;
import java.util.*;

public class Main {
    private static int round = 1;
    private static int width = 15;
    private static int numOfMines = 30;
    public static void onStart(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Config your Minesweepers, follow the prompts");

        System.out.println("Select level of difficulty (type easy, or hard).");
        String difficulty = "not yet selected";
        while (!difficulty.equals("easy") && !difficulty.equals("hard")) {
            try {
                difficulty = scanner.next();
                difficulty = difficulty.toLowerCase().strip();
                if (!difficulty.equals("easy") && !difficulty.equals("hard")) {
                    throw new Exception("Not a valid String");
                }
            } catch (Exception e) {
                System.out.println("Please type easy or hard.");
            }
        }

        if (difficulty.equals("easy")) {width = 10; numOfMines = 20;}
        if (difficulty.equals("hard")) {width = 15; numOfMines = 30;}

        Grid grid = new Grid(width, numOfMines);
        // called once to fill grid and draw grid
        // use drawGrid to redraw subsequent renders !
        System.out.println("\nRound - " + "Start !");
        grid.createGrid();
        System.out.print("\n");
    }

    public static void main(String[] args) {
        System.out.println("\nMINESWEEPER IN THE COMMAND LINE");
        onStart();

        boolean didLose = false;
        while (!didLose){
            switch (Tiles.checkWin(Grid.getListTilesMap())) {
                case "CONTINUE" -> {
                    try {
                        Scanner scanner = new Scanner(System.in);
                        System.out.print("\nEnter X cord: ");
                        int X = scanner.nextInt();
                        System.out.print("Enter Y cord: ");
                        int Y = scanner.nextInt();
                        if ( X > width || Y > width || X <= 0 || Y <= 0 ) { throw new Exception(); };
                        System.out.println("");
                        System.out.println("\u001B[41m" + " * ".repeat(width) + "\u001B[0m");
                        System.out.println("\nRound - " + (round));
                        if (Tiles.checkRevealMine(new ArrayList<>(){{add(X);add(Y);}})){
                            Grid.drawGrid(X, Y); System.out.println("\n\nBOOM ! You lose"); didLose = true; break;}
                        Grid.drawGrid(X, Y);
                        System.out.println("");
                    } catch (InputMismatchException e) {
                        System.out.println("Integers only ! Try Again.");
                    } catch (Exception e) {
                        System.out.println("Input number has to stay within boundary of 1 - " + width);
                    }
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
