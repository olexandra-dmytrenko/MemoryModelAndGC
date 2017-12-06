package memory;

/**
 * Created by olexandra on 12/4/17.
 */
public class OutOfMemErrorGenerator {
    /**
     * Check if more iterations are possible through using main or through the jUnit.
     * @param args
     */
    public static void main(String[] args) {
        MyString string = new MyString("My very long string");
        int i = 0;
        while (true) {
            System.out.println("Iteration " + ++i);
            string.setMyString(string.getMyString() + string.getMyString());
        }
    }
}
