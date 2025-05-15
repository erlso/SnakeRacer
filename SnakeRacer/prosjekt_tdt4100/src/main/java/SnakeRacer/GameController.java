package SnakeRacer;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.input.KeyEvent;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;

import java.util.Random;
import javafx.util.Duration;
import java.util.List;
import java.util.Optional;

public class GameController {
    
    public SnakeRacerGame game = new SnakeRacerGame();
    public Player player;
    private SaveHandlerInterface SaveHandler = new SaveHandler("/Users/erlend/Objektorientert/SnakeRacer/prosjekt_tdt4100/src/main/resources/saves/highscores.txt");

    @FXML
    private GridPane gridPane;

    @FXML
    private Label scoreLabel;

    @FXML
    private Label timerLabel;

    @FXML
    private Label messageLabel;

    private int score;
    private boolean hasSavedScore = false;

    Random random = new Random();

    // Lager en spiller og et "mål"
    private Rectangle rectangle;
    private Rectangle goal;
    private int timeLeft;
    private Timeline timeline;

    // Metode som initialiserer brettet med spiller og mål på tilfeldig posisjon
    public void initialize() {

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> updateTimer()));
        timeline.setCycleCount(timeLeft + 1);

        this.player = game.player;
      
        // Brikker som representerer en spiller og et mål
        rectangle = new Rectangle(25, 25, Color.RED);
        goal = new Rectangle(25, 25, Color.YELLOW);
    }

    @FXML
    private void handleStart() {

        // Stopper tidligere timeline hvis den fortsatt kjører
        if (timeline != null) {
            timeline.stop();
        }

        // Restarter spillet når man trykker på startknappen
        timeLeft = 15;
        score = 0;
        hasSavedScore = false;
        scoreLabel.setText("Score: 0");
        timerLabel.setText(String.valueOf("Time left: " + timeLeft));
        messageLabel.setText("");
   
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> updateTimer()));
        timeline.setCycleCount(timeLeft + 1);
        timeline.play();

        Scene scene = gridPane.getScene();
        movement(scene);

        // Setter spiller og mål på tilfeldige posisjoner
        game.setGoalPos(random.nextInt(16), random.nextInt(16));
        player.setPlayerPos(random.nextInt(16), random.nextInt(16));

        updateGrid();
    }

    @FXML
    public void handleSave() {
        
        if (hasSavedScore) { // Sjekker om brukeren allerede har trykket på lagre
            messageLabel.setText("Du har allerede lagret.");
            return;
        }

        // Lager en pop-up boks som tar med navn
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Lagre Score");
        dialog.setHeaderText("Skriv inn navnet ditt:");
        dialog.setContentText("Navn: ");

        // Viser pop-up vinduet, og krever ikke at brukeren må skrive en string
        Optional<String> result = dialog.showAndWait();
    
        if (result.isPresent() && !result.get().trim().isEmpty()) { // Sjekker om navn er null eller ugyldig
            String name = result.get().trim();
            SaveHandler.checkValidName(name);
            SaveHandler.save(name, score); 
            hasSavedScore = true;
            messageLabel.setText("Score lagret!");
        } else {
            messageLabel.setText("Navn kan ikke være tomt.");
        }
    }

    @FXML
    public void handleLoad() {
        SaveHandler.load();
    }

    private void updateTimer() {
        if (timeLeft > 0){
            timeLeft--;
            timerLabel.setText(String.valueOf("Time left: " + timeLeft));
        }
        else if (timeLeft == 0) {
            gridPane.getScene().setOnKeyPressed(null); // Fjerner mulighet for bevegelse
            timeline.stop();
            timerLabel.setText("Time's up!");
        }
    }

    // Oppdaterer brettet etter at brikkene har flyttet på seg
    private void updateGrid() {
    
        gridPane.getChildren().removeAll(rectangle, goal);

        List<Integer> playerPos = player.getPlayerPos();
        List<Integer> goalPos = game.getGoalPos();

        gridPane.add(rectangle, playerPos.get(1), playerPos.get(0));
        gridPane.add(goal, goalPos.get(1), goalPos.get(0));
    }

    public void updateScore() {

        score += 1;
        scoreLabel.setText("Score: " + score);
    }

    // Metode for å håndtere bevegelse i spillet
    public void movement(Scene scene) {
        
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:
                        player.moveUp();
                        break;
                    case DOWN:
                        player.moveDown();
                        break;
                    case LEFT:
                        player.moveLeft();
                        break;
                    case RIGHT:
                        player.moveRight();
                        break;
                    default:
                        break;
                }

                // Sørger for at målet flyttes når spilleren er på målet
                game.moveGoal(GameController.this);
                updateGrid(); // Oppdaterer rutenettet for hver bevegelse
            }
        });
    }
}