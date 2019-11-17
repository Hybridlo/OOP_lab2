package lab2;

import org.junit.jupiter.api.Test;

import javax.xml.stream.XMLStreamException;

import static org.junit.jupiter.api.Assertions.*;

class CandyStAXParserTest {
    @Test
    void test_candy_with_filling() throws XMLStreamException {
        CandyParserInterface SAXParser = new CandyStAXParser(GenerateCandyXml.generate(true, true, false));
        Candy candy = SAXParser.getCandyList().get(0);
        assertEquals("milk cream", candy.filling);
    }

    @Test
    void test_candy_without_filling() throws XMLStreamException {
        CandyParserInterface SAXParser = new CandyStAXParser(GenerateCandyXml.generate(true, false, false));
        Candy candy = SAXParser.getCandyList().get(0);
        assertNull(candy.filling);
    }

    @Test
    void test_candy_without_ingredients() throws XMLStreamException {
        CandyParserInterface SAXParser = new CandyStAXParser(GenerateCandyXml.generate(true, false, false));
        Candy candy = SAXParser.getCandyList().get(0);
        assertEquals(0, candy.ingredients.size());
    }

    @Test
    void test_candy_with_ingredients() throws XMLStreamException {
        CandyParserInterface SAXParser = new CandyStAXParser(GenerateCandyXml.generate(true, false, true));
        Candy candy = SAXParser.getCandyList().get(0);
        assertFalse(candy.ingredients.isEmpty());
        assertEquals(5, candy.ingredients.size());
    }
}