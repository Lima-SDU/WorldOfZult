package worldofzult.presentation;

import javafx.scene.image.Image;
import worldofzult.domain.commands.Command;
import worldofzult.domain.session.Context;

import java.util.Arrays;
import java.util.HashMap;

public class ImageRegistry {
    // Fallback image to be displayed when there is not associated image
    private Image fallback;

    // Hashmap, which associates room-names (String) with their corresponding images.
    private HashMap<String, Image> images;

    // Constructor
    public ImageRegistry(String fallback) {
        this.fallback = new Image(fallback); // Fallback for incorrect command
        this.images = new HashMap<String, Image>();
    }

    // Add Commands.Command to register (Map)
    public void register (String roomName, String imagePath) {
        images.put(roomName, new Image(imagePath));
    }

    // Get command
    public Image getImage (String roomName) {
        if (images.get(roomName) != null) {
            return images.get(roomName);
        } else {
            return fallback;
        }

    }

    // Get all commands
    public String getImages () {
        return Arrays.toString(images.values().toArray());
    }
}
