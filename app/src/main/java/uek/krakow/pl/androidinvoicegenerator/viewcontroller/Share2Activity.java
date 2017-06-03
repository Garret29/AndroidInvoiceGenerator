package uek.krakow.pl.androidinvoicegenerator.viewcontroller;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.File;

import uek.krakow.pl.androidinvoicegenerator.R;

public class Share2Activity extends AppCompatActivity {
    String filename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share2);
        filename = getIntent().getStringExtra("faktura");
    }

    public void openFile(View view) {

        File pdf = new File(MainActivity.invoicesDir, filename);
        Uri uri = Uri.fromFile(pdf);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(uri, "application/pdf");
        startActivity(Intent.createChooser(intent, "Wybierz aplikacje do odczytu faktury"));

    }

    public void toMain(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void share(View view){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Faktura: "+filename);
        intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(MainActivity.invoicesDir, filename)));
        startActivity(Intent.createChooser(intent, "Wyślij mail z fakturą"));
    }
}
