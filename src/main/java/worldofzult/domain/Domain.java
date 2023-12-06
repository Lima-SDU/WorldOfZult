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
    private Context context;
    private Registry registry;
    private Quiz quiz;

    public Domain() {
       Game.initRegistry();
       context = Game.getContext();
       registry = Game.getRegistry();
       Player player = new Player("");
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

    public int getCount() {
        return Counter.getCount();
    }

    public int getNonHungryGroupCount() {
        int notHungryGroups = 0;
        for (Group group : context.getGroups()) {
            if (!group.isHungry()) {
                notHungryGroups++;
            }
        }
        return notHungryGroups;
    }

    public void setCapacity(int capacity) {
        context.setCapacity(capacity);
    }

    public void setPlayerName(String playerName) {
        context.getPlayer().setName(playerName);
    }

    public boolean checkQuizAnswer(int index, String answer) {
        return quiz.checkAnswer(index, answer);
    }

    public void runQuizCLI() {
        quiz.run();
    }
}
