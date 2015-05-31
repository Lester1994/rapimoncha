package una.android.rapimoncha.activities.modulos;

import java.util.ArrayList;

import una.android.rapimoncha.Main;
import una.android.rapimoncha.R;
import una.android.rapimoncha.activities.RegistroComercioScreen;
import una.android.rapimoncha.activities.WelcomeScreen;
import una.android.rapimoncha.activities.adapter.MenuAdapter;
import una.android.rapimoncha.activities.modulos.producto.ListadoProductosScreen;
import una.android.rapimoncha.entidades.Comercio;
import una.android.rapimoncha.entidades.MenuVItem;
import una.android.rapimoncha.entidades.Producto;
import una.android.rapimoncha.entidades.Usuario;
import una.android.rapimoncha.fragments.RegistroProducto1Fragment;
import una.android.rapimoncha.fragments.RegistroProducto2Fragment;
import una.android.rapimoncha.fragments.RegistroProducto3Fragment;
import una.android.rapimoncha.fragments.RegistroProductoSkyFragment;
import una.android.rapimoncha.interfaces.IGenComercio;
import una.android.rapimoncha.interfaces.IGenProducto;
import una.android.rapimoncha.recursos.Recursos;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class RegistroProductoScreen extends Activity{
	public static final int MIN_REGISTROFRAGMENTS_INDEX = 0;
	public static final int MAX_REGISTROFRAGMENTS_INDEX = 4;
	Fragment fragment;
	int index_actual = 0;
	Button btn_prev;
	Button btn_next;
	Comercio comercio;
	Producto producto;

	ArrayList<MenuVItem>menuprincipal;
	MenuAdapter adaptermenu;
	Intent intent;
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_producto1, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		switch (id) {
		case R.id.menu_producto_action_gohome:
			intent=new Intent(this,Main.class);
			startActivity(intent);
			RegistroProductoScreen.this.finish();
			break;
		case R.id.menu_producto_action_goparent:
			intent=new Intent(this,ListadoProductosScreen.class);
			startActivity(intent);
			RegistroProductoScreen.this.finish();
			break;		

		} 
		
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registroproducto);
		btn_next = (Button) findViewById(R.id.btn_next_product);
		btn_prev = (Button) findViewById(R.id.btnprev_product);
		fragment = null;
		comercio = new Comercio();
		SharedPreferences sharedPref =getApplication().getSharedPreferences("rapimoncha",Context.MODE_PRIVATE);
		int idempresa = sharedPref.getInt("idcomercio", -1);
		
		comercio.setIdComercio(idempresa);		
		Log.i("agregando producto idempresa ",comercio.getIdComercio()+":"+idempresa);
		producto=new Producto();
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
					Toast.makeText(RegistroProductoScreen.this,
							R.string.activity_registroproducto_java_noiradelante, Toast.LENGTH_LONG).show();
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
					Toast.makeText(RegistroProductoScreen.this,
							R.string.activity_registroproducto_java_noiratras, Toast.LENGTH_LONG).show();
				}
			}
		});
		cargarFragment(0);
		
		
	    //colocar el menú
	    menuprincipal=Recursos.getMenuItemsSecundarios(this);
		adaptermenu=new MenuAdapter(menuprincipal, this);
        ListView lstv=(ListView)findViewById(R.id.menu_container_registroproducto_111);
        lstv.setAdapter(adaptermenu);

	}

	private void cargarFragment(int index) {
		

		if (fragment != null) {
			if ((fragment instanceof IGenProducto)
					&& ((IGenProducto) fragment).actualizarProducto()) {
				//Toast.makeText(RegistroComercioScreen.this, "Actualizado",Toast.LENGTH_LONG).show();
			}
		}
		switch (index) {
		case 0:
			fragment = new RegistroProductoSkyFragment();
			break;
		case 1:
			fragment = new RegistroProducto1Fragment();
			((RegistroProducto1Fragment) fragment).setProducto(producto);
			break;
		case 2:
			fragment = new RegistroProducto2Fragment();
			((RegistroProducto2Fragment) fragment).setProducto(producto);
			break;	
		case 3:
			fragment = new RegistroProducto3Fragment();
			((RegistroProducto3Fragment) fragment).setProducto(producto);
			((RegistroProducto3Fragment) fragment).setComercio(comercio);
			break;				
		
		default:

			break;
		}
		if (fragment != null) {
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.frg_container_product, fragment).commit();
		}

	}

	

}
