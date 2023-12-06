package worldofzult.domain.commands;

import worldofzult.domain.character.Group;
import worldofzult.domain.session.Context;
import worldofzult.domain.world.Space;

public class CommandTalk extends BaseCommand implements Command {
    //Description of the command
    public CommandTalk() {
        description = "Brug til at tale med de forskellige grupper i verden. Udføres ved at skrive “tal” i et rum der indeholder en gruppe\n";
    }
    @Override
    public String execute (Context context, String command, String parameters[]) {
        StringBuilder message = new StringBuilder();

        //Get current space from context
        Space space = context.getCurrent();

        //Get the group from the current space
        Group group = space.getGroup();

        //If space has group, print out the groups message
        if (group != null) {
            //Returns two different messages, depending on the hungry status of the group
            if (group.isHungry()) {
                return message.append(String.format(group.getSpeech1(),context.getPlayer().getName())).toString();
            } else {
                return message.append(group.getSpeech2()).toString();
            }
        } else {
            //Prints out an error, as there is no group in the space
            return message.append("\nDer er ingen gruppe i rummet du kan snakke med\n").toString();
        }
    }
}