import java.sql.SQLOutput;
import java.util.*;

public class Main {
    private static int round = 1;
    private static int width = 15;
    public static void onStart(){
        Grid grid = new Grid(width, 30);
        // called once to fill grid and draw grid
        // use drawGrid to redraw subsequent renders !
        grid.createGrid();
        System.out.println("");
    }

    public static void main(String[] args) {
        System.out.println("\nMINESWEEPER IN THE COMMAND LINE");
        System.out.println("Follow the prompts, and enter the X, Y coordinates.");
        System.out.println("\nRound - " + "Start !");
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
                        System.out.println("");
                        System.out.println("\u001B[41m" + " * ".repeat(width) + "\u001B[0m");
                        System.out.println("\nRound - " + (round));
                        if (Tiles.checkRevealMine(new ArrayList<>(){{add(X);add(Y);}})){
                            Grid.drawGrid(X, Y); System.out.println("\n\nYou lose!"); didLose = true; break;}
                        Grid.drawGrid(X, Y);
                        System.out.println("");
                    } catch (InputMismatchException e) {
                        System.out.println("Integers only ! Try Again.");
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
