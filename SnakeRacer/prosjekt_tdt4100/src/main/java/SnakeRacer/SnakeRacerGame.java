package SnakeRacer;

import java.util.List;
import java.util.Arrays;
import java.util.Random;

public class SnakeRacerGame {
    
    // Variabler for å styre posisjonen til målet
    private int goalCol;
    private int goalRow;

    // Initialiserer konstruktører
    Random random = new Random();
    Player player = new Player();

    // Konstruktør som starter spillet med mål på tilfeldig posisjon
    public SnakeRacerGame() {

        goalCol = random.nextInt(16);
        goalRow = random.nextInt(16);

        setGoalPos(goalCol, goalRow);
    }

    public void moveGoal(GameController controller) {

        // Sjekker om spiller og mål har samme posisjon
        if (player.getPlayerPos().get(0) == getGoalPos().get(0) && player.getPlayerPos().get(1) == getGoalPos().get(1)) {
            setGoalPos(random.nextInt(16), random.nextInt(16)); // Flytter målet til ny tilfeldig posisjon

            controller.updateScore();
        }
    }

    // Getter-metode for å hente ut posisjonen til målet
    public List<Integer> getGoalPos() {
        return Arrays.asList(goalCol, goalRow);
    }

    // Setter-metode for å sette posisjoner
    public void setGoalPos(int x, int y) {

        this.goalCol = x;
        this.goalRow = y;
    }

}
