package worldofzult.domain.world;

import java.util.HashMap;
import java.util.Map;

public class Node {
    // Name of room
    private String name;

    // Adjacent rooms with their name (String) and Node-instance
    private Map<String, Node> edges = new HashMap<String, Node>();

    // Constructor, which sets the name
    public Node (String name) {
        this.name = name;
    }

    // Gets name of node
    public String getName () {
        return name;
    }

    // Adds edges, so places to go
    public void addEdge (String name, Node node) {
        edges.put(name, node);
    }

    // Go to new room by following edge. Retrives next node in Map
    public Node followEdge (String direction) {
        return edges.get(direction);
    }

    public Map<String, Node> getEdges() {
        return edges;
    }
}
