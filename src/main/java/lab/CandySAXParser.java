package lab;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class CandySAXParser implements CandyParserInterface {

    private CandySAXHandler handler;

    @Override
    public List<Candy> getCandyList()
    {
        return handler.getResult();
    }

    CandySAXParser(InputStream file) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
        SAXParser parser = factory.newSAXParser();
        handler = new CandySAXHandler();

        parser.parse(file, handler);
    }
}
