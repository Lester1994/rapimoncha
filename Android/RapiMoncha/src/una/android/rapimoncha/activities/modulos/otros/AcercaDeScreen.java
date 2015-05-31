package una.android.rapimoncha.activities.modulos.otros;

import java.util.ArrayList;

import una.android.rapimoncha.Main;
import una.android.rapimoncha.R;
import una.android.rapimoncha.activities.adapter.MenuAdapter;
import una.android.rapimoncha.activities.modulos.configuracion.ListadoConfiguracionesScreen;
import una.android.rapimoncha.entidades.MenuVItem;
import una.android.rapimoncha.recursos.Recursos;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class AcercaDeScreen extends Activity {

	ArrayList<MenuVItem> menuprincipal;
	MenuAdapter adaptermenu;
	Intent intent;

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Recursos.validateSession(this);//validamos la sesión
		
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_acercade);
		menuprincipal = Recursos.getMenuPrincipal(this);
		adaptermenu=new MenuAdapter(menuprincipal, this);
		ListView lstv = (ListView) findViewById(R.id.menu_container_acercade_111);
		lstv.setAdapter(adaptermenu);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_acercade, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		switch (id) {
		case R.id.menu_acercade_action_gohome:
			intent=new Intent(this,Main.class);
			startActivity(intent);
			AcercaDeScreen.this.finish();
			break;
		

		} 
		
		return super.onOptionsItemSelected(item);
	}

}
