package una.android.rapimoncha.activities.modulos.producto;

import java.util.ArrayList;

import una.android.rapimoncha.Main;
import una.android.rapimoncha.R;
import una.android.rapimoncha.activities.adapter.MenuAdapter;
import una.android.rapimoncha.activities.adapter.ProductosComercioAdapter;
import una.android.rapimoncha.activities.modulos.RegistroProductoScreen;
import una.android.rapimoncha.activities.modulos.configuracion.ListadoConfiguracionesScreen;
import una.android.rapimoncha.entidades.MenuVItem;
import una.android.rapimoncha.entidades.Producto;
import una.android.rapimoncha.recursos.Recursos;
import una.android.rapimoncha.sw.SwProducto;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class ListadoProductosScreen extends Activity {

	ArrayList<Producto> productos;
	ProductosComercioAdapter productoadapter;
	Menu menu;
	ListView lstviewproductos;
	Producto producto;
	// menus
	MenuItem eliminar;
	MenuItem editar;
	MenuItem agregar;
	int positionaux=-1;
	boolean cambioaux=true;
	
	ArrayList<MenuVItem>menuprincipal;
	MenuAdapter adaptermenu;
	Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listado_productos);
		lstviewproductos = (ListView) findViewById(R.id.list_todosproductos);
		initProductos();
		
		 //colocar el menú
	    menuprincipal=Recursos.getMenuItemsSecundarios(this);
		adaptermenu=new MenuAdapter(menuprincipal, this);
        ListView lstv=(ListView)findViewById(R.id.menu_container_productos_111);
        lstv.setAdapter(adaptermenu);

	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Recursos.validateSession(this);//validamos la sesión
		
	}
	private void initProductos() {
		SharedPreferences sharedPref = getApplication().getSharedPreferences(
				"rapimoncha", Context.MODE_PRIVATE);
		int idcomercio = sharedPref.getInt("idcomercio", 0);
		new SwProducto().getProductosComercios(idcomercio, this);
	}
	
@Override
public boolean onOptionsItemSelected(MenuItem item) {
	// TODO Auto-generated method stub
	boolean resp=true;
	int id=item.getItemId();
	switch (id) {
	case R.id.menu_mproducto_agregar:
		Intent intent=new Intent(this,RegistroProductoScreen.class);
		startActivity(intent);
		break;
	case R.id.menu_mproducto_editar:
		if(producto==null||producto.getIdProducto()<1){
			Toast.makeText(this, "elige 1", Toast.LENGTH_LONG).show();
			
		}else{
			Intent intentactu=new Intent(this,ActualizarProductoScreen.class);
			intentactu.putExtra("producto", producto);
			startActivity(intentactu);
		}
		break;
	case R.id.menu_mproducto_eliminar:
		if(producto==null||producto.getIdProducto()<1){
			Toast.makeText(this, "elige 1", Toast.LENGTH_LONG).show();
			
		}else{
			/*Intent intentactu=new Intent(this,ActualizarProductoScreen.class);
			intentactu.putExtra("producto", producto);
			startActivity(intentactu);*/
			
			  new AlertDialog.Builder(ListadoProductosScreen.this)
              .setIcon(android.R.drawable.ic_dialog_alert)
              .setTitle("Confirmación")
              .setMessage("¿Desea Eliminarlo?")
              .setCancelable(true)
              .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialog, int which) {
                    //eliminar
              		SharedPreferences sharedPref = getApplication().getSharedPreferences(
            				"rapimoncha", Context.MODE_PRIVATE);
            		int idcomercio = sharedPref.getInt("idcomercio", 0);
                	  new SwProducto().eliminarProducto(producto, idcomercio, ListadoProductosScreen.this);
                  }

              })
              .setNegativeButton("No", null)
              .show();			
		}		
		break;
	case R.id.menu_productos_action_gohome:
		intent=new Intent(this,Main.class);
		startActivity(intent);
		ListadoProductosScreen.this.finish();
		break;
	default:
		break;
	}
	return resp;
}


	public void setProductos(ArrayList<Producto> productos) {
		this.productos = productos;
		productoadapter = new ProductosComercioAdapter(this, this.productos);
		lstviewproductos.setAdapter(productoadapter);
		lstviewproductos.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				
				if (arg2==positionaux&&cambioaux) {
					arg1.setSelected(false);
					producto = null;
					updateMenu_Inicial();
					Log.i("entre", "Estoy en donde no deberia!");
					cambioaux=false;
					lstviewproductos.clearChoices();
					productoadapter.notifyDataSetChanged();

				} else {
					arg1.setSelected(true);
					producto = (Producto) arg0.getItemAtPosition(arg2);
					Log.i("entre", "Estoy en donde  deberia!");
					updateMenu_ItemSeleccionado();
					cambioaux=true;
				}
				positionaux=arg2;
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		this.menu = menu;
		getMenuInflater().inflate(R.menu.menu_modulo_productos, menu);
		updateMenu_Inicial();
		return super.onCreateOptionsMenu(menu);
	}

	private void updateMenu_Inicial() {
		eliminar = (MenuItem) menu.findItem(R.id.menu_mproducto_eliminar);
		eliminar.setVisible(false);
		editar = (MenuItem) menu.findItem(R.id.menu_mproducto_editar);
		editar.setVisible(false);
		agregar = (MenuItem) menu.findItem(R.id.menu_mproducto_agregar);
		agregar.setVisible(true);
	}

	private void updateMenu_ItemSeleccionado() {
		eliminar = (MenuItem) menu.findItem(R.id.menu_mproducto_eliminar);
		eliminar.setVisible(true);
		editar = (MenuItem) menu.findItem(R.id.menu_mproducto_editar);
		editar.setVisible(true);
		agregar = (MenuItem) menu.findItem(R.id.menu_mproducto_agregar);
		agregar.setVisible(false);
	}
}
