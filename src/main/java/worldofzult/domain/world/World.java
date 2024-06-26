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
        Item lys = new Item("Lyspære", "\nDenne pære udsender specifikke bølgelængder af lys, " +
                "ofte blåt og rødt, som er afgørende for fotosyntese og plantevækst." +
                " Dette er også kaldet et vækstlys.\n");
        Item  vandingssystem = new Item("Vandingssystem","\nVandingssystemet opsættes på en farm," +
                " hvor afgrøderne ikke får nok vand fra naturen til at kunne gro." +
                " Dette gør ellers golde områder til frugtbar jord.\n");
        Item biokul = new Item("Biokul","\nBiokul modvirker dårlig jordkvalitet," +
                " som er et problem i nogle lande. Bedre jordkvalitet gør, at man kan dyrke flere varianter" +
                " af afgrøder.\n");
        Item maskine = new Item("Såmaskine", "\nSåmaskine anvendes til såning af korn eller frø." +
                " Hvilket gør landbrugsprodutionen meget mere effektiv.\n");
        Item opbevaringskasse = new Item("Opbevaringskasse", "\nDenne boks skærmer for vind og vejr," +
                " hvilket forlænger holdbarheden af maden." + " På verdensplan går 33% af høsten til spilde.\n");

        // Adds groups
        Group groupDrivhus = new Group(lys, "\nHej %s, her dyrker vi grøntsager," +
                " som f.eks. tomater, agurker og peberfrugter. Vores grøntsager vokser bedst, " +
                "når der er meget dagslys, da lys er nødvendigt for fotosyntesen. Desværre kan vi ikke styre vejret eller hvornår solen går" +
                " ned, derfor er vi meget afhængige af sæsonerne.\n \n", "\nLyspærer? Genialt! Det kan vi sætte op i drivhusene, så der også kan være lys når solen ikke skinner.\n \n");
        Group groupVand = new Group(vandingssystem, "\nHey %s! Her på vores gård bliver der dyrket en del frugt, " +
                " især bananer. Problemet er at mange af dem ender som uspiselige," +
                " da de ender som små tøre gnallinger. Dette skyldes at jorden er alt for tør. " +
                "Vi laver endda en regndans hver morgen, men det ser desværre ikke ud til at virke.\n \n", "\nWow! Et helt vandingssystem, det vil vi gøre godt brug af. Nu behøver vi ikke lave flere regndanse. Mange tak!\n \n");
        Group groupKul = new Group(biokul, "\nHej %s, vi har rigtigt meget landbrugsjord, " +
                "som vi rigtigt gerne vil dyrke vores afgrøder på. Vi har dog et stort problem, som forhindrer os i at dyrke noget på det. " +
                "Jordkvaliteten er for dårlig til at vores planter kan gro i det. Vil du hjælpe os?\n \n", "\nJuhuu, biokul!!! Det vil forbedre vores jordkvalitet en hel del, det er perfekt for vores afgrøder." +
                " Mange tak!\n \n");
        Group groupMaskine = new Group(maskine, "\nHej %s! Vi er landmænd, og gror korn. " +
                "Vi har længe selv stået for at så vores korn, men det tager lang tid og processen er hård." +
                " Kan du komme i tanke om en maskine som ville kunne hjælpe med at så korn mere effektivt?\n \n", "\nJubii! Tusind tak for såmaskinen, nu kan vi så vores korn på en meget mere effektiv måde!\n \n");
        Group groupOpbevaringskasse = new Group(opbevaringskasse, "\nHej %s! Vi har nogle afgrøder" +
                " som producerer en masse mad. Vi prøver at udnytte så meget af det som vi kan nå, men maden fordærver desværre for hurtigt og en del ender med at gå til spilde" +
                ". Vi ville ønske at maden kunne holde længere.\n \n", "\nTusind tak for opbevaringskassen, nu kan vi opbevare vores mad i meget længere tid, inden det fordærver. Nu vil vores mad ikke længere gå til spilde.\n \n");

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

        // Adds edges to the rooms / paths between the rooms
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

        // Set entry / spawnpoint for the player
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


