package lab2;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CandyDOMParser implements CandyParserInterface {

    private List<Candy> candies = new ArrayList<>();

    @Override
    public List<Candy> getCandyList()
    {
        return candies;
    }

    CandyDOMParser(String file) throws IOException, SAXException, ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File(file));

        NodeList candyElements = document.getDocumentElement().getElementsByTagName("Candy");

        for (int i = 0; i < candyElements.getLength(); i++) {
            HashMap<String, Object> fields = new HashMap<>();
            HashMap<String, String> ingredients = new HashMap<>();
            HashMap<String, Integer> value = new HashMap<>();

            Node candyItem = candyElements.item(i);

            fields.put("id", candyItem.getAttributes().getNamedItem("id").getNodeValue());

            NodeList candyChildren = candyItem.getChildNodes();
            for(int j = 0; j < candyChildren.getLength(); j++) {
                Node child = candyChildren.item(j);
                switch (child.getNodeName()) {
                    case "Name":
                        fields.put("name", child.getTextContent());
                        break;
                    case "Energy":
                        fields.put("energy", child.getTextContent());
                        break;
                    case "Type":
                        fields.put("type", child.getTextContent());
                        if (child.getAttributes().getLength() > 0)
                            fields.put("filling", child.getAttributes().getNamedItem("filling").getNodeValue());
                        break;
                    case "Ingredients":
                        NodeList ingredientsChildren = child.getChildNodes();
                        for(int k = 0; k < ingredientsChildren.getLength(); k++) {
                            Node ingredientChild = ingredientsChildren.item(k);
                            switch (ingredientChild.getNodeName()) {
                                case "Water":
                                    ingredients.put("Water", ingredientChild.getTextContent());
                                    break;
                                case "Sugar":
                                    ingredients.put("Sugar", ingredientChild.getTextContent());
                                    break;
                                case "Fructose":
                                    ingredients.put("Fructose", ingredientChild.getTextContent());
                                    break;
                                case "ChocolateType":
                                    ingredients.put("ChocolateType", ingredientChild.getTextContent());
                                    break;
                                case "Vanilla":
                                    ingredients.put("Vanilla", ingredientChild.getTextContent());
                                    break;
                            }
                        }
                        break;
                    case "Value":
                        NodeList valueChildren = child.getChildNodes();
                        for(int k = 0; k < valueChildren.getLength(); k++) {
                            Node valueChild = valueChildren.item(k);
                            switch (valueChild.getNodeName()) {
                                case "Proteins":
                                    value.put("Proteins", Integer.valueOf(valueChild.getTextContent()));
                                    break;
                                case "Fat":
                                    value.put("Fat", Integer.valueOf(valueChild.getTextContent()));
                                    break;
                                case "Carbon":
                                    value.put("Carbon", Integer.valueOf(valueChild.getTextContent()));
                                    break;
                            }
                        }
                        break;
                    case "Production":
                        fields.put("production", child.getTextContent());
                        break;
                }
            }
            fields.put("ingredients", ingredients);
            fields.put("value", value);

            Candy candy = new Candy(fields);
            candies.add(candy);
        }
    }
}
