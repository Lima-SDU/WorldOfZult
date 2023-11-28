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
    public void execute (Context context, String command, String[] parameters) {
        StringBuilder sb = new StringBuilder();

        String[] commandNames = registry.getCommandNames();
        Arrays.sort(commandNames);

        // find max length of command name
        int max = 0;
        for (String commandName: commandNames) {
            int length = commandName.length();
            if (length>max) max = length;
        }

        // present list of commands

        //System.out.println("Kommandoer:");
        sb.append("Kommandoer:\n");

        for (String commandName: commandNames) {
            String description = registry.getCommand(commandName).getDescription();

            //System.out.printf(" - %-"+max+"s %s%n", commandName, description);
            sb.append(String.format(" - %-"+max+"s %s%n", commandName, description));
        }

        System.out.println(sb.toString());
    }
}