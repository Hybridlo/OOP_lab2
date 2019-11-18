package lab;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

final class GenerateCandyXml {
    private GenerateCandyXml(){}
    static InputStream generate(boolean bySchema, boolean hasFilling, boolean hasIngredients) {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                "<Candies xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "xsi:noNamespaceSchemaLocation=\"candy.xsd\">" +
                "<Candy id=\"ID-1\">" +
                "<Name>Slivki linivki vaflya</Name>";
        if (bySchema)
            xml += "<Energy>420</Energy>";
        if (hasFilling)
            xml += "<Type filling=\"milk cream\">Chocolate with filling</Type>";
        else
            xml += "<Type>Chocolate with filling</Type>";
        xml += "<Ingredients>";
        if (hasIngredients)
            xml += "<Water>15</Water>" +
                    "<Sugar>30</Sugar>" +
                    "<Fructose>15</Fructose>" +
                    "<ChocolateType>Black chocolate</ChocolateType>" +
                    "<Vanilla>5</Vanilla>";
        xml += "</Ingredients>" +
                "<Value>" +
                "<Proteins>15</Proteins>" +
                "<Fat>5</Fat>" +
                "<Carbon>80</Carbon>" +
                "</Value>" +
                "<Production>Roshen</Production>\n" +
                "</Candy>" +
                "</Candies>";
        return new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8));
    }
}
