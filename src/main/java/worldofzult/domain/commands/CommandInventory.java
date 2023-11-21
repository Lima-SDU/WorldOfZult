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
    public void execute (Context context, String command, String parameters[]) {
        Space space = context.getCurrent();

        //Get the spaces inventory
        Inventory spaceInventory = space.getInventory();

        //Print items in space
        if (!spaceInventory.getItems().isEmpty()) {
            System.out.println("Følgende redskaber ligger i rummet");
            for (Item item : spaceInventory.getItems()) {
                System.out.println(item.getName());
            }
        } else {
            System.out.println("Der er ingen redskaber i rummet");
        }

        //Get the players inventory
        Inventory playerInventory = context.getPlayer().getInventory();

        //Print items in player
        if (!playerInventory.getItems().isEmpty()) {
            System.out.println("Følgende redskaber ligger i inventaret");
            for (Item item : playerInventory.getItems()) {
                System.out.println(item.getName());
            }
        } else {
            System.out.println("Der er ingen redskaber i inventaret");
        }
    }
}