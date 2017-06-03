package uek.krakow.pl.androidinvoicegenerator.viewcontroller;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
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

import java.util.ArrayList;

import uek.krakow.pl.androidinvoicegenerator.R;

public class SettingsActivity extends AppCompatActivity {
    //TODO Dodać możliwość czyszczenia zapisanych danych Dostawcy
    private ArrayList<String> styles;
    private ArrayAdapter<String> adapter;

    private Button styleButton;
    private Button remoteStyleButton;
//    private CheckBox validationCheckBox;
    private EditText styleNameText;
    private ListView listView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        listView = (ListView) findViewById(R.id.stylesListView);
        styleButton = (Button) findViewById(R.id.styleButton);
        remoteStyleButton = (Button) findViewById(R.id.remoteStyleButton);
        styleNameText = (EditText) findViewById(R.id.styleNameText);

        styles = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, styles);

        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


    @Override
    protected void onPause() {
        super.onPause();

//        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putBoolean("validation", validationCheckBox.isChecked());
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
                            pe.commit();
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
}
