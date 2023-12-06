package worldofzult.domain.menus;

import java.util.Scanner;
import worldofzult.domain.commands.Registry;

public class Startscreen {
    private String name; // Name of the player

    // Displays startscreen. Also asks for the name of players
    public String displayStartscreen (Scanner scanner) {
        // Display Startscreen and get user-input on their name and difficulty
        System.out.println("\n" +
                " _    _            _     _ _____  __ ______      _ _   \n" +
                "| |  | |          | |   | |  _  |/ _|___  /     | | |  \n" +
                "| |  | | ___  _ __| | __| | | | | |_   / / _   _| | |_ \n" +
                "| |/\\| |/ _ \\| '__| |/ _` | | | |  _| / / | | | | | __|\n" +
                "\\  /\\  / (_) | |  | | (_| \\ \\_/ / | ./ /__| |_| | | |_ \n" +
                " \\/  \\/ \\___/|_|  |_|\\__,_|\\___/|_| \\_____/\\__,_|_|\\__|\n" +
                "                                                       \n" +
                "                                                       \n");

        System.out.print("Indtast navn: ");

        this.name = scanner.nextLine();
        return this.name;
    }

    // Prints welcome message
    public void printWelcomeMessage() {
        String message = "\nHej " + name + "! Velkommen til World of Zult!\n" +
                "Dette spil handler om at stoppe sult ved at udforske denne fiktive verden, " +
                "som er fyldt med grupper af mennesker, der alle sulter af forskellige årsager." +
                "\nDit mål er at finde ud af hvad de har brug for og opsamle og aflevere de " +
                "rigtige redskaber til de rigtige grupper.\nPrøv at snakke med de forskellige " +
                "grupper for at høre hvad de har brug for.\nLyt godt med, for der kommer en " +
                "quiz til sidst. Her er en liste med forskellige commands:\n";
        System.out.println(message);
        System.out.println();
    }
}
