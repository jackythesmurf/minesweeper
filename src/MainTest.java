import org.junit.jupiter.api.*;

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
    void getCordFromInputValid() throws Main.UserInputOutOfBounds {
        setUserInput("1\n1");
        ArrayList<Integer> cordTest1 = new ArrayList<>() {
            {
                add(1);
                add(1);
            }
        };
        ArrayList<Integer> returnedCord = Main.getCordFromInput();
        assertEquals(cordTest1, returnedCord);

    }

    @Test
    void getCordFromInputShouldThrowInputError() throws Main.UserInputOutOfBounds {
        setUserInput("str");
        assertThrows(InputMismatchException.class, Main::getCordFromInput);
    }

    @Test
    void getCordFromInputShouldThrowOutOfBoundsError() throws Main.UserInputOutOfBounds {

        ArrayList<Integer> smallBoundary = new ArrayList<>() {
            {
                add(0);
                add(5);
            }
        };
        ArrayList<Integer> bigBoundary = new ArrayList<>() {
            {
                add(Main.getWidth() + 1);
                add(0);
            }
        };
        ArrayList<Integer> negative = new ArrayList<>() {
            {
                add(-1);
                add(0);
            }
        };

        ArrayList<ArrayList<Integer>> outOfBoundInputs = new ArrayList<>() {
            {
                add(smallBoundary);
                add(bigBoundary);
                add(negative);
            }
        };

        for (ArrayList<Integer> invalidInputs : outOfBoundInputs) {
            setUserInput(String.valueOf(invalidInputs.get(0)) + "\n" + String.valueOf(invalidInputs.get(1)));
            assertEquals("Input number has to stay within boundary of 1 - " + Main.getWidth(),
                    assertThrows(Main.UserInputOutOfBounds.class, Main::getCordFromInput).getMessage());
        }
    }

    // should assert no errors
    @Test

    void sampleGame() {

        setUserInput("1\n1");
        Main.repeatGame(true);

    }
 

}
