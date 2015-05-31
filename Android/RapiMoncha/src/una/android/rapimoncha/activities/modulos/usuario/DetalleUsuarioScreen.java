package una.android.rapimoncha.activities.modulos.usuario;

import java.util.ArrayList;

import una.android.rapimoncha.Main;
import una.android.rapimoncha.R;
import una.android.rapimoncha.activities.adapter.MenuAdapter;
import una.android.rapimoncha.activities.modulos.configuracion.ListadoConfiguracionesScreen;
import una.android.rapimoncha.activities.modulos.solicitudes.ListadoSolicitudesScreen;
import una.android.rapimoncha.entidades.MenuVItem;
import una.android.rapimoncha.entidades.Usuario;
import una.android.rapimoncha.recursos.Recursos;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;



public class DetalleUsuarioScreen extends Activity {
	
	ArrayList<MenuVItem>menuprincipal;
	MenuAdapter adaptermenu;
	Intent intent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detalle_usuario1);
		Recursos.validateSession(this);//validamos la sesión
		TextView lblnombreusuariodata=(TextView)findViewById(R.id.lblnombreusuariodata);
		TextView lblap1usuariodata=(TextView)findViewById(R.id.lblap1usuariodata);
		TextView lblap2usuariodata=(TextView)findViewById(R.id.lblap2usuariodata);
		TextView lblfenacimientodata=(TextView)findViewById(R.id.lblfenacimientodata);
		TextView lblsexousuariodata=(TextView)findViewById(R.id.lblsexousuariodata);
		TextView lblusernameusuariodata=(TextView)findViewById(R.id.lblusernameusuariodata);
		
		Usuario usuario=(Usuario)getIntent().getExtras().getSerializable("usuario");
		if(usuario!=null){
			lblap1usuariodata.setText(usuario.getA1Usuario());
			lblap2usuariodata.setText(usuario.getA2Usuario());
			lblnombreusuariodata.setText(usuario.getNoUsuario());
			lblsexousuariodata.setText(usuario.getSeUsuario());
			lblusernameusuariodata.setText(usuario.getUsername());
			lblfenacimientodata.setText(usuario.getDateTimeFeNacimiento());
			
		}
		
		 //colocar el menú
	    menuprincipal=Recursos.getMenuItemsTerciarios(this);
		adaptermenu=new MenuAdapter(menuprincipal, this);
        ListView lstv=(ListView)findViewById(R.id.menu_container_detalleusuario_111);
        lstv.setAdapter(adaptermenu);
		
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
		getMenuInflater().inflate(R.menu.menu_detalleusuario, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		switch (id) {
		case R.id.menu_detalleusuario_action_goparent:
			intent=new Intent(this,ListadoSolicitudesScreen.class);
			startActivity(intent);
			DetalleUsuarioScreen.this.finish();
			break;
		

		} 
		
		return super.onOptionsItemSelected(item);
	}
}
