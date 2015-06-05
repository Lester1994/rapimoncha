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

import una.android.rapimoncha.Main;
import una.android.rapimoncha.R;
import una.android.rapimoncha.activities.RegistroComercioScreen;
import una.android.rapimoncha.activities.WelcomeScreen;
import una.android.rapimoncha.activities.modulos.comercio.ActualizarComercioScreen;
import una.android.rapimoncha.activities.modulos.comercio.MiPaginaScreen;
import una.android.rapimoncha.activities.modulos.comercio.PaginaComercioScreen;
import una.android.rapimoncha.entidades.CategoriaComercio;
import una.android.rapimoncha.entidades.Comercio;
import una.android.rapimoncha.entidades.Usuario;
import una.android.rapimoncha.recursos.Recursos;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class SwCategoriasComercio {
	Activity context;
	Comercio comercio;
	ProgressDialog pDialog;
	int success = 0;

	ArrayList<CategoriaComercio> categorias;

	
	public void obtenerCategoriasComercio(Activity activity,ArrayList<CategoriaComercio>categorias){
		this.categorias=categorias;
		this.context=activity;
		new SwCategoriasComercioGet().execute();
	}
	public void obtenerCategoriasComerciob(Activity activity,ArrayList<CategoriaComercio>categorias){
		this.categorias=categorias;
		this.context=activity;
		new SwCategoriasComercioGetb().execute();
	}
	public void obtenerCategoriasComercioc(Activity activity,ArrayList<CategoriaComercio>categorias){
		this.categorias=categorias;
		this.context=activity;
		new SwCategoriasComercioGetc().execute();
	}	
	public void obtenerCategoriasComerciod(Activity activity,ArrayList<CategoriaComercio>categorias){
		this.categorias=categorias;
		this.context=activity;
		new SwCategoriasComercioGetd().execute();
	}		
	class SwCategoriasComercioGet extends AsyncTask<String, String, String> {
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
			HttpPost post = new HttpPost(
					Recursos.getWebServiceCategoriasComercio());

			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
			nameValuePairs.add(new BasicNameValuePair("accion","obtener_categoriascomercios"));
			
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
					categorias = new ArrayList<CategoriaComercio>();
					int size = resps.length();
					for (int i = 0; i < size; i++) {
						JSONObject obj = resps.getJSONObject(i);
						Log.i("Entre videndo",obj.toString());
						CategoriaComercio categoria = new CategoriaComercio();
						categoria.setIdTipoCategoria(obj.getInt("idCategoria"));
						categoria.setNoTipoCategoria(obj.getString("noCategoria"));
						categorias.add(categoria);

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
						//Toast.makeText(context.getApplicationContext(), msg,Toast.LENGTH_LONG).show();
						((RegistroComercioScreen)context).setCategoriasComercio(categorias);
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

	class SwCategoriasComercioGetb extends AsyncTask<String, String, String> {
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
			HttpPost post = new HttpPost(
					Recursos.getWebServiceCategoriasComercio());

			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
			nameValuePairs.add(new BasicNameValuePair("accion","obtener_categoriascomercios"));
			
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
					categorias = new ArrayList<CategoriaComercio>();
					int size = resps.length();
					for (int i = 0; i < size; i++) {
						JSONObject obj = resps.getJSONObject(i);
						Log.i("Entre videndo",obj.toString());
						CategoriaComercio categoria = new CategoriaComercio();
						categoria.setIdTipoCategoria(obj.getInt("idCategoria"));
						categoria.setNoTipoCategoria(obj.getString("noCategoria"));
						categorias.add(categoria);

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
						//Toast.makeText(context.getApplicationContext(), msg,Toast.LENGTH_LONG).show();
						((ActualizarComercioScreen)context).setCategoriasComercio(categorias);
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
	
	class SwCategoriasComercioGetc extends AsyncTask<String, String, String> {
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
			HttpPost post = new HttpPost(
					Recursos.getWebServiceCategoriasComercio());

			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
			nameValuePairs.add(new BasicNameValuePair("accion","obtener_categoriascomercios"));
			
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
					categorias = new ArrayList<CategoriaComercio>();
					int size = resps.length();
					for (int i = 0; i < size; i++) {
						JSONObject obj = resps.getJSONObject(i);
						Log.i("Entre videndo",obj.toString());
						CategoriaComercio categoria = new CategoriaComercio();
						categoria.setIdTipoCategoria(obj.getInt("idCategoria"));
						categoria.setNoTipoCategoria(obj.getString("noCategoria"));
						categorias.add(categoria);

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
						//Toast.makeText(context.getApplicationContext(), msg,Toast.LENGTH_LONG).show();
						((MiPaginaScreen)context).setCategoriasComercio(categorias);
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
	
	class SwCategoriasComercioGetd extends AsyncTask<String, String, String> {
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
			HttpPost post = new HttpPost(
					Recursos.getWebServiceCategoriasComercio());

			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
			nameValuePairs.add(new BasicNameValuePair("accion","obtener_categoriascomercios"));
			
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
					categorias = new ArrayList<CategoriaComercio>();
					int size = resps.length();
					for (int i = 0; i < size; i++) {
						JSONObject obj = resps.getJSONObject(i);
						Log.i("Entre videndo",obj.toString());
						CategoriaComercio categoria = new CategoriaComercio();
						categoria.setIdTipoCategoria(obj.getInt("idCategoria"));
						categoria.setNoTipoCategoria(obj.getString("noCategoria"));
						categorias.add(categoria);

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
						//Toast.makeText(context.getApplicationContext(), msg,Toast.LENGTH_LONG).show();
						((PaginaComercioScreen)context).setCategoriasComercio(categorias);
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
