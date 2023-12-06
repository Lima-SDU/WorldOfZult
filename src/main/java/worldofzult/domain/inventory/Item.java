package worldofzult.domain.inventory;

public class Item {
    private String name; //Item name
    private String description; //Item description

    // Constructor with item name
    public Item(String name) {
        this.name = name;
    }

    // Constructor with item name and description
    public Item(String name, String description) { //contructor for Item
        this(name); // Reuse other contructor
        this.description = description;
    }

    // Get name
    public String getName(){ //getter for Name
        return name;
    }

    // Get description
    public String getDescription(){return description;}

    // Equals-method for items. Only overloaded, not overrided
    public boolean equals(Item item) {
        return item.getName().equalsIgnoreCase(this.name);
    }
}
