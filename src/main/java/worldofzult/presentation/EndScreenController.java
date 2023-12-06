package worldofzult.presentation;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class EndScreenController {
    @FXML
    public Label moveLabel;
    public Label quizLabel;

    public void storeResults(int gameResult, int quizResult) {
        moveLabel.setText(moveLabel.getText() + " " + gameResult);
        quizLabel.setText(quizLabel.getText() + " " + quizResult);
    }
}
