// ID: 205694441

package other;

/**
 * A class of counter.
 * The class support basic operations - add, remove and get value.
 */
public class Counter {
    // the counter's field
    private int counter;

    /**
     * A constructor method.
     */
    public Counter() {
        this.counter = 0;
    }

    /**
     * This method increases the value of the count.
     * @param number value that is added to the count.
     */
    public void increase(int number) {
        this.counter += number;
    }

    /**
     * This method decreases the value of the count.
     * @param number value that is removed from the count.
     */
    public void decrease(int number) {
        this.counter -= number;
    }

    /**
     * This method returns the count.
     * @return the count.
     */
    public int getValue() {
        return this.counter;
    }
}