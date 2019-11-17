/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package lab2;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.*;
import java.util.List;

public class App {
    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException, XMLStreamException {
        String parser = args[0];
        CandyParserInterface SAXParser;
        switch (parser) {
            case "1":
                SAXParser = new CandySAXParser("candy.xml");
                System.out.println("Using SAX parser");
                break;
            case "2":
                SAXParser = new CandyDOMParser("candy.xml");
                System.out.println("Using DOM parser");
                break;
            default:
                SAXParser = new CandyStAXParser("candy.xml");
                System.out.println("Using StAX parser");
                break;
        }
        List<Candy> candies = SAXParser.getCandyList();
        for (Candy candy : candies) {
            candy.printFields();
        }
    }
}
