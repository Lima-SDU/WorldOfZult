package worldofzult.domain.session;

// Class for counting moves by the player. Can be reused to count other things.
public class Counter {
    // Initialize count
    private int count = 0;

    // Increment count
    public void incrCount(){
        count++;
    }

    // Reset count
    public void resetCount() {
        count = 0;
    }

    // Get count
    public int getCount() {
        return count;
    }
}