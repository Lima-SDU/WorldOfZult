package worldofzult.domain.session;

public class Counter {
    private int count = 0;

    public void incrCount(){
        count++;
    }

    public void resetCount() {
        count = 0;
    }

    public int getCount() {
        return count;
    }
}