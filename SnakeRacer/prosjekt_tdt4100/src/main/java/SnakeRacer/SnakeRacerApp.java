package SnakeRacer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;

public class SnakeRacerApp extends Application {
    
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        // Bruk FXMLLoader for å laste FXML-filen og få GameController-objektet
        FXMLLoader loader = new FXMLLoader(getClass().getResource("snakeracer.fxml"));
        Pane root = loader.load();
        
        // Sett opp scenen
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
