package uek.krakow.pl.androidinvoicegenerator.viewcontroller;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

import uek.krakow.pl.androidinvoicegenerator.R;

public class Share2Activity extends AppCompatActivity {
    String filename;
    TextView tv_nazwaIstniejacejFaktury;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share2);
        filename = getIntent().getStringExtra("invoice");

        tv_nazwaIstniejacejFaktury = (TextView) findViewById(R.id.tv_nazwa_istniejacej_faktury);
        tv_nazwaIstniejacejFaktury.setText(filename);
    }

    public void openFile(View view) {

        File pdf = new File(MainActivity.invoicesDir, filename);
        Uri uri = Uri.fromFile(pdf);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(uri, "application/pdf");
        startActivity(Intent.createChooser(intent, "Wybierz aplikacje do odczytu faktury"));

    }

    public void toMain(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void share(View view){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Invoice: "+filename);
        intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(MainActivity.invoicesDir, filename)));
        startActivity(Intent.createChooser(intent, "Udostępnij fakturę"));
    }

    public void usunFakture(View view) {
        final Context context = this;
        AlertDialog.Builder a_builder = new AlertDialog.Builder(Share2Activity.this);
        a_builder.setMessage("Czy na pewno chcesz usunąć fakturę "+filename+"?")
                .setCancelable(false)
                .setPositiveButton("Tak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        File file = new File(MainActivity.invoicesDir, filename);
                        boolean result = file.delete();
                        if(result){
                            Toast.makeText(getApplicationContext(), "Poprawnie usunięto fakturę "+filename, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(context, MainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(), "Błąd usuwania", Toast.LENGTH_SHORT).show();
                        }
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

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Brak możliwości cofania po wygenerowaniu pliku faktury.")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
