package lab;

import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CandySAXHandler extends DefaultHandler {

    private String thisElement;
    private List<Candy> candies = new ArrayList<>();
    private Map<String, String> ingredients = new HashMap<>();
    private Map<String, Integer> value = new HashMap<>();

    private Candy candy;

    List<Candy> getResult() {
        return candies;
    }

    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes attrs) {
        thisElement = qName;

        int attributeLength = attrs.getLength();
        if ("Candy".equals(qName)) {
            candy = new Candy();
        }
        if ("Candy".equals(qName) || "Type".equals(qName)) {
            for (int i = 0; i < attributeLength; i++) {
                // Get attribute names and values
                String attrName = attrs.getQName(i);
                String attrVal = attrs.getValue(i);
                switch (attrName) {
                    case "id":
                        candy.id = attrVal;
                        break;
                    case "filling":
                        candy.filling = attrVal;
                }
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String val = new String(ch, start, length);
        switch (thisElement) {
            case "Name":
                candy.name = val;
                break;
            case "Energy":
                candy.energy = val;
                break;
            case "Type":
                candy.type = val;
                break;
            case "Water":
                ingredients.put("Water", val);
                break;
            case "Sugar":
                ingredients.put("Sugar", val);
                break;
            case "Fructose":
                ingredients.put("Fructose", val);
                break;
            case "ChocolateType":
                ingredients.put("ChocolateType", val);
                break;
            case "Vanilla":
                ingredients.put("Vanilla", val);
                break;
            case "Proteins":
                value.put("Proteins", Integer.valueOf(val));
                break;
            case "Fat":
                value.put("Fat", Integer.valueOf(val));
                break;
            case "Carbon":
                value.put("Carbon", Integer.valueOf(val));
                break;
            case "Production":
                candy.production = val;
                break;
            default:
                break;
        }
    }

    @Override
    public void endElement(String namespaceURI, String localName, String qName) {
        thisElement = "";

        if ("Candy".equals(qName)) {
            candy.ingredients = ingredients;
            candy.value = value;

            candies.add(candy);

            ingredients = new HashMap<>();
            value = new HashMap<>();
        }
    }
}
