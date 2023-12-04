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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Polygon;
import worldofzult.domain.Domain;

public class WorldOfZultController {
    @FXML
    public ImageView miniMap;
    public TextArea terminal;
    public ImageView imgGame;

    // BUTTONS
    public Button talkButton;
    public Button helpButton;


    // ARROWS
    @FXML
    public Polygon arrowUp;
    public Polygon arrowLeft;
    public Polygon arrowRight;
    public Polygon arrowDown;

    // INVENTORY FXML-OBJECTS
    @FXML
    // Inventory Item1
    public MenuButton item1;
    public ImageView imgItem1;
    public MenuItem item1Give;
    public MenuItem item1PutDown;
    public MenuItem item1Info;

    // Inventory Item2
    public MenuButton item2;
    public ImageView imgItem2;
    public MenuItem item2Give;
    public MenuItem item2PutDown;
    public MenuItem item2Info;

    // Inventory Item3
    public MenuButton item3;
    public ImageView imgItem3;
    public MenuItem item3Give;
    public MenuItem item3PutDown;
    public MenuItem item3Info;

    // Inventory Item5
    public MenuButton item4;
    public ImageView imgItem4;
    public MenuItem item4Give;
    public MenuItem item4PutDown;
    public MenuItem item4Info;

    // Inventory Item5
    public MenuButton item5;
    public ImageView imgItem5;
    public MenuItem item5Give;
    public MenuItem item5PutDown;
    public MenuItem item5Info;


    // NON-FXML OBJECTS
    public Domain domain;

    @FXML
    public void initialize() {
        domain = new Domain();
        imgGame.setImage(new Image("file:src/main/resources/worldofzult/presentation/images/before/Indgang.png"));
        miniMap.setImage(new Image("file:src/main/resources/worldofzult/presentation/images/minimap/indgang.jpg"));

        arrowDown.setUserData("syd");
        arrowLeft.setUserData("vest");
        arrowUp.setUserData("nord");
        arrowRight.setUserData("øst");

        item1.setPopupSide(Side.TOP);
        item2.setPopupSide(Side.TOP);
        item3.setPopupSide(Side.TOP);
        item4.setPopupSide(Side.TOP);
        item5.setPopupSide(Side.TOP);

        item1Give.setUserData("Item1:Give");
        item2Give.setUserData("Item2:Give");
        item3Give.setUserData("Item3:Give");
        item4Give.setUserData("Item4:Give");
        item5Give.setUserData("Item5:Give");

        item1PutDown.setUserData("Item1:PutDown");
        item2PutDown.setUserData("Item2:PutDown");
        item3PutDown.setUserData("Item3:PutDown");
        item4PutDown.setUserData("Item4:PutDown");
        item5PutDown.setUserData("Item5:PutDown");

        item1Info.setUserData("Item1:Info");
        item2Info.setUserData("Item2:Info");
        item3Info.setUserData("Item3:Info");
        item4Info.setUserData("Item4:Info");
        item5Info.setUserData("Item5:Info");

        arrowDown.addEventHandler(MouseEvent.MOUSE_CLICKED, navigationButton);
        arrowLeft.addEventHandler(MouseEvent.MOUSE_CLICKED, navigationButton);
        arrowUp.addEventHandler(MouseEvent.MOUSE_CLICKED, navigationButton);
        arrowRight.addEventHandler(MouseEvent.MOUSE_CLICKED, navigationButton);

        item1Give.setOnAction(giveButton);
        item2Give.setOnAction(giveButton);
        item3Give.setOnAction(giveButton);
        item4Give.setOnAction(giveButton);
        item5Give.setOnAction(giveButton);

        item1PutDown.setOnAction(putDownButton);
        item2PutDown.setOnAction(putDownButton);
        item3PutDown.setOnAction(putDownButton);
        item4PutDown.setOnAction(putDownButton);
        item5PutDown.setOnAction(putDownButton);

        item1Info.setOnAction(infoButton);
        item2Info.setOnAction(infoButton);
        item3Info.setOnAction(infoButton);
        item4Info.setOnAction(infoButton);
        item5Info.setOnAction(infoButton);

        boolean[] edges = domain.getCurrentExits();
        arrowUp.setVisible(edges[0]);
        arrowRight.setVisible(edges[1]);
        arrowDown.setVisible(edges[2]);
        arrowLeft.setVisible(edges[3]);
    }

    @FXML
    public void talkButton() {
        terminal.appendText("TALK - YAY\n");
    }

    @FXML
    public void helpButton() {
        terminal.appendText("HELP - YAY\n");
        domain.runCommand("hjælp");
    }

    @FXML
    EventHandler<MouseEvent> navigationButton = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            Polygon button = (Polygon) mouseEvent.getSource();
            String direction = (String) button.getUserData();
            domain.runCommand("gå " + direction);

            imgGame.setImage(new Image(findGameImage(domain.getCurrent())));
            miniMap.setImage(new Image("file:src/main/resources/worldofzult/presentation/images/minimap/" + domain.getCurrent() + ".jpg"));

            boolean[] edges = domain.getCurrentExits();
            arrowUp.setVisible(edges[0]);
            arrowRight.setVisible(edges[1]);
            arrowDown.setVisible(edges[2]);
            arrowLeft.setVisible(edges[3]);
        }
    };

    @FXML
    EventHandler<ActionEvent> giveButton = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            MenuItem button = (MenuItem) actionEvent.getSource();
            terminal.appendText(button.getUserData().toString() + "\n");
        }
    };

    @FXML
    EventHandler<ActionEvent> putDownButton = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            MenuItem button = (MenuItem) actionEvent.getSource();
            terminal.appendText(button.getUserData().toString() + "\n");
        }
    };

    @FXML
    EventHandler<ActionEvent> infoButton = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            MenuItem button = (MenuItem) actionEvent.getSource();
            terminal.appendText(button.getUserData().toString() + "\n");
        }
    };

    public String findGameImage(String name) {
        if (domain.checkGroupStatus()) {
            return "file:src/main/resources/worldofzult/presentation/images/before/" + name + ".png";
        } else {
            return "file:src/main/resources/worldofzult/presentation/images/after/" + name + ".png";
        }
    }
}
