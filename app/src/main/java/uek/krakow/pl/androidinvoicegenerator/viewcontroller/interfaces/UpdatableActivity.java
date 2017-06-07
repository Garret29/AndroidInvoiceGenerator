package uek.krakow.pl.androidinvoicegenerator.viewcontroller.interfaces;

import org.json.JSONObject;

/**
 * Created by Szymon on 04.06.2017.
 */

public interface UpdatableActivity {
    public void update(JSONObject jsonObject);
    public void  onUpdateFailure();
}
