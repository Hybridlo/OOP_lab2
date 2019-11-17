package lab2;

import org.xml.sax.InputSource;

import javax.xml.XMLConstants;
import javax.xml.transform.sax.SAXSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.InputStream;

final class SchemaValidator {
    private SchemaValidator(){}
    static boolean validateXml(String xsdName, InputStream xmlFile) {
        try {
            String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
            SchemaFactory factory = SchemaFactory.newInstance(language);
            Schema schema = factory.newSchema(new File(xsdName));

            Validator validator = schema.newValidator();

            SAXSource source = new SAXSource(new InputSource(xmlFile));

            validator.validate(source);
            return true;

        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return false;
    }
}
