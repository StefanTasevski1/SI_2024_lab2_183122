import org.junit.jupiter.api.Test;

import org.main.Lab2;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class SILab2Test {

    @Test
    void testEveryBranch() {
        // Test Case 1
        Exception exception1 = assertThrows(RuntimeException.class, () -> Lab2.SILab2.checkCart(null, 100));
        assertEquals("allItems list can't be null!", exception1.getMessage());

        // Test Case 2
        assertTrue(Lab2.SILab2.checkCart(Collections.emptyList(), 100));

        // Test Case 3
        assertTrue(Lab2.SILab2.checkCart(List.of(new Lab2.Item(null, "123456", 100, 0)), 100));

        // Test Case 4
        Exception exception4 = assertThrows(RuntimeException.class, () -> Lab2.SILab2.checkCart(List.of(new Lab2.Item("item", "12a456", 100, 0)), 100));
        assertEquals("Invalid character in item barcode!", exception4.getMessage());

        // Test Case 5
        assertTrue(Lab2.SILab2.checkCart(List.of(new Lab2.Item("item", "123456", 100, 0.1f)), 10));

        // Test Case 6
        Exception exception6 = assertThrows(RuntimeException.class, () -> Lab2.SILab2.checkCart(List.of(new Lab2.Item("item", null, 100, 0)), 100));
        assertEquals("No barcode!", exception6.getMessage());

        // Test Case 7
        assertTrue(Lab2.SILab2.checkCart(List.of(new Lab2.Item("item", "012345", 400, 0.1f)), 70));

        // Test Case 8
        assertFalse(Lab2.SILab2.checkCart(List.of(new Lab2.Item("item", "123456", 100, 0)), 50));
    }

    @Test
    void testMultipleCondition() {
        // Test Case 1
        Lab2.Item item1 = new Lab2.Item("item", "012345", 400, 0.1f);
        assertTrue(Lab2.SILab2.checkCart(List.of(item1), 70));

        // Test Case 2
        Lab2.Item item2 = new Lab2.Item("item", "012345", 300, 0.1f);
        assertFalse(Lab2.SILab2.checkCart(List.of(item2), 70));

        // Test Case 3
        Lab2.Item item3 = new Lab2.Item("item", "012345", 400, 0f);
        assertFalse(Lab2.SILab2.checkCart(List.of(item3), 70));

        // Test Case 4
        Lab2.Item item4 = new Lab2.Item("item", "112345", 400, 0.1f);
        assertFalse(Lab2.SILab2.checkCart(List.of(item4), 70));

        // Test Case 5
        Lab2.Item item5 = new Lab2.Item("item", "112345", 400, 0.1f);
        assertFalse(Lab2.SILab2.checkCart(List.of(item5), 70));
    }
}