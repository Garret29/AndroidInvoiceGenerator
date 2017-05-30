package uek.krakow.pl.androidinvoicegenerator;

import android.app.Application;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import uek.krakow.pl.androidinvoicegenerator.generator.invoicemodel.Faktura;
import uek.krakow.pl.androidinvoicegenerator.tasks.PortForwardTask;

public class MyApp extends Application {
    private InputStream xslStream;
    private Document doc;
    private PortForwardTask portForwardTask;
    private File xml;
    private File pdf;
    private Faktura faktura;

    @Override
    public void onCreate() {
        super.onCreate();

        faktura = new Faktura();

        portForwardTask = new PortForwardTask();
        portForwardTask.execute();

        xslStream = getResources().openRawResource(R.raw.faktury_style);

        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = builderFactory.newDocumentBuilder();
            doc = builder.parse(getResources().openRawResource(R.raw.faktury));
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        portForwardTask.cancel(true);
    }

    public InputStream getXslStream() {
        return xslStream;
    }

    public Document getDoc() {
        return doc;
    }

    public File getXml() {
        return xml;
    }

    public void setXml(File xml) {
        this.xml = xml;
    }

    public File getPdf() {
        return pdf;
    }

    public void setPdf(File pdf) {
        this.pdf = pdf;
    }

    public void setXslStream(InputStream xslStream) {
        this.xslStream = xslStream;
    }

    public void setDoc(Document doc) {
        this.doc = doc;
    }

    public PortForwardTask getPortForwardTask() {
        return portForwardTask;
    }

    public void setPortForwardTask(PortForwardTask portForwardTask) {
        this.portForwardTask = portForwardTask;
    }

    public Faktura getFaktura() {
        return faktura;
    }

    public void setFaktura(Faktura faktura) {
        this.faktura = faktura;
    }
}
