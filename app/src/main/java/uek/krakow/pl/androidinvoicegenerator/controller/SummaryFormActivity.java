package uek.krakow.pl.androidinvoicegenerator.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import org.w3c.dom.Document;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import uek.krakow.pl.androidinvoicegenerator.MyApp;
import uek.krakow.pl.androidinvoicegenerator.R;
import uek.krakow.pl.androidinvoicegenerator.generator.PDFGenerator;

public class SummaryFormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary_form);
    }

    public void toShare(View view) {
        PDFGenerator pdfGenerator = new PDFGenerator();
        MyApp app = (MyApp) getApplication();
        Document document = app.getDoc();
        TransformerFactory factory = TransformerFactory.newInstance();
        File xml=new File(getCacheDir(), "invoice.xml");
        try {
            Transformer transformer = factory.newTransformer();
            Result result = new StreamResult(xml);
            Source source = new DOMSource(document);
            transformer.transform(source, result);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        app.setXml(xml);

        File dir = new File(getApplicationInfo().dataDir);

        try {
            app.setPdf(pdfGenerator.generatePDF(app.getXslStream(), new FileInputStream(xml), dir));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Intent intent = new Intent(this, ShareFormActivity.class);
        startActivity(intent);
    }
}
