package una.android.rapimoncha;

import java.util.ArrayList;

import una.android.rapimoncha.activities.adapter.MenuAdapter;
import una.android.rapimoncha.activities.modulos.RegistroProductoScreen;
import una.android.rapimoncha.activities.modulos.comercio.ActualizarComercioScreen;
import una.android.rapimoncha.activities.modulos.configuracion.ListadoConfiguracionesScreen;
import una.android.rapimoncha.activities.modulos.otros.AcercaDeScreen;
import una.android.rapimoncha.activities.modulos.otros.ExitScreen;
import una.android.rapimoncha.activities.modulos.producto.ListadoProductosScreen;
import una.android.rapimoncha.activities.modulos.solicitudes.ListadoSolicitudesScreen;
import una.android.rapimoncha.activities.modulos.usuario.LogoutScreen;
import una.android.rapimoncha.entidades.MenuVItem;
import una.android.rapimoncha.recursos.Recursos;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

public class Main extends Activity {

	ArrayList<MenuVItem>menuprincipal;
	MenuAdapter adaptermenu;
	Intent intent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ImageButton btn=(ImageButton)findViewById(R.id.btn_main_productos);
		menuprincipal=Recursos.getMenuPrincipal(this);
		adaptermenu=new MenuAdapter(menuprincipal, this);
		
        ListView lstv=(ListView)findViewById(R.id.menu_container_111);
        lstv.setAdapter(adaptermenu);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(Main.this,ListadoProductosScreen.class);
				startActivity(intent);
				
			}
		});
		
		ImageButton btncomer=(ImageButton)findViewById(R.id.btn_main_configuracion);
		btncomer.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(Main.this,ListadoConfiguracionesScreen.class);
				startActivity(intent);
				
			}
		});
		
		ImageButton btn_main_solicitudes=(ImageButton)findViewById(R.id.btn_main_solicitudes);
		btn_main_solicitudes.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(Main.this,ListadoSolicitudesScreen.class);
				startActivity(intent);
				
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
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		switch (id) {
		case R.id.menu_main_action_configuration:
			intent=new Intent(this,ListadoConfiguracionesScreen.class);
			startActivity(intent);
			break;
		case R.id.menu_main_action_logout:
			intent=new Intent(Main.this,LogoutScreen.class);
			startActivity(intent);
			break;
		case R.id.menu_main_action_exit:
			intent=new Intent(Main.this,ExitScreen.class);
			startActivity(intent);
			break;
		case R.id.menu_main_action_about:
			intent=new Intent(Main.this,AcercaDeScreen.class);
			startActivity(intent);
			break;			

		} 
		
		return super.onOptionsItemSelected(item);
	}
}
