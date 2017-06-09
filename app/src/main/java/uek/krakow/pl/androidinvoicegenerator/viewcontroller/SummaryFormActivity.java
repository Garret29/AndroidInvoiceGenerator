package uek.krakow.pl.androidinvoicegenerator.viewcontroller;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.apache.commons.io.FilenameUtils;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.File;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import uek.krakow.pl.androidinvoicegenerator.InvoiceGeneratorApplication;
import uek.krakow.pl.androidinvoicegenerator.R;
import uek.krakow.pl.androidinvoicegenerator.generator.PDFGenerator;
import uek.krakow.pl.androidinvoicegenerator.invoicemodel.Invoice;

public class SummaryFormActivity extends AppCompatActivity {
    EditText ed_naleznoscSlownie;
    TextView tv_naleznoscOgolem;
    String style;
    Invoice invoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary_form);
        getSupportActionBar().setTitle("Krok 5 z 5");
        style = "default1.xsl";
        ed_naleznoscSlownie = (EditText) findViewById(R.id.ed_naleznoscSlownie);
        tv_naleznoscOgolem = (TextView) findViewById(R.id.tv_naleznoscOgolem);
        invoice = (Invoice) getIntent().getSerializableExtra("invoice");
        tv_naleznoscOgolem.setText(invoice.summary.gross + "zł");

        ArrayList<String> styles = new ArrayList<>();
        for (File f : MainActivity.stylesDir.listFiles()
                ) {
            if (FilenameUtils.getExtension(f.getName()).equals("xsl") && !styles.contains(f.getName())) {
                styles.add(f.getName());
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, styles);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                style = (String) parent.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private boolean niePuste(String pole){
        String NIEPUSTE_PATTERN = "^\\S.*$";
        Pattern pattern = Pattern.compile(NIEPUSTE_PATTERN);
        Matcher matcher = pattern.matcher(pole);
        return matcher.matches();
    }

    public void toShare(View view) {
        if (!niePuste(ed_naleznoscSlownie.getText().toString())){
            ed_naleznoscSlownie.setError("Puste pole");
            AlertDialog.Builder a_builder = new AlertDialog.Builder(SummaryFormActivity.this);
            a_builder.setMessage("Należność słownie nie została uzupełniona, czy chcesz kontynuować mimo to?")
                    .setCancelable(false)
                    .setPositiveButton("Tak", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            przejdzDalej();
                        }
                    })
                    .setNegativeButton("Nie", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = a_builder.create();
            alert.setTitle("Ostrzeżenie");
            alert.show();
        }else {
            przejdzDalej();
        }


    }

    public void przejdzDalej(){
        //TODO zablokowac możliwość cofania do poprzedniej aktywności, wywala błędną sume po powrocie
        PDFGenerator pdfGenerator = new PDFGenerator();

        Invoice invoice = (Invoice) getIntent().getSerializableExtra("invoice");
        invoice.summary.grossWords = ed_naleznoscSlownie.getText().toString();

        File xml = new File(MainActivity.dataDir, invoice.id + ".xml");
        Serializer serializer = new Persister();
        try {
            serializer.write(invoice, xml);
        } catch (Exception e) {
            e.printStackTrace();
        }

        File pdf;
        File styleFile = new File(MainActivity.stylesDir, style);
        Context context = InvoiceGeneratorApplication.getAppContext();
        pdf = pdfGenerator.generatePDF(styleFile, xml, MainActivity.invoicesDir, invoice.id, context.getCacheDir(), MainActivity.fontsDir.getAbsolutePath()+"/Roboto-Regular.ttf", "Roboto");

        Intent intent = new Intent(this, ShareFormActivity.class);
        intent.putExtra("invoice", pdf.getName());
        startActivity(intent);
    }

}
