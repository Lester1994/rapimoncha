package una.android.rapimoncha.recursos;

import java.io.IOException;
import java.io.OutputStreamWriter;

import una.android.rapimoncha.R;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class Utilidades {
	public static void writeToFile(Activity activity,String data) {
	    try {
	        OutputStreamWriter outputStreamWriter =
	        		new OutputStreamWriter(activity.openFileOutput("config.txt", Context.MODE_PRIVATE));
	        outputStreamWriter.write(data);
	        outputStreamWriter.close();
	    }
	    catch (IOException e) {
	        Log.e("Exception", "File write failed: " + e.toString());
	    } 
	}
	
	public static synchronized boolean isUsuarioLogueado(Activity activity){
		SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
		
		String username = sharedPref.getString(activity.getResources().getString(R.string.function_userloginstatus_usernamekey), null);
		String status = sharedPref.getString(activity.getResources().getString(R.string.function_userloginstatus_statuskey), null);
		
		return username!=null&&status!=null;
	}
}
