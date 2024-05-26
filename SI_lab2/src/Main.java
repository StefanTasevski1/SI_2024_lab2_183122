import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Item item1 = new Item("Item1", "123456", 200, 0.1f);
        Item item2 = new Item("Item2", "789012", 150, 0);
        Item item3 = new Item("Item3", "034567", 350, 0.4f); // Special case for discount and price > 300

        // Create a list of items
        List<Item> items = Arrays.asList(item1, item2, item3);

        // Define the payment amount
        int payment = 500;

        // Check if the payment is sufficient
        boolean result = SILab2.checkCart(items, payment);
        System.out.println("Is the payment sufficient? " + result);
    }
}