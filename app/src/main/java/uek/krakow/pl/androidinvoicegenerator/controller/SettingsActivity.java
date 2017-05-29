package uek.krakow.pl.androidinvoicegenerator.controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import uek.krakow.pl.androidinvoicegenerator.R;

public class SettingsActivity extends AppCompatActivity {

    private ArrayList<String> styles;
    private ArrayAdapter<String> adapter;

    private Button styleButton;
    private Button remoteStyleButton;
    private CheckBox validationCheckBox;
    private EditText styleNameText;
    private ListView listView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        listView = (ListView) findViewById(R.id.stylesListView);
        styleButton = (Button) findViewById(R.id.styleButton);
        remoteStyleButton = (Button) findViewById(R.id.remoteStyleButton);
        validationCheckBox = (CheckBox) findViewById(R.id.validationCheckBox);
        styleNameText = (EditText) findViewById(R.id.styleNameText);

        styles = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, styles);

        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("validation", validationCheckBox.isChecked());
    }
}
