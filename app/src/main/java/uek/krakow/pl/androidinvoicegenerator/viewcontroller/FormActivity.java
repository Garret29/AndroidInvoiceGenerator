package uek.krakow.pl.androidinvoicegenerator.viewcontroller;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import uek.krakow.pl.androidinvoicegenerator.R;
import uek.krakow.pl.androidinvoicegenerator.invoicemodel.Faktura;

import static android.R.color.holo_red_dark;

public class FormActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    EditText ed_numerFaktury, ed_miejscowoscWystawienia, ed_sposobZaplaty;
    TextView date_DataWystawienia, date_dataDostawy, date_terimnZaplatyDo;
    String terimnZaplatyDo = "Ustaw datę", dataDostawy = "Ustaw datę", dataWystawienia = "Ustaw datę", pustePole ="";
    int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        getSupportActionBar().setTitle("Krok 1 z 5");

        ed_numerFaktury = (EditText) findViewById(R.id.ed_numerFaktury);
        ed_miejscowoscWystawienia = (EditText) findViewById(R.id.ed_miejscowoscWystawienia);
        ed_sposobZaplaty = (EditText) findViewById(R.id.ed_sposobZaplaty);
        date_DataWystawienia = (TextView) findViewById(R.id.date_dataWystawienia);
        date_dataDostawy = (TextView) findViewById(R.id.date_dataDostawy);
        date_terimnZaplatyDo = (TextView) findViewById(R.id.date_terimnZaplatyDo);
    }

    private void pustyNumer() {
        AlertDialog alertDialog = new AlertDialog.Builder(FormActivity.this).create();
        alertDialog.setTitle("Ostrzeżenie");
        alertDialog.setMessage("Proszę podać numer faktury");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    public void setDataWystawienia(View v) {
        id = 1;
        DialogFragment newFragment1 = new DatePickerFragment();
        newFragment1.show(getSupportFragmentManager(), "datePicker");
    }

    public void setDataDostawy(View v) {
        id = 2;
        DialogFragment newFragment2 = new DatePickerFragment();
        newFragment2.show(getSupportFragmentManager(), "datePicker");
    }

    public void setTerimnZaplaty(View v) {
        id = 3;
        DialogFragment newFragment3 = new DatePickerFragment();
        newFragment3.show(getSupportFragmentManager(), "datePicker");
    }



    public static class DatePickerFragment extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(),
                    (DatePickerDialog.OnDateSetListener)
                            getActivity(), year, month, day);
        }

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        if (id == 1) {
            dataWystawienia = Integer.toString(dayOfMonth) + "/" + Integer.toString(month + 1) + "/" + Integer.toString(year);
            date_DataWystawienia.setText(dataWystawienia);
        } else if (id == 2) {
            dataDostawy = Integer.toString(dayOfMonth) + "/" + Integer.toString(month + 1) + "/" + Integer.toString(year);
            date_dataDostawy.setText(dataDostawy);
        } else {
            terimnZaplatyDo = Integer.toString(dayOfMonth) + "/" + Integer.toString(month + 1) + "/" + Integer.toString(year);
            date_terimnZaplatyDo.setText(terimnZaplatyDo);
        }

    }

    private boolean niePuste(String pole){
        String NIEPUSTE_PATTERN = "^\\S.*$";
        Pattern pattern = Pattern.compile(NIEPUSTE_PATTERN);
        Matcher matcher = pattern.matcher(pole);
        return matcher.matches();
    }

    public void toProvider(View view) {
        if (TextUtils.isEmpty(ed_numerFaktury.getText().toString()) || !niePuste(ed_numerFaktury.getText().toString())) {
            pustyNumer();
            ed_numerFaktury.setError("Proszę wpisać numer faktury");
        }else if (!niePuste(ed_miejscowoscWystawienia.getText().toString()) || !niePuste(ed_sposobZaplaty.getText().toString())|| date_DataWystawienia.getText().toString().equals("Ustaw datę") || date_dataDostawy.getText().toString().equals("Ustaw datę")|| date_terimnZaplatyDo.getText().toString().equals("Ustaw datę")){
            if(!niePuste(ed_miejscowoscWystawienia.getText().toString())){
                ed_miejscowoscWystawienia.setError("Puste pole");
                pustePole+="Miejscowość wystawienia, \n";
            }
            if(!niePuste(ed_sposobZaplaty.getText().toString())){
                ed_sposobZaplaty.setError("Puste pole");
                pustePole+="Sposób zapłaty, \n";
            }
            if(date_DataWystawienia.getText().toString().equals("Ustaw datę")){
                date_DataWystawienia.setError("Podaj datę");;
                pustePole+="Data wystawienia, \n";
            }
            if(date_dataDostawy.getText().toString().equals("Ustaw datę")){
                date_dataDostawy.setError("Podaj datę");
                pustePole+="Data dostawy, \n";
            }
            if(date_terimnZaplatyDo.getText().toString().equals("Ustaw datę")){
                date_terimnZaplatyDo.setError("Podaj datę");;
                pustePole+="Termin zapłaty, \n";
            }
            AlertDialog.Builder a_builder = new AlertDialog.Builder(FormActivity.this);
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
        }
        else {
            przejdzDalej();

        }
    }
    public void przejdzDalej(){
        String[] forbiddenChars = new String[]{"\\|", "\\/", "\\?", "\\\\", "\\:", "\\<", "\\>", "\\*", "\\%","\""," "};

        Faktura faktura = new Faktura();
        String nazwa = ed_numerFaktury.getText().toString();
        for (String s : forbiddenChars) {
            nazwa = nazwa.replaceAll(s, "_");
        }
        faktura.id = nazwa;
        faktura.invoiceCity = ed_miejscowoscWystawienia.getText().toString();
        faktura.invoiceDate = dataWystawienia;
        faktura.invoiceShippingDate = dataDostawy;
        faktura.paymentDate = terimnZaplatyDo;
        faktura.paymentMethod = ed_sposobZaplaty.getText().toString();

        Intent intent = new Intent(this, ProviderFormActivity.class);
        intent.putExtra("faktura", faktura);
        startActivity(intent);

    }


}

