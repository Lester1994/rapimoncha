package una.android.rapimoncha.activities.modulos.comercio;

import java.util.ArrayList;

import una.android.rapimoncha.Main;
import una.android.rapimoncha.R;
import una.android.rapimoncha.activities.WelcomeScreen;
import una.android.rapimoncha.activities.modulos.configuracion.ListadoConfiguracionesScreen;
import una.android.rapimoncha.entidades.CategoriaComercio;
import una.android.rapimoncha.entidades.Comercio;
import una.android.rapimoncha.entidades.Usuario;
import una.android.rapimoncha.fragments.MostrarComercio1Fragment;
import una.android.rapimoncha.fragments.MostrarComercio2Fragment;
import una.android.rapimoncha.fragments.MostrarComercio3Fragment;
import una.android.rapimoncha.fragments.MostrarComercio4Fragment;
import una.android.rapimoncha.fragments.MostrarComercio5Fragment;
import una.android.rapimoncha.fragments.RegistroComercio1Fragment;
import una.android.rapimoncha.fragments.RegistroComercio2Fragment;
import una.android.rapimoncha.fragments.RegistroComercio3Fragment;
import una.android.rapimoncha.fragments.RegistroComercio4Fragment;
import una.android.rapimoncha.fragments.RegistroComercio5Fragment;
import una.android.rapimoncha.fragments.RegistroComercio6Fragment;
import una.android.rapimoncha.fragments.RegistroComercioSkyFragment;
import una.android.rapimoncha.interfaces.IGenComercio;
import una.android.rapimoncha.sw.SwCategoriasComercio;
import una.android.rapimoncha.sw.SwComercio;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MiPaginaScreen extends Activity {
	String[] data;
	Intent intent;
	ListView navList;
	ArrayAdapter<String> adapter;
	View lastview=null;
	Fragment fragment;
	Comercio comercio;
	ArrayList<CategoriaComercio> categorias;

	private void initCategorias() {
		new SwCategoriasComercio().obtenerCategoriasComercioc(this, categorias);

	}

	public void setCategoriasComercio(ArrayList<CategoriaComercio> categorias) {
		this.categorias = categorias;
	}

	public void setComercio(Comercio comercio) {
		this.comercio = comercio;
		Log.i("Cantidad de geo",
				(comercio != null && comercio.getUbicacionmapa() != null ? comercio
						.getUbicacionmapa().size() : 0)
						+ " <<< este es el tamanio");

		initCategorias();

		cargarFragment(1);

	}

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mipagina);
		Resources r = getApplication().getResources();
		data = new String[] {
				r.getString(R.string.activity_mipagina_menuv_datosgenerales),
				r.getString(R.string.activity_mipagina_menuv_categorias),
				r.getString(R.string.activity_mipagina_menuv_geolocalizacion),
				r.getString(R.string.activity_mipagina_menuv_imagenes),
				r.getString(R.string.activity_mipagina_menuv_preferencias),
				r.getString(R.string.activity_mipagina_menuv_actualizarme),
				r.getString(R.string.activity_mipagina_menuv_irainicio),
				r.getString(R.string.activity_mipagina_menuv_cambiarcomercio) };
		if (comercio == null) {
			comercio = new Comercio();
		}
		// asignar el usuario
		SharedPreferences sharedPref = getApplication().getSharedPreferences(
				"rapimoncha", Context.MODE_PRIVATE);
		String username = sharedPref.getString(
				getResources().getString(
						R.string.function_userloginstatus_usernamekey), null);
		int idcomercio = sharedPref.getInt("idcomercio", 0);
		Usuario usuario = new Usuario();
		usuario.setUsername(username);
		comercio.setUsuario(usuario);
		comercio.setIdComercio(idcomercio);
		new SwComercio().getComerciob(idcomercio, this);

		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, data);

		navList = (ListView) findViewById(R.id.menu_container_mipagina_111);
		navList.setAdapter(adapter);
		navList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
					long arg3) {
				// TODO Auto-generated method stub
				

				arg1.setBackgroundColor(Color.LTGRAY);
				// actualizarListaVertical(arg1);
				if(lastview!=null){
					lastview.setBackgroundColor(Color.WHITE);
				}
				lastview=arg1;
				cargarFragment(pos + 1);

			}

		});
	}

	private void actualizarListaVertical(View view) {
		// TODO Auto-generated method stub

	}

	private void cargarFragment(int index) {

		switch (index) {

		case 1:
			fragment = new MostrarComercio1Fragment();
			((MostrarComercio1Fragment) fragment).setComercio(comercio);
			break;
		case 2:
			fragment = new MostrarComercio2Fragment();
			((MostrarComercio2Fragment) fragment).setComercio(comercio);
			((MostrarComercio2Fragment) fragment).setCategorias(categorias);
			break;
		case 3:
			fragment = new MostrarComercio3Fragment();
			((MostrarComercio3Fragment) fragment).setComercio(comercio);
			break;
		case 4:
			fragment = new MostrarComercio4Fragment();
			((MostrarComercio4Fragment) fragment).setComercio(comercio);
			break;
		case 5:
			fragment = new MostrarComercio5Fragment();
			((MostrarComercio5Fragment) fragment).setComercio(comercio);
			break;
		case 6:
			intent = new Intent(MiPaginaScreen.this,
					ActualizarComercioScreen.class);
			startActivity(intent);
			finish();
			break;
		case 7:
			intent = new Intent(MiPaginaScreen.this, Main.class);
			startActivity(intent);
			finish();
			break;
		case 8:
			intent = new Intent(MiPaginaScreen.this, WelcomeScreen.class);
			startActivity(intent);
			finish();
			break;
		}
		if (fragment != null) {
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.frg_container, fragment).commit();
		}

	}

}
