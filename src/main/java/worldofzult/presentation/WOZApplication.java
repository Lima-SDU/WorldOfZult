package worldofzult.presentation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class WOZApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Loads the startscreen scene
        FXMLLoader fxmlLoader = new FXMLLoader(WOZApplication.class.getResource("startscreen1.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        // Sets the title and disables resizing
        stage.setTitle("WorldOfZult");
        stage.setResizable(false);

        // Displays the scene and shows the window
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        // Tells JavaFX to launch the game and run start()
        launch();
    }
}