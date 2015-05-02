package una.android.rapimoncha.recursos;

import java.io.IOException;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.content.Context;
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
}
