import java.util.HashMap;
import java.util.List;

public class Shop {
    HashMap<String, Integer> items = new HashMap<>();
    {
        items.put("Огромное зелье лечения", 25);
        items.put("Большое зелье лечения", 15);
        items.put("Малое зелье лечения", 10);
    }

    public HashMap<String, Integer> getItems() {
        return items;
    }

    public String getItemsInfo() {
        return "Содержимое лавки торговца: " +
                "\n1. Малое зелье лечения" +
                "\n+25 к здоровью " +
                "\nТребуется уровень: 3"
        + "\n2. Большое зелье лечения(" +
                "\n+40 к здоровью " +
                "\nТребуется уровень: 5"
        + "\n3. Огромное зелье лечения" +
                "\n+100 к здоровью " +
                "\nТребуется уровень: 8";
    }
}
