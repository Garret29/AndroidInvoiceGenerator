package uek.krakow.pl.androidinvoicegenerator.viewcontroller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import uek.krakow.pl.androidinvoicegenerator.R;
import uek.krakow.pl.androidinvoicegenerator.generator.invoicemodel.Faktura;
import uek.krakow.pl.androidinvoicegenerator.generator.invoicemodel.Nabywca;
import uek.krakow.pl.androidinvoicegenerator.generator.invoicemodel.Razem;
import uek.krakow.pl.androidinvoicegenerator.generator.invoicemodel.Tax0;
import uek.krakow.pl.androidinvoicegenerator.generator.invoicemodel.Tax23;
import uek.krakow.pl.androidinvoicegenerator.generator.invoicemodel.Tax5;
import uek.krakow.pl.androidinvoicegenerator.generator.invoicemodel.Tax8;

public class BuyerFormActivity extends AppCompatActivity {
    EditText ed_nazwaNabyw, ed_ulicaNabyw, ed_domNabyw, ed_lokalNabyw, ed_miejscowoscNabyw, ed_kodNabyw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_form);
        getSupportActionBar().setTitle("Krok 3 z 5");

        ed_nazwaNabyw = (EditText) findViewById(R.id.ed_nazwaNabyw);
        ed_ulicaNabyw = (EditText) findViewById(R.id.ed_ulicaNabyw);
        ed_domNabyw = (EditText) findViewById(R.id.ed_domNabyw);
        ed_lokalNabyw = (EditText) findViewById(R.id.ed_lokalNabyw);
        ed_miejscowoscNabyw = (EditText) findViewById(R.id.ed_miejscowoscNabyw);
        ed_kodNabyw = (EditText) findViewById(R.id.ed_kodNabyw);

    }

    public void toGoods(View view) {
        Faktura faktura = (Faktura) getIntent().getSerializableExtra("faktura");
        Nabywca nabywca = new Nabywca();

        nabywca.buyerAppartment=ed_lokalNabyw.getText().toString();
        nabywca.buyerCity=ed_miejscowoscNabyw.getText().toString();
        nabywca.buyerHouse=ed_domNabyw.getText().toString();
        nabywca.buyerName=ed_nazwaNabyw.getText().toString();
        nabywca.buyerPostalCode=ed_kodNabyw.getText().toString();
        nabywca.buyerStreet=ed_ulicaNabyw.getText().toString();

        faktura.nabywca=nabywca;

        Razem razem = new Razem();
        faktura.razem = razem;
        faktura.razem.brutto = Integer.toString(0);
        faktura.razem.netto = Integer.toString(0);
        faktura.razem.vat = Integer.toString(0);
        faktura.razem.tax0 = new Tax0();
        faktura.razem.tax0.brutto=Integer.toString(0);
        faktura.razem.tax0.netto=Integer.toString(0);
        faktura.razem.tax0.VAT=Integer.toString(0);
        faktura.razem.tax5= new Tax5();
        faktura.razem.tax5.brutto=Integer.toString(0);
        faktura.razem.tax5.netto=Integer.toString(0);
        faktura.razem.tax5.VAT=Integer.toString(0);
        faktura.razem.tax8 = new Tax8();
        faktura.razem.tax8.brutto=Integer.toString(0);
        faktura.razem.tax8.netto=Integer.toString(0);
        faktura.razem.tax8.VAT=Integer.toString(0);
        faktura.razem.tax23 = new Tax23();
        faktura.razem.tax23.brutto=Integer.toString(0);
        faktura.razem.tax23.netto=Integer.toString(0);
        faktura.razem.tax23.VAT=Integer.toString(0);

        Intent intent = new Intent(this, GoodsFormActivity.class);
        intent.putExtra("faktura", faktura);
        startActivity(intent);
    }
}
