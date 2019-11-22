package lab;

import javax.xml.stream.*;
import javax.xml.stream.events.*;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class CandyStAXParser implements CandyParserInterface {

    private List<Candy> candies = new ArrayList<>();
    private String thisElement;

    @Override
    public List<Candy> getCandyList()
    {
        return candies;
    }

    CandyStAXParser(InputStream file) throws XMLStreamException {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        factory.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES, false);
        XMLEventReader eventReader =
                factory.createXMLEventReader(new InputStreamReader(file));

        Map<String, String> ingredients = new HashMap<>();
        Map<String, Integer> value = new HashMap<>();

        Candy candy = new Candy();

        while(eventReader.hasNext()) {

            XMLEvent event = eventReader.nextEvent();

            switch(event.getEventType()) {

                case XMLStreamConstants.START_ELEMENT:
                    StartElement startElement = event.asStartElement();
                    thisElement = startElement.getName().getLocalPart();

                    if ("Candy".equals(thisElement) || "Type".equals(thisElement)) {
                        Iterator<Attribute> attributes = startElement.getAttributes();
                        while (attributes.hasNext()) {
                            Attribute attribute = attributes.next();
                            switch (attribute.getName().toString()) {
                                case "id":
                                    candy.id = attribute.getValue();
                                    break;
                                case "filling":
                                    candy.filling = attribute.getValue();
                            }
                        }
                    }
                    break;

                case XMLStreamConstants.CHARACTERS:
                    Characters characters = event.asCharacters();
                    String val = characters.getData();
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
                    break;

                case XMLStreamConstants.END_ELEMENT:
                    EndElement endElement = event.asEndElement();
                    thisElement = "";

                    if ("Candy".equals(endElement.getName().toString())) {
                        candy.ingredients = ingredients;
                        candy.value = value;

                        candies.add(candy);

                        ingredients = new HashMap<>();
                        value = new HashMap<>();
                    }

                    break;
                default:
                    break;
            }
        }
    }
}
