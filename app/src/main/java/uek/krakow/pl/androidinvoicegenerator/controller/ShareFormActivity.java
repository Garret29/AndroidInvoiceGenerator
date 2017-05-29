package uek.krakow.pl.androidinvoicegenerator.controller;

import android.app.Application;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;

import uek.krakow.pl.androidinvoicegenerator.MyApp;
import uek.krakow.pl.androidinvoicegenerator.R;

public class ShareFormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_form);
    }

    public void openFile(View view){
        String mime = MimeTypeMap.getSingleton().getExtensionFromMimeType("PDF");
        MyApp app = (MyApp)  getApplication();

        Uri uri = Uri.fromFile(app.getPdf());
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(uri, mime);
        startActivity(intent);

    }
}
