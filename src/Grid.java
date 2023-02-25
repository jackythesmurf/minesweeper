import java.awt.font.NumericShaper;
import java.util.*;

public class Grid {
    private static int width;
    private static int numOfMines;

    public static final String ANSI_CYAN = "\u001B[36m";

    private static Map<ArrayList<Integer>, Tiles> listTilesMap = new HashMap<>();

    public Grid(int width, int numOfMines) {
        Grid.width = width;
        Grid.numOfMines = numOfMines;
    }

    public static int getNumOfMines() {
        return numOfMines;
    }

    public static int getWidth() {
        return width;
    }

    public static Map<ArrayList<Integer>, Tiles> getListTilesMap() {
        return listTilesMap;
    }

    // fill flood recursive algo
    public static void floodFill(ArrayList<Integer> startingLoc) {
        // Check if the starting location is null or is not an instance of Numbered
        if (listTilesMap.get(startingLoc) == null || !(listTilesMap.get(startingLoc) instanceof Numbered)) {
            return;
        }
        // Get the current tile at the starting location
        Numbered currentTile = (Numbered) listTilesMap.get(startingLoc);
        // Check if the current tile has already been revealed or is a number
        if (currentTile.getRevealed() || currentTile.getSurroundedMines() != 0) {
            currentTile.setIsRevealed();
            return;
        }
        // Reveal the current tile and update its image
        currentTile.setIsRevealed();
        currentTile.setHiddenImg(" ");
        // Check if the current tile has any surrounding mines
        if (currentTile.getSurroundedMines() == 0) {
            // Recursive calls to reveal the adjacent tiles
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    ArrayList<Integer> locateTile = new ArrayList<>();
                    locateTile.add(startingLoc.get(0) + i - 1);
                    locateTile.add(startingLoc.get(1) + j - 1);
                    if (!locateTile.equals(startingLoc)) {
                        floodFill(locateTile);
                    }
                }
            }
        }
    }

    public static void loopingArray(boolean toDraw, boolean toFillTiles) {
        for (int i = width; i > 0; i--) {
            for (int j = 1; j < width + 1; j++) {
                Integer finalI = i;
                Integer finalJ = j;
                ArrayList<Integer> checkLoc = new ArrayList<Integer>() {
                    { // J and I need to be reversed, so grid displays x as horn and y as vert
                        // DON'T adjust for reverse in other areas !
                        add(finalJ);
                        add(finalI);
                    }
                };
                if (toFillTiles) {
                    if (listTilesMap.get(checkLoc) == null)
                        listTilesMap.put(checkLoc, new Numbered(checkLoc));
                }
                if (toDraw)
                    Tiles.drawTileByType(checkLoc, listTilesMap);
            }
            if (toDraw)
                System.out.print((i) + "\n");
        }
        if (toDraw) {
            for (int i = 1; i < width + 1; i++) {
                if (i >= 10)
                    System.out.print(i + " ");
                else {
                    System.out.print(" " + i + " ");
                }
            }
        }
    }

    public static void drawGrid(int x, int y) {

        floodFill(new ArrayList<>() {
            {
                add(x);
                add(y);
            }
        });
        loopingArray(true, false);
    }



    public void createGrid() {
        Mine.generateMines(listTilesMap, width, numOfMines);
        loopingArray(false, true);
        Numbered.generateNumbered(listTilesMap);
        loopingArray(true, false);
    }
}
