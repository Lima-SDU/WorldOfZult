package worldofzult.presentation;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import worldofzult.domain.Domain;

import java.io.IOException;
import java.util.ArrayList;

public class WorldOfZultController {
    public int capacity;

    // MISCELLANEOUS OBJECTS
    @FXML
    public ImageView miniMap;
    public TextArea terminal;
    public ImageView imgGame;

    // BUTTONS
    @FXML
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
    @FXML
    public MenuButton item2;
    public ImageView imgItem2;
    public MenuItem item2Give;
    public MenuItem item2PutDown;
    public MenuItem item2Info;

    // Inventory Item3
    @FXML
    public MenuButton item3;
    public ImageView imgItem3;
    public MenuItem item3Give;
    public MenuItem item3PutDown;
    public MenuItem item3Info;

    // Inventory Item5
    @FXML
    public MenuButton item4;
    public ImageView imgItem4;
    public MenuItem item4Give;
    public MenuItem item4PutDown;
    public MenuItem item4Info;

    // Inventory Item5
    @FXML
    public MenuButton item5;
    public ImageView imgItem5;
    public MenuItem item5Give;
    public MenuItem item5PutDown;
    public MenuItem item5Info;

    // Spaces for displaying items in a room
    @FXML
    public ImageView itemPlace1;
    public ImageView itemPlace2;
    public ImageView itemPlace3;

    // Button for transitioning to the quiz
    @FXML
    public Button goToQuizButton;

    // NON-FXML OBJECTS
    public Domain domain;
    public ArrayList<ImageView> roomImageViews;
    public ArrayList<ImageView> inventoryImageViews;
    public ArrayList<MenuItem> giveButtons;
    public ArrayList<MenuItem> putDownButtons;

    @FXML
    public void initialize() {
        // Create interface with domain-layer
        domain = new Domain();

        // SETUP INITIAL ROOM-VIEW
        // Set initial image for the room and minimap
        imgGame.setImage(new Image("file:src/main/resources/worldofzult/presentation/images/before/Indgang.png"));
        miniMap.setImage(new Image("file:src/main/resources/worldofzult/presentation/images/minimap/indgang.png"));
        updateStatusBar();

        // Set initial visible arrows
        boolean[] edges = domain.getCurrentExits();
        arrowUp.setVisible(edges[0]);
        arrowRight.setVisible(edges[1]);
        arrowDown.setVisible(edges[2]);
        arrowLeft.setVisible(edges[3]);

        // Set side of the inventory menu
        item1.setPopupSide(Side.TOP);
        item2.setPopupSide(Side.TOP);
        item3.setPopupSide(Side.TOP);
        item4.setPopupSide(Side.TOP);
        item5.setPopupSide(Side.TOP);

        // INITIALIZE VARIABLES AND GROUPING VARIABLES IN ARRAYS
        // Add ImageViews of the item placements to an ArrayList
        roomImageViews = new ArrayList<ImageView>();

        roomImageViews.add(itemPlace1);
        roomImageViews.add(itemPlace2);
        roomImageViews.add(itemPlace3);

        // Add ImageViews of the item-inventory to an ArrayList
        inventoryImageViews = new ArrayList<ImageView>();

        inventoryImageViews.add(imgItem1);
        inventoryImageViews.add(imgItem2);
        inventoryImageViews.add(imgItem3);
        inventoryImageViews.add(imgItem4);
        inventoryImageViews.add(imgItem5);

        // Add the MenuItem buttons for giving an item to an ArrayList
        giveButtons = new ArrayList<MenuItem>();

        giveButtons.add(item1Give);
        giveButtons.add(item2Give);
        giveButtons.add(item3Give);
        giveButtons.add(item4Give);
        giveButtons.add(item5Give);

        // Add the MenuItem buttons for putting down an item to an ArrayList
        putDownButtons = new ArrayList<MenuItem>();

        putDownButtons.add(item1PutDown);
        putDownButtons.add(item2PutDown);
        putDownButtons.add(item3PutDown);
        putDownButtons.add(item4PutDown);
        putDownButtons.add(item5PutDown);

        // ADDING USERDATA TO OBJECTS
        // Set direction to arrows as UserData
        arrowDown.setUserData("syd");
        arrowLeft.setUserData("vest");
        arrowUp.setUserData("nord");
        arrowRight.setUserData("øst");

        item1PutDown.setUserData("Item1:PutDown");
        item2PutDown.setUserData("Item2:PutDown");
        item3PutDown.setUserData("Item3:PutDown");
        item4PutDown.setUserData("Item4:PutDown");
        item5PutDown.setUserData("Item5:PutDown");

        item1Info.setUserData("0");
        item2Info.setUserData("1");
        item3Info.setUserData("2");
        item4Info.setUserData("3");
        item5Info.setUserData("4");

        // ADDING EVENTHANDLERS
        // Set EventHandler for Arrows to navigate around the game
        arrowDown.addEventHandler(MouseEvent.MOUSE_CLICKED, navigationButton);
        arrowLeft.addEventHandler(MouseEvent.MOUSE_CLICKED, navigationButton);
        arrowUp.addEventHandler(MouseEvent.MOUSE_CLICKED, navigationButton);
        arrowRight.addEventHandler(MouseEvent.MOUSE_CLICKED, navigationButton);

        // Set EventHandler for ImageViews of the items in room to pickUpButton
        itemPlace1.addEventHandler(MouseEvent.MOUSE_CLICKED, pickUpButton);
        itemPlace2.addEventHandler(MouseEvent.MOUSE_CLICKED, pickUpButton);
        itemPlace3.addEventHandler(MouseEvent.MOUSE_CLICKED, pickUpButton);

        // SET ON ACTION EVENTHANDLERS
        // Set MenuItems for giving items to giveButton
        item1Give.setOnAction(giveButton);
        item2Give.setOnAction(giveButton);
        item3Give.setOnAction(giveButton);
        item4Give.setOnAction(giveButton);
        item5Give.setOnAction(giveButton);

        // Set MenuItems for putting down items to putDownButton
        item1PutDown.setOnAction(putDownButton);
        item2PutDown.setOnAction(putDownButton);
        item3PutDown.setOnAction(putDownButton);
        item4PutDown.setOnAction(putDownButton);
        item5PutDown.setOnAction(putDownButton);

        // Set MenuItems for giving items information to infoButton
        item1Info.setOnAction(infoButton);
        item2Info.setOnAction(infoButton);
        item3Info.setOnAction(infoButton);
        item4Info.setOnAction(infoButton);
        item5Info.setOnAction(infoButton);

        // Set action for quizButton
        goToQuizButton.setOnAction(goToQuiz);
    }

