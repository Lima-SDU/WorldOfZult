package worldofzult.domain.world;

import java.util.Set;
import worldofzult.domain.character.Group;
import worldofzult.domain.inventory.Item;
import worldofzult.domain.inventory.Inventory;

public class Space extends Node {
    private Inventory inventory;
    private Group group;
    private String description;

    //Only name constructor
    public Space (String name, String description) {
        super(name);
        this.inventory = new Inventory();
        this.description = description;
        //set group to null
    }

    //Name + Group constructor
    public Space (String name, String description, Group group){
        this(name, description);
        this.group = group;
    }

    public void printItems (){

    }

    // Welcome command, which runs automatically when entering a room
    public void welcome () {
        System.out.println("Du er nu her: "+ this.getName());

        if (description != "") {
            System.out.println(description);
        }

        if (group != null) {
            System.out.println("Der er en gruppe af lokale i rummet.\nHvis du ønsker at tale med dem kan du bruge 'tal' kommandoen");
        }

        Set<String> exits = this.getEdges().keySet();
        System.out.println("De mulige udgange er: ");
        for (String exit: exits) {
            System.out.println(" - "+exit);
        }
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void addItem(Item item) {
        this.inventory.addItem(item);
    }

    public Group getGroup() {
        return group;
    }

    public void goodbye () {
    }

    @Override
    public Space followEdge (String direction) {
        return (Space) (super.followEdge(direction));
    }
}
