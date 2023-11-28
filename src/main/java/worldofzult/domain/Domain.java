package worldofzult.domain;

import worldofzult.domain.commands.Registry;
import worldofzult.domain.game.Game;
import worldofzult.domain.session.Context;
import worldofzult.domain.world.Node;
import worldofzult.domain.world.Space;

import java.util.Map;

public class Domain {
    private Context context;
    private Registry registry;

    public Domain() {
       Game.initRegistry();
       context = Game.getContext();
       registry = Game.getRegistry();
    }

    public void runCommand(String command) {
        registry.dispatch(command);
    }

    public String getCurrent() {
        Space current = context.getCurrent();
        String name = current.getName();
        return "src/main/resources/worldofzult/presentation/images/" + name + ".png";
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
}
