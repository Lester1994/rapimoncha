package una.android.rapimoncha.activities;



import una.android.rapimoncha.Main;
import una.android.rapimoncha.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginScreen extends Activity {

	EditText username;
	EditText password;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		username = (EditText) findViewById(R.id.txtusername);
		password = (EditText) findViewById(R.id.txtpassword);
		Button btn_login = (Button) findViewById(R.id.btn_login);
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
					Intent intent = new Intent(LoginScreen.this,Main.class);
					//SI TIENE mas de un usuario le pide la forma de iniciar sesion
			
					startActivity(intent);


			}
		});
	}
}
