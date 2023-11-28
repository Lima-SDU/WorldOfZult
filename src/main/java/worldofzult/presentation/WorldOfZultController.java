package worldofzult.presentation;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Polygon;

public class WorldOfZultController {
    @FXML
    public ImageView miniMap;
    public TextArea terminal;
    public ImageView imgGame;
    public Polygon arrowUp;
    public Polygon arrowLeft;
    public Polygon arrowRight;
    public Polygon arrowDown;
    public Button item1;
    public ImageView imgItem1;
    public Button item2;
    public ImageView imgItem2;
    public Button item3;
    public ImageView imgItem3;
    public Button item4;
    public ImageView imgItem4;
    public Button item5;
    public ImageView imgItem5;
    public Button talkButton;
    public Button helpButton;

    @FXML
    public void initialize() {
        item1.setUserData("item1");
        item2.setUserData("item2");
        item3.setUserData("item3");
        item4.setUserData("item4");
        item5.setUserData("item5");

        arrowDown.setUserData("syd");
        arrowLeft.setUserData("vest");
        arrowUp.setUserData("nord");
        arrowRight.setUserData("øst");

        item1.addEventHandler(MouseEvent.MOUSE_CLICKED, itemButton);
        item2.addEventHandler(MouseEvent.MOUSE_CLICKED, itemButton);
        item3.addEventHandler(MouseEvent.MOUSE_CLICKED, itemButton);
        item4.addEventHandler(MouseEvent.MOUSE_CLICKED, itemButton);
        item5.addEventHandler(MouseEvent.MOUSE_CLICKED, itemButton);

        arrowDown.addEventHandler(MouseEvent.MOUSE_CLICKED, navigationButton);
        arrowLeft.addEventHandler(MouseEvent.MOUSE_CLICKED, navigationButton);
        arrowUp.addEventHandler(MouseEvent.MOUSE_CLICKED, navigationButton);
        arrowRight.addEventHandler(MouseEvent.MOUSE_CLICKED, navigationButton);
    }

    @FXML
    public void talkButton() {
        terminal.appendText("TALK - YAY\n");
    }

    @FXML
    public void helpButton() {
        terminal.appendText("HELP - YAY\n");
    }

    @FXML
    EventHandler<MouseEvent> itemButton = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            Button button = (Button) mouseEvent.getSource();
            terminal.appendText(button.getUserData().toString() + "\n");
        }
    };

    @FXML
    EventHandler<MouseEvent> navigationButton = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            Polygon button = (Polygon) mouseEvent.getSource();
            terminal.appendText(button.getUserData().toString() + "\n");
        }
    };
}
