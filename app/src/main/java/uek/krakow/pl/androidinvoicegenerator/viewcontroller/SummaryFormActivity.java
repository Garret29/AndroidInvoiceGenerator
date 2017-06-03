package uek.krakow.pl.androidinvoicegenerator.viewcontroller;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import uek.krakow.pl.androidinvoicegenerator.InvoiceGeneratorApplication;
import uek.krakow.pl.androidinvoicegenerator.R;
import uek.krakow.pl.androidinvoicegenerator.generator.PDFGenerator;
import uek.krakow.pl.androidinvoicegenerator.generator.invoicemodel.Faktura;

public class SummaryFormActivity extends AppCompatActivity {
    EditText ed_naleznoscSlownie;
    private static final int REQUEST_ID_WRITE_PERMISSION = 200;

    //TODO Stworzyć więcej stylów faktury
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary_form);
        getSupportActionBar().setTitle("Krok 5 z 5");

        ed_naleznoscSlownie = (EditText) findViewById(R.id.ed_naleznoscSlownie);
    }

    public void toShare(View view) {
        PDFGenerator pdfGenerator = new PDFGenerator();

        Faktura faktura = (Faktura) getIntent().getSerializableExtra("faktura");
        faktura.razem.bruttoWords = ed_naleznoscSlownie.getText().toString();
//        faktura.razem = new Razem();
//        faktura.razem.brutto = Integer.toString(0);
//        faktura.razem.tax0 = new Tax0();
//        faktura.razem.tax5= new Tax5();
//        faktura.razem.tax8 = new Tax8();
//        faktura.razem.tax23 = new Tax23();
//        int razemBrutto = 0;
//        faktura.razem.brutto=Integer.toString(razemBrutto);

        File xml = new File(MainActivity.dataDir, faktura.id + ".xml");

        Log.d("hehe", "linia 50");

        Serializer serializer = new Persister();
        try {
            serializer.write(faktura, xml);
        } catch (Exception e) {
            e.printStackTrace();
        }

//        File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"invoice_generator"+"/invoices");
        File pdf = null;

        Log.d("hehe", "linia 62");

        Context context = InvoiceGeneratorApplication.getAppContext();
        try {
            pdf = pdfGenerator.generatePDF(getResources().openRawResource(R.raw.faktury_style), new FileInputStream(xml), MainActivity.invoicesDir, faktura.id, context.getCacheDir());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Intent intent = new Intent(this, ShareFormActivity.class);
        intent.putExtra("faktura", pdf.getName());
        startActivity(intent);
    }

    /*
    public void toShare(View view) {
        askPermissionAndWriteFile();
        PDFGenerator pdfGenerator = new PDFGenerator();
        Intent intent = new Intent(this, ShareFormActivity.class);

        Faktura faktura = (Faktura) getIntent().getSerializableExtra("faktura");
        faktura.razem.bruttoWords = ed_naleznoscSlownie.getText().toString();//słownie


        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            File root = Environment.getExternalStorageDirectory();
            File dir = new File(root.getAbsolutePath() + "/MojePliki");
            if (!dir.exists()) {
                dir.mkdir();
            }
            File xml = new File(getCacheDir(), "invoice.xml");

            Log.d("hehe", "linia 50");

            Serializer serializer = new Persister();
            try {
                serializer.write(faktura, xml);
            } catch (Exception e) {
                e.printStackTrace();
            }

            File dirr = getExternalFilesDir("");
            File pdf = null;

            Log.d("hehe", "linia 62");

            try {
                pdf = pdfGenerator.generatePDF(getResources().openRawResource(R.raw.faktury_style), new FileInputStream(xml), dirr);
                intent.putExtra("faktura", pdf.getName());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Toast.makeText(this, "Zapisano", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Nie znaleziono pamięci!", Toast.LENGTH_SHORT).show();
        }

        startActivity(intent);


    }
    */

    private void askPermissionAndWriteFile() {
        boolean canWrite = this.askPermission(REQUEST_ID_WRITE_PERMISSION,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        //
        if (canWrite) {
            Toast.makeText(this, "można", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean askPermission(int requestId, String permissionName) {
        if (android.os.Build.VERSION.SDK_INT >= 23) {

            // Check if we have permission
            int permission = ActivityCompat.checkSelfPermission(this, permissionName);


            if (permission != PackageManager.PERMISSION_GRANTED) {
                // If don't have permission so prompt the user.
                this.requestPermissions(
                        new String[]{permissionName},
                        requestId
                );
                return false;
            }
        }
        return true;
    }


}
