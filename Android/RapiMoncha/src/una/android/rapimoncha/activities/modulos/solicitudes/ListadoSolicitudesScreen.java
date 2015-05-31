package una.android.rapimoncha.activities.modulos.solicitudes;

import java.util.ArrayList;

import una.android.rapimoncha.Main;
import una.android.rapimoncha.R;
import una.android.rapimoncha.activities.adapter.MenuAdapter;
import una.android.rapimoncha.activities.adapter.ProductosComercioAdapter;
import una.android.rapimoncha.activities.adapter.SolicitudesAdapter;
import una.android.rapimoncha.activities.modulos.configuracion.ListadoConfiguracionesScreen;
import una.android.rapimoncha.entidades.MenuVItem;
import una.android.rapimoncha.entidades.Producto;
import una.android.rapimoncha.entidades.SolicitudCliente;
import una.android.rapimoncha.recursos.Recursos;
import una.android.rapimoncha.sw.SwProducto;
import una.android.rapimoncha.sw.SwSolicitud;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ListadoSolicitudesScreen extends Activity {
	
	ArrayList<SolicitudCliente> solicitudes;
	SolicitudesAdapter adapter;
	ListView listviewsolicitudes;
	Menu menu;
	SolicitudCliente solicitud;
	
	ArrayList<MenuVItem>menuprincipal;
	MenuAdapter adaptermenu;
	Intent intent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listado_solicitudes);
		listviewsolicitudes=(ListView)findViewById(R.id.lstsolicitudesusuarios);
		
		initSolicitudes();
		
		 //colocar el menú
	    menuprincipal=Recursos.getMenuItemsSecundarios(this);
		adaptermenu=new MenuAdapter(menuprincipal, this);
        ListView lstv=(ListView)findViewById(R.id.menu_container_solicitudes_111);
        lstv.setAdapter(adaptermenu);
	}
	
	public void initSolicitudes(){
		SharedPreferences sharedPref = getApplication().getSharedPreferences(
				"rapimoncha", Context.MODE_PRIVATE);
		int idcomercio = sharedPref.getInt("idcomercio", 0);
		new SwSolicitud().getSolicitudesComercio(idcomercio, this);
		Log.i("FInf","HE pasado por aqui");
	}
	public void setSolicitudes(ArrayList<SolicitudCliente> solicitudes) {
		this.solicitudes = solicitudes;
		adapter = new SolicitudesAdapter(this, this.solicitudes);
		listviewsolicitudes.setAdapter(adapter);
		listviewsolicitudes.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				solicitud=ListadoSolicitudesScreen.this.solicitudes.get(arg2);
				Intent intent=new Intent(ListadoSolicitudesScreen.this,DetalleSolicitudScreen.class);
				intent.putExtra("solicitud", solicitud);
				startActivity(intent);
				Log.i("Paseeeeee!", solicitud.getIdSolicitud()+" este es el codigo de solicitud");
				Toast.makeText(ListadoSolicitudesScreen.this, "Listo", Toast.LENGTH_LONG).show();
				return true;
			}
		});
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Recursos.validateSession(this);//validamos la sesión
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_solicitud, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		switch (id) {
		case R.id.menu_solicitud_action_gohome:
			intent=new Intent(this,Main.class);
			startActivity(intent);
			ListadoSolicitudesScreen.this.finish();
			break;
		

		} 
		
		return super.onOptionsItemSelected(item);
	}
}
