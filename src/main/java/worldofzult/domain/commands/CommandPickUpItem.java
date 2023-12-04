package worldofzult.domain.commands;

import worldofzult.domain.inventory.Inventory;
import worldofzult.domain.inventory.Item;
import worldofzult.domain.session.Context;
import worldofzult.domain.world.Space;

public class CommandPickUpItem extends BaseCommand implements Command {
    //Description of the command
    public CommandPickUpItem() {
        description = "Brug til at samle et redskab op fra et rum. Udføres ved at skrive “Opsaml” + (navn på redskab) fx “Opsaml fiskestang”";
    }

    @Override
    public String execute (Context context, String command, String parameters[]) {
        StringBuilder message = new StringBuilder();

        //Ensure that only 1 item is picked up at a time
        if (guardEq(parameters, 1)) {
            return message.append("Kun et redskab kan samles op ad gangen").toString(); //Stops command
        }

        //Get current space from context
        Space space = context.getCurrent();

        //Get the players inventory
        Inventory playerInventory = context.getPlayer().getInventory();

        //Get the spaces inventory
        Inventory spaceInventory = space.getInventory();

        //Check if user input corresponds to an item in space
        for (Item item : spaceInventory.getItems()) {
            //If it does, place it in inventory and remove from space
            if (item.getName().equalsIgnoreCase(parameters[0])) {
                playerInventory.addItem(item);
                message.append("Samlede " + item.getName() + " op");
                spaceInventory.removeItem(item);
                return message.toString(); //Stops command
            }
        }
        //Prints error, if the input doesn't match any item
        return message.append("Fejl: Redskab ikke fundet").toString();
    }
}
