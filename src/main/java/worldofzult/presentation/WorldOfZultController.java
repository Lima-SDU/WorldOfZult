package worldofzult.presentation;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
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
    public MenuButton item1;
    public ImageView imgItem1;
    public MenuButton item2;
    public ImageView imgItem2;
    public MenuButton item3;
    public ImageView imgItem3;
    public MenuButton item4;
    public ImageView imgItem4;
    public MenuButton item5;
    public ImageView imgItem5;
    public Button talkButton;
    public Button helpButton;
    public MenuItem item1Give;
    public MenuItem itemPutDown;
    public MenuItem item1Info;
    public MenuItem item2Give;
    public MenuItem item2PutDown;
    public MenuItem item2Info;
    public MenuItem item3Give;
    public MenuItem Item3PutDown;
    public MenuItem item3Info;
    public MenuItem item4Give;
    public MenuItem item4PutDown;
    public MenuItem item4Info;
    public MenuItem item5Give;
    public MenuItem item5PutDown;
    public MenuItem item5Info;

    @FXML
    public void initialize() {
        arrowDown.setUserData("syd");
        arrowLeft.setUserData("vest");
        arrowUp.setUserData("nord");
        arrowRight.setUserData("øst");

        item1.setPopupSide(Side.TOP);
        item2.setPopupSide(Side.TOP);
        item3.setPopupSide(Side.TOP);
        item4.setPopupSide(Side.TOP);
        item5.setPopupSide(Side.TOP);

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
    EventHandler<MouseEvent> navigationButton = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            Polygon button = (Polygon) mouseEvent.getSource();
            terminal.appendText(button.getUserData().toString() + "\n");
        }
    };
}
