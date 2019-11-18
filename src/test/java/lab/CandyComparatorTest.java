package lab;

import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CandyComparatorTest {
    @Test
    void test_candy_comparator() throws IOException, SAXException, ParserConfigurationException {
        CandyParserInterface SAXParser = new CandySAXParser(GenerateCandyXml.generate(true, false, false));
        Candy a = SAXParser.getCandyList().get(0);

        SAXParser = new CandySAXParser(GenerateCandyXml.generate(true, false, false));
        Candy b = SAXParser.getCandyList().get(0);

        CandyComparator candyc = new CandyComparator();

        b.name = "F";
        assertTrue(candyc.compare(a, b) > 0);

        b.name = "V";
        assertTrue(candyc.compare(a, b) < 0);

        b.name = a.name;
        assertEquals(0, candyc.compare(a, b));
    }
}