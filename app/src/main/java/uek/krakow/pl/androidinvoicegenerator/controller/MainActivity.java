package uek.krakow.pl.androidinvoicegenerator.controller;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;

import uek.krakow.pl.androidinvoicegenerator.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)==PackageManager.PERMISSION_DENIED){

        }

        File invoicesDir = new File(Environment.getExternalStorageDirectory(), "invoices");
        File stylesDir = new File(Environment.getExternalStorageDirectory(), "invoice_styles");

        if(!invoicesDir.exists()){
            invoicesDir.mkdirs();
        }

        if (!stylesDir.exists()){
            stylesDir.mkdirs();
        }


    }

    public void add(View view) {
        Intent intent = new Intent(this, FormActivity.class);
        startActivity(intent);
    }

    public void goToPrefs(View view){
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
}
