package worldofzult.domain.commands;

import worldofzult.domain.inventory.Inventory;
import worldofzult.domain.inventory.Item;
import worldofzult.domain.session.Context;
import worldofzult.domain.world.Space;

public class CommandInventory extends BaseCommand implements Command {
    //Description of the command
    public CommandInventory() {
        description = "Brug til at tjekke redskaberne i din inventar samt redskaberne i rummet. Udføres ved at skrive “Inventar”";
    }

    @Override
    public String execute (Context context, String command, String parameters[]) {
        StringBuilder message = new StringBuilder();

        Space space = context.getCurrent();

        //Get the spaces inventory
        Inventory spaceInventory = space.getInventory();

        //Print items in space
        if (!spaceInventory.getItems().isEmpty()) {
            message.append("Følgende redskaber ligger i rummet:\n");
            for (Item item : spaceInventory.getItems()) {
                message.append("- " + item.getName() + "\n");
            }
        } else {
            message.append("Der er ingen redskaber i rummet\n");
        }

        //Get the players inventory
        Inventory playerInventory = context.getPlayer().getInventory();

        //Print items in player
        if (!playerInventory.getItems().isEmpty()) {
            message.append("\nFølgende redskaber ligger i inventaret: \n");
            for (Item item : playerInventory.getItems()) {
                message.append("- " + item.getName() + "\n");
            }
        } else {
            message.append("\nDer er ingen redskaber i inventaret\n");
        }

        return message.toString();
    }
}