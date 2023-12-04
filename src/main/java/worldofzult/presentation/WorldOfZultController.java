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

import java.util.ArrayList;

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

    // Spaces for displaying items in a room
    public ImageView itemPlace1;
    public ImageView itemPlace2;
    public ImageView itemPlace3;

    // NON-FXML OBJECTS
    public Domain domain;
    public ArrayList<ImageView> roomImageViews;
    public ArrayList<ImageView> inventoryImageViews;
    public ArrayList<MenuItem> giveButtons;
    public ArrayList<MenuItem> putDownButtons;

    @FXML
    public void initialize() {
        domain = new Domain();
        imgGame.setImage(new Image("file:src/main/resources/worldofzult/presentation/images/before/Indgang.png"));
        miniMap.setImage(new Image("file:src/main/resources/worldofzult/presentation/images/minimap/indgang.jpg"));

        roomImageViews = new ArrayList<ImageView>();

        roomImageViews.add(itemPlace1);
        roomImageViews.add(itemPlace2);
        roomImageViews.add(itemPlace3);

        inventoryImageViews = new ArrayList<ImageView>();

        inventoryImageViews.add(imgItem1);
        inventoryImageViews.add(imgItem2);
        inventoryImageViews.add(imgItem3);
        inventoryImageViews.add(imgItem4);
        inventoryImageViews.add(imgItem5);

        giveButtons = new ArrayList<MenuItem>();

        giveButtons.add(item1Give);
        giveButtons.add(item2Give);
        giveButtons.add(item3Give);
        giveButtons.add(item4Give);
        giveButtons.add(item5Give);

        putDownButtons = new ArrayList<MenuItem>();

        putDownButtons.add(item1PutDown);
        putDownButtons.add(item2PutDown);
        putDownButtons.add(item3PutDown);
        putDownButtons.add(item4PutDown);
        putDownButtons.add(item5PutDown);

        arrowDown.setUserData("syd");
        arrowLeft.setUserData("vest");
        arrowUp.setUserData("nord");
        arrowRight.setUserData("øst");

        item1.setPopupSide(Side.TOP);
        item2.setPopupSide(Side.TOP);
        item3.setPopupSide(Side.TOP);
        item4.setPopupSide(Side.TOP);
        item5.setPopupSide(Side.TOP);

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

        itemPlace1.addEventHandler(MouseEvent.MOUSE_CLICKED, pickUpButton);
        itemPlace2.addEventHandler(MouseEvent.MOUSE_CLICKED, pickUpButton);
        itemPlace3.addEventHandler(MouseEvent.MOUSE_CLICKED, pickUpButton);
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
            updateGame();
        }
    };

    @FXML
    EventHandler<ActionEvent> giveButton = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            MenuItem button = (MenuItem) actionEvent.getSource();
            terminal.appendText(button.getUserData().toString() + "\n");
            domain.runCommand("giv " + button.getUserData().toString());
            updateGame();
        }
    };

    @FXML
    EventHandler<ActionEvent> putDownButton = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            MenuItem button = (MenuItem) actionEvent.getSource();
            domain.runCommand("læg " + button.getUserData().toString());
            updateGame();
        }
    };

    EventHandler<MouseEvent> pickUpButton = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            ImageView imageView = (ImageView) mouseEvent.getSource();
            domain.runCommand("opsaml " + imageView.getUserData());
            updateGame();
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

    public String findItemImage(String name) {
         return "file:src/main/resources/worldofzult/presentation/images/items/" + name + ".png";
    }

    public void updateItemsInInventory() {
        for (ImageView imageView : inventoryImageViews) {
            imageView.setImage(null);
            imageView.setUserData("");
        }

        for (int i = 0; i < giveButtons.size(); i++) {
            giveButtons.get(i).setUserData("");
            putDownButtons.get(i).setUserData("");
        }

        ArrayList<String> itemsInInventory = domain.getItemsInInventory();

        for (int i = 0; i < itemsInInventory.size(); i++) {
            inventoryImageViews.get(i).setImage(new Image(findItemImage(itemsInInventory.get(i))));
            inventoryImageViews.get(i).setUserData(itemsInInventory.get(i));
            giveButtons.get(i).setUserData(itemsInInventory.get(i));
            putDownButtons.get(i).setUserData(itemsInInventory.get(i));
        }
    }

    public void updateItemsInRoom() {
        for (ImageView imageView : roomImageViews) {
            imageView.setVisible(false);
            imageView.setUserData("");
        }

        ArrayList<String> itemsInRoom = domain.getItemsInRoom();

        for (int i = 0; i < itemsInRoom.size(); i++) {
            roomImageViews.get(i).setVisible(true);
            roomImageViews.get(i).setUserData(itemsInRoom.get(i));
            roomImageViews.get(i).setImage(new Image(findItemImage(itemsInRoom.get(i))));
        }
    }

    public void updateRoomImage() {
        imgGame.setImage(new Image(findGameImage(domain.getCurrent())));
        miniMap.setImage(new Image("file:src/main/resources/worldofzult/presentation/images/minimap/" + domain.getCurrent() + ".jpg"));

        boolean[] edges = domain.getCurrentExits();
        arrowUp.setVisible(edges[0]);
        arrowRight.setVisible(edges[1]);
        arrowDown.setVisible(edges[2]);
        arrowLeft.setVisible(edges[3]);
    }

    public void updateGame() {
        updateRoomImage();
        updateItemsInRoom();
        updateItemsInInventory();
    }
}
