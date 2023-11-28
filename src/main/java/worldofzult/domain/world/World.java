package worldofzult.domain.world;

import java.util.ArrayList;
import java.util.Collections;

import worldofzult.domain.character.Group;
import worldofzult.domain.inventory.Item;

public class World {
    private Space entry;
    private ArrayList<Group> groups;
    private ArrayList<Item> items;

    // Constructor
    public World () {
        // Adds items
        Item lys = new Item("Lyspære", "Denne pære udsender specifikke bølgelængder af lys, " +
                "ofte blåt og rødt, som er afgørende for fotosyntese og plantevækst." +
                " Dette er også kaldet et vækstlys.");
        Item  vandingssystem = new Item("Vandingssystem","Vandingssystemet opsættes på en farm," +
                " hvor afgrøderne ikke får nok vand fra naturen til at kunne gro." +
                " Dette gør ellers golde områder til frugtbar jord.");
        Item biokul = new Item("Biokul","Biokul modvirker dårlig jordkvalitet," +
                " som er et problem i nogle lande. Bedre jordkvalitet gør, at man kan dyrke flere varianter" +
                " af afgrøder.");
        Item maskine = new Item("Såmaskine", "Anvendes til såning af korn eller frø.");
        Item opbevaringskasse = new Item("Opbevaringskasse", "Denne boks skærmer for vind og vejr," +
                " hvilket forlænger holdbarheden af maden.");


        // Adds groups
        Group groupDrivhus = new Group(lys, "Hej %s, her dyrker vi grøntsager," +
                " som f.eks. tomater, agurker og peberfrugter. Vores grøntsager vokser bedst, " +
                "når der er meget dagslys. Desværre kan vi ikke styre vejret eller hvornår solen går" +
                " ned, så vi er meget afhængige af sæsonerne.\n", "Lyspærer? Mange tak! Det vil vi sætte op i drivhusene, så der også kan være lys når solen ikke skinner.");
        Group groupVand = new Group(vandingssystem, "Hey %s! Her på vores gård dyrkes der forskellige typer frugter," +
                " alt fra citrusfrugter til æbler. Mange af de høstede frugter er næsten uspiselige," +
                " da de ender som små gnallinger. Der er nemlig ret tørt, der hvor vi dyrker.  " +
                "Vi laver en regndans hver morgen, men det ser desværre ikke ud til at virke.\n", "Wow! Et helt vandingssystem, det var da flinkt af dig. Nu behøver vi ikke lave flere regndanse. Mange tak!");
        Group groupKul = new Group(biokul, "Hej %s, vi har rigtigt meget landbrugsjord, " +
                "som vi rigtigt gerne vil dyrke vores afgrøder på. Vi har dog et stort problem, som forhindrer os i at dyrke noget på det. " +
                "Jordkvaliteten er for dårlig til at vores planter kan gro i det. Vil du hjælpe os?\n", "Juhuu, biokul!!! Det er lige det, som vores jord mangler, til at dyrke afgrøder på. \n" +
                "Mange tak!\n");
        Group groupMaskine = new Group(maskine, "Hej %s! Vi er landmænd, og gror korn." +
                "Vi har længe selv stået for at så vores korn, men det tager lang tid og processen er hård." +
                " Kan du komme i tanke om en maskine som ville kunne hjælpe med at så korn mere effektivt?\n", "Jubii! Tusind tak for såmaskinen, nu kan vi så vores korn på en meget mere effektiv måde!");
        Group groupOpbevaringskasse = new Group(opbevaringskasse, "Hej %s! Vi har nogle afgrøder" +
                " som producerer dejlig mad. Vi spiser så meget af det som vi kan komme til, men maden fordærver desværre for hurtigt til at vi kan" +
                " spise det hele. Vi ville ønske at maden holdt lidt længere.\n", "Tusind tak for opbevaringskassen, nu kan vi opbevare vores mad bedre, sådan at vores mad holder længere og ikke går til spilde.");

        // Adds groups to the list of groups
        this.groups = new ArrayList<Group>();
        groups.add(groupDrivhus);
        groups.add(groupVand);
        groups.add(groupKul);
        groups.add(groupMaskine);
        groups.add(groupOpbevaringskasse);

        // Adds rooms
        Space entry  = new Space("Indgang", "Du befinder dig i indgangen til World of Zult");
        Space space1 = new Space("Strand", "Her er der en flot strand med hvidt sand og turkisblåt vand. Det ligner, at der er et koralrev lidt længere ude.\n");
        Space space2 = new Space("Monsunland", "Her ligger et lille landbrugssamfund, vejret er vådt og varmt.\n", groupOpbevaringskasse);
        Space space3 = new Space("Eng", "Der er en vidtstrækkende eng med højt græs og vilde blomster. En å snor sig langsomt gennem landskabet.\n");
        Space space4 = new Space("Bondegård", "En bondegård med en gruppe landmænd og en tom mark. \n", groupMaskine);
        Space space5 = new Space("Jordland", "Her er et land med meget dårlig jordkvalitet.\n", groupKul);
        Space space6 = new Space("Frugtplantage", "Her er der en stor frugtplantage med flinke mennesker, men der er godt nok tørt.\n", groupVand);
        Space space7 = new Space("Bjergkæde", "Du står ved bunden af en bjergkæde. De sneklædte bjerge er kilometer høje og toppene er gemt bagved et lag skyer.\n");
        Space space8 = new Space("Drivhus-land", "Her er en gård med flere drivhuse. Ved det ene drivhus står to personer. \n", groupDrivhus);

        // Adds the items to the list of items
        this.items = new ArrayList<Item>();
        items.add(lys);
        items.add(vandingssystem);
        items.add(biokul);
        items.add(maskine);
        items.add(opbevaringskasse);

        // Adds spaces for the itemRandomizer
        ArrayList<Space> spaces = new ArrayList<Space>();
        spaces.add(space1);
        spaces.add(space2);
        spaces.add(space3);
        spaces.add(space4);
        spaces.add(space5);
        spaces.add(space6);
        spaces.add(space7);
        spaces.add(space8);

        ArrayList<Space> copy = (ArrayList<Space>) spaces.clone();

        // ItemRandomizer - for each item, shuffle the spaces and check if the first space is empty or has the "wrong" group
        // and add the item, else remove the space and shuffle again
        for (Item item : items) {
            while (true) {
                Collections.shuffle(spaces);
                if (spaces.get(0).getGroup() == null || !spaces.get(0).getGroup().checkItem(item.getName())) {
                    spaces.get(0).addItem(item);
                    spaces.remove(0);
                    break;
                }
            }
        }

        /*
        // Test af randomizer
        for (Space space : copy) {
            if (!space.getInventory().getItems().isEmpty()) {
                System.out.println("Name: " + space.getName() +
                        "Item: " + space.getInventory().getItems().get(0).getName()
                );
            } else {
                System.out.println("Name: " + space.getName() + " - No item");
            }
        }
        */

        entry.addEdge("syd", space2);
        space2.addEdge("nord", entry);
        space2.addEdge("øst", space3);
        space2.addEdge("syd", space6);
        space2.addEdge("vest", space1);
        space1.addEdge("øst", space2);
        space1.addEdge("syd", space5);
        space3.addEdge("vest", space2);
        space3.addEdge("øst", space4);
        space4.addEdge("syd", space7);
        space4.addEdge("vest", space3);
        space5.addEdge("øst", space6);
        space6.addEdge("øst", space7);
        space6.addEdge("vest", space5);
        space7.addEdge("vest", space6);
        space7.addEdge("nord", space4);
        space7.addEdge("syd", space8);
        space8.addEdge("nord", space7);

        this.entry = entry;
    }

    // Get spawnpoint / entry to the world
    public Space getEntry () {
        return entry;
    }

    // Get list of groups
    public ArrayList<Group> getGroups() {
        return groups;
    }
}


