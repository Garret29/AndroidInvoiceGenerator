package uek.krakow.pl.androidinvoicegenerator.generator;

import android.util.Log;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

import org.w3c.tidy.Tidy;

import java.io.File;
import java.io.FileInputStream;
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
    public File generatePDF(InputStream xslStream, InputStream xmlStream, File dir) {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        File html = new File(dir, "invoice.html");
        File pdf = new File(dir, "invoice.pdf");
        try {
            Transformer transformer = transformerFactory.newTransformer(new StreamSource(xslStream));
            transformer.transform(new StreamSource(xmlStream), new StreamResult(new FileOutputStream(html)));

            Tidy tidy = new Tidy();
            tidy.setXHTML(true);
            tidy.parseDOM(new FileInputStream(html), new FileOutputStream(new File(dir, "invoice2.html")));

            html = new File(dir, "invoice2.html");

            OutputStream os = new FileOutputStream(pdf);
            Document document = new Document();
            PdfWriter pdfWriter = PdfWriter.getInstance(document, os);
            document.open();
            XMLWorkerHelper.getInstance().parseXHtml(pdfWriter, document, new FileInputStream(html));
            document.close();
            os.close();
        } catch (DocumentException | IOException | TransformerException e) {
            e.printStackTrace();
        }
        return pdf;
    }
}
