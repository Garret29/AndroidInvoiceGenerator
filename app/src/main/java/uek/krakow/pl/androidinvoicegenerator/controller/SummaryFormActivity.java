package uek.krakow.pl.androidinvoicegenerator.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import uek.krakow.pl.androidinvoicegenerator.R;
import uek.krakow.pl.androidinvoicegenerator.generator.PDFGenerator;
import uek.krakow.pl.androidinvoicegenerator.generator.invoicemodel.Faktura;
import uek.krakow.pl.androidinvoicegenerator.generator.invoicemodel.Razem;
import uek.krakow.pl.androidinvoicegenerator.generator.invoicemodel.Tax0;
import uek.krakow.pl.androidinvoicegenerator.generator.invoicemodel.Tax23;
import uek.krakow.pl.androidinvoicegenerator.generator.invoicemodel.Tax5;
import uek.krakow.pl.androidinvoicegenerator.generator.invoicemodel.Tax8;
import uek.krakow.pl.androidinvoicegenerator.generator.invoicemodel.Towar;

public class SummaryFormActivity extends AppCompatActivity {
    EditText ed_naleznoscSlownie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary_form);

        ed_naleznoscSlownie = (EditText) findViewById(R.id.ed_naleznoscSlownie);
    }

    public void toShare(View view) {
        PDFGenerator pdfGenerator = new PDFGenerator();

        Faktura faktura = (Faktura) getIntent().getSerializableExtra("faktura");
        Razem razem = new Razem();
        faktura.razem = razem;
        razem.bruttoWords = ed_naleznoscSlownie.getText().toString();//słownie
        //TODO: Tworzenie Tax przeniesione przed aktywnosć Goods, każdy nowy towar dodaje dane do Tax
        /*
        Razem razem = new Razem();
        faktura.razem = razem;
        faktura.razem.brutto = Integer.toString(0);
        faktura.razem.tax0 = new Tax0();
        faktura.razem.tax5= new Tax5();
        faktura.razem.tax8 = new Tax8();
        faktura.razem.tax23 = new Tax23();

        int razemBrutto = 0;

        faktura.razem.brutto=Integer.toString(razemBrutto);
        */
        File xml = new File(getCacheDir(), "invoice.xml");

        Log.d("hehe", "linia 50");

        Serializer serializer = new Persister();
        try {
            serializer.write(faktura, xml);
        } catch (Exception e) {
            e.printStackTrace();
        }

        File dir = new File(String.valueOf(getFilesDir()));
        File pdf = null;

        Log.d("hehe", "linia 62");

        try {
            pdf = pdfGenerator.generatePDF(getResources().openRawResource(R.raw.faktury_style), new FileInputStream(xml), dir);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Intent intent = new Intent(this, ShareFormActivity.class);
        intent.putExtra("faktura", pdf);
        startActivity(intent);
    }
}
