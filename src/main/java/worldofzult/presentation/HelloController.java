package worldofzult.presentation;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HelloController {
    @FXML
    public ImageView imageDisplay;

    @FXML
    private Label welcomeText;

    @FXML
    public Button userDataButton;

    Image image1 = new Image("file:src/main/resources/worldofzult/presentation/images/cat-behavior-social.jpg");
    Image image2 = new Image("file:src/main/resources/worldofzult/presentation/images/vizsla-running.jpg");


    @FXML
    public void initialize() {
        userDataButton.setUserData("Gå vest");

        imageDisplay.setImage(image1);
        imageDisplay.setCache(true);
    }

    @FXML
    protected void onUserButtonClick() {
        System.out.println(userDataButton.getUserData());
    }

    @FXML
    public void onImageButtonClick(ActionEvent actionEvent) {
        if (imageDisplay.getImage() == image2) {
            imageDisplay.setImage(image1);
        } else {
            imageDisplay.setImage(image2);
        }
    }
}

class ImageFile {
    SimpleStringProperty filepath;
    Image image;

    ImageFile(String filepath) {
        this.filepath = new SimpleStringProperty(filepath);
        image = new Image(String.valueOf(this.filepath));
    }
}