package worldofzult.domain.session;

public class Counter {
    private static int count = 0;

    public static void incrCount(){
        count++;
    }

    public static void resetCount() {
        count = 0;
    }

    public static int getCount() {
        return count;
    }
}