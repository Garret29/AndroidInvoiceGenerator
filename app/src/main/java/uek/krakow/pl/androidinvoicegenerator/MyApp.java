package uek.krakow.pl.androidinvoicegenerator;

import android.app.Application;
import android.os.StrictMode;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import uek.krakow.pl.androidinvoicegenerator.tasks.PortForwardTask;

/**
 * Created by Szymon on 29.05.2017.
 */

public class MyApp extends Application {
    private InputStream xslStream;
    private Document doc;
    private PortForwardTask portForwardTask;
    private File xml;
    private File pdf;

    @Override
    public void onCreate() {
        super.onCreate();
        portForwardTask = new PortForwardTask();
        portForwardTask.execute();
        StrictMode.VmPolicy.Builder buildervm = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(buildervm.build());

        xslStream = getResources().openRawResource(R.raw.faktura);

        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = builderFactory.newDocumentBuilder();
            doc = builder.parse(getResources().openRawResource(R.raw.fakturax));
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
}
