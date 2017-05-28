package uek.krakow.pl.androidinvoicegenerator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SummaryFormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary_form);
    }

    public void toShare(View view) {
        Intent intent = new Intent(this, ShareFormActivity.class);
        startActivity(intent);
    }
}
