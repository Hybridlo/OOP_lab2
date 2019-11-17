package lab2;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class CandyStAXParser implements CandyParserInterface {

    private List<Candy> candies = new ArrayList<>();
    private String thisElement;
    private boolean processed = true;

    @Override
    public List<Candy> getCandyList()
    {
        return candies;
    }

    CandyStAXParser(String file) throws FileNotFoundException, XMLStreamException {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader eventReader =
                factory.createXMLEventReader(new FileReader(file));

        HashMap<String, Object> fields = new HashMap<>();
        HashMap<String, String> ingredients = new HashMap<>();
        HashMap<String, Integer> value = new HashMap<>();

        while(eventReader.hasNext()) {

            XMLEvent event = eventReader.nextEvent();

            switch(event.getEventType()) {

                case XMLStreamConstants.START_ELEMENT:
                    StartElement startElement = event.asStartElement();
                    thisElement = startElement.getName().getLocalPart();
                    processed = false;

                    if ("Candy".equals(thisElement) || "Type".equals(thisElement)) {
                        Iterator<Attribute> attributes = startElement.getAttributes();
                        while (attributes.hasNext()) {
                            Attribute attribute = attributes.next();
                            fields.put(attribute.getName().toString(), attribute.getValue());
                        }
                    }
                    break;

                case XMLStreamConstants.CHARACTERS:
                    if (processed)
                        break;
                    Characters characters = event.asCharacters();
                    String val = characters.getData();
                    processed = true;
                    switch (thisElement) {
                        case "Name":
                            fields.put("name", val);
                            break;
                        case "Energy":
                            fields.put("energy", val);
                            break;
                        case "Type":
                            fields.put("type", val);
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
                            fields.put("production", val);
                            break;
                    }
                    break;

                case XMLStreamConstants.END_ELEMENT:
                    EndElement endElement = event.asEndElement();

                    if ("Candy".equals(endElement.getName().toString())) {
                        fields.put("ingredients", ingredients);
                        fields.put("value", value);

                        Candy candy = new Candy(fields);
                        candies.add(candy);

                        fields = new HashMap<>();
                        ingredients = new HashMap<>();
                        value = new HashMap<>();
                    }

                    break;
            }
        }
    }
}
