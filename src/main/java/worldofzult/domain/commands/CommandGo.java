package worldofzult.domain.commands;

import worldofzult.domain.session.Context;

public class CommandGo extends BaseCommand implements Command {
    //Description of command
    public CommandGo () {
        description = "Brug til at gå mellem forskellige rum. Udføres ved at skrive “gå + (retning du vil gå i)” fx “Gå nord”";
    }

    @Override
    public String execute (Context context, String command, String[] parameters) {
        StringBuilder message = new StringBuilder();

        if (guardEq(parameters, 1)) { // Checks for the right amount of parameters, so if too many parameters, give error
            return message.append("Jeg ved ikke hvor det er henne 🤔").toString(); // Stops command
        }
        context.transition(parameters[0]); // Transistions only if given one parameter
        return message.toString();
    }
}