package lab;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings(value="unchecked")

public class Candy {
    String id;
    String name;
    String energy;
    String type;
    String filling;
    Map<String, String> ingredients;
    Map<String, Integer> value;
    String production;
    
    public String toString() {
        String result = "";
        result += "Name: " + name + "\n";
        result += "ID: " + id + "\n";
        result += "Energy: " + energy + "\n";
        result += "Type: " + type + "\n";
        if (filling != null)
            result += "Filling: " + filling + "\n";
        result += "Ingredients\n";
        for (Map.Entry<String, String> entry : ingredients.entrySet()) {
            String key = entry.getKey();
            String val = entry.getValue();
            result += "\t" + key + ": " + val + "\n";
        }
        result += "Value" + "\n";
        for (Map.Entry<String, Integer> entry : value.entrySet()) {
            String key = entry.getKey();
            int val = entry.getValue();
            result += "\t" + key + ": " + val + "\n";
        }
        result = "Production: " + production + "\n";

        return result;
    }
}