    @FXML
    public void talkButton() {
        String speech = domain.runCommand("tal");
        terminal.appendText(speech);
    }

    @FXML
    public void helpButton() {
        String speech = domain.runCommand("hjælpgui");
        terminal.appendText(speech);
    }

    @FXML
    EventHandler<MouseEvent> navigationButton = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            // Get source of event and retrieve UserData from it to run command. Then update Game
            Polygon button = (Polygon) mouseEvent.getSource();
            String direction = (String) button.getUserData();
            domain.runCommand("gå " + direction);
            updateGame();
            updateStatusBar();
        }
    };

    @FXML
    EventHandler<ActionEvent> goToQuiz = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            try {
                Stage stage = (Stage) imgGame.getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(WOZApplication.class.getResource("quizfinal.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                stage.setScene(scene);
                QuizController controller = fxmlLoader.getController();
                controller.storeResult(domain.getCount());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    };

    @FXML
    EventHandler<ActionEvent> giveButton = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            // Get source of event and retrieve UserData from it to run command. Then update Game
            MenuItem button = (MenuItem) actionEvent.getSource();
            String speech = domain.runCommand("giv " + button.getUserData().toString());
            updateGame();
            updateStatusBar();
            terminal.appendText(speech);
            if (domain.checkIsDone()) {
                goToQuizButton.setVisible(true);
            }
        }
    };

    @FXML
    EventHandler<ActionEvent> putDownButton = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            // Get source of event and retrieve UserData from it to run command. Then update Game
            MenuItem button = (MenuItem) actionEvent.getSource();
            String text = domain.runCommand("læg " + button.getUserData().toString());
            updateGame();
            terminal.appendText(text);
        }
    };

    EventHandler<MouseEvent> pickUpButton = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            // Get source of event and retrieve UserData from it to run command. Then update Game
            ImageView imageView = (ImageView) mouseEvent.getSource();
            String text = domain.runCommand("opsaml " + imageView.getUserData());
            updateGame();
            terminal.appendText(text);

        }
    };

    @FXML
    EventHandler<ActionEvent> infoButton = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            // Get source of event and retrieve UserData from it to run command
            MenuItem button = (MenuItem) actionEvent.getSource();
            String infoText = domain.getItemInformation(Integer.parseInt(button.getUserData().toString()));
            terminal.appendText(infoText);
        }
    };

    public String findGameImage(String name) {
        // Check status for showing before- or after-images of the room and then return filepath
        // Images for corridors without a group are placed in the after-folder
        if (domain.checkGroupStatus()) {
            return "file:src/main/resources/worldofzult/presentation/images/before/" + name + ".png";
        } else {
            return "file:src/main/resources/worldofzult/presentation/images/after/" + name + ".png";
        }
    }

    public String findItemImage(String name) {
        // Return  filepath of the given item
         return "file:src/main/resources/worldofzult/presentation/images/items/" + name + ".png";
    }

    public void updateItemsInInventory() {
        // RESET PLAYER-INVENTORY
        for (ImageView imageView : inventoryImageViews) {
            imageView.setImage(null);
            imageView.setUserData("");
        }

        for (int i = 0; i < giveButtons.size(); i++) {
            giveButtons.get(i).setUserData("");
            putDownButtons.get(i).setUserData("");
        }

        // GET CURRENT ITEMS IN INVENTORY
        ArrayList<String> itemsInInventory = domain.getItemsInInventory();

        // SHOW CURRENT ITEMS IN INVENTORY BY ITERATING THROUGH THEM
        for (int i = 0; i < itemsInInventory.size(); i++) {
            inventoryImageViews.get(i).setImage(new Image(findItemImage(itemsInInventory.get(i))));
            inventoryImageViews.get(i).setUserData(itemsInInventory.get(i));
            giveButtons.get(i).setUserData(itemsInInventory.get(i));
            putDownButtons.get(i).setUserData(itemsInInventory.get(i));
        }
    }

    public void updateItemsInRoom() {
        // RESET ROOM-INVENTORY
        for (ImageView imageView : roomImageViews) {
            imageView.setVisible(false);
            imageView.setUserData("");
        }

        // GET ITEMS IN THE ROOM
        ArrayList<String> itemsInRoom = domain.getItemsInRoom();

        // SHOW ITEMS IN THE ROOM
        for (int i = 0; i < itemsInRoom.size(); i++) {
            roomImageViews.get(i).setVisible(true);
            roomImageViews.get(i).setUserData(itemsInRoom.get(i));
            roomImageViews.get(i).setImage(new Image(findItemImage(itemsInRoom.get(i))));
        }
    }

    public void updateRoomImage() {
        // FIND ROOM-IMAGE AND SHOW IT
        imgGame.setImage(new Image(findGameImage(domain.getCurrent())));
        miniMap.setImage(new Image("file:src/main/resources/worldofzult/presentation/images/minimap/" + domain.getCurrent() + ".png"));

        // CHANGE VISIBILITY OF ARROWS TO MATCH ROOM
        boolean[] edges = domain.getCurrentExits();
        arrowUp.setVisible(edges[0]);
        arrowRight.setVisible(edges[1]);
        arrowDown.setVisible(edges[2]);
        arrowLeft.setVisible(edges[3]);
    }

    public void updateGame() {
        // UPDATES THE WHOLE GAME
        updateRoomImage();
        updateItemsInRoom();
        updateItemsInInventory();
    }

    public void updateStatusBar() {
        terminal.clear();
        int count = domain.getCount();
        int notHungryGroups = domain.getNonHungryGroupCount();
        terminal.appendText("Counter: " + count + " | Status: " + notHungryGroups + "/5\n");
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
        domain.setCapacity(this.capacity);
        switch (this.capacity) {
            case 3: {
                item4.setVisible(false);
                item5.setVisible(false);
                break;
            }
            case 1: {
                item2.setVisible(false);
                item3.setVisible(false);
                item4.setVisible(false);
                item5.setVisible(false);
                break;
            }
        }
    }

    public void setPlayerName(String playerName) {
        domain.setPlayerName(playerName);
    }
}