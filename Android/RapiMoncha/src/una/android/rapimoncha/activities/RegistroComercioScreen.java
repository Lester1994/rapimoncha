package una.android.rapimoncha.activities;

import java.util.ArrayList;

import una.android.rapimoncha.Main;
import una.android.rapimoncha.R;
import una.android.rapimoncha.activities.adapter.MenuAdapter;
import una.android.rapimoncha.activities.modulos.configuracion.ListadoConfiguracionesScreen;
import una.android.rapimoncha.entidades.CategoriaComercio;
import una.android.rapimoncha.entidades.Comercio;
import una.android.rapimoncha.entidades.MenuVItem;
import una.android.rapimoncha.entidades.Usuario;
import una.android.rapimoncha.fragments.RegistroComercio1Fragment;
import una.android.rapimoncha.fragments.RegistroComercio2Fragment;
import una.android.rapimoncha.fragments.RegistroComercio3Fragment;
import una.android.rapimoncha.fragments.RegistroComercio4Fragment;
import una.android.rapimoncha.fragments.RegistroComercio5Fragment;
import una.android.rapimoncha.fragments.RegistroComercio6Fragment;
import una.android.rapimoncha.fragments.RegistroComercioSkyFragment;
import una.android.rapimoncha.interfaces.IGenComercio;
import una.android.rapimoncha.recursos.Recursos;
import una.android.rapimoncha.sw.SwCategoriasComercio;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class RegistroComercioScreen extends Activity {

	public static final int MIN_REGISTROFRAGMENTS_INDEX = 0;
	public static final int MAX_REGISTROFRAGMENTS_INDEX = 7;
	Fragment fragment;
	int index_actual = 0;
	Button btn_prev;
	Button btn_next;
	Comercio comercio;
	ArrayList<CategoriaComercio> categorias;
	
	ArrayList<MenuVItem>menuprincipal;
	MenuAdapter adaptermenu;
	Intent intent;
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_comercio1, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		switch (id) {
		case R.id.menu_comercio_action_gohome:
			intent=new Intent(this,WelcomeScreen.class);
			startActivity(intent);
			RegistroComercioScreen.this.finish();
			break;
		

		} 
		
		return super.onOptionsItemSelected(item);
	}
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registrocomercio);
		btn_next = (Button) findViewById(R.id.btn_next);
		btn_prev = (Button) findViewById(R.id.btnprev);
		fragment = null;
		comercio = new Comercio();
		
		//asignar el usuario 
		SharedPreferences sharedPref =getApplication().getSharedPreferences("rapimoncha",Context.MODE_PRIVATE);
		String username = sharedPref.getString(getResources().getString(R.string.function_userloginstatus_usernamekey), null);
		Usuario usuario=new Usuario();
		usuario.setUsername(username);
		comercio.setUsuario(usuario);
		
		initCategorias();
		btn_next.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				++index_actual;
				if (index_actual < MAX_REGISTROFRAGMENTS_INDEX) {
					cargarFragment(index_actual);
					if (!(index_actual < MAX_REGISTROFRAGMENTS_INDEX)) {
						btn_next.setEnabled(false);
						btn_prev.setEnabled(true);
					}
				} else {
					btn_next.setEnabled(false);
					btn_prev.setEnabled(true);
					Toast.makeText(RegistroComercioScreen.this,
							R.string.activity_registrocomercio_java_noiradelante, Toast.LENGTH_LONG).show();
				}
			}
		});

		btn_prev.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				--index_actual;
				if (index_actual >= MIN_REGISTROFRAGMENTS_INDEX) {
					cargarFragment(index_actual);
					if (!(index_actual >= MIN_REGISTROFRAGMENTS_INDEX)) {
						btn_prev.setEnabled(false);
						btn_next.setEnabled(true);
					}
				} else {
					btn_prev.setEnabled(false);
					btn_next.setEnabled(true);
					Toast.makeText(RegistroComercioScreen.this,
							R.string.activity_registrocomercio_java_noiratras, Toast.LENGTH_LONG).show();
				}
			}
		});
		cargarFragment(0);
		
	    //colocar el men�
	    menuprincipal=Recursos.getMenuItemsSecundarios(this);
		adaptermenu=new MenuAdapter(menuprincipal, this);
        ListView lstv=(ListView)findViewById(R.id.menu_container_registrocomercio_111);
        lstv.setAdapter(adaptermenu);

	}

	private void cargarFragment(int index) {
		

		if (fragment != null) {
			if ((fragment instanceof IGenComercio)
					&& ((IGenComercio) fragment).actualizarComercio()) {
				//Toast.makeText(RegistroComercioScreen.this, "Actualizado",Toast.LENGTH_LONG).show();
			}
		}
		switch (index) {
		case 0:
			fragment = new RegistroComercioSkyFragment();
			break;
		case 1:
			fragment = new RegistroComercio1Fragment();
			((RegistroComercio1Fragment) fragment).setComercio(comercio);
			break;
		case 2:
			fragment = new RegistroComercio2Fragment();
			((RegistroComercio2Fragment) fragment).setComercio(comercio);
			((RegistroComercio2Fragment) fragment).setCategorias(categorias);
			break;
		case 3:
			fragment = new RegistroComercio3Fragment();
			((RegistroComercio3Fragment) fragment).setComercio(comercio);
			break;
		case 4:
			fragment = new RegistroComercio4Fragment();
			((RegistroComercio4Fragment) fragment).setComercio(comercio);
			break;
		case 5:
			fragment = new RegistroComercio5Fragment();
			((RegistroComercio5Fragment) fragment).setComercio(comercio);
			break;			
		case 6:
			fragment = new RegistroComercio6Fragment();
			((RegistroComercio6Fragment) fragment).setComercio(comercio);
			break;
		default:

			break;
		}
		if (fragment != null) {
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.frg_container, fragment).commit();
		}

	}

	private void initCategorias() {
		new SwCategoriasComercio().obtenerCategoriasComercio(this, categorias);

	}
	
	public void setCategoriasComercio(ArrayList<CategoriaComercio>categorias){
		this.categorias=categorias;
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Recursos.validateSession(this);//validamos la sesi�n
		
	}
}
