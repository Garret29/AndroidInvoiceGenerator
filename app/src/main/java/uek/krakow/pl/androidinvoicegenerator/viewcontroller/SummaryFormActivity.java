package uek.krakow.pl.androidinvoicegenerator.viewcontroller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import org.apache.commons.io.FilenameUtils;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.File;
import java.util.ArrayList;

import uek.krakow.pl.androidinvoicegenerator.InvoiceGeneratorApplication;
import uek.krakow.pl.androidinvoicegenerator.R;
import uek.krakow.pl.androidinvoicegenerator.generator.PDFGenerator;
import uek.krakow.pl.androidinvoicegenerator.invoicemodel.Faktura;

public class SummaryFormActivity extends AppCompatActivity {
    EditText ed_naleznoscSlownie;
    String style;

    //TODO Stworzyć więcej stylów faktury
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary_form);
        getSupportActionBar().setTitle("Krok 5 z 5");
        style = "default1.xsl";
        ed_naleznoscSlownie = (EditText) findViewById(R.id.ed_naleznoscSlownie);


        ArrayList<String> styles = new ArrayList<>();
        for (File f : MainActivity.stylesDir.listFiles()
                ) {
            if (FilenameUtils.getExtension(f.getName()).equals("xsl") && !styles.contains(f.getName())){
                styles.add(f.getName());
            }
        }

        ArrayAdapter<String > adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, styles);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                style = (String) parent.getSelectedItem();
                Log.d("hehe", style);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void toShare(View view) {
        PDFGenerator pdfGenerator = new PDFGenerator();

        Faktura faktura = (Faktura) getIntent().getSerializableExtra("faktura");
        faktura.razem.bruttoWords = ed_naleznoscSlownie.getText().toString();

        File xml = new File(MainActivity.dataDir, faktura.id + ".xml");
        Serializer serializer = new Persister();
        try {
            serializer.write(faktura, xml);
        } catch (Exception e) {
            e.printStackTrace();
        }

        File pdf;
        Context context = InvoiceGeneratorApplication.getAppContext();
        pdf = pdfGenerator.generatePDF(style, xml, MainActivity.invoicesDir, faktura.id, context.getCacheDir());

        Intent intent = new Intent(this, ShareFormActivity.class);
        intent.putExtra("faktura", pdf.getName());
        startActivity(intent);
    }

}
