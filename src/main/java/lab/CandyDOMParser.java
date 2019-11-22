package lab;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CandyDOMParser implements CandyParserInterface {

    private List<Candy> candies = new ArrayList<>();

    @Override
    public List<Candy> getCandyList()
    {
        return candies;
    }

    CandyDOMParser(InputStream file) throws IOException, SAXException, ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(file);

        NodeList candyElements = document.getDocumentElement().getElementsByTagName("Candy");

        for (int i = 0; i < candyElements.getLength(); i++) {
            Candy candy = new Candy();
            Map<String, String> ingredients = new HashMap<>();
            Map<String, Integer> value = new HashMap<>();

            Node candyItem = candyElements.item(i);

            candy.id = candyItem.getAttributes().getNamedItem("id").getNodeValue();

            NodeList candyChildren = candyItem.getChildNodes();
            for(int j = 0; j < candyChildren.getLength(); j++) {
                Node child = candyChildren.item(j);
                switch (child.getNodeName()) {
                    case "Name":
                        candy.name = child.getTextContent();
                        break;
                    case "Energy":
                        candy.energy = child.getTextContent();
                        break;
                    case "Type":
                        candy.type = child.getTextContent();
                        if (child.getAttributes().getLength() > 0)
                            candy.filling = child.getAttributes().getNamedItem("filling").getNodeValue();
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
                                default:
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
                                default:
                                    break;
                            }
                        }
                        break;
                    case "Production":
                        candy.production = child.getTextContent();
                        break;
                    default:
                        break;
                }
            }
            candy.ingredients = ingredients;
            candy.value = value;

            candies.add(candy);
        }
    }
}
