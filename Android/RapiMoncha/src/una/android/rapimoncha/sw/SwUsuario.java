package una.android.rapimoncha.sw;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import una.android.rapimoncha.Main;
import una.android.rapimoncha.R;
import una.android.rapimoncha.activities.WelcomeScreen;
import una.android.rapimoncha.entidades.Comercio;
import una.android.rapimoncha.entidades.Usuario;
import una.android.rapimoncha.recursos.Recursos;
import una.android.rapimoncha.sw.SwComercio.SwComercioAgregar;
import una.android.rapimoncha.sw.SwComercio.SwComercioAgregarProducto;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class SwUsuario {
	Activity context;
	Usuario usuario;
	ProgressDialog pDialog;
	int success = 0;

	public void agregarusuario(Usuario usuario, Activity context) {
		this.usuario = usuario;
		this.context = context;
		SwUsuarioAgregar swagregar = new SwUsuarioAgregar();
		swagregar.execute();
	}

	class SwUsuarioAgregar extends AsyncTask<String, String, String> {
		String msg = "";

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(context);
			pDialog.setMessage(context.getResources().getString(R.string.sw_usuario_java_msginicioregistrouser));
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		protected String doInBackground(String... params) {
			String resul = "1";

			HttpClient httpClient = new DefaultHttpClient();

			HttpPost post = new HttpPost(Recursos.getWebServiceUsuario());

			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			nameValuePairs.add(new BasicNameValuePair("accion","agregar_usuario"));
			nameValuePairs.add(new BasicNameValuePair("data", usuario.getJson().toString()));
			try {
				post.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
			} catch (Exception ex) {
				Log.i("Error", ex.getMessage());
			}
			try {
				Log.i("Resultado", post.toString());
				HttpResponse resp = httpClient.execute(post);
				String respStr = EntityUtils.toString(resp.getEntity());
				Log.i("Mensaje", respStr);
				JSONObject respJSON = new JSONObject(respStr);

				success = respJSON.getInt("codigo");
				msg = respJSON.getString("mensaje");

			} catch (Exception ex) {
				Log.i("ServicioRest", ex.getMessage());
			}
			return resul;
		}

		/**
		 * After completing background task Dismiss the progress dialog
		 * **/
		protected void onPostExecute(String file_url) {
			// dismiss the dialog after getting all products
			pDialog.dismiss();
			// updating UI from Background Thread
			context.runOnUiThread(new Runnable() {
				public void run() {
					if (success == Recursos.SW_CO_EXITO) {
						Toast.makeText(context.getApplicationContext(), msg,
								Toast.LENGTH_LONG).show();

						Intent intent = new Intent(context
								.getApplicationContext(), WelcomeScreen.class);
						intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						context.getApplicationContext().startActivity(intent);
						context.finish();
					}else{
						Toast.makeText(context.getApplicationContext(),
								R.string.sw_usuario_java_msgfinregistrouser_error,
								Toast.LENGTH_LONG).show();
						
					}
				}
			});

		}

	}


}
