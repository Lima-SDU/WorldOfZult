package worldofzult.domain.character;

import worldofzult.domain.inventory.Item;

public class Group {
    private Item correctItem; // Their allowed items
    private String speech1 = ""; // Their first speech when hungry
    private String speech2 = "";// Their thank-you speech when they are not hungry
    private boolean hungry = true; // Their hunger-state. Default is hungry, so true


    // Constructor
    public Group(Item correctItem, String speech1, String speech2) {
        this.correctItem = correctItem;
        this.speech1 = speech1;
        this.speech2 = speech2;
    }

    // Check item with Item's equal method. It compares the item name
    public boolean checkItem(String itemName) { // checks if item is allowed to be given
        return correctItem.equals(new Item(itemName));
    }

    // Set hunger
    public void setHunger(boolean state) { // Set the hunger state to given parameter
        this.hungry = state;
    }

    // Check if hungry
    public boolean isHungry() { // Checks if group is hungry
        return hungry;
    }

    // Get speech 1
    public String getSpeech1() { // Get the group's speech
        return speech1;
    }

    // Get speech 2
    public String getSpeech2() { // Get the group's speech
        return speech2;
    }
}