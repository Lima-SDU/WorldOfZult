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
    public void execute (Context context, String command, String parameters[]) {
        //Ensure that only 1 item is put down at a time
        if (guardEq(parameters, 1)) {
            System.out.println("Kun et redskab kan lægges ad gangen");
            return; //Stops command
        }

        //Get current space from context
        Space space = context.getCurrent();

        //Get the players inventory
        Inventory playerInventory = context.getPlayer().getInventory();

        //Get the spaces inventory
        Inventory spaceInventory = space.getInventory();

        //Check if any items name in inventory matches specified input from user
        for (Item item : playerInventory.getItems()) {
            if (item.getName().equalsIgnoreCase(parameters[0])) {
                //Remove specified item from inventory and add item in space
                spaceInventory.addItem(item);
                System.out.println("Lagde " + item.getName() + " ned");
                playerInventory.removeItem(item);
                return;
            }
        }
        System.out.println("Fejl: Redskab ikke fundet");
    }
}
