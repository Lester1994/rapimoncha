package una.android.rapimoncha.activities.modulos.otros;

import una.android.rapimoncha.Main;
import una.android.rapimoncha.R;
import una.android.rapimoncha.activities.modulos.configuracion.ListadoConfiguracionesScreen;
import una.android.rapimoncha.activities.modulos.solicitudes.DetalleSolicitudScreen;
import una.android.rapimoncha.activities.modulos.solicitudes.ListadoSolicitudesScreen;
import una.android.rapimoncha.entidades.Calificacion;
import una.android.rapimoncha.entidades.Usuario;
import una.android.rapimoncha.recursos.Recursos;
import una.android.rapimoncha.sw.SwCalificacion;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RatingBar;

public class EvaluacionAppScreen extends Activity{
	
	RatingBar rtbutilidad;
	RatingBar rtbfacilidad;
	RatingBar rtbestilo;
	RatingBar rtbsoporte;
	EditText txtnotas;
	
	Intent intent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_evaluacion_aplicacion);
		
		rtbutilidad=(RatingBar)findViewById(R.id.rtbutilidad);
		rtbfacilidad=(RatingBar)findViewById(R.id.rtbfacilidad);
		rtbestilo=(RatingBar)findViewById(R.id.rtbestilo);
		rtbsoporte=(RatingBar)findViewById(R.id.rtbsoporte);
		txtnotas=(EditText)findViewById(R.id.txtnotas);
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
		getMenuInflater().inflate(R.menu.menu_evaluacionapp, menu);
		
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		switch (id) {
		case R.id.action_registrar_evaluacion:
			//hacer registro
			registrarEvaluacion();
			break;
		case R.id.menu_evaluacionapp_action_goparent:
			intent=new Intent(this,ListadoConfiguracionesScreen.class);
			startActivity(intent);
			EvaluacionAppScreen.this.finish();
			break;
		

		} 
		
		return super.onOptionsItemSelected(item);
	}
	
	private void registrarEvaluacion(){
		
		rtbutilidad=(RatingBar)findViewById(R.id.rtbutilidad);
		rtbfacilidad=(RatingBar)findViewById(R.id.rtbfacilidad);
		rtbestilo=(RatingBar)findViewById(R.id.rtbestilo);
		rtbsoporte=(RatingBar)findViewById(R.id.rtbsoporte);
		txtnotas=(EditText)findViewById(R.id.txtnotas);
		//servicio web
		Calificacion calificacion=new Calificacion();
		calificacion.setEstilo((int)rtbestilo.getRating());
		calificacion.setFacilidad((int)rtbfacilidad.getRating());
		calificacion.setNotas(txtnotas.getText().toString());
		calificacion.setSoporte((int)rtbsoporte.getRating());
		calificacion.setUtilidad((int)rtbutilidad.getRating());
		
		SharedPreferences sharedPref =getApplication().getSharedPreferences("rapimoncha",Context.MODE_PRIVATE);
		String username = sharedPref.getString(getResources().getString(R.string.function_userloginstatus_usernamekey), null);
		Usuario user=new Usuario();
		user.setUsername(username);
		calificacion.setUsuario(user);
		new SwCalificacion().agregarCalificacion(calificacion, this);
		
	}
	
	public void openMainActivity(){
		intent=new Intent(this,Main.class);
			startActivity(intent);
			EvaluacionAppScreen.this.finish();
	}
}
