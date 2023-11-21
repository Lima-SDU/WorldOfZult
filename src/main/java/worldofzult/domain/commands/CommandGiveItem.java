package worldofzult.domain.commands;

import worldofzult.domain.character.Group;
import worldofzult.domain.inventory.Inventory;
import worldofzult.domain.session.Context;
import worldofzult.domain.world.Space;

public class CommandGiveItem extends BaseCommand implements Command {
    //Description of the command
    public CommandGiveItem() {
        description = "Brug til at give grupper de redskaber de skal bruge. Udføres ved at skrive “Giv” + (navn på redskab) fx “Giv fiskestang”";
    }

    @Override
    public void execute (Context context, String command, String parameters[]) {
        //Check if exactly one item is selected
        if (guardEq(parameters, 1)) {
            System.out.println("Fejl kun et redskab må gives");
            return; //Stops command
        }

        //Get current space from context
        Space space = context.getCurrent();

        //Get the group in the current space
        Group group = space.getGroup();

        //Get the players inventory
        Inventory playerInventory = context.getPlayer().getInventory();

        //Check if space has a group
        if (group != null) {
            //Try to give specified item to group
            if (group.checkItem(parameters[0])) {
                playerInventory.removeItem(playerInventory.getItem(parameters[0]));
                group.setHunger(false);
                System.out.println(parameters[0] + " blev givet til gruppen");
                System.out.println(group.getSpeech2());

                boolean isDone = true;

                for (Group g : context.getGroups()) {
                    if (g.isHungry()) {
                        isDone = false;
                        break;
                    }
                }

                if (isDone) {
                    context.makeDone();
                }

            } else {
                //if group refuses item, print wrong item
                System.out.println("Fejl: " + parameters[0] + " kan ikke bruges af gruppen");
            }
        } else {
            //if there is no group in the current space, print no group in this space
            System.out.println("Fejl: Der er ingen gruppe i rummet");
        }
    }
}

