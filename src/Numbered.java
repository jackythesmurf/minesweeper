import java.util.ArrayList;
import java.util.Map;

public class Numbered extends Tiles{
    private int surroundedMines = 0;
    private String[] colourList = new String[] {"\u001B[34m", "\u001B[32m", "\u001B[33m"};

    public Numbered(ArrayList<Integer> location) {
        super(location, false, " - ");
    }
    public int getSurroundedMines() {
        return surroundedMines;
    }
    public void increment(){
        surroundedMines++;
        String colour = switch (surroundedMines) {
            case 1 -> colourList[0];
            case 2 -> colourList[1];
            default -> colourList[2];
        };
        setHiddenImg(colour + String.valueOf(surroundedMines) + "\u001B[0m");
    }
    public static void generateNumbered(Map<ArrayList<Integer>, Tiles> listTilesMap){
        for (ArrayList<Integer> key : listTilesMap.keySet()) {
            // Increment all surrounding Numbered tiles once a Mine tile is found
            if (listTilesMap.get(key).getClass().equals(Mine.class)){
                for (int i = 0; i < 3 ; i++) {
                    for (int j = 0; j < 3; j++) {
                        ArrayList<Integer> locateTile = new ArrayList<>();
                        locateTile.add(key.get(0) + i - 1);
                        locateTile.add(key.get(1) + j - 1);
                        // if null means out of range and it is ignored
                        if(listTilesMap.get(locateTile) != null){
                            if (listTilesMap.get(locateTile).getClass().equals(Numbered.class)) {
                                Numbered surroundingTile = (Numbered) listTilesMap.get(locateTile);
                                surroundingTile.increment();
                            }
                        }

                    }
                }
            }
        }
    }
}
