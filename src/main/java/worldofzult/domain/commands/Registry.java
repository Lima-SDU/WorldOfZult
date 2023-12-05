package worldofzult.domain.commands;

import java.util.HashMap;
import java.util.Map;
import worldofzult.domain.session.Context;

public class Registry {
    private Context context;
    private Command fallback;

    // Map, which contains string-keyword and their associated command
    private Map<String, Command> commands = new HashMap<String, Command>();

    // Constructor
    public Registry(Context context, Command fallback) {
        this.context = context; // Gets given context to get session information
        this.fallback = fallback; // Fallback for incorrect command
    }

    // Add Commands.Command to register (Map)
    public void register (String name, Command command) {
        commands.put(name, command);
    }

    // Handles and runs commands from player input
    public String dispatch (String line) {
        String[] elements = line.split(" "); //Splits string into string-array. go door -> {go, door}
        String command = elements[0].toLowerCase(); // Get command from string-array
        String[] parameters = getParameters(elements); // Get parameters
        Command handler = getCommand(command); // Retrieves command. If not a recognized command, it returns null
        String message = (handler==null ? fallback : handler).execute(context, command, parameters); // Runs command if there is no error
        System.out.println(message);
        return message;
    }

    // Get command
    public Command getCommand (String commandName) {
        return commands.get(commandName);
    }

    // Get all commands
    public String[] getCommandNames () {
        return commands.keySet().toArray(new String[0]);
    }

    // helpers

    private String[] getParameters (String[] input) {
        String[] output = new String[input.length-1];
        for (int i=0 ; i<output.length ; i++) {
            output[i] = input[i+1];
        }
        return output;
    }
}