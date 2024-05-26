# SI_2024_lab2_183122
CFG graph: https://lucid.app/lucidchart/aa243042-bfa2-42d7-b7aa-d59984a0973d/edit?viewport_loc=275%2C1415%2C2219%2C1087%2C0_0&invitationId=inv_5b864c88-2bd3-4143-8369-3d7cafcf1308

Цикломатска комплексност:
- Формула: \[ CC = E - N + 2P \]
- Nodes:
1. Старт (1 node)
2. allItems, null (2 nodes)
3. Иницијализација на sum (1 node)
4. for loop (2 nodes почеток и крај)
5. земи го истиот продукт getItem(i) (1 node)
6. провери го името на продуктот (2 nodes: проверка и ставање на името)
7. провери баркод (2 nodes: проверка и процесирање баркод)
8. конвертирање на баркодот во charr (1 node)
9. 2 for-a за баркод (2 nodes  почеток и крај)
10. проверка за исправноста на карактерите за баркодот(2 nodes: проверка и exception)
11. проверка за попуст (2 nodes: проверка и додавање на цената на продуктот)
12. специјален попуст проверка (2 nodes: проверка и одземање на сумата)
13. Exception за null баркод (1 node)
14. Проверка на средства (2 nodes: проверка и враќање на вредноста)
15. крај (1 node)
 
-N = 24 (24 nodes)

- edges (E): 
1. allItems null проверка (1 edge)
2. allItems null проверка до фрлање exc. и до сл. чекор (2 egdes)
3. иницијализација sum (1 edge)
4. for loop од почеток до крај и содржината (3 edges)
5. getItem(i) (1 edge)
6. проверка на име на продукт и именување до сл. чекор (3 edges)
7. баркод null проверка процесирање и exc. (2 edges)
8. баркод --> char (1 edge)
9. for во for циклус од почеток до крај и содржината (3 edges)
10. проверка на карактери и exc. и сл. чекор (2 edges)
11. проверка за попуст и додавање на цената со попуст и без попустот (2 edges)
12. специјален попуст до одземање на 30 и сл. чекор (2 edges)
13. проверка на средства и враќање на true, false (2 edges)
14. крај(1 edge)

E = 24

CC = E - N + 2P=(1*2 = 2)
2P=(1*2) = 2
CC = 24 - 24 + 2
CC = 2

Every branch criterium: 
1. Null check for allItems
- или е null или не е.
2. Итерации на for loop
- базирано на allItems for loop-от може да итерира 0 или повеќе пати.
3. Проверка на името на продуктот може да е null, празно или не празно и не null
4. Проверка на баркод null или не.
5. Валидација на баркодот, содржи валидни карактери или не.
6. Проверка на попустот, или е поголем од 0 или не
7. Проверка на специјалниот попуст, односно цената на продуктот е поголема од 300, а попустот е поголем од 0 и првиот карактер на баркодот е '0'
8. Целосната сума на продуктите е поголема или помала од претходно утврдената сума.
   
