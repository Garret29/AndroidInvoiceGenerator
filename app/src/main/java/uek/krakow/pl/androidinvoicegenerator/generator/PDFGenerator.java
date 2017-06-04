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
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.css.StyleAttrCSSResolver;
import com.itextpdf.tool.xml.html.CssAppliers;
import com.itextpdf.tool.xml.html.CssAppliersImpl;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.end.PdfWriterPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;

import org.w3c.tidy.Tidy;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import uek.krakow.pl.androidinvoicegenerator.InvoiceGeneratorApplication;
import uek.krakow.pl.androidinvoicegenerator.viewcontroller.MainActivity;

public class PDFGenerator {
    public File generatePDF(File xsl, File xml, File dir, String filename, File cacheDir, String fontPath, String fontFamily) {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();

        File html = new File(cacheDir, "invoice.html");
        File pdf = new File(dir, filename + ".pdf");

        try {
            Transformer transformer = transformerFactory.newTransformer(new StreamSource(xsl));
            transformer.transform(new StreamSource(xml), new StreamResult(new FileOutputStream(html)));

            Tidy tidy = new Tidy();
            tidy.setInputEncoding("UTF-8");
            tidy.setXHTML(true);
            tidy.parseDOM(new FileInputStream(html), new FileOutputStream(new File(cacheDir, "invoice2.html")));

            html = new File(cacheDir, "invoice2.html");

            OutputStream os = new FileOutputStream(pdf);
            Document document = new Document();
            PdfWriter pdfWriter = PdfWriter.getInstance(document, os);
            document.open();
            XMLWorkerFontProvider xmlWorkerFontProvider = new XMLWorkerFontProvider(XMLWorkerFontProvider.DONTLOOKFORFONTS);
            xmlWorkerFontProvider.register(fontPath, fontFamily);
            XMLWorkerHelper.getInstance().parseXHtml(pdfWriter, document, new FileInputStream(html), Charset.forName("UTF-8"), xmlWorkerFontProvider);
            document.close();
            os.close();
        } catch (DocumentException | IOException | TransformerException e) {
            e.printStackTrace();
        }

        Log.d("hehe", fontPath);
        Log.d("hehe", pdf.getAbsolutePath());

        return pdf;
    }
}
