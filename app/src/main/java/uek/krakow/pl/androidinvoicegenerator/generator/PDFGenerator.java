package uek.krakow.pl.androidinvoicegenerator.generator;

import com.lowagie.text.DocumentException;

import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class PDFGenerator {
    public void generatePDF(InputStream xslStream, InputStream xmlStream, File dir) {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        try {
            Transformer transformer = transformerFactory.newTransformer(new StreamSource(xslStream));
            transformer.transform(new StreamSource(xmlStream), new StreamResult(new FileOutputStream(new File(dir, "invoice.html"))));

            String File_To_Convert = "invoice.html";
            String url = new File(dir, File_To_Convert).toURI().toURL().toString();
            String HTML_TO_PDF = "invoice.pdf";
            OutputStream os = new FileOutputStream(HTML_TO_PDF);
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocument(url);
            renderer.layout();
            renderer.createPDF(os);
            os.close();

        } catch (TransformerException | IOException | DocumentException e) {
            e.printStackTrace();
        }

    }
}
