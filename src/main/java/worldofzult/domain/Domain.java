package worldofzult.domain;

import worldofzult.domain.character.Group;
import worldofzult.domain.commands.Registry;
import worldofzult.domain.game.Game;
import worldofzult.domain.inventory.Item;
import worldofzult.domain.menus.Quiz;
import worldofzult.domain.player.Player;
import worldofzult.domain.session.Context;
import worldofzult.domain.session.Counter;
import worldofzult.domain.world.Node;

import java.util.ArrayList;
import java.util.Map;

public class Domain {
    private Game game; // Contains current game
    private Context context; // Session-information
    private Registry registry; // Registry of commands
    private Quiz quiz; // Quiz

    // Constructor
    public Domain() {
        // Make new game
       game = new Game();
       game.initRegistry();

       // Get context and registry for easier access than accessing through game
       context = game.getContext();
       registry = game.getRegistry();

       // Create new player and set default name
       Player player = new Player("Spiller");
       context.setPlayer(player);

       // Initialize quiz
       this.quiz = new Quiz();
       quiz.initQuiz();
    }

    // Run command through registry
    public String runCommand(String command) {
        return registry.dispatch(command);
    }

    // Get name of the current room
    public String getCurrent() {
        return context.getCurrent().getName();
    }

    // Check status of the group in a room
    public boolean checkGroupStatus() {
        // Return status if there is a group
        if (context.getCurrent().getGroup() != null) {
            return this.context.getCurrent().getGroup().isHungry();
        } else {
            return false;
        }
    }

    // Get current exits / edges of the room
    public boolean[] getCurrentExits() {
        boolean[] result = new boolean[4]; // Boolean array containing each cardinal direction

        Map<String, Node> edges = context.getCurrent().getEdges(); // Get edges

        // Go through each edge of the room and set the respective boolean to true
        for (String key : edges.keySet()) {
            switch (key) {
                case "nord" -> result[0] = true;
                case "øst" -> result[1] = true;
                case "syd" -> result[2] = true;
                case "vest" -> result[3] = true;
            }
        }

        return result; // Return array
    }

    // Get a string containing the names of the items in a room
    public ArrayList<String> getItemsInRoom() {
        // Get the rooms inventory of items
        ArrayList<Item> items = context.getCurrent().getInventory().getItems();
        ArrayList<String> itemsString = new ArrayList<String>();

        // Get the name of each item and add it to an ArrayList of their item names as Strings
        for (Item item : items) {
            itemsString.add(item.getName());
        }

        // Return ArrayList of the item names
        return itemsString;
    }

    // Get a string containing the names of the items in the player's inventory
    public ArrayList<String> getItemsInInventory() {
        // Get the rooms inventory of items
        ArrayList<Item> items = context.getPlayer().getInventory().getItems();
        ArrayList<String> itemsString = new ArrayList<String>();

        // Get the name of each item and add it to an ArrayList of their item names as Strings
        for (Item item : items) {
            itemsString.add(item.getName());
        }

        // Return ArrayList of the item names
        return itemsString;
    }

    // Get information of item at specific index in inventory
    public String getItemInformation(int index) {
        // Get a list of the items
        ArrayList<Item> items = context.getPlayer().getInventory().getItems();

        // If statement made obsolote by disabling itemInfo buttons without items
        if (index > items.size() - 1) { // If there is no item in the specified index, return empty string
            return "";
        } else {
            return items.get(index).getDescription(); // Get description of item
        }
    }

    // Returns if the game is done
    public boolean checkIsDone() {
        return context.isDone();
    }

    // Returns the current amount of moves made
    public int getCount() {
        return context.getCount();
    }

    // Returns the number of non hungry groups
    public int getNonHungryGroupCount() {
        int notHungryGroupCount = 0;
        // Runs through the groups and checks if they are hungry, if they are -> add them to the count
        for (Group group : context.getGroups()) {
            if (!group.isHungry()) {
                notHungryGroupCount++;
            }
        }
        return notHungryGroupCount;
    }

    // Sets the capacity in context, so that the player has less capacity in inventory
    public void setCapacity(int capacity) {
        context.setCapacity(capacity);
    }

    // Sets the players name in context
    public void setPlayerName(String playerName) {
        context.getPlayer().setName(playerName);
    }

    // Given a question index and an answer, returns whether it is right or wrong
    public boolean checkQuizAnswer(int index, String answer) {
        return quiz.checkAnswer(index, answer);
    }

    // Runs the quiz - used only for CLI
    public void runQuizCLI() {
        quiz.run();
    }
}
