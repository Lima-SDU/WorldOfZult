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
    private Game game;
    private Context context;
    private Registry registry;
    private Quiz quiz;

    public Domain() {
       game = new Game();
       game.initRegistry();
       context = game.getContext();
       registry = game.getRegistry();
       Player player = new Player("Spiller");
       context.setPlayer(player);
       this.quiz = new Quiz();
       quiz.initQuiz();
    }

    public String runCommand(String command) {
        return registry.dispatch(command);
    }

    public String getCurrent() {
        return context.getCurrent().getName();
    }

    public boolean checkGroupStatus() {
        if (context.getCurrent().getGroup() != null) {
            return this.context.getCurrent().getGroup().isHungry();
        } else {
            return false;
        }
    }

    public boolean[] getCurrentExits() {
        boolean[] result = new boolean[4];
        Map<String, Node> edges = context.getCurrent().getEdges();
        for (String key : edges.keySet()) {
            switch (key) {
                case "nord" -> result[0] = true;
                case "øst" -> result[1] = true;
                case "syd" -> result[2] = true;
                case "vest" -> result[3] = true;
            }
        }
        return result;
    }

    public ArrayList<String> getItemsInRoom() {
        ArrayList<Item> items = context.getCurrent().getInventory().getItems();
        ArrayList<String> itemsString = new ArrayList<String>();
        for (Item item : items) {
            itemsString.add(item.getName());
        }
        return itemsString;
    }

    public ArrayList<String> getItemsInInventory() {
        ArrayList<Item> items = context.getPlayer().getInventory().getItems();
        ArrayList<String> itemsString = new ArrayList<String>();
        for (Item item : items) {
            itemsString.add(item.getName());
        }
        return itemsString;
    }

    public String getItemInformation(int index) {
        ArrayList<Item> items = context.getPlayer().getInventory().getItems();

        if (index > items.size() - 1) {
            return "";
        } else {
            return items.get(index).getDescription();
        }
    }

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
