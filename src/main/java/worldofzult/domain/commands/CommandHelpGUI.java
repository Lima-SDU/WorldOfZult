package worldofzult.domain.commands;

import worldofzult.domain.session.Context;

public class CommandHelpGUI extends BaseCommand implements Command{
    @Override
    public String execute(Context context, String command, String[] parameters) {
        return "\nTal med folk og undersøg hvilke udfordringer de har. " +
                "Find redskaber på jorden, klik på dem for at samle dem op og find den gruppe som mangler lige det redskab. " +
                "Når du har fundet den rette, så klik på redskabet i dit inventar og giv redskabet til dem for at " +
                "løse deres problem.\n" + "\n";
    }
}
