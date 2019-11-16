package lab2;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings(value="unchecked")

public class Candy {
    String id;
    String name;
    String energy;
    String type;
    String filling;
    HashMap<String, String> ingredients;
    HashMap<String, Integer> value;
    String production;

    Candy(HashMap<String,Object> fields) {
        this.id = (String) fields.get("id");
        this.name = (String) fields.get("name");
        this.energy = (String) fields.get("energy");
        this.type = (String) fields.get("type");

        if (fields.containsKey("filling"))
            this.filling = (String) fields.get("filling");
        this.ingredients = (HashMap<String, String>) fields.get("ingredients"); //parser will only return HashMap<String, String>
        this.value = (HashMap<String, Integer>) fields.get("value"); //same as above
        this.production = (String) fields.get("production");
    }
    void printFields() {
        System.out.println("Name: " + name);
        System.out.println("ID: " + id);
        System.out.println("Energy: " + energy);
        System.out.println("Type: " + type);
        if (filling.length() > 0)
            System.out.println("Filling: " + filling);
        System.out.println("Ingredients");
        for (Map.Entry<String, String> entry : ingredients.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println("\t" + key + ": " + value);
        }
        System.out.println("Value");
        for (Map.Entry<String, Integer> entry : value.entrySet()) {
            String key = entry.getKey();
            int value = entry.getValue();
            System.out.println("\t" + key + ": " + value);
        }
        System.out.println("Production: " + production + '\n');
    }
}
