package worldofzult.presentation;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
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

       startButton1.getParent().addEventFilter(KeyEvent.KEY_RELEASED, difficultyKey);
   }

    EventHandler<KeyEvent> difficultyKey = new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent keyEvent) {
            // Get source of event and retrieve UserData from it to run command. Then update Game
            String keyPressed = String.valueOf(keyEvent.getCode());

            if ("SPACE".contains(keyPressed)) {
                try {
                    startGame((Stage) startButton1.getScene().getWindow(), 1, inputName.getText());
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
            keyEvent.consume();
        }
    };

    public void startGame(Stage stage, int capacity, String playerName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(WOZApplication.class.getResource("guiny.fxml"));
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("guiwoz.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        WorldOfZultController controller = fxmlLoader.getController();
        controller.setCapacity(capacity);
        controller.setPlayerName(playerName);
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
