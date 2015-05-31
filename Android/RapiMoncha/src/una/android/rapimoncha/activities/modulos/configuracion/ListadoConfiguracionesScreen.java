package una.android.rapimoncha.activities.modulos.configuracion;


import java.util.ArrayList;

import una.android.rapimoncha.Main;
import una.android.rapimoncha.R;
import una.android.rapimoncha.activities.adapter.MenuAdapter;
import una.android.rapimoncha.activities.modulos.comercio.ActualizarComercioScreen;
import una.android.rapimoncha.activities.modulos.otros.AcercaDeScreen;
import una.android.rapimoncha.activities.modulos.otros.EvaluacionAppScreen;
import una.android.rapimoncha.activities.modulos.otros.ExitScreen;
import una.android.rapimoncha.activities.modulos.usuario.LogoutScreen;
import una.android.rapimoncha.entidades.MenuVItem;
import una.android.rapimoncha.recursos.Recursos;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListadoConfiguracionesScreen extends Activity {
	String titulos[];
	Class<?> clases[];
	ListView lst;
	ArrayList<MenuVItem>menuprincipal;
	MenuAdapter adaptermenu;
	Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listadoconfiguraciones);
		initListas();
		lst=(ListView)findViewById(R.id.lst_configuraciones);
	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
	            android.R.layout.simple_list_item_1, titulos);
	    lst.setAdapter(adapter);
	    
	    lst.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				if(clases[arg2]!=null){
					Intent intent=new Intent(ListadoConfiguracionesScreen.this,clases[arg2]);
					startActivity(intent);
				}
				
			}
		});
	    
	    //colocar el menú
	    menuprincipal=Recursos.getMenuItemsSecundarios(this);
		adaptermenu=new MenuAdapter(menuprincipal, this);
        ListView lstv=(ListView)findViewById(R.id.menu_container_configuraciones_111);
        lstv.setAdapter(adaptermenu);
	}
	
	private void initListas(){
		Resources r=getApplication().getResources();
		titulos=new String[]{
				r.getString(R.string.activity_listadoconfiguraciones_opcion_ActualizarComercio),
				r.getString(R.string.activity_listadoconfiguraciones_opcion_Acerca),
				r.getString(R.string.activity_listadoconfiguraciones_opcion_Preferencias),
				r.getString(R.string.activity_listadoconfiguraciones_opcion_valoracion),
				r.getString(R.string.activity_listadoconfiguraciones_opcion_exit)
				
				
		};
		clases=new Class[]{
				ActualizarComercioScreen.class,
				AcercaDeScreen.class,
				null,
				EvaluacionAppScreen.class,
				ExitScreen.class
		};
		
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
		getMenuInflater().inflate(R.menu.menu_configuracion, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		switch (id) {
		case R.id.menu_configuracion_action_gohome:
			intent=new Intent(this,Main.class);
			startActivity(intent);
			ListadoConfiguracionesScreen.this.finish();
			break;
		

		} 
		
		return super.onOptionsItemSelected(item);
	}
}
