package SnakeRacer;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Player {
    
    // Posisjonen til spiller
    private int playerCol;
    private int playerRow;

    Random random = new Random();

    public Player() {

        playerCol = random.nextInt(16);
        playerRow = random.nextInt(16);

        setPlayerPos(playerCol, playerRow);
    }

    // Setter- og getter-metoder for posisjonen til spiller-objektet
    public List<Integer> getPlayerPos() {
        return Arrays.asList(playerCol, playerRow);
    }

    public void setPlayerPos(int x, int y) {

        this.playerCol = x;
        this.playerRow = y;
    }

    // Bevegelse-metoder
    public void moveUp() {
        setPlayerPos(playerCol - 1, playerRow);
        checkWallCollission();
    }

    public void moveDown() {
        setPlayerPos(playerCol + 1, playerRow);
        checkWallCollission();
    }

    public void moveLeft() {
        setPlayerPos(playerCol, playerRow - 1);
        checkWallCollission();
    }

    public void moveRight() {
        setPlayerPos(playerCol, playerRow + 1);
        checkWallCollission();
    }

    // Håndterer når spilleren treffer veggen
    private void checkWallCollission() {

        if (playerCol > 15 && playerRow >= 0) {
            setPlayerPos(0, playerRow);
        }
        else if (playerCol < 0 && playerRow >= 0) {
            setPlayerPos(15, playerRow);
        }
        else if (playerCol >= 0 && playerRow > 15) {
            setPlayerPos(playerCol, 0);
        }
        else if (playerCol >= 0 && playerRow < 0) {
            setPlayerPos(playerCol, 15);
        }
    }
}
