package una.android.rapimoncha.interfaces;

import org.json.JSONObject;

/**
 * Created by FchescO on 02/04/2015.
 * Esta interfaz provee un método que permite transformar el objeto en una estructura Json, para las solicitudes
 */
public interface IJsonable {
    JSONObject getJson();
}
