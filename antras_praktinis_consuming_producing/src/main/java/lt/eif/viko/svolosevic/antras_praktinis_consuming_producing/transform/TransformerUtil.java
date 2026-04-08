package lt.eif.viko.svolosevic.antras_praktinis_consuming_producing.transform;

import lt.eif.viko.svolosevic.antras_praktinis_consuming_producing.model.Person;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.apache.fop.apps.FOUserAgent;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;

/**
 * Utility class for applying XSL-FO transformations to Person data.
 */
public class TransformerUtil {

    private static final FopFactory fopFactory;

    static {
        try {
            fopFactory = FopFactory.newInstance(new File(".").toURI());
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize FopFactory", e);
        }
    }

    /**
     * Converts a Person object to XML string with a <people> root element.
     *
     * @param person The person to convert
     * @return XML string representation
     * @throws Exception If conversion fails
     */
    public static String personToXml(Person person) throws Exception {
        JAXBContext context = JAXBContext.newInstance(Person.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        StringWriter writer = new StringWriter();
        marshaller.marshal(new Object[] {person}, writer); // Wrap in array to simulate <people> root
        return "<people>" + writer.toString().replaceFirst("array", "") + "</people>";
    }

    /**
     * Transforms a Person to PDF using the specified XSL-FO file.
     *
     * @param person The person to transform
     * @param xslFilePath Path to the XSL-FO file (e.g., from classpath)
     * @param outputFilePath Path to the output PDF file
     * @throws Exception If transformation fails
     */
    public static void transformToPdf(Person person, String xslFilePath, String outputFilePath) throws Exception {
        String xml = personToXml(person);
        StreamSource xmlSource = new StreamSource(new StringReader(xml));
        File xsltFile = new File(xslFilePath);
        if (!xsltFile.exists()) {
            throw new FileNotFoundException("XSL file not found: " + xslFilePath);
        }

        FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
        try (FileOutputStream out = new FileOutputStream(outputFilePath)) {
            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out);
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(new StreamSource(xsltFile));
            SAXResult res = new SAXResult(fop.getDefaultHandler());
            transformer.transform(xmlSource, res);
        }
    }

    /**
     * Utility method to transform with classpath resources.
     *
     * @param person The person to transform
     * @param outputFilePath Path to the output PDF file
     * @throws Exception If transformation fails
     */
    public static void transformToPdfFromClasspath(Person person, String outputFilePath) throws Exception {
        String xslFilePath = TransformerUtil.class.getClassLoader().getResource("xml_to_fo.xsl").getFile();
        transformToPdf(person, xslFilePath, outputFilePath);
    }
}