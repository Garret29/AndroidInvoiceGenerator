package uek.krakow.pl.androidinvoicegenerator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class BuyerFormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_form);
    }

    public void toGoods(View view) {
        Intent intent = new Intent(this, GoodsFormActivity.class);
        startActivity(intent);
    }
}