public void testNullItemList() {
   RuntimeException exception = assertThrows(
   RuntimeException.class,
   () -> SILab2.checkCart(null, 100)
   );
   assertEquals("allItems list can't be null!", exception.getMessage());
   }

   @Test
   public void testEmptyItemList() {
   List<Item> items = new ArrayList<>();
   assertTrue(SILab2.checkCart(items, 0));
   }

   @Test
   public void testValidItemsNoDiscount() {
   List<Item> items = new ArrayList<>();
   items.add(new Item("Item1", "123456", 100, 0));
   items.add(new Item("Item2", "789012", 200, 0));
   assertFalse(SILab2.checkCart(items, 250));
   }

   @Test
   public void testValidItemsWithDiscount() {
   List<Item> items = new ArrayList<>();
   items.add(new Item("Item1", "123456", 100, 0.1f));
   items.add(new Item("Item2", "789012", 200, 0.2f));
   assertTrue(SILab2.checkCart(items, 270));
   }

   @Test
   public void testInvalidBarcodeCharacter() {
   List<Item> items = new ArrayList<>();
   items.add(new Item("Item1", "12A456", 100, 0));
   RuntimeException exception = assertThrows(
   RuntimeException.class,
   () -> SILab2.checkCart(items, 100)
   );
   assertEquals("Invalid character in item barcode!", exception.getMessage());
   }

   @Test
   public void testMissingBarcode() {
   List<Item> items = new ArrayList<>();
   items.add(new Item("Item1", null, 100, 0));
   RuntimeException exception = assertThrows(
   RuntimeException.class,
   () -> SILab2.checkCart(items, 100)
   );
   assertEquals("No barcode!", exception.getMessage());
   }

   @Test
   public void testSpecialDiscountCondition() {
   List<Item> items = new ArrayList<>();
   items.add(new Item("Item1", "012345", 400, 0.2f));
   assertFalse(SILab2.checkCart(items, 320));
   }

   @Test
   public void testPaymentSufficient() {
   List<Item> items = new ArrayList<>();
   items.add(new Item("Item1", "123456", 100, 0));
   items.add(new Item("Item2", "789012", 200, 0));
   assertTrue(SILab2.checkCart(items, 300));
   }

   @Test
   public void testPaymentInsufficient() {
   List<Item> items = new ArrayList<>();
   items.add(new Item("Item1", "123456", 100, 0));
   items.add(new Item("Item2", "789012", 200, 0));
   assertFalse(SILab2.checkCart(items, 250));
   }
   }

Multiple condition
public class SILab2Test {

    @Test
    public void testAllConditionsTrue() {
        Item item = new Item("Item1", "012345", 400, 0.2f);
        boolean result = SILab2.checkCart(List.of(item), 320);
        assertFalse(result);
    }

    @Test
    public void testFirstConditionFalse() {
        Item item = new Item("Item1", "012345", 200, 0.2f);
        boolean result = SILab2.checkCart(List.of(item), 320);
        assertTrue(result);
    }

    @Test
    public void testSecondConditionFalse() {
        Item item = new Item("Item1", "012345", 400, 0);
        boolean result = SILab2.checkCart(List.of(item), 320);
        assertTrue(result);
    }

    @Test
    public void testThirdConditionFalse() {
        Item item = new Item("Item1", "112345", 400, 0.2f);
        boolean result = SILab2.checkCart(List.of(item), 320);
        assertTrue(result);
    }

    @Test
    public void testFirstAndSecondConditionsFalse() {
        Item item = new Item("Item1", "112345", 200, 0);
        boolean result = SILab2.checkCart(List.of(item), 320);
        assertTrue(result);
    }

    @Test
    public void testSecondAndThirdConditionsFalse() {
        Item item = new Item("Item1", "112345", 400, 0);
        boolean result = SILab2.checkCart(List.of(item), 320);
        assertTrue(result);
    }

    @Test
    public void testFirstAndThirdConditionsFalse() {
        Item item = new Item("Item1", "112345", 200, 0.2f);
        boolean result = SILab2.checkCart(List.of(item), 320);
        assertTrue(result);
    }

    @Test
    public void testAllConditionsFalse() {
        Item item = new Item("Item1", "112345", 200, 0);
        boolean result = SILab2.checkCart(List.of(item), 200);
        assertTrue(result);
    }
}

Тестови: 
1. Ако сите проверки се точни. Продукт со цена на 300, попуст поголем од нула, и баркод што почнува со 0.
2. Ако првата проверка е грешна. Продукт со цена помалку или еднаква на 300
3. Ако втората проверка е грешна. Попуст помал или еднаков на 0.
4. Ако третата проверка е грешна. Баркодот не почнува со 0.
5. Ако првата и втората проверка се грешни. Цена на продукт помала или еднаква на 300 и попуст помал или еднаков на 0.
6. Ако втората и третата проверка се грешни. Попуст помал или еднаков на 0 и баркод што не почнува со 0.
7. Ако првата и третата проверка се грешни. Цена на продукт помала или еднаква на 0 и баркод што не почнува со 0.
8. Ако сите проверки се грешни. Цена на продукт помала или еднаква на 0, попуст помал или еднаков на 0 и баркод што не почнува со 0. 


