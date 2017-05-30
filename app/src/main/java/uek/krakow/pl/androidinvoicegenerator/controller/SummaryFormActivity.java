package uek.krakow.pl.androidinvoicegenerator.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
        razem.bruttoWords=ed_naleznoscSlownie.getText().toString();//słownie

        for (Towar t: faktura.towary
             ) {faktura.razem.brutto+= t.priceBrutto;

        }

        faktura.razem = razem;

        File xml=new File(getCacheDir(), "invoice.xml");

        Serializer serializer = new Persister();
        try {
            serializer.write(faktura, xml);
        } catch (Exception e) {
            e.printStackTrace();
        }

        File dir = new File(String.valueOf(getFilesDir()));

        try {
            pdfGenerator.generatePDF(getResources().openRawResource(R.raw.faktury_style), new FileInputStream(xml), dir);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Intent intent = new Intent(this, ShareFormActivity.class);
        startActivity(intent);
    }
}
