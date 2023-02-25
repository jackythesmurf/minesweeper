import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.*;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    public static void setUserInput(String userInput) {
        InputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
    }

    @Test
    void selectDifficultyEasy() throws Main.InvalidOption {
        ArrayList<String> validEasyInputs = new ArrayList<>() {
            {
                add("easy");
                add("EASY");
                add(" easy ");
                add(" Easy ");
            }
        };
        for (String userInput : validEasyInputs) {
            setUserInput(userInput);
            Main.selectDifficulty();
            assertEquals(Main.getWidth(), 10);
            assertEquals(Main.getNumOfMines(), 20);
        }
    }

    @Test
    void selectDifficultyHard() throws Main.InvalidOption {
        ArrayList<String> validEasyInputs = new ArrayList<>() {
            {
                add("hard");
                add("HARD");
                add(" hard ");
                add(" Hard ");
            }
        };
        for (String userInput : validEasyInputs) {
            setUserInput(userInput);
            Main.selectDifficulty();
            assertEquals(Main.getWidth(), 15);
            assertEquals(Main.getNumOfMines(), 30);
        }
    }

    @Test
    void selectDifficultyInvalid() {
        ArrayList<String> validEasyInputs = new ArrayList<>() {
            {
                add("123");
                add("easy.");
                add("hard23");
                add("easyhard");
            }
        };
        for (String userInput : validEasyInputs) {
            setUserInput(userInput);
            assertEquals("Not a valid String, please type 'easy' or 'hard'",
                    assertThrows(Main.InvalidOption.class, Main::selectDifficulty).getMessage());
        }
    }

    @Test
    void testOnStart() {
        Main.onStart();
        assertEquals(15, Grid.getWidth());
        assertEquals(30, Grid.getNumOfMines());
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
