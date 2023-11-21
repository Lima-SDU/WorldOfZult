package worldofzult.domain.commands;

import worldofzult.domain.session.Context;

public interface Command {
    void execute (Context context, String command, String parameters[]);
    String getDescription ();
}