package worldofzult.domain.player;

import worldofzult.domain.inventory.Inventory;

public class Player {
    private String name; //Player Name
    private Inventory inventory; //Player Inventory

    // Constructor
    public Player(String name) { //contructor for Player
        this.name = name;
        this.inventory = new Inventory();
    }

    // Get name
    public String getName() { // getter for Player name
        return name;
    }

    // Set name
    public void setName(String name) {
        this.name = name;
    }

    // Get inventory
    public Inventory getInventory() {
        return inventory;
    }
}