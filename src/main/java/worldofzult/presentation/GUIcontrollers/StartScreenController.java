package worldofzult.presentation.GUIcontrollers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import worldofzult.presentation.WOZApplication;

import java.io.IOException;

public class StartScreenController {

    // FXML Elements
    @FXML
    public Button difficulty1;
    public Button difficulty2;
    public Button difficulty3;
    public TextField inputName;

    @FXML
    public void initialize() {
       // Adds userdata representing the capacity of playerInventory
       difficulty1.setUserData(5);
       difficulty2.setUserData(3);
       difficulty3.setUserData(1);

       // Style
       inputName.setFocusTraversable(false);
    }

    // Loads the game scene. Sends the users name and the chosen difficulty
    public void startGame(Stage stage, int capacity, String playerName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(WOZApplication.class.getResource("game.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        WorldOfZultController controller = fxmlLoader.getController();
        controller.setCapacity(capacity);

        if (!playerName.isEmpty()) {
            controller.setPlayerName(playerName);
        }
    }

    // Coupling the startGame method with a MouseEvent for the difficultyButtons.
    public void startGameButton(MouseEvent mouseEvent) {
        Button button = (Button) mouseEvent.getSource();
        try {
            startGame((Stage) button.getScene().getWindow(), Integer.parseInt(button.getUserData().toString()), inputName.getText());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
