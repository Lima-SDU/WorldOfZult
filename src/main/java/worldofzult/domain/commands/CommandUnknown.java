package worldofzult.domain.commands;

import worldofzult.domain.session.Context;

public class CommandUnknown extends BaseCommand implements Command {
    @Override
    public void execute (Context context, String command, String parameters[]) {
        System.out.println("Ups, jeg forstår ikke '"+command+"' 😕");
    }
}
