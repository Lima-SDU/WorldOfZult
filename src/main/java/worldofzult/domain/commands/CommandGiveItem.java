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
    public String execute (Context context, String command, String parameters[]) {
        StringBuilder message = new StringBuilder();

        //Check if exactly one item is selected
        if (guardEq(parameters, 1)) {
            message.append("Kun et redskab må gives.\n");
            return message.toString(); //Stops command
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
            if (group.checkItem(parameters[0])) { // Check if the item is correct
                playerInventory.removeItem(playerInventory.getItem(parameters[0])); // Remove from player's inventory
                group.setHunger(false); // Update group status

                // Build string message
                message.append(parameters[0] + " blev givet til gruppen.\n");
                message.append(group.getSpeech2());

                // Set isDone to true and if there is a hungry group, set isDone to false as the game is not finished.
                boolean isDone = true;

                for (Group g : context.getGroups()) {
                    if (g.isHungry()) {
                        isDone = false;
                        break;
                    }
                }

                // If there are no hungry groups, finish game
                if (isDone) {
                    context.makeDone();
                }

                return message.toString();

            } else {
                //if group refuses item, print wrong item
                return message.append(parameters[0] + " kan ikke bruges af gruppen.\n").toString();
            }
        } else {
            //if there is no group in the current space, print no group in this space
            return message.append("Der er ingen gruppe i rummet.\n").toString();
        }
    }
}

