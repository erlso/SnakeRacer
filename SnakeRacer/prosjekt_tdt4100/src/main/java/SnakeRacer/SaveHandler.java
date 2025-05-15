package SnakeRacer;

import javafx.fxml.FXML;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import javafx.scene.control.ListView;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Label;

public class SaveHandler implements SaveHandlerInterface {
    
    private final Path filePath;

    @FXML
    public Label messageLabel;

    public SaveHandler (String filename) {

        this.filePath = Paths.get(filename);
    }

    public void save(String name, int score) {

        try {
            if (!Files.exists(filePath)) {
                Files.createFile(filePath); // Lager fil hvis den ikke finnes
            }
            try (FileWriter writer = new FileWriter(filePath.toString(), true)) {
                writer.write(name + " fikk " + score + " poeng." + "\n");
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("Could not save: " + e.getMessage());
        }
    }

    // Metode for å lese highscores i highscores-filen
    public List<String> readScores() {
        
        List <String> scores;

        try {
            if (!Files.exists(filePath)) {
                throw new FileNotFoundException("File not found");
            }   else {
                scores = Files.readAllLines(filePath);
                return scores;
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("Something went wrong: " + e.getMessage());
        }
    }

    // Laster inn et nytt JavaFX-vindu
    public void load() {
        List<String> scores = readScores();

        // Lager nytt JavaFX-vindu
        Stage stage = new Stage();
        stage.setTitle("Highscores");

        ListView<String> listView = new ListView<>();
        listView.getItems().addAll(scores); // Legger til alle scores

        // Lager en listView i javaFX
        Scene scene = new Scene(listView, 300, 400);
        stage.setScene(scene);
        stage.show();
    }

    public void checkValidName(String name) {

        for (int i = 0; i < name.length(); i++) {
            if (!Character.isLetter(name.charAt(i)) && name.charAt(i) != ' ') {
                messageLabel.setText("Navn må inneholde bokstaver eller mellomrom.");
                throw new IllegalArgumentException("Name can only contain letters.");
            }
        }
    }
}
