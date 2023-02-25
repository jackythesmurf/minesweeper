import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.*;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    public static void setUserInput(String userInput){
        InputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
    }

    @Test
    void onStart() {

    }


    @Test
    void selectDifficultyEasy() {
        ArrayList<String> validEasyInputs = new ArrayList<>(){{
            add("easy"); add("EASY"); add(" easy "); add(" Easy ");
            }
        };
        for (String userInput: validEasyInputs){
            setUserInput(userInput);
            Main.selectDifficulty();
            assertEquals(Main.getWidth(), 10);
            assertEquals(Main.getNumOfMines(), 20);
        }
    }
    @Test
    void selectDifficultyHard() {
        ArrayList<String> validEasyInputs = new ArrayList<>(){{
            add("hard"); add("HARD"); add(" hard "); add(" Hard ");
            }
        };
        for (String userInput: validEasyInputs){
            setUserInput(userInput);
            Main.selectDifficulty();
            assertEquals(Main.getWidth(), 15);
            assertEquals(Main.getNumOfMines(), 30);
        }
    }
    @Test
    void selectDifficultyInvalid() {
        ArrayList<String> validEasyInputs = new ArrayList<>(){{
            add("123"); add("easy."); add(""); add(" ");
            }
        };
        for (String userInput: validEasyInputs){
            setUserInput(userInput);
            assertThrows(Exception.class, Main::selectDifficulty);
        }

    }

    @Test
    void testOnStart() {
    }

    @Test
    void getCordFromInput() {
    }

    @Test
    void checkMine() {
    }

    @Test
    void main() {
    }
}
