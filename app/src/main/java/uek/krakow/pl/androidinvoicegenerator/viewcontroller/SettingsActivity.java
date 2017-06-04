package uek.krakow.pl.androidinvoicegenerator.viewcontroller;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.util.ArrayList;

import uek.krakow.pl.androidinvoicegenerator.R;

public class SettingsActivity extends AppCompatActivity {
    private ArrayList<String> styles;
    private ArrayAdapter<String> adapter;

    private EditText styleNameText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ListView listView = (ListView) findViewById(R.id.stylesListView);
        styleNameText = (EditText) findViewById(R.id.styleNameText);

        styles = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, styles);

        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        fillStylesList();
        adapter.notifyDataSetChanged();
    }

    public void wymazDostawce(View view) {
        SharedPreferences p = getSharedPreferences("Dostawca", MODE_PRIVATE);
        if (!p.getBoolean("zapisano", false)) {
            Toast.makeText(this, "Brak zapisanych danych", Toast.LENGTH_SHORT).show();
        } else {
            AlertDialog.Builder a_builder = new AlertDialog.Builder(SettingsActivity.this);
            a_builder.setMessage("Czy na pewno chcesz usunąć dane?")
                    .setCancelable(false)
                    .setPositiveButton("Tak", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            SharedPreferences p = getSharedPreferences("Dostawca", MODE_PRIVATE);
                            SharedPreferences.Editor pe = p.edit();
                            pe.putBoolean("zapisano", false);
                            pe.putString("lokalDost", "");
                            pe.putString("rachunekDost", "");
                            pe.putString("miejscowoscDost", "");
                            pe.putString("emailDost", "");
                            pe.putString("domDost", "");
                            pe.putString("NIPDost", "");
                            pe.putString("nazwaDost", "");
                            pe.putString("ulicaDost", "");
                            pe.putString("kodDost", "");
                            pe.putString("telefonDost", "");
                            pe.putString("bankDost", "");
                            pe.apply();
                            Toast.makeText(SettingsActivity.this, "Usunięto dane", Toast.LENGTH_SHORT).show();
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

        }
    }

    private void fillStylesList() {
        if (MainActivity.stylesDir.exists() && ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            for (File f : MainActivity.stylesDir.listFiles()
                    ) {
                if (FilenameUtils.getExtension(f.getName()).equals("xsl") && !styles.contains(f.getName())){
                    styles.add(f.getName());
                }
            }
        }
    }
}
