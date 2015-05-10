package una.android.rapimoncha.activities.modulos.usuario;

import una.android.rapimoncha.R;
import una.android.rapimoncha.activities.LoginScreen;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class DatosLoginUsuarioScreen extends Activity {

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
}
