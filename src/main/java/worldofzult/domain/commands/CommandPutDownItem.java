package worldofzult.domain.commands;

import worldofzult.domain.inventory.Inventory;
import worldofzult.domain.inventory.Item;
import worldofzult.domain.session.Context;
import worldofzult.domain.world.Space;

public class CommandPutDownItem extends BaseCommand implements Command {
    //Description of the command
    public CommandPutDownItem() {
        description = "Brug til at lægge redskaber ned i rummet. Udføres ved at skrive “Læg” + (navn på redskab) fx “Læg fiskestang”";
    }

    @Override
    public String execute (Context context, String command, String parameters[]) {
        StringBuilder message = new StringBuilder();

        //Ensure that only 1 item is put down at a time
        if (guardEq(parameters, 1)) {
            return message.append("Kun et redskab kan lægges ad gangen").toString(); //Stops command
        }

        //Get current space from context
        Space space = context.getCurrent();

        //Get the players inventory
        Inventory playerInventory = context.getPlayer().getInventory();

        //Get the spaces inventory
        Inventory spaceInventory = space.getInventory();

        if (spaceInventory.getItems().size() < 3) {
            //Check if any items name in inventory matches specified input from user
            for (Item item : playerInventory.getItems()) {
                if (item.getName().equalsIgnoreCase(parameters[0])) {
                    //Remove specified item from inventory and add item in space
                    spaceInventory.addItem(item);
                    message.append("Lagde " + item.getName() + " ned");
                    playerInventory.removeItem(item);
                    return message.toString();
                }
            }
            return message.append("Fejl: Redskab ikke fundet").toString();
        } else {
            return message.append("Fejl: Der er ikke plads til flere items i rummet").toString();
        }
    }
}
