package uek.krakow.pl.androidinvoicegenerator.controller;

import android.content.Intent;
import android.net.Uri;
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

        File pdf = (File) getIntent().getSerializableExtra("faktura");
        Uri uri = Uri.fromFile(pdf);
        Intent intent = new Intent(Intent.ACTION_VIEW);
//        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setDataAndType(uri, "application/pdf");
//        intent.setType("text/plain");
//        intent.putExtra(Intent.EXTRA_EMAIL, "xseventythreex@gmail.com");
//        intent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://"+pdf.getAbsolutePath()));
        startActivity(intent);

    }
}
