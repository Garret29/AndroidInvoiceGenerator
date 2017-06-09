package uek.krakow.pl.androidinvoicegenerator.viewcontroller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.util.ArrayList;

import uek.krakow.pl.androidinvoicegenerator.R;
import uek.krakow.pl.androidinvoicegenerator.generator.PDFGenerator;

public class DataActivity extends AppCompatActivity {

    Spinner dataSpinner;
    Spinner styleSpinner;
    EditText editText;
    File data;
    File style;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        ArrayAdapter<String> adapterStyle, adapterData;
        ArrayList<String> styles, xmls;

        styles = new ArrayList<>();
        xmls = new ArrayList<>();

        adapterData = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, xmls);
        adapterStyle = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, styles);

        editText = (EditText) findViewById(R.id.nazwaFakturyZXML);
        dataSpinner = (Spinner) findViewById(R.id.spinner2);
        styleSpinner = (Spinner) findViewById(R.id.spinner3);

        dataSpinner.setAdapter(adapterData);
        styleSpinner.setAdapter(adapterStyle);

        for (File f : MainActivity.stylesDir.listFiles()
                ) {
            if (FilenameUtils.getExtension(f.getName()).equals("xsl")) {
                styles.add(f.getName());
            }
        }
        adapterStyle.notifyDataSetChanged();

        for (File f : MainActivity.dataDir.listFiles()
                ) {
            if (FilenameUtils.getExtension(f.getName()).equals("xml")) {
                xmls.add(f.getName());
            }
        }
        adapterData.notifyDataSetChanged();

        dataSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                data = new File(MainActivity.dataDir, parent.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        styleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                style = new File(MainActivity.stylesDir, parent.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void createInvoiceFromData(View v) {
        String filename = editText.getText().toString();
        if (filename.equals("")) {
            Toast.makeText(this, "Wpisz nazwę faktury", Toast.LENGTH_LONG).show();
            return;
        }

        String[] forbiddenChars = new String[]{"\\|", "\\/", "\\?", "\\\\", "\\:", "\\<", "\\>", "\\*", "\\%", "\"", " "};
        for (String s : forbiddenChars) {
            filename = filename.replaceAll(s, "_");
        }

        PDFGenerator pdfGenerator = new PDFGenerator();
        File pdf = pdfGenerator.generatePDF(style, data, MainActivity.invoicesDir, filename, getCacheDir(), MainActivity.fontsDir.getAbsolutePath()+"/Roboto-Regular.ttf", "Roboto");
        Intent intent = new Intent(this, ShareFormActivity.class);
        intent.putExtra("invoice", pdf.getName());
        startActivity(intent);
    }
}
