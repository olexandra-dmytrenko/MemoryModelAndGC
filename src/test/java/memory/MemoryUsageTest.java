package memory;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by olexandra on 12/3/17.
 */
public class MemoryUsageTest {

    @Test
    public void mainObjectsSizes() {
        System.out.println("Double size = " + Double.SIZE / Byte.SIZE);
        assertEquals(8, Byte.SIZE);
        assertEquals(16, Character.SIZE);
        assertEquals(16, Short.SIZE);
        assertEquals(32, Integer.SIZE);
        assertEquals(32, Float.SIZE);
        assertEquals(64, Long.SIZE);
        assertEquals(64, Double.SIZE);
    }

    /**
     * Adding integers to string is not very expensive, still loads memory heavily.
     */
    @Test
    public void eternalCycleWithInlinedString() {
        String a = "Very long string";
        int j = 1;
        while (true) {
            int i = j + 1;
            a = a + i;
            j = i;
        }
    }

    /**
     * Run this method with -Xmx6g property set which gives 6 gig for the heap, pause it before the OOME (24-25 iteration)
     */
    @Test
    public void causeOutOfMemoryError() throws InterruptedException {
        MyString string = new MyString("My very long string");
        int i = 0;
        while (true) {
            System.out.println("Iteration " + ++i);
            Thread.sleep(100);
            string.setMyString(string.getMyString() + string.getMyString());
        }
    }

    /**
     * combination of an object and ints. Look at how much space is consumed by MyString, String and char[],
     * query in jvisualvm in the Heap dump for the Strings that consume to much
     */
    @Test
    public void eternalCycleWithObject() {
        MyString a = new MyString("Very long string");
        int j = 1;
        while (true) {
            int i = j + 1;
            a.setMyString(a.getMyString() + i);
            j = i;
        }
    }
}