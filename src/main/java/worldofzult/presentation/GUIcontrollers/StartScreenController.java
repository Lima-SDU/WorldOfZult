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

    @FXML
    public Button startButton1;
    public Button startButton2;
    public Button startButton3;
    public TextField inputName;

   @FXML
    public void initialize() {
       startButton1.setUserData(5);
       startButton2.setUserData(3);
       startButton3.setUserData(1);

       inputName.setFocusTraversable(false);
   }

    public void startGame(Stage stage, int capacity, String playerName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(WOZApplication.class.getResource("game.fxml"));
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("guiwoz.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        WorldOfZultController controller = fxmlLoader.getController();
        controller.setCapacity(capacity);
        if (!playerName.equals("")) {
            controller.setPlayerName(playerName);
        }
    }

    public void startGameButton(MouseEvent mouseEvent) {
        Button button = (Button) mouseEvent.getSource();
        try {
            startGame((Stage) button.getScene().getWindow(), Integer.parseInt(button.getUserData().toString()), inputName.getText());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
