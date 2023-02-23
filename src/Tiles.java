import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class Tiles {
    private ArrayList<Integer> location = new ArrayList<>();
    private Boolean isRevealed;
    private String hiddenImg;
    public Tiles( ArrayList<Integer> location, Boolean isRevealed, String hiddenImg) {
        this.location = location;
        this.isRevealed = isRevealed;
        this.hiddenImg = hiddenImg;
    }
    public ArrayList<Integer> getLocation() {
        return location;
    }
    public Boolean getRevealed() {
        return isRevealed;
    }
    public String getHiddenImg() {
        return hiddenImg;
    }
    public void setHiddenImg(String str){
        this.hiddenImg = " " + str + " ";
    }
    public void setIsRevealed(){
        this.isRevealed = true;
    }
    public static void drawTileByType(ArrayList<Integer> checkLoc, Map<ArrayList<Integer>, Tiles> listTilesMap){
        if (listTilesMap.get(checkLoc) != null) {
            if (listTilesMap.get(checkLoc).getRevealed()) {
                System.out.print(listTilesMap.get(checkLoc).getHiddenImg());
            } else {
                System.out.print("\u001B[35m" + " * " + "\u001B[0m");
            }
        }
    }
    public static String checkWin(Map<ArrayList<Integer>, Tiles> listTilesMap){
        int hasRevealed = 0;
        int totalNumbered = 0;
        for (ArrayList<Integer> key : listTilesMap.keySet()) {
            if (listTilesMap.get(key).getClass().equals(Numbered.class)){
                totalNumbered++;
                if (listTilesMap.get(key).isRevealed) hasRevealed++;
            }
        }
        if (hasRevealed == totalNumbered) return "WIN";
        return "CONTINUE";
    }
    public static boolean checkRevealMine(ArrayList<Integer> checkLoc){
        if (Grid.getListTilesMap().get(checkLoc).getClass().equals(Mine.class)){
            Grid.getListTilesMap().get(checkLoc).setIsRevealed();
            return true;
        }
        return false;
    }
}
