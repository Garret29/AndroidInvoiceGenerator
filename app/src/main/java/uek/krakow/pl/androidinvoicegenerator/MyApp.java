package uek.krakow.pl.androidinvoicegenerator;

import android.app.Application;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by Szymon on 29.05.2017.
 */

public class MyApp extends Application {
    private InputStream xslStream;
    private Document doc;
    private PortForwardTask portForwardTask;

    @Override
    public void onCreate() {
        super.onCreate();
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
}
