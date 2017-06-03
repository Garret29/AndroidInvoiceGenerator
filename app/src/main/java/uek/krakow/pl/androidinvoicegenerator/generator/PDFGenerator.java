package uek.krakow.pl.androidinvoicegenerator.generator;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
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

import uek.krakow.pl.androidinvoicegenerator.InvoiceGeneratorApplication;
import uek.krakow.pl.androidinvoicegenerator.viewcontroller.MainActivity;

public class PDFGenerator {
    public File generatePDF(String xsl, File xml, File dir, String filename, File cacheDir) {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();

        File style = new File(MainActivity.stylesDir, xsl);
        File html = new File(cacheDir, "invoice.html");
        File pdf = new File(dir, filename+".pdf");
        if (!dir.mkdirs()){
            Log.d("hehe", "nie ma :(");
        }

        Log.d("hehe", "start PDF");

        try {

            Transformer transformer = transformerFactory.newTransformer(new StreamSource(style));
            transformer.transform(new StreamSource(xml), new StreamResult(new FileOutputStream(html)));

            Tidy tidy = new Tidy();
            tidy.setXHTML(true);
            tidy.parseDOM(new FileInputStream(html), new FileOutputStream(new File(cacheDir, "invoice2.html")));

            html = new File(cacheDir, "invoice2.html");

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

        Log.d("hehe", pdf.getAbsolutePath());

        return pdf;
    }
}
