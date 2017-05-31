package uek.krakow.pl.androidinvoicegenerator.controller;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;

import java.io.File;

import uek.krakow.pl.androidinvoicegenerator.R;

public class ShareFormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_form);
    }

    public void openFile(View view){

        //File pdf = (File) getIntent().getSerializableExtra("faktura");
        String filename = getIntent().getStringExtra("nazwa");


        File pdf = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/invoices/"+filename);
        Uri uri = Uri.fromFile(pdf);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(uri, "application/pdf");
        startActivity(Intent.createChooser(intent, "Wybierz aplikacje do odczytu faktury"));

    }
}
