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

import una.android.rapimoncha.R;
import una.android.rapimoncha.activities.WelcomeScreen;
import una.android.rapimoncha.activities.modulos.usuario.DatosLoginUsuarioScreen;
import una.android.rapimoncha.entidades.Comercio;
import una.android.rapimoncha.entidades.Usuario;
import una.android.rapimoncha.recursos.Recursos;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class SwUsuario {
	Activity context;
	Usuario usuario;
	ProgressDialog pDialog;
	int success = 0;
	ArrayList<Comercio> comercios;

	public void agregarusuario(Usuario usuario, Activity context) {
		this.usuario = usuario;
		this.context = context;
		SwUsuarioAgregar swagregar = new SwUsuarioAgregar();
		swagregar.execute();
	}
	public void loginUsuario(Usuario usuario, Activity context) {
		this.usuario = usuario;
		this.context = context;
		SwUsuarioLogin swagregar = new SwUsuarioLogin();
		swagregar.execute();
	}	
	public void getComerciosUsuarios(Usuario usuario, Activity context) {
		this.usuario = usuario;
		this.context = context;
		SwComerciosUsuarioGet swagregar = new SwComerciosUsuarioGet();
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
								.getApplicationContext(), DatosLoginUsuarioScreen.class);
						intent.putExtra("username", usuario.getUsername());
						intent.putExtra("password", usuario.getPassword());
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

	class SwUsuarioLogin extends AsyncTask<String, String, String> {
		String msg = "";

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(context);
			pDialog.setMessage(context.getResources().getString(R.string.activity_login_java_msginiciologinusuario));
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		protected String doInBackground(String... params) {
			String resul = "1";

			HttpClient httpClient = new DefaultHttpClient();

			HttpPost post = new HttpPost(Recursos.getWebServiceUsuario());

			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			nameValuePairs.add(new BasicNameValuePair("accion","login_usuario"));
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

						//guardar que si se registro el login
						SharedPreferences sharedPref = context.getSharedPreferences("rapimoncha",Context.MODE_PRIVATE);
						SharedPreferences.Editor editor = sharedPref.edit();
						editor.putString(context.getApplicationContext().getResources().getString(R.string.function_userloginstatus_usernamekey), usuario.getUsername());
						editor.putString(context.getApplicationContext().getResources().getString(R.string.function_userloginstatus_statuskey), "activo");
						
						editor.commit();
						String username = sharedPref.getString(context.getResources().getString(R.string.function_userloginstatus_usernamekey), null);
						String status = sharedPref.getString(context.getResources().getString(R.string.function_userloginstatus_statuskey), null);
						
						
						Log.i("username", username+" este es el valor");
						Log.i("status",status+" este es status");
						
						Intent intent = new Intent(context
								.getApplicationContext(), WelcomeScreen.class);
						//registrar el usuari del equpo actual y seleccionar las empresas registradas.
						intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						context.getApplicationContext().startActivity(intent);
						context.finish();
					}else{
						Toast.makeText(context.getApplicationContext(),
								R.string.activity_login_java_msgfinalloginusuarioerror,
								Toast.LENGTH_LONG).show();
						
					}
				}
			});

		}

	}

	

	class SwComerciosUsuarioGet extends AsyncTask<String, String, String> {
		String msg = "";

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(context);
			pDialog.setMessage(context.getResources().getString(
					R.string.sw_comercio_java_msginiciousuariocomercioget));
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
					Recursos.getWebServiceUsuario());

			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			nameValuePairs.add(new BasicNameValuePair("accion","comercio_usuario"));
			nameValuePairs.add(new BasicNameValuePair("data",usuario.getUsername()));
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
					comercios = new ArrayList<Comercio>();
					int size = resps.length();
					for (int i = 0; i < size; i++) {
						JSONObject obj = resps.getJSONObject(i);
						Log.i("Entre videndo",obj.toString());
						Comercio comercio = new Comercio();
						comercio.setIdComercio(obj.getInt("idComercio"));
						comercio.setNoComerncio(obj.getString("noComercio"));
						comercios.add(comercio);

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
						((WelcomeScreen)context).setComercios(comercios);
					} else {
						Toast.makeText(
								context.getApplicationContext(),
								R.string.sw_comercio_java_msgfinusuariocomercioget_error,
								Toast.LENGTH_LONG).show();

					}
				}
			});

		}

	}


}
