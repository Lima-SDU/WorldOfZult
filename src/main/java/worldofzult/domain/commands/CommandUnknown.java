package worldofzult.domain.commands;

import worldofzult.domain.session.Context;

public class CommandUnknown extends BaseCommand implements Command {
    @Override
    public String execute (Context context, String command, String parameters[]) {
        StringBuilder message = new StringBuilder();
        return message.append("Ups, jeg forstår ikke '"+command+"' 😕").toString();
    }
}
