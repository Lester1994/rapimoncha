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
import una.android.rapimoncha.activities.modulos.producto.ActualizarProductoScreen;
import una.android.rapimoncha.activities.modulos.producto.ListadoProductosScreen;
import una.android.rapimoncha.entidades.CategoriaComercio;
import una.android.rapimoncha.entidades.Producto;
import una.android.rapimoncha.recursos.Recursos;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class SwProducto {

	ArrayList<Producto>productos;
	Activity context;
	ProgressDialog pDialog;
	int success = 0;
	int idcomercio;
	int idproducto;
	Producto producto;
	
	public void getProductosComercios(int idcomercio,Activity context){
		this.idcomercio=idcomercio;
		this.context=context;
		SwProductosComercioGet pro=new SwProductosComercioGet();
		pro.execute();
	}
	
	public void getProducto(int idcomercio,int idproducto,Activity context){
		producto=new Producto();
		
		this.idproducto=idproducto;
		this.idcomercio=idcomercio;
		this.context=context;
		producto.setIdProducto(this.idproducto);
		new SwProductoComercioGet().execute();
		
	}
	
	public void actualizarProducto(Producto producto,int idcomercio,Activity activity){
		this.producto=producto;
		this.context=activity;
		this.idcomercio=idcomercio;
		new SwProductoActualizarProducto().execute();
	}
	public void eliminarProducto(Producto producto,int idcomercio,Activity activity){
		this.producto=producto;
		this.idproducto=producto.getIdProducto();
		this.context=activity;
		this.idcomercio=idcomercio;
		new SwProductoEliminarProducto().execute();
	}
	class SwProductosComercioGet extends AsyncTask<String, String, String> {
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
			HttpPost post = new HttpPost(Recursos.getWebServiceProducto());

			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			nameValuePairs.add(new BasicNameValuePair("accion","obtener_productos_comercio"));
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
					productos = new ArrayList<Producto>();
					int size = resps.length();
					for (int i = 0; i < size; i++) {
						JSONObject obj = resps.getJSONObject(i);
						Log.i("Entre viendo",obj.toString());
						Producto producto =new Producto();
						producto.generateFromJson(obj);
						productos.add(producto);

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
						((ListadoProductosScreen)context).setProductos(productos);
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

//obtener producto
	
	class SwProductoComercioGet extends AsyncTask<String, String, String> {
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
			HttpPost post = new HttpPost(Recursos.getWebServiceProducto());

			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(3);
			nameValuePairs.add(new BasicNameValuePair("accion","obtener_producto_comercio"));
			nameValuePairs.add(new BasicNameValuePair("data",idcomercio+""));
			nameValuePairs.add(new BasicNameValuePair("extra",idproducto+""));
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
					producto.generateFromJson(resps);
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
						((ActualizarProductoScreen)context).setProducto(producto);
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
	
	
     class SwProductoActualizarProducto extends AsyncTask<String, String, String> {
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
			HttpPost post = new HttpPost(Recursos.getWebServiceProducto());

			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(3);
			nameValuePairs.add(new BasicNameValuePair("accion","actualizar_producto"));
			nameValuePairs.add(new BasicNameValuePair("data", producto.getJson().toString()));
			nameValuePairs.add(new BasicNameValuePair("extra", idcomercio+""));
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
								.getApplicationContext(), ListadoProductosScreen.class);
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

     class SwProductoEliminarProducto extends AsyncTask<String, String, String> {
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
 			HttpPost post = new HttpPost(Recursos.getWebServiceProducto());

 			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(3);
 			nameValuePairs.add(new BasicNameValuePair("accion","eliminar_producto"));
 			nameValuePairs.add(new BasicNameValuePair("data", producto.getIdProducto()+""));
 			nameValuePairs.add(new BasicNameValuePair("extra", idcomercio+""));
 			try {
 				post.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
 			} catch (Exception ex) {
 				Log.i("Error", ex.getMessage());
 			}
 			try {
 				Log.i("Resultado", post.toString());
 				HttpResponse resp = httpClient.execute(post);
 				String respStr = EntityUtils.toString(resp.getEntity());
 				Log.i("Mensaje del", respStr);
 				Log.i("codigo  del ", idproducto+"");
 				Log.i("comercio del ", idcomercio+"");
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
 								.getApplicationContext(), ListadoProductosScreen.class);
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


}
