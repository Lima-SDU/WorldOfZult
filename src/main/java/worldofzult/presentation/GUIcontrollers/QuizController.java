package worldofzult.presentation.GUIcontrollers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import worldofzult.domain.Domain;
import worldofzult.presentation.WOZApplication;

import java.io.IOException;
import java.util.ArrayList;

public class QuizController {
    private int gameResult;
    private int quizResult;
    private Domain domain;

    @FXML
    public RadioButton button1_1;
    public RadioButton button1_2;
    public RadioButton button1_3;
    public RadioButton button2_1;
    public RadioButton button2_2;
    public RadioButton button2_3;
    public RadioButton button3_1;
    public RadioButton button3_2;
    public RadioButton button3_3;
    public RadioButton button4_1;
    public RadioButton button4_2;
    public RadioButton button4_3;
    public RadioButton button5_1;
    public RadioButton button5_2;
    public RadioButton button5_3;

    public Button submitButton;

    public ToggleGroup tGroup1;
    public ToggleGroup tGroup2;
    public ToggleGroup tGroup3;
    public ToggleGroup tGroup4;
    public ToggleGroup tGroup5;

    public ArrayList<ToggleGroup> groups;

    @FXML
    public void initialize() {
        domain = new Domain();

        tGroup1 = new ToggleGroup();
        tGroup2 = new ToggleGroup();
        tGroup3 = new ToggleGroup();
        tGroup4 = new ToggleGroup();
        tGroup5 = new ToggleGroup();

        groups = new ArrayList<ToggleGroup>();

        groups.add(tGroup1);
        groups.add(tGroup2);
        groups.add(tGroup3);
        groups.add(tGroup4);
        groups.add(tGroup5);

        button1_1.setToggleGroup(tGroup1);
        button1_2.setToggleGroup(tGroup1);
        button1_3.setToggleGroup(tGroup1);

        button2_1.setToggleGroup(tGroup2);
        button2_2.setToggleGroup(tGroup2);
        button2_3.setToggleGroup(tGroup2);

        button3_1.setToggleGroup(tGroup3);
        button3_2.setToggleGroup(tGroup3);
        button3_3.setToggleGroup(tGroup3);

        button4_1.setToggleGroup(tGroup4);
        button4_2.setToggleGroup(tGroup4);
        button4_3.setToggleGroup(tGroup4);

        button5_1.setToggleGroup(tGroup5);
        button5_2.setToggleGroup(tGroup5);
        button5_3.setToggleGroup(tGroup5);

        for (ToggleGroup group : groups) {
            for (int i = 0; i < group.getToggles().size(); i++) {
                RadioButton button = (RadioButton) group.getToggles().get(i);
                switch (i) {
                    case 0 -> button.setUserData("A");
                    case 1 -> button.setUserData("B");
                    case 2 -> button.setUserData("C");
                }
            }
        }
    }

    public void storeResult(int gameResult) {
        this.gameResult = gameResult;
        System.out.println(this.gameResult);
    }

    public void checkAnswers() {
        int result = 0;
        for (int i = 0; i < groups.size(); i++) {
            RadioButton button = (RadioButton) groups.get(i).getSelectedToggle();
            if (button != null && domain.checkQuizAnswer(i, button.getUserData().toString())) {
                result++;
            }
        }
        quizResult = result;

        try {
            goToEndScreen((Stage) submitButton.getScene().getWindow());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void goToEndScreen(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(WOZApplication.class.getResource("spilslut.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        EndScreenController controller = fxmlLoader.getController();
        controller.storeResults(this.gameResult, this.quizResult);
    }
}
