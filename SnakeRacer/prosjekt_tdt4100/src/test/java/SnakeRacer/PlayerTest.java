package SnakeRacer;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    
    private Player player = new Player();

    @Test
    public void testMoveUp() {

        player.setPlayerPos(12, 9); // Setter utgangsposisjonen
        player.moveUp();

        // Sjekker at kun column har endret seg
        assertTrue((player.getPlayerPos().get(0) == 11) && (player.getPlayerPos().get(1) == 9), "Feil posisjon: " + player.getPlayerPos().toString());
    }
    
    @Test
    public void testMoveDown() {

        player.setPlayerPos(4, 2); // Setter utgangsposisjonen
        player.moveDown();

        // Sjekker at kun column har endret seg
        assertTrue((player.getPlayerPos().get(0) == 5) && (player.getPlayerPos().get(1) == 2), "Feil posisjon: " + player.getPlayerPos().toString());
    }

    @Test
    public void testMoveLeft() {

        player.setPlayerPos(8, 8); // Setter utgangsposisjonen
        player.moveLeft();

        // Sjekker at kun row har endret seg
        assertTrue((player.getPlayerPos().get(0) == 8) && (player.getPlayerPos().get(1) == 7), "Feil posisjon: " + player.getPlayerPos().toString());
    }

    @Test
    public void testMoveRight() {

        player.setPlayerPos(14, 15); // Testter at bevegelse gjennom vegg funker
        player.moveRight();

        // Sjekker at kun row har endret seg
        assertTrue((player.getPlayerPos().get(0) == 14) && (player.getPlayerPos().get(1) == 0), "Feil posisjon: " + player.getPlayerPos().toString());
    }
}
