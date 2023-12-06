package worldofzult.presentation;

import worldofzult.domain.Domain;
import worldofzult.domain.menus.Startscreen;

import java.util.Scanner;


public class CLIApplication {
    private static Scanner scanner;

    // Main-loop
    public static void main (String args[]) {
        boolean isDone = false;

        while (!isDone) {
            scanner = new Scanner(System.in);
            Domain domain = new Domain();

            Startscreen startscreen = new Startscreen();
            String name = startscreen.displayStartscreen(scanner);

            domain.setPlayerName(name);
            startscreen.printWelcomeMessage();
            domain.runCommand("hjælp");

            System.out.println("Du kan vælge mellem 3 sværhedsgrader:\n" +
                    "'1' (Let)    : 5 pladser i inventory\n" +
                    "'2' (Medium) : 3 pladser i inventory\n" +
                    "'3' (Svær)   : 1 plads i inventory\n");
            System.out.print("Indtast ønsket sværhedsgrad (1/2/3): ");
            String capacity = scanner.nextLine();

            while ((!capacity.equals("1") && !capacity.equals("2") && !capacity.equals("3"))) {
                System.out.print("Indtast ønsket sværhedsgrad (1/2/3): ");
                capacity = scanner.nextLine();
            }

            switch (capacity) {
                case "1" -> domain.setCapacity(5);
                case "2" -> domain.setCapacity(3);
                case "3" -> domain.setCapacity(1);
            }

            while (domain.checkIsDone() == false) { // Runs game if not done
                System.out.print("> ");
                String line = scanner.nextLine(); // Get next line from commandline input
                String message = domain.runCommand(line); // Run command from registry
                System.out.println(message);
            }

            System.out.println("Godt gået " + name + "! Du har klaret spillet, men der er en quiz inden spillet er helt slut. Den skal teste, hvor opmærksom du har været undervejs i spillet. Der kommer en række spørgsmål og de har hver tre valgmuligheder, hvor én af dem er korrekt. Du skal vælge det rigtige svar.\n");

            domain.runQuizCLI();

            System.out.println("Tak for at spille! Du er nu helt færdig og klarede det på " + domain.getCount() + " træk. Sult er et stort problem i nogle dele af verden, så vi håber, at du lærte noget nyt og selvfølgelig at spillet var spændende. \\^o^/\n\n");

            System.out.print("Ønsker du at genstarte spillet? (Ja/Nej): ");
            String doneText = scanner.nextLine();

            while (!doneText.equalsIgnoreCase("ja") && !doneText.equalsIgnoreCase("nej")) {
                System.out.print("Ønsker du at genstarte spillet? (Ja/Nej): ");
                doneText = scanner.nextLine();
            }

            if (doneText.equalsIgnoreCase("nej")) {
                isDone = true;
            }
        }
    }
}
