package lab;

import java.util.Map;

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
        result += "Production: " + production + "\n";

        return result;
    }

    public boolean equals(Candy other) {
        boolean isEquals = true;
        if (!this.id.equals(other.id))
            isEquals = false;

        if (!this.name.equals(other.name))
            isEquals = false;

        if (!this.energy.equals(other.energy))
            isEquals = false;

        if (!this.type.equals(other.type))
            isEquals = false;

        if (!this.filling.equals(other.filling))
            isEquals = false;

        for (Map.Entry<String, String> entry : this.ingredients.entrySet()) {
            if (!other.ingredients.get(entry.getKey()).equals(entry.getValue()))
                isEquals = false;
        }

        for (Map.Entry<String, Integer> entry : this.value.entrySet()) {
            if (!other.value.get(entry.getKey()).equals(entry.getValue()))
                isEquals = false;
        }

        if (!this.production.equals(other.production))
            isEquals = false;

        return isEquals;
    }
}
