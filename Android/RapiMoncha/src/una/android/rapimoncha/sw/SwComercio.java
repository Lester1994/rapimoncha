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
import una.android.rapimoncha.activities.modulos.comercio.ActualizarComercioScreen;
import una.android.rapimoncha.activities.modulos.producto.ActualizarProductoScreen;
import una.android.rapimoncha.entidades.Comercio;
import una.android.rapimoncha.recursos.Recursos;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class SwComercio {
	Activity context;
	Comercio comercio;
	ProgressDialog pDialog;
	int success = 0;
	int idcomercio;
	public void agregarcomercio(Comercio comercio, Activity context) {
		this.comercio = comercio;
		this.context = context;
		SwComercioAgregar swagregar = new SwComercioAgregar();
		swagregar.execute();
	}
	public void actualizarComercio(Comercio comercio, Activity context) {
		this.comercio = comercio;
		this.context = context;
		SwComercioActualizar swagregar = new SwComercioActualizar();
		swagregar.execute();
	}	
	public void agregarProductoComercio(Comercio comercio, Activity context) {
		this.comercio = comercio;
		this.context = context;
		SwComercioAgregarProducto swagregar = new SwComercioAgregarProducto();
		swagregar.execute();
	}	
	
	public void getComercio(int idcomercio,Activity context){
		this.idcomercio=idcomercio;
		this.context=context;
		new SwComercioGet().execute();
	}

	class SwComercioAgregar extends AsyncTask<String, String, String> {
		String msg = "";

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(context);
			pDialog.setMessage(context.getResources().getString(R.string.sw_comercio_java_msginicioregistrocomer));
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
			HttpPost post = new HttpPost(Recursos.getWebServiceComercio());

			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			nameValuePairs.add(new BasicNameValuePair("accion","agregar_comercio"));
			nameValuePairs.add(new BasicNameValuePair("data", comercio.getJson().toString()));
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
								.getApplicationContext(), Main.class);
						intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						context.getApplicationContext().startActivity(intent);
						context.finish();
					}else{
						Toast.makeText(context.getApplicationContext(),
								R.string.sw_comercio_java_msgfinregistrocomer_error,
								Toast.LENGTH_LONG).show();
						
					}
				}
			});

		}

	}

	class SwComercioAgregarProducto extends AsyncTask<String, String, String> {
		String msg = "";

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(context);
			pDialog.setMessage(context.getResources().getString(R.string.sw_comercio_java_msginicioregistroproduct));
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
			HttpPost post = new HttpPost(Recursos.getWebServiceComercio());

			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			nameValuePairs.add(new BasicNameValuePair("accion","agregar_producto"));
			nameValuePairs.add(new BasicNameValuePair("data", comercio.getJson().toString()));
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
								.getApplicationContext(), Main.class);
						intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						context.getApplicationContext().startActivity(intent);
						context.finish();
					}else{
						Toast.makeText(context.getApplicationContext(),
								R.string.sw_comercio_java_msgfinregistroproduct_error,
								Toast.LENGTH_LONG).show();
						
					}
				}
			});

		}

	}

	class SwComercioGet extends AsyncTask<String, String, String> {
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
			HttpPost post = new HttpPost(Recursos.getWebServiceComercio());

			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			nameValuePairs.add(new BasicNameValuePair("accion","obtener_comercio"));
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
				JSONObject resps = respJSON.getJSONObject("detalles");
				if (resps != null) {
					comercio=new Comercio();
					comercio.generateFromJson(resps);
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
						((ActualizarComercioScreen)context).setComercio(comercio);
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
	
	class SwComercioActualizar extends AsyncTask<String, String, String> {
		String msg = "";

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(context);
			pDialog.setMessage(context.getResources().getString(R.string.sw_comercio_java_msginicioregistrocomer));
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
			HttpPost post = new HttpPost(Recursos.getWebServiceComercio());

			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			nameValuePairs.add(new BasicNameValuePair("accion","modificar_comercio"));
			Log.i("Cantidad de geo",(comercio!=null&&comercio.getUbicacionmapa()!=null?comercio.getUbicacionmapa().size():0)+" <<< este es el tamanio");
			nameValuePairs.add(new BasicNameValuePair("data", comercio.getJson().toString()));
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
								.getApplicationContext(), Main.class);
						intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						context.getApplicationContext().startActivity(intent);
						context.finish();
					}else{
						Toast.makeText(context.getApplicationContext(),
								R.string.sw_comercio_java_msgfinregistrocomer_error,
								Toast.LENGTH_LONG).show();
						
					}
				}
			});

		}

	}
	
	
}
