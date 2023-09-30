import java.util.HashMap;
import java.util.List;

public class Shop {
    HashMap<String, Integer> items = new HashMap<>();
    {
        items.put("3. Огромное зелье лечения(+100 к здоровью, требуется уровень: 8)", 25);
        items.put("2. Большое зелье лечения(+40 к здоровью, требуется уровень: 5)", 15);
        items.put("1. Малое зелье лечения(+25 к здоровью, требуется уровень: 3)", 10);
    }

    public HashMap<String, Integer> getItems() {
        return items;
    }
}
