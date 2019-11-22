/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package lab;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.*;
import java.util.List;

public class App {
    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException, XMLStreamException {
        String parser = args[0];
        CandyParserInterface saxParser;
        String xmlFile = "candy.xml";
        InputStream xmlStream = new FileInputStream(xmlFile);

        if(SchemaValidator.validateXml("candy.xsd", xmlStream))
            System.out.println("Validation success");
        else
        {
            System.out.println("Validation failure");
            return;
        }
        xmlStream = new FileInputStream(xmlFile);
        switch (parser) {
            case "1":
                saxParser = new CandySAXParser(xmlStream);
                System.out.println("Using SAX parser");
                break;
            case "2":
                saxParser = new CandyDOMParser(xmlStream);
                System.out.println("Using DOM parser");
                break;
            default:
                saxParser = new CandyStAXParser(xmlStream);
                System.out.println("Using StAX parser");
                break;
        }
        List<Candy> candies = saxParser.getCandyList();
        for (Candy candy : candies) {
            System.out.println(candy.toString());
        }

        CandyComparator candyc = new CandyComparator();
        candies.sort(candyc);

        for (Candy candy : candies) {
            System.out.println(candy.name);
        }
    }
}
