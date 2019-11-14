package lab2;

import java.util.HashMap;
@SuppressWarnings(value="unchecked")

public class Candy {
    String name;
    int energy;
    String type;
    String filling;
    HashMap<String, String> ingredients;
    HashMap<String, Integer> value;
    String production;

    Candy(HashMap<String,Object> fields)
    {
        this.name = (String) fields.get("name");
        this.energy = (int) fields.get("energy");
        this.type = (String) fields.get("type");
        this.filling = (String) fields.get("filling");
        this.ingredients = (HashMap<String, String>) fields.get("type"); //parser will only return HashMap<String, String>
        this.value = (HashMap<String, Integer>) fields.get("value"); //same as above
        this.production = (String) fields.get("production");
    }
}
