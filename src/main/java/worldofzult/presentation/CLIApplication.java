package worldofzult.presentation;

import worldofzult.domain.Domain;
import worldofzult.domain.menus.Startscreen;

import java.util.Scanner;


public class CLIApplication {
    private static Scanner scanner;

    // Main-loop
    public static void main (String args[]) {
        scanner = new Scanner(System.in);
        Domain domain = new Domain();


        Startscreen startscreen = new Startscreen();
        String name = startscreen.displayStartscreen(scanner);

        domain.setPlayerName(name);
        startscreen.printWelcomeMessage();
        domain.runCommand("hjælp");

        domain.setCapacity(5);

        while (domain.checkIsDone()==false) { // Runs game if not done
            System.out.print("> ");
            String line = scanner.nextLine(); // Get next line from commandline input
            String message = domain.runCommand(line); // Run command from registry
            System.out.println(message);
        }

        System.out.println("Godt gået " + name + "! Du har klaret spillet, men der er en quiz inden spillet er helt slut. Den skal teste, hvor opmærksom du har været undervejs i spillet. Der kommer en række spørgsmål og de har hver tre valgmuligheder, hvor én af dem er korrekt. Du skal vælge det rigtige svar.");

        //Quiz quiz = new Quiz();
        //quiz.initQuiz();
        //quiz.run();

        System.out.println("Tak for at spille! Du er nu helt færdig og klarede det på " + domain.getCount() + " træk. Sult er et stort problem i nogle dele af verden, så vi håber, at du lærte noget nyt og selvfølgelig at spillet var spændende \\^o^/");

    }
}
