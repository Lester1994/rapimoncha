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
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import una.android.rapimoncha.R;
import una.android.rapimoncha.activities.modulos.producto.ListadoProductosScreen;
import una.android.rapimoncha.activities.modulos.solicitudes.ListadoSolicitudesScreen;
import una.android.rapimoncha.entidades.Producto;
import una.android.rapimoncha.entidades.SolicitudCliente;
import una.android.rapimoncha.recursos.Recursos;

public class SwSolicitud {
	ArrayList<SolicitudCliente>solicitudes;
	SolicitudCliente solicitudcliente;
	Activity context;
	ProgressDialog pDialog;
	int success = 0;
	int idcomercio;
	
	public void getSolicitudesComercio(int idcomercio,Activity context){
		this.idcomercio=idcomercio;
		this.context=context;
		new SwSolicitudesComercioGet().execute();
		
}
	public void resolverSolicitudComercio(SolicitudCliente solicitud,Activity context){
		this.context=context;
		this.solicitudcliente=solicitud;
		new SwSolicitudComercioResolver().execute();
	}
	public void eliminarSolicitudComercio(SolicitudCliente solicitud,Activity context){
		this.context=context;
		this.solicitudcliente=solicitud;
		new SwSolicitudComercioEliminar().execute();
	}	
	
	class SwSolicitudesComercioGet extends AsyncTask<String, String, String> {
		String msg = "";

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(context);
			pDialog.setMessage(context.getResources().getString(
					R.string.sw_comercio_java_msginiciocategoriacomercio));
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		protected String doInBackground(String... params) {
			String resul = "1";

			HttpClient httpClient = new DefaultHttpClient();
			// String id = txtId.getText().toString();

			// HttpGet post = new HttpGet(
			// "http://10.211.55.2:8090/exa1/loguin.php?mail="+params[0].toString()+"&&pass="+params[1].toString());
			HttpPost post = new HttpPost(Recursos.getWebServiceSolicitudesComercio());

			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			nameValuePairs.add(new BasicNameValuePair("accion","obtener_solicitudes_comercio"));
			nameValuePairs.add(new BasicNameValuePair("data",idcomercio+""));
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
				Log.i("success",success+" este es el codigo");
				msg = respJSON.getString("mensaje");
				JSONArray resps = respJSON.getJSONArray("detalles");
				if (resps != null) {
					solicitudes = new ArrayList<SolicitudCliente>();
					int size = resps.length();
					for (int i = 0; i < size; i++) {
						JSONObject obj = resps.getJSONObject(i);
						Log.i("Entre viendo",obj.toString());
						SolicitudCliente solicitud =new SolicitudCliente();
						solicitud.generateFromJson(obj);
						solicitudes.add(solicitud);

					}
				}
			} catch (Exception ex) {
				Log.i("ServicioRest", ex.getMessage());
				ex.printStackTrace();
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
						((ListadoSolicitudesScreen)context).setSolicitudes(solicitudes);
					} else {
						Toast.makeText(
								context.getApplicationContext(),
								R.string.sw_comercio_java_msgfincategoriacomercio_error,
								Toast.LENGTH_LONG).show();

					}
				}
			});

		}

	}

	class SwSolicitudComercioResolver extends AsyncTask<String, String, String> {
		String msg = "";

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(context);
			pDialog.setMessage(context.getResources().getString(
					R.string.sw_comercio_java_msginiciocategoriacomercio));
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		protected String doInBackground(String... params) {
			String resul = "1";

			HttpClient httpClient = new DefaultHttpClient();
			// String id = txtId.getText().toString();

			// HttpGet post = new HttpGet(
			// "http://10.211.55.2:8090/exa1/loguin.php?mail="+params[0].toString()+"&&pass="+params[1].toString());
			HttpPost post = new HttpPost(Recursos.getWebServiceSolicitudesComercio());

			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			nameValuePairs.add(new BasicNameValuePair("accion","resolver_solicitud_comercio"));
			nameValuePairs.add(new BasicNameValuePair("data",solicitudcliente.getJson().toString()));
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
				Log.i("success",success+" este es el codigo");
				msg = respJSON.getString("mensaje");
				JSONArray resps = respJSON.getJSONArray("detalles");
			
			} catch (Exception ex) {
				Log.i("ServicioRest", ex.getMessage());
				ex.printStackTrace();
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
						Log.i("Ayyda",msg);
						Toast.makeText(context.getApplicationContext(), "esta es la respuesta "+msg,Toast.LENGTH_LONG).show();
						((ListadoSolicitudesScreen)context).initSolicitudes();
					} else {
						Toast.makeText(
								context.getApplicationContext(),
								R.string.sw_comercio_java_msgfincategoriacomercio_error,
								Toast.LENGTH_LONG).show();

					}
				}
			});

		}

	}


	class SwSolicitudComercioEliminar extends AsyncTask<String, String, String> {
		String msg = "";

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(context);
			pDialog.setMessage(context.getResources().getString(
					R.string.sw_comercio_java_msginiciocategoriacomercio));
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		protected String doInBackground(String... params) {
			String resul = "1";

			HttpClient httpClient = new DefaultHttpClient();
			// String id = txtId.getText().toString();

			// HttpGet post = new HttpGet(
			// "http://10.211.55.2:8090/exa1/loguin.php?mail="+params[0].toString()+"&&pass="+params[1].toString());
			HttpPost post = new HttpPost(Recursos.getWebServiceSolicitudesComercio());

			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			nameValuePairs.add(new BasicNameValuePair("accion","eliminar_solicitud_comercio"));
			nameValuePairs.add(new BasicNameValuePair("data",solicitudcliente.getJson().toString()));
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
				Log.i("success",success+" este es el codigo");
				msg = respJSON.getString("mensaje");
				JSONArray resps = respJSON.getJSONArray("detalles");
			
			} catch (Exception ex) {
				Log.i("ServicioRest", ex.getMessage());
				ex.printStackTrace();
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
						Log.i("Ayyda",msg);
						Toast.makeText(context.getApplicationContext(), "esta es la respuesta "+msg,Toast.LENGTH_LONG).show();
						((ListadoSolicitudesScreen)context).initSolicitudes();
					} else {
						Toast.makeText(
								context.getApplicationContext(),
								R.string.sw_comercio_java_msgfincategoriacomercio_error,
								Toast.LENGTH_LONG).show();

					}
				}
			});

		}

	}

}
