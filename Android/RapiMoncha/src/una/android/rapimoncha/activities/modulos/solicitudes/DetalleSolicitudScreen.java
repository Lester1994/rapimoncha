package una.android.rapimoncha.activities.modulos.solicitudes;

import java.util.ArrayList;

import una.android.rapimoncha.Main;
import una.android.rapimoncha.R;
import una.android.rapimoncha.activities.adapter.MenuAdapter;
import una.android.rapimoncha.activities.adapter.ProductosSolicitadosAdapter;
import una.android.rapimoncha.activities.modulos.configuracion.ListadoConfiguracionesScreen;
import una.android.rapimoncha.entidades.MenuVItem;
import una.android.rapimoncha.entidades.Producto;
import una.android.rapimoncha.entidades.SolicitudCliente;
import una.android.rapimoncha.recursos.Recursos;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

public class DetalleSolicitudScreen extends Activity {
	
	ArrayList<Producto>productos;
	ProductosSolicitadosAdapter solicitadosadapter;
	
	ArrayList<MenuVItem>menuprincipal;
	MenuAdapter adaptermenu;
	Intent intent;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detalle_solicitud);
		TextView lbldetallesolicitantedata=(TextView)findViewById(R.id.lbldetallesolicitantedata);
		TextView lbldetallefechadata=(TextView)findViewById(R.id.lbldetallefechadata);
		TextView lbldetalleesperohastadata=(TextView)findViewById(R.id.lbldetalleesperohastadata);
		TextView lbldetallecostodata=(TextView)findViewById(R.id.lbldetallecostodata);
		TextView lblestadodata=(TextView)findViewById(R.id.lblestadodata);
		
		SolicitudCliente solicitud=(SolicitudCliente) getIntent().getExtras().getSerializable("solicitud");
		if(solicitud!=null){
			lbldetallesolicitantedata.setText(solicitud.getUsuario().getNombreCompleto());
			lbldetallefechadata.setText(solicitud.getDateTimeSolicitud());
			lbldetalleesperohastadata.setText(solicitud.getDateTimeEsperoHasta());
			lbldetallecostodata.setText(solicitud.getCoFinalSoli()+"");
			lblestadodata.setText(solicitud.getEsSolicitud()+"");
			this.productos=solicitud.getDetalles();
			if(productos!=null){
				solicitadosadapter=new ProductosSolicitadosAdapter(this, productos);
				ListView lstdetalleproductos=(ListView)findViewById(R.id.lstdetalleproductos);
				lstdetalleproductos.setAdapter(solicitadosadapter);
			}
		}
		
	    //colocar el menú
	    menuprincipal=Recursos.getMenuItemsTerciarios(this);
		adaptermenu=new MenuAdapter(menuprincipal, this);
        ListView lstv=(ListView)findViewById(R.id.menu_container_detallesolicitud_111);
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
		getMenuInflater().inflate(R.menu.menu_detallesolicitud, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		switch (id) {
		case R.id.menu_detallesolicitud_action_goparent:
			intent=new Intent(this,ListadoSolicitudesScreen.class);
			startActivity(intent);
			DetalleSolicitudScreen.this.finish();
			break;
		

		} 
		
		return super.onOptionsItemSelected(item);
	}
}
