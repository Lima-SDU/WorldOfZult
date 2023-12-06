package worldofzult.presentation.GUIcontrollers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import worldofzult.presentation.WOZApplication;

import java.io.IOException;

public class IntroScreenController {
    // FXML Element
    @FXML
    public Button startButton;

    // Set scene to the startscreen
    public void goToStartScreen() {
        try {
            Stage stage = (Stage) startButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(WOZApplication.class.getResource("startscreen2.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
