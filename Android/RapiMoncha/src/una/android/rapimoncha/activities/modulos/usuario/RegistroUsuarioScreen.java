package una.android.rapimoncha.activities.modulos.usuario;

import java.util.Calendar;

import una.android.rapimoncha.R;
import una.android.rapimoncha.activities.LoginScreen;
import una.android.rapimoncha.entidades.Usuario;
import una.android.rapimoncha.recursos.Recursos;
import una.android.rapimoncha.sw.SwUsuario;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class RegistroUsuarioScreen extends Activity {
	EditText nousuario;
	EditText a1usuario;
	EditText a2usuario;
	EditText user;
	EditText password;
	EditText password2;
	Spinner sexo;
	TextView fenacimiento;
	DatePickerDialog datepickerdialog;
	Usuario usuario;
	Intent intent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registrousuario);
		nousuario = (EditText) findViewById(R.id.txtnousuario);
		a1usuario = (EditText) findViewById(R.id.txtap1usuario);
		a2usuario = (EditText) findViewById(R.id.txtap2usuario);
		user = (EditText) findViewById(R.id.txtrusername);
		password = (EditText) findViewById(R.id.txtrpassword1);
		password2 = (EditText) findViewById(R.id.txtrpassword2);
		sexo = (Spinner) findViewById(R.id.spinnersexo);
		fenacimiento = (TextView) findViewById(R.id.lblfechanac);
		agregarEventosaDialogos();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_registrousuario, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_registrar) {
			//hacer registro del usuario
			actualizarUsuario();
			SwUsuario sw=new SwUsuario();
			sw.agregarusuario(usuario, this);
		}else if(id==R.id.menu_registrarusuario_action_gologin){
			intent = new Intent(this, LoginScreen.class);
			startActivity(intent);
			RegistroUsuarioScreen.this.finish();
		}
		return super.onOptionsItemSelected(item);
	}
	
	private void actualizarUsuario(){
		if(usuario==null){
			usuario=new Usuario();
		}
		usuario.setNoUsuario(nousuario.getText().toString());
		usuario.setA1Usuario(a1usuario.getText().toString());
		usuario.setA2Usuario(a2usuario.getText().toString());
		usuario.setUsername(user.getText().toString());
		usuario.setPassword(password2.getText().toString());
		usuario.setSeUsuario(sexo.getSelectedItem().toString());
		usuario.fechanacString=fenacimiento.getText().toString();
	}
	private void agregarEventosaDialogos() {

		fenacimiento.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (v.getId() == R.id.lblfechanac) {
					if (!datepickerdialog.isShowing()) {
						datepickerdialog.show();
					}
				}
			}
		});

		// Date date = new Date(System.currentTimeMillis());
		Calendar cal = Calendar.getInstance();

		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		datepickerdialog = new DatePickerDialog(RegistroUsuarioScreen.this,
				new DatePickerDialog.OnDateSetListener() {
					@Override
					public void onDateSet(DatePicker view, int year,
							int monthOfYear, int dayOfMonth) {
						fenacimiento.setText(year + "-" + (monthOfYear + 1)
								+ "-" + dayOfMonth);
					}
				}, year, month, day);

		fenacimiento.setText(year + "-" + month + "-" + day);
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Recursos.validateSession(this);//validamos la sesión
		
	}
}
