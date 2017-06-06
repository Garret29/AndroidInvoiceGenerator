package uek.krakow.pl.androidinvoicegenerator;

import android.app.Application;
import android.content.Context;

/**
 * Created by Szymon on 03.06.2017.
 */

public class InvoiceGeneratorApplication extends Application {
    private static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        InvoiceGeneratorApplication.appContext=getApplicationContext();
    }

    public static Context getAppContext() {
        return appContext;
    }
}
