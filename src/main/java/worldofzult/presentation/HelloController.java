package worldofzult.presentation;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    public Button userDataButton;

    @FXML
    public void initialize() {
        userDataButton.setUserData("Gå vest");
    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected void onUserButtonClick() {
        System.out.println(userDataButton.getUserData());
    }
}