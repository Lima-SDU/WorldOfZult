package worldofzult.domain.commands;

import java.util.Arrays;
import worldofzult.domain.session.Context;

public class CommandHelp extends BaseCommand implements Command {
    private Registry registry;

    public CommandHelp(Registry registry) {
        this.registry = registry;
        this.description = "Brug til at få en liste over de forskellige handlinger, du kan foretage dig. Udføres ved at skrive “hjælp”";
    }

    @Override
    public String execute (Context context, String command, String[] parameters) {
        StringBuilder message = new StringBuilder();

        // Get names of commands and sort alphabetically
        String[] commandNames = registry.getCommandNames();
        Arrays.sort(commandNames);

        // find max length of command name
        int max = 0;
        for (String commandName: commandNames) {
            int length = commandName.length();
            if (length>max) max = length;
        }


        message.append("Kommandoer:\n");

        // Append each command to the StrinBuilder with their name and description
        for (String commandName: commandNames) {
            String description = registry.getCommand(commandName).getDescription();

            message.append(String.format(" - %-"+max+"s %s%n", commandName, description));
        }

        return message.toString();
    }
}