package lab2;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class CandyTest {
    @Test
    void test_class_constructor() {
        HashMap<String, Object> fields = new HashMap<>();
        fields.put("id", "1");
        fields.put("name", "Слівкі лінівкі вафля");
        fields.put("energy", "420");
        fields.put("type", "Chocolate with filling");
        fields.put("filling", "milk cream");

        HashMap<String, String> ingredients = new HashMap<>();
        ingredients.put("Water", "15");
        ingredients.put("Sugar", "30");
        ingredients.put("ChocolateType", "Black chocolate");
        ingredients.put("Vanilla", "5");

        fields.put("ingredients", ingredients);

        HashMap<String, Integer> value = new HashMap<>();
        value.put("Proteins", 15);
        value.put("Fat", 5);
        value.put("Carbon", 80);

        fields.put("value", value);

        fields.put("production", "Рошен");

        Candy candy = new Candy(fields);

        assertSame("1", candy.id);
        assertSame("Слівкі лінівкі вафля", candy.name);
        assertSame("420", candy.energy);
        assertSame("Chocolate with filling", candy.type);
        assertSame("milk cream", candy.filling);
        assertSame("15", candy.ingredients.get("Water"));
        assertSame("30", candy.ingredients.get("Sugar"));
        assertSame("Black chocolate", candy.ingredients.get("ChocolateType"));
        assertSame("5", candy.ingredients.get("Vanilla"));
        assertEquals(15, (int) candy.value.get("Proteins"));
        assertEquals(5, (int) candy.value.get("Fat"));
        assertEquals(80, (int) candy.value.get("Carbon"));
        assertSame("Рошен", candy.production);
    }
}