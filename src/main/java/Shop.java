import java.util.HashMap;
import java.util.List;

public class Shop {
    HashMap<String, Integer> items = new HashMap<>();
    {
        items.put("Огромное зелье лечения", 50);
        items.put("Большое зелье лечения", 35);
        items.put("Малое зелье лечения", 20);
        items.put("Кольчужный нагрудник", 30);
        items.put("Кольчужный шлем", 15);
        items.put("Кольчужные поножи", 30);
        items.put("Кольчужные наплечники", 15);
    }

    public HashMap<String, Integer> getItems() {
        return items;
    }

    public String getItemsInfo() {
        return "Содержимое лавки торговца: " +
                "\n1. Малое зелье лечения" +
                "\nЦена: 20" +
                "\n+25 к здоровью " +
                "\nТребуется уровень: 1"
        + "\n2. Большое зелье лечения" +
                "\nЦена: 35" +
                "\n+50 к здоровью " +
                "\nТребуется уровень: 10"
        + "\n3. Огромное зелье лечения" +
                "\nЦена: 50" +
                "\n+100 к здоровью " +
                "\nТребуется уровень: 15"
                + "\n4. Кольчужный нагрудник" +
                "\nЦена: 30" +
                "\n+10 к максимальному уровню здоровья " +
                "\nТребуется уровень: 10"
                + "\n5. Кольчужный шлем" +
                "\nЦена: 15" +
                "\n+5 к максимальному уровню здоровья " +
                "\nТребуется уровень: 15"
                + "\n6. Кольчужные поножи" +
                "\nЦена: 30" +
                "\n+10 к максимальному уровню здоровья " +
                "\nТребуется уровень: 10" +
                "\n7. Кольчужные наплечники" +
                "\nЦена: 15" +
                "\n+5 к максимальному уровню здоровья " +
                "\nТребуется уровень: 15"




                ;
    }
}
