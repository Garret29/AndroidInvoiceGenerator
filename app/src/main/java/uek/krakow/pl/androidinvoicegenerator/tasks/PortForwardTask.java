package uek.krakow.pl.androidinvoicegenerator.tasks;

import android.os.AsyncTask;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import java.util.Properties;

/**
 * Created by Szymon on 29.05.2017.
 */

public class PortForwardTask extends AsyncTask<Void, Void, Void> {

    @Override
    protected Void doInBackground(Void... params) {

        final JSch jsch = new JSch();
        Session session;
        try {
            session = jsch.getSession("s187825", "v-ie.uek.krakow.pl", 22);

            session.setPassword("qwerty12");

            final Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);

            session.connect();
            session.setPortForwardingL(5433, "sbazy.uek.krakow.pl", 5432);
        } catch (JSchException e) {
            e.printStackTrace();
        }



        return null;
    }
}
