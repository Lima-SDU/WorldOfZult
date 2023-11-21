package worldofzult.domain.inventory;

public class Item {
    private String name; //Item name
    private String description; //Item description

    public Item(String name, String description) { //contructor for Item
        this.name = name;
        this.description =description;
    }

    public Item(String name) {
        this.name = name;
        this.description = "";
    }

    public String getName(){ //getter for Name
        return name;
    }
    public String getDescription(){return description;}

    public boolean equals(Item item) {
        return item.getName().equalsIgnoreCase(this.name);
    }

    //public boolean equals(String itemName) {
    //    return this.name.equalsIgnoreCase(itemName);
    //}
}
