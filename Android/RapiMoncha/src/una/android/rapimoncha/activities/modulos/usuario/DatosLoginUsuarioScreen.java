package una.android.rapimoncha.activities.modulos.usuario;

import una.android.rapimoncha.Main;
import una.android.rapimoncha.R;
import una.android.rapimoncha.activities.LoginScreen;
import una.android.rapimoncha.activities.modulos.configuracion.ListadoConfiguracionesScreen;
import una.android.rapimoncha.activities.modulos.otros.ExitScreen;
import una.android.rapimoncha.recursos.Recursos;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class DatosLoginUsuarioScreen extends Activity {

	Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_datosloginusuario);
		TextView lblautenticar = (TextView) findViewById(R.id.lbldatosloginiralogin);

		lblautenticar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(DatosLoginUsuarioScreen.this,
						LoginScreen.class);
				// SI TIENE mas de un usuario le pide la forma de iniciar sesion
				startActivity(intent);
				DatosLoginUsuarioScreen.this.finish();
			}
		});
		setValues();

	}

	private void setValues() {
		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			TextView lblusername = (TextView) findViewById(R.id.lbldatosloginusernamedata);
			TextView lblpassword = (TextView) findViewById(R.id.lbldatosloginpassworddata);
			lblusername.setText(bundle.getString("username"));
			lblpassword.setText(bundle.getString("password"));
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_datosloginusuario, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		switch (id) {
		case R.id.menu_datoslogin_action_gologin:
			intent = new Intent(this, LoginScreen.class);
			startActivity(intent);
			DatosLoginUsuarioScreen.this.finish();
			break;
		case R.id.menu_datoslogin_action_exit:
			intent = new Intent(this, ExitScreen.class);
			startActivity(intent);
			DatosLoginUsuarioScreen.this.finish();
			break;

		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Recursos.validateSession(this);// validamos la sesión

	}
}
