package lab2;

import org.xml.sax.InputSource;

import javax.xml.XMLConstants;
import javax.xml.transform.sax.SAXSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;

class SchemaValidator {
    static boolean validateXml(String xsdName, String xmlName) {
        try {
            String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
            SchemaFactory factory = SchemaFactory.newInstance(language);
            Schema schema = factory.newSchema(new File(xsdName));

            Validator validator = schema.newValidator();
            System.out.println();
            System.out.println("Validator Class: " + validator.getClass().getName());

            // preparing the XML file as a SAX source
            SAXSource source = new SAXSource(new InputSource(new java.io.FileInputStream(xmlName)));

            // validating the SAX source against the schema
            validator.validate(source);
            return true;

        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return false;
    }
}
