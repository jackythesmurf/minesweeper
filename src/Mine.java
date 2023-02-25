import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class Mine extends Tiles {

    public Mine(ArrayList<Integer> location) {
        super(location, false, "\u001B[31m" + " x " + "\u001B[0m");
    }

    public static void generateMines(Map<ArrayList<Integer>, Tiles> listTilesMap, int width, int numOfMines) {
        Random random = new Random();
        while (numOfMines > 0) {
            ArrayList<Integer> mineLocation = new ArrayList<>();
            // grid renders nums > 0
            int randI = random.nextInt((width)) + 1;
            int randY = random.nextInt((width)) + 1;
            mineLocation.add(randI);
            mineLocation.add(randY);
            // ensure mine location are distinct
            if (listTilesMap.get(mineLocation) != null)
                continue;
            // if distinct add to mine to list
            listTilesMap.put(mineLocation, new Mine(mineLocation));
            numOfMines--;
        }
    }
}
