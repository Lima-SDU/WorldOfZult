package worldofzult.presentation.GUIcontrollers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import worldofzult.presentation.WOZApplication;

import java.io.IOException;

public class EndScreenController {
    // FXML Elements
    @FXML
    public Label moveLabel;
    public Label quizLabel;
    public Button replayButton;
    public Button endGameButton;

    // Adding eventhandler to the buttons
    @FXML
    private void initialize() {
        replayButton.setOnAction(replayGame);
        endGameButton.setOnAction(endGame);
    }

    // Storing the game and quiz results
    public void storeResults(int gameResult, int quizResult) {
        moveLabel.setText(moveLabel.getText() + " " + gameResult);
        quizLabel.setText(quizLabel.getText() + " " + quizResult+"/5");
    }

    // Setting the scene back to startscreen
    private EventHandler<ActionEvent> replayGame = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            try {
                Stage stage = (Stage) replayButton.getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(WOZApplication.class.getResource("startscreen2.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                stage.setScene(scene);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    };

    // Exiting the game
    private EventHandler<ActionEvent> endGame = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            System.exit(0);
        }
    };
}
