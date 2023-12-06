package worldofzult.domain.game;

import java.util.Scanner;
import worldofzult.domain.menus.Quiz;
import worldofzult.domain.commands.*;
import worldofzult.domain.menus.Startscreen;
import worldofzult.domain.player.Player;
import worldofzult.domain.session.Context;
import worldofzult.domain.world.World;
import worldofzult.domain.session.Counter;

public class Game {
    // Creates world with rooms
    private World world = new World();

    // Creates a counter for the game
    private Counter counter = new Counter();

    // Creates context with the start room
    private Context context  = new Context(world.getEntry(), world.getGroups(), counter);

    // Fallback for misinput from player
    private Command fallback = new CommandUnknown();

    // Creates registry for commands
    private Registry registry = new Registry(context, fallback);

    // Adds new commands with their name and attached command-instance in the Registry
    public void initRegistry () {
        // Add initial commands
        Command cmdExit = new CommandExit();
        registry.register("afslut", cmdExit);
        registry.register("gå", new CommandGo());
        registry.register("hjælp", new CommandHelp(registry));

        //Add our own commands
        registry.register("giv", new CommandGiveItem());
        registry.register("opsaml", new CommandPickUpItem());
        registry.register("læg", new CommandPutDownItem());
        registry.register("tal", new CommandTalk());
        registry.register("inventar", new CommandInventory());
        registry.register("hjælpgui", new CommandHelpGUI());
    }

    // Get context
    public Context getContext() {
        return context;
    }

    // Get registry
    public Registry getRegistry() {
        return registry;
    }
}