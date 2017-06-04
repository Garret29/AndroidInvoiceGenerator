package uek.krakow.pl.androidinvoicegenerator.viewcontroller;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import uek.krakow.pl.androidinvoicegenerator.R;
import uek.krakow.pl.androidinvoicegenerator.invoicemodel.Faktura;
import uek.krakow.pl.androidinvoicegenerator.invoicemodel.Nabywca;
import uek.krakow.pl.androidinvoicegenerator.invoicemodel.Razem;
import uek.krakow.pl.androidinvoicegenerator.invoicemodel.Tax0;
import uek.krakow.pl.androidinvoicegenerator.invoicemodel.Tax23;
import uek.krakow.pl.androidinvoicegenerator.invoicemodel.Tax5;
import uek.krakow.pl.androidinvoicegenerator.invoicemodel.Tax8;
import uek.krakow.pl.androidinvoicegenerator.tasks.GetFromApiTask;

public class BuyerFormActivity extends AppCompatActivity {
    EditText ed_nazwaNabyw, ed_ulicaNabyw, ed_domNabyw, ed_lokalNabyw, ed_miejscowoscNabyw, ed_kodNabyw, ed_NIpNabyw;
    String pustePole = "";
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
        ed_NIpNabyw = (EditText) findViewById(R.id.ed_NIpNabyw);

    }

    public void toGoods(View view) {
        if(!kodPocztowy(ed_kodNabyw.getText().toString()) && niePuste(ed_kodNabyw.getText().toString())){
            ed_kodNabyw.setError("Wymagana forma 00-000");
        }else if(!NIP(ed_NIpNabyw.getText().toString()) && niePuste(ed_NIpNabyw.getText().toString())){
            ed_NIpNabyw.setError("Wymagane 10 cyfr");
        }else if(!niePuste(ed_nazwaNabyw.getText().toString()) || !niePuste(ed_ulicaNabyw.getText().toString()) || !niePuste(ed_domNabyw.getText().toString()) || !niePuste(ed_lokalNabyw.getText().toString()) || !niePuste(ed_miejscowoscNabyw.getText().toString()) || !niePuste(ed_kodNabyw.getText().toString()) || !niePuste(ed_NIpNabyw.getText().toString())){
            if (!niePuste(ed_nazwaNabyw.getText().toString())){
                ed_nazwaNabyw.setError("Puste pole");
                pustePole +="Nazwa,\n";
            }
            if (!niePuste(ed_ulicaNabyw.getText().toString())){
                ed_ulicaNabyw.setError("Puste pole");
                pustePole +="Ulica,\n";
            }
            if (!niePuste(ed_domNabyw.getText().toString())){
                ed_domNabyw.setError("Puste pole");
                pustePole +="Nr domu,\n";
            }
            if (!niePuste(ed_lokalNabyw.getText().toString())){
                ed_lokalNabyw.setError("Puste pole");
                pustePole +="Nr lokalu,\n";
            }
            if (!niePuste(ed_miejscowoscNabyw.getText().toString())){
                ed_miejscowoscNabyw.setError("Puste pole");
                pustePole +="Miejscowość,\n";
            }
            if (!niePuste(ed_kodNabyw.getText().toString())){
                ed_kodNabyw.setError("Puste pole");
                pustePole +="Kod pocztowy,\n";
            }
            if (!niePuste(ed_NIpNabyw.getText().toString())){
                ed_NIpNabyw.setError("Puste pole");
                pustePole +="NIP,\n";
            }
            AlertDialog.Builder a_builder = new AlertDialog.Builder(BuyerFormActivity.this);
            a_builder.setMessage("Nie wszystkie pola zostały uzupełnione:\n"+pustePole+"czy chcesz kontynuować mimo to?")
                    .setCancelable(false)
                    .setPositiveButton("Tak", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            przejdzDalej();
                        }
                    })
                    .setNegativeButton("Nie", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            pustePole="";
                        }
                    });
            AlertDialog alert = a_builder.create();
            alert.setTitle("Ostrzeżenie");
            alert.show();
        }else {
            przejdzDalej();
        }


    }

    public void przejdzDalej(){
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

    private boolean NIP(String pole){
        String NIP_PATTERN = "^[0-9]{10}$";
        Pattern pattern = Pattern.compile(NIP_PATTERN);
        Matcher matcher = pattern.matcher(pole);
        return matcher.matches();
    }
    private boolean niePuste(String pole){
        String NIEPUSTE_PATTERN = "^\\S.*$";
        Pattern pattern = Pattern.compile(NIEPUSTE_PATTERN);
        Matcher matcher = pattern.matcher(pole);
        return matcher.matches();
    }
    private boolean kodPocztowy(String pole){
        String KOD_PATTERN = "^\\d{2}-\\d{3}$";
        Pattern pattern = Pattern.compile(KOD_PATTERN);
        Matcher matcher = pattern.matcher(pole);
        return matcher.matches();
    }

    public void daneGUS(View view) {
        if(!NIP(ed_NIpNabyw.getText().toString())){
            ed_NIpNabyw.setError("Wymagane 10 cyfr");
        }else {
            //TODO pobieranie danych z gus dla nabywcy
            //GetFromApiTask task = new GetFromApiTask(this);
            //task.execute(ed_NIpNabyw.getText().toString());
        }
    }
}
