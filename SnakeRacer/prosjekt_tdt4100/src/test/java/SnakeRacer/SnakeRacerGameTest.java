package SnakeRacer;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class SnakeRacerGameTest {
    
    @Test
    public void testGameConstructor() {

        SnakeRacerGame game = new SnakeRacerGame();

        int x = game.getGoalPos().get(0);
        int y = game.getGoalPos().get(1);

        assertTrue((x >= 0 && x < 16), "Kolonne skal være mellom 0 og 15.");
        assertTrue((y >= 0 && y < 16), "Rad må være mellom 0 og 15.");
    }
}
