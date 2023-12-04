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
    private static World world    = new World();

    // Creates context with the start room
    private static Context context  = new Context(world.getEntry(), world.getGroups());

    // Fallback for misinput from player
    private static Command fallback = new CommandUnknown();

    // Creates registry for commands
    private static Registry registry = new Registry(context, fallback);

    // Scanner for getting player input
    private static Scanner scanner  = new Scanner(System.in);


    // Adds new commands with their name and attached command-instance in the Registry
    public static void initRegistry () {
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
    }

    public static Context getContext() {
        return context;
    }

    public static Registry getRegistry() {
        return registry;
    }

    // Main-loop
    //public static void main (String args[]) {
        //initRegistry(); // Initializes registry

        /*
        Startscreen startscreen = new Startscreen();
        String name = startscreen.displayStartscreen(scanner);

        Player player = new Player(name);
        context.setPlayer(player);

        startscreen.printWelcomeMessage(registry);

        context.getCurrent().welcome(); // Runs welcome command for first room

        while (context.isDone()==false) { // Runs game if not done
            System.out.print("> ");
            String line = scanner.nextLine(); // Get next line from commandline input
            registry.dispatch(line); // Run command from registry
        }

        System.out.println("Godt gået " + context.getPlayer().getName() + "! Du har klaret spillet, men der er en quiz inden spillet er helt slut. Den skal teste, hvor opmærksom du har været undervejs i spillet. Der kommer en række spørgsmål og de har hver tre valgmuligheder, hvor én af dem er korrekt. Du skal vælge det rigtige svar.");

        Quiz quiz = new Quiz();
        quiz.initQuiz();
        quiz.run();

        System.out.println("Tak for at spille! Du er nu helt færdig og klarede det på " + Counter.getCount() + " træk. Sult er et stort problem i nogle dele af verden, så vi håber, at du lærte noget nyt og selvfølgelig at spillet var spændende \\^o^/");
        */
    //}
}