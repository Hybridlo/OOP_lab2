package lab2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SchemaValidatorTest {
    @Test
    void test_valid_xml() {
        assertTrue(SchemaValidator.validateXml("candy.xsd", GenerateCandyXml.generate(true, false, false)));
    }

    @Test
    void test_invalid_xml() {
        assertFalse(SchemaValidator.validateXml("candy.xsd", GenerateCandyXml.generate(false, false, false)));
    }
}