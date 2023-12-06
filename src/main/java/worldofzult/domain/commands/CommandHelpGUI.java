package worldofzult.domain.commands;

import worldofzult.domain.session.Context;

public class CommandHelpGUI extends BaseCommand implements Command{
    @Override
    public String execute(Context context, String command, String[] parameters) {
        return "\n Find redskaber på jorden, klik på dem for at samle dem op og find den gruppe som mangler lige det redskabet. " +
                "Når du er har fundet den sultne gruppe så klik på redskabet i dit inventar og gi' redskabet til gruppen for at " +
                "løse deres problem.\n" + "\n";
    }
}
