package worldofzult.domain;

import worldofzult.domain.commands.Registry;
import worldofzult.domain.game.Game;
import worldofzult.domain.inventory.Item;
import worldofzult.domain.player.Player;
import worldofzult.domain.session.Context;
import worldofzult.domain.world.Node;
import worldofzult.domain.world.Space;

import java.util.ArrayList;
import java.util.Map;

public class Domain {
    private Context context;
    private Registry registry;

    public Domain() {
       Game.initRegistry();
       context = Game.getContext();
       registry = Game.getRegistry();
       Player player = new Player("");
       context.setPlayer(player);
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
}
